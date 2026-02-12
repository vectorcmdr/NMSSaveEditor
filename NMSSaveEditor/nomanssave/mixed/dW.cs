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

public class dW : G {
   // $FF: synthetic field
   public dN ia;

   public dW(dN var1) {
      this.ia = var1;
   }

   public string g(string var1) {
      if (dN.o(this.ia) == null) {
         return "";
      } else {
         int var2 = dN.o(this.ia).dM();

         try {
            int var3 = hf.b(var1, 1, 500);
            if (var3 != var2) {
               dN.o(this.ia).aB(var3);
            }

            return Convert.ToString(var3);
         } catch (Exception var4) {
            return Convert.ToString(var2);
         }
      }
   }
}

}
