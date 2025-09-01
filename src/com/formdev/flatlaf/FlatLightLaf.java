package com.formdev.flatlaf;

public class FlatLightLaf extends FlatLaf {
   public static final String NAME = "FlatLaf Light";

   public static boolean setup() {
      return setup(new FlatLightLaf());
   }

   /** @deprecated */
   @Deprecated
   public static boolean install() {
      return setup();
   }

   public static void installLafInfo() {
      installLafInfo("FlatLaf Light", FlatLightLaf.class);
   }

   public String getName() {
      return "FlatLaf Light";
   }

   public String getDescription() {
      return "FlatLaf Light Look and Feel";
   }

   public boolean isDark() {
      return false;
   }
}
