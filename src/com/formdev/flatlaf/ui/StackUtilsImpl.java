package com.formdev.flatlaf.ui;

import java.util.function.BiPredicate;

class StackUtilsImpl extends StackUtils {
   boolean wasInvokedFromImpl(BiPredicate<String, String> predicate, int limit) {
      int count = -2;
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      StackTraceElement[] var5 = stackTrace;
      int var6 = stackTrace.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         StackTraceElement stackTraceElement = var5[var7];
         if (predicate.test(stackTraceElement.getClassName(), stackTraceElement.getMethodName())) {
            return true;
         }

         ++count;
         if (limit > 0 && count > limit) {
            return false;
         }
      }

      return false;
   }
}
