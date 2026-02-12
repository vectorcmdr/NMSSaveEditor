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

public class eh : G {
   // $FF: synthetic field
   public ec ik;
   // $FF: synthetic field
   public int il;

   public eh(ec var1, int var2) {
      this.ik = var1;
      this.il = var2;
   }

   public string g(string var1) {
      try {
         var1 = hg.aB(var1).ToString();
         if (!var1.Equals(eb.a(ec.h(this.ik))[this.il].eg())) {
            eb.a(ec.h(this.ik))[this.il].ay(var1);
         }

         return var1;
      } catch (Exception var3) {
         return eb.a(ec.h(this.ik))[this.il].eg();
      }
   }
}

}
