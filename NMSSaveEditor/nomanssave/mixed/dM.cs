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

public class dM : G {
   public gG hH;
   // $FF: synthetic field
   public dE hE;

   public dM(dE var1, gG var2) {
      this.hE = var1;
      this.hH = var2;
   }

   public string g(string var1) {
      gE var2 = (gE)dE.a(this.hE).SelectedItem;
      if (var2 == null) {
         return "";
      } else {
         int var3 = var2.aq(this.hH.ordinal());

         try {
            int var4 = hf.b(var1, 0, this.hH.dY());
            if (var4 != var3) {
               var2.e(this.hH.ordinal(), var4);
            }

            return Convert.ToString(var4);
         } catch (Exception var5) {
            return Convert.ToString(var3);
         }
      }
   }

   // $FF: synthetic method
   public dM(dE var1, gG var2, dM var3) {
      // Constructor chain: base(var1, var2)
   }
}

}
