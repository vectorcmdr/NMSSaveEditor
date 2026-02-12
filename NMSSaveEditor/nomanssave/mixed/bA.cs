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

public class bA : G {
   public int index;
   // $FF: synthetic field
   public bl er;

   public bA(bl var1, int var2) {
      this.er = var1;
      this.index = var2;
   }

   public string g(string var1) {
      if (bl.b(this.er) < 0) {
         return "";
      } else {
         int var2 = bl.c(this.er)[bl.b(this.er)].aq(this.index);

         try {
            int var3 = hf.b(var1, 0, 50);
            if (var3 != var2) {
               bl.c(this.er)[bl.b(this.er)].e(this.index, var3);
            }

            return Convert.ToString(var3);
         } catch (Exception var4) {
            return Convert.ToString(var2);
         }
      }
   }

   // $FF: synthetic method
   public bA(bl var1, int var2, bA var3) {
      // Constructor chain: base(var1, var2)
   }
}

}
