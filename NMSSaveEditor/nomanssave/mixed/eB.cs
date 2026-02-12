using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public sealed class eB {
   public static readonly eB jN = new eB("Technology");
   public static readonly eB jO = new eB("Product");
   public static readonly eB jP = new eB("Substance");
   public static readonly eB jQ = new eB("TechBox");


   private string name;

   private eB(string var3) {
      this.name = var3;
   }

   public string toString() {
      return this.name;
   }
}

}
