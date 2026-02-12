using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class ck : object {
   cg fF;

   ck(cg var1) {
      this.fF = var1;
   }

   public void windowClosing(FormClosedEventArgs var1) {
      cg.e(this.fF).N();
      cg.f(this.fF).N();
   }
}

}
