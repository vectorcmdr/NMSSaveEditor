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

public class dZ : G {
   // $FF: synthetic field
   dN ia;

   dZ(dN var1) {
      this.ia = var1;
   }

   protected string g(string var1) {
      gH var2 = (gH)dN.p(this.ia).SelectedItem;
      if (var2 == null) {
         return "";
      } else {
         double var3 = var2.eb();

         try {
            double var5 = hf.a(var1, 0.0D, 1000.0D);
            if (var5 != var3) {
               var2.h(var5);
            }

            return Double.toString(var5);
         } catch (Exception var7) {
            return Double.toString(var3);
         }
      }
   }
}

}
