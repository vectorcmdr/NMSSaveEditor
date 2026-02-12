using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{


public class v : Runnable {
   public Application aZ;

   public v(Application var1) {
      this.aZ = var1;
   }

   public void run() {
      Application.p(this.aZ);
   }
}



}
