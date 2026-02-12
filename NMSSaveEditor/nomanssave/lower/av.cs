using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class av : EventHandler {
   ap cu;

   av(ap var1) {
      this.cu = var1;
   }

   public void actionPerformed(EventArgs var1) {
      string[] var2 = p.c((Container)this.cu);
      bool var3 = false;

      for(int var4 = 0; var4 < var2.Length; ++var4) {
         ey var5 = ey.d(var2[var4]);
         if (!ap.d(this.cu).Contains(var2[var4])) {
            if (var5.be()) {
               ap.e(this.cu).f(var2[var4]);
            }

            if (var5.bd()) {
               ap.f(this.cu).f(var2[var4]);
            }

            ap.d(this.cu).Add(var2[var4]);
            var3 = true;
         }
      }

      if (var3) {
         ap.g(this.cu).sort();
         ap.h(this.cu).updateUI();
      }

   }
}

}
