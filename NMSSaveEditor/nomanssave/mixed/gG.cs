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

public sealed class gG {
   public static readonly gG rg = new gG("rg", 200);
   public static readonly gG rh = new gG("rh", 100);
   public static readonly gG ri = new gG("ri", 1000000);
   public static readonly gG rj = new gG("rj", 1000000);
   public static readonly gG rk = new gG("rk", 100);
   public static readonly gG rl = new gG("rl", 10000000);
   public static readonly gG rm = new gG("rm", 100);

   public int _ordinal;
   public string _name;
   public readonly int rn;

   public gG(string __name, int var3) {
      this._ordinal = _nextOrdinal++;
      this._name = __name;
      this.rn = var3;
   }

   public static int _nextOrdinal = 0;
   public static readonly gG[] _values = new gG[] { rg, rh, ri, rj, rk, rl, rm };
   public static gG[] values() { return _values; }
   public static gG valueOf(string n) { return _values.FirstOrDefault(v => v._name == n); }
   public int ordinal() { return _ordinal; }
   public string name() { return _name; }
   public override string ToString() { return _name; }

   public int dY() {
      return this.rn;
   }
}
}
