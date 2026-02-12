using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class at : ActionListener {
   public ap cu;

   public at(ap var1) {
      this.cu = var1;
   }

   public void actionPerformed(EventArgs var1) {
      int[] var2 = ap.c(this.cu).getSelectedRows();
      bool var3 = false;

      for(int var4 = var2.Length - 1; var4 >= 0; --var4) {
         ap.a(this.cu).ac(ap.c(this.cu).convertRowIndexToModel(var2[var4]));
         var3 = true;
      }

      if (var3) {
         ap.c(this.cu).clearSelection();
         // PORT_TODO: ap.b(this.cu).sort();
         ap.c(this.cu).Refresh();
      }

   }
}



}
