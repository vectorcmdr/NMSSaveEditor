package com.formdev.flatlaf.util;

import com.formdev.flatlaf.FlatLaf;
import java.util.logging.Level;
import java.util.logging.Logger;

class LoggingFacadeImpl implements LoggingFacade {
   private static final Logger LOG = Logger.getLogger(FlatLaf.class.getName());

   public void logSevere(String message, Throwable t) {
      LOG.log(Level.SEVERE, message, t);
   }

   public void logConfig(String message, Throwable t) {
      LOG.log(Level.CONFIG, message, t);
   }
}
