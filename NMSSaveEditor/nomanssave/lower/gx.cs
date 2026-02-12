using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using System.Globalization;

namespace NMSSaveEditor
{

public sealed class gx : gD {
   public static readonly gx qH = new gx("qH", "Standard", "MODELS/COMMON/WEAPONS/MULTITOOL/MULTITOOL.SCENE.MBIN");
   public static readonly gx qI = new gx("qI", "Royal", "MODELS/COMMON/WEAPONS/MULTITOOL/ROYALMULTITOOL.SCENE.MBIN");
   public static readonly gx qJ = new gx("qJ", "Sentinel", "MODELS/COMMON/WEAPONS/MULTITOOL/SENTINELMULTITOOL.SCENE.MBIN");
   public static readonly gx qK = new gx("qK", "Sentinel B", "MODELS/COMMON/WEAPONS/MULTITOOL/SENTINELMULTITOOLB.SCENE.MBIN");
   public static readonly gx qL = new gx("qL", "Switch", "MODELS/COMMON/WEAPONS/MULTITOOL/SWITCHMULTITOOL.SCENE.MBIN");
   public static readonly gx qM = new gx("qM", "Staff", "MODELS/COMMON/WEAPONS/MULTITOOL/STAFFMULTITOOL.SCENE.MBIN");
   public static readonly gx qN = new gx("qN", "Staff NPC", "MODELS/COMMON/WEAPONS/MULTITOOL/STAFFNPCMULTITOOL.SCENE.MBIN");
   public static readonly gx qO = new gx("qO", "Atlas", "MODELS/COMMON/WEAPONS/MULTITOOL/ATLASMULTITOOL.SCENE.MBIN");
   public static readonly gx qP = new gx("qP", "Atlas Scepter", "MODELS/COMMON/WEAPONS/MULTITOOL/STAFFMULTITOOLATLAS.SCENE.MBIN");

   private int _ordinal;
   private string _name;
   private string name;
   private string filename;

   private gx(string __name, string var3, string var4) {
      this._ordinal = _nextOrdinal++;
      this._name = __name;
      this.name = var3;
      this.filename = var4;
   }

   private static int _nextOrdinal = 0;
   private static readonly gx[] _values = new gx[] { qH, qI, qJ, qK, qL, qM, qN, qO, qP };
   public static gx[] values() { return _values; }
   public static gx valueOf(string n) { return _values.FirstOrDefault(v => v._name == n); }
   public int ordinal() { return _ordinal; }
   public string name() { return _name; }
   public override string ToString() { return _name; }

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
         for(int var1 = 0; var1 < values().Length; ++var1) {
            if (var0.Equals(values()[var1].filename)) {
               return values()[var1];
            }
         }

         return null;
      }
   }
}
}
