using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public class bg : G {
   bd dP;

   public bg(bd var1) {
      this.dP = var1;
   }

   protected override string g(string var1) {
      if (bd.a(this.dP) == null) {
         return "";
      } else {
         try {
            var1 = hg.aB(var1).ToString();
            if (!var1.Equals(bd.a(this.dP).cV())) {
               bd.a(this.dP).ai(var1);
            }

            return var1;
         } catch (Exception var3) {
            return bd.a(this.dP).cV();
         }
      }
   }
}

}
