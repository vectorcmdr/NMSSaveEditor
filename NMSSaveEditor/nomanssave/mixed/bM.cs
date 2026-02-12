using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class bM : FocusListener {
   public bL eC;
   public bK eD;

   public bM(bL var1, bK var2) {
      this.eC = var1;
      this.eD = var2;
   }

   public void focusGained(FocusEvent var1) {
   }

   public void focusLost(FocusEvent var1) {
      if (bE.a(bL.a(this.eC)) != null) {
         // PORT_TODO: TextBox var2 = (TextBox)var1.Controls[0];
         string var3 = this.eD.ab();
         // PORT_TODO: string var4 = var2.Text;
         if (!var4.Equals(var3)) {
            try {
               this.eD.l(var4);
            } catch (Exception var6) {
               // PORT_TODO: var2.Text = (var3);
            }
         }

      }
   }
}



}
