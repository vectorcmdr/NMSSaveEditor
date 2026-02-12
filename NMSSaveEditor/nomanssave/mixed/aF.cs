using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class aF : EventHandler {
   aD cB;

   aF(aD var1) {
      this.cB = var1;
   }

   public void actionPerformed(EventArgs var1) {
      string var2 = aH.getProperty("LookAndFeel");
      aI var3 = (aI)Stream.of(aI.Values).filter((var1x) => {
         return var1x.name().Equals(var2);
      }).findFirst().orElse(aI.cN);
      aI var4 = (aI)aD.a(this.cB).SelectedItem;
      aD.a(this.cB, false);
      if (var4 == null) {
         if (var3 != null) {
            aH.setProperty("LookAndFeel", null);
            aD.a(this.cB, true);
         }
      } else if (var3 == null || var3 != var4) {
         aH.setProperty("LookAndFeel", var4.name());
         aD.a(this.cB, true);
      }

      double var5 = double.Parse(aD.b(this.cB).Text);
      if (var5 != aH.a("InventoryScaling", 1.0D)) {
         aH.b("InventoryScaling", var5);
         aD.a(this.cB, true);
      }

      this.cB.Hide();
   }
}

}
