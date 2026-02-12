using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using System.Globalization;

namespace NMSSaveEditor
{

public class aw : ActionListener {
   // $FF: synthetic field
   ap cu;

   aw(ap var1) {
      this.cu = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      int[] var2 = ap.h(this.cu).GetSelectedRows();
      bool var3 = false;

      for(int var4 = var2.length - 1; var4 >= 0; --var4) {
         int var5 = ap.h(this.cu).convertRowIndexToModel(var2[var4]);
         string var6 = (string)ap.d(this.cu).Get(var5);
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
