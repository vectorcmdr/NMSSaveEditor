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

public sealed class eU {
   public static readonly eU kr = new eU("kr", "Gek");
   public static readonly eU ks = new eU("ks", "Vy'keen");
   public static readonly eU kt = new eU("kt", "Korvax");
   public static readonly eU ku = new eU("ku", "Robot");
   public static readonly eU kv = new eU("kv", "Atlas");
   public static readonly eU kw = new eU("kw", "Diplomats");
   public static readonly eU kx = new eU("kx", "Exotics");
   public static readonly eU ky = new eU("ky", "None");
   public static readonly eU kz = new eU("kz", "Autophage");

   private int _ordinal;
   private string _name;
   private string name;

   private eU(string __name, string var3) {
      this._ordinal = _nextOrdinal++;
      this._name = __name;
      this.name = var3;
   }

   private static int _nextOrdinal = 0;
   private static readonly eU[] _values = new eU[] { kr, ks, kt, ku, kv, kw, kx, ky, kz };
   public static eU[] values() { return _values; }
   public static eU valueOf(string n) { return _values.FirstOrDefault(v => v._name == n); }
   public int ordinal() { return _ordinal; }
   public string name() { return _name; }
   public override string ToString() { return _name; }

   public string toString() {
      return this.name;
   }

   public static eU C(string var0) {
      eU[] var4;
      int var3 = (var4 = values()).Length;

      for(int var2 = 0; var2 < var3; ++var2) {
         eU var1 = var4[var2];
         if (var1.name().Equals(var0)) {
            return var1;
         }
      }

      return null;
   }
}
}
