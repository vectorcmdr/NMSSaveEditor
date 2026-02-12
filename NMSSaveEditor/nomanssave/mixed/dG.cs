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

public class dG : G {
   // $FF: synthetic field
   dE hE;

   dG(dE var1) {
      this.hE = var1;
   }

   protected string g(string var1) {
      gE var2 = (gE)dE.a(this.hE).SelectedItem;
      if (var2 == null) {
         return "";
      } else {
         var1 = var1.Trim();
         if (!var1.Equals(var2.Name)) {
            var2.setName(var1);
            dE.c(this.hE).SetText(var1);
         }

         return var1;
      }
   }
}

}
