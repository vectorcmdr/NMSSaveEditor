package com.formdev.flatlaf.util;

public interface LoggingFacade {
   LoggingFacade INSTANCE = new LoggingFacadeImpl();

   void logSevere(String var1, Throwable var2);

   void logConfig(String var1, Throwable var2);
}
