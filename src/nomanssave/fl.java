package nomanssave;

import java.io.File;
import java.io.IOException;

public class fl {
   private static final Object lock = new Object();
   private static fm lj;

   public static void a(fq var0, File var1) {
      synchronized(lock) {
         try {
            if (lj == null) {
               lj = new fm();
            }

            lj.a(var0, var1);
         } catch (IOException var4) {
            hc.a("Unable to register storage", var4);
         }

      }
   }

   public static void b(fq var0) {
      synchronized(lock) {
         try {
            if (lj != null) {
               lj.b(var0);
            }
         } catch (IOException var3) {
            hc.a("Unable to unregister storage", var3);
         }

      }
   }

   // $FF: synthetic method
   static Object bQ() {
      return lock;
   }
}
