using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public class by : G {
   bl er;

   public by(bl var1) {
      this.er = var1;
   }

   protected override string g(string var1) {
      if (bl.b(this.er) < 0) {
         return "";
      } else {
         try {
            var1 = hg.aB(var1).ToString();
            if (!var1.Equals(bl.c(this.er)[bl.b(this.er)].cV())) {
               bl.c(this.er)[bl.b(this.er)].ai(var1);
            }

            return var1;
         } catch (Exception var3) {
            return bl.c(this.er)[bl.b(this.er)].cV();
         }
      }
   }
}

}
