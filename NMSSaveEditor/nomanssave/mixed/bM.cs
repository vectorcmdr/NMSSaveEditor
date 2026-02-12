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

public class bM : FocusListener {
   // $FF: synthetic field
   public bL eC;
   // $FF: synthetic field
   public bK eD;

   public bM(bL var1, bK var2) {
      this.eC = var1;
      this.eD = var2;
   }

   public void focusGained(FocusEvent var1) {
   }

   public void focusLost(FocusEvent var1) {
      if (bE.a(bL.a(this.eC)) != null) {
         TextBox var2 = (TextBox)var1.getComponent();
         string var3 = this.eD.ab();
         string var4 = var2.GetText();
         if (!var4.Equals(var3)) {
            try {
               this.eD.l(var4);
            } catch (Exception var6) {
               var2.SetText(var3);
            }
         }

      }
   }
}

}
