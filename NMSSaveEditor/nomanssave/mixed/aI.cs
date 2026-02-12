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

public sealed class aI {
   public static readonly aI cN = new aI("cN", "Light");
   public static readonly aI cO = new aI("cO", "Dark");
   public static readonly aI cP = new aI("cP", "IntelliJ");
   public static readonly aI cQ = new aI("cQ", "Darcula");
   public static readonly aI cR = new aI("cR", "macOS Light");
   public static readonly aI cS = new aI("cS", "macOS Dark");

   public int _ordinal;
   public string _name;
   public readonly string cT;

   public aI(string __name, string var3) {
      this._ordinal = _nextOrdinal++;
      this._name = __name;
      this.cT = var3;
   }

   public static int _nextOrdinal = 0;
   public static readonly aI[] _values = new aI[] { cN, cO, cP, cQ, cR, cS };
   public static aI[] values() { return _values; }
   public static aI valueOf(string n) { return _values.FirstOrDefault(v => v._name == n); }
   public int ordinal() { return _ordinal; }
   public string name() { return _name; }
   public override string ToString() { return _name; }

   public string toString() {
      return this.cT;
   }
}
}
