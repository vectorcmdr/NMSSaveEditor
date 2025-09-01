package nomanssave;

public enum go implements gD {
   oL("Tiny", "MODELS/COMMON/SPACECRAFT/INDUSTRIAL/FREIGHTERTINY_PROC.SCENE.MBIN"),
   oM("Small", "MODELS/COMMON/SPACECRAFT/INDUSTRIAL/FREIGHTERSMALL_PROC.SCENE.MBIN"),
   oN("Normal", "MODELS/COMMON/SPACECRAFT/INDUSTRIAL/FREIGHTER_PROC.SCENE.MBIN"),
   oO("Capital", "MODELS/COMMON/SPACECRAFT/INDUSTRIAL/CAPITALFREIGHTER_PROC.SCENE.MBIN"),
   oP("Pirate", "MODELS/COMMON/SPACECRAFT/INDUSTRIAL/PIRATEFREIGHTER.SCENE.MBIN");

   private String name;
   private String filename;

   private go(String var3, String var4) {
      this.name = var3;
      this.filename = var4;
   }

   public String K() {
      return this.filename;
   }

   public String toString() {
      return this.name;
   }

   public static go al(String var0) {
      for(int var1 = 0; var1 < values().length; ++var1) {
         if (var0.equals(values()[var1].filename)) {
            return values()[var1];
         }
      }

      return null;
   }
}
