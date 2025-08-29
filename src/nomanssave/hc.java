package nomanssave;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class hc {
  private static final Handler sk = new hd();
  
  private static final PrintStream sl = System.out;
  
  private static final PrintStream sm = System.err;
  
  private static PrintStream sn;
  
  private static int so = Level.INFO.intValue();
  
  private static void em() {
    try {
      Class<?> clazz1 = Class.forName("sun.misc.Unsafe");
      Field field1 = clazz1.getDeclaredField("theUnsafe");
      field1.setAccessible(true);
      Object object = field1.get((Object)null);
      Method method1 = clazz1.getDeclaredMethod("putObjectVolatile", new Class[] { Object.class, long.class, Object.class });
      Method method2 = clazz1.getDeclaredMethod("staticFieldOffset", new Class[] { Field.class });
      Class<?> clazz2 = Class.forName("jdk.internal.module.IllegalAccessLogger");
      Field field2 = clazz2.getDeclaredField("logger");
      Long long_ = (Long)method2.invoke(object, new Object[] { field2 });
      method1.invoke(object, new Object[] { clazz2, long_, null });
    } catch (Throwable throwable) {}
  }
  
  public static void k(File paramFile) {
    OutputStream outputStream;
    try {
      outputStream = new FileOutputStream(paramFile);
      Runtime.getRuntime().addShutdownHook(new Thread(() -> close()));
      System.setOut(new PrintStream(new he(sl, "[STDOUT] ")));
      System.setErr(new PrintStream(new he(sm, "[STDERR] ")));
    } catch (FileNotFoundException fileNotFoundException) {
      outputStream = null;
    } 
    sn = (outputStream == null) ? null : new PrintStream(outputStream, true);
  }
  
  public static void aA(String paramString) {
    try {
      Level level = Level.parse(paramString);
      so = level.intValue();
      info("Set LogLevel: " + paramString);
    } catch (IllegalArgumentException illegalArgumentException) {
      warn("Invalid LogLevel: " + paramString);
    } 
  }
  
  public static void debug(String paramString) {
    if (sn != null)
      synchronized (sn) {
        sn.println("[DEBUG] " + paramString.trim());
      }  
  }
  
  public static void debug(String paramString, Throwable paramThrowable) {
    if (sn != null)
      synchronized (sn) {
        sn.println("[DEBUG] " + paramString.trim());
        if (paramThrowable != null) {
          sn.print("[DEBUG] ");
          paramThrowable.printStackTrace(sn);
        } 
      }  
  }
  
  public static void info(String paramString) {
    sl.println(paramString);
    if (sn != null)
      synchronized (sn) {
        sn.println("[INFO] " + paramString.trim());
      }  
  }
  
  public static void info(String paramString, Throwable paramThrowable) {
    sl.println(paramString);
    if (sn != null)
      synchronized (sn) {
        sn.println("[INFO] " + paramString.trim());
        if (paramThrowable != null) {
          sn.print("[INFO] ");
          paramThrowable.printStackTrace(sn);
        } 
      }  
  }
  
  private static String d(String paramString1, String paramString2) {
    for (byte b = 0; b < paramString1.length(); b++) {
      if (!Character.isWhitespace(paramString1.charAt(b)))
        return String.valueOf(paramString1.substring(0, b)) + paramString2 + paramString1.substring(b); 
    } 
    return "";
  }
  
  public static void warn(String paramString) {
    sl.println(d(paramString, "WARNING: "));
    if (sn != null)
      synchronized (sn) {
        sn.println("[WARNING] " + paramString.trim());
      }  
  }
  
  public static void a(String paramString, Throwable paramThrowable) {
    sl.println(d(paramString, "WARNING: "));
    if (sn != null)
      synchronized (sn) {
        sn.println("[WARNING] " + paramString.trim());
        if (paramThrowable != null) {
          sn.print("[WARNING] ");
          paramThrowable.printStackTrace(sn);
        } 
      }  
  }
  
  public static void error(String paramString, Throwable paramThrowable) {
    sm.println(d(paramString, "ERROR: "));
    if (sn != null)
      synchronized (sn) {
        sn.println("[ERROR] " + paramString.trim());
        if (paramThrowable != null) {
          sn.print("[ERROR] ");
          paramThrowable.printStackTrace(sn);
        } 
      }  
  }
  
  private static void log(LogRecord paramLogRecord) {
    String str2;
    PrintStream printStream;
    Level level = paramLogRecord.getLevel();
    if (level.intValue() < so)
      return; 
    String str1 = paramLogRecord.getLoggerName();
    if (str1 == null) {
      str1 = "";
    } else {
      str1 = String.valueOf(str1) + ": ";
    } 
    str1 = String.valueOf(str1) + paramLogRecord.getMessage();
    if (level.intValue() >= Level.SEVERE.intValue()) {
      str2 = "SEVERE";
      printStream = sm;
    } else if (level.intValue() >= Level.WARNING.intValue()) {
      str2 = "WARNING";
      printStream = sm;
    } else if (level.intValue() >= Level.INFO.intValue()) {
      str2 = "INFO";
      printStream = sl;
    } else if (level.intValue() >= Level.CONFIG.intValue()) {
      str2 = "CONFIG";
      printStream = sl;
    } else if (level.intValue() >= Level.FINE.intValue()) {
      str2 = "FINE";
      printStream = sl;
    } else if (level.intValue() >= Level.FINER.intValue()) {
      str2 = "FINER";
      printStream = sl;
    } else if (level.intValue() >= Level.FINEST.intValue()) {
      str2 = "FINEST";
      printStream = sl;
    } else {
      str2 = "DEBUG";
      printStream = sl;
    } 
    if (level.intValue() >= Level.INFO.intValue())
      printStream.println(d(str1, String.valueOf(str2) + ": ")); 
    if (sn != null)
      synchronized (sn) {
        sn.println("[" + str2 + "] " + str1.trim());
        if (paramLogRecord.getThrown() != null) {
          sn.print("[" + str2 + "] ");
          paramLogRecord.getThrown().printStackTrace(sn);
        } 
      }  
  }
  
  private static void close() {
    sn.close();
  }
  
  static {
    Logger logger = LogManager.getLogManager().getLogger("");
    Handler[] arrayOfHandler = logger.getHandlers();
    for (byte b = 0; b < arrayOfHandler.length; b++)
      logger.removeHandler(arrayOfHandler[0]); 
    logger.setLevel(Level.ALL);
    logger.addHandler(sk);
    em();
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\hc.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */