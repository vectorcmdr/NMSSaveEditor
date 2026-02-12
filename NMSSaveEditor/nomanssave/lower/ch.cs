using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
class ch : G {
   cg fF;

   ch(cg var1) {
      this.fF = var1;
   }

   protected string g(string var1) {
      if (cg.a(this.fF) == null) {
         return "";
      } else {
         try {
            int var2 = hf.b(var1, 0, 99999);
            if (cg.a(this.fF) != var2) {
               cg.b(this.fF).m(cg.c(this.fF).M(var2));
               cg.a(this.fF, new Integer(var2));
            }
         } catch (Exception var3) {
         }

         return cg.a(this.fF).ToString();
      }
   }
}

}
