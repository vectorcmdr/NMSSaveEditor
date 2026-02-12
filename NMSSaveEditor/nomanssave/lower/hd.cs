using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class hd : Handler {
   public void publish(LogRecord var1) {
      hc.a(var1);
   }

   public void flush() {
   }

   public void close() {
   }
}


#else

public class hd
{
   public hd() { }
   public hd(params object[] args) { }
   public void publish(LogRecord var1) { }
   public void flush() { }
   public void close() { }
}

#endif

}
