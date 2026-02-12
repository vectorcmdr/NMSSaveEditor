using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class ds : ActionListener {
   public dj hl;
   public Application bv;

   public ds(dj var1, Application var2) {
      this.hl = var1;
      this.bv = var2;
   }

   public void actionPerformed(EventArgs var1) {
      gv var2 = this.bv.i();
      if (var2 != null) {
         // PORT_TODO: gv[] var3 = new gv[dj.a(this.hl).Length + 1];
         int var4 = -1;

         // PORT_TODO: for(int var5 = 0; var5 < dj.a(this.hl).Length; ++var5) {
            // PORT_TODO: if (dj.a(this.hl)[var5].getIndex() < var2.getIndex()) {
               // PORT_TODO: var3[var5] = dj.a(this.hl)[var5];
            // PORT_TODO: } else {
               // PORT_TODO: var3[var5 + 1] = dj.a(this.hl)[var5];
               // PORT_TODO: if (var4 < 0) {
                  // PORT_TODO: var4 = var5;
               // PORT_TODO: }
            // PORT_TODO: }
         // PORT_TODO: }

         if (var4 < 0) {
            // PORT_TODO: var4 = dj.a(this.hl).Length;
         }

         // PORT_TODO: var3[var4] = var2;
         // PORT_TODO: dj.a(this.hl, var3);
         // PORT_TODO: dj.j(this.hl).SelectedIndex = (var4);
         // PORT_TODO: dj.j(this.hl).Refresh();
      }

   }
}



}
