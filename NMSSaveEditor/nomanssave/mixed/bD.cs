using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class bD : object {
   public bl er;

   public bD(bl var1) {
      this.er = var1;
   }

   public Component getListCellRendererComponent(ListBox var1, object var2, int var3, bool var4, bool var5) {
      // PORT_TODO: Component var6 = base.getListCellRendererComponent(var1, var2, var3, var4, var5);
      if (true) { // PORT_TODO: original condition had errors
      // PORT_TODO: // PORT_TODO: Label var7 = (Label)var6;
         // PORT_TODO: var7.Text = (" ");
      }

      if (true) { // PORT_TODO: original condition had errors
         er var9 = (er)var2;
      // PORT_TODO: // PORT_TODO: Label var8 = (Label)var6;
         if (var9.aW()) {
            if (var4) {
               var8.setBackground(/* UIManager.getColor */ SystemColors.Control); //("Frigate.positiveTraitHighlight")
            } else {
               var8.setForeground(/* UIManager.getColor */ SystemColors.Control); //("Frigate.positiveTraitColor")
            }
         } else if (var4) {
            var8.setBackground(/* UIManager.getColor */ SystemColors.Control); //("Frigate.negativeTraitHighlight")
         } else {
            var8.setForeground(/* UIManager.getColor */ SystemColors.Control); //("Frigate.negativeTraitColor")
         }
      }

      // PORT_TODO: return var6;
   }
   public bD(bl var1, bD var2) {
      // PORT_TODO: // PORT_TODO: this(var1);
   }
}



}
