using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class A : Runnable {
   Application aZ;
   private string bc;

   A(Application var1, string var2) {
      this.aZ = var1;
      this.bc = var2;
   }

   public void run() {
      MessageBox.showOptionDialog(Application.h(this.aZ), this.bc, "Warning", 0, 2, (Icon)null, new object[]{"OK"}, (object)null);
   }
}

}
