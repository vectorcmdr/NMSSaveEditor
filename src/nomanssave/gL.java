package nomanssave;

public enum gL implements gD {
   rs("Hauler", "MODELS/COMMON/SPACECRAFT/DROPSHIPS/DROPSHIP_PROC.SCENE.MBIN", 4),
   rt("Explorer", "MODELS/COMMON/SPACECRAFT/SCIENTIFIC/SCIENTIFIC_PROC.SCENE.MBIN", 4),
   ru("Shuttle", "MODELS/COMMON/SPACECRAFT/SHUTTLE/SHUTTLE_PROC.SCENE.MBIN", 4),
   rv("Fighter", "MODELS/COMMON/SPACECRAFT/FIGHTERS/FIGHTER_PROC.SCENE.MBIN", 4),
   rw("Exotic", "MODELS/COMMON/SPACECRAFT/S-CLASS/S-CLASS_PROC.SCENE.MBIN", 4),
   rx("Living", "MODELS/COMMON/SPACECRAFT/S-CLASS/BIOPARTS/BIOSHIP_PROC.SCENE.MBIN", 64),
   ry("Solar", "MODELS/COMMON/SPACECRAFT/SAILSHIP/SAILSHIP_PROC.SCENE.MBIN", 4),
   rz("Utopia Speeder", "MODELS/COMMON/SPACECRAFT/FIGHTERS/VRSPEEDER.SCENE.MBIN", 4),
   rA("Golden Vector", "MODELS/COMMON/SPACECRAFT/FIGHTERS/FIGHTERCLASSICGOLD.SCENE.MBIN", 4),
   rB("Horizon Vector NX (Switch)", "MODELS/COMMON/SPACECRAFT/FIGHTERS/FIGHTERSPECIALSWITCH.SCENE.MBIN", 4),
   rC("Robot", "MODELS/COMMON/SPACECRAFT/SENTINELSHIP/SENTINELSHIP_PROC.SCENE.MBIN", 256),
   rD("Starborn Runner", "MODELS/COMMON/SPACECRAFT/FIGHTERS/WRACER.SCENE.MBIN", 4),
   rE("Corvette", "MODELS/COMMON/SPACECRAFT/BIGGS/BIGGS.SCENE.MBIN", 4);

   private String name;
   private String filename;
   private int rF;

   private gL(String var3, String var4, int var5) {
      this.name = var3;
      this.filename = var4;
      this.rF = var5;
   }

   public String K() {
      return this.filename;
   }

   public int ea() {
      return this.rF;
   }

   public String toString() {
      return this.name;
   }

   public static gL aw(String var0) {
      for(int var1 = 0; var1 < values().length; ++var1) {
         if (var0.equals(values()[var1].filename)) {
            return values()[var1];
         }
      }

      return null;
   }
}
