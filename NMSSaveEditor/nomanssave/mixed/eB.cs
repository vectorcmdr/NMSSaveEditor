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

public sealed class eB {
   public static readonly eB jN = new eB("jN", "Technology");
   public static readonly eB jO = new eB("jO", "Product");
   public static readonly eB jP = new eB("jP", "Substance");
   public static readonly eB jQ = new eB("jQ", "TechBox");

   public int _ordinal;
   public string _name;
   public string displayName;

   public eB(string __name, string var3) {
      this._ordinal = _nextOrdinal++;
      this._name = __name;
      this.displayName = var3;
   }

   public static int _nextOrdinal = 0;
   public static readonly eB[] _values = new eB[] { jN, jO, jP, jQ };
   public static eB[] values() { return _values; }
   public static eB valueOf(string n) { return _values.FirstOrDefault(v => v._name == n); }
   public int ordinal() { return _ordinal; }
   public string name() { return _name; }
   public override string ToString() { return _name; }

   public string toString() {
      return this.displayName;
   }
}
}
