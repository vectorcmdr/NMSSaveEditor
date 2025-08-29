/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.ActionMap;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.UIResource;
/*     */ import javax.swing.text.BadLocationException;
/*     */ import javax.swing.text.DefaultCaret;
/*     */ import javax.swing.text.Document;
/*     */ import javax.swing.text.JTextComponent;
/*     */ import javax.swing.text.Utilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlatCaret
/*     */   extends DefaultCaret
/*     */   implements UIResource
/*     */ {
/*     */   private static final String KEY_CARET_INFO = "FlatLaf.internal.caretInfo";
/*     */   private final String selectAllOnFocusPolicy;
/*     */   private final boolean selectAllOnMouseClick;
/*     */   private boolean inInstall;
/*     */   private boolean wasFocused;
/*     */   private boolean wasTemporaryLost;
/*     */   private boolean isMousePressed;
/*     */   private boolean isWordSelection;
/*     */   private boolean isLineSelection;
/*     */   private int dragSelectionStart;
/*     */   private int dragSelectionEnd;
/*     */   
/*     */   public FlatCaret(String selectAllOnFocusPolicy, boolean selectAllOnMouseClick) {
/*  64 */     this.selectAllOnFocusPolicy = selectAllOnFocusPolicy;
/*  65 */     this.selectAllOnMouseClick = selectAllOnMouseClick;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void install(JTextComponent c) {
/*  71 */     long[] ci = (long[])c.getClientProperty("FlatLaf.internal.caretInfo");
/*  72 */     if (ci != null) {
/*  73 */       c.putClientProperty("FlatLaf.internal.caretInfo", (Object)null);
/*     */ 
/*     */ 
/*     */       
/*  77 */       if (System.currentTimeMillis() - 500L > ci[3])
/*  78 */         ci = null; 
/*     */     } 
/*  80 */     if (ci != null)
/*     */     {
/*     */       
/*  83 */       setBlinkRate((int)ci[2]);
/*     */     }
/*     */     
/*  86 */     this.inInstall = true;
/*     */     try {
/*  88 */       super.install(c);
/*     */     } finally {
/*  90 */       this.inInstall = false;
/*     */     } 
/*     */     
/*  93 */     if (ci != null) {
/*     */       
/*  95 */       select((int)ci[1], (int)ci[0]);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 102 */       if (isSelectionVisible()) {
/* 103 */         EventQueue.invokeLater(() -> {
/*     */               if (getComponent() == null) {
/*     */                 return;
/*     */               }
/*     */               if (isSelectionVisible()) {
/*     */                 setSelectionVisible(false);
/*     */                 setSelectionVisible(true);
/*     */               } 
/*     */             });
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void deinstall(JTextComponent c) {
/* 119 */     c.putClientProperty("FlatLaf.internal.caretInfo", new long[] {
/* 120 */           getDot(), 
/* 121 */           getMark(), 
/* 122 */           getBlinkRate(), 
/* 123 */           System.currentTimeMillis()
/*     */         });
/*     */     
/* 126 */     super.deinstall(c);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void adjustVisibility(Rectangle nloc) {
/* 131 */     JTextComponent c = getComponent();
/* 132 */     if (c != null && c.getUI() instanceof FlatTextFieldUI) {
/*     */ 
/*     */ 
/*     */       
/* 136 */       Rectangle r = ((FlatTextFieldUI)c.getUI()).getVisibleEditorRect();
/* 137 */       if (r != null)
/* 138 */         nloc.x -= r.x - (c.getInsets()).left; 
/*     */     } 
/* 140 */     super.adjustVisibility(nloc);
/*     */   }
/*     */ 
/*     */   
/*     */   public void focusGained(FocusEvent e) {
/* 145 */     if (!this.inInstall && !this.wasTemporaryLost && (!this.isMousePressed || this.selectAllOnMouseClick))
/* 146 */       selectAllOnFocusGained(); 
/* 147 */     this.wasTemporaryLost = false;
/* 148 */     this.wasFocused = true;
/*     */     
/* 150 */     super.focusGained(e);
/*     */   }
/*     */ 
/*     */   
/*     */   public void focusLost(FocusEvent e) {
/* 155 */     this.wasTemporaryLost = e.isTemporary();
/* 156 */     super.focusLost(e);
/*     */   }
/*     */ 
/*     */   
/*     */   public void mousePressed(MouseEvent e) {
/* 161 */     this.isMousePressed = true;
/* 162 */     super.mousePressed(e);
/*     */     
/* 164 */     JTextComponent c = getComponent();
/*     */ 
/*     */     
/* 167 */     this.isWordSelection = (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e) && !e.isConsumed());
/*     */ 
/*     */     
/* 170 */     this.isLineSelection = (e.getClickCount() == 3 && SwingUtilities.isLeftMouseButton(e) && (!e.isConsumed() || c.getDragEnabled()));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 175 */     if (this.isLineSelection) {
/* 176 */       ActionMap actionMap = c.getActionMap();
/*     */ 
/*     */       
/* 179 */       Action selectLineAction = (actionMap != null) ? actionMap.get("select-line") : null;
/* 180 */       if (selectLineAction != null) {
/* 181 */         selectLineAction.actionPerformed(new ActionEvent(c, 1001, null, e
/* 182 */               .getWhen(), e.getModifiers()));
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 187 */     if (this.isWordSelection || this.isLineSelection) {
/* 188 */       int mark = getMark();
/* 189 */       int dot = getDot();
/* 190 */       this.dragSelectionStart = Math.min(dot, mark);
/* 191 */       this.dragSelectionEnd = Math.max(dot, mark);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseReleased(MouseEvent e) {
/* 197 */     this.isMousePressed = false;
/* 198 */     this.isWordSelection = false;
/* 199 */     this.isLineSelection = false;
/* 200 */     super.mouseReleased(e);
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseDragged(MouseEvent e) {
/* 205 */     if ((this.isWordSelection || this.isLineSelection) && 
/* 206 */       !e.isConsumed() && SwingUtilities.isLeftMouseButton(e)) {
/*     */ 
/*     */ 
/*     */       
/* 210 */       JTextComponent c = getComponent();
/* 211 */       int pos = c.viewToModel(e.getPoint());
/* 212 */       if (pos < 0) {
/*     */         return;
/*     */       }
/*     */       try {
/* 216 */         if (pos > this.dragSelectionEnd)
/* 217 */         { select(this.dragSelectionStart, this.isWordSelection ? Utilities.getWordEnd(c, pos) : Utilities.getRowEnd(c, pos)); }
/* 218 */         else if (pos < this.dragSelectionStart)
/* 219 */         { select(this.dragSelectionEnd, this.isWordSelection ? Utilities.getWordStart(c, pos) : Utilities.getRowStart(c, pos)); }
/*     */         else
/* 221 */         { select(this.dragSelectionStart, this.dragSelectionEnd); } 
/* 222 */       } catch (BadLocationException ex) {
/* 223 */         UIManager.getLookAndFeel().provideErrorFeedback(c);
/*     */       } 
/*     */     } else {
/* 226 */       super.mouseDragged(e);
/*     */     } 
/*     */   }
/*     */   protected void selectAllOnFocusGained() {
/* 230 */     JTextComponent c = getComponent();
/* 231 */     Document doc = c.getDocument();
/* 232 */     if (doc == null || !c.isEnabled() || !c.isEditable() || FlatUIUtils.isCellEditor(c)) {
/*     */       return;
/*     */     }
/* 235 */     Object selectAllOnFocusPolicy = c.getClientProperty("JTextField.selectAllOnFocusPolicy");
/* 236 */     if (selectAllOnFocusPolicy == null) {
/* 237 */       selectAllOnFocusPolicy = this.selectAllOnFocusPolicy;
/*     */     }
/* 239 */     if (selectAllOnFocusPolicy == null || "never".equals(selectAllOnFocusPolicy)) {
/*     */       return;
/*     */     }
/* 242 */     if (!"always".equals(selectAllOnFocusPolicy)) {
/*     */ 
/*     */ 
/*     */       
/* 246 */       if (this.wasFocused) {
/*     */         return;
/*     */       }
/*     */       
/* 250 */       int dot = getDot();
/* 251 */       int mark = getMark();
/* 252 */       if (dot != mark || dot != doc.getLength()) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */     
/* 257 */     if (c instanceof javax.swing.JFormattedTextField) {
/* 258 */       EventQueue.invokeLater(() -> {
/*     */             if (getComponent() == null) {
/*     */               return;
/*     */             }
/*     */             select(0, doc.getLength());
/*     */           });
/*     */     } else {
/* 265 */       select(0, doc.getLength());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void select(int mark, int dot) {
/* 270 */     if (mark != getMark())
/* 271 */       setDot(mark); 
/* 272 */     if (dot != getDot()) {
/* 273 */       moveDot(dot);
/*     */     }
/*     */   }
/*     */   
/*     */   public void scrollCaretToVisible() {
/* 278 */     JTextComponent c = getComponent();
/* 279 */     if (c == null || c.getUI() == null) {
/*     */       return;
/*     */     }
/*     */     try {
/* 283 */       Rectangle loc = c.getUI().modelToView(c, getDot(), getDotBias());
/* 284 */       if (loc != null) {
/* 285 */         adjustVisibility(loc);
/* 286 */         damage(loc);
/*     */       } 
/* 288 */     } catch (BadLocationException badLocationException) {}
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatCaret.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */