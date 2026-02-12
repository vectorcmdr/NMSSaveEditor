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

public class as : ActionListener {
   // $FF: synthetic field
   ap cu;

   as(ap var1) {
      this.cu = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      string[] var2 = p.b((Container)this.cu);
      bool var3 = false;

      for(int var4 = 0; var4 < var2.length; ++var4) {
         if (!ap.a(this.cu).hasValue(var2[var4])) {
            ap.a(this.cu).f(var2[var4]);
            var3 = true;
         }
      }

      if (var3) {
         ap.b(this.cu).sort();
         ap.c(this.cu).updateUI();
      }

   }
}

}
