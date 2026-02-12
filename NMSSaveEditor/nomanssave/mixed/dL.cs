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
      // PORT_TODO: Label var6 = (Label)base.getListCellRendererComponent(var1, var2, var3, var4, var5);
      if (var2 == null) {
         // PORT_TODO: var6.Text = ("");
      } else {
         eM var7 = (eM)var2;
         if (var7.aW()) {
            if (var4) {
      Label var6 = null; // PORT_TODO: stub declaration
               var6.setBackground(/* UIManager.getColor */ SystemColors.Control); //("Settlement.positivePerkHighlight")
            } else {
      Label var6 = null; // PORT_TODO: stub declaration
               var6.setForeground(/* UIManager.getColor */ SystemColors.Control); //("Settlement.positivePerkColor")
               return default;
            }
         } else if (var4) {
      Label var6 = null; // PORT_TODO: stub declaration
            var6.setBackground(/* UIManager.getColor */ SystemColors.Control); //("Settlement.negativePerkHighlight")
         } else {
      Label var6 = null; // PORT_TODO: stub declaration
            var6.setForeground(/* UIManager.getColor */ SystemColors.Control); //("Settlement.negativePerkColor")
         }
      }

      // PORT_TODO: return var6;
      return default;
   }
}



}
