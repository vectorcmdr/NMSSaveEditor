using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class bL : TextBox {
   public bK eB;
   public bE ey;

   public bL(bE var1, bK var2, bool var3) {
      this.ey = var1;
      this.eB = var2;
      this.Enabled = (var3);
      this.addFocusListener(new bM(this, var2));
   }

   public void ac() {
      string var1;
      if (bE.a(this.ey) == null) {
         var1 = "";
      } else {
         var1 = this.eB.ab();
      }

      this.Text = (var1);
   }
   public static bE a(bL var0) {
      return var0.ey;
   }
}


#else

public class bL
{
   public bL() { }
   public bL(params object[] args) { }
   public bK eB = default;
   public bE ey = default;
   public void ac() { }
   public static bE a(bL var0) { return default; }
}

#endif

}
