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

public class ds : ActionListener {
   // $FF: synthetic field
   public dj hl;
   // $FF: synthetic field
   public Application bv;

   public ds(dj var1, Application var2) {
      this.hl = var1;
      this.bv = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      gv var2 = this.bv.i();
      if (var2 != null) {
         gv[] var3 = new gv[dj.a(this.hl).length + 1];
         int var4 = -1;

         for(int var5 = 0; var5 < dj.a(this.hl).length; ++var5) {
            if (dj.a(this.hl)[var5].getIndex() < var2.getIndex()) {
               var3[var5] = dj.a(this.hl)[var5];
            } else {
               var3[var5 + 1] = dj.a(this.hl)[var5];
               if (var4 < 0) {
                  var4 = var5;
               }
            }
         }

         if (var4 < 0) {
            var4 = dj.a(this.hl).length;
         }

         var3[var4] = var2;
         dj.a(this.hl, var3);
         dj.j(this.hl).SetSelectedIndex(var4);
         dj.j(this.hl).updateUI();
      }

   }
}

}
