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

public class dX : G {
   // $FF: synthetic field
   dN ia;

   dX(dN var1) {
      this.ia = var1;
   }

   protected string g(string var1) {
      if (dN.o(this.ia) == null) {
         return "";
      } else {
         int var2 = dN.o(this.ia).dN();

         try {
            int var3 = hf.b(var1, 1, 200);
            if (var3 != var2) {
               dN.o(this.ia).aC(var3);
            }

            return Convert.ToString(var3);
         } catch (Exception var4) {
            return Convert.ToString(var2);
         }
      }
   }
}

}
