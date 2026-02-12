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

public class A : Runnable {
   // $FF: synthetic field
   public Application aZ;
   // $FF: synthetic field
   public string bc;

   public A(Application var1, string var2) {
      this.aZ = var1;
      this.bc = var2;
   }

   public void run() {
      JavaCompat.ShowOptionDialog(Application.h(this.aZ), this.bc, "Warning", 0, 2, (Icon)null, new Object[]{"OK"}, (Object)null);
   }

}


}