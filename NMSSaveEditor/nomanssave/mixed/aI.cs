using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public sealed class aI {
   public static readonly aI cN = new aI("Light");
   public static readonly aI cO = new aI("Dark");
   public static readonly aI cP = new aI("IntelliJ");
   public static readonly aI cQ = new aI("Darcula");
   public static readonly aI cR = new aI("macOS Light");
   public static readonly aI cS = new aI("macOS Dark");


   public string cT;

   public aI(string var3) {
      this.cT = var3;
   }

   public string toString() {
      return this.cT;
   }
}

}
