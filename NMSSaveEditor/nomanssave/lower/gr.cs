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

public sealed class gr {
   public static readonly gr pf = new gr("pf", "Combat", false);
   public static readonly gr pg = new gr("pg", "Exploration", false);
   public static readonly gr ph = new gr("ph", "Mining", false);
   public static readonly gr pi = new gr("pi", "Diplomacy", false);
   public static readonly gr pj = new gr("pj", "Support", false);
   public static readonly gr pk = new gr("pk", "Normandy", true);
   public static readonly gr pl = new gr("pl", "DeepSpace", true);
   public static readonly gr pm = new gr("pm", "DeepSpaceCommon", true);
   public static readonly gr pn = new gr("pn", "Pirate", false);
   public static readonly gr po = new gr("po", "GhostShip", true);

   public int _ordinal;
   public string _name;
   public string displayName;
   public bool special;

   public gr(string __name, string var3, bool var4) {
      this._ordinal = _nextOrdinal++;
      this._name = __name;
      this.displayName = var3;
      this.special = var4;
   }

   public static int _nextOrdinal = 0;
   public static readonly gr[] _values = new gr[] { pf, pg, ph, pi, pj, pk, pl, pm, pn, po };
   public static gr[] values() { return _values; }
   public static gr valueOf(string n) { return _values.FirstOrDefault(v => v._name == n); }
   public int ordinal() { return _ordinal; }
   public string name() { return _name; }
   public override string ToString() { return _name; }

   public bool isSpecial() {
      return this.special;
   }

   public string toString() {
      return this.displayName;
   }

   public static gr an(string var0) {
      for(int var1 = 0; var1 < values().Length; ++var1) {
         if (var0.Equals(values()[var1].name, StringComparison.OrdinalIgnoreCase)) {
            return values()[var1];
         }
      }
       return null;
   }

   public static gr[] Values() { return new gr[] { pf, pg, ph, pi, pj, pk, pl, pm, pn, po, valueOf, an }; }
}
}
