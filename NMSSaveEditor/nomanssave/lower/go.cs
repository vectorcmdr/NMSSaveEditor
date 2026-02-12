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

public sealed class go : gD {
   public static readonly go oL = new go("oL", "Tiny", "MODELS/COMMON/SPACECRAFT/INDUSTRIAL/FREIGHTERTINY_PROC.SCENE.MBIN");
   public static readonly go oM = new go("oM", "Small", "MODELS/COMMON/SPACECRAFT/INDUSTRIAL/FREIGHTERSMALL_PROC.SCENE.MBIN");
   public static readonly go oN = new go("oN", "Normal", "MODELS/COMMON/SPACECRAFT/INDUSTRIAL/FREIGHTER_PROC.SCENE.MBIN");
   public static readonly go oO = new go("oO", "Capital", "MODELS/COMMON/SPACECRAFT/INDUSTRIAL/CAPITALFREIGHTER_PROC.SCENE.MBIN");
   public static readonly go oP = new go("oP", "Pirate", "MODELS/COMMON/SPACECRAFT/INDUSTRIAL/PIRATEFREIGHTER.SCENE.MBIN");

   public int _ordinal;
   public string _name;
   public string displayName;
   public string filename;

   public go(string __name, string var3, string var4) {
      this._ordinal = _nextOrdinal++;
      this._name = __name;
      this.displayName = var3;
      this.filename = var4;
   }

   public static int _nextOrdinal = 0;
   public static readonly go[] _values = new go[] { oL, oM, oN, oO, oP };
   public static go[] values() { return _values; }
   public static go valueOf(string n) { return _values.FirstOrDefault(v => v._name == n); }
   public int ordinal() { return _ordinal; }
   public string name() { return _name; }
   public override string ToString() { return _name; }

   public string K() {
      return this.filename;
   }

   public string toString() {
      return this.displayName;
   }

   public static go al(string var0) {
      for(int var1 = 0; var1 < values().Length; ++var1) {
         if (var0.Equals(values()[var1].filename)) {
            return values()[var1];
         }
      }

      return null;
   }
}
}
