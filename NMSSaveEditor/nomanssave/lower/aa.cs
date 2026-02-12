using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class aa : ActionListener {
   public X bV;
   public Application bv;

   public aa(X var1, Application var2) {
      this.bV = var1;
      this.bv = var2;
   }

   public void actionPerformed(EventArgs var1) {
      gj var2 = this.bv.j();
      if (var2 != null) {
         int var3 = -1;
         // PORT_TODO: gj[] var4 = new gj[X.a(this.bV).Length + 1];

         // PORT_TODO: for(int var5 = 0; var5 < X.a(this.bV).Length; ++var5) {
            // PORT_TODO: if (X.a(this.bV)[var5].getIndex() < var2.getIndex()) {
               // PORT_TODO: var4[var5] = X.a(this.bV)[var5];
            // PORT_TODO: } else {
               // PORT_TODO: var4[var5 + 1] = X.a(this.bV)[var5];
               // PORT_TODO: if (var3 < 0) {
                  // PORT_TODO: var3 = var5;
               // PORT_TODO: }
            // PORT_TODO: }
         // PORT_TODO: }

         if (var3 < 0) {
            // PORT_TODO: var3 = X.a(this.bV).Length;
         }

         // PORT_TODO: var4[var3] = var2;
         // PORT_TODO: X.a(this.bV, var4);
         // PORT_TODO: hc.info("Imported " + var2.cL().Name.ToLower() + ": " + var2.getIndex());
         // PORT_TODO: X.k(this.bV).SelectedIndex = (var3);
         // PORT_TODO: X.k(this.bV).Refresh();
      }

   }
}



}
