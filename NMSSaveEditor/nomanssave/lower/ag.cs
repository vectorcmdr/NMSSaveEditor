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

public class ag : G {
   // $FF: synthetic field
   public X bV;

   public ag(X var1) {
      this.bV = var1;
   }

   public string g(string var1) {
      gj var2 = (gj)X.k(this.bV).SelectedItem;
      if (var2 == null) {
         return "";
      } else {
         var1 = var1.Trim();
         if (!var1.Equals(var2.cP())) {
            var2.ad(var1);
            X.g(this.bV).SetText(var1);
         }

         return var1;
      }
   }
}

}
