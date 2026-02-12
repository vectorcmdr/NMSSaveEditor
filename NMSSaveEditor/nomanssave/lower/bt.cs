using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class bt : EventHandler {
   bl er;
   private Application bv;

   bt(bl var1, Application var2) {
      this.er = var1;
      this.bv = var2;
   }

   public void valueChanged(EventArgs var1) {
      System.Windows.Forms.Application.Run(new bu(this, this.bv));
   }
   static bl a(bt var0) {
      return var0.er;
   }
}

}
