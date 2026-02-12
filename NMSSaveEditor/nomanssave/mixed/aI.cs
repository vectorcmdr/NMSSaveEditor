using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public enum aI {
   cN("Light"),
   cO("Dark"),
   cP("IntelliJ"),
   cQ("Darcula"),
   cR("macOS Light"),
   cS("macOS Dark");

   string cT;

   private aI(string var3) {
      this.cT = var3;
   }

   public string toString() {
      return this.cT;
   }
}

}
