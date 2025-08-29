package nomanssave;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

class hd extends Handler {
  public void publish(LogRecord paramLogRecord) {
    hc.a(paramLogRecord);
  }
  
  public void flush() {}
  
  public void close() {}
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\hd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */