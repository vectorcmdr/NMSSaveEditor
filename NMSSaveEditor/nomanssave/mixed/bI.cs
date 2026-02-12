using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public class bI : bK {
   bE ey;

   public bI(bE var1) {
      this.ey = var1;
   }

   public string getID() {
      return "TWordsLearnt";
   }

   public bool isSpecial() {
      return true;
   }

   public string ab() {
      return Integer.toString(bE.a(this.ey).b(eU.kt));
   }

   public void l(string var1) {
      throw new Exception("Cannot set words learnt");
   }
}

}
