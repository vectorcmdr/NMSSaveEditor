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

public class ci : G {
   // $FF: synthetic field
   cg fF;

   ci(cg var1) {
      this.fF = var1;
   }

   protected string g(string var1) {
      if (cg.d(this.fF) == null) {
         return "";
      } else {
         try {
            int var2 = hf.b(var1, 1, cg.b(this.fF).dB());
            if (cg.d(this.fF) != var2) {
               cg.b(this.fF).aA(var2);
               cg.b(this.fF, new Integer(var2));
            }
         } catch (Exception var3) {
         }

         return cg.d(this.fF).ToString();
      }
   }
}

}
