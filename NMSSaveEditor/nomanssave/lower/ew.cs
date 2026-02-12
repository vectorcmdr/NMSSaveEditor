using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;

namespace NMSSaveEditor
{

public class ew {
   string iI;
   int iJ;
   int iK;

   ew(Element var1) {
      this.iI = var1.getAttribute("group");
      this.iJ = int.Parse(var1.getAttribute("substance"));
      this.iK = int.Parse(var1.getAttribute("product"));
   }

   public int aX() {
      return this.iJ;
   }

   public int aY() {
      return this.iK;
   }
}

}
