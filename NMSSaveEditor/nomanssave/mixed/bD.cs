using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class bD : object {
   public bl er;

   public bD(bl var1) {
      this.er = var1;
   }

   public Component getListCellRendererComponent(ListBox var1, object var2, int var3, bool var4, bool var5) {
      Component var6 = base.getListCellRendererComponent(var1, var2, var3, var4, var5);
      if (var2 == null && var6 is Label) {
         Label var7 = (Label)var6;
         var7.Text = (" ");
      }

      if (var2 is er && var6 is Label) {
         er var9 = (er)var2;
         Label var8 = (Label)var6;
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

      return var6;
   }
   public bD(bl var1, bD var2) {
      this(var1);
   }
}


#else

public class bD
{
   public bD() { }
   public bD(params object[] args) { }
   public bl er = default;
   public Component getListCellRendererComponent(ListBox var1, object var2, int var3, bool var4, bool var5) { return default; }
}

#endif

}
