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

public class bH : bK {
   // $FF: synthetic field
   bE ey;

   bH(bE var1) {
      this.ey = var1;
   }

   public string getID() {
      return "TWordsLearnt";
   }

   public bool isSpecial() {
      return true;
   }

   public string ab() {
      return Convert.ToString(bE.a(this.ey).b(eU.ks));
   }

   public void l(string var1) {
      throw new Exception("Cannot set words learnt");
   }
}

}
