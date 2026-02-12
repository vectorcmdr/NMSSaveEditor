using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public class gh {
   string name;
   eY nn;
   ge nk;

   private gh(ge var1, string var2, eY var3) {
      this.nk = var1;
      this.name = var2;
      this.nn = var3;
   }

   public gy cJ() {
      return gy.@as(this.nn.getValueAsString("ResourceElement.Filename"));
   }

   public string cK() {
      return this.nn.d("ResourceElement.Seed").X(1);
   }

   public void aa(string var1) {
      this.nn.d("ResourceElement.Seed").a(1, var1);
   }

   public string toString() {
      return this.name;
   }
   gh(ge var1, string var2, eY var3, gh var4) {
      this(var1, var2, var3);
   }
}

}
