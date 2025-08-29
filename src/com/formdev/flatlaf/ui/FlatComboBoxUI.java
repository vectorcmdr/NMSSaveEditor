/*      */ package com.formdev.flatlaf.ui;
/*      */ 
/*      */ import com.formdev.flatlaf.FlatClientProperties;
/*      */ import com.formdev.flatlaf.icons.FlatCheckBoxMenuItemIcon;
/*      */ import com.formdev.flatlaf.util.LoggingFacade;
/*      */ import com.formdev.flatlaf.util.SystemInfo;
/*      */ import com.formdev.flatlaf.util.UIScale;
/*      */ import java.awt.Color;
/*      */ import java.awt.Component;
/*      */ import java.awt.ComponentOrientation;
/*      */ import java.awt.Container;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Graphics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.GraphicsConfiguration;
/*      */ import java.awt.Insets;
/*      */ import java.awt.LayoutManager;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Toolkit;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.FocusEvent;
/*      */ import java.awt.event.FocusListener;
/*      */ import java.awt.event.MouseAdapter;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.awt.event.MouseListener;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.beans.PropertyChangeEvent;
/*      */ import java.beans.PropertyChangeListener;
/*      */ import java.lang.invoke.MethodHandles;
/*      */ import java.util.Map;
/*      */ import java.util.concurrent.atomic.AtomicBoolean;
/*      */ import javax.swing.AbstractAction;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.DefaultListCellRenderer;
/*      */ import javax.swing.InputMap;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JComboBox;
/*      */ import javax.swing.JComponent;
/*      */ import javax.swing.JList;
/*      */ import javax.swing.JScrollBar;
/*      */ import javax.swing.JTextField;
/*      */ import javax.swing.KeyStroke;
/*      */ import javax.swing.ListCellRenderer;
/*      */ import javax.swing.LookAndFeel;
/*      */ import javax.swing.UIManager;
/*      */ import javax.swing.border.AbstractBorder;
/*      */ import javax.swing.border.Border;
/*      */ import javax.swing.plaf.ComponentUI;
/*      */ import javax.swing.plaf.basic.BasicComboBoxUI;
/*      */ import javax.swing.plaf.basic.BasicComboPopup;
/*      */ import javax.swing.plaf.basic.ComboPopup;
/*      */ import javax.swing.text.JTextComponent;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ @StyleableField(cls = BasicComboBoxUI.class, key = "padding")
/*      */ public class FlatComboBoxUI
/*      */   extends BasicComboBoxUI
/*      */   implements FlatStylingSupport.StyleableUI, FlatStylingSupport.StyleableLookupProvider
/*      */ {
/*      */   @Styleable
/*      */   protected int minimumWidth;
/*      */   @Styleable
/*      */   protected int editorColumns;
/*      */   @Styleable
/*      */   protected String buttonStyle;
/*      */   @Styleable
/*      */   protected String arrowType;
/*      */   protected boolean isIntelliJTheme;
/*      */   private Color background;
/*      */   @Styleable
/*      */   protected Color editableBackground;
/*      */   @Styleable
/*      */   protected Color focusedBackground;
/*      */   @Styleable
/*      */   protected Color disabledBackground;
/*      */   @Styleable
/*      */   protected Color disabledForeground;
/*      */   @Styleable
/*      */   protected Color buttonBackground;
/*      */   @Styleable
/*      */   protected Color buttonEditableBackground;
/*      */   @Styleable
/*      */   protected Color buttonFocusedBackground;
/*      */   @Styleable
/*      */   protected float buttonSeparatorWidth;
/*      */   @Styleable
/*      */   protected Color buttonSeparatorColor;
/*      */   @Styleable
/*      */   protected Color buttonDisabledSeparatorColor;
/*      */   @Styleable
/*      */   protected Color buttonArrowColor;
/*      */   @Styleable
/*      */   protected Color buttonDisabledArrowColor;
/*      */   @Styleable
/*      */   protected Color buttonHoverArrowColor;
/*      */   @Styleable
/*      */   protected Color buttonPressedArrowColor;
/*      */   @Styleable
/*      */   protected Color popupBackground;
/*      */   @Styleable
/*      */   protected Insets popupInsets;
/*      */   @Styleable
/*      */   protected Insets selectionInsets;
/*      */   @Styleable
/*      */   protected int selectionArc;
/*      */   private MouseListener hoverListener;
/*      */   protected boolean hover;
/*      */   protected boolean pressed;
/*      */   private CellPaddingBorder paddingBorder;
/*      */   private Map<String, Object> oldStyleValues;
/*      */   private AtomicBoolean borderShared;
/*      */   
/*      */   public static ComponentUI createUI(JComponent c) {
/*  171 */     return new FlatComboBoxUI();
/*      */   }
/*      */ 
/*      */   
/*      */   public void installUI(JComponent c) {
/*  176 */     if (FlatUIUtils.needsLightAWTPeer(c)) {
/*  177 */       FlatUIUtils.runWithLightAWTPeerUIDefaults(() -> installUIImpl(c));
/*      */     } else {
/*  179 */       installUIImpl(c);
/*      */     } 
/*      */   }
/*      */   private void installUIImpl(JComponent c) {
/*  183 */     super.installUI(c);
/*      */     
/*  185 */     installStyle();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void installListeners() {
/*  190 */     super.installListeners();
/*      */     
/*  192 */     this.hoverListener = new MouseAdapter()
/*      */       {
/*      */         public void mouseEntered(MouseEvent e) {
/*  195 */           FlatComboBoxUI.this.hover = true;
/*  196 */           repaintArrowButton();
/*      */         }
/*      */ 
/*      */         
/*      */         public void mouseExited(MouseEvent e) {
/*  201 */           FlatComboBoxUI.this.hover = false;
/*  202 */           repaintArrowButton();
/*      */         }
/*      */ 
/*      */         
/*      */         public void mousePressed(MouseEvent e) {
/*  207 */           FlatComboBoxUI.this.pressed = true;
/*  208 */           repaintArrowButton();
/*      */         }
/*      */ 
/*      */         
/*      */         public void mouseReleased(MouseEvent e) {
/*  213 */           FlatComboBoxUI.this.pressed = false;
/*  214 */           repaintArrowButton();
/*      */         }
/*      */         
/*      */         private void repaintArrowButton() {
/*  218 */           if (FlatComboBoxUI.this.arrowButton != null && !FlatComboBoxUI.this.comboBox.isEditable())
/*  219 */             FlatComboBoxUI.this.arrowButton.repaint(); 
/*      */         }
/*      */       };
/*  222 */     this.comboBox.addMouseListener(this.hoverListener);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void uninstallListeners() {
/*  227 */     super.uninstallListeners();
/*      */     
/*  229 */     this.comboBox.removeMouseListener(this.hoverListener);
/*  230 */     this.hoverListener = null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void installDefaults() {
/*  235 */     super.installDefaults();
/*      */     
/*  237 */     LookAndFeel.installProperty(this.comboBox, "opaque", Boolean.valueOf(false));
/*      */     
/*  239 */     this.minimumWidth = UIManager.getInt("ComboBox.minimumWidth");
/*  240 */     this.editorColumns = UIManager.getInt("ComboBox.editorColumns");
/*  241 */     this.buttonStyle = UIManager.getString("ComboBox.buttonStyle");
/*  242 */     this.arrowType = UIManager.getString("Component.arrowType");
/*  243 */     this.isIntelliJTheme = UIManager.getBoolean("Component.isIntelliJTheme");
/*      */     
/*  245 */     this.background = UIManager.getColor("ComboBox.background");
/*  246 */     this.editableBackground = UIManager.getColor("ComboBox.editableBackground");
/*  247 */     this.focusedBackground = UIManager.getColor("ComboBox.focusedBackground");
/*  248 */     this.disabledBackground = UIManager.getColor("ComboBox.disabledBackground");
/*  249 */     this.disabledForeground = UIManager.getColor("ComboBox.disabledForeground");
/*      */     
/*  251 */     this.buttonBackground = UIManager.getColor("ComboBox.buttonBackground");
/*  252 */     this.buttonFocusedBackground = UIManager.getColor("ComboBox.buttonFocusedBackground");
/*  253 */     this.buttonEditableBackground = UIManager.getColor("ComboBox.buttonEditableBackground");
/*  254 */     this.buttonSeparatorWidth = FlatUIUtils.getUIFloat("ComboBox.buttonSeparatorWidth", FlatUIUtils.getUIFloat("Component.borderWidth", 1.0F));
/*  255 */     this.buttonSeparatorColor = UIManager.getColor("ComboBox.buttonSeparatorColor");
/*  256 */     this.buttonDisabledSeparatorColor = UIManager.getColor("ComboBox.buttonDisabledSeparatorColor");
/*  257 */     this.buttonArrowColor = UIManager.getColor("ComboBox.buttonArrowColor");
/*  258 */     this.buttonDisabledArrowColor = UIManager.getColor("ComboBox.buttonDisabledArrowColor");
/*  259 */     this.buttonHoverArrowColor = UIManager.getColor("ComboBox.buttonHoverArrowColor");
/*  260 */     this.buttonPressedArrowColor = UIManager.getColor("ComboBox.buttonPressedArrowColor");
/*      */     
/*  262 */     this.popupBackground = UIManager.getColor("ComboBox.popupBackground");
/*  263 */     this.popupInsets = UIManager.getInsets("ComboBox.popupInsets");
/*  264 */     this.selectionInsets = UIManager.getInsets("ComboBox.selectionInsets");
/*  265 */     this.selectionArc = UIManager.getInt("ComboBox.selectionArc");
/*      */ 
/*      */     
/*  268 */     int maximumRowCount = UIManager.getInt("ComboBox.maximumRowCount");
/*  269 */     if (maximumRowCount > 0 && maximumRowCount != 8 && this.comboBox.getMaximumRowCount() == 8) {
/*  270 */       this.comboBox.setMaximumRowCount(maximumRowCount);
/*      */     }
/*  272 */     this.paddingBorder = new CellPaddingBorder(this.padding);
/*      */     
/*  274 */     MigLayoutVisualPadding.install(this.comboBox);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void uninstallDefaults() {
/*  279 */     super.uninstallDefaults();
/*      */     
/*  281 */     this.background = null;
/*  282 */     this.editableBackground = null;
/*  283 */     this.focusedBackground = null;
/*  284 */     this.disabledBackground = null;
/*  285 */     this.disabledForeground = null;
/*      */     
/*  287 */     this.buttonBackground = null;
/*  288 */     this.buttonEditableBackground = null;
/*  289 */     this.buttonFocusedBackground = null;
/*  290 */     this.buttonSeparatorColor = null;
/*  291 */     this.buttonDisabledSeparatorColor = null;
/*  292 */     this.buttonArrowColor = null;
/*  293 */     this.buttonDisabledArrowColor = null;
/*  294 */     this.buttonHoverArrowColor = null;
/*  295 */     this.buttonPressedArrowColor = null;
/*      */     
/*  297 */     this.popupBackground = null;
/*      */     
/*  299 */     this.paddingBorder.uninstall();
/*      */     
/*  301 */     this.oldStyleValues = null;
/*  302 */     this.borderShared = null;
/*      */     
/*  304 */     MigLayoutVisualPadding.uninstall(this.comboBox);
/*      */   }
/*      */ 
/*      */   
/*      */   protected LayoutManager createLayoutManager() {
/*  309 */     return new BasicComboBoxUI.ComboBoxLayoutManager()
/*      */       {
/*      */         public void layoutContainer(Container parent) {
/*  312 */           super.layoutContainer(parent);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  317 */           if (FlatComboBoxUI.this.arrowButton != null && FlatComboBoxUI.this.comboBox.getFont() != null) {
/*      */             
/*  319 */             FontMetrics fm = FlatComboBoxUI.this.comboBox.getFontMetrics(FlatComboBoxUI.this.comboBox.getFont());
/*  320 */             int maxButtonWidth = fm.getHeight() + UIScale.scale(FlatComboBoxUI.this.padding.top) + UIScale.scale(FlatComboBoxUI.this.padding.bottom);
/*  321 */             int minButtonWidth = maxButtonWidth * 3 / 4;
/*      */ 
/*      */             
/*  324 */             Insets insets = FlatComboBoxUI.this.getInsets();
/*  325 */             int buttonWidth = Math.min(Math.max(parent.getHeight() - insets.top - insets.bottom, minButtonWidth), maxButtonWidth);
/*      */             
/*  327 */             if (buttonWidth != FlatComboBoxUI.this.arrowButton.getWidth()) {
/*      */ 
/*      */ 
/*      */               
/*  331 */               int xOffset = FlatComboBoxUI.this.comboBox.getComponentOrientation().isLeftToRight() ? (FlatComboBoxUI.this.arrowButton.getWidth() - buttonWidth) : 0;
/*  332 */               FlatComboBoxUI.this.arrowButton.setBounds(FlatComboBoxUI.this.arrowButton.getX() + xOffset, FlatComboBoxUI.this.arrowButton.getY(), buttonWidth, FlatComboBoxUI.this
/*  333 */                   .arrowButton.getHeight());
/*      */ 
/*      */               
/*  336 */               if (FlatComboBoxUI.this.editor != null) {
/*  337 */                 FlatComboBoxUI.this.editor.setBounds(FlatComboBoxUI.this.rectangleForCurrentValue());
/*      */               }
/*      */             } 
/*      */           } 
/*      */         }
/*      */       };
/*      */   }
/*      */ 
/*      */   
/*      */   protected FocusListener createFocusListener() {
/*  347 */     return new BasicComboBoxUI.FocusHandler()
/*      */       {
/*      */         public void focusGained(FocusEvent e) {
/*  350 */           super.focusGained(e);
/*  351 */           if (FlatComboBoxUI.this.comboBox != null && FlatComboBoxUI.this.comboBox.isEditable()) {
/*  352 */             FlatComboBoxUI.this.comboBox.repaint();
/*      */           }
/*      */         }
/*      */         
/*      */         public void focusLost(FocusEvent e) {
/*  357 */           super.focusLost(e);
/*  358 */           if (FlatComboBoxUI.this.comboBox != null && FlatComboBoxUI.this.comboBox.isEditable()) {
/*  359 */             FlatComboBoxUI.this.comboBox.repaint();
/*      */           }
/*      */         }
/*      */       };
/*      */   }
/*      */   
/*      */   protected PropertyChangeListener createPropertyChangeListener() {
/*  366 */     PropertyChangeListener superListener = super.createPropertyChangeListener();
/*  367 */     return e -> {
/*      */         superListener.propertyChange(e);
/*      */         Object source = e.getSource();
/*      */         String propertyName = e.getPropertyName();
/*      */         if (this.editor != null && ((source == this.comboBox && propertyName == "foreground") || (source == this.editor && propertyName == "enabled"))) {
/*      */           updateEditorColors();
/*      */         } else if (this.editor != null && source == this.comboBox && propertyName == "componentOrientation") {
/*      */           ComponentOrientation o = (ComponentOrientation)e.getNewValue();
/*      */           this.editor.applyComponentOrientation(o);
/*      */         } else {
/*      */           switch (propertyName) {
/*      */             case "JTextField.placeholderText":
/*      */               if (this.editor != null) {
/*      */                 this.editor.repaint();
/*      */               }
/*      */               break;
/*      */             case "JComponent.roundRect":
/*      */             case "JComponent.outline":
/*      */               this.comboBox.repaint();
/*      */               break;
/*      */             case "JComponent.minimumWidth":
/*      */               this.comboBox.revalidate();
/*      */               break;
/*      */             case "FlatLaf.style":
/*      */             case "FlatLaf.styleClass":
/*      */               installStyle();
/*      */               this.comboBox.revalidate();
/*      */               this.comboBox.repaint();
/*      */               break;
/*      */           } 
/*      */         } 
/*      */       };
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ComboPopup createPopup() {
/*  411 */     return new FlatComboPopup(this.comboBox);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void configureEditor() {
/*  416 */     super.configureEditor();
/*      */     
/*  418 */     if (this.editor instanceof JTextField) {
/*  419 */       JTextField textField = (JTextField)this.editor;
/*  420 */       textField.setColumns(this.editorColumns);
/*      */ 
/*      */       
/*  423 */       Border border = textField.getBorder();
/*  424 */       if (border == null || border instanceof javax.swing.plaf.UIResource)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  430 */         textField.setBorder(BorderFactory.createEmptyBorder());
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  435 */     if (this.editor instanceof JComponent) {
/*  436 */       ((JComponent)this.editor).setOpaque(false);
/*      */     }
/*  438 */     this.editor.applyComponentOrientation(this.comboBox.getComponentOrientation());
/*      */     
/*  440 */     updateEditorPadding();
/*  441 */     updateEditorColors();
/*      */ 
/*      */     
/*  444 */     if (SystemInfo.isMacOS && this.editor instanceof JTextComponent) {
/*      */ 
/*      */       
/*  447 */       InputMap inputMap = ((JTextComponent)this.editor).getInputMap();
/*  448 */       new EditorDelegateAction(inputMap, KeyStroke.getKeyStroke("UP"));
/*  449 */       new EditorDelegateAction(inputMap, KeyStroke.getKeyStroke("KP_UP"));
/*  450 */       new EditorDelegateAction(inputMap, KeyStroke.getKeyStroke("DOWN"));
/*  451 */       new EditorDelegateAction(inputMap, KeyStroke.getKeyStroke("KP_DOWN"));
/*  452 */       new EditorDelegateAction(inputMap, KeyStroke.getKeyStroke("HOME"));
/*  453 */       new EditorDelegateAction(inputMap, KeyStroke.getKeyStroke("END"));
/*      */     } 
/*      */   }
/*      */   
/*      */   private void updateEditorPadding() {
/*  458 */     if (!(this.editor instanceof JTextField)) {
/*      */       return;
/*      */     }
/*  461 */     JTextField textField = (JTextField)this.editor;
/*  462 */     Insets insets = textField.getInsets();
/*  463 */     Insets pad = this.padding;
/*  464 */     if (insets.top != 0 || insets.left != 0 || insets.bottom != 0 || insets.right != 0)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  470 */       pad = new Insets(UIScale.unscale(Math.max(UIScale.scale(this.padding.top) - insets.top, 0)), UIScale.unscale(Math.max(UIScale.scale(this.padding.left) - insets.left, 0)), UIScale.unscale(Math.max(UIScale.scale(this.padding.bottom) - insets.bottom, 0)), UIScale.unscale(Math.max(UIScale.scale(this.padding.right) - insets.right, 0)));
/*      */     }
/*      */     
/*  473 */     textField.putClientProperty("JTextField.padding", pad);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateEditorColors() {
/*  480 */     boolean isTextComponent = this.editor instanceof JTextComponent;
/*  481 */     this.editor.setForeground(FlatUIUtils.nonUIResource(getForeground((isTextComponent || this.editor.isEnabled()))));
/*      */     
/*  483 */     if (isTextComponent) {
/*  484 */       ((JTextComponent)this.editor).setDisabledTextColor(FlatUIUtils.nonUIResource(getForeground(false)));
/*      */     }
/*      */   }
/*      */   
/*      */   protected JButton createArrowButton() {
/*  489 */     return new FlatComboBoxButton();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void installStyle() {
/*      */     try {
/*  495 */       applyStyle(FlatStylingSupport.getResolvedStyle(this.comboBox, "ComboBox"));
/*  496 */     } catch (RuntimeException ex) {
/*  497 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void applyStyle(Object style) {
/*  503 */     Insets oldPadding = this.padding;
/*  504 */     int oldEditorColumns = this.editorColumns;
/*      */     
/*  506 */     this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
/*      */     
/*  508 */     if (!this.padding.equals(oldPadding)) {
/*  509 */       this.paddingBorder.padding = this.padding;
/*  510 */       updateEditorPadding();
/*      */     } 
/*  512 */     if (this.arrowButton instanceof FlatComboBoxButton)
/*  513 */       ((FlatComboBoxButton)this.arrowButton).updateStyle(); 
/*  514 */     if (this.popup instanceof FlatComboPopup)
/*  515 */       ((FlatComboPopup)this.popup).updateStyle(); 
/*  516 */     if (this.editorColumns != oldEditorColumns && this.editor instanceof JTextField) {
/*  517 */       ((JTextField)this.editor).setColumns(this.editorColumns);
/*      */     }
/*      */   }
/*      */   
/*      */   protected Object applyStyleProperty(String key, Object value) {
/*  522 */     if (this.borderShared == null)
/*  523 */       this.borderShared = new AtomicBoolean(true); 
/*  524 */     return FlatStylingSupport.applyToAnnotatedObjectOrBorder(this, key, value, this.comboBox, this.borderShared);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
/*  530 */     return FlatStylingSupport.getAnnotatedStyleableInfos(this, this.comboBox.getBorder());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getStyleableValue(JComponent c, String key) {
/*  536 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, this.comboBox.getBorder(), key);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MethodHandles.Lookup getLookupForStyling() {
/*  544 */     return MethodHandles.lookup();
/*      */   }
/*      */ 
/*      */   
/*      */   public void update(Graphics g, JComponent c) {
/*  549 */     float focusWidth = FlatUIUtils.getBorderFocusWidth(c);
/*  550 */     float arc = FlatUIUtils.getBorderArc(c);
/*  551 */     boolean paintBackground = true;
/*      */ 
/*      */     
/*  554 */     boolean isCellRenderer = c.getParent() instanceof javax.swing.CellRendererPane;
/*  555 */     if (isCellRenderer) {
/*  556 */       focusWidth = 0.0F;
/*  557 */       arc = 0.0F;
/*  558 */       paintBackground = isCellRendererBackgroundChanged();
/*      */     } 
/*      */ 
/*      */     
/*  562 */     if (c.isOpaque() && (focusWidth > 0.0F || arc > 0.0F)) {
/*  563 */       FlatUIUtils.paintParentBackground(g, c);
/*      */     }
/*  565 */     Graphics2D g2 = (Graphics2D)g;
/*  566 */     Object[] oldRenderingHints = FlatUIUtils.setRenderingHints(g2);
/*      */     
/*  568 */     int width = c.getWidth();
/*  569 */     int height = c.getHeight();
/*  570 */     int arrowX = this.arrowButton.getX();
/*  571 */     int arrowWidth = this.arrowButton.getWidth();
/*      */ 
/*      */     
/*  574 */     boolean paintButton = ((this.comboBox.isEditable() || "button".equals(this.buttonStyle)) && !"none".equals(this.buttonStyle) && !isMacStyle());
/*  575 */     boolean enabled = this.comboBox.isEnabled();
/*  576 */     boolean isLeftToRight = this.comboBox.getComponentOrientation().isLeftToRight();
/*      */ 
/*      */     
/*  579 */     if (paintBackground || c.isOpaque()) {
/*  580 */       g2.setColor(getBackground(enabled));
/*  581 */       FlatUIUtils.paintComponentBackground(g2, 0, 0, width, height, focusWidth, arc);
/*      */ 
/*      */       
/*  584 */       if (enabled && !isCellRenderer) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  589 */         Color buttonColor = paintButton ? this.buttonEditableBackground : (((this.buttonFocusedBackground != null || this.focusedBackground != null) && isPermanentFocusOwner(this.comboBox)) ? ((this.buttonFocusedBackground != null) ? this.buttonFocusedBackground : this.focusedBackground) : this.buttonBackground);
/*  590 */         if (buttonColor != null) {
/*  591 */           g2.setColor(buttonColor);
/*  592 */           if (isMacStyle()) {
/*  593 */             Insets insets = this.comboBox.getInsets();
/*  594 */             int gap = UIScale.scale(2);
/*  595 */             FlatUIUtils.paintComponentBackground(g2, arrowX + gap, insets.top + gap, arrowWidth - gap * 2, height - insets.top - insets.bottom - gap * 2, 0.0F, arc - focusWidth);
/*      */           }
/*      */           else {
/*      */             
/*  599 */             Shape oldClip = g2.getClip();
/*  600 */             if (isLeftToRight) {
/*  601 */               g2.clipRect(arrowX, 0, width - arrowX, height);
/*      */             } else {
/*  603 */               g2.clipRect(0, 0, arrowX + arrowWidth, height);
/*  604 */             }  FlatUIUtils.paintComponentBackground(g2, 0, 0, width, height, focusWidth, arc);
/*  605 */             g2.setClip(oldClip);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  611 */       if (paintButton) {
/*  612 */         Color separatorColor = enabled ? this.buttonSeparatorColor : this.buttonDisabledSeparatorColor;
/*  613 */         if (separatorColor != null && this.buttonSeparatorWidth > 0.0F) {
/*  614 */           g2.setColor(separatorColor);
/*  615 */           float lw = UIScale.scale(this.buttonSeparatorWidth);
/*  616 */           float lx = isLeftToRight ? arrowX : ((arrowX + arrowWidth) - lw);
/*  617 */           g2.fill(new Rectangle2D.Float(lx, focusWidth, lw, (height - 1) - focusWidth * 2.0F));
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  623 */     FlatUIUtils.resetRenderingHints(g2, oldRenderingHints);
/*      */     
/*  625 */     paint(g, c);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void paintCurrentValue(Graphics g, Rectangle bounds, boolean hasFocus) {
/*  631 */     this.paddingBorder.uninstall();
/*      */     
/*  633 */     ListCellRenderer<Object> renderer = (ListCellRenderer)this.comboBox.getRenderer();
/*  634 */     if (renderer == null)
/*  635 */       renderer = new DefaultListCellRenderer(); 
/*  636 */     Component c = renderer.getListCellRendererComponent(this.listBox, this.comboBox.getSelectedItem(), -1, false, false);
/*  637 */     c.setFont(this.comboBox.getFont());
/*  638 */     c.applyComponentOrientation(this.comboBox.getComponentOrientation());
/*      */     
/*  640 */     boolean enabled = this.comboBox.isEnabled();
/*  641 */     c.setBackground(getBackground(enabled));
/*  642 */     c.setForeground(getForeground(enabled));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  647 */     if (c instanceof JComponent) {
/*  648 */       ((JComponent)c).setOpaque(false);
/*      */     }
/*  650 */     boolean shouldValidate = c instanceof javax.swing.JPanel;
/*      */     
/*  652 */     this.paddingBorder.install(c, 0);
/*  653 */     this.currentValuePane.paintComponent(g, c, this.comboBox, bounds.x, bounds.y, bounds.width, bounds.height, shouldValidate);
/*  654 */     this.paddingBorder.uninstall();
/*      */     
/*  656 */     if (c instanceof JComponent) {
/*  657 */       ((JComponent)c).setOpaque(true);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {}
/*      */ 
/*      */   
/*      */   protected Color getBackground(boolean enabled) {
/*  666 */     if (enabled) {
/*  667 */       if (FlatUIUtils.isAWTPeer(this.comboBox)) {
/*  668 */         return this.background;
/*      */       }
/*  670 */       Color background = this.comboBox.getBackground();
/*      */ 
/*      */       
/*  673 */       if (!(background instanceof javax.swing.plaf.UIResource)) {
/*  674 */         return background;
/*      */       }
/*      */       
/*  677 */       if (this.focusedBackground != null && isPermanentFocusOwner(this.comboBox)) {
/*  678 */         return this.focusedBackground;
/*      */       }
/*  680 */       return (this.editableBackground != null && this.comboBox.isEditable()) ? this.editableBackground : background;
/*      */     } 
/*  682 */     return this.isIntelliJTheme ? FlatUIUtils.getParentBackground(this.comboBox) : this.disabledBackground;
/*      */   }
/*      */   
/*      */   protected Color getForeground(boolean enabled) {
/*  686 */     return enabled ? this.comboBox.getForeground() : this.disabledForeground;
/*      */   }
/*      */ 
/*      */   
/*      */   public Dimension getMinimumSize(JComponent c) {
/*  691 */     Dimension minimumSize = super.getMinimumSize(c);
/*  692 */     int fw = Math.round(FlatUIUtils.getBorderFocusWidth(c) * 2.0F);
/*  693 */     minimumSize.width = Math.max(minimumSize.width, UIScale.scale(FlatUIUtils.minimumWidth(c, this.minimumWidth)) + fw);
/*  694 */     return minimumSize;
/*      */   }
/*      */ 
/*      */   
/*      */   protected Dimension getDefaultSize() {
/*  699 */     this.paddingBorder.uninstall();
/*  700 */     Dimension size = super.getDefaultSize();
/*  701 */     this.paddingBorder.uninstall();
/*  702 */     return size;
/*      */   }
/*      */ 
/*      */   
/*      */   protected Dimension getDisplaySize() {
/*  707 */     this.paddingBorder.uninstall();
/*  708 */     Dimension displaySize = super.getDisplaySize();
/*  709 */     this.paddingBorder.uninstall();
/*      */ 
/*      */     
/*  712 */     int displayWidth = displaySize.width - this.padding.left - this.padding.right;
/*  713 */     int displayHeight = displaySize.height - this.padding.top - this.padding.bottom;
/*      */ 
/*      */     
/*  716 */     if (displayWidth == 100 && this.comboBox
/*  717 */       .isEditable() && this.comboBox
/*  718 */       .getItemCount() == 0 && this.comboBox
/*  719 */       .getPrototypeDisplayValue() == null)
/*      */     {
/*  721 */       displayWidth = Math.max((getDefaultSize()).width, (this.editor.getPreferredSize()).width);
/*      */     }
/*      */     
/*  724 */     return new Dimension(displayWidth, displayHeight);
/*      */   }
/*      */ 
/*      */   
/*      */   protected Dimension getSizeForComponent(Component comp) {
/*  729 */     this.paddingBorder.install(comp, 0);
/*  730 */     Dimension size = super.getSizeForComponent(comp);
/*  731 */     this.paddingBorder.uninstall();
/*  732 */     return size;
/*      */   }
/*      */   
/*      */   private boolean isCellRenderer() {
/*  736 */     return this.comboBox.getParent() instanceof javax.swing.CellRendererPane;
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean isCellRendererBackgroundChanged() {
/*  741 */     Container parentParent = this.comboBox.getParent().getParent();
/*  742 */     return (parentParent != null && !this.comboBox.getBackground().equals(parentParent.getBackground()));
/*      */   }
/*      */   
/*      */   private boolean isMacStyle() {
/*  746 */     return "mac".equals(this.buttonStyle);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isPermanentFocusOwner(JComboBox<?> comboBox) {
/*  751 */     if (comboBox.isEditable()) {
/*  752 */       if (FlatUIUtils.isPermanentFocusOwner(comboBox)) {
/*  753 */         return true;
/*      */       }
/*  755 */       Component editorComponent = comboBox.getEditor().getEditorComponent();
/*  756 */       return (editorComponent != null && FlatUIUtils.isPermanentFocusOwner(editorComponent));
/*      */     } 
/*  758 */     return FlatUIUtils.isPermanentFocusOwner(comboBox);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected class FlatComboBoxButton
/*      */     extends FlatArrowButton
/*      */   {
/*      */     protected FlatComboBoxButton() {
/*  767 */       this(5, FlatComboBoxUI.this.arrowType, FlatComboBoxUI.this.buttonArrowColor, FlatComboBoxUI.this.buttonDisabledArrowColor, FlatComboBoxUI.this.buttonHoverArrowColor, (Color)null, FlatComboBoxUI.this.buttonPressedArrowColor, (Color)null);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected FlatComboBoxButton(int direction, String type, Color foreground, Color disabledForeground, Color hoverForeground, Color hoverBackground, Color pressedForeground, Color pressedBackground) {
/*  774 */       super(direction, type, foreground, disabledForeground, hoverForeground, hoverBackground, pressedForeground, pressedBackground);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void updateStyle() {
/*  779 */       updateStyle(FlatComboBoxUI.this.arrowType, FlatComboBoxUI.this.buttonArrowColor, FlatComboBoxUI.this.buttonDisabledArrowColor, FlatComboBoxUI.this.buttonHoverArrowColor, (Color)null, FlatComboBoxUI.this.buttonPressedArrowColor, (Color)null);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int getArrowWidth() {
/*  785 */       return FlatComboBoxUI.this.isMacStyle() ? ((getWidth() % 2 == 0) ? 6 : 7) : super.getArrowWidth();
/*      */     }
/*      */ 
/*      */     
/*      */     public float getArrowThickness() {
/*  790 */       return FlatComboBoxUI.this.isMacStyle() ? 1.5F : super.getArrowThickness();
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isRoundBorderAutoXOffset() {
/*  795 */       return FlatComboBoxUI.this.isMacStyle() ? false : super.isRoundBorderAutoXOffset();
/*      */     }
/*      */ 
/*      */     
/*      */     protected boolean isHover() {
/*  800 */       return (super.isHover() || (!FlatComboBoxUI.this.comboBox.isEditable() && FlatComboBoxUI.this.hover));
/*      */     }
/*      */ 
/*      */     
/*      */     protected boolean isPressed() {
/*  805 */       return (super.isPressed() || (!FlatComboBoxUI.this.comboBox.isEditable() && FlatComboBoxUI.this.pressed));
/*      */     }
/*      */ 
/*      */     
/*      */     protected Color getArrowColor() {
/*  810 */       if (FlatComboBoxUI.this.isCellRenderer() && FlatComboBoxUI.this.isCellRendererBackgroundChanged()) {
/*  811 */         return FlatComboBoxUI.this.comboBox.getForeground();
/*      */       }
/*  813 */       return super.getArrowColor();
/*      */     }
/*      */ 
/*      */     
/*      */     protected void paintArrow(Graphics2D g) {
/*  818 */       if (FlatComboBoxUI.this.isMacStyle() && !FlatComboBoxUI.this.comboBox.isEditable()) {
/*      */         
/*  820 */         int height = getHeight();
/*  821 */         int h = Math.round(height / 2.0F);
/*  822 */         FlatUIUtils.paintArrow(g, 0, 0, getWidth(), h, 1, this.chevron, 
/*  823 */             getArrowWidth(), getArrowThickness(), getXOffset(), getYOffset() + 1.25F);
/*  824 */         FlatUIUtils.paintArrow(g, 0, height - h, getWidth(), h, 5, this.chevron, 
/*  825 */             getArrowWidth(), getArrowThickness(), getXOffset(), getYOffset() - 1.25F);
/*      */       } else {
/*  827 */         super.paintArrow(g);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected class FlatComboPopup
/*      */     extends BasicComboPopup
/*      */   {
/*      */     protected FlatComboPopup(JComboBox combo) {
/*  838 */       super(combo);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  844 */       ComponentOrientation o = this.comboBox.getComponentOrientation();
/*  845 */       this.list.setComponentOrientation(o);
/*  846 */       this.scroller.setComponentOrientation(o);
/*  847 */       setComponentOrientation(o);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected Rectangle computePopupBounds(int px, int py, int pw, int ph) {
/*  853 */       int displayWidth = (FlatComboBoxUI.this.getDisplaySize()).width;
/*      */ 
/*      */       
/*  856 */       for (Border border : new Border[] { this.scroller.getViewportBorder(), this.scroller.getBorder() }) {
/*  857 */         if (border != null) {
/*  858 */           Insets borderInsets = border.getBorderInsets(null);
/*  859 */           displayWidth += borderInsets.left + borderInsets.right;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  864 */       boolean isPopupOverComboBox = isPopupOverComboBox();
/*  865 */       int selectedIndex = -1;
/*  866 */       if (isPopupOverComboBox && (selectedIndex = this.comboBox.getSelectedIndex()) >= 0) {
/*  867 */         displayWidth += FlatComboBoxUI.MacCheckedItemIcon.INSTANCE.getIconWidth() + UIScale.scale(4);
/*      */       }
/*      */       
/*  870 */       JScrollBar verticalScrollBar = this.scroller.getVerticalScrollBar();
/*  871 */       if (verticalScrollBar != null) {
/*  872 */         displayWidth += (verticalScrollBar.getPreferredSize()).width;
/*      */       }
/*      */       
/*  875 */       int pw0 = pw;
/*  876 */       if (displayWidth > pw) {
/*      */         
/*  878 */         GraphicsConfiguration gc = this.comboBox.getGraphicsConfiguration();
/*  879 */         if (gc != null) {
/*  880 */           Rectangle screenBounds = gc.getBounds();
/*  881 */           Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(gc);
/*  882 */           displayWidth = Math.min(displayWidth, screenBounds.width - screenInsets.left - screenInsets.right);
/*      */         } else {
/*  884 */           Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  885 */           displayWidth = Math.min(displayWidth, screenSize.width);
/*      */         } 
/*      */         
/*  888 */         int diff = displayWidth - pw;
/*  889 */         pw = displayWidth;
/*      */         
/*  891 */         if (!this.comboBox.getComponentOrientation().isLeftToRight()) {
/*  892 */           px -= diff;
/*      */         }
/*      */       } 
/*      */       
/*      */       Rectangle cellBounds;
/*  897 */       if (isPopupOverComboBox && selectedIndex >= 0 && (
/*  898 */         cellBounds = this.list.getCellBounds(0, 0)) != null) {
/*      */         
/*  900 */         Insets comboBoxInsets = this.comboBox.getInsets();
/*  901 */         Insets listInsets = this.list.getInsets();
/*  902 */         Insets popupInsets = getInsets();
/*      */ 
/*      */         
/*  905 */         py -= cellBounds.height * (selectedIndex + 1) + comboBoxInsets.top + listInsets.top + popupInsets.top;
/*      */ 
/*      */         
/*  908 */         int offset = Math.min(pw - pw0, FlatComboBoxUI.MacCheckedItemIcon.INSTANCE.getIconWidth()) + UIScale.scale(4);
/*  909 */         if (this.comboBox.getComponentOrientation().isLeftToRight()) {
/*  910 */           px -= offset + comboBoxInsets.right + listInsets.right;
/*      */         } else {
/*  912 */           px += offset + comboBoxInsets.left + listInsets.left;
/*      */         } 
/*      */ 
/*      */         
/*  916 */         return new Rectangle(px, py, pw, ph);
/*      */       } 
/*      */       
/*  919 */       return super.computePopupBounds(px, py, pw, ph);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void configurePopup() {
/*  924 */       super.configurePopup();
/*      */ 
/*      */       
/*  927 */       setOpaque(true);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  932 */       Border border = UIManager.getBorder("PopupMenu.border");
/*  933 */       if (border != null) {
/*  934 */         setBorder(FlatUIUtils.nonUIResource(border));
/*      */       }
/*  936 */       this.list.setCellRenderer(new PopupListCellRenderer());
/*  937 */       updateStyle();
/*      */     }
/*      */     
/*      */     void updateStyle() {
/*  941 */       if (FlatComboBoxUI.this.popupBackground != null) {
/*  942 */         this.list.setBackground(FlatComboBoxUI.this.popupBackground);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  947 */       setBackground(FlatUIUtils.nonUIResource(this.list.getBackground()));
/*      */       
/*  949 */       this.scroller.setViewportBorder((FlatComboBoxUI.this.popupInsets != null) ? new FlatEmptyBorder(FlatComboBoxUI.this.popupInsets) : null);
/*  950 */       this.scroller.setOpaque(false);
/*      */       
/*  952 */       if (this.list.getUI() instanceof FlatListUI) {
/*  953 */         FlatListUI ui = (FlatListUI)this.list.getUI();
/*  954 */         ui.selectionInsets = FlatComboBoxUI.this.selectionInsets;
/*  955 */         ui.selectionArc = FlatComboBoxUI.this.selectionArc;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     protected PropertyChangeListener createPropertyChangeListener() {
/*  961 */       PropertyChangeListener superListener = super.createPropertyChangeListener();
/*  962 */       return e -> {
/*      */           superListener.propertyChange(e);
/*      */           if (e.getPropertyName() == "renderer") {
/*      */             this.list.setCellRenderer(new PopupListCellRenderer());
/*      */           }
/*      */         };
/*      */     }
/*      */ 
/*      */     
/*      */     protected int getPopupHeightForRowCount(int maxRowCount) {
/*  972 */       int height = super.getPopupHeightForRowCount(maxRowCount);
/*  973 */       FlatComboBoxUI.this.paddingBorder.uninstall();
/*  974 */       return height;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void show(Component invoker, int x, int y) {
/*  981 */       if (y < 0 && !SystemInfo.isJava_9_orLater) {
/*  982 */         Border popupBorder = getBorder();
/*  983 */         if (popupBorder != null) {
/*  984 */           Insets insets = popupBorder.getBorderInsets(this);
/*  985 */           y -= insets.top + insets.bottom;
/*      */         } 
/*      */       } 
/*      */       
/*  989 */       super.show(invoker, x, y);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void paintChildren(Graphics g) {
/*  994 */       super.paintChildren(g);
/*  995 */       FlatComboBoxUI.this.paddingBorder.uninstall();
/*      */     }
/*      */     
/*      */     private boolean isPopupOverComboBox() {
/*  999 */       return (FlatComboBoxUI.this.isMacStyle() && 
/* 1000 */         !this.comboBox.isEditable() && this.comboBox
/* 1001 */         .getItemCount() > 0 && this.comboBox
/* 1002 */         .getItemCount() <= this.comboBox.getMaximumRowCount() && 
/*      */         
/* 1004 */         !FlatClientProperties.clientPropertyBoolean(this.comboBox, "JComboBox.isPopDown", false));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private class PopupListCellRenderer
/*      */       implements ListCellRenderer
/*      */     {
/*      */       private PopupListCellRenderer() {}
/*      */ 
/*      */       
/*      */       public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
/* 1016 */         FlatComboBoxUI.this.paddingBorder.uninstall();
/*      */         
/* 1018 */         ListCellRenderer<Object> renderer = FlatComboBoxUI.FlatComboPopup.this.comboBox.getRenderer();
/* 1019 */         if (renderer == null)
/* 1020 */           renderer = new DefaultListCellRenderer(); 
/* 1021 */         Component c = renderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
/* 1022 */         c.applyComponentOrientation(FlatComboBoxUI.FlatComboPopup.this.comboBox.getComponentOrientation());
/*      */ 
/*      */         
/* 1025 */         if (FlatComboBoxUI.FlatComboPopup.this.isPopupOverComboBox() && c instanceof JComponent) {
/* 1026 */           int selectedIndex = FlatComboBoxUI.FlatComboPopup.this.comboBox.getSelectedIndex();
/* 1027 */           ((JComponent)c).putClientProperty("FlatLaf.internal.FlatComboBoxUI.macStyleHint", 
/* 1028 */               (selectedIndex >= 0) ? Boolean.valueOf((index == selectedIndex)) : null);
/*      */         } 
/*      */         
/* 1031 */         FlatComboBoxUI.this.paddingBorder.install(c, Math.round(FlatUIUtils.getBorderFocusWidth(FlatComboBoxUI.FlatComboPopup.this.comboBox)));
/*      */         
/* 1033 */         return c;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static class CellPaddingBorder
/*      */     extends AbstractBorder
/*      */   {
/*      */     static final String KEY_MAC_STYLE_HINT = "FlatLaf.internal.FlatComboBoxUI.macStyleHint";
/*      */ 
/*      */     
/*      */     static final int MAC_STYLE_GAP = 4;
/*      */ 
/*      */     
/*      */     private Insets padding;
/*      */ 
/*      */     
/*      */     private JComponent rendererComponent;
/*      */ 
/*      */     
/*      */     private Border rendererBorder;
/*      */ 
/*      */     
/*      */     private int focusWidth;
/*      */ 
/*      */ 
/*      */     
/*      */     CellPaddingBorder(Insets padding) {
/* 1063 */       this.padding = padding;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     synchronized void install(Component c, int focusWidth) {
/* 1069 */       if (!(c instanceof JComponent)) {
/*      */         return;
/*      */       }
/* 1072 */       this.focusWidth = focusWidth;
/*      */       
/* 1074 */       JComponent jc = (JComponent)c;
/* 1075 */       Border oldBorder = jc.getBorder();
/* 1076 */       if (oldBorder == this) {
/*      */         return;
/*      */       }
/*      */ 
/*      */       
/* 1081 */       if (oldBorder instanceof CellPaddingBorder) {
/* 1082 */         ((CellPaddingBorder)oldBorder).uninstall();
/*      */       }
/*      */ 
/*      */       
/* 1086 */       uninstall();
/*      */ 
/*      */       
/* 1089 */       this.rendererComponent = jc;
/*      */ 
/*      */       
/* 1092 */       this.rendererBorder = jc.getBorder();
/* 1093 */       jc.setBorder(this);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     synchronized void uninstall() {
/* 1103 */       if (this.rendererComponent == null) {
/*      */         return;
/*      */       }
/* 1106 */       this.rendererComponent.putClientProperty("FlatLaf.internal.FlatComboBoxUI.macStyleHint", (Object)null);
/*      */       
/* 1108 */       if (this.rendererComponent.getBorder() == this)
/* 1109 */         this.rendererComponent.setBorder(this.rendererBorder); 
/* 1110 */       this.rendererComponent = null;
/* 1111 */       this.rendererBorder = null;
/*      */     }
/*      */ 
/*      */     
/*      */     public synchronized Insets getBorderInsets(Component c, Insets insets) {
/* 1116 */       Insets padding = UIScale.scale(this.padding);
/* 1117 */       if (this.rendererBorder != null && !(this.rendererBorder instanceof CellPaddingBorder)) {
/* 1118 */         Insets insideInsets = this.rendererBorder.getBorderInsets(c);
/* 1119 */         insets.top = Math.max(padding.top, insideInsets.top);
/* 1120 */         insets.left = Math.max(padding.left, insideInsets.left);
/* 1121 */         insets.bottom = Math.max(padding.bottom, insideInsets.bottom);
/* 1122 */         insets.right = Math.max(padding.right, insideInsets.right);
/*      */       } else {
/* 1124 */         insets.top = padding.top;
/* 1125 */         insets.left = padding.left;
/* 1126 */         insets.bottom = padding.bottom;
/* 1127 */         insets.right = padding.right;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1132 */       insets.left += this.focusWidth;
/* 1133 */       insets.right += this.focusWidth;
/*      */ 
/*      */       
/* 1136 */       if (c instanceof JComponent) {
/* 1137 */         Boolean macStyleHint = FlatClientProperties.clientPropertyBooleanStrict((JComponent)c, "FlatLaf.internal.FlatComboBoxUI.macStyleHint", null);
/* 1138 */         if (macStyleHint != null) {
/* 1139 */           int indent = FlatComboBoxUI.MacCheckedItemIcon.INSTANCE.getIconWidth() + UIScale.scale(4);
/* 1140 */           if (c.getComponentOrientation().isLeftToRight()) {
/* 1141 */             insets.left += indent;
/*      */           } else {
/* 1143 */             insets.right += indent;
/*      */           } 
/*      */         } 
/*      */       } 
/* 1147 */       return insets;
/*      */     }
/*      */ 
/*      */     
/*      */     public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
/* 1152 */       if (this.rendererBorder != null) {
/* 1153 */         this.rendererBorder.paintBorder(c, g, x, y, width, height);
/*      */       }
/*      */       
/* 1156 */       if (c instanceof JComponent) {
/* 1157 */         Boolean macStyleHint = FlatClientProperties.clientPropertyBooleanStrict((JComponent)c, "FlatLaf.internal.FlatComboBoxUI.macStyleHint", null);
/* 1158 */         if (macStyleHint == Boolean.TRUE) {
/*      */ 
/*      */ 
/*      */           
/* 1162 */           int ix = c.getComponentOrientation().isLeftToRight() ? (x + UIScale.scale(this.padding.left)) : (x + width - UIScale.scale(this.padding.right) - FlatComboBoxUI.MacCheckedItemIcon.INSTANCE.getIconWidth());
/* 1163 */           FlatComboBoxUI.MacCheckedItemIcon.INSTANCE.paintIcon(c, g, ix, y + (height - FlatComboBoxUI.MacCheckedItemIcon.INSTANCE.getIconHeight()) / 2);
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class MacCheckedItemIcon
/*      */     extends FlatCheckBoxMenuItemIcon
/*      */   {
/* 1177 */     static MacCheckedItemIcon INSTANCE = new MacCheckedItemIcon();
/*      */ 
/*      */     
/*      */     protected void paintIcon(Component c, Graphics2D g2) {
/* 1181 */       g2.setColor(c.getForeground());
/* 1182 */       paintCheckmark(g2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private class EditorDelegateAction
/*      */     extends AbstractAction
/*      */   {
/*      */     private final KeyStroke keyStroke;
/*      */ 
/*      */ 
/*      */     
/*      */     EditorDelegateAction(InputMap inputMap, KeyStroke keyStroke) {
/* 1197 */       this.keyStroke = keyStroke;
/*      */ 
/*      */       
/* 1200 */       inputMap.put(keyStroke, this);
/*      */     }
/*      */ 
/*      */     
/*      */     public void actionPerformed(ActionEvent e) {
/* 1205 */       ActionListener action = FlatComboBoxUI.this.comboBox.getActionForKeyStroke(this.keyStroke);
/* 1206 */       if (action != null)
/* 1207 */         action.actionPerformed(new ActionEvent(FlatComboBoxUI.this.comboBox, e.getID(), e
/* 1208 */               .getActionCommand(), e.getWhen(), e.getModifiers())); 
/*      */     }
/*      */   }
/*      */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatComboBoxUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */