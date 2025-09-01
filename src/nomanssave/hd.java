package nomanssave;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

class hd extends Handler {
   public void publish(LogRecord var1) {
      hc.a(var1);
   }

   public void flush() {
   }

   public void close() {
   }
}
