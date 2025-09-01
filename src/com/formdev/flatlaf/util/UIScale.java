package com.formdev.flatlaf.util;

import com.formdev.flatlaf.FlatSystemProperties;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Method;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.plaf.UIResource;

public class UIScale {
   private static final boolean DEBUG = false;
   private static PropertyChangeSupport changeSupport;
   private static Boolean jreHiDPI;
   private static float scaleFactor = 1.0F;
   private static boolean initialized;

   public static void addPropertyChangeListener(PropertyChangeListener listener) {
      if (changeSupport == null) {
         changeSupport = new PropertyChangeSupport(UIScale.class);
      }

      changeSupport.addPropertyChangeListener(listener);
   }

   public static void removePropertyChangeListener(PropertyChangeListener listener) {
      if (changeSupport != null) {
         changeSupport.removePropertyChangeListener(listener);
      }
   }

   public static boolean isSystemScalingEnabled() {
      if (jreHiDPI != null) {
         return jreHiDPI;
      } else {
         jreHiDPI = false;
         if (SystemInfo.isJava_9_orLater) {
            jreHiDPI = true;
         } else if (SystemInfo.isJetBrainsJVM) {
            try {
               GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
               Class<?> sunGeClass = Class.forName("sun.java2d.SunGraphicsEnvironment");
               if (sunGeClass.isInstance(ge)) {
                  Method m = sunGeClass.getDeclaredMethod("isUIScaleOn");
                  jreHiDPI = (Boolean)m.invoke(ge);
               }
            } catch (Throwable var3) {
            }
         }

         return jreHiDPI;
      }
   }

   public static double getSystemScaleFactor(Graphics2D g) {
      return isSystemScalingEnabled() ? getSystemScaleFactor(g.getDeviceConfiguration()) : 1.0D;
   }

   public static double getSystemScaleFactor(GraphicsConfiguration gc) {
      return isSystemScalingEnabled() && gc != null ? gc.getDefaultTransform().getScaleX() : 1.0D;
   }

   private static void initialize() {
      if (!initialized) {
         initialized = true;
         if (isUserScalingEnabled()) {
            PropertyChangeListener listener = new PropertyChangeListener() {
               public void propertyChange(PropertyChangeEvent e) {
                  String var2 = e.getPropertyName();
                  byte var3 = -1;
                  switch(var2.hashCode()) {
                  case -1595277186:
                     if (var2.equals("lookAndFeel")) {
                        var3 = 0;
                     }
                     break;
                  case -437367248:
                     if (var2.equals("defaultFont")) {
                        var3 = 1;
                     }
                     break;
                  case 298310441:
                     if (var2.equals("Label.font")) {
                        var3 = 2;
                     }
                  }

                  switch(var3) {
                  case 0:
                     if (e.getNewValue() instanceof LookAndFeel) {
                        UIManager.getLookAndFeelDefaults().addPropertyChangeListener(this);
                     }

                     UIScale.updateScaleFactor();
                     break;
                  case 1:
                  case 2:
                     UIScale.updateScaleFactor();
                  }

               }
            };
            UIManager.addPropertyChangeListener(listener);
            UIManager.getDefaults().addPropertyChangeListener(listener);
            UIManager.getLookAndFeelDefaults().addPropertyChangeListener(listener);
            updateScaleFactor();
         }
      }
   }

   private static void updateScaleFactor() {
      if (isUserScalingEnabled()) {
         float customScaleFactor = getCustomScaleFactor();
         if (customScaleFactor > 0.0F) {
            setUserScaleFactor(customScaleFactor, false);
         } else {
            Font font = UIManager.getFont("defaultFont");
            if (font == null) {
               font = UIManager.getFont("Label.font");
            }

            setUserScaleFactor(computeFontScaleFactor(font), true);
         }
      }
   }

   public static float computeFontScaleFactor(Font font) {
      if (SystemInfo.isWindows && font instanceof UIResource) {
         Font uiFont = (Font)Toolkit.getDefaultToolkit().getDesktopProperty("win.messagebox.font");
         if (uiFont == null || uiFont.getSize() == font.getSize()) {
            if (isSystemScalingEnabled()) {
               return 1.0F;
            } else {
               Font winFont = (Font)Toolkit.getDefaultToolkit().getDesktopProperty("win.defaultGUI.font");
               return computeScaleFactor(winFont != null ? winFont : font);
            }
         }
      }

      return computeScaleFactor(font);
   }

   private static float computeScaleFactor(Font font) {
      float fontSizeDivider = 12.0F;
      if (SystemInfo.isWindows) {
         if ("Tahoma".equals(font.getFamily())) {
            fontSizeDivider = 11.0F;
         }
      } else if (SystemInfo.isMacOS) {
         fontSizeDivider = 13.0F;
      } else if (SystemInfo.isLinux) {
         fontSizeDivider = SystemInfo.isKDE ? 13.0F : 15.0F;
      }

      return (float)font.getSize() / fontSizeDivider;
   }

   private static boolean isUserScalingEnabled() {
      return FlatSystemProperties.getBoolean("flatlaf.uiScale.enabled", true);
   }

   public static FontUIResource applyCustomScaleFactor(FontUIResource font) {
      if (!isUserScalingEnabled()) {
         return font;
      } else {
         float scaleFactor = getCustomScaleFactor();
         if (scaleFactor <= 0.0F) {
            return font;
         } else {
            float fontScaleFactor = computeScaleFactor(font);
            if (scaleFactor == fontScaleFactor) {
               return font;
            } else {
               int newFontSize = Math.max(Math.round((float)font.getSize() / fontScaleFactor * scaleFactor), 1);
               return new FontUIResource(font.deriveFont((float)newFontSize));
            }
         }
      }
   }

   private static float getCustomScaleFactor() {
      return parseScaleFactor(System.getProperty("flatlaf.uiScale"));
   }

   private static float parseScaleFactor(String s) {
      if (s == null) {
         return -1.0F;
      } else {
         float units = 1.0F;
         if (s.endsWith("x")) {
            s = s.substring(0, s.length() - 1);
         } else if (s.endsWith("dpi")) {
            units = 96.0F;
            s = s.substring(0, s.length() - 3);
         } else if (s.endsWith("%")) {
            units = 100.0F;
            s = s.substring(0, s.length() - 1);
         }

         try {
            float scale = Float.parseFloat(s);
            return scale > 0.0F ? scale / units : -1.0F;
         } catch (NumberFormatException var3) {
            return -1.0F;
         }
      }
   }

   public static float getUserScaleFactor() {
      initialize();
      return scaleFactor;
   }

   private static void setUserScaleFactor(float scaleFactor, boolean normalize) {
      if (normalize) {
         if (scaleFactor < 1.0F) {
            scaleFactor = FlatSystemProperties.getBoolean("flatlaf.uiScale.allowScaleDown", false) ? (float)Math.round(scaleFactor * 10.0F) / 10.0F : 1.0F;
         } else if (scaleFactor > 1.0F) {
            scaleFactor = (float)Math.round(scaleFactor * 4.0F) / 4.0F;
         }
      }

      scaleFactor = Math.max(scaleFactor, 0.1F);
      float oldScaleFactor = UIScale.scaleFactor;
      UIScale.scaleFactor = scaleFactor;
      if (changeSupport != null) {
         changeSupport.firePropertyChange("userScaleFactor", oldScaleFactor, scaleFactor);
      }

   }

   public static float scale(float value) {
      initialize();
      return scaleFactor == 1.0F ? value : value * scaleFactor;
   }

   public static int scale(int value) {
      initialize();
      return scaleFactor == 1.0F ? value : Math.round((float)value * scaleFactor);
   }

   public static int scale2(int value) {
      initialize();
      return scaleFactor == 1.0F ? value : (int)((float)value * scaleFactor);
   }

   public static float unscale(float value) {
      initialize();
      return scaleFactor == 1.0F ? value : value / scaleFactor;
   }

   public static int unscale(int value) {
      initialize();
      return scaleFactor == 1.0F ? value : Math.round((float)value / scaleFactor);
   }

   public static void scaleGraphics(Graphics2D g) {
      initialize();
      if (scaleFactor != 1.0F) {
         g.scale((double)scaleFactor, (double)scaleFactor);
      }

   }

   public static Dimension scale(Dimension dimension) {
      initialize();
      return (Dimension)(dimension != null && scaleFactor != 1.0F ? (dimension instanceof UIResource ? new DimensionUIResource(scale(dimension.width), scale(dimension.height)) : new Dimension(scale(dimension.width), scale(dimension.height))) : dimension);
   }

   public static Insets scale(Insets insets) {
      initialize();
      return (Insets)(insets != null && scaleFactor != 1.0F ? (insets instanceof UIResource ? new InsetsUIResource(scale(insets.top), scale(insets.left), scale(insets.bottom), scale(insets.right)) : new Insets(scale(insets.top), scale(insets.left), scale(insets.bottom), scale(insets.right))) : insets);
   }
}
