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

public sealed class gq {
   public static readonly gq oS = new gq("oS", "Combat");
   public static readonly gq oT = new gq("oT", "Exploration");
   public static readonly gq oU = new gq("oU", "Industry");
   public static readonly gq oV = new gq("oV", "Trading");
   public static readonly gq oW = new gq("oW", "Cost Per Warp");
   public static readonly gq oX = new gq("oX", "Expedition Fuel Cost", -1);
   public static readonly gq oY = new gq("oY", "Expedition Duration", -1);
   public static readonly gq oZ = new gq("oZ", "Loot");
   public static readonly gq pa = new gq("pa", "Repair");
   public static readonly gq pb = new gq("pb", "Damage Reduction");
   public static readonly gq pc = new gq("pc", "Stealth");

   public int _ordinal;
   public string _name;
   public string displayName;
   public int pd;

   public gq(string __name, string var3) : this(__name, var3, 1) {
   }

   public gq(string __name, string var3, int var4) {
      this._ordinal = _nextOrdinal++;
      this._name = __name;
      this.displayName = var3;
      this.pd = var4;
   }

   public static int _nextOrdinal = 0;
   public static readonly gq[] _values = new gq[] { oS, oT, oU, oV, oW, oX, oY, oZ, pa, pb, pc };
   public static gq[] values() { return _values; }
   public static gq valueOf(string n) { return _values.FirstOrDefault(v => v._name == n); }
   public int ordinal() { return _ordinal; }
   public string name() { return _name; }
   public override string ToString() { return _name; }

   public int di() {
      return this.pd;
   }

   public string toString() {
      return this.displayName;
   }

   public static gq[] Values() { return new gq[] { oS, oT, oU, oV, oW, oX, oY, oZ, pa, pb, pc, valueOf }; }
}
}
