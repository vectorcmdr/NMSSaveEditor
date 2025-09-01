package com.formdev.flatlaf;

import com.formdev.flatlaf.ui.FlatNativeWindowBorder;
import com.formdev.flatlaf.ui.FlatPopupFactory;
import com.formdev.flatlaf.ui.FlatRootPaneUI;
import com.formdev.flatlaf.ui.FlatStylingSupport;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.FontUtils;
import com.formdev.flatlaf.util.GrayFilter;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.MultiResolutionImageSupport;
import com.formdev.flatlaf.util.StringUtils;
import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.ServiceLoader;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.LookAndFeel;
import javax.swing.PopupFactory;
import javax.swing.RootPaneContainer;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIDefaults.ActiveValue;
import javax.swing.UIDefaults.LazyValue;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.IconUIResource;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicLookAndFeel;
import javax.swing.text.StyleContext;
import javax.swing.text.html.HTMLEditorKit;

public abstract class FlatLaf extends BasicLookAndFeel {
   private static final String DESKTOPFONTHINTS = "awt.font.desktophints";
   private static List<Object> customDefaultsSources;
   private static Map<String, String> globalExtraDefaults;
   private Map<String, String> extraDefaults;
   private static Function<String, Color> systemColorGetter;
   private String desktopPropertyName;
   private String desktopPropertyName2;
   private PropertyChangeListener desktopPropertyListener;
   private static boolean aquaLoaded;
   private static boolean updateUIPending;
   private PopupFactory oldPopupFactory;
   private MnemonicHandler mnemonicHandler;
   private boolean subMenuUsabilityHelperInstalled;
   private Consumer<UIDefaults> postInitialization;
   private List<Function<Object, Object>> uiDefaultsGetters;
   private static String preferredFontFamily;
   private static String preferredLightFontFamily;
   private static String preferredSemiboldFontFamily;
   private static String preferredMonospacedFontFamily;
   public static final Object NULL_VALUE = new Object();
   private static boolean getUIMethodInitialized;
   private static MethodHandle getUIMethod;

   public static boolean setup(LookAndFeel newLookAndFeel) {
      try {
         UIManager.setLookAndFeel(newLookAndFeel);
         return true;
      } catch (Exception var2) {
         LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to setup look and feel '" + newLookAndFeel.getClass().getName() + "'.", var2);
         return false;
      }
   }

   /** @deprecated */
   @Deprecated
   public static boolean install(LookAndFeel newLookAndFeel) {
      return setup(newLookAndFeel);
   }

   public static void installLafInfo(String lafName, Class<? extends LookAndFeel> lafClass) {
      UIManager.installLookAndFeel(new LookAndFeelInfo(lafName, lafClass.getName()));
   }

   public String getID() {
      return "FlatLaf - " + this.getName();
   }

   public abstract boolean isDark();

   public static boolean isLafDark() {
      LookAndFeel lookAndFeel = UIManager.getLookAndFeel();
      return lookAndFeel instanceof FlatLaf && ((FlatLaf)lookAndFeel).isDark();
   }

   public boolean getSupportsWindowDecorations() {
      if (!SystemInfo.isProjector && !SystemInfo.isWebswing && !SystemInfo.isWinPE) {
         if (SystemInfo.isWindows_10_orLater && FlatNativeWindowBorder.isSupported()) {
            return false;
         } else {
            return SystemInfo.isWindows_10_orLater || SystemInfo.isLinux;
         }
      } else {
         return false;
      }
   }

   public boolean isNativeLookAndFeel() {
      return false;
   }

   public boolean isSupportedLookAndFeel() {
      return true;
   }

   public Icon getDisabledIcon(JComponent component, Icon icon) {
      if (icon instanceof FlatLaf.DisabledIconProvider) {
         Icon disabledIcon = ((FlatLaf.DisabledIconProvider)icon).getDisabledIcon();
         return (Icon)(!(disabledIcon instanceof UIResource) ? new IconUIResource(disabledIcon) : disabledIcon);
      } else if (icon instanceof ImageIcon) {
         Object grayFilter = UIManager.get("Component.grayFilter");
         ImageFilter filter = grayFilter instanceof ImageFilter ? (ImageFilter)grayFilter : GrayFilter.createDisabledIconFilter(this.isDark());
         Function<Image, Image> mapper = (img) -> {
            ImageProducer producer = new FilteredImageSource(img.getSource(), filter);
            return Toolkit.getDefaultToolkit().createImage(producer);
         };
         Image image = ((ImageIcon)icon).getImage();
         return new FlatLaf.ImageIconUIResource(MultiResolutionImageSupport.map(image, mapper));
      } else {
         return null;
      }
   }

   public void initialize() {
      if (UIManager.getLookAndFeel() == this) {
         if (SystemInfo.isMacOS) {
            this.initializeAqua();
         }

         super.initialize();
         this.oldPopupFactory = PopupFactory.getSharedInstance();
         PopupFactory.setSharedInstance(new FlatPopupFactory());
         this.mnemonicHandler = new MnemonicHandler();
         this.mnemonicHandler.install();
         this.subMenuUsabilityHelperInstalled = SubMenuUsabilityHelper.install();
         if (SystemInfo.isWindows) {
            this.desktopPropertyName = "win.messagebox.font";
         } else if (SystemInfo.isLinux) {
            this.desktopPropertyName = "gnome.Gtk/FontName";
            this.desktopPropertyName2 = "gnome.Xft/DPI";
         }

         if (this.desktopPropertyName != null) {
            this.desktopPropertyListener = (e) -> {
               if (FlatSystemProperties.getBoolean("flatlaf.updateUIOnSystemFontChange", true)) {
                  String propertyName = e.getPropertyName();
                  if (!this.desktopPropertyName.equals(propertyName) && !propertyName.equals(this.desktopPropertyName2)) {
                     if ("awt.font.desktophints".equals(propertyName) && UIManager.getLookAndFeel() instanceof FlatLaf) {
                        this.putAATextInfo(UIManager.getLookAndFeelDefaults());
                        updateUILater();
                     }
                  } else {
                     reSetLookAndFeel();
                  }

               }
            };
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            toolkit.getDesktopProperty("dummy");
            toolkit.addPropertyChangeListener(this.desktopPropertyName, this.desktopPropertyListener);
            if (this.desktopPropertyName2 != null) {
               toolkit.addPropertyChangeListener(this.desktopPropertyName2, this.desktopPropertyListener);
            }

            toolkit.addPropertyChangeListener("awt.font.desktophints", this.desktopPropertyListener);
         }

         this.postInitialization = (defaults) -> {
            Color linkColor = defaults.getColor("Component.linkColor");
            if (linkColor != null) {
               (new HTMLEditorKit()).getStyleSheet().addRule(String.format("a, address { color: #%06x; }", linkColor.getRGB() & 16777215));
            }

         };
      }
   }

   public void uninitialize() {
      if (UIManager.getLookAndFeel() == this) {
         if (this.desktopPropertyListener != null) {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            toolkit.removePropertyChangeListener(this.desktopPropertyName, this.desktopPropertyListener);
            if (this.desktopPropertyName2 != null) {
               toolkit.removePropertyChangeListener(this.desktopPropertyName2, this.desktopPropertyListener);
            }

            toolkit.removePropertyChangeListener("awt.font.desktophints", this.desktopPropertyListener);
            this.desktopPropertyName = null;
            this.desktopPropertyName2 = null;
            this.desktopPropertyListener = null;
         }

         if (this.oldPopupFactory != null) {
            PopupFactory.setSharedInstance(this.oldPopupFactory);
            this.oldPopupFactory = null;
         }

         if (this.mnemonicHandler != null) {
            this.mnemonicHandler.uninstall();
            this.mnemonicHandler = null;
         }

         if (this.subMenuUsabilityHelperInstalled) {
            SubMenuUsabilityHelper.uninstall();
            this.subMenuUsabilityHelperInstalled = false;
         }

         (new HTMLEditorKit()).getStyleSheet().addRule("a, address { color: blue; }");
         this.postInitialization = null;
         super.uninitialize();
      }
   }

   private void initializeAqua() {
      if (!aquaLoaded) {
         aquaLoaded = true;
         String aquaLafClassName = "com.apple.laf.AquaLookAndFeel";

         BasicLookAndFeel aquaLaf;
         try {
            if (SystemInfo.isJava_9_orLater) {
               Method m = UIManager.class.getMethod("createLookAndFeel", String.class);
               aquaLaf = (BasicLookAndFeel)m.invoke((Object)null, "Mac OS X");
            } else {
               aquaLaf = (BasicLookAndFeel)Class.forName(aquaLafClassName).getDeclaredConstructor().newInstance();
            }
         } catch (Exception var4) {
            LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to initialize Aqua look and feel '" + aquaLafClassName + "'.", var4);
            throw new IllegalStateException();
         }

         PopupFactory oldPopupFactory = PopupFactory.getSharedInstance();
         aquaLaf.initialize();
         aquaLaf.uninitialize();
         PopupFactory.setSharedInstance(oldPopupFactory);
      }
   }

   public UIDefaults getDefaults() {
      UIDefaults defaults = new FlatLaf.FlatUIDefaults(1500, 0.75F);
      this.initClassDefaults(defaults);
      this.initSystemColorDefaults(defaults);
      this.initComponentDefaults(defaults);
      defaults.put("laf.dark", this.isDark());
      this.initResourceBundle(defaults, "com.formdev.flatlaf.resources.Bundle");
      this.putDefaults(defaults, defaults.getColor("control"), "Button.disabledBackground", "EditorPane.disabledBackground", "EditorPane.inactiveBackground", "FormattedTextField.disabledBackground", "PasswordField.disabledBackground", "RootPane.background", "Spinner.disabledBackground", "TextArea.disabledBackground", "TextArea.inactiveBackground", "TextField.disabledBackground", "TextPane.disabledBackground", "TextPane.inactiveBackground", "ToggleButton.disabledBackground");
      this.putDefaults(defaults, defaults.getColor("textInactiveText"), "Button.disabledText", "CheckBox.disabledText", "CheckBoxMenuItem.disabledForeground", "Menu.disabledForeground", "MenuItem.disabledForeground", "RadioButton.disabledText", "RadioButtonMenuItem.disabledForeground", "Spinner.disabledForeground", "ToggleButton.disabledText");
      this.putDefaults(defaults, defaults.getColor("textText"), "DesktopIcon.foreground", "RootPane.foreground");
      this.initFonts(defaults);
      initIconColors(defaults, this.isDark());
      FlatInputMaps.initInputMaps(defaults);
      Object icon = defaults.remove("InternalFrame.icon");
      defaults.put("InternalFrame.icon", icon);
      defaults.put("TitlePane.icon", icon);
      ServiceLoader<FlatDefaultsAddon> addonLoader = ServiceLoader.load(FlatDefaultsAddon.class);
      List<FlatDefaultsAddon> addons = new ArrayList();
      Iterator var5 = addonLoader.iterator();

      while(var5.hasNext()) {
         FlatDefaultsAddon addon = (FlatDefaultsAddon)var5.next();
         addons.add(addon);
      }

      addons.sort((addon1, addon2) -> {
         return addon1.getPriority() - addon2.getPriority();
      });
      List<Class<?>> lafClassesForDefaultsLoading = this.getLafClassesForDefaultsLoading();
      if (lafClassesForDefaultsLoading != null) {
         UIDefaultsLoader.loadDefaultsFromProperties((List)lafClassesForDefaultsLoading, addons, this.getAdditionalDefaults(), this.isDark(), defaults);
      } else {
         UIDefaultsLoader.loadDefaultsFromProperties((Class)this.getClass(), addons, this.getAdditionalDefaults(), this.isDark(), defaults);
      }

      this.initDefaultFont(defaults);
      if (SystemInfo.isMacOS && Boolean.getBoolean("apple.laf.useScreenMenuBar")) {
         defaults.put("MenuBarUI", "com.apple.laf.AquaMenuBarUI");
         defaults.put("MenuBar.backgroundPainter", BorderFactory.createEmptyBorder());
      }

      this.putAATextInfo(defaults);
      this.applyAdditionalDefaults(defaults);
      Iterator var9 = addons.iterator();

      while(var9.hasNext()) {
         FlatDefaultsAddon addon = (FlatDefaultsAddon)var9.next();
         addon.afterDefaultsLoading(this, defaults);
      }

      defaults.put("laf.scaleFactor", (t) -> {
         return UIScale.getUserScaleFactor();
      });
      if (this.postInitialization != null) {
         this.postInitialization.accept(defaults);
         this.postInitialization = null;
      }

      return defaults;
   }

   void applyAdditionalDefaults(UIDefaults defaults) {
   }

   protected List<Class<?>> getLafClassesForDefaultsLoading() {
      return null;
   }

   protected Properties getAdditionalDefaults() {
      if (globalExtraDefaults == null && this.extraDefaults == null) {
         return null;
      } else {
         Properties properties = new Properties();
         if (globalExtraDefaults != null) {
            properties.putAll(globalExtraDefaults);
         }

         if (this.extraDefaults != null) {
            properties.putAll(this.extraDefaults);
         }

         return properties;
      }
   }

   private void initResourceBundle(UIDefaults defaults, String bundleName) {
      defaults.addResourceBundle(bundleName);
      if (defaults.get("FileChooser.fileNameHeaderText") == null) {
         try {
            ResourceBundle bundle = ResourceBundle.getBundle(bundleName, defaults.getDefaultLocale());
            Enumeration keys = bundle.getKeys();

            while(keys.hasMoreElements()) {
               String key = (String)keys.nextElement();
               String value = bundle.getString(key);
               String baseKey = StringUtils.removeTrailing(key, ".textAndMnemonic");
               if (baseKey != key) {
                  String text = value.replace("&", "");
                  String mnemonic = null;
                  int index = value.indexOf(38);
                  if (index >= 0) {
                     mnemonic = Integer.toString(Character.toUpperCase(value.charAt(index + 1)));
                  }

                  defaults.put(baseKey + "Text", text);
                  if (mnemonic != null) {
                     defaults.put(baseKey + "Mnemonic", mnemonic);
                  }
               } else {
                  defaults.put(key, value);
               }
            }
         } catch (MissingResourceException var11) {
            LoggingFacade.INSTANCE.logSevere((String)null, var11);
         }

      }
   }

   private void initFonts(UIDefaults defaults) {
      Object activeFont = new FlatLaf.ActiveFont((String)null, (List)null, -1, 0, 0, 0, 0.0F);
      Iterator var3 = defaults.keySet().iterator();

      while(true) {
         Object key;
         do {
            do {
               if (!var3.hasNext()) {
                  defaults.put("RootPane.font", activeFont);
                  defaults.put("TitlePane.font", activeFont);
                  return;
               }

               key = var3.next();
            } while(!(key instanceof String));
         } while(!((String)key).endsWith(".font") && !((String)key).endsWith("Font"));

         defaults.put(key, activeFont);
      }
   }

   private void initDefaultFont(UIDefaults defaults) {
      FontUIResource uiFont = null;
      Font winFont;
      if (SystemInfo.isWindows) {
         winFont = (Font)Toolkit.getDefaultToolkit().getDesktopProperty("win.messagebox.font");
         if (winFont != null) {
            if (SystemInfo.isWinPE) {
               Font winPEFont = (Font)Toolkit.getDefaultToolkit().getDesktopProperty("win.defaultGUI.font");
               if (winPEFont != null) {
                  uiFont = createCompositeFont(winPEFont.getFamily(), winPEFont.getStyle(), winFont.getSize());
               }
            } else {
               uiFont = createCompositeFont(winFont.getFamily(), winFont.getStyle(), winFont.getSize());
            }
         }
      } else if (SystemInfo.isMacOS) {
         String fontName;
         if (SystemInfo.isMacOS_10_15_Catalina_orLater) {
            if (SystemInfo.isJetBrainsJVM_11_orLater) {
               fontName = ".AppleSystemUIFont";
            } else {
               fontName = "Helvetica Neue";
            }
         } else if (SystemInfo.isMacOS_10_11_ElCapitan_orLater) {
            fontName = ".SF NS Text";
         } else {
            fontName = "Lucida Grande";
         }

         uiFont = createCompositeFont(fontName, 0, 13);
      } else if (SystemInfo.isLinux) {
         winFont = LinuxFontPolicy.getFont();
         uiFont = winFont instanceof FontUIResource ? (FontUIResource)winFont : new FontUIResource(winFont);
      }

      if (uiFont == null) {
         uiFont = createCompositeFont("SansSerif", 0, 12);
      }

      if (preferredFontFamily != null) {
         FontUIResource preferredFont = createCompositeFont(preferredFontFamily, uiFont.getStyle(), uiFont.getSize());
         if (!FlatLaf.ActiveFont.isFallbackFont(preferredFont) || FlatLaf.ActiveFont.isDialogFamily(preferredFontFamily)) {
            uiFont = preferredFont;
         }
      }

      Object defaultFont = defaults.remove("defaultFont");
      if (defaultFont instanceof FlatLaf.ActiveFont) {
         uiFont = ((FlatLaf.ActiveFont)defaultFont).derive(uiFont, (fontSize) -> {
            return Math.round((float)fontSize * UIScale.computeFontScaleFactor(uiFont));
         });
      }

      uiFont = UIScale.applyCustomScaleFactor(uiFont);
      defaults.put("defaultFont", uiFont);
   }

   static FontUIResource createCompositeFont(String family, int style, int size) {
      FontUtils.loadFontFamily(family);
      Font font = StyleContext.getDefaultStyleContext().getFont(family, style, size);
      return font instanceof FontUIResource ? (FontUIResource)font : new FontUIResource(font);
   }

   public static ActiveValue createActiveFontValue(float scaleFactor) {
      return new FlatLaf.ActiveFont((String)null, (List)null, -1, 0, 0, 0, scaleFactor);
   }

   public static void initIconColors(UIDefaults defaults, boolean dark) {
      FlatIconColors[] var2 = FlatIconColors.values();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         FlatIconColors c = var2[var4];
         if (c.light == !dark || c.dark == dark) {
            defaults.put(c.key, new ColorUIResource(c.rgb));
         }
      }

   }

   private void putAATextInfo(UIDefaults defaults) {
      if (SystemInfo.isMacOS && SystemInfo.isJetBrainsJVM) {
         defaults.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      } else {
         Object desktopHints;
         if (SystemInfo.isJava_9_orLater) {
            desktopHints = Toolkit.getDefaultToolkit().getDesktopProperty("awt.font.desktophints");
            if (desktopHints == null) {
               desktopHints = this.fallbackAATextInfo();
            }

            if (desktopHints instanceof Map) {
               Map<Object, Object> hints = (Map)desktopHints;
               Object aaHint = hints.get(RenderingHints.KEY_TEXT_ANTIALIASING);
               if (aaHint != null && aaHint != RenderingHints.VALUE_TEXT_ANTIALIAS_OFF && aaHint != RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT) {
                  defaults.put(RenderingHints.KEY_TEXT_ANTIALIASING, aaHint);
                  defaults.put(RenderingHints.KEY_TEXT_LCD_CONTRAST, hints.get(RenderingHints.KEY_TEXT_LCD_CONTRAST));
               }
            }
         } else {
            try {
               desktopHints = Class.forName("sun.swing.SwingUtilities2").getField("AA_TEXT_PROPERTY_KEY").get((Object)null);
               Object value = Class.forName("sun.swing.SwingUtilities2$AATextInfo").getMethod("getAATextInfo", Boolean.TYPE).invoke((Object)null, true);
               if (value == null) {
                  value = this.fallbackAATextInfo();
               }

               defaults.put(desktopHints, value);
            } catch (Exception var5) {
               LoggingFacade.INSTANCE.logSevere((String)null, var5);
               throw new RuntimeException(var5);
            }
         }
      }

   }

   private Object fallbackAATextInfo() {
      if (System.getProperty("awt.useSystemAAFontSettings") != null) {
         return null;
      } else {
         Object aaHint = null;
         Integer lcdContrastHint = null;
         if (SystemInfo.isLinux) {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            if (toolkit.getDesktopProperty("gnome.Xft/Antialias") == null && toolkit.getDesktopProperty("fontconfig/Antialias") == null) {
               aaHint = RenderingHints.VALUE_TEXT_ANTIALIAS_ON;
            }
         }

         if (aaHint == null) {
            return null;
         } else if (SystemInfo.isJava_9_orLater) {
            Map<Object, Object> hints = new HashMap();
            hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, aaHint);
            hints.put(RenderingHints.KEY_TEXT_LCD_CONTRAST, lcdContrastHint);
            return hints;
         } else {
            try {
               return Class.forName("sun.swing.SwingUtilities2$AATextInfo").getConstructor(Object.class, Integer.class).newInstance(aaHint, lcdContrastHint);
            } catch (Exception var4) {
               LoggingFacade.INSTANCE.logSevere((String)null, var4);
               throw new RuntimeException(var4);
            }
         }
      }
   }

   private void putDefaults(UIDefaults defaults, Object value, String... keys) {
      String[] var4 = keys;
      int var5 = keys.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         String key = var4[var6];
         defaults.put(key, value);
      }

   }

   static List<Object> getCustomDefaultsSources() {
      return customDefaultsSources;
   }

   public static void registerCustomDefaultsSource(String packageName) {
      registerCustomDefaultsSource(packageName, (ClassLoader)null);
   }

   public static void unregisterCustomDefaultsSource(String packageName) {
      unregisterCustomDefaultsSource(packageName, (ClassLoader)null);
   }

   public static void registerCustomDefaultsSource(String packageName, ClassLoader classLoader) {
      if (customDefaultsSources == null) {
         customDefaultsSources = new ArrayList();
      }

      customDefaultsSources.add(packageName);
      customDefaultsSources.add(classLoader);
   }

   public static void unregisterCustomDefaultsSource(String packageName, ClassLoader classLoader) {
      if (customDefaultsSources != null) {
         int size = customDefaultsSources.size();

         for(int i = 0; i < size - 1; ++i) {
            Object source = customDefaultsSources.get(i);
            if (packageName.equals(source) && customDefaultsSources.get(i + 1) == classLoader) {
               customDefaultsSources.remove(i + 1);
               customDefaultsSources.remove(i);
               break;
            }
         }

      }
   }

   public static void registerCustomDefaultsSource(URL packageUrl) {
      if (customDefaultsSources == null) {
         customDefaultsSources = new ArrayList();
      }

      customDefaultsSources.add(packageUrl);
   }

   public static void unregisterCustomDefaultsSource(URL packageUrl) {
      if (customDefaultsSources != null) {
         customDefaultsSources.remove(packageUrl);
      }
   }

   public static void registerCustomDefaultsSource(File folder) {
      if (customDefaultsSources == null) {
         customDefaultsSources = new ArrayList();
      }

      customDefaultsSources.add(folder);
   }

   public static void unregisterCustomDefaultsSource(File folder) {
      if (customDefaultsSources != null) {
         customDefaultsSources.remove(folder);
      }
   }

   public static Map<String, String> getGlobalExtraDefaults() {
      return globalExtraDefaults;
   }

   public static void setGlobalExtraDefaults(Map<String, String> globalExtraDefaults) {
      FlatLaf.globalExtraDefaults = globalExtraDefaults;
   }

   public Map<String, String> getExtraDefaults() {
      return this.extraDefaults;
   }

   public void setExtraDefaults(Map<String, String> extraDefaults) {
      this.extraDefaults = extraDefaults;
   }

   public static Object parseDefaultsValue(String key, String value, Class<?> valueType) throws IllegalArgumentException {
      value = UIDefaultsLoader.resolveValueFromUIManager(value);
      Object val = UIDefaultsLoader.parseValue(key, value, valueType, (UIDefaultsLoader.ValueType[])null, (v) -> {
         return UIDefaultsLoader.resolveValueFromUIManager(v);
      }, Collections.emptyList());
      if (val instanceof LazyValue) {
         val = ((LazyValue)val).createValue((UIDefaults)null);
      } else if (val instanceof ActiveValue) {
         val = ((ActiveValue)val).createValue((UIDefaults)null);
      }

      return val;
   }

   public static Function<String, Color> getSystemColorGetter() {
      return systemColorGetter;
   }

   public static void setSystemColorGetter(Function<String, Color> systemColorGetter) {
      FlatLaf.systemColorGetter = systemColorGetter;
   }

   private static void reSetLookAndFeel() {
      EventQueue.invokeLater(() -> {
         LookAndFeel lookAndFeel = UIManager.getLookAndFeel();

         try {
            UIManager.setLookAndFeel(lookAndFeel);
            PropertyChangeEvent e = new PropertyChangeEvent(UIManager.class, "lookAndFeel", lookAndFeel, lookAndFeel);
            PropertyChangeListener[] var2 = UIManager.getPropertyChangeListeners();
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
               PropertyChangeListener l = var2[var4];
               l.propertyChange(e);
            }

            updateUI();
         } catch (UnsupportedLookAndFeelException var6) {
            LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to reinitialize look and feel '" + lookAndFeel.getClass().getName() + "'.", var6);
         }

      });
   }

   public static void updateUI() {
      Window[] var0 = Window.getWindows();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         Window w = var0[var2];
         SwingUtilities.updateComponentTreeUI(w);
      }

   }

   public static void updateUILater() {
      Class var0 = FlatLaf.class;
      synchronized(FlatLaf.class) {
         if (updateUIPending) {
            return;
         }

         updateUIPending = true;
      }

      EventQueue.invokeLater(() -> {
         updateUI();
         Class var0 = FlatLaf.class;
         synchronized(FlatLaf.class) {
            updateUIPending = false;
         }
      });
   }

   public static boolean supportsNativeWindowDecorations() {
      return SystemInfo.isWindows_10_orLater && FlatNativeWindowBorder.isSupported();
   }

   public static boolean isUseNativeWindowDecorations() {
      return UIManager.getBoolean("TitlePane.useWindowDecorations");
   }

   public static void setUseNativeWindowDecorations(boolean enabled) {
      UIManager.put("TitlePane.useWindowDecorations", enabled);
      if (UIManager.getLookAndFeel() instanceof FlatLaf) {
         Window[] var1 = Window.getWindows();
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            Window w = var1[var3];
            if (isDisplayableFrameOrDialog(w)) {
               FlatRootPaneUI.updateNativeWindowBorder(((RootPaneContainer)w).getRootPane());
            }
         }

      }
   }

   public static void revalidateAndRepaintAllFramesAndDialogs() {
      Window[] var0 = Window.getWindows();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         Window w = var0[var2];
         if (isDisplayableFrameOrDialog(w)) {
            JMenuBar menuBar = w instanceof JFrame ? ((JFrame)w).getJMenuBar() : (w instanceof JDialog ? ((JDialog)w).getJMenuBar() : null);
            if (menuBar != null) {
               menuBar.revalidate();
            }

            w.revalidate();
            w.repaint();
         }
      }

   }

   public static void repaintAllFramesAndDialogs() {
      Window[] var0 = Window.getWindows();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         Window w = var0[var2];
         if (isDisplayableFrameOrDialog(w)) {
            w.repaint();
         }
      }

   }

   private static boolean isDisplayableFrameOrDialog(Window w) {
      return w.isDisplayable() && (w instanceof JFrame || w instanceof JDialog);
   }

   public static boolean isShowMnemonics() {
      return MnemonicHandler.isShowMnemonics();
   }

   public static void showMnemonics(Component c) {
      MnemonicHandler.showMnemonics(true, c);
   }

   public static void hideMnemonics() {
      MnemonicHandler.showMnemonics(false, (Component)null);
   }

   public final boolean equals(Object obj) {
      return super.equals(obj);
   }

   public final int hashCode() {
      return super.hashCode();
   }

   public void registerUIDefaultsGetter(Function<Object, Object> uiDefaultsGetter) {
      if (this.uiDefaultsGetters == null) {
         this.uiDefaultsGetters = new ArrayList();
      }

      this.uiDefaultsGetters.remove(uiDefaultsGetter);
      this.uiDefaultsGetters.add(uiDefaultsGetter);
      FlatUIUtils.setUseSharedUIs(false);
   }

   public void unregisterUIDefaultsGetter(Function<Object, Object> uiDefaultsGetter) {
      if (this.uiDefaultsGetters != null) {
         this.uiDefaultsGetters.remove(uiDefaultsGetter);
         if (this.uiDefaultsGetters.isEmpty()) {
            FlatUIUtils.setUseSharedUIs(true);
         }

      }
   }

   public static void runWithUIDefaultsGetter(Function<Object, Object> uiDefaultsGetter, Runnable runnable) {
      LookAndFeel laf = UIManager.getLookAndFeel();
      if (laf instanceof FlatLaf) {
         ((FlatLaf)laf).registerUIDefaultsGetter(uiDefaultsGetter);

         try {
            runnable.run();
         } finally {
            ((FlatLaf)laf).unregisterUIDefaultsGetter(uiDefaultsGetter);
         }
      } else {
         runnable.run();
      }

   }

   public static Map<String, Class<?>> getStyleableInfos(JComponent c) {
      FlatStylingSupport.StyleableUI ui = getStyleableUI(c);
      return ui != null ? ui.getStyleableInfos(c) : null;
   }

   public static <T> T getStyleableValue(JComponent c, String key) {
      FlatStylingSupport.StyleableUI ui = getStyleableUI(c);
      return ui != null ? ui.getStyleableValue(c, key) : null;
   }

   private static FlatStylingSupport.StyleableUI getStyleableUI(JComponent c) {
      if (!getUIMethodInitialized) {
         getUIMethodInitialized = true;
         if (SystemInfo.isJava_9_orLater) {
            try {
               getUIMethod = MethodHandles.lookup().findVirtual(JComponent.class, "getUI", MethodType.methodType(ComponentUI.class));
            } catch (Exception var3) {
            }
         }
      }

      try {
         Object ui;
         if (getUIMethod != null) {
            ui = getUIMethod.invoke(c);
         } else {
            ui = c.getClass().getMethod("getUI").invoke(c);
         }

         return ui instanceof FlatStylingSupport.StyleableUI ? (FlatStylingSupport.StyleableUI)ui : null;
      } catch (Throwable var2) {
         return null;
      }
   }

   public static String getPreferredFontFamily() {
      return preferredFontFamily;
   }

   public static void setPreferredFontFamily(String preferredFontFamily) {
      FlatLaf.preferredFontFamily = preferredFontFamily;
   }

   public static String getPreferredLightFontFamily() {
      return preferredLightFontFamily;
   }

   public static void setPreferredLightFontFamily(String preferredLightFontFamily) {
      FlatLaf.preferredLightFontFamily = preferredLightFontFamily;
   }

   public static String getPreferredSemiboldFontFamily() {
      return preferredSemiboldFontFamily;
   }

   public static void setPreferredSemiboldFontFamily(String preferredSemiboldFontFamily) {
      FlatLaf.preferredSemiboldFontFamily = preferredSemiboldFontFamily;
   }

   public static String getPreferredMonospacedFontFamily() {
      return preferredMonospacedFontFamily;
   }

   public static void setPreferredMonospacedFontFamily(String preferredMonospacedFontFamily) {
      FlatLaf.preferredMonospacedFontFamily = preferredMonospacedFontFamily;
   }

   public interface DisabledIconProvider {
      Icon getDisabledIcon();
   }

   private static class ImageIconUIResource extends ImageIcon implements UIResource {
      ImageIconUIResource(Image image) {
         super(image);
      }
   }

   static class ActiveFont implements ActiveValue {
      private final String baseFontKey;
      private final List<String> families;
      private final int style;
      private final int styleChange;
      private final int absoluteSize;
      private final int relativeSize;
      private final float scaleSize;
      private FontUIResource font;
      private Font lastBaseFont;
      private boolean inCreateValue;

      ActiveFont(String baseFontKey, List<String> families, int style, int styleChange, int absoluteSize, int relativeSize, float scaleSize) {
         this.baseFontKey = baseFontKey;
         this.families = families;
         this.style = style;
         this.styleChange = styleChange;
         this.absoluteSize = absoluteSize;
         this.relativeSize = relativeSize;
         this.scaleSize = scaleSize;
      }

      public synchronized Object createValue(UIDefaults table) {
         if (this.inCreateValue) {
            throw new IllegalStateException("FlatLaf: endless recursion in font");
         } else {
            Font baseFont = null;
            this.inCreateValue = true;

            try {
               if (this.baseFontKey != null) {
                  baseFont = (Font)UIDefaultsLoader.lazyUIManagerGet(this.baseFontKey);
               }

               if (baseFont == null) {
                  baseFont = UIManager.getFont("defaultFont");
               }

               if (baseFont == null) {
                  baseFont = UIManager.getFont("Label.font");
               }
            } finally {
               this.inCreateValue = false;
            }

            if (this.lastBaseFont != baseFont) {
               this.lastBaseFont = baseFont;
               this.font = this.derive(baseFont, (fontSize) -> {
                  return UIScale.scale(fontSize);
               });
            }

            return this.font;
         }
      }

      FontUIResource derive(Font baseFont, IntUnaryOperator scale) {
         int baseStyle = baseFont.getStyle();
         int baseSize = baseFont.getSize();
         int newStyle = this.style != -1 ? this.style : (this.styleChange != 0 ? baseStyle & ~(this.styleChange >> 16 & '\uffff') | this.styleChange & '\uffff' : baseStyle);
         int newSize = this.absoluteSize > 0 ? scale.applyAsInt(this.absoluteSize) : (this.relativeSize != 0 ? baseSize + scale.applyAsInt(this.relativeSize) : (this.scaleSize > 0.0F ? Math.round((float)baseSize * this.scaleSize) : baseSize));
         if (newSize <= 0) {
            newSize = 1;
         }

         if (this.families != null && !this.families.isEmpty()) {
            label77: {
               String preferredFamily = preferredFamily(this.families);
               if (preferredFamily != null) {
                  Font font = FlatLaf.createCompositeFont(preferredFamily, newStyle, newSize);
                  if (!isFallbackFont(font) || isDialogFamily(preferredFamily)) {
                     return this.toUIResource(font);
                  }
               }

               Iterator var12 = this.families.iterator();

               String family;
               FontUIResource font;
               do {
                  if (!var12.hasNext()) {
                     break label77;
                  }

                  family = (String)var12.next();
                  font = FlatLaf.createCompositeFont(family, newStyle, newSize);
               } while(isFallbackFont(font) && !isDialogFamily(family));

               return this.toUIResource(font);
            }
         }

         if (newStyle == baseStyle && newSize == baseSize) {
            return this.toUIResource(baseFont);
         } else {
            if ("Ubuntu Medium".equalsIgnoreCase(baseFont.getName()) && "Ubuntu Light".equalsIgnoreCase(baseFont.getFamily())) {
               Font font = FlatLaf.createCompositeFont("Ubuntu Medium", newStyle, newSize);
               if (!isFallbackFont(font)) {
                  return this.toUIResource(font);
               }
            }

            return this.toUIResource(baseFont.deriveFont(newStyle, (float)newSize));
         }
      }

      private FontUIResource toUIResource(Font font) {
         return font instanceof FontUIResource ? (FontUIResource)font : new FontUIResource(font);
      }

      private static boolean isFallbackFont(Font font) {
         return "Dialog".equalsIgnoreCase(font.getFamily());
      }

      private static boolean isDialogFamily(String family) {
         return family.equalsIgnoreCase("Dialog");
      }

      private static String preferredFamily(List<String> families) {
         Iterator var1 = families.iterator();

         while(var1.hasNext()) {
            String family = (String)var1.next();
            family = family.toLowerCase(Locale.ENGLISH);
            if (!family.endsWith(" light") && !family.endsWith("-thin")) {
               if (!family.endsWith(" semibold") && !family.endsWith("-medium")) {
                  if (family.equals("monospaced")) {
                     return FlatLaf.preferredMonospacedFontFamily;
                  }
                  continue;
               }

               return FlatLaf.preferredSemiboldFontFamily;
            }

            return FlatLaf.preferredLightFontFamily;
         }

         return null;
      }
   }

   private class FlatUIDefaults extends UIDefaults {
      FlatUIDefaults(int initialCapacity, float loadFactor) {
         super(initialCapacity, loadFactor);
      }

      public Object get(Object key) {
         Object value = this.getValue(key);
         return value != null ? (value != FlatLaf.NULL_VALUE ? value : null) : super.get(key);
      }

      public Object get(Object key, Locale l) {
         Object value = this.getValue(key);
         return value != null ? (value != FlatLaf.NULL_VALUE ? value : null) : super.get(key, l);
      }

      private Object getValue(Object key) {
         List<Function<Object, Object>> uiDefaultsGetters = FlatLaf.this.uiDefaultsGetters;
         if (uiDefaultsGetters == null) {
            return null;
         } else {
            for(int i = uiDefaultsGetters.size() - 1; i >= 0; --i) {
               Object value = ((Function)uiDefaultsGetters.get(i)).apply(key);
               if (value != null) {
                  return value;
               }
            }

            return null;
         }
      }
   }
}
