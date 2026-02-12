using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class gf {
   public string Name => getName();
   public eY nj;
   public ge nk;

   public gf(ge var1, eY var2) {
      this.nk = var1;
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

   public List<object> cI() {
      List<object> var1 = new List<object>();
      IEnumerator var3 = gV.G(this.nj).GetEnumerator();

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
   public gf(ge var1, eY var2, gf var3) : this(var1, var2) {
   }
}


#else

public class gf
{
   public gf() { }
   public gf(params object[] args) { }
   public string Name = "";
   public eY nj = default;
   public ge nk = default;
   public string cF() { return ""; }
   public string getName() { return ""; }
   public void setName(string var1) { }
   public int cG() { return 0; }
   public eY cH() { return default; }
   public List<object> cI() { return default; }
   public bool a(gg var1) { return false; }
   public string toString() { return ""; }
}

#endif

}
