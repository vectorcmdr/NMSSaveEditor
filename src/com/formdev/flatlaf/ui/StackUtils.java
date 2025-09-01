package com.formdev.flatlaf.ui;

import java.util.function.BiPredicate;

class StackUtils {
   private static final StackUtils INSTANCE = new StackUtilsImpl();

   public static boolean wasInvokedFrom(String className, String methodName, int limit) {
      return wasInvokedFrom((c, m) -> {
         return c.equals(className) && m.equals(methodName);
      }, limit);
   }

   public static boolean wasInvokedFrom(BiPredicate<String, String> predicate, int limit) {
      return INSTANCE.wasInvokedFromImpl(predicate, limit);
   }

   boolean wasInvokedFromImpl(BiPredicate<String, String> predicate, int limit) {
      throw new UnsupportedOperationException();
   }
}
