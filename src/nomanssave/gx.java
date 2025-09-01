package nomanssave;

public enum gx implements gD {
   qH("Standard", "MODELS/COMMON/WEAPONS/MULTITOOL/MULTITOOL.SCENE.MBIN"),
   qI("Royal", "MODELS/COMMON/WEAPONS/MULTITOOL/ROYALMULTITOOL.SCENE.MBIN"),
   qJ("Sentinel", "MODELS/COMMON/WEAPONS/MULTITOOL/SENTINELMULTITOOL.SCENE.MBIN"),
   qK("Sentinel B", "MODELS/COMMON/WEAPONS/MULTITOOL/SENTINELMULTITOOLB.SCENE.MBIN"),
   qL("Switch", "MODELS/COMMON/WEAPONS/MULTITOOL/SWITCHMULTITOOL.SCENE.MBIN"),
   qM("Staff", "MODELS/COMMON/WEAPONS/MULTITOOL/STAFFMULTITOOL.SCENE.MBIN"),
   qN("Staff NPC", "MODELS/COMMON/WEAPONS/MULTITOOL/STAFFNPCMULTITOOL.SCENE.MBIN"),
   qO("Atlas", "MODELS/COMMON/WEAPONS/MULTITOOL/ATLASMULTITOOL.SCENE.MBIN"),
   qP("Atlas Scepter", "MODELS/COMMON/WEAPONS/MULTITOOL/STAFFMULTITOOLATLAS.SCENE.MBIN");

   private String name;
   private String filename;

   private gx(String var3, String var4) {
      this.name = var3;
      this.filename = var4;
   }

   public String K() {
      return this.filename;
   }

   public String toString() {
      return this.name;
   }

   public static gx ar(String var0) {
      if (var0 == null) {
         return null;
      } else {
         for(int var1 = 0; var1 < values().length; ++var1) {
            if (var0.equals(values()[var1].filename)) {
               return values()[var1];
            }
         }

         return null;
      }
   }
}
