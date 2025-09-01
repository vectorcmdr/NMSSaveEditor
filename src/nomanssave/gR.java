package nomanssave;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class gR {
   private static Map rR = new HashMap();

   public static eY az(String var0) {
      eY var1 = null;
      if (rR.containsKey(var0)) {
         var1 = (eY)rR.get(var0);
      } else {
         InputStream var2 = Application.class.getResourceAsStream("templates/" + var0 + ".json");
         if (var2 != null) {
            try {
               byte[] var3 = hk.g(var2);
               var1 = ff.b(var3);
            } catch (IOException var4) {
               hc.a("Cannot load template: " + var0, var4);
            }
         }

         rR.put(var0, var1);
      }

      return var1 == null ? null : var1.bE();
   }
}
