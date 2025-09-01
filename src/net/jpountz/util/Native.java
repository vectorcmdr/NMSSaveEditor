package net.jpountz.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;

public enum Native {
   private static boolean loaded = false;

   private static String arch() {
      return System.getProperty("os.arch");
   }

   private static Native.OS os() {
      String osName = System.getProperty("os.name");
      if (osName.contains("Linux")) {
         return Native.OS.LINUX;
      } else if (osName.contains("Mac")) {
         return Native.OS.MAC;
      } else if (osName.contains("Windows")) {
         return Native.OS.WINDOWS;
      } else if (!osName.contains("Solaris") && !osName.contains("SunOS")) {
         throw new UnsupportedOperationException("Unsupported operating system: " + osName);
      } else {
         return Native.OS.SOLARIS;
      }
   }

   private static String resourceName() {
      Native.OS os = os();
      String packagePrefix = Native.class.getPackage().getName().replace('.', '/');
      return "/" + packagePrefix + "/" + os.name + "/" + arch() + "/liblz4-java." + os.libExtension;
   }

   public static synchronized boolean isLoaded() {
      return loaded;
   }

   private static void cleanupOldTempLibs() {
      String tempFolder = (new File(System.getProperty("java.io.tmpdir"))).getAbsolutePath();
      File dir = new File(tempFolder);
      File[] tempLibFiles = dir.listFiles(new FilenameFilter() {
         private final String searchPattern = "liblz4-java-";

         public boolean accept(File dir, String name) {
            return name.startsWith("liblz4-java-") && !name.endsWith(".lck");
         }
      });
      if (tempLibFiles != null) {
         File[] arr$ = tempLibFiles;
         int len$ = tempLibFiles.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            File tempLibFile = arr$[i$];
            File lckFile = new File(tempLibFile.getAbsolutePath() + ".lck");
            if (!lckFile.exists()) {
               try {
                  tempLibFile.delete();
               } catch (SecurityException var9) {
                  System.err.println("Failed to delete old temp lib" + var9.getMessage());
               }
            }
         }
      }

   }

   public static synchronized void load() {
      if (!loaded) {
         cleanupOldTempLibs();

         try {
            System.loadLibrary("lz4-java");
            loaded = true;
         } catch (UnsatisfiedLinkError var33) {
            String resourceName = resourceName();
            InputStream is = Native.class.getResourceAsStream(resourceName);
            if (is == null) {
               throw new UnsupportedOperationException("Unsupported OS/arch, cannot find " + resourceName + ". Please try building from source.");
            } else {
               File tempLib = null;
               File tempLibLock = null;
               boolean var20 = false;

               try {
                  var20 = true;
                  tempLibLock = File.createTempFile("liblz4-java-", "." + os().libExtension + ".lck");
                  tempLib = new File(tempLibLock.getAbsolutePath().replaceFirst(".lck$", ""));
                  FileOutputStream out = new FileOutputStream(tempLib);
                  Throwable var5 = null;

                  try {
                     byte[] buf = new byte[4096];

                     while(true) {
                        int read = is.read(buf);
                        if (read == -1) {
                           break;
                        }

                        out.write(buf, 0, read);
                     }
                  } catch (Throwable var29) {
                     var5 = var29;
                     throw var29;
                  } finally {
                     if (out != null) {
                        if (var5 != null) {
                           try {
                              out.close();
                           } catch (Throwable var28) {
                              var5.addSuppressed(var28);
                           }
                        } else {
                           out.close();
                        }
                     }

                  }

                  System.load(tempLib.getAbsolutePath());
                  loaded = true;
                  var20 = false;
               } catch (IOException var31) {
                  throw new ExceptionInInitializerError("Cannot unpack liblz4-java: " + var31);
               } finally {
                  if (var20) {
                     if (!loaded) {
                        if (tempLib != null && tempLib.exists() && !tempLib.delete()) {
                           throw new ExceptionInInitializerError("Cannot unpack liblz4-java / cannot delete a temporary native library " + tempLib);
                        }

                        if (tempLibLock != null && tempLibLock.exists() && !tempLibLock.delete()) {
                           throw new ExceptionInInitializerError("Cannot unpack liblz4-java / cannot delete a temporary lock file " + tempLibLock);
                        }
                     } else {
                        String keepEnv = System.getenv("LZ4JAVA_KEEP_TEMP_JNI_LIB");
                        String keepProp = System.getProperty("lz4java.jnilib.temp.keep");
                        if ((keepEnv == null || !keepEnv.equals("true")) && (keepProp == null || !keepProp.equals("true"))) {
                           tempLib.deleteOnExit();
                        }

                        tempLibLock.deleteOnExit();
                     }

                  }
               }

               if (!loaded) {
                  if (tempLib != null && tempLib.exists() && !tempLib.delete()) {
                     throw new ExceptionInInitializerError("Cannot unpack liblz4-java / cannot delete a temporary native library " + tempLib);
                  }

                  if (tempLibLock != null && tempLibLock.exists() && !tempLibLock.delete()) {
                     throw new ExceptionInInitializerError("Cannot unpack liblz4-java / cannot delete a temporary lock file " + tempLibLock);
                  }
               } else {
                  String keepEnv = System.getenv("LZ4JAVA_KEEP_TEMP_JNI_LIB");
                  String keepProp = System.getProperty("lz4java.jnilib.temp.keep");
                  if ((keepEnv == null || !keepEnv.equals("true")) && (keepProp == null || !keepProp.equals("true"))) {
                     tempLib.deleteOnExit();
                  }

                  tempLibLock.deleteOnExit();
               }

            }
         }
      }
   }

   private static enum OS {
      WINDOWS("win32", "so"),
      LINUX("linux", "so"),
      MAC("darwin", "dylib"),
      SOLARIS("solaris", "so");

      public final String name;
      public final String libExtension;

      private OS(String name, String libExtension) {
         this.name = name;
         this.libExtension = libExtension;
      }
   }
}
