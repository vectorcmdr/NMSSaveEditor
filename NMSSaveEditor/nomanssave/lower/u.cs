using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public class u : fR {
   public Application aZ;

   public u(Application var1) {
      this.aZ = var1;
   }

   public void a(fq var1) {
      if (Application.a(this.aZ) && Application.b(this.aZ) == var1) {
         Application.a(this.aZ, true);
      }
   }

   public void a(fq var1, int var2, string var3) {
      if (Application.a(this.aZ) && Application.b(this.aZ) == var1) {
         Application.b(this.aZ, true);
         if (Application.c(this.aZ) >= 0 && Application.d(this.aZ)[Application.c(this.aZ)].getIndex() == var2) {
            Application.c(this.aZ, true);
            if (Application.e(this.aZ) >= 0 && Application.f(this.aZ)[Application.e(this.aZ)].K().Equals(var3)) {
               Application.d(this.aZ, true);
            }
         }

      }
   }
}

}
