package com.formdev.flatlaf.ui;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JFormattedTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.UIResource;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.Utilities;

public class FlatCaret extends DefaultCaret implements UIResource {
   private static final String KEY_CARET_INFO = "FlatLaf.internal.caretInfo";
   private final String selectAllOnFocusPolicy;
   private final boolean selectAllOnMouseClick;
   private boolean inInstall;
   private boolean wasFocused;
   private boolean wasTemporaryLost;
   private boolean isMousePressed;
   private boolean isWordSelection;
   private boolean isLineSelection;
   private int dragSelectionStart;
   private int dragSelectionEnd;

   public FlatCaret(String selectAllOnFocusPolicy, boolean selectAllOnMouseClick) {
      this.selectAllOnFocusPolicy = selectAllOnFocusPolicy;
      this.selectAllOnMouseClick = selectAllOnMouseClick;
   }

   public void install(JTextComponent c) {
      long[] ci = (long[])c.getClientProperty("FlatLaf.internal.caretInfo");
      if (ci != null) {
         c.putClientProperty("FlatLaf.internal.caretInfo", (Object)null);
         if (System.currentTimeMillis() - 500L > ci[3]) {
            ci = null;
         }
      }

      if (ci != null) {
         this.setBlinkRate((int)ci[2]);
      }

      this.inInstall = true;

      try {
         super.install(c);
      } finally {
         this.inInstall = false;
      }

      if (ci != null) {
         this.select((int)ci[1], (int)ci[0]);
         if (this.isSelectionVisible()) {
            EventQueue.invokeLater(() -> {
               if (this.getComponent() != null) {
                  if (this.isSelectionVisible()) {
                     this.setSelectionVisible(false);
                     this.setSelectionVisible(true);
                  }

               }
            });
         }
      }

   }

   public void deinstall(JTextComponent c) {
      c.putClientProperty("FlatLaf.internal.caretInfo", new long[]{(long)this.getDot(), (long)this.getMark(), (long)this.getBlinkRate(), System.currentTimeMillis()});
      super.deinstall(c);
   }

   protected void adjustVisibility(Rectangle nloc) {
      JTextComponent c = this.getComponent();
      if (c != null && c.getUI() instanceof FlatTextFieldUI) {
         Rectangle r = ((FlatTextFieldUI)c.getUI()).getVisibleEditorRect();
         if (r != null) {
            nloc.x -= r.x - c.getInsets().left;
         }
      }

      super.adjustVisibility(nloc);
   }

   public void focusGained(FocusEvent e) {
      if (!this.inInstall && !this.wasTemporaryLost && (!this.isMousePressed || this.selectAllOnMouseClick)) {
         this.selectAllOnFocusGained();
      }

      this.wasTemporaryLost = false;
      this.wasFocused = true;
      super.focusGained(e);
   }

   public void focusLost(FocusEvent e) {
      this.wasTemporaryLost = e.isTemporary();
      super.focusLost(e);
   }

   public void mousePressed(MouseEvent e) {
      this.isMousePressed = true;
      super.mousePressed(e);
      JTextComponent c = this.getComponent();
      this.isWordSelection = e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e) && !e.isConsumed();
      this.isLineSelection = e.getClickCount() == 3 && SwingUtilities.isLeftMouseButton(e) && (!e.isConsumed() || c.getDragEnabled());
      if (this.isLineSelection) {
         ActionMap actionMap = c.getActionMap();
         Action selectLineAction = actionMap != null ? actionMap.get("select-line") : null;
         if (selectLineAction != null) {
            selectLineAction.actionPerformed(new ActionEvent(c, 1001, (String)null, e.getWhen(), e.getModifiers()));
         }
      }

      if (this.isWordSelection || this.isLineSelection) {
         int mark = this.getMark();
         int dot = this.getDot();
         this.dragSelectionStart = Math.min(dot, mark);
         this.dragSelectionEnd = Math.max(dot, mark);
      }

   }

   public void mouseReleased(MouseEvent e) {
      this.isMousePressed = false;
      this.isWordSelection = false;
      this.isLineSelection = false;
      super.mouseReleased(e);
   }

   public void mouseDragged(MouseEvent e) {
      if ((this.isWordSelection || this.isLineSelection) && !e.isConsumed() && SwingUtilities.isLeftMouseButton(e)) {
         JTextComponent c = this.getComponent();
         int pos = c.viewToModel(e.getPoint());
         if (pos < 0) {
            return;
         }

         try {
            if (pos > this.dragSelectionEnd) {
               this.select(this.dragSelectionStart, this.isWordSelection ? Utilities.getWordEnd(c, pos) : Utilities.getRowEnd(c, pos));
            } else if (pos < this.dragSelectionStart) {
               this.select(this.dragSelectionEnd, this.isWordSelection ? Utilities.getWordStart(c, pos) : Utilities.getRowStart(c, pos));
            } else {
               this.select(this.dragSelectionStart, this.dragSelectionEnd);
            }
         } catch (BadLocationException var5) {
            UIManager.getLookAndFeel().provideErrorFeedback(c);
         }
      } else {
         super.mouseDragged(e);
      }

   }

   protected void selectAllOnFocusGained() {
      JTextComponent c = this.getComponent();
      Document doc = c.getDocument();
      if (doc != null && c.isEnabled() && c.isEditable() && !FlatUIUtils.isCellEditor(c)) {
         Object selectAllOnFocusPolicy = c.getClientProperty("JTextField.selectAllOnFocusPolicy");
         if (selectAllOnFocusPolicy == null) {
            selectAllOnFocusPolicy = this.selectAllOnFocusPolicy;
         }

         if (selectAllOnFocusPolicy != null && !"never".equals(selectAllOnFocusPolicy)) {
            if (!"always".equals(selectAllOnFocusPolicy)) {
               if (this.wasFocused) {
                  return;
               }

               int dot = this.getDot();
               int mark = this.getMark();
               if (dot != mark || dot != doc.getLength()) {
                  return;
               }
            }

            if (c instanceof JFormattedTextField) {
               EventQueue.invokeLater(() -> {
                  if (this.getComponent() != null) {
                     this.select(0, doc.getLength());
                  }
               });
            } else {
               this.select(0, doc.getLength());
            }

         }
      }
   }

   private void select(int mark, int dot) {
      if (mark != this.getMark()) {
         this.setDot(mark);
      }

      if (dot != this.getDot()) {
         this.moveDot(dot);
      }

   }

   public void scrollCaretToVisible() {
      JTextComponent c = this.getComponent();
      if (c != null && c.getUI() != null) {
         try {
            Rectangle loc = c.getUI().modelToView(c, this.getDot(), this.getDotBias());
            if (loc != null) {
               this.adjustVisibility(loc);
               this.damage(loc);
            }
         } catch (BadLocationException var3) {
         }

      }
   }
}
