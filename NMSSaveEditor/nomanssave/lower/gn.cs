using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public class gn {
   eY nj;
   gm oK;

   public gn(gm var1, eY var2) {
      this.oK = var1;
      this.nj = var2;
   }

   public string cF() {
      object var1 = this.nj.getValue("GalacticAddress");
      if (var1 is string) {
         return (string)var1;
      } else {
         return var1 is Number ? "0x" + Long.toHexString(((Number)var1).longValue()) : null;
      }
   }

   public string getName() {
      return this.nj.getValueAsString("Name");
   }

   public void setName(string var1) {
      this.nj.b("Name", (object)var1);
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
   public gn(gm var1, eY var2, gn var3) {
      this(var1, var2);
   }
}

}
