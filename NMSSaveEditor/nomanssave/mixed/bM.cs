using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class bM : FocusListener {
   bL eC;
   private bK eD;

   bM(bL var1, bK var2) {
      this.eC = var1;
      this.eD = var2;
   }

   public void focusGained(FocusEvent var1) {
   }

   public void focusLost(FocusEvent var1) {
      if (bE.a(bL.a(this.eC)) != null) {
         TextBox var2 = (TextBox)var1.Controls[0];
         string var3 = this.eD.ab();
         string var4 = var2.Text;
         if (!var4.Equals(var3)) {
            try {
               this.eD.l(var4);
            } catch (Exception var6) {
               var2.Text = (var3);
            }
         }

      }
   }
}

}