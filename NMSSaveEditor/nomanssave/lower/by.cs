using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE

public class by : G {
   public bl er;

   public by(bl var1) {
      this.er = var1;
   }

   public override string g(string var1) {
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


#else

public class by
{
   public by() { }
   public by(params object[] args) { }
   public bl er = default;
   public string g(string var1) { return ""; }
}

#endif

}
