using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
class w : Runnable {
   private bool ba;

   w(bool var1) {
      this.ba = var1;
   }

   public void run() {
      try {
         Application.g(new Application(this.ba, (Application)null));
         Application.h(Application.H()).Show();
      } catch (Exception var2) {
         var2.printStackTrace();
         Environment.Exit(1);
      }

   }
}

}
