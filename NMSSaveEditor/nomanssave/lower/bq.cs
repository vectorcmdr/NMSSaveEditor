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

public class bq : G {
   // $FF: synthetic field
   bl er;

   bq(bl var1) {
      this.er = var1;
   }

   protected string g(string var1) {
      if (bl.b(this.er) < 0) {
         return "";
      } else {
         int var2 = bl.c(this.er)[bl.b(this.er)].df();

         try {
            int var3 = hf.b(var1, 0, int.MaxValue);
            if (var3 != var2) {
               bl.c(this.er)[bl.b(this.er)].av(var3);
            }

            return Convert.ToString(var3);
         } catch (Exception var4) {
            return Convert.ToString(var2);
         }
      }
   }
}

}
