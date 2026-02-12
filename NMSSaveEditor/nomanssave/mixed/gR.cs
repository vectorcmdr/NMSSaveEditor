using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class gR {
   private static Dictionary<object, object> rR = new Dictionary<object, object>();

   public static eY az(string var0) {
      eY var1 = null;
      if (rR.ContainsKey(var0)) {
         var1 = (eY)rR[var0];
      } else {
         Stream var2 = typeof(Application).GetManifestResourceStream("templates/" + var0 + ".json");
         if (var2 != null) {
            try {
               byte[] var3 = hk.g(var2);
               var1 = ff.b(var3);
            } catch (IOException var4) {
               hc.a("Cannot load template: " + var0, var4);
            }
         }

         rR.Put(var0, var1);
      }

      return var1 == null ? null : var1.bE();
   }
}

}
