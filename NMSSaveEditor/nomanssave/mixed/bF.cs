using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using System.Globalization;

namespace NMSSaveEditor
{

public class bF : bK {
   // $FF: synthetic field
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
      return Double.toString(bE.a(this.ey).dT());
   }

   public void l(string var1) {
      double var2 = double.Parse(var1);
      bE.a(this.ey).g(var2);
   }

}


}