/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.LoggingFacade;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JSpinner;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.LookAndFeel;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicSpinnerUI;
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
/*     */ public class FlatSpinnerUI
/*     */   extends BasicSpinnerUI
/*     */   implements FlatStylingSupport.StyleableUI
/*     */ {
/*     */   private Handler handler;
/*     */   @Styleable
/*     */   protected int minimumWidth;
/*     */   @Styleable
/*     */   protected String buttonStyle;
/*     */   @Styleable
/*     */   protected String arrowType;
/*     */   protected boolean isIntelliJTheme;
/*     */   @Styleable
/*     */   protected Color disabledBackground;
/*     */   @Styleable
/*     */   protected Color disabledForeground;
/*     */   @Styleable
/*     */   protected Color focusedBackground;
/*     */   @Styleable
/*     */   protected Color buttonBackground;
/*     */   @Styleable
/*     */   protected float buttonSeparatorWidth;
/*     */   @Styleable
/*     */   protected Color buttonSeparatorColor;
/*     */   @Styleable
/*     */   protected Color buttonDisabledSeparatorColor;
/*     */   @Styleable
/*     */   protected Color buttonArrowColor;
/*     */   @Styleable
/*     */   protected Color buttonDisabledArrowColor;
/*     */   @Styleable
/*     */   protected Color buttonHoverArrowColor;
/*     */   @Styleable
/*     */   protected Color buttonPressedArrowColor;
/*     */   @Styleable
/*     */   protected Insets padding;
/*     */   private Map<String, Object> oldStyleValues;
/*     */   private AtomicBoolean borderShared;
/*     */   private static final int MAC_STEPPER_WIDTH = 15;
/*     */   private static final int MAC_STEPPER_GAP = 3;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/* 113 */     return new FlatSpinnerUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/* 118 */     super.installUI(c);
/*     */     
/* 120 */     installStyle();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installDefaults() {
/* 125 */     super.installDefaults();
/*     */     
/* 127 */     LookAndFeel.installProperty(this.spinner, "opaque", Boolean.valueOf(false));
/*     */     
/* 129 */     this.minimumWidth = UIManager.getInt("Component.minimumWidth");
/* 130 */     this.buttonStyle = UIManager.getString("Spinner.buttonStyle");
/* 131 */     this.arrowType = UIManager.getString("Component.arrowType");
/* 132 */     this.isIntelliJTheme = UIManager.getBoolean("Component.isIntelliJTheme");
/* 133 */     this.disabledBackground = UIManager.getColor("Spinner.disabledBackground");
/* 134 */     this.disabledForeground = UIManager.getColor("Spinner.disabledForeground");
/* 135 */     this.focusedBackground = UIManager.getColor("Spinner.focusedBackground");
/* 136 */     this.buttonBackground = UIManager.getColor("Spinner.buttonBackground");
/* 137 */     this.buttonSeparatorWidth = FlatUIUtils.getUIFloat("Spinner.buttonSeparatorWidth", FlatUIUtils.getUIFloat("Component.borderWidth", 1.0F));
/* 138 */     this.buttonSeparatorColor = UIManager.getColor("Spinner.buttonSeparatorColor");
/* 139 */     this.buttonDisabledSeparatorColor = UIManager.getColor("Spinner.buttonDisabledSeparatorColor");
/* 140 */     this.buttonArrowColor = UIManager.getColor("Spinner.buttonArrowColor");
/* 141 */     this.buttonDisabledArrowColor = UIManager.getColor("Spinner.buttonDisabledArrowColor");
/* 142 */     this.buttonHoverArrowColor = UIManager.getColor("Spinner.buttonHoverArrowColor");
/* 143 */     this.buttonPressedArrowColor = UIManager.getColor("Spinner.buttonPressedArrowColor");
/* 144 */     this.padding = UIManager.getInsets("Spinner.padding");
/*     */     
/* 146 */     MigLayoutVisualPadding.install(this.spinner);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallDefaults() {
/* 151 */     super.uninstallDefaults();
/*     */     
/* 153 */     this.disabledBackground = null;
/* 154 */     this.disabledForeground = null;
/* 155 */     this.focusedBackground = null;
/* 156 */     this.buttonBackground = null;
/* 157 */     this.buttonSeparatorColor = null;
/* 158 */     this.buttonDisabledSeparatorColor = null;
/* 159 */     this.buttonArrowColor = null;
/* 160 */     this.buttonDisabledArrowColor = null;
/* 161 */     this.buttonHoverArrowColor = null;
/* 162 */     this.buttonPressedArrowColor = null;
/* 163 */     this.padding = null;
/*     */     
/* 165 */     this.oldStyleValues = null;
/* 166 */     this.borderShared = null;
/*     */     
/* 168 */     MigLayoutVisualPadding.uninstall(this.spinner);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installListeners() {
/* 173 */     super.installListeners();
/*     */     
/* 175 */     addEditorFocusListener(this.spinner.getEditor());
/* 176 */     this.spinner.addFocusListener(getHandler());
/* 177 */     this.spinner.addPropertyChangeListener(getHandler());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallListeners() {
/* 182 */     super.uninstallListeners();
/*     */     
/* 184 */     removeEditorFocusListener(this.spinner.getEditor());
/* 185 */     this.spinner.removeFocusListener(getHandler());
/* 186 */     this.spinner.removePropertyChangeListener(getHandler());
/*     */     
/* 188 */     this.handler = null;
/*     */   }
/*     */   
/*     */   private Handler getHandler() {
/* 192 */     if (this.handler == null)
/* 193 */       this.handler = new Handler(); 
/* 194 */     return this.handler;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installStyle() {
/*     */     try {
/* 200 */       applyStyle(FlatStylingSupport.getResolvedStyle(this.spinner, "Spinner"));
/* 201 */     } catch (RuntimeException ex) {
/* 202 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyStyle(Object style) {
/* 208 */     this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
/* 209 */     updateEditorPadding();
/* 210 */     updateArrowButtonsStyle();
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object applyStyleProperty(String key, Object value) {
/* 215 */     if (this.borderShared == null)
/* 216 */       this.borderShared = new AtomicBoolean(true); 
/* 217 */     return FlatStylingSupport.applyToAnnotatedObjectOrBorder(this, key, value, this.spinner, this.borderShared);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
/* 223 */     return FlatStylingSupport.getAnnotatedStyleableInfos(this, this.spinner.getBorder());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(JComponent c, String key) {
/* 229 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, this.spinner.getBorder(), key);
/*     */   }
/*     */ 
/*     */   
/*     */   protected JComponent createEditor() {
/* 234 */     JComponent editor = super.createEditor();
/* 235 */     configureEditor(editor);
/* 236 */     return editor;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void replaceEditor(JComponent oldEditor, JComponent newEditor) {
/* 241 */     super.replaceEditor(oldEditor, newEditor);
/*     */     
/* 243 */     configureEditor(newEditor);
/*     */     
/* 245 */     removeEditorFocusListener(oldEditor);
/* 246 */     addEditorFocusListener(newEditor);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void configureEditor(JComponent editor) {
/* 252 */     editor.setOpaque(false);
/* 253 */     JTextField textField = getEditorTextField(editor);
/* 254 */     if (textField != null) {
/* 255 */       textField.setOpaque(false);
/*     */     }
/* 257 */     updateEditorPadding();
/* 258 */     updateEditorColors();
/*     */   }
/*     */   
/*     */   private void addEditorFocusListener(JComponent editor) {
/* 262 */     JTextField textField = getEditorTextField(editor);
/* 263 */     if (textField != null)
/* 264 */       textField.addFocusListener(getHandler()); 
/*     */   }
/*     */   
/*     */   private void removeEditorFocusListener(JComponent editor) {
/* 268 */     JTextField textField = getEditorTextField(editor);
/* 269 */     if (textField != null)
/* 270 */       textField.removeFocusListener(getHandler()); 
/*     */   }
/*     */   
/*     */   private void updateEditorPadding() {
/* 274 */     JTextField textField = getEditorTextField(this.spinner.getEditor());
/* 275 */     if (textField != null)
/* 276 */       textField.putClientProperty("JTextField.padding", this.padding); 
/*     */   }
/*     */   
/*     */   private void updateEditorColors() {
/* 280 */     JTextField textField = getEditorTextField(this.spinner.getEditor());
/* 281 */     if (textField != null) {
/*     */ 
/*     */ 
/*     */       
/* 285 */       textField.setForeground(FlatUIUtils.nonUIResource(getForeground(true)));
/* 286 */       textField.setDisabledTextColor(FlatUIUtils.nonUIResource(getForeground(false)));
/*     */     } 
/*     */   }
/*     */   
/*     */   private static JTextField getEditorTextField(JComponent editor) {
/* 291 */     return (editor instanceof JSpinner.DefaultEditor) ? (
/* 292 */       (JSpinner.DefaultEditor)editor).getTextField() : 
/* 293 */       null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isPermanentFocusOwner(JSpinner spinner) {
/* 298 */     if (FlatUIUtils.isPermanentFocusOwner(spinner)) {
/* 299 */       return true;
/*     */     }
/* 301 */     JTextField textField = getEditorTextField(spinner.getEditor());
/* 302 */     return (textField != null && FlatUIUtils.isPermanentFocusOwner(textField));
/*     */   }
/*     */   
/*     */   protected Color getBackground(boolean enabled) {
/* 306 */     if (enabled) {
/* 307 */       Color background = this.spinner.getBackground();
/*     */ 
/*     */       
/* 310 */       if (!(background instanceof javax.swing.plaf.UIResource)) {
/* 311 */         return background;
/*     */       }
/*     */       
/* 314 */       if (this.focusedBackground != null && isPermanentFocusOwner(this.spinner)) {
/* 315 */         return this.focusedBackground;
/*     */       }
/* 317 */       return background;
/*     */     } 
/* 319 */     return this.isIntelliJTheme ? FlatUIUtils.getParentBackground(this.spinner) : this.disabledBackground;
/*     */   }
/*     */   
/*     */   protected Color getForeground(boolean enabled) {
/* 323 */     return enabled ? this.spinner.getForeground() : this.disabledForeground;
/*     */   }
/*     */ 
/*     */   
/*     */   protected LayoutManager createLayout() {
/* 328 */     return getHandler();
/*     */   }
/*     */ 
/*     */   
/*     */   protected Component createNextButton() {
/* 333 */     return createArrowButton(1, "Spinner.nextButton");
/*     */   }
/*     */ 
/*     */   
/*     */   protected Component createPreviousButton() {
/* 338 */     return createArrowButton(5, "Spinner.previousButton");
/*     */   }
/*     */   
/*     */   private Component createArrowButton(int direction, String name) {
/* 342 */     FlatArrowButton button = new FlatArrowButton(direction, this.arrowType, this.buttonArrowColor, this.buttonDisabledArrowColor, this.buttonHoverArrowColor, null, this.buttonPressedArrowColor, null)
/*     */       {
/*     */         
/*     */         public int getArrowWidth()
/*     */         {
/* 347 */           return FlatSpinnerUI.this.isMacStyle() ? 7 : super.getArrowWidth();
/*     */         }
/*     */         
/*     */         public float getArrowThickness() {
/* 351 */           return FlatSpinnerUI.this.isMacStyle() ? 1.5F : super.getArrowThickness();
/*     */         }
/*     */         
/*     */         public float getYOffset() {
/* 355 */           return FlatSpinnerUI.this.isMacStyle() ? 0.0F : super.getYOffset();
/*     */         }
/*     */         
/*     */         public boolean isRoundBorderAutoXOffset() {
/* 359 */           return FlatSpinnerUI.this.isMacStyle() ? false : super.isRoundBorderAutoXOffset();
/*     */         }
/*     */       };
/* 362 */     button.setName(name);
/* 363 */     button.setYOffset((direction == 1) ? 1.25F : -1.25F);
/* 364 */     if (direction == 1) {
/* 365 */       installNextButtonListeners(button);
/*     */     } else {
/* 367 */       installPreviousButtonListeners(button);
/* 368 */     }  return button;
/*     */   }
/*     */   
/*     */   private void updateArrowButtonsStyle() {
/* 372 */     for (Component c : this.spinner.getComponents()) {
/* 373 */       if (c instanceof FlatArrowButton) {
/* 374 */         ((FlatArrowButton)c).updateStyle(this.arrowType, this.buttonArrowColor, this.buttonDisabledArrowColor, this.buttonHoverArrowColor, (Color)null, this.buttonPressedArrowColor, (Color)null);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void update(Graphics g, JComponent c) {
/* 382 */     float focusWidth = FlatUIUtils.getBorderFocusWidth(c);
/* 383 */     float arc = FlatUIUtils.getBorderArc(c);
/*     */ 
/*     */     
/* 386 */     if (c.isOpaque() && (focusWidth > 0.0F || arc > 0.0F)) {
/* 387 */       FlatUIUtils.paintParentBackground(g, c);
/*     */     }
/* 389 */     Graphics2D g2 = (Graphics2D)g;
/* 390 */     Object[] oldRenderingHints = FlatUIUtils.setRenderingHints(g2);
/*     */     
/* 392 */     int width = c.getWidth();
/* 393 */     int height = c.getHeight();
/* 394 */     boolean enabled = this.spinner.isEnabled();
/* 395 */     boolean ltr = this.spinner.getComponentOrientation().isLeftToRight();
/* 396 */     boolean isMacStyle = isMacStyle();
/* 397 */     int macStyleButtonsWidth = isMacStyle ? getMacStyleButtonsWidth() : 0;
/*     */ 
/*     */     
/* 400 */     g2.setColor(getBackground(enabled));
/* 401 */     FlatUIUtils.paintComponentBackground(g2, ltr ? 0 : macStyleButtonsWidth, 0, width - macStyleButtonsWidth, height, focusWidth, arc);
/*     */ 
/*     */     
/* 404 */     boolean paintButton = !"none".equals(this.buttonStyle);
/* 405 */     Handler handler = getHandler();
/* 406 */     if (paintButton && (handler.nextButton != null || handler.previousButton != null)) {
/* 407 */       Component button = (handler.nextButton != null) ? handler.nextButton : handler.previousButton;
/* 408 */       int arrowX = button.getX();
/* 409 */       int arrowWidth = button.getWidth();
/* 410 */       Color separatorColor = enabled ? this.buttonSeparatorColor : this.buttonDisabledSeparatorColor;
/*     */       
/* 412 */       if (isMacStyle) {
/* 413 */         Insets insets = this.spinner.getInsets();
/* 414 */         int lineWidth = Math.round(FlatUIUtils.getBorderLineWidth(this.spinner));
/* 415 */         int bx = arrowX;
/* 416 */         int by = insets.top - lineWidth;
/* 417 */         int bw = arrowWidth;
/* 418 */         int bh = height - insets.top - insets.bottom + lineWidth * 2;
/* 419 */         float lw = UIScale.scale(this.buttonSeparatorWidth);
/*     */ 
/*     */         
/* 422 */         FlatUIUtils.paintOutlinedComponent(g2, bx, by, bw, bh, 0.0F, 0.0F, 0.0F, lw, 
/* 423 */             UIScale.scale(12), null, separatorColor, this.buttonBackground);
/*     */ 
/*     */ 
/*     */         
/* 427 */         if (separatorColor != null) {
/* 428 */           int thickness = UIScale.scale(1);
/* 429 */           g2.setColor(separatorColor);
/* 430 */           g2.fill(new Rectangle2D.Float(bx + lw, by + (bh - thickness) / 2.0F, bw - lw * 2.0F, thickness));
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 435 */         if (enabled && this.buttonBackground != null) {
/* 436 */           g2.setColor(this.buttonBackground);
/* 437 */           Shape oldClip = g2.getClip();
/* 438 */           if (ltr) {
/* 439 */             g2.clipRect(arrowX, 0, width - arrowX, height);
/*     */           } else {
/* 441 */             g2.clipRect(0, 0, arrowX + arrowWidth, height);
/* 442 */           }  FlatUIUtils.paintComponentBackground(g2, 0, 0, width, height, focusWidth, arc);
/* 443 */           g2.setClip(oldClip);
/*     */         } 
/*     */ 
/*     */         
/* 447 */         if (separatorColor != null && this.buttonSeparatorWidth > 0.0F) {
/* 448 */           g2.setColor(separatorColor);
/* 449 */           float lw = UIScale.scale(this.buttonSeparatorWidth);
/* 450 */           float lx = ltr ? arrowX : ((arrowX + arrowWidth) - lw);
/* 451 */           g2.fill(new Rectangle2D.Float(lx, focusWidth, lw, (height - 1) - focusWidth * 2.0F));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 456 */     paint(g, c);
/*     */     
/* 458 */     FlatUIUtils.resetRenderingHints(g, oldRenderingHints);
/*     */   }
/*     */   
/*     */   boolean isMacStyle() {
/* 462 */     return "mac".equals(this.buttonStyle);
/*     */   }
/*     */   
/*     */   int getMacStyleButtonsWidth() {
/* 466 */     return (this.handler.nextButton != null || this.handler.previousButton != null) ? (
/* 467 */       UIScale.scale(3) + UIScale.scale(15)) : 
/* 468 */       0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private class Handler
/*     */     implements LayoutManager, FocusListener, PropertyChangeListener
/*     */   {
/* 481 */     private Component editor = null;
/*     */     
/*     */     private Component nextButton;
/*     */     private Component previousButton;
/*     */     
/*     */     public void addLayoutComponent(String name, Component c) {
/* 487 */       switch (name) { case "Editor":
/* 488 */           this.editor = c; break;
/* 489 */         case "Next": this.nextButton = c; break;
/* 490 */         case "Previous": this.previousButton = c;
/*     */           break; }
/*     */     
/*     */     }
/*     */     
/*     */     public void removeLayoutComponent(Component c) {
/* 496 */       if (c == this.editor) {
/* 497 */         this.editor = null;
/* 498 */       } else if (c == this.nextButton) {
/* 499 */         this.nextButton = null;
/* 500 */       } else if (c == this.previousButton) {
/* 501 */         this.previousButton = null;
/*     */       } 
/*     */     }
/*     */     
/*     */     public Dimension preferredLayoutSize(Container parent) {
/* 506 */       Insets insets = parent.getInsets();
/* 507 */       Insets padding = UIScale.scale(FlatSpinnerUI.this.padding);
/* 508 */       Dimension editorSize = (this.editor != null) ? this.editor.getPreferredSize() : new Dimension(0, 0);
/*     */ 
/*     */       
/* 511 */       int minimumWidth = FlatUIUtils.minimumWidth(FlatSpinnerUI.this.spinner, FlatSpinnerUI.this.minimumWidth);
/* 512 */       int innerHeight = editorSize.height + padding.top + padding.bottom;
/* 513 */       float focusWidth = FlatUIUtils.getBorderFocusWidth(FlatSpinnerUI.this.spinner);
/* 514 */       return new Dimension(
/* 515 */           Math.max(insets.left + insets.right + editorSize.width + padding.left + padding.right + innerHeight, UIScale.scale(minimumWidth) + Math.round(focusWidth * 2.0F)), insets.top + insets.bottom + innerHeight);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Dimension minimumLayoutSize(Container parent) {
/* 521 */       return preferredLayoutSize(parent);
/*     */     }
/*     */ 
/*     */     
/*     */     public void layoutContainer(Container parent) {
/* 526 */       Dimension size = parent.getSize();
/* 527 */       Insets insets = parent.getInsets();
/* 528 */       Rectangle r = FlatUIUtils.subtractInsets(new Rectangle(size), insets);
/*     */ 
/*     */       
/* 531 */       if (this.nextButton == null && this.previousButton == null) {
/* 532 */         if (this.editor != null) {
/* 533 */           this.editor.setBounds(r);
/*     */         }
/*     */         return;
/*     */       } 
/* 537 */       Rectangle editorRect = new Rectangle(r);
/* 538 */       Rectangle buttonsRect = new Rectangle(r);
/*     */ 
/*     */       
/* 541 */       FontMetrics fm = FlatSpinnerUI.this.spinner.getFontMetrics(FlatSpinnerUI.this.spinner.getFont());
/* 542 */       int maxButtonWidth = fm.getHeight() + UIScale.scale(FlatSpinnerUI.this.padding.top) + UIScale.scale(FlatSpinnerUI.this.padding.bottom);
/* 543 */       int minButtonWidth = maxButtonWidth * 3 / 4;
/*     */ 
/*     */       
/* 546 */       boolean isMacStyle = FlatSpinnerUI.this.isMacStyle();
/* 547 */       int buttonsGap = isMacStyle ? UIScale.scale(3) : 0;
/* 548 */       int prefButtonWidth = isMacStyle ? UIScale.scale(15) : buttonsRect.height;
/* 549 */       int buttonsWidth = Math.min(Math.max(prefButtonWidth, minButtonWidth), maxButtonWidth);
/*     */ 
/*     */       
/* 552 */       buttonsRect.width = buttonsWidth;
/* 553 */       editorRect.width -= buttonsWidth + buttonsGap;
/* 554 */       boolean ltr = parent.getComponentOrientation().isLeftToRight();
/* 555 */       if (ltr) {
/* 556 */         buttonsRect.x += editorRect.width + buttonsGap;
/*     */       } else {
/* 558 */         editorRect.x += buttonsWidth + buttonsGap;
/*     */       } 
/*     */ 
/*     */       
/* 562 */       if (isMacStyle) {
/* 563 */         int lineWidth = Math.round(FlatUIUtils.getBorderLineWidth(FlatSpinnerUI.this.spinner));
/* 564 */         if (lineWidth > 0) {
/* 565 */           buttonsRect.x += ltr ? lineWidth : -lineWidth;
/* 566 */           buttonsRect.y -= lineWidth;
/* 567 */           buttonsRect.height += lineWidth * 2;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 572 */       if (this.editor != null) {
/* 573 */         this.editor.setBounds(editorRect);
/*     */       }
/*     */       
/* 576 */       int nextHeight = buttonsRect.height / 2 + buttonsRect.height % 2;
/* 577 */       if (this.nextButton != null)
/* 578 */         this.nextButton.setBounds(buttonsRect.x, buttonsRect.y, buttonsRect.width, nextHeight); 
/* 579 */       if (this.previousButton != null) {
/*     */ 
/*     */         
/* 582 */         int previousY = buttonsRect.y + buttonsRect.height - nextHeight;
/* 583 */         this.previousButton.setBounds(buttonsRect.x, previousY, buttonsRect.width, nextHeight);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void focusGained(FocusEvent e) {
/* 592 */       FlatSpinnerUI.this.spinner.repaint();
/*     */ 
/*     */       
/* 595 */       if (e.getComponent() == FlatSpinnerUI.this.spinner) {
/* 596 */         JTextField textField = FlatSpinnerUI.getEditorTextField(FlatSpinnerUI.this.spinner.getEditor());
/* 597 */         if (textField != null) {
/* 598 */           textField.requestFocusInWindow();
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void focusLost(FocusEvent e) {
/* 605 */       FlatSpinnerUI.this.spinner.repaint();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void propertyChange(PropertyChangeEvent e) {
/* 612 */       switch (e.getPropertyName()) {
/*     */         case "foreground":
/*     */         case "enabled":
/* 615 */           FlatSpinnerUI.this.updateEditorColors();
/*     */           break;
/*     */         
/*     */         case "JComponent.roundRect":
/*     */         case "JComponent.outline":
/* 620 */           FlatSpinnerUI.this.spinner.repaint();
/*     */           break;
/*     */         
/*     */         case "JComponent.minimumWidth":
/* 624 */           FlatSpinnerUI.this.spinner.revalidate();
/*     */           break;
/*     */         
/*     */         case "FlatLaf.style":
/*     */         case "FlatLaf.styleClass":
/* 629 */           FlatSpinnerUI.this.installStyle();
/* 630 */           FlatSpinnerUI.this.spinner.revalidate();
/* 631 */           FlatSpinnerUI.this.spinner.repaint();
/*     */           break;
/*     */       } 
/*     */     }
/*     */     
/*     */     private Handler() {}
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatSpinnerUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */