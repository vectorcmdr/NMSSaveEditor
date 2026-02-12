using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class dL : object {
   public dJ hG;

   public dL(dJ var1) {
      this.hG = var1;
   }

   public Component getListCellRendererComponent(ListBox var1, object var2, int var3, bool var4, bool var5) {
      Label var6 = (Label)base.getListCellRendererComponent(var1, var2, var3, var4, var5);
      if (var2 == null) {
         var6.Text = ("");
      } else {
         eM var7 = (eM)var2;
         if (var7.aW()) {
            if (var4) {
               var6.setBackground(/* UIManager.getColor */ SystemColors.Control); //("Settlement.positivePerkHighlight")
            } else {
               var6.setForeground(/* UIManager.getColor */ SystemColors.Control); //("Settlement.positivePerkColor")
            }
         } else if (var4) {
            var6.setBackground(/* UIManager.getColor */ SystemColors.Control); //("Settlement.negativePerkHighlight")
         } else {
            var6.setForeground(/* UIManager.getColor */ SystemColors.Control); //("Settlement.negativePerkColor")
         }
      }

      return var6;
   }
}

}
