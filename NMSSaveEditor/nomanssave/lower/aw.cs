using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class aw : EventHandler {
   ap cu;

   aw(ap var1) {
      this.cu = var1;
   }

   public void actionPerformed(EventArgs var1) {
      int[] var2 = ap.h(this.cu).getSelectedRows();
      bool var3 = false;

      for(int var4 = var2.Length - 1; var4 >= 0; --var4) {
         int var5 = ap.h(this.cu).convertRowIndexToModel(var2[var4]);
         string var6 = (string)ap.d(this.cu).get(var5);
         ap.d(this.cu).Remove(var5);

         while((var5 = ap.e(this.cu).IndexOf(var6)) >= 0) {
            ap.e(this.cu).ac(var5);
         }

         while((var5 = ap.f(this.cu).IndexOf(var6)) >= 0) {
            ap.f(this.cu).ac(var5);
         }

         var3 = true;
      }

      if (var3) {
         ap.h(this.cu).clearSelection();
         ap.g(this.cu).sort();
         ap.h(this.cu).updateUI();
      }

   }
}

}
