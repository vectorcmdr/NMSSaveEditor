using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class z : Runnable {
   Application aZ;
   private string bc;

   z(Application var1, string var2) {
      this.aZ = var1;
      this.bc = var2;
   }

   public void run() {
      MessageBox.showOptionDialog(Application.h(this.aZ), this.bc, "Error", 0, 0, (Icon)null, new object[]{"Cancel"}, (object)null);
   }
}

}
