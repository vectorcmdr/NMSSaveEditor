using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{


public class bF : bK {
   public bE ey;

   public bF(bE var1) {
      this.ey = var1;
   }

   public string getID() {
      return "ExtremeSurvival";
   }

   public bool isSpecial() {
      return false;
   }

   public string ab() {
      return (bE.a(this.ey).ToString().dT());
   }

   public void l(string var1) {
      double var2 = double.Parse(var1);
      bE.a(this.ey).g(var2);
   }
}



}
