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

public class hc {
   private static Handler sn = new hd();
   private static StreamWriter so;
   private static StreamWriter sp;
   private static StreamWriter sq;
   private static int sr;

   static hc() {
      Logger var0 = JavaCompat.GetLogManager().GetLogger("");
      Handler[] var1 = var0.GetHandlers();

      for(int var2 = 0; var2 < var1.length; ++var2) {
         var0.RemoveHandler(var1[0]);
      }

      var0.SetLevel(JavaCompat.LevelAll);
      var0.AddHandler(sn);
      em();
      so = System.out;
      sp = System.err;
      sr = JavaCompat.LevelInfo.intValue();
   }

   private static void em() {
      try {
         Class var0 = Class.forName("sun.misc.Unsafe");
         Field var1 = var0.getDeclaredField("theUnsafe");
         var1.setAccessible(true);
         Object var2 = var1.Get((Object)null);
         Method var3 = var0.getDeclaredMethod("putObjectVolatile", typeof(Object), Long.TYPE, typeof(Object));
         Method var4 = var0.getDeclaredMethod("staticFieldOffset", typeof(Field));
         Class var5 = Class.forName("jdk.internal.module.IllegalAccessLogger");
         Field var6 = var5.getDeclaredField("logger");
         Long var7 = (Long)var4.invoke(var2, var6);
         var3.invoke(var2, var5, var7, null);
      } catch (Exception var8) {
      }

   }

   public static void k(FileInfo var0) {
      FileStream var1;
      try {
         var1 = new FileStream(var0);
         Runtime.getRuntime().addShutdownHook(new System.Threading.Thread(() => {
            close();
         }));
         System.setOut(new StreamWriter(new he(so, "[STDOUT] ")));
         System.setErr(new StreamWriter(new he(sp, "[STDERR] ")));
      } catch (FileNotFoundException var3) {
         var1 = null;
      }

      sq = var1 == null ? null : new StreamWriter(var1, true);
   }

   public static void aA(string var0) {
      try {
         Level var1 = Level.parse(var0);
         sr = var1.intValue();
         info("Set LogLevel: " + var0);
      } catch (ArgumentException var2) {
         warn("Invalid LogLevel: " + var0);
      }

   }

   public static void debug(string var0) {
      if (sq != null) {
         lock(sq) {
            sq.println("[DEBUG] " + var0.Trim());
         }
      }

   }

   public static void debug(string var0, Exception var1) {
      if (sq != null) {
         lock(sq) {
            sq.println("[DEBUG] " + var0.Trim());
            if (var1 != null) {
               sq.print("[DEBUG] ");
               var1.printStackTrace(sq);
            }
         }
      }

   }

   public static void info(string var0) {
      so.println(var0);
      if (sq != null) {
         lock(sq) {
            sq.println("[INFO] " + var0.Trim());
         }
      }

   }

   public static void info(string var0, Exception var1) {
      so.println(var0);
      if (sq != null) {
         lock(sq) {
            sq.println("[INFO] " + var0.Trim());
            if (var1 != null) {
               sq.print("[INFO] ");
               var1.printStackTrace(sq);
            }
         }
      }

   }

   private static string d(string var0, string var1) {
      for(int var2 = 0; var2 < var0.Length; ++var2) {
         if (!Character.isWhitespace(var0[var2])) {
            return var0.Substring(0, var2) + var1 + var0.Substring(var2);
         }
      }

      return "";
   }

   public static void warn(string var0) {
      so.println(d(var0, "WARNING: "));
      if (sq != null) {
         lock(sq) {
            sq.println("[WARNING] " + var0.Trim());
         }
      }

   }

   public static void a(string var0, Exception var1) {
      so.println(d(var0, "WARNING: "));
      if (sq != null) {
         lock(sq) {
            sq.println("[WARNING] " + var0.Trim());
            if (var1 != null) {
               sq.print("[WARNING] ");
               var1.printStackTrace(sq);
            }
         }
      }

   }

   public static void error(string var0, Exception var1) {
      sp.println(d(var0, "ERROR: "));
      if (sq != null) {
         lock(sq) {
            sq.println("[ERROR] " + var0.Trim());
            if (var1 != null) {
               sq.print("[ERROR] ");
               var1.printStackTrace(sq);
            }
         }
      }

   }

   private static void log(LogRecord var0) {
      Level var1 = var0.getLevel();
      if (var1.intValue() >= sr) {
         string var2 = var0.getLoggerName();
         if (var2 == null) {
            var2 = "";
         } else {
            var2 = var2 + ": ";
         }

         var2 = var2 + var0.getMessage();
         string var3;
         StreamWriter var4;
         if (var1.intValue() >= JavaCompat.LevelSevere.intValue()) {
            var3 = "SEVERE";
            var4 = sp;
         } else if (var1.intValue() >= JavaCompat.LevelWarning.intValue()) {
            var3 = "WARNING";
            var4 = sp;
         } else if (var1.intValue() >= JavaCompat.LevelInfo.intValue()) {
            var3 = "INFO";
            var4 = so;
         } else if (var1.intValue() >= Level.CONFIG.intValue()) {
            var3 = "CONFIG";
            var4 = so;
         } else if (var1.intValue() >= JavaCompat.LevelFine.intValue()) {
            var3 = "FINE";
            var4 = so;
         } else if (var1.intValue() >= JavaCompat.LevelFineR.intValue()) {
            var3 = "FINER";
            var4 = so;
         } else if (var1.intValue() >= JavaCompat.LevelFineST.intValue()) {
            var3 = "FINEST";
            var4 = so;
         } else {
            var3 = "DEBUG";
            var4 = so;
         }

         if (var1.intValue() >= JavaCompat.LevelInfo.intValue()) {
            var4.println(d(var2, var3 + ": "));
         }

         if (sq != null) {
            lock(sq) {
               sq.println("[" + var3 + "] " + var2.Trim());
               if (var0.getThrown() != null) {
                  sq.print("[" + var3 + "] ");
                  var0.getThrown().printStackTrace(sq);
               }
            }
         }

      }
   }

   private static void close() {
      sq.Close();
   }

   // $FF: synthetic method
   static void a(LogRecord var0) {
      log(var0);
   }

   // $FF: synthetic method
   static StreamWriter en() {
      return sq;
   }
}

}
