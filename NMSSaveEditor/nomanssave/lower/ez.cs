using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;

namespace NMSSaveEditor
{

public class ez {
   public string id;
   public int jK;
   public ey jL;

   public ez(ey var1, Element var2) {
      this.jL = var1;
      this.id = var2.getAttribute("id");
      this.jK = int.Parse(var2.getAttribute("quantity"));
   }

   public string getID() {
      return this.id;
   }

   public int bo() {
      return this.jK;
   }
}

}
