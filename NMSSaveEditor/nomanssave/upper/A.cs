using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class A : Runnable {
   public Application aZ;
   public string bc;

   public A(Application var1, string var2) {
      this.aZ = var1;
      this.bc = var2;
   }

   public void run() {
      MessageBox.Show(this.bc, "Warning");
   }
}

}
