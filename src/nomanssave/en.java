package nomanssave;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class en {
   private static boolean iu = false;
   private static List iv = new ArrayList();

   public static void a(eo var0) {
      iv.add(var0);
   }

   public static boolean aS() {
      return iu;
   }

   public static void c(boolean var0) {
      iu = var0;
      Iterator var2 = iv.iterator();

      while(var2.hasNext()) {
         eo var1 = (eo)var2.next();
         var1.a(var0);
      }

   }
}
