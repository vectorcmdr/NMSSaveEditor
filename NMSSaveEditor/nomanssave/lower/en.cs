using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class en {
   private static bool iu = false;
   private static List iv = new List<object>();

   public static void a(eo var0) {
      iv.Add(var0);
   }

   public static bool aS() {
      return iu;
   }

   public static void c(bool var0) {
      iu = var0;
      IEnumerator var2 = iv.iterator();

      while(var2.hasNext()) {
         eo var1 = (eo)var2.next();
         var1.a(var0);
      }

   }
}

}
