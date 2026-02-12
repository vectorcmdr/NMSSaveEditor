using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE

public class bo : G {
   public bl er;

   public bo(bl var1) {
      this.er = var1;
   }

   public override string g(string var1) {
      if (bl.b(this.er) < 0) {
         return "";
      } else {
         int var2 = bl.c(this.er)[bl.b(this.er)].dd();

         try {
            int var3 = hf.b(var1, 0, int.MaxValue);
            if (var3 != var2) {
               bl.c(this.er)[bl.b(this.er)].at(var3);
            }

            return Integer.toString(var3);
         } catch (Exception var4) {
            return Integer.toString(var2);
         }
      }
   }
}


#else

public class bo
{
   public bo() { }
   public bo(params object[] args) { }
   public bl er = default;
   public string g(string var1) { return ""; }
}

#endif

}
