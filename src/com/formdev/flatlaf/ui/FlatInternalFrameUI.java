/*     */ package com.formdev.flatlaf.ui;
/*     */ 
/*     */ import com.formdev.flatlaf.util.LoggingFacade;
/*     */ import com.formdev.flatlaf.util.UIScale;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JInternalFrame;
/*     */ import javax.swing.LookAndFeel;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.event.MouseInputAdapter;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicInternalFrameUI;
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
/*     */ public class FlatInternalFrameUI
/*     */   extends BasicInternalFrameUI
/*     */   implements FlatStylingSupport.StyleableUI
/*     */ {
/*     */   protected FlatWindowResizer windowResizer;
/*     */   private Map<String, Object> oldStyleValues;
/*     */   private AtomicBoolean borderShared;
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/* 104 */     return new FlatInternalFrameUI((JInternalFrame)c);
/*     */   }
/*     */   
/*     */   public FlatInternalFrameUI(JInternalFrame b) {
/* 108 */     super(b);
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/* 113 */     super.installUI(c);
/*     */     
/* 115 */     LookAndFeel.installProperty(this.frame, "opaque", Boolean.valueOf(false));
/*     */     
/* 117 */     this.windowResizer = createWindowResizer();
/*     */     
/* 119 */     installStyle();
/*     */   }
/*     */ 
/*     */   
/*     */   public void uninstallUI(JComponent c) {
/* 124 */     super.uninstallUI(c);
/*     */     
/* 126 */     if (this.windowResizer != null) {
/* 127 */       this.windowResizer.uninstall();
/* 128 */       this.windowResizer = null;
/*     */     } 
/*     */     
/* 131 */     this.oldStyleValues = null;
/* 132 */     this.borderShared = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected JComponent createNorthPane(JInternalFrame w) {
/* 137 */     return new FlatInternalFrameTitlePane(w);
/*     */   }
/*     */   
/*     */   protected FlatWindowResizer createWindowResizer() {
/* 141 */     return new FlatWindowResizer.InternalFrameResizer(this.frame, this::getDesktopManager);
/*     */   }
/*     */ 
/*     */   
/*     */   protected MouseInputAdapter createBorderListener(JInternalFrame w) {
/* 146 */     return new FlatBorderListener();
/*     */   }
/*     */ 
/*     */   
/*     */   protected PropertyChangeListener createPropertyChangeListener() {
/* 151 */     return FlatStylingSupport.createPropertyChangeListener(this.frame, this::installStyle, super
/* 152 */         .createPropertyChangeListener());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void installStyle() {
/*     */     try {
/* 158 */       applyStyle(FlatStylingSupport.getResolvedStyle(this.frame, "InternalFrame"));
/* 159 */     } catch (RuntimeException ex) {
/* 160 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyStyle(Object style) {
/* 166 */     this.oldStyleValues = FlatStylingSupport.parseAndApply(this.oldStyleValues, style, this::applyStyleProperty);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object applyStyleProperty(String key, Object value) {
/* 171 */     if (this.borderShared == null)
/* 172 */       this.borderShared = new AtomicBoolean(true); 
/* 173 */     return FlatStylingSupport.applyToAnnotatedObjectOrBorder(this, key, value, this.frame, this.borderShared);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Class<?>> getStyleableInfos(JComponent c) {
/* 179 */     return FlatStylingSupport.getAnnotatedStyleableInfos(this, this.frame.getBorder());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getStyleableValue(JComponent c, String key) {
/* 185 */     return FlatStylingSupport.getAnnotatedStyleableValue(this, this.frame.getBorder(), key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update(Graphics g, JComponent c) {
/* 196 */     if (!c.isOpaque() && !FlatUIUtils.hasOpaqueBeenExplicitlySet(c)) {
/* 197 */       Insets insets = c.getInsets();
/*     */       
/* 199 */       g.setColor(c.getBackground());
/* 200 */       g.fillRect(insets.left, insets.top, c
/* 201 */           .getWidth() - insets.left - insets.right, c
/* 202 */           .getHeight() - insets.top - insets.bottom);
/*     */     } 
/*     */     
/* 205 */     super.update(g, c);
/*     */   }
/*     */ 
/*     */   
/*     */   public static class FlatInternalFrameBorder
/*     */     extends FlatEmptyBorder
/*     */     implements FlatStylingSupport.StyleableBorder
/*     */   {
/*     */     @Styleable
/* 214 */     protected Color activeBorderColor = UIManager.getColor("InternalFrame.activeBorderColor"); @Styleable
/* 215 */     protected Color inactiveBorderColor = UIManager.getColor("InternalFrame.inactiveBorderColor"); @Styleable
/* 216 */     protected int borderLineWidth = FlatUIUtils.getUIInt("InternalFrame.borderLineWidth", 1); @Styleable
/* 217 */     protected boolean dropShadowPainted = UIManager.getBoolean("InternalFrame.dropShadowPainted");
/*     */     
/* 219 */     private final FlatDropShadowBorder activeDropShadowBorder = new FlatDropShadowBorder(
/* 220 */         UIManager.getColor("InternalFrame.activeDropShadowColor"), 
/* 221 */         UIManager.getInsets("InternalFrame.activeDropShadowInsets"), 
/* 222 */         FlatUIUtils.getUIFloat("InternalFrame.activeDropShadowOpacity", 0.5F));
/* 223 */     private final FlatDropShadowBorder inactiveDropShadowBorder = new FlatDropShadowBorder(
/* 224 */         UIManager.getColor("InternalFrame.inactiveDropShadowColor"), 
/* 225 */         UIManager.getInsets("InternalFrame.inactiveDropShadowInsets"), 
/* 226 */         FlatUIUtils.getUIFloat("InternalFrame.inactiveDropShadowOpacity", 0.5F));
/*     */     
/*     */     public FlatInternalFrameBorder() {
/* 229 */       super(UIManager.getInsets("InternalFrame.borderMargins"));
/*     */     }
/*     */ 
/*     */     
/*     */     public Object applyStyleProperty(String key, Object value) {
/* 234 */       switch (key) { case "borderMargins":
/* 235 */           return applyStyleProperty((Insets)value);
/*     */         case "activeDropShadowColor":
/* 237 */           return this.activeDropShadowBorder.applyStyleProperty("shadowColor", value);
/* 238 */         case "activeDropShadowInsets": return this.activeDropShadowBorder.applyStyleProperty("shadowInsets", value);
/* 239 */         case "activeDropShadowOpacity": return this.activeDropShadowBorder.applyStyleProperty("shadowOpacity", value);
/* 240 */         case "inactiveDropShadowColor": return this.inactiveDropShadowBorder.applyStyleProperty("shadowColor", value);
/* 241 */         case "inactiveDropShadowInsets": return this.inactiveDropShadowBorder.applyStyleProperty("shadowInsets", value);
/* 242 */         case "inactiveDropShadowOpacity": return this.inactiveDropShadowBorder.applyStyleProperty("shadowOpacity", value); }
/*     */ 
/*     */       
/* 245 */       return FlatStylingSupport.applyToAnnotatedObject(this, key, value);
/*     */     }
/*     */ 
/*     */     
/*     */     public Map<String, Class<?>> getStyleableInfos() {
/* 250 */       Map<String, Class<?>> infos = new FlatStylingSupport.StyleableInfosMap<>();
/* 251 */       FlatStylingSupport.collectAnnotatedStyleableInfos(this, infos);
/* 252 */       infos.put("borderMargins", Insets.class);
/* 253 */       infos.put("activeDropShadowColor", Color.class);
/* 254 */       infos.put("activeDropShadowInsets", Insets.class);
/* 255 */       infos.put("activeDropShadowOpacity", float.class);
/* 256 */       infos.put("inactiveDropShadowColor", Color.class);
/* 257 */       infos.put("inactiveDropShadowInsets", Insets.class);
/* 258 */       infos.put("inactiveDropShadowOpacity", float.class);
/* 259 */       return infos;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Object getStyleableValue(String key) {
/* 265 */       switch (key) { case "borderMargins":
/* 266 */           return getStyleableValue();
/*     */         case "activeDropShadowColor":
/* 268 */           return this.activeDropShadowBorder.getStyleableValue("shadowColor");
/* 269 */         case "activeDropShadowInsets": return this.activeDropShadowBorder.getStyleableValue("shadowInsets");
/* 270 */         case "activeDropShadowOpacity": return this.activeDropShadowBorder.getStyleableValue("shadowOpacity");
/* 271 */         case "inactiveDropShadowColor": return this.inactiveDropShadowBorder.getStyleableValue("shadowColor");
/* 272 */         case "inactiveDropShadowInsets": return this.inactiveDropShadowBorder.getStyleableValue("shadowInsets");
/* 273 */         case "inactiveDropShadowOpacity": return this.inactiveDropShadowBorder.getStyleableValue("shadowOpacity"); }
/*     */ 
/*     */       
/* 276 */       return FlatStylingSupport.getAnnotatedStyleableValue(this, key);
/*     */     }
/*     */ 
/*     */     
/*     */     public Insets getBorderInsets(Component c, Insets insets) {
/* 281 */       if (c instanceof JInternalFrame && ((JInternalFrame)c).isMaximum()) {
/* 282 */         insets.left = UIScale.scale(Math.min(this.borderLineWidth, this.left));
/* 283 */         insets.top = UIScale.scale(Math.min(this.borderLineWidth, this.top));
/* 284 */         insets.right = UIScale.scale(Math.min(this.borderLineWidth, this.right));
/* 285 */         insets.bottom = UIScale.scale(Math.min(this.borderLineWidth, this.bottom));
/* 286 */         return insets;
/*     */       } 
/*     */       
/* 289 */       return super.getBorderInsets(c, insets);
/*     */     }
/*     */ 
/*     */     
/*     */     public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
/* 294 */       JInternalFrame f = (JInternalFrame)c;
/*     */       
/* 296 */       Insets insets = getBorderInsets(c);
/* 297 */       float lineWidth = UIScale.scale(this.borderLineWidth);
/*     */       
/* 299 */       float rx = (x + insets.left) - lineWidth;
/* 300 */       float ry = (y + insets.top) - lineWidth;
/* 301 */       float rwidth = (width - insets.left - insets.right) + lineWidth * 2.0F;
/* 302 */       float rheight = (height - insets.top - insets.bottom) + lineWidth * 2.0F;
/*     */       
/* 304 */       Graphics2D g2 = (Graphics2D)g.create();
/*     */       try {
/* 306 */         FlatUIUtils.setRenderingHints(g2);
/* 307 */         g2.setColor(f.isSelected() ? this.activeBorderColor : this.inactiveBorderColor);
/*     */ 
/*     */         
/* 310 */         if (this.dropShadowPainted) {
/*     */           
/* 312 */           FlatDropShadowBorder dropShadowBorder = f.isSelected() ? this.activeDropShadowBorder : this.inactiveDropShadowBorder;
/*     */           
/* 314 */           Insets dropShadowInsets = dropShadowBorder.getBorderInsets();
/* 315 */           dropShadowBorder.paintBorder(c, g2, (int)rx - dropShadowInsets.left, (int)ry - dropShadowInsets.top, (int)rwidth + dropShadowInsets.left + dropShadowInsets.right, (int)rheight + dropShadowInsets.top + dropShadowInsets.bottom);
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 323 */         g2.fill(FlatUIUtils.createRectangle(rx, ry, rwidth, rheight, lineWidth));
/*     */       } finally {
/* 325 */         g2.dispose();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class FlatBorderListener
/*     */     extends BasicInternalFrameUI.BorderListener
/*     */   {
/*     */     public void mouseClicked(MouseEvent e) {
/* 338 */       if (e.getClickCount() == 2 && !FlatInternalFrameUI.this.frame.isIcon() && e
/* 339 */         .getSource() instanceof FlatInternalFrameTitlePane) {
/*     */         
/* 341 */         Rectangle iconBounds = ((FlatInternalFrameTitlePane)e.getSource()).getFrameIconBounds();
/* 342 */         if (iconBounds != null && iconBounds.contains(e.getX(), e.getY())) {
/* 343 */           if (FlatInternalFrameUI.this.frame.isClosable()) {
/* 344 */             FlatInternalFrameUI.this.frame.doDefaultCloseAction();
/*     */           }
/*     */           return;
/*     */         } 
/*     */       } 
/* 349 */       super.mouseClicked(e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\ui\FlatInternalFrameUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */