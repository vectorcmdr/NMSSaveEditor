using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE
class @do : G {
   public dj hl;
   public @do(dj var1) {
      this.hl = var1;
   }

   public override string g(string var1) {
      gv var2 = (gv)dj.j(this.hl).SelectedItem;
      if (var2 == null) {
         return "";
      } else {
         double var3 = var2.dG();

         try {
            double var5 = hf.a(var1, 0.0D, 1000.0D);
            if (var5 != var3) {
               var2.e(var5);
            }

            return var5.ToString();
         } catch (Exception var7) {
            return var3.ToString();
         }
      }
   }
}
#else
public class @do
{
   public @do() { }
   public @do(params object[] args) { }
   public string g(string var1) { return ""; }
}
#endif

}
