using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class dS : ActionListener {
   public dN ia;
   public Application bv;

   public dS(dN var1, Application var2) {
      this.ia = var1;
      this.bv = var2;
   }

   public void actionPerformed(EventArgs var1) {
      gH var2 = this.bv.h();
      if (var2 != null) {
         // PORT_TODO: gH[] var3 = new gH[dN.a(this.ia).Length + 1];
         int var4 = -1;

         // PORT_TODO: for(int var5 = 0; var5 < dN.a(this.ia).Length; ++var5) {
            // PORT_TODO: if (dN.a(this.ia)[var5].getIndex() < var2.getIndex()) {
               // PORT_TODO: var3[var5] = dN.a(this.ia)[var5];
            // PORT_TODO: } else {
               // PORT_TODO: var3[var5 + 1] = dN.a(this.ia)[var5];
               // PORT_TODO: if (var4 < 0) {
                  // PORT_TODO: var4 = var5;
               // PORT_TODO: }
            // PORT_TODO: }
         // PORT_TODO: }

         if (var4 < 0) {
            // PORT_TODO: var4 = dN.a(this.ia).Length;
         }

         // PORT_TODO: var3[var4] = var2;
         // PORT_TODO: dN.a(this.ia, var3);
         hc.info("Imported ship: " + var2.getIndex());
         // PORT_TODO: dN.p(this.ia).SelectedIndex = (var4);
         // PORT_TODO: dN.p(this.ia).Refresh();
      }

   }
}



}
