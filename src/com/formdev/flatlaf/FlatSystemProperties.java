package com.formdev.flatlaf;

public interface FlatSystemProperties {
   String UI_SCALE = "flatlaf.uiScale";
   String UI_SCALE_ENABLED = "flatlaf.uiScale.enabled";
   String UI_SCALE_ALLOW_SCALE_DOWN = "flatlaf.uiScale.allowScaleDown";
   String USE_UBUNTU_FONT = "flatlaf.useUbuntuFont";
   String USE_WINDOW_DECORATIONS = "flatlaf.useWindowDecorations";
   String USE_JETBRAINS_CUSTOM_DECORATIONS = "flatlaf.useJetBrainsCustomDecorations";
   String MENUBAR_EMBEDDED = "flatlaf.menuBarEmbedded";
   String ANIMATION = "flatlaf.animation";
   String USE_TEXT_Y_CORRECTION = "flatlaf.useTextYCorrection";
   String UPDATE_UI_ON_SYSTEM_FONT_CHANGE = "flatlaf.updateUIOnSystemFontChange";
   String NATIVE_LIBRARY_PATH = "flatlaf.nativeLibraryPath";

   static boolean getBoolean(String key, boolean defaultValue) {
      String value = System.getProperty(key);
      return value != null ? Boolean.parseBoolean(value) : defaultValue;
   }

   static Boolean getBooleanStrict(String key, Boolean defaultValue) {
      String value = System.getProperty(key);
      if ("true".equalsIgnoreCase(value)) {
         return Boolean.TRUE;
      } else {
         return "false".equalsIgnoreCase(value) ? Boolean.FALSE : defaultValue;
      }
   }
}
