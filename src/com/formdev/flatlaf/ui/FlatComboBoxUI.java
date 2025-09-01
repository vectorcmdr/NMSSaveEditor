package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.icons.FlatCheckBoxMenuItemIcon;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D.Float;
import java.beans.PropertyChangeListener;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.CellRendererPane;
import javax.swing.DefaultListCellRenderer;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListCellRenderer;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.plaf.basic.BasicComboBoxUI.ComboBoxLayoutManager;
import javax.swing.plaf.basic.BasicComboBoxUI.FocusHandler;
import javax.swing.text.JTextComponent;

@FlatStylingSupport.StyleableField(
   cls = BasicComboBoxUI.class,
   key = "padding"
)
public class FlatComboBoxUI extends BasicComboBoxUI implements FlatStylingSupport.StyleableUI, FlatStylingSupport.StyleableLookupProvider {
   @FlatStylingSupport.Styleable
   protected int minimumWidth;
   @FlatStylingSupport.Styleable
   protected int editorColumns;
   @FlatStylingSupport.Styleable
   protected String buttonStyle;
   @FlatStylingSupport.Styleable
   protected String arrowType;
   protected boolean isIntelliJTheme;
   private Color background;
   @FlatStylingSupport.Styleable
   protected Color editableBackground;
   @FlatStylingSupport.Styleable
   protected Color focusedBackground;
   @FlatStylingSupport.Styleable
   protected Color disabledBackground;
   @FlatStylingSupport.Styleable
   protected Color disabledForeground;
   @FlatStylingSupport.Styleable
   protected Color buttonBackground;
   @FlatStylingSupport.Styleable
   protected Color buttonEditableBackground;
   @FlatStylingSupport.Styleable
   protected Color buttonFocusedBackground;
   @FlatStylingSupport.Styleable
   protected float buttonSeparatorWidth;
   @FlatStylingSupport.Styleable
   protected Color buttonSeparatorColor;
   @FlatStylingSupport.Styleable
   protected Color buttonDisabledSeparatorColor;
   @FlatStylingSupport.Styleable
   protected Color buttonArrowColor;
   @FlatStylingSupport.Styleable
   protected Color buttonDisabledArrowColor;
   @FlatStylingSupport.Styleable
   protected Color buttonHoverArrowColor;
   @FlatStylingSupport.Styleable
   protected Color buttonPressedArrowColor;
   @FlatStylingSupport.Styleable
   protected Color popupBackground;
   @FlatStylingSupport.Styleable
   protected Insets popupInsets;
   @FlatStylingSupport.Styleable
   protected Insets selectionInsets;
   @FlatStylingSupport.Styleable
   protected int selectionArc;
   private MouseListener hoverListener;
   protected boolean hover;
   protected boolean pressed;
   private FlatComboBoxUI.CellPaddingBorder paddingBorder;
   private Map<String, Object> oldStyleValues;
   private AtomicBoolean borderShared;

   public static ComponentUI createUI(JComponent c) {
      return new FlatComboBoxUI();
   }

   public void installUI(JComponent c) {
      if (FlatUIUtils.needsLightAWTPeer(c)) {
         FlatUIUtils.runWithLightAWTPeerUIDefaults(() -> {
            this.installUIImpl(c);
         });
      } else {
         this.installUIImpl(c);
      }

   }

   private void installUIImpl(JComponent c) {
      super.installUI(c);
      this.installStyle();
   }

   protected void installListeners() {
      super.installListeners();
      this.hoverListener = new MouseAdapter() {
         public void mouseEntered(MouseEvent e) {
            FlatComboBoxUI.this.hover = true;
            this.repaintArrowButton();
         }

         public void mouseExited(MouseEvent e) {
            FlatComboBoxUI.this.hover = false;
            this.repaintArrowButton();
         }

         public void mousePressed(MouseEvent e) {
            FlatComboBoxUI.this.pressed = true;
            this.repaintArrowButton();
         }

         public void mouseReleased(MouseEvent e) {
            FlatComboBoxUI.this.pressed = false;
            this.repaintArrowButton();
         }

         private void repaintArrowButton() {
            if (FlatComboBoxUI.this.arrowButton != null && !FlatComboBoxUI.this.comboBox.isEditable()) {
               FlatComboBoxUI.this.arrowButton.repaint();
            }

         }
      };
      this.comboBox.addMouseListener(this.hoverListener);
   }

   protected void uninstallListeners() {
      super.uninstallListeners();
      this.comboBox.removeMouseListener(this.hoverListener);
      this.hoverListener = null;
   }

   protected void installDefaults() {
      super.installDefaults();
      LookAndFeel.installProperty(this.comboBox, "opaque", false);
      this.minimumWidth = UIManager.getInt("ComboBox.minimumWidth");
      this.editorColumns = UIManager.getInt("ComboBox.editorColumns");
      this.buttonStyle = UIManager.getString("ComboBox.buttonStyle");
      this.arrowType = UIManager.getString("Component.arrowType");
      this.isIntelliJTheme = UIManager.getBoolean("Component.isIntelliJTheme");
      this.background = UIManager.getColor("ComboBox.background");
      this.editableBackground = UIManager.getColor("ComboBox.editableBackground");
      this.focusedBackground = UIManager.getColor("ComboBox.focusedBackground");
      this.disabledBackground = UIManager.getColor("ComboBox.disabledBackground");
      this.disabledForeground = UIManager.getColor("ComboBox.disabledForeground");
      this.buttonBackground = UIManager.getColor("ComboBox.buttonBackground");
      this.buttonFocusedBackground = UIManager.getColor("ComboBox.buttonFocusedBackground");
      this.buttonEditableBackground = UIManager.getColor("ComboBox.buttonEditableBackground");
      this.buttonSeparatorWidth = FlatUIUtils.getUIFloat("ComboBox.buttonSeparatorWidth", FlatUIUtils.getUIFloat("Component.borderWidth", 1.0F));
      this.buttonSeparatorColor = UIManager.getColor("ComboBox.buttonSeparatorColor");
      this.buttonDisabledSeparatorColor = UIManager.getColor("ComboBox.buttonDisabledSeparatorColor");
      this.buttonArrowColor = UIManager.getColor("ComboBox.buttonArrowColor");
      this.buttonDisabledArrowColor = UIManager.getColor("ComboBox.buttonDisabledArrowColor");
      this.buttonHoverArrowColor = UIManager.getColor("ComboBox.buttonHoverArrowColor");
      this.buttonPressedArrowColor = UIManager.getColor("ComboBox.buttonPressedArrowColor");
      this.popupBackground = UIManager.getColor("ComboBox.popupBackground");
      this.popupInsets = UIManager.getInsets("ComboBox.popupInsets");
      this.selectionInsets = UIManager.getInsets("ComboBox.selectionInsets");
      this.selectionArc = UIManager.getInt("ComboBox.selectionArc");
      int maximumRowCount = UIManager.getInt("ComboBox.maximumRowCount");
      if (maximumRowCount > 0 && maximumRowCount != 8 && this.comboBox.getMaximumRowCount() == 8) {
         this.comboBox.setMaximumRowCount(maximumRowCount);
      }

      this.paddingBorder = new FlatComboBoxUI.CellPaddingBorder(this.padding);
      MigLayoutVisualPadding.install(this.comboBox);
   }

   protected void uninstallDefaults() {
      super.uninstallDefaults();
      this.background = null;
      this.editableBackground = null;
      this.focusedBackground = null;
      this.disabledBackground = null;
      this.disabledForeground = null;
      this.buttonBackground = null;
      this.buttonEditableBackground = null;
      this.buttonFocusedBackground = null;
      this.buttonSeparatorColor = null;
      this.buttonDisabledSeparatorColor = null;
      this.buttonArrowColor = null;
      this.buttonDisabledArrowColor = null;
      this.buttonHoverArrowColor = null;
      this.buttonPressedArrowColor = null;
      this.popupBackground = null;
      this.paddingBorder.uninstall();
      this.oldStyleValues = null;
      this.borderShared = null;
      MigLayoutVisualPadding.uninstall(this.comboBox);
   }

   protected LayoutManager createLayoutManager() {
      return new ComboBoxLayoutManager() {
         public void layoutContainer(Container parent) {
            super.layoutContainer(parent);
            if (FlatComboBoxUI.this.arrowButton != null && FlatComboBoxUI.this.comboBox.getFont() != null) {
               FontMetrics fm = FlatComboBoxUI.this.comboBox.getFontMetrics(FlatComboBoxUI.this.comboBox.getFont());
               int maxButtonWidth = fm.getHeight() + UIScale.scale(FlatComboBoxUI.this.padding.top) + UIScale.scale(FlatComboBoxUI.this.padding.bottom);
               int minButtonWidth = maxButtonWidth * 3 / 4;
               Insets insets = FlatComboBoxUI.this.getInsets();
               int buttonWidth = Math.min(Math.max(parent.getHeight() - insets.top - insets.bottom, minButtonWidth), maxButtonWidth);
               if (buttonWidth != FlatComboBoxUI.this.arrowButton.getWidth()) {
                  int xOffset = FlatComboBoxUI.this.comboBox.getComponentOrientation().isLeftToRight() ? FlatComboBoxUI.this.arrowButton.getWidth() - buttonWidth : 0;
                  FlatComboBoxUI.this.arrowButton.setBounds(FlatComboBoxUI.this.arrowButton.getX() + xOffset, FlatComboBoxUI.this.arrowButton.getY(), buttonWidth, FlatComboBoxUI.this.arrowButton.getHeight());
                  if (FlatComboBoxUI.this.editor != null) {
                     FlatComboBoxUI.this.editor.setBounds(FlatComboBoxUI.this.rectangleForCurrentValue());
                  }
               }
            }

         }
      };
   }

   protected FocusListener createFocusListener() {
      return new FocusHandler() {
         public void focusGained(FocusEvent e) {
            super.focusGained(e);
            if (FlatComboBoxUI.this.comboBox != null && FlatComboBoxUI.this.comboBox.isEditable()) {
               FlatComboBoxUI.this.comboBox.repaint();
            }

         }

         public void focusLost(FocusEvent e) {
            super.focusLost(e);
            if (FlatComboBoxUI.this.comboBox != null && FlatComboBoxUI.this.comboBox.isEditable()) {
               FlatComboBoxUI.this.comboBox.repaint();
            }

         }
      };
   }

   protected PropertyChangeListener createPropertyChangeListener() {
      PropertyChangeListener superListener = super.createPropertyChangeListener();
      return (e) -> {
         superListener.propertyChange(e);
         Object source = e.getSource();
         String propertyName = e.getPropertyName();
         if (this.editor != null && (source == this.comboBox && propertyName == "foreground" || source == this.editor && propertyName == "enabled")) {
            this.updateEditorColors();
         } else if (this.editor != null && source == this.comboBox && propertyName == "componentOrientation") {
            ComponentOrientation o = (ComponentOrientation)e.getNewValue();
            this.editor.applyComponentOrientation(o);
         } else {
            byte var6 = -1;
            switch(propertyName.hashCode()) {
            case -1302441837:
               if (propertyName.equals("JComponent.minimumWidth")) {
                  var6 = 3;
               }
               break;
            case -742334409:
               if (propertyName.equals("JComponent.roundRect")) {
                  var6 = 1;
               }
               break;
            case -691370713:
               if (propertyName.equals("JComponent.outline")) {
                  var6 = 2;
               }
               break;
            case 151255029:
               if (propertyName.equals("JTextField.placeholderText")) {
                  var6 = 0;
               }
               break;
            case 1030195901:
               if (propertyName.equals("FlatLaf.styleClass")) {
                  var6 = 5;
               }
               break;
            case 1545413499:
               if (propertyName.equals("FlatLaf.style")) {
                  var6 = 4;
               }
            }

            switch(var6) {
            case 0:
               if (this.editor != null) {
                  this.editor.repaint();
               }
               break;
            case 1:
            case 2:
               this.comboBox.repaint();
               break;
            case 3:
               this.comboBox.revalidate();
               break;
            case 4:
            case 5:
               this.installStyle();
               this.comboBox.revalidate();
               this.comboBox.repaint();
            }
         }

      };
   }

   protected ComboPopup createPopup() {
      return new FlatComboBoxUI.FlatComboPopup(this.comboBox);
   }

   protected void configureEditor() {
      super.configureEditor();
      if (this.editor instanceof JTextField) {
         JTextField textField = (JTextField)this.editor;
         textField.setColumns(this.editorColumns);
         Border border = textField.getBorder();
         if (border == null || border instanceof UIResource) {
            textField.setBorder(BorderFactory.createEmptyBorder());
         }
      }

      if (this.editor instanceof JComponent) {
         ((JComponent)this.editor).setOpaque(false);
      }

      this.editor.applyComponentOrientation(this.comboBox.getComponentOrientation());
      this.updateEditorPadding();
      this.updateEditorColors();
      if (SystemInfo.isMacOS && this.editor instanceof JTextComponent) {
         InputMap inputMap = ((JTextComponent)this.editor).getInputMap();
         new FlatComboBoxUI.EditorDelegateAction(inputMap, KeyStroke.getKeyStroke("UP"));
         new FlatComboBoxUI.EditorDelegateAction(inputMap, KeyStroke.getKeyStroke("KP_UP"));
         new FlatComboBoxUI.EditorDelegateAction(inputMap, KeyStroke.getKeyStroke("DOWN"));
         new FlatComboBoxUI.EditorDelegateAction(inputMap, KeyStroke.getKeyStroke("KP_DOWN"));
         new FlatComboBoxUI.EditorDelegateAction(inputMap, KeyStroke.getKeyStroke("HOME"));
         new FlatComboBoxUI.EditorDelegateAction(inputMap, KeyStroke.getKeyStroke("END"));
      }

   }

   private void updateEditorPadding() {
      if (this.editor instanceof JTextField) {
         JTextField textField = (JTextField)this.editor;
         Insets insets = textField.getInsets();
         Insets pad = this.padding;
         if (insets.top != 0 || insets.left != 0 || insets.bottom != 0 || insets.right != 0) {
            pad = new Insets(UIScale.unscale(Math.max(UIScale.scale(this.padding.top) - insets.top, 0)), UIScale.unscale(Math.max(UIScale.scale(this.padding.left) - insets.left, 0)), UIScale.unscale(Math.max(UIScale.scale(this.padding.bottom) - insets.bottom, 0)), UIScale.unscale(Math.max(UIScale.scale(this.padding.right) - insets.right, 0)));
         }

         textField.putClientProperty("JTextField.padding", pad);
      }
   }

   private void updateEditorColors() {
      boolean isTextComponent = this.editor instanceof JTextComponent;
      this.editor.setForeground(FlatUIUtils.nonUIResource(this.getForeground(isTextComponent || this.editor.isEnabled())));
      if (isTextComponent) {
         ((JTextComponent)this.editor).setDisabledTextColor(FlatUIUtils.nonUIResource(this.getForeground(false)));
      }

   }

   protected JButton createArrowButton() {
      return new FlatComboBoxUI.FlatComboBoxButton();
   }

   protected void installStyle() {
      try {
         this.applyStyle(FlatStylingSupport.getResolvedStyle(this.comboBox, "ComboBox"));
      } catch (RuntimeException var2) {
         LoggingFacade.INSTANCE.logSevere((String)null, var2);
      }

   }

   protected void applyStyle(Object style) {
      Insets oldPadding = this.padding;
      int oldEditorColumns = this.editorColumns;
      this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
      if (!this.padding.equals(oldPadding)) {
         this.paddingBorder.padding = this.padding;
         this.updateEditorPadding();
      }

      if (this.arrowButton instanceof FlatComboBoxUI.FlatComboBoxButton) {
         ((FlatComboBoxUI.FlatComboBoxButton)this.arrowButton).updateStyle();
      }

      if (this.popup instanceof FlatComboBoxUI.FlatComboPopup) {
         ((FlatComboBoxUI.FlatComboPopup)this.popup).updateStyle();
      }

      if (this.editorColumns != oldEditorColumns && this.editor instanceof JTextField) {
         ((JTextField)this.editor).setColumns(this.editorColumns);
      }

   }

   protected Object applyStyleProperty(String key, Object value) {
      if (this.borderShared == null) {
         this.borderShared = new AtomicBoolean(true);
      }

      return FlatStylingSupport.applyToAnnotatedObjectOrBorder(this, key, value, this.comboBox, this.borderShared);
   }

   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
      return FlatStylingSupport.getAnnotatedStyleableInfos(this, this.comboBox.getBorder());
   }

   public Object getStyleableValue(JComponent c, String key) {
      return FlatStylingSupport.getAnnotatedStyleableValue(this, this.comboBox.getBorder(), key);
   }

   public Lookup getLookupForStyling() {
      return MethodHandles.lookup();
   }

   public void update(Graphics g, JComponent c) {
      float focusWidth = FlatUIUtils.getBorderFocusWidth(c);
      float arc = FlatUIUtils.getBorderArc(c);
      boolean paintBackground = true;
      boolean isCellRenderer = c.getParent() instanceof CellRendererPane;
      if (isCellRenderer) {
         focusWidth = 0.0F;
         arc = 0.0F;
         paintBackground = this.isCellRendererBackgroundChanged();
      }

      if (c.isOpaque() && (focusWidth > 0.0F || arc > 0.0F)) {
         FlatUIUtils.paintParentBackground(g, c);
      }

      Graphics2D g2 = (Graphics2D)g;
      Object[] oldRenderingHints = FlatUIUtils.setRenderingHints(g2);
      int width = c.getWidth();
      int height = c.getHeight();
      int arrowX = this.arrowButton.getX();
      int arrowWidth = this.arrowButton.getWidth();
      boolean paintButton = (this.comboBox.isEditable() || "button".equals(this.buttonStyle)) && !"none".equals(this.buttonStyle) && !this.isMacStyle();
      boolean enabled = this.comboBox.isEnabled();
      boolean isLeftToRight = this.comboBox.getComponentOrientation().isLeftToRight();
      if (paintBackground || c.isOpaque()) {
         g2.setColor(this.getBackground(enabled));
         FlatUIUtils.paintComponentBackground(g2, 0, 0, width, height, focusWidth, arc);
         Color separatorColor;
         if (enabled && !isCellRenderer) {
            separatorColor = paintButton ? this.buttonEditableBackground : ((this.buttonFocusedBackground != null || this.focusedBackground != null) && isPermanentFocusOwner(this.comboBox) ? (this.buttonFocusedBackground != null ? this.buttonFocusedBackground : this.focusedBackground) : this.buttonBackground);
            if (separatorColor != null) {
               g2.setColor(separatorColor);
               if (this.isMacStyle()) {
                  Insets insets = this.comboBox.getInsets();
                  int gap = UIScale.scale(2);
                  FlatUIUtils.paintComponentBackground(g2, arrowX + gap, insets.top + gap, arrowWidth - gap * 2, height - insets.top - insets.bottom - gap * 2, 0.0F, arc - focusWidth);
               } else {
                  Shape oldClip = g2.getClip();
                  if (isLeftToRight) {
                     g2.clipRect(arrowX, 0, width - arrowX, height);
                  } else {
                     g2.clipRect(0, 0, arrowX + arrowWidth, height);
                  }

                  FlatUIUtils.paintComponentBackground(g2, 0, 0, width, height, focusWidth, arc);
                  g2.setClip(oldClip);
               }
            }
         }

         if (paintButton) {
            separatorColor = enabled ? this.buttonSeparatorColor : this.buttonDisabledSeparatorColor;
            if (separatorColor != null && this.buttonSeparatorWidth > 0.0F) {
               g2.setColor(separatorColor);
               float lw = UIScale.scale(this.buttonSeparatorWidth);
               float lx = isLeftToRight ? (float)arrowX : (float)(arrowX + arrowWidth) - lw;
               g2.fill(new Float(lx, focusWidth, lw, (float)(height - 1) - focusWidth * 2.0F));
            }
         }
      }

      FlatUIUtils.resetRenderingHints(g2, oldRenderingHints);
      this.paint(g, c);
   }

   public void paintCurrentValue(Graphics g, Rectangle bounds, boolean hasFocus) {
      this.paddingBorder.uninstall();
      ListCellRenderer<Object> renderer = this.comboBox.getRenderer();
      if (renderer == null) {
         renderer = new DefaultListCellRenderer();
      }

      Component c = ((ListCellRenderer)renderer).getListCellRendererComponent(this.listBox, this.comboBox.getSelectedItem(), -1, false, false);
      c.setFont(this.comboBox.getFont());
      c.applyComponentOrientation(this.comboBox.getComponentOrientation());
      boolean enabled = this.comboBox.isEnabled();
      c.setBackground(this.getBackground(enabled));
      c.setForeground(this.getForeground(enabled));
      if (c instanceof JComponent) {
         ((JComponent)c).setOpaque(false);
      }

      boolean shouldValidate = c instanceof JPanel;
      this.paddingBorder.install(c, 0);
      this.currentValuePane.paintComponent(g, c, this.comboBox, bounds.x, bounds.y, bounds.width, bounds.height, shouldValidate);
      this.paddingBorder.uninstall();
      if (c instanceof JComponent) {
         ((JComponent)c).setOpaque(true);
      }

   }

   public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
   }

   protected Color getBackground(boolean enabled) {
      if (enabled) {
         if (FlatUIUtils.isAWTPeer(this.comboBox)) {
            return this.background;
         } else {
            Color background = this.comboBox.getBackground();
            if (!(background instanceof UIResource)) {
               return background;
            } else if (this.focusedBackground != null && isPermanentFocusOwner(this.comboBox)) {
               return this.focusedBackground;
            } else {
               return this.editableBackground != null && this.comboBox.isEditable() ? this.editableBackground : background;
            }
         }
      } else {
         return this.isIntelliJTheme ? FlatUIUtils.getParentBackground(this.comboBox) : this.disabledBackground;
      }
   }

   protected Color getForeground(boolean enabled) {
      return enabled ? this.comboBox.getForeground() : this.disabledForeground;
   }

   public Dimension getMinimumSize(JComponent c) {
      Dimension minimumSize = super.getMinimumSize(c);
      int fw = Math.round(FlatUIUtils.getBorderFocusWidth(c) * 2.0F);
      minimumSize.width = Math.max(minimumSize.width, UIScale.scale(FlatUIUtils.minimumWidth(c, this.minimumWidth)) + fw);
      return minimumSize;
   }

   protected Dimension getDefaultSize() {
      this.paddingBorder.uninstall();
      Dimension size = super.getDefaultSize();
      this.paddingBorder.uninstall();
      return size;
   }

   protected Dimension getDisplaySize() {
      this.paddingBorder.uninstall();
      Dimension displaySize = super.getDisplaySize();
      this.paddingBorder.uninstall();
      int displayWidth = displaySize.width - this.padding.left - this.padding.right;
      int displayHeight = displaySize.height - this.padding.top - this.padding.bottom;
      if (displayWidth == 100 && this.comboBox.isEditable() && this.comboBox.getItemCount() == 0 && this.comboBox.getPrototypeDisplayValue() == null) {
         displayWidth = Math.max(this.getDefaultSize().width, this.editor.getPreferredSize().width);
      }

      return new Dimension(displayWidth, displayHeight);
   }

   protected Dimension getSizeForComponent(Component comp) {
      this.paddingBorder.install(comp, 0);
      Dimension size = super.getSizeForComponent(comp);
      this.paddingBorder.uninstall();
      return size;
   }

   private boolean isCellRenderer() {
      return this.comboBox.getParent() instanceof CellRendererPane;
   }

   private boolean isCellRendererBackgroundChanged() {
      Container parentParent = this.comboBox.getParent().getParent();
      return parentParent != null && !this.comboBox.getBackground().equals(parentParent.getBackground());
   }

   private boolean isMacStyle() {
      return "mac".equals(this.buttonStyle);
   }

   public static boolean isPermanentFocusOwner(JComboBox<?> comboBox) {
      if (comboBox.isEditable()) {
         if (FlatUIUtils.isPermanentFocusOwner(comboBox)) {
            return true;
         } else {
            Component editorComponent = comboBox.getEditor().getEditorComponent();
            return editorComponent != null && FlatUIUtils.isPermanentFocusOwner(editorComponent);
         }
      } else {
         return FlatUIUtils.isPermanentFocusOwner(comboBox);
      }
   }

   private class EditorDelegateAction extends AbstractAction {
      private final KeyStroke keyStroke;

      EditorDelegateAction(InputMap inputMap, KeyStroke keyStroke) {
         this.keyStroke = keyStroke;
         inputMap.put(keyStroke, this);
      }

      public void actionPerformed(ActionEvent e) {
         ActionListener action = FlatComboBoxUI.this.comboBox.getActionForKeyStroke(this.keyStroke);
         if (action != null) {
            action.actionPerformed(new ActionEvent(FlatComboBoxUI.this.comboBox, e.getID(), e.getActionCommand(), e.getWhen(), e.getModifiers()));
         }

      }
   }

   private static class MacCheckedItemIcon extends FlatCheckBoxMenuItemIcon {
      static FlatComboBoxUI.MacCheckedItemIcon INSTANCE = new FlatComboBoxUI.MacCheckedItemIcon();

      protected void paintIcon(Component c, Graphics2D g2) {
         g2.setColor(c.getForeground());
         this.paintCheckmark(g2);
      }
   }

   private static class CellPaddingBorder extends AbstractBorder {
      static final String KEY_MAC_STYLE_HINT = "FlatLaf.internal.FlatComboBoxUI.macStyleHint";
      static final int MAC_STYLE_GAP = 4;
      private Insets padding;
      private JComponent rendererComponent;
      private Border rendererBorder;
      private int focusWidth;

      CellPaddingBorder(Insets padding) {
         this.padding = padding;
      }

      synchronized void install(Component c, int focusWidth) {
         if (c instanceof JComponent) {
            this.focusWidth = focusWidth;
            JComponent jc = (JComponent)c;
            Border oldBorder = jc.getBorder();
            if (oldBorder != this) {
               if (oldBorder instanceof FlatComboBoxUI.CellPaddingBorder) {
                  ((FlatComboBoxUI.CellPaddingBorder)oldBorder).uninstall();
               }

               this.uninstall();
               this.rendererComponent = jc;
               this.rendererBorder = jc.getBorder();
               jc.setBorder(this);
            }
         }
      }

      synchronized void uninstall() {
         if (this.rendererComponent != null) {
            this.rendererComponent.putClientProperty("FlatLaf.internal.FlatComboBoxUI.macStyleHint", (Object)null);
            if (this.rendererComponent.getBorder() == this) {
               this.rendererComponent.setBorder(this.rendererBorder);
            }

            this.rendererComponent = null;
            this.rendererBorder = null;
         }
      }

      public synchronized Insets getBorderInsets(Component c, Insets insets) {
         Insets padding = UIScale.scale(this.padding);
         if (this.rendererBorder != null && !(this.rendererBorder instanceof FlatComboBoxUI.CellPaddingBorder)) {
            Insets insideInsets = this.rendererBorder.getBorderInsets(c);
            insets.top = Math.max(padding.top, insideInsets.top);
            insets.left = Math.max(padding.left, insideInsets.left);
            insets.bottom = Math.max(padding.bottom, insideInsets.bottom);
            insets.right = Math.max(padding.right, insideInsets.right);
         } else {
            insets.top = padding.top;
            insets.left = padding.left;
            insets.bottom = padding.bottom;
            insets.right = padding.right;
         }

         insets.left += this.focusWidth;
         insets.right += this.focusWidth;
         if (c instanceof JComponent) {
            Boolean macStyleHint = FlatClientProperties.clientPropertyBooleanStrict((JComponent)c, "FlatLaf.internal.FlatComboBoxUI.macStyleHint", (Boolean)null);
            if (macStyleHint != null) {
               int indent = FlatComboBoxUI.MacCheckedItemIcon.INSTANCE.getIconWidth() + UIScale.scale(4);
               if (c.getComponentOrientation().isLeftToRight()) {
                  insets.left += indent;
               } else {
                  insets.right += indent;
               }
            }
         }

         return insets;
      }

      public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
         if (this.rendererBorder != null) {
            this.rendererBorder.paintBorder(c, g, x, y, width, height);
         }

         if (c instanceof JComponent) {
            Boolean macStyleHint = FlatClientProperties.clientPropertyBooleanStrict((JComponent)c, "FlatLaf.internal.FlatComboBoxUI.macStyleHint", (Boolean)null);
            if (macStyleHint == Boolean.TRUE) {
               int ix = c.getComponentOrientation().isLeftToRight() ? x + UIScale.scale(this.padding.left) : x + width - UIScale.scale(this.padding.right) - FlatComboBoxUI.MacCheckedItemIcon.INSTANCE.getIconWidth();
               FlatComboBoxUI.MacCheckedItemIcon.INSTANCE.paintIcon(c, g, ix, y + (height - FlatComboBoxUI.MacCheckedItemIcon.INSTANCE.getIconHeight()) / 2);
            }
         }

      }
   }

   protected class FlatComboPopup extends BasicComboPopup {
      protected FlatComboPopup(JComboBox combo) {
         super(combo);
         ComponentOrientation o = this.comboBox.getComponentOrientation();
         this.list.setComponentOrientation(o);
         this.scroller.setComponentOrientation(o);
         this.setComponentOrientation(o);
      }

      protected Rectangle computePopupBounds(int px, int py, int pw, int ph) {
         int displayWidth = FlatComboBoxUI.this.getDisplaySize().width;
         Border[] var6 = new Border[]{this.scroller.getViewportBorder(), this.scroller.getBorder()};
         int selectedIndex = var6.length;

         for(int var8 = 0; var8 < selectedIndex; ++var8) {
            Border border = var6[var8];
            if (border != null) {
               Insets borderInsets = border.getBorderInsets((Component)null);
               displayWidth += borderInsets.left + borderInsets.right;
            }
         }

         boolean isPopupOverComboBox = this.isPopupOverComboBox();
         selectedIndex = -1;
         if (isPopupOverComboBox && (selectedIndex = this.comboBox.getSelectedIndex()) >= 0) {
            displayWidth += FlatComboBoxUI.MacCheckedItemIcon.INSTANCE.getIconWidth() + UIScale.scale(4);
         }

         JScrollBar verticalScrollBar = this.scroller.getVerticalScrollBar();
         if (verticalScrollBar != null) {
            displayWidth += verticalScrollBar.getPreferredSize().width;
         }

         int pw0 = pw;
         Insets listInsets;
         if (displayWidth > pw) {
            GraphicsConfiguration gc = this.comboBox.getGraphicsConfiguration();
            if (gc != null) {
               Rectangle screenBounds = gc.getBounds();
               listInsets = Toolkit.getDefaultToolkit().getScreenInsets(gc);
               displayWidth = Math.min(displayWidth, screenBounds.width - listInsets.left - listInsets.right);
            } else {
               Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
               displayWidth = Math.min(displayWidth, screenSize.width);
            }

            int diff = displayWidth - pw;
            pw = displayWidth;
            if (!this.comboBox.getComponentOrientation().isLeftToRight()) {
               px -= diff;
            }
         }

         Rectangle cellBounds;
         if (isPopupOverComboBox && selectedIndex >= 0 && (cellBounds = this.list.getCellBounds(0, 0)) != null) {
            Insets comboBoxInsets = this.comboBox.getInsets();
            listInsets = this.list.getInsets();
            Insets popupInsets = this.getInsets();
            py -= cellBounds.height * (selectedIndex + 1) + comboBoxInsets.top + listInsets.top + popupInsets.top;
            int offset = Math.min(pw - pw0, FlatComboBoxUI.MacCheckedItemIcon.INSTANCE.getIconWidth()) + UIScale.scale(4);
            if (this.comboBox.getComponentOrientation().isLeftToRight()) {
               px -= offset + comboBoxInsets.right + listInsets.right;
            } else {
               px += offset + comboBoxInsets.left + listInsets.left;
            }

            return new Rectangle(px, py, pw, ph);
         } else {
            return super.computePopupBounds(px, py, pw, ph);
         }
      }

      protected void configurePopup() {
         super.configurePopup();
         this.setOpaque(true);
         Border border = UIManager.getBorder("PopupMenu.border");
         if (border != null) {
            this.setBorder(FlatUIUtils.nonUIResource(border));
         }

         this.list.setCellRenderer(new FlatComboBoxUI.FlatComboPopup.PopupListCellRenderer());
         this.updateStyle();
      }

      void updateStyle() {
         if (FlatComboBoxUI.this.popupBackground != null) {
            this.list.setBackground(FlatComboBoxUI.this.popupBackground);
         }

         this.setBackground(FlatUIUtils.nonUIResource(this.list.getBackground()));
         this.scroller.setViewportBorder(FlatComboBoxUI.this.popupInsets != null ? new FlatEmptyBorder(FlatComboBoxUI.this.popupInsets) : null);
         this.scroller.setOpaque(false);
         if (this.list.getUI() instanceof FlatListUI) {
            FlatListUI ui = (FlatListUI)this.list.getUI();
            ui.selectionInsets = FlatComboBoxUI.this.selectionInsets;
            ui.selectionArc = FlatComboBoxUI.this.selectionArc;
         }

      }

      protected PropertyChangeListener createPropertyChangeListener() {
         PropertyChangeListener superListener = super.createPropertyChangeListener();
         return (e) -> {
            superListener.propertyChange(e);
            if (e.getPropertyName() == "renderer") {
               this.list.setCellRenderer(new FlatComboBoxUI.FlatComboPopup.PopupListCellRenderer());
            }

         };
      }

      protected int getPopupHeightForRowCount(int maxRowCount) {
         int height = super.getPopupHeightForRowCount(maxRowCount);
         FlatComboBoxUI.this.paddingBorder.uninstall();
         return height;
      }

      public void show(Component invoker, int x, int y) {
         if (y < 0 && !SystemInfo.isJava_9_orLater) {
            Border popupBorder = this.getBorder();
            if (popupBorder != null) {
               Insets insets = popupBorder.getBorderInsets(this);
               y -= insets.top + insets.bottom;
            }
         }

         super.show(invoker, x, y);
      }

      protected void paintChildren(Graphics g) {
         super.paintChildren(g);
         FlatComboBoxUI.this.paddingBorder.uninstall();
      }

      private boolean isPopupOverComboBox() {
         return FlatComboBoxUI.this.isMacStyle() && !this.comboBox.isEditable() && this.comboBox.getItemCount() > 0 && this.comboBox.getItemCount() <= this.comboBox.getMaximumRowCount() && !FlatClientProperties.clientPropertyBoolean(this.comboBox, "JComboBox.isPopDown", false);
      }

      private class PopupListCellRenderer implements ListCellRenderer {
         private PopupListCellRenderer() {
         }

         public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            FlatComboBoxUI.this.paddingBorder.uninstall();
            ListCellRenderer renderer = FlatComboPopup.this.comboBox.getRenderer();
            if (renderer == null) {
               renderer = new DefaultListCellRenderer();
            }

            Component c = ((ListCellRenderer)renderer).getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            c.applyComponentOrientation(FlatComboPopup.this.comboBox.getComponentOrientation());
            if (FlatComboPopup.this.isPopupOverComboBox() && c instanceof JComponent) {
               int selectedIndex = FlatComboPopup.this.comboBox.getSelectedIndex();
               ((JComponent)c).putClientProperty("FlatLaf.internal.FlatComboBoxUI.macStyleHint", selectedIndex >= 0 ? index == selectedIndex : null);
            }

            FlatComboBoxUI.this.paddingBorder.install(c, Math.round(FlatUIUtils.getBorderFocusWidth(FlatComboPopup.this.comboBox)));
            return c;
         }

         // $FF: synthetic method
         PopupListCellRenderer(Object x1) {
            this();
         }
      }
   }

   protected class FlatComboBoxButton extends FlatArrowButton {
      protected FlatComboBoxButton() {
         this(5, FlatComboBoxUI.this.arrowType, FlatComboBoxUI.this.buttonArrowColor, FlatComboBoxUI.this.buttonDisabledArrowColor, FlatComboBoxUI.this.buttonHoverArrowColor, (Color)null, FlatComboBoxUI.this.buttonPressedArrowColor, (Color)null);
      }

      protected FlatComboBoxButton(int direction, String type, Color foreground, Color disabledForeground, Color hoverForeground, Color hoverBackground, Color pressedForeground, Color pressedBackground) {
         super(direction, type, foreground, disabledForeground, hoverForeground, hoverBackground, pressedForeground, pressedBackground);
      }

      protected void updateStyle() {
         this.updateStyle(FlatComboBoxUI.this.arrowType, FlatComboBoxUI.this.buttonArrowColor, FlatComboBoxUI.this.buttonDisabledArrowColor, FlatComboBoxUI.this.buttonHoverArrowColor, (Color)null, FlatComboBoxUI.this.buttonPressedArrowColor, (Color)null);
      }

      public int getArrowWidth() {
         return FlatComboBoxUI.this.isMacStyle() ? (this.getWidth() % 2 == 0 ? 6 : 7) : super.getArrowWidth();
      }

      public float getArrowThickness() {
         return FlatComboBoxUI.this.isMacStyle() ? 1.5F : super.getArrowThickness();
      }

      public boolean isRoundBorderAutoXOffset() {
         return FlatComboBoxUI.this.isMacStyle() ? false : super.isRoundBorderAutoXOffset();
      }

      protected boolean isHover() {
         return super.isHover() || !FlatComboBoxUI.this.comboBox.isEditable() && FlatComboBoxUI.this.hover;
      }

      protected boolean isPressed() {
         return super.isPressed() || !FlatComboBoxUI.this.comboBox.isEditable() && FlatComboBoxUI.this.pressed;
      }

      protected Color getArrowColor() {
         return FlatComboBoxUI.this.isCellRenderer() && FlatComboBoxUI.this.isCellRendererBackgroundChanged() ? FlatComboBoxUI.this.comboBox.getForeground() : super.getArrowColor();
      }

      protected void paintArrow(Graphics2D g) {
         if (FlatComboBoxUI.this.isMacStyle() && !FlatComboBoxUI.this.comboBox.isEditable()) {
            int height = this.getHeight();
            int h = Math.round((float)height / 2.0F);
            FlatUIUtils.paintArrow(g, 0, 0, this.getWidth(), h, 1, this.chevron, this.getArrowWidth(), this.getArrowThickness(), this.getXOffset(), this.getYOffset() + 1.25F);
            FlatUIUtils.paintArrow(g, 0, height - h, this.getWidth(), h, 5, this.chevron, this.getArrowWidth(), this.getArrowThickness(), this.getXOffset(), this.getYOffset() - 1.25F);
         } else {
            super.paintArrow(g);
         }

      }
   }
}
