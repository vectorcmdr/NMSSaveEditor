/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.LoggingFacade;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.FocusTraversalPolicy;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.ContainerEvent;
/*     */ import java.awt.event.ContainerListener;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Map;
/*     */ import javax.swing.AbstractButton;
/*     */ import javax.swing.ButtonGroup;
/*     */ import javax.swing.ButtonModel;
/*     */ import javax.swing.DefaultButtonModel;
/*     */ import javax.swing.InputMap;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JToolBar;
/*     */ import javax.swing.LayoutFocusTraversalPolicy;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicToolBarUI;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlatToolBarUI
/*     */   extends BasicToolBarUI
/*     */   implements FlatStylingSupport.StyleableUI
/*     */ {
/*     */   @Styleable
/*     */   protected boolean focusableButtons;
/*     */   @Styleable
/*     */   protected boolean arrowKeysOnlyNavigation;
/*     */   @Styleable
/*     */   protected int hoverButtonGroupArc;
/*     */   @Styleable
/*     */   protected Color hoverButtonGroupBackground;
/*     */   @Styleable
/*     */   protected Insets borderMargins;
/*     */   @Styleable
/*     */   protected Color gripColor;
/*     */   private FocusTraversalPolicy focusTraversalPolicy;
/*     */   private Boolean oldFloatable;
/*     */   private Map<String, Object> oldStyleValues;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/*  99 */     return new FlatToolBarUI();
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/* 104 */     super.installUI(c);
/*     */     
/* 106 */     installFocusTraversalPolicy();
/*     */     
/* 108 */     installStyle();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 113 */     if (!this.focusableButtons) {
/* 114 */       setButtonsFocusable(false);
/*     */     }
/*     */   }
/*     */   
/*     */   public void uninstallUI(JComponent c) {
/* 119 */     super.uninstallUI(c);
/*     */ 
/*     */     
/* 122 */     if (!this.focusableButtons) {
/* 123 */       setButtonsFocusable(true);
/*     */     }
/* 125 */     uninstallFocusTraversalPolicy();
/*     */     
/* 127 */     this.oldStyleValues = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installDefaults() {
/* 132 */     super.installDefaults();
/*     */     
/* 134 */     this.focusableButtons = UIManager.getBoolean("ToolBar.focusableButtons");
/* 135 */     this.arrowKeysOnlyNavigation = UIManager.getBoolean("ToolBar.arrowKeysOnlyNavigation");
/* 136 */     this.hoverButtonGroupArc = UIManager.getInt("ToolBar.hoverButtonGroupArc");
/* 137 */     this.hoverButtonGroupBackground = UIManager.getColor("ToolBar.hoverButtonGroupBackground");
/*     */ 
/*     */     
/* 140 */     if (!UIManager.getBoolean("ToolBar.floatable")) {
/* 141 */       this.oldFloatable = Boolean.valueOf(this.toolBar.isFloatable());
/* 142 */       this.toolBar.setFloatable(false);
/*     */     } else {
/* 144 */       this.oldFloatable = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void uninstallDefaults() {
/* 149 */     super.uninstallDefaults();
/*     */     
/* 151 */     this.hoverButtonGroupBackground = null;
/*     */     
/* 153 */     if (this.oldFloatable != null) {
/* 154 */       this.toolBar.setFloatable(this.oldFloatable.booleanValue());
/* 155 */       this.oldFloatable = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected ContainerListener createToolBarContListener() {
/* 161 */     return new BasicToolBarUI.ToolBarContListener()
/*     */       {
/*     */         public void componentAdded(ContainerEvent e) {
/* 164 */           super.componentAdded(e);
/*     */           
/* 166 */           if (!FlatToolBarUI.this.focusableButtons) {
/* 167 */             FlatToolBarUI.this.setButtonFocusable(e.getChild(), false);
/*     */           }
/*     */         }
/*     */         
/*     */         public void componentRemoved(ContainerEvent e) {
/* 172 */           super.componentRemoved(e);
/*     */           
/* 174 */           if (!FlatToolBarUI.this.focusableButtons) {
/* 175 */             FlatToolBarUI.this.setButtonFocusable(e.getChild(), true);
/*     */           }
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   protected PropertyChangeListener createPropertyListener() {
/* 182 */     return FlatStylingSupport.createPropertyChangeListener(this.toolBar, this::installStyle, super.createPropertyListener());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installStyle() {
/*     */     try {
/* 188 */       applyStyle(FlatStylingSupport.getResolvedStyle(this.toolBar, "ToolBar"));
/* 189 */     } catch (RuntimeException ex) {
/* 190 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyStyle(Object style) {
/* 196 */     boolean oldFocusableButtons = this.focusableButtons;
/* 197 */     boolean oldArrowKeysOnlyNavigation = this.arrowKeysOnlyNavigation;
/*     */     
/* 199 */     this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
/*     */     
/* 201 */     if (this.focusableButtons != oldFocusableButtons)
/* 202 */       setButtonsFocusable(this.focusableButtons); 
/* 203 */     if (this.arrowKeysOnlyNavigation != oldArrowKeysOnlyNavigation || this.focusableButtons != oldFocusableButtons) {
/* 204 */       if (this.arrowKeysOnlyNavigation) {
/* 205 */         installFocusTraversalPolicy();
/*     */       } else {
/* 207 */         uninstallFocusTraversalPolicy();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   protected Object applyStyleProperty(String key, Object value) {
/* 213 */     return FlatStylingSupport.applyToAnnotatedObjectOrComponent(this, this.toolBar, key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
/* 219 */     return FlatStylingSupport.getAnnotatedStyleableInfos(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(JComponent c, String key) {
/* 225 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setButtonsFocusable(boolean focusable) {
/* 230 */     for (Component c : this.toolBar.getComponents())
/* 231 */       setButtonFocusable(c, focusable); 
/*     */   }
/*     */   
/*     */   private void setButtonFocusable(Component c, boolean focusable) {
/* 235 */     if (c instanceof AbstractButton && focusable != c.isFocusable()) {
/* 236 */       c.setFocusable(focusable);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void installFocusTraversalPolicy() {
/* 241 */     if (!this.arrowKeysOnlyNavigation || !this.focusableButtons || this.toolBar.getFocusTraversalPolicy() != null) {
/*     */       return;
/*     */     }
/* 244 */     this.focusTraversalPolicy = createFocusTraversalPolicy();
/* 245 */     if (this.focusTraversalPolicy != null) {
/* 246 */       this.toolBar.setFocusTraversalPolicy(this.focusTraversalPolicy);
/* 247 */       this.toolBar.setFocusTraversalPolicyProvider(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallFocusTraversalPolicy() {
/* 253 */     if (this.focusTraversalPolicy != null && this.toolBar.getFocusTraversalPolicy() == this.focusTraversalPolicy) {
/* 254 */       this.toolBar.setFocusTraversalPolicy((FocusTraversalPolicy)null);
/* 255 */       this.toolBar.setFocusTraversalPolicyProvider(false);
/*     */     } 
/* 257 */     this.focusTraversalPolicy = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected FocusTraversalPolicy createFocusTraversalPolicy() {
/* 262 */     return new FlatToolBarFocusTraversalPolicy();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void navigateFocusedComp(int direction) {
/* 271 */     int add, count = this.toolBar.getComponentCount();
/*     */     
/* 273 */     if (this.focusedCompIndex < 0 || this.focusedCompIndex >= count) {
/*     */       return;
/*     */     }
/*     */     
/* 277 */     switch (direction) { case 3: case 5:
/* 278 */         add = 1; break;
/* 279 */       case 1: case 7: add = -1; break;
/*     */       default:
/*     */         return; }
/*     */     
/* 283 */     int i = this.focusedCompIndex;
/*     */     while (true) {
/* 285 */       i += add;
/* 286 */       if (i < 0) {
/* 287 */         i = count - 1;
/* 288 */       } else if (i >= count) {
/* 289 */         i = 0;
/* 290 */       }  if (i == this.focusedCompIndex) {
/*     */         break;
/*     */       }
/* 293 */       Component c = this.toolBar.getComponentAtIndex(i);
/* 294 */       if (canBeFocusOwner(c)) {
/* 295 */         c.requestFocus();
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   protected void setBorderToRollover(Component c) {}
/*     */   
/*     */   private static boolean canBeFocusOwner(Component c) {
/* 303 */     if (c == null || !c.isEnabled() || !c.isVisible() || !c.isDisplayable() || !c.isFocusable()) {
/* 304 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 308 */     if (c instanceof JComboBox) {
/* 309 */       JComboBox<?> comboBox = (JComboBox)c;
/* 310 */       return comboBox.getUI().isFocusTraversable(comboBox);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 316 */     if (c instanceof JComponent) {
/* 317 */       InputMap inputMap = ((JComponent)c).getInputMap(0);
/* 318 */       while (inputMap != null && inputMap.size() == 0)
/* 319 */         inputMap = inputMap.getParent(); 
/* 320 */       if (inputMap == null) {
/* 321 */         return false;
/*     */       }
/*     */     } 
/* 324 */     return true;
/*     */   }
/*     */   protected void setBorderToNonRollover(Component c) {}
/*     */   protected void setBorderToNormal(Component c) {}
/*     */   protected void installRolloverBorders(JComponent c) {}
/*     */   protected void installNonRolloverBorders(JComponent c) {}
/*     */   
/*     */   protected void installNormalBorders(JComponent c) {}
/*     */   
/*     */   protected Border createRolloverBorder() {
/* 334 */     return null; } protected Border createNonRolloverBorder() {
/* 335 */     return null;
/*     */   }
/*     */   
/*     */   public void setOrientation(int orientation) {
/* 339 */     if (orientation != this.toolBar.getOrientation()) {
/*     */       
/* 341 */       Insets margin = this.toolBar.getMargin();
/* 342 */       Insets newMargin = new Insets(margin.left, margin.top, margin.right, margin.bottom);
/* 343 */       if (!newMargin.equals(margin)) {
/* 344 */         this.toolBar.setMargin(newMargin);
/*     */       }
/*     */     } 
/* 347 */     super.setOrientation(orientation);
/*     */   }
/*     */ 
/*     */   
/*     */   public void paint(Graphics g, JComponent c) {
/* 352 */     super.paint(g, c);
/*     */     
/* 354 */     paintButtonGroup(g);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintButtonGroup(Graphics g) {
/* 359 */     if (this.hoverButtonGroupBackground == null) {
/*     */       return;
/*     */     }
/*     */     
/* 363 */     ButtonGroup group = null;
/* 364 */     for (Component b : this.toolBar.getComponents()) {
/* 365 */       if (b instanceof AbstractButton && ((AbstractButton)b).getModel().isRollover()) {
/* 366 */         group = getButtonGroup((AbstractButton)b);
/* 367 */         if (group != null)
/*     */           break; 
/*     */       } 
/*     */     } 
/* 371 */     if (group == null) {
/*     */       return;
/*     */     }
/*     */     
/* 375 */     ArrayList<Rectangle> rects = new ArrayList<>();
/* 376 */     Enumeration<AbstractButton> e = group.getElements();
/* 377 */     while (e.hasMoreElements()) {
/* 378 */       AbstractButton gb = e.nextElement();
/* 379 */       if (gb.getParent() == this.toolBar) {
/* 380 */         rects.add(gb.getBounds());
/*     */       }
/*     */     } 
/*     */     
/* 384 */     boolean horizontal = (this.toolBar.getOrientation() == 0);
/* 385 */     rects.sort((r1, r2) -> horizontal ? (r1.x - r2.x) : (r1.y - r2.y));
/*     */     
/* 387 */     Object[] oldRenderingHints = FlatUIUtils.setRenderingHints(g);
/* 388 */     g.setColor(FlatUIUtils.deriveColor(this.hoverButtonGroupBackground, this.toolBar.getBackground()));
/*     */ 
/*     */     
/* 391 */     int maxSepWidth = UIScale.scale(10);
/* 392 */     Rectangle gr = null;
/* 393 */     for (Rectangle r : rects) {
/* 394 */       if (gr == null) {
/*     */         
/* 396 */         gr = r; continue;
/* 397 */       }  if (horizontal ? (gr.x + gr.width + maxSepWidth >= r.x) : (gr.y + gr.height + maxSepWidth >= r.y)) {
/*     */         
/* 399 */         gr = gr.union(r);
/*     */         continue;
/*     */       } 
/* 402 */       FlatUIUtils.paintComponentBackground((Graphics2D)g, gr.x, gr.y, gr.width, gr.height, 0.0F, UIScale.scale(this.hoverButtonGroupArc));
/* 403 */       gr = r;
/*     */     } 
/*     */     
/* 406 */     if (gr != null) {
/* 407 */       FlatUIUtils.paintComponentBackground((Graphics2D)g, gr.x, gr.y, gr.width, gr.height, 0.0F, UIScale.scale(this.hoverButtonGroupArc));
/*     */     }
/* 409 */     FlatUIUtils.resetRenderingHints(g, oldRenderingHints);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void repaintButtonGroup(AbstractButton b) {
/* 414 */     if (this.hoverButtonGroupBackground == null) {
/*     */       return;
/*     */     }
/* 417 */     ButtonGroup group = getButtonGroup(b);
/* 418 */     if (group == null) {
/*     */       return;
/*     */     }
/*     */     
/* 422 */     Rectangle gr = null;
/* 423 */     Enumeration<AbstractButton> e = group.getElements();
/* 424 */     while (e.hasMoreElements()) {
/* 425 */       AbstractButton gb = e.nextElement();
/* 426 */       Container parent = gb.getParent();
/* 427 */       if (parent == this.toolBar) {
/* 428 */         gr = (gr != null) ? gr.union(gb.getBounds()) : gb.getBounds();
/*     */       }
/*     */     } 
/*     */     
/* 432 */     if (gr != null)
/* 433 */       this.toolBar.repaint(gr); 
/*     */   }
/*     */   
/*     */   private ButtonGroup getButtonGroup(AbstractButton b) {
/* 437 */     ButtonModel model = b.getModel();
/* 438 */     return (model instanceof DefaultButtonModel) ? (
/* 439 */       (DefaultButtonModel)model).getGroup() : 
/* 440 */       null;
/*     */   }
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
/*     */   protected class FlatToolBarFocusTraversalPolicy
/*     */     extends LayoutFocusTraversalPolicy
/*     */   {
/*     */     public Component getComponentAfter(Container aContainer, Component aComponent) {
/* 463 */       if (!(aComponent instanceof AbstractButton)) {
/* 464 */         return super.getComponentAfter(aContainer, aComponent);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 469 */       Component c = aComponent;
/* 470 */       while ((c = super.getComponentAfter(aContainer, c)) != null) {
/* 471 */         if (!(c instanceof AbstractButton)) {
/* 472 */           return c;
/*     */         }
/*     */       } 
/*     */       
/* 476 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Component getComponentBefore(Container aContainer, Component aComponent) {
/* 483 */       if (!(aComponent instanceof AbstractButton)) {
/* 484 */         return super.getComponentBefore(aContainer, aComponent);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 489 */       Component c = aComponent;
/* 490 */       while ((c = super.getComponentBefore(aContainer, c)) != null) {
/* 491 */         if (!(c instanceof AbstractButton)) {
/* 492 */           return c;
/*     */         }
/*     */       } 
/*     */       
/* 496 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     public Component getFirstComponent(Container aContainer) {
/* 501 */       return getRecentComponent(aContainer, true);
/*     */     }
/*     */ 
/*     */     
/*     */     public Component getLastComponent(Container aContainer) {
/* 506 */       return getRecentComponent(aContainer, false);
/*     */     }
/*     */ 
/*     */     
/*     */     private Component getRecentComponent(Container aContainer, boolean first) {
/* 511 */       if (FlatToolBarUI.this.focusedCompIndex >= 0 && FlatToolBarUI.this.focusedCompIndex < FlatToolBarUI.this.toolBar.getComponentCount()) {
/* 512 */         return FlatToolBarUI.this.toolBar.getComponent(FlatToolBarUI.this.focusedCompIndex);
/*     */       }
/* 514 */       return first ? 
/* 515 */         super.getFirstComponent(aContainer) : 
/* 516 */         super.getLastComponent(aContainer);
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatToolBarUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */