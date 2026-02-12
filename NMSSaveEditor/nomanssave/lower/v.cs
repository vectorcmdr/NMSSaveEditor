using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
class v : Runnable {
   Application aZ;

   v(Application var1) {
      this.aZ = var1;
   }

   public void run() {
      Application.p(this.aZ);
   }
}

}
