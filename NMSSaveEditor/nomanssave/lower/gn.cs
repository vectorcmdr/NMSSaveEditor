using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE

public class gn {
   public string Name => getName();
   public eY nj;
   public gm oK;

   public gn(gm var1, eY var2) {
      this.oK = var1;
      this.nj = var2;
   }

   public string cF() {
      object var1 = this.nj.getValue("GalacticAddress");
      if (var1 is string) {
         return (string)var1;
      } else {
         return var1 is Number ? "0x" + ((Number)var1).longValue().ToString("X") : null;
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
   public gn(gm var1, eY var2, gn var3) : this(var1, var2) {
   }
}


#else

public class gn
{
   public gn() { }
   public gn(params object[] args) { }
   public string Name = "";
   public eY nj = default;
   public gm oK = default;
   public string cF() { return ""; }
   public string getName() { return ""; }
   public void setName(string var1) { }
   public int cG() { return 0; }
   public eY cH() { return default; }
   public string toString() { return ""; }
}

#endif

}
