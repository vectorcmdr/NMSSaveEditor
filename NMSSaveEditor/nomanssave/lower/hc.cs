using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class hc {
   public static Handler sn = new hd();
   public static StreamWriter so;
   public static StreamWriter sp;
   public static StreamWriter sq;
   public static int sr;

   static hc() {
      // PORT_TODO: Logger var0 = /* LogManager */ null.getLogger("");
      // PORT_TODO: Handler[] var1 = var0.getHandlers();

      for(int var2 = 0; var2 < var1.Length; ++var2) {
         // PORT_TODO: var0.removeHandler(var1[0]);
      }

      // PORT_TODO: var0.setLevel(Level.ALL);
      // PORT_TODO: var0.addHandler(sn);
      em();
      // PORT_TODO: so = Console.Out;
      // PORT_TODO: sp = Console.Error;
      sr = Level.INFO.intValue();
   }

   public static void em() {
      try {
         // PORT_TODO: Class var0 = Class.forName("sun.misc.Unsafe");
         // PORT_TODO: Field var1 = var0.getDeclaredField("theUnsafe");
         // PORT_TODO: var1.setAccessible(true);
         // PORT_TODO: object var2 = var1[(object)null];
         // PORT_TODO: Method var3 = var0.getDeclaredMethod("putObjectVolatile", typeof(object), Long.TYPE, typeof(object));
         // PORT_TODO: Method var4 = var0.getDeclaredMethod("staticFieldOffset", typeof(Field));
         // PORT_TODO: Class var5 = Class.forName("jdk.internal.module.IllegalAccessLogger");
         // PORT_TODO: Field var6 = var5.getDeclaredField("logger");
         // PORT_TODO: Long var7 = (Long)var4.invoke(var2, var6);
         // PORT_TODO: var3.invoke(var2, var5, var7, null);
      } catch (Exception var8) {
      }

   }

   public static void k(FileInfo var0) {
      FileStream var1;
      try {
         var1 = new FileStream((var0).ToString(), System.IO.FileMode.Open);
         Process.GetCurrentProcess().addShutdownHook(new Thread(() => {
            close();
         }));
         // PORT_TODO: System.setOut(new StreamWriter(new he(so, "[STDOUT] ")));
         // PORT_TODO: System.setErr(new StreamWriter(new he(sp, "[STDERR] ")));
      } catch (FileNotFoundException var3) {
         var1 = null;
      }

      // PORT_TODO: sq = var1 == null ? null : new StreamWriter(var1, true);
   }

   public static void aA(string var0) {
      try {
         // PORT_TODO: Level var1 = Level.parse(var0);
         // PORT_TODO: sr = var1.intValue();
         info("HashSet<object> LogLevel: " + var0);
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
               var1.ToString();
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
               var1.ToString();
            }
         }
      }

   }

   public static string d(string var0, string var1) {
      for(int var2 = 0; var2 < var0.Length; ++var2) {
         if (!char.IsWhiteSpace(var0[var2])) {
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
               var1.ToString();
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
               var1.ToString();
            }
         }
      }

   }

   public static void log(LogRecord var0) {
      // PORT_TODO: Level var1 = var0.getLevel();
      if (true) { // PORT_TODO: original condition had errors
         // PORT_TODO: string var2 = var0.getLoggerName();
         if (true) { // PORT_TODO: original condition had errors
            // PORT_TODO: var2 = "";
         } else {
            // PORT_TODO: var2 = var2 + ": ";
         }

         // PORT_TODO: var2 = var2 + var0.getMessage();
         string var3;
         StreamWriter var4;
         if (true) { // PORT_TODO: original condition had errors
            var3 = "SEVERE";
            var4 = sp;
         } else if (var1.intValue() >= Level.WARNING.intValue()) {
            var3 = "WARNING";
            var4 = sp;
         } else if (var1.intValue() >= Level.INFO.intValue()) {
            var3 = "INFO";
            var4 = so;
         } else if (var1.intValue() >= Level.CONFIG.intValue()) {
            var3 = "CONFIG";
            var4 = so;
         } else if (var1.intValue() >= Level.FINE.intValue()) {
            var3 = "FINE";
            var4 = so;
         } else if (var1.intValue() >= Level.FINER.intValue()) {
            var3 = "FINER";
            var4 = so;
         } else if (var1.intValue() >= Level.FINEST.intValue()) {
            var3 = "FINEST";
            var4 = so;
         } else {
            var3 = "DEBUG";
            var4 = so;
         }

         if (true) { // PORT_TODO: original condition had errors
            // PORT_TODO: var4.println(d(var2, var3 + ": "));
         }

         if (sq != null) {
            lock(sq) {
               // PORT_TODO: sq.println("[" + var3 + "] " + var2.Trim());
               if (var0.getThrown() != null) {
                  sq.print("[" + var3 + "] ");
                  var0.getThrown().ToString();
               }
            }
         }

      }
   }

   public static void close() {
      sq.Close();
   }
   public static void a(LogRecord var0) {
      log(var0);
   }
   public static StreamWriter en() {
      return sq;
   }
}



}
