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

public class gf {
   eY nj;
   // $FF: synthetic field
   ge nk;

   private gf(ge var1, eY var2) {
      this.nk = var1;
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

   public List<object> cI() {
      List<object> var1 = new List<object>();
      IEnumerator<object> var3 = gV.G(this.nj).GetEnumerator();

      while(var3.MoveNext()) {
         eY var2 = (eY)var3.Current;
         var1.Add(new gg(this, var2));
      }

      return var1;
   }

   public bool a(gg var1) {
      return gV.a(this.nj, var1.nl);
   }

   public string toString() {
      return this.nj.getValueAsString("Name");
   }

   // $FF: synthetic method
   gf(ge var1, eY var2, gf var3) {
      // Constructor chain: base(var1, var2)
   }
}

}
