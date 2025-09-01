package com.formdev.flatlaf.themes;

import com.formdev.flatlaf.FlatLightLaf;

public class FlatMacLightLaf extends FlatLightLaf {
   public static final String NAME = "FlatLaf macOS Light";

   public static boolean setup() {
      return setup(new FlatMacLightLaf());
   }

   public static void installLafInfo() {
      installLafInfo("FlatLaf macOS Light", FlatMacLightLaf.class);
   }

   public String getName() {
      return "FlatLaf macOS Light";
   }

   public String getDescription() {
      return "FlatLaf macOS Light Look and Feel";
   }

   public boolean isDark() {
      return false;
   }
}
