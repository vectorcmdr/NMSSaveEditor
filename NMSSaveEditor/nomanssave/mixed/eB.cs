using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public sealed class eB {
   public static eB[] Values() { return new eB[] { jN, jO, jP, jQ }; }
   public static eB valueOf(string name) { foreach (var v in Values()) if (v.ToString() == name) return v; return null; }

   public static readonly eB jN = new eB("Technology");
   public static readonly eB jO = new eB("Product");
   public static readonly eB jP = new eB("Substance");
   public static readonly eB jQ = new eB("TechBox");


   public string name;

   public eB(string var3) {
      this.name = var3;
   }

   public string toString() {
      return this.name;
   }
}

}
