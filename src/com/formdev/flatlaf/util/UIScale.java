/*     */ package com.formdev.flatlaf.util;
/*     */ 
/*     */ import com.formdev.flatlaf.FlatSystemProperties;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Toolkit;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.beans.PropertyChangeSupport;
/*     */ import java.lang.reflect.Method;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.DimensionUIResource;
/*     */ import javax.swing.plaf.FontUIResource;
/*     */ import javax.swing.plaf.InsetsUIResource;
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
/*     */ public class UIScale
/*     */ {
/*     */   private static final boolean DEBUG = false;
/*     */   private static PropertyChangeSupport changeSupport;
/*     */   private static Boolean jreHiDPI;
/*     */   
/*     */   public static void addPropertyChangeListener(PropertyChangeListener listener) {
/*  78 */     if (changeSupport == null)
/*  79 */       changeSupport = new PropertyChangeSupport(UIScale.class); 
/*  80 */     changeSupport.addPropertyChangeListener(listener);
/*     */   }
/*     */   
/*     */   public static void removePropertyChangeListener(PropertyChangeListener listener) {
/*  84 */     if (changeSupport == null)
/*     */       return; 
/*  86 */     changeSupport.removePropertyChangeListener(listener);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isSystemScalingEnabled() {
/*  97 */     if (jreHiDPI != null) {
/*  98 */       return jreHiDPI.booleanValue();
/*     */     }
/* 100 */     jreHiDPI = Boolean.valueOf(false);
/*     */     
/* 102 */     if (SystemInfo.isJava_9_orLater) {
/*     */       
/* 104 */       jreHiDPI = Boolean.valueOf(true);
/* 105 */     } else if (SystemInfo.isJetBrainsJVM) {
/*     */ 
/*     */       
/*     */       try {
/* 109 */         GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
/* 110 */         Class<?> sunGeClass = Class.forName("sun.java2d.SunGraphicsEnvironment");
/* 111 */         if (sunGeClass.isInstance(ge)) {
/* 112 */           Method m = sunGeClass.getDeclaredMethod("isUIScaleOn", new Class[0]);
/* 113 */           jreHiDPI = (Boolean)m.invoke(ge, new Object[0]);
/*     */         } 
/* 115 */       } catch (Throwable throwable) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 120 */     return jreHiDPI.booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double getSystemScaleFactor(Graphics2D g) {
/* 127 */     return isSystemScalingEnabled() ? getSystemScaleFactor(g.getDeviceConfiguration()) : 1.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double getSystemScaleFactor(GraphicsConfiguration gc) {
/* 134 */     return (isSystemScalingEnabled() && gc != null) ? gc.getDefaultTransform().getScaleX() : 1.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 139 */   private static float scaleFactor = 1.0F;
/*     */   private static boolean initialized;
/*     */   
/*     */   private static void initialize() {
/* 143 */     if (initialized)
/*     */       return; 
/* 145 */     initialized = true;
/*     */     
/* 147 */     if (!isUserScalingEnabled()) {
/*     */       return;
/*     */     }
/*     */     
/* 151 */     PropertyChangeListener listener = new PropertyChangeListener()
/*     */       {
/*     */         public void propertyChange(PropertyChangeEvent e) {
/* 154 */           switch (e.getPropertyName()) {
/*     */             
/*     */             case "lookAndFeel":
/* 157 */               if (e.getNewValue() instanceof javax.swing.LookAndFeel)
/* 158 */                 UIManager.getLookAndFeelDefaults().addPropertyChangeListener(this); 
/* 159 */               UIScale.updateScaleFactor();
/*     */               break;
/*     */             
/*     */             case "defaultFont":
/*     */             case "Label.font":
/* 164 */               UIScale.updateScaleFactor();
/*     */               break;
/*     */           } 
/*     */         }
/*     */       };
/* 169 */     UIManager.addPropertyChangeListener(listener);
/* 170 */     UIManager.getDefaults().addPropertyChangeListener(listener);
/* 171 */     UIManager.getLookAndFeelDefaults().addPropertyChangeListener(listener);
/*     */     
/* 173 */     updateScaleFactor();
/*     */   }
/*     */   
/*     */   private static void updateScaleFactor() {
/* 177 */     if (!isUserScalingEnabled()) {
/*     */       return;
/*     */     }
/*     */     
/* 181 */     float customScaleFactor = getCustomScaleFactor();
/* 182 */     if (customScaleFactor > 0.0F) {
/* 183 */       setUserScaleFactor(customScaleFactor, false);
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/* 191 */     Font font = UIManager.getFont("defaultFont");
/* 192 */     if (font == null) {
/* 193 */       font = UIManager.getFont("Label.font");
/*     */     }
/* 195 */     setUserScaleFactor(computeFontScaleFactor(font), true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float computeFontScaleFactor(Font font) {
/* 204 */     if (SystemInfo.isWindows)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 214 */       if (font instanceof javax.swing.plaf.UIResource) {
/* 215 */         Font uiFont = (Font)Toolkit.getDefaultToolkit().getDesktopProperty("win.messagebox.font");
/* 216 */         if (uiFont == null || uiFont.getSize() == font.getSize()) {
/* 217 */           if (isSystemScalingEnabled())
/*     */           {
/*     */ 
/*     */             
/* 221 */             return 1.0F;
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 229 */           Font winFont = (Font)Toolkit.getDefaultToolkit().getDesktopProperty("win.defaultGUI.font");
/* 230 */           return computeScaleFactor((winFont != null) ? winFont : font);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 243 */     return computeScaleFactor(font);
/*     */   }
/*     */ 
/*     */   
/*     */   private static float computeScaleFactor(Font font) {
/* 248 */     float fontSizeDivider = 12.0F;
/*     */     
/* 250 */     if (SystemInfo.isWindows) {
/*     */ 
/*     */ 
/*     */       
/* 254 */       if ("Tahoma".equals(font.getFamily()))
/* 255 */         fontSizeDivider = 11.0F; 
/* 256 */     } else if (SystemInfo.isMacOS) {
/*     */       
/* 258 */       fontSizeDivider = 13.0F;
/* 259 */     } else if (SystemInfo.isLinux) {
/*     */       
/* 261 */       fontSizeDivider = SystemInfo.isKDE ? 13.0F : 15.0F;
/*     */     } 
/*     */     
/* 264 */     return font.getSize() / fontSizeDivider;
/*     */   }
/*     */   
/*     */   private static boolean isUserScalingEnabled() {
/* 268 */     return FlatSystemProperties.getBoolean("flatlaf.uiScale.enabled", true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static FontUIResource applyCustomScaleFactor(FontUIResource font) {
/* 276 */     if (!isUserScalingEnabled()) {
/* 277 */       return font;
/*     */     }
/* 279 */     float scaleFactor = getCustomScaleFactor();
/* 280 */     if (scaleFactor <= 0.0F) {
/* 281 */       return font;
/*     */     }
/* 283 */     float fontScaleFactor = computeScaleFactor(font);
/* 284 */     if (scaleFactor == fontScaleFactor) {
/* 285 */       return font;
/*     */     }
/* 287 */     int newFontSize = Math.max(Math.round(font.getSize() / fontScaleFactor * scaleFactor), 1);
/* 288 */     return new FontUIResource(font.deriveFont(newFontSize));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static float getCustomScaleFactor() {
/* 295 */     return parseScaleFactor(System.getProperty("flatlaf.uiScale"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static float parseScaleFactor(String s) {
/* 302 */     if (s == null) {
/* 303 */       return -1.0F;
/*     */     }
/* 305 */     float units = 1.0F;
/* 306 */     if (s.endsWith("x")) {
/* 307 */       s = s.substring(0, s.length() - 1);
/* 308 */     } else if (s.endsWith("dpi")) {
/* 309 */       units = 96.0F;
/* 310 */       s = s.substring(0, s.length() - 3);
/* 311 */     } else if (s.endsWith("%")) {
/* 312 */       units = 100.0F;
/* 313 */       s = s.substring(0, s.length() - 1);
/*     */     } 
/*     */     
/*     */     try {
/* 317 */       float scale = Float.parseFloat(s);
/* 318 */       return (scale > 0.0F) ? (scale / units) : -1.0F;
/* 319 */     } catch (NumberFormatException ex) {
/* 320 */       return -1.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getUserScaleFactor() {
/* 328 */     initialize();
/* 329 */     return scaleFactor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void setUserScaleFactor(float scaleFactor, boolean normalize) {
/* 336 */     if (normalize) {
/* 337 */       if (scaleFactor < 1.0F) {
/*     */ 
/*     */         
/* 340 */         scaleFactor = FlatSystemProperties.getBoolean("flatlaf.uiScale.allowScaleDown", false) ? (Math.round(scaleFactor * 10.0F) / 10.0F) : 1.0F;
/* 341 */       } else if (scaleFactor > 1.0F) {
/* 342 */         scaleFactor = Math.round(scaleFactor * 4.0F) / 4.0F;
/*     */       } 
/*     */     }
/*     */     
/* 346 */     scaleFactor = Math.max(scaleFactor, 0.1F);
/*     */     
/* 348 */     float oldScaleFactor = UIScale.scaleFactor;
/* 349 */     UIScale.scaleFactor = scaleFactor;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 354 */     if (changeSupport != null) {
/* 355 */       changeSupport.firePropertyChange("userScaleFactor", Float.valueOf(oldScaleFactor), Float.valueOf(scaleFactor));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static float scale(float value) {
/* 362 */     initialize();
/* 363 */     return (scaleFactor == 1.0F) ? value : (value * scaleFactor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int scale(int value) {
/* 370 */     initialize();
/* 371 */     return (scaleFactor == 1.0F) ? value : Math.round(value * scaleFactor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int scale2(int value) {
/* 380 */     initialize();
/* 381 */     return (scaleFactor == 1.0F) ? value : (int)(value * scaleFactor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float unscale(float value) {
/* 388 */     initialize();
/* 389 */     return (scaleFactor == 1.0F) ? value : (value / scaleFactor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int unscale(int value) {
/* 396 */     initialize();
/* 397 */     return (scaleFactor == 1.0F) ? value : Math.round(value / scaleFactor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void scaleGraphics(Graphics2D g) {
/* 405 */     initialize();
/* 406 */     if (scaleFactor != 1.0F) {
/* 407 */       g.scale(scaleFactor, scaleFactor);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Dimension scale(Dimension dimension) {
/* 418 */     initialize();
/* 419 */     return (dimension == null || scaleFactor == 1.0F) ? 
/* 420 */       dimension : (
/* 421 */       (dimension instanceof javax.swing.plaf.UIResource) ? 
/* 422 */       new DimensionUIResource(scale(dimension.width), scale(dimension.height)) : 
/* 423 */       new Dimension(scale(dimension.width), scale(dimension.height)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Insets scale(Insets insets) {
/* 434 */     initialize();
/* 435 */     return (insets == null || scaleFactor == 1.0F) ? 
/* 436 */       insets : (
/* 437 */       (insets instanceof javax.swing.plaf.UIResource) ? 
/* 438 */       new InsetsUIResource(scale(insets.top), scale(insets.left), scale(insets.bottom), scale(insets.right)) : 
/* 439 */       new Insets(scale(insets.top), scale(insets.left), scale(insets.bottom), scale(insets.right)));
/*     */   }
/*     */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatla\\util\UIScale.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */