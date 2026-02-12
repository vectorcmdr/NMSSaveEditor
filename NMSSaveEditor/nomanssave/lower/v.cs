using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE

public class v : Runnable {
   public Application aZ;

   public v(Application var1) {
      this.aZ = var1;
   }

   public void run() {
      Application.p(this.aZ);
   }
}


#else

public class v
{
   public v() { }
   public v(params object[] args) { }
   public Application aZ = default;
   public void run() { }
}

#endif

}
