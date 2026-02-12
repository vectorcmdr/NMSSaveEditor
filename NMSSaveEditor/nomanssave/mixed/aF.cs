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

public class aF : ActionListener {
   // $FF: synthetic field
   aD cB;

   aF(aD var1) {
      this.cB = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      string var2 = aH.getProperty("LookAndFeel");
      aI var3 = (aI)Stream.of(aI.Values).filter((var1x) => {
         return var1x.ToString().Equals(var2);
      }).findFirst().orElse(aI.cN);
      aI var4 = (aI)aD.a(this.cB).SelectedItem;
      aD.a(this.cB, false);
      if (var4 == null) {
         if (var3 != null) {
            aH.setProperty("LookAndFeel", (string)null);
            aD.a(this.cB, true);
         }
      } else if (var3 == null || var3 != var4) {
         aH.setProperty("LookAndFeel", var4.ToString());
         aD.a(this.cB, true);
      }

      double var5 = double.Parse(aD.b(this.cB).GetText());
      if (var5 != aH.a("InventoryScaling", 1.0D)) {
         aH.b("InventoryScaling", var5);
         aD.a(this.cB, true);
      }

      this.cB.SetVisible(false);
   }
}

}
