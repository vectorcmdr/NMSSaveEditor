using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class cP : object {
   public cN gt;

   public cP(cN var1) {
      this.gt = var1;
   }

   public Component getListCellRendererComponent(ListBox var1, object var2, int var3, bool var4, bool var5) {
      // PORT_TODO: Component var6 = base.getListCellRendererComponent(var1, var2, var3, var4, var5);
      if (true) { // PORT_TODO: original condition had errors
      // PORT_TODO: // PORT_TODO: Label var7 = (Label)var6;
         // PORT_TODO: var7.Text = (" ");
      }

      if (true) { // PORT_TODO: original condition had errors
         bool var12 = false;
         Enum[] var11;
         int var10 = (var11 = cN.e(this.gt)).Length;

         for(int var9 = 0; var9 < var10; ++var9) {
            Enum var8 = var11[var9];
            if (var8 == var2) {
               var12 = true;
               break;
            }
         }

      // PORT_TODO: // PORT_TODO: Label var13 = (Label)var6;
         if (var12 == null) {
            if (var4) {
               // PORT_TODO: var13.setBackground(cN.ag());
            } else {
               // PORT_TODO: var13.setForeground(cN.aB());
            }
         }
      }

      // PORT_TODO: return var6;
      return default;
   }
}



}
