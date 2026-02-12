using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class P : ActionListener {
   public I bt;
   public Application bv;

   public P(I var1, Application var2) {
      this.bt = var1;
      this.bv = var2;
   }

   public void actionPerformed(EventArgs var1) {
      gf var2 = (gf)I.j(this.bt).SelectedItem;
      if (var2 != null) {
         List<object> var3 = var2.cI();
         if (var3.Count == 0) {
            this.bv.c("Cannot move base computer.\nPlease ensure that your base has a suitable Signal Booster / Blueprint Analyser / Beacon placed where you want your base computer to be.");
         } else {
            int var4;
            if (true) { // PORT_TODO: original condition had errors
      gg var5 = null; // PORT_TODO: stub declaration
               // PORT_TODO: gg var5 = (gg)var3[var4];
               hc.info("Attempting to swap base computer with " + var5.ToString() + "...");
               if (var2.a(var5)) {
      var5 = null; // PORT_TODO: stub declaration
                  hc.info("Base computer relocated: " + var2.Name);
               } else {
                  hc.info("Base computer not moved.");
                  this.bv.c("An error occurred while attempting to move base computer.");
               }

            }
         }
      }
   }
}



}
