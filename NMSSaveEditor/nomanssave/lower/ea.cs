using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE

public class ea : G {
   public dN ia;

   public ea(dN var1) {
      this.ia = var1;
   }

   public override string g(string var1) {
      gH var2 = (gH)dN.p(this.ia).SelectedItem;
      if (var2 == null) {
         return "";
      } else {
         double var3 = var2.cX();

         try {
            double var5 = hf.a(var1, 0.0D, 1000.0D);
            if (var5 != var3) {
               var2.a(var5);
            }

            return Double.toString(var5);
         } catch (Exception var7) {
            return Double.toString(var3);
         }
      }
   }
}


#else

public class ea
{
   public ea() { }
   public ea(params object[] args) { }
   public dN ia = default;
   public string g(string var1) { return ""; }
}

#endif

}
