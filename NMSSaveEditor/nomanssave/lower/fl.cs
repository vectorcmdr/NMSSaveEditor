using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using System.Globalization;

namespace NMSSaveEditor
{

public class fl {
   private static Object lock = new Object();
   private static fm lj;

   public static void a(fq var0, FileInfo var1) {
      lock(lock) {
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
      lock(lock) {
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

}
