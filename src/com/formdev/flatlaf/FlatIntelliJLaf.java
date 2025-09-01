package com.formdev.flatlaf;

public class FlatIntelliJLaf extends FlatLightLaf {
   public static final String NAME = "FlatLaf IntelliJ";

   public static boolean setup() {
      return setup(new FlatIntelliJLaf());
   }

   /** @deprecated */
   @Deprecated
   public static boolean install() {
      return setup();
   }

   public static void installLafInfo() {
      installLafInfo("FlatLaf IntelliJ", FlatIntelliJLaf.class);
   }

   public String getName() {
      return "FlatLaf IntelliJ";
   }

   public String getDescription() {
      return "FlatLaf IntelliJ Look and Feel";
   }
}
