package nomanssave;

public enum gr {
   pf("Combat", false),
   pg("Exploration", false),
   ph("Mining", false),
   pi("Diplomacy", false),
   pj("Support", false),
   pk("Normandy", true),
   pl("DeepSpace", true),
   pm("DeepSpaceCommon", true),
   pn("Pirate", false),
   po("GhostShip", true);

   private String name;
   private boolean special;

   private gr(String var3, boolean var4) {
      this.name = var3;
      this.special = var4;
   }

   public boolean isSpecial() {
      return this.special;
   }

   public String toString() {
      return this.name;
   }

   public static gr an(String var0) {
      for(int var1 = 0; var1 < values().length; ++var1) {
         if (var0.equalsIgnoreCase(values()[var1].name)) {
            return values()[var1];
         }
      }

      return null;
   }
}
