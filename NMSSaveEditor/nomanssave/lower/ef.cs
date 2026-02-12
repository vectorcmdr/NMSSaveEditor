using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE

public class ef : G {
   public ec ik;
   public int il;

   public ef(ec var1, int var2) {
      this.ik = var1;
      this.il = var2;
   }

   public override string g(string var1) {
      try {
         var1 = hg.aB(var1).ToString();
         if (!var1.Equals(eb.a(ec.h(this.ik))[this.il].ee())) {
            eb.a(ec.h(this.ik))[this.il].ax(var1);
         }

         return var1;
      } catch (Exception var3) {
         return eb.a(ec.h(this.ik))[this.il].ee();
      }
   }
}


#else

public class ef
{
   public ef() { }
   public ef(params object[] args) { }
   public ec ik = default;
   public int il = 0;
   public string g(string var1) { return ""; }
}

#endif

}
