using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class dS : ActionListener {
   dN ia;
   public Application bv;

   public dS(dN var1, Application var2) {
      this.ia = var1;
      this.bv = var2;
   }

   public void actionPerformed(EventArgs var1) {
      gH var2 = this.bv.h();
      if (var2 != null) {
         gH[] var3 = new gH[dN.a(this.ia).Length + 1];
         int var4 = -1;

         for(int var5 = 0; var5 < dN.a(this.ia).Length; ++var5) {
            if (dN.a(this.ia)[var5].getIndex() < var2.getIndex()) {
               var3[var5] = dN.a(this.ia)[var5];
            } else {
               var3[var5 + 1] = dN.a(this.ia)[var5];
               if (var4 < 0) {
                  var4 = var5;
               }
            }
         }

         if (var4 < 0) {
            var4 = dN.a(this.ia).Length;
         }

         var3[var4] = var2;
         dN.a(this.ia, var3);
         hc.info("Imported ship: " + var2.getIndex());
         dN.p(this.ia).SelectedIndex = (var4);
         dN.p(this.ia).Refresh();
      }

   }
}

}
