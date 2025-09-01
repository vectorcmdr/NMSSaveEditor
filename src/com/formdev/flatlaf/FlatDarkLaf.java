package com.formdev.flatlaf;

public class FlatDarkLaf extends FlatLaf {
   public static final String NAME = "FlatLaf Dark";

   public static boolean setup() {
      return setup(new FlatDarkLaf());
   }

   /** @deprecated */
   @Deprecated
   public static boolean install() {
      return setup();
   }

   public static void installLafInfo() {
      installLafInfo("FlatLaf Dark", FlatDarkLaf.class);
   }

   public String getName() {
      return "FlatLaf Dark";
   }

   public String getDescription() {
      return "FlatLaf Dark Look and Feel";
   }

   public boolean isDark() {
      return true;
   }
}
