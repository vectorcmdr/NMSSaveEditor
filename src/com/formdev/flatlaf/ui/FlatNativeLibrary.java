package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.NativeLibrary;
import com.formdev.flatlaf.util.SystemInfo;
import java.io.File;
import java.net.URL;
import java.security.CodeSource;

class FlatNativeLibrary {
   private static NativeLibrary nativeLibrary;

   static synchronized boolean isLoaded() {
      initialize();
      return nativeLibrary != null ? nativeLibrary.isLoaded() : false;
   }

   private static void initialize() {
      if (nativeLibrary == null) {
         String classifier;
         String ext;
         if (!SystemInfo.isWindows_10_orLater || !SystemInfo.isX86 && !SystemInfo.isX86_64) {
            if (!SystemInfo.isLinux || !SystemInfo.isX86_64) {
               return;
            }

            classifier = "linux-x86_64";
            ext = "so";
            loadJAWT();
         } else {
            classifier = SystemInfo.isX86_64 ? "windows-x86_64" : "windows-x86";
            ext = "dll";
            loadJAWT();
         }

         nativeLibrary = createNativeLibrary(classifier, ext);
      }
   }

   private static NativeLibrary createNativeLibrary(String classifier, String ext) {
      String libraryName = "flatlaf-" + classifier;
      String libraryPath = System.getProperty("flatlaf.nativeLibraryPath");
      File libraryFile;
      if (libraryPath != null) {
         if ("system".equals(libraryPath)) {
            NativeLibrary library = new NativeLibrary(libraryName, true);
            if (library.isLoaded()) {
               return library;
            }

            LoggingFacade.INSTANCE.logSevere("Did not find library " + libraryName + " in java.library.path, using extracted library instead", (Throwable)null);
         } else {
            libraryFile = new File(libraryPath, System.mapLibraryName(libraryName));
            if (libraryFile.exists()) {
               return new NativeLibrary(libraryFile, true);
            }

            LoggingFacade.INSTANCE.logSevere("Did not find external library " + libraryFile + ", using extracted library instead", (Throwable)null);
         }
      }

      libraryFile = findLibraryBesideJar(classifier, ext);
      return libraryFile != null ? new NativeLibrary(libraryFile, true) : new NativeLibrary("com/formdev/flatlaf/natives/" + libraryName, (ClassLoader)null, true);
   }

   private static File findLibraryBesideJar(String classifier, String ext) {
      try {
         CodeSource codeSource = FlatNativeLibrary.class.getProtectionDomain().getCodeSource();
         URL jarUrl = codeSource != null ? codeSource.getLocation() : null;
         if (jarUrl == null) {
            return null;
         }

         if (!"file".equals(jarUrl.getProtocol())) {
            return null;
         }

         File jarFile = new File(jarUrl.toURI());
         if (!jarFile.isFile()) {
            return null;
         }

         String jarName = jarFile.getName();
         String jarBasename = jarName.substring(0, jarName.lastIndexOf(46));
         File parent = jarFile.getParentFile();
         String libraryName = jarBasename + (jarBasename.contains("flatlaf") ? "" : "-flatlaf") + '-' + classifier + '.' + ext;
         File libraryFile = new File(parent, libraryName);
         if (libraryFile.isFile()) {
            return libraryFile;
         }

         if (parent.getName().equalsIgnoreCase("lib")) {
            libraryFile = new File(parent.getParentFile(), "bin/" + libraryName);
            if (libraryFile.isFile()) {
               return libraryFile;
            }
         }
      } catch (Exception var10) {
         LoggingFacade.INSTANCE.logSevere(var10.getMessage(), var10);
      }

      return null;
   }

   private static void loadJAWT() {
      try {
         System.loadLibrary("jawt");
      } catch (UnsatisfiedLinkError var2) {
         String message = var2.getMessage();
         if (message == null || !message.contains("already loaded in another classloader")) {
            LoggingFacade.INSTANCE.logSevere(message, var2);
         }
      } catch (Exception var3) {
         LoggingFacade.INSTANCE.logSevere(var3.getMessage(), var3);
      }

   }
}
