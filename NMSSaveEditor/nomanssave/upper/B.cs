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

public class B : WindowAdapter {
   // $FF: synthetic field
   public Application aZ;

   public B(Application var1) {
      this.aZ = var1;
   }

   public void windowClosing(WindowEvent var1) {
      if (Application.i(this.aZ) || Application.j(this.aZ)) {
         int var2 = MessageBox.Show("Save data before closing?".ToString(), "Save".ToString(), MessageBoxButtons.YesNo);
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

   public void windowDeactivated(WindowEvent var1) {
      Application.e(this.aZ, true);
   }

   public void windowActivated(WindowEvent var1) {
      Application.e(this.aZ, false);
      Application.m(this.aZ);
   }
}

}
