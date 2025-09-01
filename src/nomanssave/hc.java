package nomanssave;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class hc {
   private static final Handler sn = new hd();
   private static final PrintStream so;
   private static final PrintStream sp;
   private static PrintStream sq;
   private static int sr;

   static {
      Logger var0 = LogManager.getLogManager().getLogger("");
      Handler[] var1 = var0.getHandlers();

      for(int var2 = 0; var2 < var1.length; ++var2) {
         var0.removeHandler(var1[0]);
      }

      var0.setLevel(Level.ALL);
      var0.addHandler(sn);
      em();
      so = System.out;
      sp = System.err;
      sr = Level.INFO.intValue();
   }

   private static void em() {
      try {
         Class var0 = Class.forName("sun.misc.Unsafe");
         Field var1 = var0.getDeclaredField("theUnsafe");
         var1.setAccessible(true);
         Object var2 = var1.get((Object)null);
         Method var3 = var0.getDeclaredMethod("putObjectVolatile", Object.class, Long.TYPE, Object.class);
         Method var4 = var0.getDeclaredMethod("staticFieldOffset", Field.class);
         Class var5 = Class.forName("jdk.internal.module.IllegalAccessLogger");
         Field var6 = var5.getDeclaredField("logger");
         Long var7 = (Long)var4.invoke(var2, var6);
         var3.invoke(var2, var5, var7, null);
      } catch (Throwable var8) {
      }

   }

   public static void k(File var0) {
      FileOutputStream var1;
      try {
         var1 = new FileOutputStream(var0);
         Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            close();
         }));
         System.setOut(new PrintStream(new he(so, "[STDOUT] ")));
         System.setErr(new PrintStream(new he(sp, "[STDERR] ")));
      } catch (FileNotFoundException var3) {
         var1 = null;
      }

      sq = var1 == null ? null : new PrintStream(var1, true);
   }

   public static void aA(String var0) {
      try {
         Level var1 = Level.parse(var0);
         sr = var1.intValue();
         info("Set LogLevel: " + var0);
      } catch (IllegalArgumentException var2) {
         warn("Invalid LogLevel: " + var0);
      }

   }

   public static void debug(String var0) {
      if (sq != null) {
         synchronized(sq) {
            sq.println("[DEBUG] " + var0.trim());
         }
      }

   }

   public static void debug(String var0, Throwable var1) {
      if (sq != null) {
         synchronized(sq) {
            sq.println("[DEBUG] " + var0.trim());
            if (var1 != null) {
               sq.print("[DEBUG] ");
               var1.printStackTrace(sq);
            }
         }
      }

   }

   public static void info(String var0) {
      so.println(var0);
      if (sq != null) {
         synchronized(sq) {
            sq.println("[INFO] " + var0.trim());
         }
      }

   }

   public static void info(String var0, Throwable var1) {
      so.println(var0);
      if (sq != null) {
         synchronized(sq) {
            sq.println("[INFO] " + var0.trim());
            if (var1 != null) {
               sq.print("[INFO] ");
               var1.printStackTrace(sq);
            }
         }
      }

   }

   private static String d(String var0, String var1) {
      for(int var2 = 0; var2 < var0.length(); ++var2) {
         if (!Character.isWhitespace(var0.charAt(var2))) {
            return var0.substring(0, var2) + var1 + var0.substring(var2);
         }
      }

      return "";
   }

   public static void warn(String var0) {
      so.println(d(var0, "WARNING: "));
      if (sq != null) {
         synchronized(sq) {
            sq.println("[WARNING] " + var0.trim());
         }
      }

   }

   public static void a(String var0, Throwable var1) {
      so.println(d(var0, "WARNING: "));
      if (sq != null) {
         synchronized(sq) {
            sq.println("[WARNING] " + var0.trim());
            if (var1 != null) {
               sq.print("[WARNING] ");
               var1.printStackTrace(sq);
            }
         }
      }

   }

   public static void error(String var0, Throwable var1) {
      sp.println(d(var0, "ERROR: "));
      if (sq != null) {
         synchronized(sq) {
            sq.println("[ERROR] " + var0.trim());
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
         String var2 = var0.getLoggerName();
         if (var2 == null) {
            var2 = "";
         } else {
            var2 = var2 + ": ";
         }

         var2 = var2 + var0.getMessage();
         String var3;
         PrintStream var4;
         if (var1.intValue() >= Level.SEVERE.intValue()) {
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

         if (var1.intValue() >= Level.INFO.intValue()) {
            var4.println(d(var2, var3 + ": "));
         }

         if (sq != null) {
            synchronized(sq) {
               sq.println("[" + var3 + "] " + var2.trim());
               if (var0.getThrown() != null) {
                  sq.print("[" + var3 + "] ");
                  var0.getThrown().printStackTrace(sq);
               }
            }
         }

      }
   }

   private static void close() {
      sq.close();
   }

   // $FF: synthetic method
   static void a(LogRecord var0) {
      log(var0);
   }

   // $FF: synthetic method
   static PrintStream en() {
      return sq;
   }
}
