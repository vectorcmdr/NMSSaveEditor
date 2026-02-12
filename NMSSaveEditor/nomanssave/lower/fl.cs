using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class fl {
   public static object @lock = new object();
   public static fm lj;

   public static void a(fq var0, FileInfo var1) {
      lock(@lock) {
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
      lock(@lock) {
         try {
            if (lj != null) {
               lj.b(var0);
            }
         } catch (IOException var3) {
            hc.a("Unable to unregister storage", var3);
         }

      }
   }
   public static object bQ() {
      return @lock;
   }
}

}
