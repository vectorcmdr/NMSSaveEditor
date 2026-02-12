using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE

public sealed class go : gD {
   public static readonly go oL = new go("Tiny", "MODELS/COMMON/SPACECRAFT/INDUSTRIAL/FREIGHTERTINY_PROC.SCENE.MBIN");
   public static readonly go oM = new go("Small", "MODELS/COMMON/SPACECRAFT/INDUSTRIAL/FREIGHTERSMALL_PROC.SCENE.MBIN");
   public static readonly go oN = new go("Normal", "MODELS/COMMON/SPACECRAFT/INDUSTRIAL/FREIGHTER_PROC.SCENE.MBIN");
   public static readonly go oO = new go("Capital", "MODELS/COMMON/SPACECRAFT/INDUSTRIAL/CAPITALFREIGHTER_PROC.SCENE.MBIN");
   public static readonly go oP = new go("Pirate", "MODELS/COMMON/SPACECRAFT/INDUSTRIAL/PIRATEFREIGHTER.SCENE.MBIN");

   public string name;
   public string filename;

   public go(string var3, string var4) {
      this.name = var3;
      this.filename = var4;
   }

   public string K() {
      return this.filename;
   }

   public string toString() {
      return this.name;
   }

   public static go al(string var0) {
      for(int var1 = 0; var1 < Values().Length; ++var1) {
         if (var0.Equals(Values()[var1].filename)) {
            return Values()[var1];
         }
      }

      return null;
   }
}


#else

public class go
{
   public go() { }
   public go(params object[] args) { }
   public static readonly go oL = default;
   public static readonly go oM = default;
   public static readonly go oN = default;
   public static readonly go oO = default;
   public static readonly go oP = default;
   public string name = "";
   public string filename = "";
   public string K() { return ""; }
   public string toString() { return ""; }
}

#endif

}
