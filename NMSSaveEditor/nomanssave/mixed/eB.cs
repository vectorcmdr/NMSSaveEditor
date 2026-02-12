using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public enum eB {
   jN("Technology"),
   jO("Product"),
   jP("Substance"),
   jQ("TechBox");

   private string name;

   private eB(string var3) {
      this.name = var3;
   }

   public string toString() {
      return this.name;
   }
}

}
