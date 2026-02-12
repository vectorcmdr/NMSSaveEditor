using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE

public class dH : G {
   public dE hE;

   public dH(dE var1) {
      this.hE = var1;
   }

   public override string g(string var1) {
      gE var2 = (gE)dE.a(this.hE).SelectedItem;
      if (var2 == null) {
         return "";
      } else {
         try {
            var1 = hg.aB(var1).ToString();
            if (!var1.Equals(var2.cK())) {
               var2.aa(var1);
            }

            return var1;
         } catch (Exception var4) {
            return var2.cK();
         }
      }
   }
}


#else

public class dH
{
   public dH() { }
   public dH(params object[] args) { }
   public dE hE = default;
   public string g(string var1) { return ""; }
}

#endif

}
