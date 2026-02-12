using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{


public class bG : bK {
   public bE ey;

   public bG(bE var1) {
      this.ey = var1;
   }

   public string getID() {
      return "TWordsLearnt";
   }

   public bool isSpecial() {
      return true;
   }

   public string ab() {
      return (bE.a(this.ey).ToString().b(eU.kr));
   }

   public void l(string var1) {
      throw new Exception("Cannot set words learnt");
   }
}



}
