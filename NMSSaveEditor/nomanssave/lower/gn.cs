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

public class gn {
   eY nj;
   // $FF: synthetic field
   gm oK;

   private gn(gm var1, eY var2) {
      this.oK = var1;
      this.nj = var2;
   }

   public string cF() {
      Object var1 = this.nj.getValue("GalacticAddress");
      if (var1 is string) {
         return (string)var1;
      } else {
         return var1 is Number ? "0x" + Convert.ToString((long)((Number)var1).longValue()) : null;
      }
   }

   public string getName() {
      return this.nj.getValueAsString("Name");
   }

   public void setName(string var1) {
      this.nj.b("Name", (Object)var1);
   }

   public int cG() {
      return this.nj.d("Objects").Count;
   }

   public eY cH() {
      return this.nj;
   }

   public string toString() {
      return this.nj.getValueAsString("Name");
   }

   // $FF: synthetic method
   gn(gm var1, eY var2, gn var3) {
      // Constructor chain: base(var1, var2)
   }
}

}
