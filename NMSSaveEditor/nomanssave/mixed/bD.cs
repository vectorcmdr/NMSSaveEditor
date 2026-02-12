using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class bD : object {
   bl er;

   private bD(bl var1) {
      this.er = var1;
   }

   public Component getListCellRendererComponent(ListBox var1, Object var2, int var3, bool var4, bool var5) {
      Component var6 = base.getListCellRendererComponent(var1, var2, var3, var4, var5);
      if (var2 == null && var6 is Label) {
         Label var7 = (Label)var6;
         var7.setText(" ");
      }

      if (var2 is er && var6 is Label) {
         er var9 = (er)var2;
         Label var8 = (Label)var6;
         if (var9.aW()) {
            if (var4) {
               var8.setBackground(SystemInformation.getColor("Frigate.positiveTraitHighlight"));
            } else {
               var8.setForeground(SystemInformation.getColor("Frigate.positiveTraitColor"));
            }
         } else if (var4) {
            var8.setBackground(SystemInformation.getColor("Frigate.negativeTraitHighlight"));
         } else {
            var8.setForeground(SystemInformation.getColor("Frigate.negativeTraitColor"));
         }
      }

      return var6;
   }
   bD(bl var1, bD var2) {
      this(var1);
   }
}

}
