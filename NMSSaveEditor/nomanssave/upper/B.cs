using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class B : object {
   public Application aZ;

   public B(Application var1) {
      this.aZ = var1;
   }

   public void windowClosing(FormClosedEventArgs var1) {
      if (Application.i(this.aZ) || Application.j(this.aZ)) {
         int var2 = (int)(int)MessageBox.Show(Application.h(this.aZ), "Save data before closing?", "Save", 0);
         if (var2 == 0) {
            if (Application.i(this.aZ)) {
               Application.k(this.aZ);
            }

            if (Application.j(this.aZ)) {
               Application.l(this.aZ);
            }
         }
      }

      if (aH.T()) {
         aH.U();
      }

      Application.h(this.aZ).Dispose();
   }

   public void windowDeactivated(FormClosedEventArgs var1) {
      Application.e(this.aZ, true);
   }

   public void windowActivated(FormClosedEventArgs var1) {
      Application.e(this.aZ, false);
      Application.m(this.aZ);
   }
}



}
