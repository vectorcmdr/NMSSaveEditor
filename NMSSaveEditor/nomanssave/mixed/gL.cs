using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public sealed class gL : gD {
   public static readonly gL rs = new gL("Hauler", "MODELS/COMMON/SPACECRAFT/DROPSHIPS/DROPSHIP_PROC.SCENE.MBIN", 4);
   public static readonly gL rt = new gL("Explorer", "MODELS/COMMON/SPACECRAFT/SCIENTIFIC/SCIENTIFIC_PROC.SCENE.MBIN", 4);
   public static readonly gL ru = new gL("Shuttle", "MODELS/COMMON/SPACECRAFT/SHUTTLE/SHUTTLE_PROC.SCENE.MBIN", 4);
   public static readonly gL rv = new gL("Fighter", "MODELS/COMMON/SPACECRAFT/FIGHTERS/FIGHTER_PROC.SCENE.MBIN", 4);
   public static readonly gL rw = new gL("Exotic", "MODELS/COMMON/SPACECRAFT/S-CLASS/S-CLASS_PROC.SCENE.MBIN", 4);
   public static readonly gL rx = new gL("Living", "MODELS/COMMON/SPACECRAFT/S-CLASS/BIOPARTS/BIOSHIP_PROC.SCENE.MBIN", 64);
   public static readonly gL ry = new gL("Solar", "MODELS/COMMON/SPACECRAFT/SAILSHIP/SAILSHIP_PROC.SCENE.MBIN", 4);
   public static readonly gL rz = new gL("Utopia Speeder", "MODELS/COMMON/SPACECRAFT/FIGHTERS/VRSPEEDER.SCENE.MBIN", 4);
   public static readonly gL rA = new gL("Golden Vector", "MODELS/COMMON/SPACECRAFT/FIGHTERS/FIGHTERCLASSICGOLD.SCENE.MBIN", 4);
   public static readonly gL rB = new gL("Horizon Vector NX (Switch)", "MODELS/COMMON/SPACECRAFT/FIGHTERS/FIGHTERSPECIALSWITCH.SCENE.MBIN", 4);
   public static readonly gL rC = new gL("Robot", "MODELS/COMMON/SPACECRAFT/SENTINELSHIP/SENTINELSHIP_PROC.SCENE.MBIN", 256);
   public static readonly gL rD = new gL("Starborn Runner", "MODELS/COMMON/SPACECRAFT/FIGHTERS/WRACER.SCENE.MBIN", 4);
   public static readonly gL rE = new gL("Corvette", "MODELS/COMMON/SPACECRAFT/BIGGS/BIGGS.SCENE.MBIN", 4);

   public string name;
   public string filename;
   public int rF;

   public gL(string var3, string var4, int var5) {
      this.name = var3;
      this.filename = var4;
      this.rF = var5;
   }

   public string K() {
      return this.filename;
   }

   public int ea() {
      return this.rF;
   }

   public string toString() {
      return this.name;
   }

   public static gL aw(string var0) {
      for(int var1 = 0; var1 < values().Length; ++var1) {
         if (var0.Equals(values()[var1].filename)) {
            return values()[var1];
         }
      }

      return null;
   }
}

}
