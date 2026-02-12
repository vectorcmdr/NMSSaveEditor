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

public class dU : G {
   // $FF: synthetic field
   dN ia;

   dU(dN var1) {
      this.ia = var1;
   }

   protected string g(string var1) {
      gH var2 = (gH)dN.p(this.ia).SelectedItem;
      if (var2 == null) {
         return "";
      } else {
         try {
            var1 = hg.aB(var1).ToString();
            if (!var1.Equals(var2.cK())) {
               var2.aa(var1);
            }

            return var1;
         } catch (Exception var4) {
            return var2.cK();
         }
      }
   }
}

}
