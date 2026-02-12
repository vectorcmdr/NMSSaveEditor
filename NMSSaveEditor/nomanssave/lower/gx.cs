using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE

public sealed class gx : gD {
   public static readonly gx qH = new gx("Standard", "MODELS/COMMON/WEAPONS/MULTITOOL/MULTITOOL.SCENE.MBIN");
   public static readonly gx qI = new gx("Royal", "MODELS/COMMON/WEAPONS/MULTITOOL/ROYALMULTITOOL.SCENE.MBIN");
   public static readonly gx qJ = new gx("Sentinel", "MODELS/COMMON/WEAPONS/MULTITOOL/SENTINELMULTITOOL.SCENE.MBIN");
   public static readonly gx qK = new gx("Sentinel B", "MODELS/COMMON/WEAPONS/MULTITOOL/SENTINELMULTITOOLB.SCENE.MBIN");
   public static readonly gx qL = new gx("Switch", "MODELS/COMMON/WEAPONS/MULTITOOL/SWITCHMULTITOOL.SCENE.MBIN");
   public static readonly gx qM = new gx("Staff", "MODELS/COMMON/WEAPONS/MULTITOOL/STAFFMULTITOOL.SCENE.MBIN");
   public static readonly gx qN = new gx("Staff NPC", "MODELS/COMMON/WEAPONS/MULTITOOL/STAFFNPCMULTITOOL.SCENE.MBIN");
   public static readonly gx qO = new gx("Atlas", "MODELS/COMMON/WEAPONS/MULTITOOL/ATLASMULTITOOL.SCENE.MBIN");
   public static readonly gx qP = new gx("Atlas Scepter", "MODELS/COMMON/WEAPONS/MULTITOOL/STAFFMULTITOOLATLAS.SCENE.MBIN");

   public string name;
   public string filename;

   public gx(string var3, string var4) {
      this.name = var3;
      this.filename = var4;
   }

   public string K() {
      return this.filename;
   }

   public string toString() {
      return this.name;
   }

   public static gx ar(string var0) {
      if (var0 == null) {
         return null;
      } else {
         for(int var1 = 0; var1 < Values().Length; ++var1) {
            if (var0.Equals(Values()[var1].filename)) {
               return Values()[var1];
            }
         }

         return null;
      }
   }
}


#else

public class gx
{
   public gx() { }
   public gx(params object[] args) { }
   public static readonly gx qH = default;
   public static readonly gx qI = default;
   public static readonly gx qJ = default;
   public static readonly gx qK = default;
   public static readonly gx qL = default;
   public static readonly gx qM = default;
   public static readonly gx qN = default;
   public static readonly gx qO = default;
   public static readonly gx qP = default;
   public string name = "";
   public string filename = "";
   public string K() { return ""; }
   public string toString() { return ""; }
}

#endif

}
