using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class gf {
   public string Name => getName();
   public eY nj;
   public ge nk;

   public gf(ge var1, eY var2) {
      this.nk = var1;
      this.nj = var2;
   }

   public string cF() {
      // PORT_TODO: object var1 = this.nj.getValue("GalacticAddress");
      // PORT_TODO: if (var1 is string) {
         // PORT_TODO: return (string)var1;
      // PORT_TODO: } else {
         // PORT_TODO: return var1 is Number ? "0x" + ((Number)var1).longValue().ToString("X") : null;
      // PORT_TODO: }
      return default;
   }

   public string getName() {
      // PORT_TODO: return this.nj.getValueAsString("Name");
      return default;
   }

   public void setName(string var1) {
      // PORT_TODO: this.nj.b("Name", (object)var1);
   }

   public int cG() {
      // PORT_TODO: return this.nj.d("Objects").Count;
      return 0;
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
      // PORT_TODO: return this.nj.getValueAsString("Name");
      return default;
   }
   public gf(ge var1, eY var2, gf var3) : this(var1, var2) {
   }
}



}
