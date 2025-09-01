package com.formdev.flatlaf.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class NativeLibrary {
   private static final String DELETE_SUFFIX = ".delete";
   private static boolean deletedTemporary;
   private final boolean loaded;

   public NativeLibrary(String libraryName, ClassLoader classLoader, boolean supported) {
      this.loaded = supported ? loadLibraryFromJar(libraryName, classLoader) : false;
   }

   public NativeLibrary(File libraryFile, boolean supported) {
      this.loaded = supported ? this.loadLibraryFromFile(libraryFile) : false;
   }

   public NativeLibrary(String libraryName, boolean supported) {
      this.loaded = supported ? this.loadLibraryFromSystem(libraryName) : false;
   }

   public boolean isLoaded() {
      return this.loaded;
   }

   private static boolean loadLibraryFromJar(String libraryName, ClassLoader classLoader) {
      libraryName = decorateLibraryName(libraryName);
      URL libraryUrl = classLoader != null ? classLoader.getResource(libraryName) : NativeLibrary.class.getResource("/" + libraryName);
      if (libraryUrl == null) {
         LoggingFacade.INSTANCE.logSevere("Library '" + libraryName + "' not found", (Throwable)null);
         return false;
      } else {
         File tempFile = null;

         try {
            if ("file".equals(libraryUrl.getProtocol())) {
               File libraryFile = new File(libraryUrl.getPath());
               if (libraryFile.isFile()) {
                  System.load(libraryFile.getCanonicalPath());
                  return true;
               }
            }

            Path tempPath = createTempFile(libraryName);
            tempFile = tempPath.toFile();
            InputStream in = libraryUrl.openStream();

            try {
               Files.copy(in, tempPath, new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
            } catch (Throwable var9) {
               if (in != null) {
                  try {
                     in.close();
                  } catch (Throwable var8) {
                     var9.addSuppressed(var8);
                  }
               }

               throw var9;
            }

            if (in != null) {
               in.close();
            }

            System.load(tempFile.getCanonicalPath());
            deleteOrMarkForDeletion(tempFile);
            return true;
         } catch (Throwable var10) {
            LoggingFacade.INSTANCE.logSevere(var10.getMessage(), var10);
            if (tempFile != null) {
               deleteOrMarkForDeletion(tempFile);
            }

            return false;
         }
      }
   }

   private boolean loadLibraryFromFile(File libraryFile) {
      try {
         System.load(libraryFile.getAbsolutePath());
         return true;
      } catch (Throwable var3) {
         LoggingFacade.INSTANCE.logSevere(var3.getMessage(), var3);
         return false;
      }
   }

   private boolean loadLibraryFromSystem(String libraryName) {
      try {
         System.loadLibrary(libraryName);
         return true;
      } catch (Throwable var4) {
         String message = var4.getMessage();
         if (var4 instanceof UnsatisfiedLinkError && message != null && message.contains("java.library.path")) {
            return false;
         } else {
            LoggingFacade.INSTANCE.logSevere(message, var4);
            return false;
         }
      }
   }

   private static String decorateLibraryName(String libraryName) {
      int sep = libraryName.lastIndexOf(47);
      return sep >= 0 ? libraryName.substring(0, sep + 1) + System.mapLibraryName(libraryName.substring(sep + 1)) : System.mapLibraryName(libraryName);
   }

   private static Path createTempFile(String libraryName) throws IOException {
      int sep = libraryName.lastIndexOf(47);
      String name = sep >= 0 ? libraryName.substring(sep + 1) : libraryName;
      int dot = name.lastIndexOf(46);
      String prefix = (dot >= 0 ? name.substring(0, dot) : name) + '-';
      String suffix = dot >= 0 ? name.substring(dot) : "";
      Path tempDir = getTempDir();
      long nanoTime = System.nanoTime();
      int i = 0;

      while(true) {
         String s = prefix + Long.toUnsignedString(nanoTime) + i + suffix;

         try {
            return Files.createFile(tempDir.resolve(s));
         } catch (FileAlreadyExistsException var12) {
            ++i;
         }
      }
   }

   private static Path getTempDir() throws IOException {
      String tmpdir = System.getProperty("java.io.tmpdir");
      if (SystemInfo.isWindows) {
         tmpdir = tmpdir + "\\flatlaf.temp";
      }

      Path tempDir = Paths.get(tmpdir);
      Files.createDirectories(tempDir);
      if (SystemInfo.isWindows) {
         deleteTemporaryFiles(tempDir);
      }

      return tempDir;
   }

   private static void deleteTemporaryFiles(Path tempDir) {
      if (!deletedTemporary) {
         deletedTemporary = true;
         File[] markerFiles = tempDir.toFile().listFiles((dir, name) -> {
            return name.endsWith(".delete");
         });
         if (markerFiles != null) {
            File[] var2 = markerFiles;
            int var3 = markerFiles.length;

            for(int var4 = 0; var4 < var3; ++var4) {
               File markerFile = var2[var4];
               File toDeleteFile = new File(markerFile.getParent(), StringUtils.removeTrailing(markerFile.getName(), ".delete"));
               if (!toDeleteFile.exists() || toDeleteFile.delete()) {
                  markerFile.delete();
               }
            }

         }
      }
   }

   private static void deleteOrMarkForDeletion(File file) {
      if (!file.delete()) {
         try {
            File markFile = new File(file.getParent(), file.getName() + ".delete");
            markFile.createNewFile();
         } catch (IOException var2) {
         }

      }
   }
}
