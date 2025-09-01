package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.SystemInfo;
import java.awt.Window;

public class FlatNativeWindowsLibrary {
   private static long osBuildNumber = Long.MIN_VALUE;
   public static final int DWMWCP_DEFAULT = 0;
   public static final int DWMWCP_DONOTROUND = 1;
   public static final int DWMWCP_ROUND = 2;
   public static final int DWMWCP_ROUNDSMALL = 3;

   public static boolean isLoaded() {
      return SystemInfo.isWindows && FlatNativeLibrary.isLoaded();
   }

   public static long getOSBuildNumber() {
      if (osBuildNumber == Long.MIN_VALUE) {
         osBuildNumber = getOSBuildNumberImpl();
      }

      return osBuildNumber;
   }

   private static native long getOSBuildNumberImpl();

   public static native long getHWND(Window var0);

   public static native boolean setWindowCornerPreference(long var0, int var2);

   public static native boolean setWindowBorderColor(long var0, int var2, int var3, int var4);
}
