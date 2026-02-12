using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE

public class ei : G {
   public ec ik;
   public int il;

   public ei(ec var1, int var2) {
      this.ik = var1;
      this.il = var2;
   }

   public override string g(string var1) {
      try {
         int var2 = int.Parse(var1);
         if (var2 != eb.a(ec.h(this.ik))[this.il].eh()) {
            eb.a(ec.h(this.ik))[this.il].aI(var2);
         }

         return var1;
      } catch (Exception var3) {
         return Integer.toString(eb.a(ec.h(this.ik))[this.il].eh());
      }
   }
}


#else

public class ei
{
   public ei() { }
   public ei(params object[] args) { }
   public ec ik = default;
   public int il = 0;
   public string g(string var1) { return ""; }
}

#endif

}
