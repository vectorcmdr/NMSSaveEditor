using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class bt : ActionListener {
   bl er;
   public Application bv;

   public bt(bl var1, Application var2) {
      this.er = var1;
      this.bv = var2;
   }

   public void valueChanged(EventArgs var1) {
      System.Windows.Forms.Application.Run(new bu(this, this.bv));
   }
   public static bl a(bt var0) {
      return var0.er;
   }
}

}
