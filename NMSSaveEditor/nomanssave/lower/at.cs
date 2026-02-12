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

public class at : ActionListener {
   // $FF: synthetic field
   public ap cu;

   public at(ap var1) {
      this.cu = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      int[] var2 = ap.c(this.cu).GetSelectedRows();
      bool var3 = false;

      for(int var4 = var2.length - 1; var4 >= 0; --var4) {
         ap.a(this.cu).ac(ap.c(this.cu).convertRowIndexToModel(var2[var4]));
         var3 = true;
      }

      if (var3) {
         ap.c(this.cu).clearSelection();
         ap.b(this.cu).sort();
         ap.c(this.cu).updateUI();
      }

   }
}

}
