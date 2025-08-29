/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.icons.FlatCheckBoxIcon;
/*     */ import com.formdev.flatlaf.util.LoggingFacade;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import javax.swing.AbstractButton;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.LookAndFeel;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicButtonListener;
/*     */ import javax.swing.plaf.basic.BasicRadioButtonUI;
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
/*     */ public class FlatRadioButtonUI
/*     */   extends BasicRadioButtonUI
/*     */   implements FlatStylingSupport.StyleableUI
/*     */ {
/*     */   protected int iconTextGap;
/*     */   @Styleable
/*     */   protected Color disabledText;
/*     */   private Color defaultBackground;
/*     */   private final boolean shared;
/*     */   private boolean iconShared = true;
/*     */   private boolean defaults_initialized = false;
/*     */   private Map<String, Object> oldStyleValues;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/*  86 */     return (FlatUIUtils.canUseSharedUI(c) && !FlatUIUtils.needsLightAWTPeer(c)) ? 
/*  87 */       FlatUIUtils.createSharedUI(FlatRadioButtonUI.class, () -> new FlatRadioButtonUI(true)) : 
/*  88 */       new FlatRadioButtonUI(false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected FlatRadioButtonUI(boolean shared) {
/*  93 */     this.shared = shared;
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/*  98 */     if (FlatUIUtils.needsLightAWTPeer(c)) {
/*  99 */       FlatUIUtils.runWithLightAWTPeerUIDefaults(() -> installUIImpl(c));
/*     */     } else {
/* 101 */       installUIImpl(c);
/*     */     } 
/*     */   }
/*     */   private void installUIImpl(JComponent c) {
/* 105 */     super.installUI(c);
/*     */     
/* 107 */     if (FlatUIUtils.isAWTPeer(c)) {
/* 108 */       AWTPeerMouseExitedFix.install(c);
/*     */     }
/* 110 */     installStyle((AbstractButton)c);
/*     */   }
/*     */ 
/*     */   
/*     */   public void uninstallUI(JComponent c) {
/* 115 */     super.uninstallUI(c);
/*     */     
/* 117 */     if (FlatUIUtils.isAWTPeer(c)) {
/* 118 */       AWTPeerMouseExitedFix.uninstall(c);
/*     */     }
/*     */   }
/*     */   
/*     */   public void installDefaults(AbstractButton b) {
/* 123 */     super.installDefaults(b);
/*     */     
/* 125 */     if (!this.defaults_initialized) {
/* 126 */       String prefix = getPropertyPrefix();
/*     */       
/* 128 */       this.iconTextGap = FlatUIUtils.getUIInt(prefix + "iconTextGap", 4);
/* 129 */       this.disabledText = UIManager.getColor(prefix + "disabledText");
/*     */       
/* 131 */       this.defaultBackground = UIManager.getColor(prefix + "background");
/*     */       
/* 133 */       this.iconShared = true;
/* 134 */       this.defaults_initialized = true;
/*     */     } 
/*     */     
/* 137 */     LookAndFeel.installProperty(b, "opaque", Boolean.valueOf(false));
/* 138 */     LookAndFeel.installProperty(b, "iconTextGap", Integer.valueOf(UIScale.scale(this.iconTextGap)));
/*     */     
/* 140 */     MigLayoutVisualPadding.install(b, null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallDefaults(AbstractButton b) {
/* 145 */     super.uninstallDefaults(b);
/*     */     
/* 147 */     this.oldStyleValues = null;
/*     */     
/* 149 */     MigLayoutVisualPadding.uninstall(b);
/* 150 */     this.defaults_initialized = false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BasicButtonListener createButtonListener(AbstractButton b) {
/* 155 */     return new FlatRadioButtonListener(b);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void propertyChange(AbstractButton b, PropertyChangeEvent e) {
/* 160 */     switch (e.getPropertyName()) {
/*     */       case "FlatLaf.style":
/*     */       case "FlatLaf.styleClass":
/* 163 */         if (this.shared && FlatStylingSupport.hasStyleProperty(b)) {
/*     */ 
/*     */           
/* 166 */           b.updateUI();
/*     */         } else {
/* 168 */           installStyle(b);
/* 169 */         }  b.revalidate();
/* 170 */         b.repaint();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installStyle(AbstractButton b) {
/*     */     try {
/* 178 */       applyStyle(b, FlatStylingSupport.getResolvedStyle(b, getStyleType()));
/* 179 */     } catch (RuntimeException ex) {
/* 180 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   String getStyleType() {
/* 186 */     return "RadioButton";
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyStyle(AbstractButton b, Object style) {
/* 191 */     this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, (key, value) -> applyStyleProperty(b, key, value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object applyStyleProperty(AbstractButton b, String key, Object value) {
/* 198 */     if (key.startsWith("icon.")) {
/* 199 */       if (!(this.icon instanceof FlatCheckBoxIcon)) {
/* 200 */         return new FlatStylingSupport.UnknownStyleException(key);
/*     */       }
/* 202 */       if (this.iconShared) {
/* 203 */         this.icon = FlatStylingSupport.cloneIcon(this.icon);
/* 204 */         this.iconShared = false;
/*     */       } 
/*     */       
/* 207 */       key = key.substring("icon.".length());
/* 208 */       return ((FlatCheckBoxIcon)this.icon).applyStyleProperty(key, value);
/*     */     } 
/*     */     
/* 211 */     return FlatStylingSupport.applyToAnnotatedObjectOrComponent(this, b, key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
/* 217 */     Map<String, Class<?>> infos = FlatStylingSupport.getAnnotatedStyleableInfos(this);
/* 218 */     if (this.icon instanceof FlatCheckBoxIcon)
/* 219 */       for (Map.Entry<String, Class<?>> e : (Iterable<Map.Entry<String, Class<?>>>)((FlatCheckBoxIcon)this.icon).getStyleableInfos().entrySet()) {
/* 220 */         infos.put("icon.".concat(e.getKey()), e.getValue());
/*     */       } 
/* 222 */     return infos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(JComponent c, String key) {
/* 229 */     if (key.startsWith("icon.")) {
/* 230 */       return (this.icon instanceof FlatCheckBoxIcon) ? (
/* 231 */         (FlatCheckBoxIcon)this.icon).getStyleableValue(key.substring("icon.".length())) : 
/* 232 */         null;
/*     */     }
/*     */     
/* 235 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
/*     */   }
/*     */   
/* 238 */   private static final Insets tempInsets = new Insets(0, 0, 0, 0);
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize(JComponent c) {
/* 242 */     Dimension size = super.getPreferredSize(c);
/* 243 */     if (size == null) {
/* 244 */       return null;
/*     */     }
/*     */     
/* 247 */     int focusWidth = getIconFocusWidth(c);
/* 248 */     if (focusWidth > 0) {
/*     */ 
/*     */ 
/*     */       
/* 252 */       Insets insets = c.getInsets(tempInsets);
/* 253 */       size.width += Math.max(focusWidth - insets.left, 0) + Math.max(focusWidth - insets.right, 0);
/* 254 */       size.height += Math.max(focusWidth - insets.top, 0) + Math.max(focusWidth - insets.bottom, 0);
/*     */     } 
/*     */     
/* 257 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paint(Graphics g, JComponent c) {
/* 266 */     if (!c.isOpaque() && ((AbstractButton)c)
/* 267 */       .isContentAreaFilled() && 
/* 268 */       !Objects.equals(c.getBackground(), getDefaultBackground(c))) {
/*     */       
/* 270 */       g.setColor(c.getBackground());
/* 271 */       g.fillRect(0, 0, c.getWidth(), c.getHeight());
/*     */     } 
/*     */ 
/*     */     
/* 275 */     int focusWidth = getIconFocusWidth(c);
/* 276 */     if (focusWidth > 0) {
/* 277 */       boolean ltr = c.getComponentOrientation().isLeftToRight();
/* 278 */       Insets insets = c.getInsets(tempInsets);
/* 279 */       int leftOrRightInset = ltr ? insets.left : insets.right;
/* 280 */       if (focusWidth > leftOrRightInset) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 285 */         int offset = focusWidth - leftOrRightInset;
/* 286 */         if (!ltr) {
/* 287 */           offset = -offset;
/*     */         }
/*     */         
/* 290 */         g.translate(offset, 0);
/* 291 */         super.paint(g, c);
/* 292 */         g.translate(-offset, 0);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 297 */     super.paint(FlatLabelUI.createGraphicsHTMLTextYCorrection(g, c), c);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintText(Graphics g, AbstractButton b, Rectangle textRect, String text) {
/* 302 */     FlatButtonUI.paintText(g, b, textRect, text, b.isEnabled() ? b.getForeground() : this.disabledText);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Color getDefaultBackground(JComponent c) {
/* 311 */     Container parent = c.getParent();
/* 312 */     return (parent instanceof javax.swing.CellRendererPane && parent.getParent() != null) ? 
/* 313 */       parent.getParent().getBackground() : 
/* 314 */       this.defaultBackground;
/*     */   }
/*     */   
/*     */   private int getIconFocusWidth(JComponent c) {
/* 318 */     AbstractButton b = (AbstractButton)c;
/* 319 */     Icon icon = b.getIcon();
/* 320 */     if (icon == null) {
/* 321 */       icon = getDefaultIcon();
/*     */     }
/* 323 */     return (icon instanceof FlatCheckBoxIcon) ? 
/* 324 */       Math.round(UIScale.scale(((FlatCheckBoxIcon)icon).getFocusWidth())) : 
/* 325 */       0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected class FlatRadioButtonListener
/*     */     extends BasicButtonListener
/*     */   {
/*     */     private final AbstractButton b;
/*     */ 
/*     */     
/*     */     protected FlatRadioButtonListener(AbstractButton b) {
/* 337 */       super(b);
/* 338 */       this.b = b;
/*     */     }
/*     */ 
/*     */     
/*     */     public void propertyChange(PropertyChangeEvent e) {
/* 343 */       super.propertyChange(e);
/* 344 */       FlatRadioButtonUI.this.propertyChange(this.b, e);
/*     */     }
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
/*     */   private static class AWTPeerMouseExitedFix
/*     */     extends MouseAdapter
/*     */     implements PropertyChangeListener
/*     */   {
/*     */     private final JComponent button;
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
/*     */     static void install(JComponent button) {
/* 374 */       AWTPeerMouseExitedFix l = new AWTPeerMouseExitedFix(button);
/* 375 */       button.addPropertyChangeListener("ancestor", l);
/* 376 */       Container parent = button.getParent();
/* 377 */       if (parent != null)
/* 378 */         parent.addMouseListener(l); 
/*     */     }
/*     */     
/*     */     static void uninstall(JComponent button) {
/* 382 */       for (PropertyChangeListener l : button.getPropertyChangeListeners("ancestor")) {
/* 383 */         if (l instanceof AWTPeerMouseExitedFix) {
/* 384 */           button.removePropertyChangeListener("ancestor", l);
/* 385 */           Container parent = button.getParent();
/* 386 */           if (parent != null)
/* 387 */             parent.removeMouseListener((AWTPeerMouseExitedFix)l); 
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     AWTPeerMouseExitedFix(JComponent button) {
/* 394 */       this.button = button;
/*     */     }
/*     */ 
/*     */     
/*     */     public void propertyChange(PropertyChangeEvent e) {
/* 399 */       if (e.getOldValue() instanceof Component)
/* 400 */         ((Component)e.getOldValue()).removeMouseListener(this); 
/* 401 */       if (e.getNewValue() instanceof Component) {
/* 402 */         ((Component)e.getNewValue()).removeMouseListener(this);
/* 403 */         ((Component)e.getNewValue()).addMouseListener(this);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseExited(MouseEvent e) {
/* 409 */       this.button.dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(), e, this.button));
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatRadioButtonUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */