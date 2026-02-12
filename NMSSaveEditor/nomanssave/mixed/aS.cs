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

public class aS : FocusListener {
   // $FF: synthetic field
   public aQ dr;

   public aS(aQ var1) {
      this.dr = var1;
   }

   public void focusGained(FocusEvent var1) {
   }

   public void focusLost(FocusEvent var1) {
      int var2;
      try {
         var2 = int.Parse(aQ.e(this.dr).GetText());
         if (var2 != aQ.b(this.dr).height) {
            if (var2 < aQ.c(this.dr).height) {
               var2 = aQ.c(this.dr).height;
            } else if (var2 > aQ.d(this.dr).height && !en.aS()) {
               var2 = aQ.d(this.dr).height;
            }
         }
      } catch (Exception var4) {
         var2 = aQ.b(this.dr).height;
      }

      aQ.e(this.dr).SetText(Convert.ToString(var2));
   }
}

}
