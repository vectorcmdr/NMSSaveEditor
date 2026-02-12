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

public class aa : ActionListener {
   // $FF: synthetic field
   public X bV;
   // $FF: synthetic field
   public Application bv;

   public aa(X var1, Application var2) {
      this.bV = var1;
      this.bv = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      gj var2 = this.bv.j();
      if (var2 != null) {
         int var3 = -1;
         gj[] var4 = new gj[X.a(this.bV).Length + 1];

         for(int var5 = 0; var5 < X.a(this.bV).Length; ++var5) {
            if (X.a(this.bV)[var5].getIndex() < var2.getIndex()) {
               var4[var5] = X.a(this.bV)[var5];
            } else {
               var4[var5 + 1] = X.a(this.bV)[var5];
               if (var3 < 0) {
                  var3 = var5;
               }
            }
         }

         if (var3 < 0) {
            var3 = X.a(this.bV).Length;
         }

         var4[var3] = var2;
         X.a(this.bV, var4);
         hc.info("Imported " + var2.cL().ToString().ToLower() + ": " + var2.getIndex());
         X.k(this.bV).SetSelectedIndex(var3);
         X.k(this.bV).updateUI();
      }

   }
}

}
