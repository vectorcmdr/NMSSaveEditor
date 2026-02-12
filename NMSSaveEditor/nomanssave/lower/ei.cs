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

public class ei : G {
   // $FF: synthetic field
   ec ik;
   // $FF: synthetic field
   private int il;

   ei(ec var1, int var2) {
      this.ik = var1;
      this.il = var2;
   }

   protected string g(string var1) {
      try {
         int var2 = int.Parse(var1);
         if (var2 != eb.a(ec.h(this.ik))[this.il].eh()) {
            eb.a(ec.h(this.ik))[this.il].aI(var2);
         }

         return var1;
      } catch (Exception var3) {
         return Convert.ToString(eb.a(ec.h(this.ik))[this.il].eh());
      }
   }
}

}
