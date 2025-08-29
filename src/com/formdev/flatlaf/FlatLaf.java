/*      */ package com.formdev.flatlaf;
/*      */ 
/*      */ import com.formdev.flatlaf.ui.FlatNativeWindowBorder;
/*      */ import com.formdev.flatlaf.ui.FlatPopupFactory;
/*      */ import com.formdev.flatlaf.ui.FlatRootPaneUI;
/*      */ import com.formdev.flatlaf.ui.FlatStylingSupport;
/*      */ import com.formdev.flatlaf.ui.FlatUIUtils;
/*      */ import com.formdev.flatlaf.util.FontUtils;
/*      */ import com.formdev.flatlaf.util.GrayFilter;
/*      */ import com.formdev.flatlaf.util.LoggingFacade;
/*      */ import com.formdev.flatlaf.util.MultiResolutionImageSupport;
/*      */ import com.formdev.flatlaf.util.StringUtils;
/*      */ import com.formdev.flatlaf.util.SystemInfo;
/*      */ import com.formdev.flatlaf.util.UIScale;
/*      */ import java.awt.Color;
/*      */ import java.awt.Component;
/*      */ import java.awt.EventQueue;
/*      */ import java.awt.Font;
/*      */ import java.awt.Image;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.Toolkit;
/*      */ import java.awt.Window;
/*      */ import java.awt.image.FilteredImageSource;
/*      */ import java.awt.image.ImageFilter;
/*      */ import java.awt.image.ImageProducer;
/*      */ import java.beans.PropertyChangeEvent;
/*      */ import java.beans.PropertyChangeListener;
/*      */ import java.io.File;
/*      */ import java.lang.invoke.MethodHandle;
/*      */ import java.lang.invoke.MethodHandles;
/*      */ import java.lang.invoke.MethodType;
/*      */ import java.lang.reflect.Method;
/*      */ import java.net.URL;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.MissingResourceException;
/*      */ import java.util.Properties;
/*      */ import java.util.ResourceBundle;
/*      */ import java.util.ServiceLoader;
/*      */ import java.util.function.Consumer;
/*      */ import java.util.function.Function;
/*      */ import java.util.function.IntUnaryOperator;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.Icon;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JComponent;
/*      */ import javax.swing.JDialog;
/*      */ import javax.swing.JFrame;
/*      */ import javax.swing.JMenuBar;
/*      */ import javax.swing.LookAndFeel;
/*      */ import javax.swing.PopupFactory;
/*      */ import javax.swing.RootPaneContainer;
/*      */ import javax.swing.SwingUtilities;
/*      */ import javax.swing.UIDefaults;
/*      */ import javax.swing.UIManager;
/*      */ import javax.swing.UnsupportedLookAndFeelException;
/*      */ import javax.swing.plaf.ColorUIResource;
/*      */ import javax.swing.plaf.ComponentUI;
/*      */ import javax.swing.plaf.FontUIResource;
/*      */ import javax.swing.plaf.IconUIResource;
/*      */ import javax.swing.plaf.UIResource;
/*      */ import javax.swing.plaf.basic.BasicLookAndFeel;
/*      */ import javax.swing.text.StyleContext;
/*      */ import javax.swing.text.html.HTMLEditorKit;
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
/*      */ public abstract class FlatLaf
/*      */   extends BasicLookAndFeel
/*      */ {
/*      */   private static final String DESKTOPFONTHINTS = "awt.font.desktophints";
/*      */   private static List<Object> customDefaultsSources;
/*      */   private static Map<String, String> globalExtraDefaults;
/*      */   private Map<String, String> extraDefaults;
/*      */   private static Function<String, Color> systemColorGetter;
/*      */   private String desktopPropertyName;
/*      */   private String desktopPropertyName2;
/*      */   private PropertyChangeListener desktopPropertyListener;
/*      */   private static boolean aquaLoaded;
/*      */   private static boolean updateUIPending;
/*      */   private PopupFactory oldPopupFactory;
/*      */   private MnemonicHandler mnemonicHandler;
/*      */   private boolean subMenuUsabilityHelperInstalled;
/*      */   private Consumer<UIDefaults> postInitialization;
/*      */   private List<Function<Object, Object>> uiDefaultsGetters;
/*      */   private static String preferredFontFamily;
/*      */   private static String preferredLightFontFamily;
/*      */   private static String preferredSemiboldFontFamily;
/*      */   private static String preferredMonospacedFontFamily;
/*      */   
/*      */   public static boolean setup(LookAndFeel newLookAndFeel) {
/*      */     try {
/*  131 */       UIManager.setLookAndFeel(newLookAndFeel);
/*  132 */       return true;
/*  133 */     } catch (Exception ex) {
/*  134 */       LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to setup look and feel '" + newLookAndFeel.getClass().getName() + "'.", ex);
/*  135 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static boolean install(LookAndFeel newLookAndFeel) {
/*  144 */     return setup(newLookAndFeel);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void installLafInfo(String lafName, Class<? extends LookAndFeel> lafClass) {
/*  154 */     UIManager.installLookAndFeel(new UIManager.LookAndFeelInfo(lafName, lafClass.getName()));
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
/*      */   public String getID() {
/*  167 */     return "FlatLaf - " + getName();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isLafDark() {
/*  176 */     LookAndFeel lookAndFeel = UIManager.getLookAndFeel();
/*  177 */     return (lookAndFeel instanceof FlatLaf && ((FlatLaf)lookAndFeel).isDark());
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
/*      */   public boolean getSupportsWindowDecorations() {
/*  202 */     if (SystemInfo.isProjector || SystemInfo.isWebswing || SystemInfo.isWinPE) {
/*  203 */       return false;
/*      */     }
/*  205 */     if (SystemInfo.isWindows_10_orLater && 
/*  206 */       FlatNativeWindowBorder.isSupported()) {
/*  207 */       return false;
/*      */     }
/*  209 */     return (SystemInfo.isWindows_10_orLater || SystemInfo.isLinux);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isNativeLookAndFeel() {
/*  214 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isSupportedLookAndFeel() {
/*  219 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public Icon getDisabledIcon(JComponent component, Icon icon) {
/*  224 */     if (icon instanceof DisabledIconProvider) {
/*  225 */       Icon disabledIcon = ((DisabledIconProvider)icon).getDisabledIcon();
/*  226 */       return !(disabledIcon instanceof UIResource) ? new IconUIResource(disabledIcon) : disabledIcon;
/*      */     } 
/*      */     
/*  229 */     if (icon instanceof ImageIcon) {
/*  230 */       Object grayFilter = UIManager.get("Component.grayFilter");
/*      */ 
/*      */       
/*  233 */       ImageFilter filter = (grayFilter instanceof ImageFilter) ? (ImageFilter)grayFilter : (ImageFilter)GrayFilter.createDisabledIconFilter(isDark());
/*      */       
/*  235 */       Function<Image, Image> mapper = img -> {
/*      */           ImageProducer producer = new FilteredImageSource(img.getSource(), filter);
/*      */           
/*      */           return Toolkit.getDefaultToolkit().createImage(producer);
/*      */         };
/*  240 */       Image image = ((ImageIcon)icon).getImage();
/*  241 */       return new ImageIconUIResource(MultiResolutionImageSupport.map(image, mapper));
/*      */     } 
/*      */     
/*  244 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void initialize() {
/*  255 */     if (UIManager.getLookAndFeel() != this) {
/*      */       return;
/*      */     }
/*  258 */     if (SystemInfo.isMacOS) {
/*  259 */       initializeAqua();
/*      */     }
/*  261 */     super.initialize();
/*      */ 
/*      */     
/*  264 */     this.oldPopupFactory = PopupFactory.getSharedInstance();
/*  265 */     PopupFactory.setSharedInstance((PopupFactory)new FlatPopupFactory());
/*      */ 
/*      */     
/*  268 */     this.mnemonicHandler = new MnemonicHandler();
/*  269 */     this.mnemonicHandler.install();
/*      */ 
/*      */     
/*  272 */     this.subMenuUsabilityHelperInstalled = SubMenuUsabilityHelper.install();
/*      */ 
/*      */     
/*  275 */     if (SystemInfo.isWindows) {
/*      */ 
/*      */       
/*  278 */       this.desktopPropertyName = "win.messagebox.font";
/*  279 */     } else if (SystemInfo.isLinux) {
/*      */       
/*  281 */       this.desktopPropertyName = "gnome.Gtk/FontName";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  287 */       this.desktopPropertyName2 = "gnome.Xft/DPI";
/*      */     } 
/*  289 */     if (this.desktopPropertyName != null) {
/*  290 */       this.desktopPropertyListener = (e -> {
/*      */           if (!FlatSystemProperties.getBoolean("flatlaf.updateUIOnSystemFontChange", true)) {
/*      */             return;
/*      */           }
/*      */           
/*      */           String propertyName = e.getPropertyName();
/*      */           
/*      */           if (this.desktopPropertyName.equals(propertyName) || propertyName.equals(this.desktopPropertyName2)) {
/*      */             reSetLookAndFeel();
/*      */           } else if ("awt.font.desktophints".equals(propertyName) && UIManager.getLookAndFeel() instanceof FlatLaf) {
/*      */             putAATextInfo(UIManager.getLookAndFeelDefaults());
/*      */             updateUILater();
/*      */           } 
/*      */         });
/*  304 */       Toolkit toolkit = Toolkit.getDefaultToolkit();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  309 */       toolkit.getDesktopProperty("dummy");
/*      */       
/*  311 */       toolkit.addPropertyChangeListener(this.desktopPropertyName, this.desktopPropertyListener);
/*  312 */       if (this.desktopPropertyName2 != null)
/*  313 */         toolkit.addPropertyChangeListener(this.desktopPropertyName2, this.desktopPropertyListener); 
/*  314 */       toolkit.addPropertyChangeListener("awt.font.desktophints", this.desktopPropertyListener);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  320 */     this.postInitialization = (defaults -> {
/*      */         Color linkColor = defaults.getColor("Component.linkColor");
/*      */         if (linkColor != null) {
/*      */           (new HTMLEditorKit()).getStyleSheet().addRule(String.format("a, address { color: #%06x; }", new Object[] { Integer.valueOf(linkColor.getRGB() & 0xFFFFFF) }));
/*      */         }
/*      */       });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void uninitialize() {
/*  333 */     if (UIManager.getLookAndFeel() != this) {
/*      */       return;
/*      */     }
/*      */     
/*  337 */     if (this.desktopPropertyListener != null) {
/*  338 */       Toolkit toolkit = Toolkit.getDefaultToolkit();
/*  339 */       toolkit.removePropertyChangeListener(this.desktopPropertyName, this.desktopPropertyListener);
/*  340 */       if (this.desktopPropertyName2 != null)
/*  341 */         toolkit.removePropertyChangeListener(this.desktopPropertyName2, this.desktopPropertyListener); 
/*  342 */       toolkit.removePropertyChangeListener("awt.font.desktophints", this.desktopPropertyListener);
/*  343 */       this.desktopPropertyName = null;
/*  344 */       this.desktopPropertyName2 = null;
/*  345 */       this.desktopPropertyListener = null;
/*      */     } 
/*      */ 
/*      */     
/*  349 */     if (this.oldPopupFactory != null) {
/*  350 */       PopupFactory.setSharedInstance(this.oldPopupFactory);
/*  351 */       this.oldPopupFactory = null;
/*      */     } 
/*      */ 
/*      */     
/*  355 */     if (this.mnemonicHandler != null) {
/*  356 */       this.mnemonicHandler.uninstall();
/*  357 */       this.mnemonicHandler = null;
/*      */     } 
/*      */ 
/*      */     
/*  361 */     if (this.subMenuUsabilityHelperInstalled) {
/*  362 */       SubMenuUsabilityHelper.uninstall();
/*  363 */       this.subMenuUsabilityHelperInstalled = false;
/*      */     } 
/*      */ 
/*      */     
/*  367 */     (new HTMLEditorKit()).getStyleSheet().addRule("a, address { color: blue; }");
/*  368 */     this.postInitialization = null;
/*      */     
/*  370 */     super.uninitialize();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void initializeAqua() {
/*      */     BasicLookAndFeel aquaLaf;
/*  381 */     if (aquaLoaded) {
/*      */       return;
/*      */     }
/*  384 */     aquaLoaded = true;
/*      */ 
/*      */     
/*  387 */     String aquaLafClassName = "com.apple.laf.AquaLookAndFeel";
/*      */     
/*      */     try {
/*  390 */       if (SystemInfo.isJava_9_orLater)
/*  391 */       { Method m = UIManager.class.getMethod("createLookAndFeel", new Class[] { String.class });
/*  392 */         aquaLaf = (BasicLookAndFeel)m.invoke(null, new Object[] { "Mac OS X" }); }
/*      */       else
/*  394 */       { aquaLaf = Class.forName(aquaLafClassName).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]); } 
/*  395 */     } catch (Exception ex) {
/*  396 */       LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to initialize Aqua look and feel '" + aquaLafClassName + "'.", ex);
/*  397 */       throw new IllegalStateException();
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  402 */     PopupFactory oldPopupFactory = PopupFactory.getSharedInstance();
/*      */ 
/*      */     
/*  405 */     aquaLaf.initialize();
/*  406 */     aquaLaf.uninitialize();
/*      */ 
/*      */     
/*  409 */     PopupFactory.setSharedInstance(oldPopupFactory);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public UIDefaults getDefaults() {
/*  416 */     UIDefaults defaults = new FlatUIDefaults(1500, 0.75F);
/*      */ 
/*      */     
/*  419 */     initClassDefaults(defaults);
/*  420 */     initSystemColorDefaults(defaults);
/*  421 */     initComponentDefaults(defaults);
/*      */ 
/*      */ 
/*      */     
/*  425 */     defaults.put("laf.dark", Boolean.valueOf(isDark()));
/*      */ 
/*      */     
/*  428 */     initResourceBundle(defaults, "com.formdev.flatlaf.resources.Bundle");
/*      */ 
/*      */ 
/*      */     
/*  432 */     putDefaults(defaults, defaults.getColor("control"), new String[] { "Button.disabledBackground", "EditorPane.disabledBackground", "EditorPane.inactiveBackground", "FormattedTextField.disabledBackground", "PasswordField.disabledBackground", "RootPane.background", "Spinner.disabledBackground", "TextArea.disabledBackground", "TextArea.inactiveBackground", "TextField.disabledBackground", "TextPane.disabledBackground", "TextPane.inactiveBackground", "ToggleButton.disabledBackground" });
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
/*  446 */     putDefaults(defaults, defaults.getColor("textInactiveText"), new String[] { "Button.disabledText", "CheckBox.disabledText", "CheckBoxMenuItem.disabledForeground", "Menu.disabledForeground", "MenuItem.disabledForeground", "RadioButton.disabledText", "RadioButtonMenuItem.disabledForeground", "Spinner.disabledForeground", "ToggleButton.disabledText" });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  456 */     putDefaults(defaults, defaults.getColor("textText"), new String[] { "DesktopIcon.foreground", "RootPane.foreground" });
/*      */ 
/*      */ 
/*      */     
/*  460 */     initFonts(defaults);
/*  461 */     initIconColors(defaults, isDark());
/*  462 */     FlatInputMaps.initInputMaps(defaults);
/*      */ 
/*      */ 
/*      */     
/*  466 */     Object icon = defaults.remove("InternalFrame.icon");
/*  467 */     defaults.put("InternalFrame.icon", icon);
/*  468 */     defaults.put("TitlePane.icon", icon);
/*      */ 
/*      */     
/*  471 */     ServiceLoader<FlatDefaultsAddon> addonLoader = ServiceLoader.load(FlatDefaultsAddon.class);
/*  472 */     List<FlatDefaultsAddon> addons = new ArrayList<>();
/*  473 */     for (FlatDefaultsAddon addon : addonLoader)
/*  474 */       addons.add(addon); 
/*  475 */     addons.sort((addon1, addon2) -> addon1.getPriority() - addon2.getPriority());
/*      */ 
/*      */     
/*  478 */     List<Class<?>> lafClassesForDefaultsLoading = getLafClassesForDefaultsLoading();
/*  479 */     if (lafClassesForDefaultsLoading != null) {
/*  480 */       UIDefaultsLoader.loadDefaultsFromProperties(lafClassesForDefaultsLoading, addons, getAdditionalDefaults(), isDark(), defaults);
/*      */     } else {
/*  482 */       UIDefaultsLoader.loadDefaultsFromProperties(getClass(), addons, getAdditionalDefaults(), isDark(), defaults);
/*      */     } 
/*      */ 
/*      */     
/*  486 */     initDefaultFont(defaults);
/*      */ 
/*      */     
/*  489 */     if (SystemInfo.isMacOS && Boolean.getBoolean("apple.laf.useScreenMenuBar")) {
/*  490 */       defaults.put("MenuBarUI", "com.apple.laf.AquaMenuBarUI");
/*      */ 
/*      */       
/*  493 */       defaults.put("MenuBar.backgroundPainter", BorderFactory.createEmptyBorder());
/*      */     } 
/*      */ 
/*      */     
/*  497 */     putAATextInfo(defaults);
/*      */ 
/*      */     
/*  500 */     applyAdditionalDefaults(defaults);
/*      */ 
/*      */     
/*  503 */     for (FlatDefaultsAddon addon : addons) {
/*  504 */       addon.afterDefaultsLoading(this, defaults);
/*      */     }
/*      */     
/*  507 */     defaults.put("laf.scaleFactor", t -> Float.valueOf(UIScale.getUserScaleFactor()));
/*      */ 
/*      */ 
/*      */     
/*  511 */     if (this.postInitialization != null) {
/*  512 */       this.postInitialization.accept(defaults);
/*  513 */       this.postInitialization = null;
/*      */     } 
/*      */     
/*  516 */     return defaults;
/*      */   }
/*      */ 
/*      */   
/*      */   void applyAdditionalDefaults(UIDefaults defaults) {}
/*      */   
/*      */   protected List<Class<?>> getLafClassesForDefaultsLoading() {
/*  523 */     return null;
/*      */   }
/*      */   
/*      */   protected Properties getAdditionalDefaults() {
/*  527 */     if (globalExtraDefaults == null && this.extraDefaults == null) {
/*  528 */       return null;
/*      */     }
/*  530 */     Properties properties = new Properties();
/*  531 */     if (globalExtraDefaults != null)
/*  532 */       properties.putAll(globalExtraDefaults); 
/*  533 */     if (this.extraDefaults != null)
/*  534 */       properties.putAll(this.extraDefaults); 
/*  535 */     return properties;
/*      */   }
/*      */ 
/*      */   
/*      */   private void initResourceBundle(UIDefaults defaults, String bundleName) {
/*  540 */     defaults.addResourceBundle(bundleName);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  546 */     if (defaults.get("FileChooser.fileNameHeaderText") != null) {
/*      */       return;
/*      */     }
/*      */     
/*      */     try {
/*  551 */       ResourceBundle bundle = ResourceBundle.getBundle(bundleName, defaults.getDefaultLocale());
/*      */       
/*  553 */       Enumeration<String> keys = bundle.getKeys();
/*  554 */       while (keys.hasMoreElements()) {
/*  555 */         String key = keys.nextElement();
/*  556 */         String value = bundle.getString(key);
/*      */         
/*  558 */         String baseKey = StringUtils.removeTrailing(key, ".textAndMnemonic");
/*  559 */         if (baseKey != key) {
/*  560 */           String text = value.replace("&", "");
/*  561 */           String mnemonic = null;
/*  562 */           int index = value.indexOf('&');
/*  563 */           if (index >= 0) {
/*  564 */             mnemonic = Integer.toString(Character.toUpperCase(value.charAt(index + 1)));
/*      */           }
/*  566 */           defaults.put(baseKey + "Text", text);
/*  567 */           if (mnemonic != null)
/*  568 */             defaults.put(baseKey + "Mnemonic", mnemonic);  continue;
/*      */         } 
/*  570 */         defaults.put(key, value);
/*      */       } 
/*  572 */     } catch (MissingResourceException ex) {
/*  573 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void initFonts(UIDefaults defaults) {
/*  581 */     Object activeFont = new ActiveFont(null, null, -1, 0, 0, 0, 0.0F);
/*      */ 
/*      */     
/*  584 */     for (Object key : defaults.keySet()) {
/*  585 */       if (key instanceof String && (((String)key).endsWith(".font") || ((String)key).endsWith("Font"))) {
/*  586 */         defaults.put(key, activeFont);
/*      */       }
/*      */     } 
/*      */     
/*  590 */     defaults.put("RootPane.font", activeFont);
/*  591 */     defaults.put("TitlePane.font", activeFont);
/*      */   }
/*      */   
/*      */   private void initDefaultFont(UIDefaults defaults) {
/*  595 */     FontUIResource uiFont = null;
/*      */ 
/*      */     
/*  598 */     if (SystemInfo.isWindows) {
/*  599 */       Font winFont = (Font)Toolkit.getDefaultToolkit().getDesktopProperty("win.messagebox.font");
/*  600 */       if (winFont != null) {
/*  601 */         if (SystemInfo.isWinPE) {
/*      */ 
/*      */           
/*  604 */           Font winPEFont = (Font)Toolkit.getDefaultToolkit().getDesktopProperty("win.defaultGUI.font");
/*  605 */           if (winPEFont != null)
/*  606 */             uiFont = createCompositeFont(winPEFont.getFamily(), winPEFont.getStyle(), winFont.getSize()); 
/*      */         } else {
/*  608 */           uiFont = createCompositeFont(winFont.getFamily(), winFont.getStyle(), winFont.getSize());
/*      */         } 
/*      */       }
/*  611 */     } else if (SystemInfo.isMacOS) {
/*      */       String fontName;
/*  613 */       if (SystemInfo.isMacOS_10_15_Catalina_orLater) {
/*  614 */         if (SystemInfo.isJetBrainsJVM_11_orLater) {
/*      */           
/*  616 */           fontName = ".AppleSystemUIFont";
/*      */         } else {
/*      */           
/*  619 */           fontName = "Helvetica Neue";
/*      */         } 
/*  621 */       } else if (SystemInfo.isMacOS_10_11_ElCapitan_orLater) {
/*      */         
/*  623 */         fontName = ".SF NS Text";
/*      */       } else {
/*      */         
/*  626 */         fontName = "Lucida Grande";
/*      */       } 
/*      */       
/*  629 */       uiFont = createCompositeFont(fontName, 0, 13);
/*      */     }
/*  631 */     else if (SystemInfo.isLinux) {
/*  632 */       Font font = LinuxFontPolicy.getFont();
/*  633 */       uiFont = (font instanceof FontUIResource) ? (FontUIResource)font : new FontUIResource(font);
/*      */     } 
/*      */ 
/*      */     
/*  637 */     if (uiFont == null) {
/*  638 */       uiFont = createCompositeFont("SansSerif", 0, 12);
/*      */     }
/*      */     
/*  641 */     if (preferredFontFamily != null) {
/*  642 */       FontUIResource preferredFont = createCompositeFont(preferredFontFamily, uiFont.getStyle(), uiFont.getSize());
/*  643 */       if (!ActiveFont.isFallbackFont(preferredFont) || ActiveFont.isDialogFamily(preferredFontFamily)) {
/*  644 */         uiFont = preferredFont;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  649 */     Object defaultFont = defaults.remove("defaultFont");
/*      */ 
/*      */     
/*  652 */     if (defaultFont instanceof ActiveFont) {
/*  653 */       Font baseFont = uiFont;
/*  654 */       uiFont = ((ActiveFont)defaultFont).derive(baseFont, fontSize -> Math.round(fontSize * UIScale.computeFontScaleFactor(baseFont)));
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  660 */     uiFont = UIScale.applyCustomScaleFactor(uiFont);
/*      */ 
/*      */     
/*  663 */     defaults.put("defaultFont", uiFont);
/*      */   }
/*      */ 
/*      */   
/*      */   static FontUIResource createCompositeFont(String family, int style, int size) {
/*  668 */     FontUtils.loadFontFamily(family);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  673 */     Font font = StyleContext.getDefaultStyleContext().getFont(family, style, size);
/*  674 */     return (font instanceof FontUIResource) ? (FontUIResource)font : new FontUIResource(font);
/*      */   }
/*      */ 
/*      */   
/*      */   public static UIDefaults.ActiveValue createActiveFontValue(float scaleFactor) {
/*  679 */     return new ActiveFont(null, null, -1, 0, 0, 0, scaleFactor);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void initIconColors(UIDefaults defaults, boolean dark) {
/*  699 */     for (FlatIconColors c : FlatIconColors.values()) {
/*  700 */       if (c.light == (!dark) || c.dark == dark)
/*  701 */         defaults.put(c.key, new ColorUIResource(c.rgb)); 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void putAATextInfo(UIDefaults defaults) {
/*  706 */     if (SystemInfo.isMacOS && SystemInfo.isJetBrainsJVM) {
/*      */ 
/*      */ 
/*      */       
/*  710 */       defaults.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
/*  711 */     } else if (SystemInfo.isJava_9_orLater) {
/*  712 */       Object desktopHints = Toolkit.getDefaultToolkit().getDesktopProperty("awt.font.desktophints");
/*  713 */       if (desktopHints == null)
/*  714 */         desktopHints = fallbackAATextInfo(); 
/*  715 */       if (desktopHints instanceof Map) {
/*      */         
/*  717 */         Map<Object, Object> hints = (Map<Object, Object>)desktopHints;
/*  718 */         Object aaHint = hints.get(RenderingHints.KEY_TEXT_ANTIALIASING);
/*  719 */         if (aaHint != null && aaHint != RenderingHints.VALUE_TEXT_ANTIALIAS_OFF && aaHint != RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT) {
/*      */ 
/*      */ 
/*      */           
/*  723 */           defaults.put(RenderingHints.KEY_TEXT_ANTIALIASING, aaHint);
/*  724 */           defaults.put(RenderingHints.KEY_TEXT_LCD_CONTRAST, hints
/*  725 */               .get(RenderingHints.KEY_TEXT_LCD_CONTRAST));
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */ 
/*      */       
/*      */       try {
/*      */         
/*  733 */         Object key = Class.forName("sun.swing.SwingUtilities2").getField("AA_TEXT_PROPERTY_KEY").get(null);
/*      */ 
/*      */         
/*  736 */         Object value = Class.forName("sun.swing.SwingUtilities2$AATextInfo").getMethod("getAATextInfo", new Class[] { boolean.class }).invoke(null, new Object[] { Boolean.valueOf(true) });
/*  737 */         if (value == null)
/*  738 */           value = fallbackAATextInfo(); 
/*  739 */         defaults.put(key, value);
/*  740 */       } catch (Exception ex) {
/*  741 */         LoggingFacade.INSTANCE.logSevere(null, ex);
/*  742 */         throw new RuntimeException(ex);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private Object fallbackAATextInfo() {
/*  749 */     if (System.getProperty("awt.useSystemAAFontSettings") != null) {
/*  750 */       return null;
/*      */     }
/*  752 */     Object aaHint = null;
/*  753 */     Integer lcdContrastHint = null;
/*      */     
/*  755 */     if (SystemInfo.isLinux) {
/*      */       
/*  757 */       Toolkit toolkit = Toolkit.getDefaultToolkit();
/*  758 */       if (toolkit.getDesktopProperty("gnome.Xft/Antialias") == null && toolkit
/*  759 */         .getDesktopProperty("fontconfig/Antialias") == null)
/*      */       {
/*      */ 
/*      */         
/*  763 */         aaHint = RenderingHints.VALUE_TEXT_ANTIALIAS_ON;
/*      */       }
/*      */     } 
/*      */     
/*  767 */     if (aaHint == null) {
/*  768 */       return null;
/*      */     }
/*  770 */     if (SystemInfo.isJava_9_orLater) {
/*  771 */       Map<Object, Object> hints = new HashMap<>();
/*  772 */       hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, aaHint);
/*  773 */       hints.put(RenderingHints.KEY_TEXT_LCD_CONTRAST, lcdContrastHint);
/*  774 */       return hints;
/*      */     } 
/*      */     
/*      */     try {
/*  778 */       return Class.forName("sun.swing.SwingUtilities2$AATextInfo")
/*  779 */         .getConstructor(new Class[] { Object.class, Integer.class
/*  780 */           }).newInstance(new Object[] { aaHint, lcdContrastHint });
/*  781 */     } catch (Exception ex) {
/*  782 */       LoggingFacade.INSTANCE.logSevere(null, ex);
/*  783 */       throw new RuntimeException(ex);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void putDefaults(UIDefaults defaults, Object value, String... keys) {
/*  789 */     for (String key : keys)
/*  790 */       defaults.put(key, value); 
/*      */   }
/*      */   
/*      */   static List<Object> getCustomDefaultsSources() {
/*  794 */     return customDefaultsSources;
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
/*      */   public static void registerCustomDefaultsSource(String packageName) {
/*  820 */     registerCustomDefaultsSource(packageName, (ClassLoader)null);
/*      */   }
/*      */   
/*      */   public static void unregisterCustomDefaultsSource(String packageName) {
/*  824 */     unregisterCustomDefaultsSource(packageName, (ClassLoader)null);
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
/*      */   public static void registerCustomDefaultsSource(String packageName, ClassLoader classLoader) {
/*  836 */     if (customDefaultsSources == null)
/*  837 */       customDefaultsSources = new ArrayList(); 
/*  838 */     customDefaultsSources.add(packageName);
/*  839 */     customDefaultsSources.add(classLoader);
/*      */   }
/*      */   
/*      */   public static void unregisterCustomDefaultsSource(String packageName, ClassLoader classLoader) {
/*  843 */     if (customDefaultsSources == null) {
/*      */       return;
/*      */     }
/*  846 */     int size = customDefaultsSources.size();
/*  847 */     for (int i = 0; i < size - 1; i++) {
/*  848 */       Object source = customDefaultsSources.get(i);
/*  849 */       if (packageName.equals(source) && customDefaultsSources.get(i + 1) == classLoader) {
/*  850 */         customDefaultsSources.remove(i + 1);
/*  851 */         customDefaultsSources.remove(i);
/*      */         break;
/*      */       } 
/*      */     } 
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
/*      */ 
/*      */ 
/*      */   
/*      */   public static void registerCustomDefaultsSource(URL packageUrl) {
/*  870 */     if (customDefaultsSources == null)
/*  871 */       customDefaultsSources = new ArrayList(); 
/*  872 */     customDefaultsSources.add(packageUrl);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void unregisterCustomDefaultsSource(URL packageUrl) {
/*  877 */     if (customDefaultsSources == null) {
/*      */       return;
/*      */     }
/*  880 */     customDefaultsSources.remove(packageUrl);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void registerCustomDefaultsSource(File folder) {
/*  891 */     if (customDefaultsSources == null)
/*  892 */       customDefaultsSources = new ArrayList(); 
/*  893 */     customDefaultsSources.add(folder);
/*      */   }
/*      */   
/*      */   public static void unregisterCustomDefaultsSource(File folder) {
/*  897 */     if (customDefaultsSources == null) {
/*      */       return;
/*      */     }
/*  900 */     customDefaultsSources.remove(folder);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map<String, String> getGlobalExtraDefaults() {
/*  909 */     return globalExtraDefaults;
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
/*      */   public static void setGlobalExtraDefaults(Map<String, String> globalExtraDefaults) {
/*  932 */     FlatLaf.globalExtraDefaults = globalExtraDefaults;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Map<String, String> getExtraDefaults() {
/*  941 */     return this.extraDefaults;
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
/*      */   public void setExtraDefaults(Map<String, String> extraDefaults) {
/*  965 */     this.extraDefaults = extraDefaults;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object parseDefaultsValue(String key, String value, Class<?> valueType) throws IllegalArgumentException {
/*  984 */     value = UIDefaultsLoader.resolveValueFromUIManager(value);
/*      */ 
/*      */     
/*  987 */     Object val = UIDefaultsLoader.parseValue(key, value, valueType, null, v -> UIDefaultsLoader.resolveValueFromUIManager(v), 
/*  988 */         Collections.emptyList());
/*      */ 
/*      */     
/*  991 */     if (val instanceof UIDefaults.LazyValue) {
/*  992 */       val = ((UIDefaults.LazyValue)val).createValue(null);
/*  993 */     } else if (val instanceof UIDefaults.ActiveValue) {
/*  994 */       val = ((UIDefaults.ActiveValue)val).createValue(null);
/*      */     } 
/*  996 */     return val;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Function<String, Color> getSystemColorGetter() {
/* 1005 */     return systemColorGetter;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setSystemColorGetter(Function<String, Color> systemColorGetter) {
/* 1026 */     FlatLaf.systemColorGetter = systemColorGetter;
/*      */   }
/*      */   
/*      */   private static void reSetLookAndFeel() {
/* 1030 */     EventQueue.invokeLater(() -> {
/*      */           LookAndFeel lookAndFeel = UIManager.getLookAndFeel();
/*      */           
/*      */           try {
/*      */             UIManager.setLookAndFeel(lookAndFeel);
/*      */             
/*      */             PropertyChangeEvent e = new PropertyChangeEvent(UIManager.class, "lookAndFeel", lookAndFeel, lookAndFeel);
/*      */             
/*      */             for (PropertyChangeListener l : UIManager.getPropertyChangeListeners()) {
/*      */               l.propertyChange(e);
/*      */             }
/*      */             
/*      */             updateUI();
/* 1043 */           } catch (UnsupportedLookAndFeelException ex) {
/*      */             LoggingFacade.INSTANCE.logSevere("FlatLaf: Failed to reinitialize look and feel '" + lookAndFeel.getClass().getName() + "'.", ex);
/*      */           } 
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void updateUI() {
/* 1054 */     for (Window w : Window.getWindows()) {
/* 1055 */       SwingUtilities.updateComponentTreeUI(w);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void updateUILater() {
/* 1062 */     synchronized (FlatLaf.class) {
/* 1063 */       if (updateUIPending) {
/*      */         return;
/*      */       }
/* 1066 */       updateUIPending = true;
/*      */     } 
/*      */     
/* 1069 */     EventQueue.invokeLater(() -> {
/*      */           updateUI();
/*      */           synchronized (FlatLaf.class) {
/*      */             updateUIPending = false;
/*      */           } 
/*      */         });
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
/*      */   
/*      */   public static boolean supportsNativeWindowDecorations() {
/* 1088 */     return (SystemInfo.isWindows_10_orLater && FlatNativeWindowBorder.isSupported());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isUseNativeWindowDecorations() {
/* 1097 */     return UIManager.getBoolean("TitlePane.useWindowDecorations");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setUseNativeWindowDecorations(boolean enabled) {
/* 1108 */     UIManager.put("TitlePane.useWindowDecorations", Boolean.valueOf(enabled));
/*      */     
/* 1110 */     if (!(UIManager.getLookAndFeel() instanceof FlatLaf)) {
/*      */       return;
/*      */     }
/*      */     
/* 1114 */     for (Window w : Window.getWindows()) {
/* 1115 */       if (isDisplayableFrameOrDialog(w)) {
/* 1116 */         FlatRootPaneUI.updateNativeWindowBorder(((RootPaneContainer)w).getRootPane());
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void revalidateAndRepaintAllFramesAndDialogs() {
/* 1128 */     for (Window w : Window.getWindows()) {
/* 1129 */       if (isDisplayableFrameOrDialog(w)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1135 */         JMenuBar menuBar = (w instanceof JFrame) ? ((JFrame)w).getJMenuBar() : ((w instanceof JDialog) ? ((JDialog)w).getJMenuBar() : null);
/* 1136 */         if (menuBar != null) {
/* 1137 */           menuBar.revalidate();
/*      */         }
/* 1139 */         w.revalidate();
/* 1140 */         w.repaint();
/*      */       } 
/*      */     } 
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
/*      */   public static void repaintAllFramesAndDialogs() {
/* 1154 */     for (Window w : Window.getWindows()) {
/* 1155 */       if (isDisplayableFrameOrDialog(w))
/* 1156 */         w.repaint(); 
/*      */     } 
/*      */   }
/*      */   
/*      */   private static boolean isDisplayableFrameOrDialog(Window w) {
/* 1161 */     return (w.isDisplayable() && (w instanceof JFrame || w instanceof JDialog));
/*      */   }
/*      */   
/*      */   public static boolean isShowMnemonics() {
/* 1165 */     return MnemonicHandler.isShowMnemonics();
/*      */   }
/*      */   
/*      */   public static void showMnemonics(Component c) {
/* 1169 */     MnemonicHandler.showMnemonics(true, c);
/*      */   }
/*      */   
/*      */   public static void hideMnemonics() {
/* 1173 */     MnemonicHandler.showMnemonics(false, null);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean equals(Object obj) {
/* 1179 */     return super.equals(obj);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final int hashCode() {
/* 1185 */     return super.hashCode();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void registerUIDefaultsGetter(Function<Object, Object> uiDefaultsGetter) {
/* 1203 */     if (this.uiDefaultsGetters == null) {
/* 1204 */       this.uiDefaultsGetters = new ArrayList<>();
/*      */     }
/* 1206 */     this.uiDefaultsGetters.remove(uiDefaultsGetter);
/* 1207 */     this.uiDefaultsGetters.add(uiDefaultsGetter);
/*      */ 
/*      */     
/* 1210 */     FlatUIUtils.setUseSharedUIs(false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unregisterUIDefaultsGetter(Function<Object, Object> uiDefaultsGetter) {
/* 1221 */     if (this.uiDefaultsGetters == null) {
/*      */       return;
/*      */     }
/* 1224 */     this.uiDefaultsGetters.remove(uiDefaultsGetter);
/*      */ 
/*      */     
/* 1227 */     if (this.uiDefaultsGetters.isEmpty()) {
/* 1228 */       FlatUIUtils.setUseSharedUIs(true);
/*      */     }
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
/*      */   public static void runWithUIDefaultsGetter(Function<Object, Object> uiDefaultsGetter, Runnable runnable) {
/* 1263 */     LookAndFeel laf = UIManager.getLookAndFeel();
/* 1264 */     if (laf instanceof FlatLaf) {
/* 1265 */       ((FlatLaf)laf).registerUIDefaultsGetter(uiDefaultsGetter);
/*      */       try {
/* 1267 */         runnable.run();
/*      */       } finally {
/* 1269 */         ((FlatLaf)laf).unregisterUIDefaultsGetter(uiDefaultsGetter);
/*      */       } 
/*      */     } else {
/* 1272 */       runnable.run();
/*      */     } 
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
/* 1284 */   public static final Object NULL_VALUE = new Object();
/*      */ 
/*      */   
/*      */   private static boolean getUIMethodInitialized;
/*      */ 
/*      */   
/*      */   private static MethodHandle getUIMethod;
/*      */ 
/*      */   
/*      */   public static Map<String, Class<?>> getStyleableInfos(JComponent c) {
/* 1294 */     FlatStylingSupport.StyleableUI ui = getStyleableUI(c);
/* 1295 */     return (ui != null) ? ui.getStyleableInfos(c) : null;
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
/*      */   public static <T> T getStyleableValue(JComponent c, String key) {
/* 1307 */     FlatStylingSupport.StyleableUI ui = getStyleableUI(c);
/* 1308 */     return (ui != null) ? (T)ui.getStyleableValue(c, key) : null;
/*      */   }
/*      */   
/*      */   private static FlatStylingSupport.StyleableUI getStyleableUI(JComponent c) {
/* 1312 */     if (!getUIMethodInitialized) {
/* 1313 */       getUIMethodInitialized = true;
/*      */       
/* 1315 */       if (SystemInfo.isJava_9_orLater) {
/*      */         
/*      */         try {
/* 1318 */           getUIMethod = MethodHandles.lookup().findVirtual(JComponent.class, "getUI", 
/* 1319 */               MethodType.methodType(ComponentUI.class));
/* 1320 */         } catch (Exception exception) {}
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/*      */       Object ui;
/*      */       
/* 1328 */       if (getUIMethod != null) {
/* 1329 */         ui = getUIMethod.invoke(c);
/*      */       } else {
/* 1331 */         ui = c.getClass().getMethod("getUI", new Class[0]).invoke(c, new Object[0]);
/* 1332 */       }  return (ui instanceof FlatStylingSupport.StyleableUI) ? (FlatStylingSupport.StyleableUI)ui : null;
/* 1333 */     } catch (Throwable ex) {
/*      */       
/* 1335 */       return null;
/*      */     } 
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
/*      */   public static String getPreferredFontFamily() {
/* 1348 */     return preferredFontFamily;
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
/*      */   public static void setPreferredFontFamily(String preferredFontFamily) {
/* 1360 */     FlatLaf.preferredFontFamily = preferredFontFamily;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getPreferredLightFontFamily() {
/* 1369 */     return preferredLightFontFamily;
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
/*      */   public static void setPreferredLightFontFamily(String preferredLightFontFamily) {
/* 1381 */     FlatLaf.preferredLightFontFamily = preferredLightFontFamily;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getPreferredSemiboldFontFamily() {
/* 1390 */     return preferredSemiboldFontFamily;
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
/*      */   public static void setPreferredSemiboldFontFamily(String preferredSemiboldFontFamily) {
/* 1402 */     FlatLaf.preferredSemiboldFontFamily = preferredSemiboldFontFamily;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getPreferredMonospacedFontFamily() {
/* 1411 */     return preferredMonospacedFontFamily;
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
/*      */   public static void setPreferredMonospacedFontFamily(String preferredMonospacedFontFamily) {
/* 1423 */     FlatLaf.preferredMonospacedFontFamily = preferredMonospacedFontFamily;
/*      */   }
/*      */   
/*      */   public abstract boolean isDark();
/*      */   
/*      */   private class FlatUIDefaults
/*      */     extends UIDefaults
/*      */   {
/*      */     FlatUIDefaults(int initialCapacity, float loadFactor) {
/* 1432 */       super(initialCapacity, loadFactor);
/*      */     }
/*      */ 
/*      */     
/*      */     public Object get(Object key) {
/* 1437 */       Object value = getValue(key);
/* 1438 */       return (value != null) ? ((value != FlatLaf.NULL_VALUE) ? value : null) : super.get(key);
/*      */     }
/*      */ 
/*      */     
/*      */     public Object get(Object key, Locale l) {
/* 1443 */       Object value = getValue(key);
/* 1444 */       return (value != null) ? ((value != FlatLaf.NULL_VALUE) ? value : null) : super.get(key, l);
/*      */     }
/*      */ 
/*      */     
/*      */     private Object getValue(Object key) {
/* 1449 */       List<Function<Object, Object>> uiDefaultsGetters = FlatLaf.this.uiDefaultsGetters;
/*      */       
/* 1451 */       if (uiDefaultsGetters == null) {
/* 1452 */         return null;
/*      */       }
/* 1454 */       for (int i = uiDefaultsGetters.size() - 1; i >= 0; i--) {
/* 1455 */         Object value = ((Function)uiDefaultsGetters.get(i)).apply(key);
/* 1456 */         if (value != null) {
/* 1457 */           return value;
/*      */         }
/*      */       } 
/* 1460 */       return null;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class ActiveFont
/*      */     implements UIDefaults.ActiveValue
/*      */   {
/*      */     private final String baseFontKey;
/*      */ 
/*      */     
/*      */     private final List<String> families;
/*      */ 
/*      */     
/*      */     private final int style;
/*      */ 
/*      */     
/*      */     private final int styleChange;
/*      */ 
/*      */     
/*      */     private final int absoluteSize;
/*      */     
/*      */     private final int relativeSize;
/*      */     
/*      */     private final float scaleSize;
/*      */     
/*      */     private FontUIResource font;
/*      */     
/*      */     private Font lastBaseFont;
/*      */     
/*      */     private boolean inCreateValue;
/*      */ 
/*      */     
/*      */     ActiveFont(String baseFontKey, List<String> families, int style, int styleChange, int absoluteSize, int relativeSize, float scaleSize) {
/* 1495 */       this.baseFontKey = baseFontKey;
/* 1496 */       this.families = families;
/* 1497 */       this.style = style;
/* 1498 */       this.styleChange = styleChange;
/* 1499 */       this.absoluteSize = absoluteSize;
/* 1500 */       this.relativeSize = relativeSize;
/* 1501 */       this.scaleSize = scaleSize;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public synchronized Object createValue(UIDefaults table) {
/* 1507 */       if (this.inCreateValue) {
/* 1508 */         throw new IllegalStateException("FlatLaf: endless recursion in font");
/*      */       }
/* 1510 */       Font baseFont = null;
/*      */       
/* 1512 */       this.inCreateValue = true;
/*      */       try {
/* 1514 */         if (this.baseFontKey != null) {
/* 1515 */           baseFont = (Font)UIDefaultsLoader.lazyUIManagerGet(this.baseFontKey);
/*      */         }
/* 1517 */         if (baseFont == null) {
/* 1518 */           baseFont = UIManager.getFont("defaultFont");
/*      */         }
/*      */         
/* 1521 */         if (baseFont == null)
/* 1522 */           baseFont = UIManager.getFont("Label.font"); 
/*      */       } finally {
/* 1524 */         this.inCreateValue = false;
/*      */       } 
/*      */       
/* 1527 */       if (this.lastBaseFont != baseFont) {
/* 1528 */         this.lastBaseFont = baseFont;
/*      */         
/* 1530 */         this.font = derive(baseFont, fontSize -> UIScale.scale(fontSize));
/*      */       } 
/*      */       
/* 1533 */       return this.font;
/*      */     }
/*      */     
/*      */     FontUIResource derive(Font baseFont, IntUnaryOperator scale) {
/* 1537 */       int baseStyle = baseFont.getStyle();
/* 1538 */       int baseSize = baseFont.getSize();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1545 */       int newStyle = (this.style != -1) ? this.style : ((this.styleChange != 0) ? (baseStyle & (this.styleChange >> 16 & 0xFFFF ^ 0xFFFFFFFF) | this.styleChange & 0xFFFF) : baseStyle);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1554 */       int newSize = (this.absoluteSize > 0) ? scale.applyAsInt(this.absoluteSize) : ((this.relativeSize != 0) ? (baseSize + scale.applyAsInt(this.relativeSize)) : ((this.scaleSize > 0.0F) ? Math.round(baseSize * this.scaleSize) : baseSize));
/* 1555 */       if (newSize <= 0) {
/* 1556 */         newSize = 1;
/*      */       }
/*      */       
/* 1559 */       if (this.families != null && !this.families.isEmpty()) {
/* 1560 */         String preferredFamily = preferredFamily(this.families);
/* 1561 */         if (preferredFamily != null) {
/* 1562 */           Font font = FlatLaf.createCompositeFont(preferredFamily, newStyle, newSize);
/* 1563 */           if (!isFallbackFont(font) || isDialogFamily(preferredFamily)) {
/* 1564 */             return toUIResource(font);
/*      */           }
/*      */         } 
/* 1567 */         for (String family : this.families) {
/* 1568 */           Font font = FlatLaf.createCompositeFont(family, newStyle, newSize);
/* 1569 */           if (!isFallbackFont(font) || isDialogFamily(family)) {
/* 1570 */             return toUIResource(font);
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/* 1575 */       if (newStyle != baseStyle || newSize != baseSize) {
/*      */ 
/*      */         
/* 1578 */         if ("Ubuntu Medium".equalsIgnoreCase(baseFont.getName()) && "Ubuntu Light"
/* 1579 */           .equalsIgnoreCase(baseFont.getFamily())) {
/*      */           
/* 1581 */           Font font = FlatLaf.createCompositeFont("Ubuntu Medium", newStyle, newSize);
/* 1582 */           if (!isFallbackFont(font)) {
/* 1583 */             return toUIResource(font);
/*      */           }
/*      */         } 
/* 1586 */         return toUIResource(baseFont.deriveFont(newStyle, newSize));
/*      */       } 
/* 1588 */       return toUIResource(baseFont);
/*      */     }
/*      */ 
/*      */     
/*      */     private FontUIResource toUIResource(Font font) {
/* 1593 */       return (font instanceof FontUIResource) ? 
/* 1594 */         (FontUIResource)font : 
/* 1595 */         new FontUIResource(font);
/*      */     }
/*      */     
/*      */     private static boolean isFallbackFont(Font font) {
/* 1599 */       return "Dialog".equalsIgnoreCase(font.getFamily());
/*      */     }
/*      */     
/*      */     private static boolean isDialogFamily(String family) {
/* 1603 */       return family.equalsIgnoreCase("Dialog");
/*      */     }
/*      */     
/*      */     private static String preferredFamily(List<String> families) {
/* 1607 */       for (String family : families) {
/* 1608 */         family = family.toLowerCase(Locale.ENGLISH);
/* 1609 */         if (family.endsWith(" light") || family.endsWith("-thin"))
/* 1610 */           return FlatLaf.preferredLightFontFamily; 
/* 1611 */         if (family.endsWith(" semibold") || family.endsWith("-medium"))
/* 1612 */           return FlatLaf.preferredSemiboldFontFamily; 
/* 1613 */         if (family.equals("monospaced"))
/* 1614 */           return FlatLaf.preferredMonospacedFontFamily; 
/*      */       } 
/* 1616 */       return null;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static class ImageIconUIResource
/*      */     extends ImageIcon
/*      */     implements UIResource
/*      */   {
/*      */     ImageIconUIResource(Image image) {
/* 1627 */       super(image);
/*      */     }
/*      */   }
/*      */   
/*      */   public static interface DisabledIconProvider {
/*      */     Icon getDisabledIcon();
/*      */   }
/*      */ }


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\com\formdev\flatlaf\FlatLaf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */