using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class bN : ba {
   public bE ey;

   public bN(bE var1) {
      base(aH.cJ, 0);
      this.ey = var1;
   }

   public void a(string var1, gs var2) {
      this.a(var1, (gs)var2, true, null);
   }

   public void a(string var1, gs var2, bool var3, string var4) {
      bJ var5 = new bJ(this.ey, var2, var3);
      if (var4 != null) {
         var5.setToolTipText(var4);
      }

      this.a(var1, (Control)var5);
   }

   public void a(string var1, bK var2) {
      this.a(var1, (bK)var2, true, null);
   }

   public void a(string var1, bK var2, bool var3, string var4) {
      bL var5 = new bL(this.ey, var2, var3);
      if (var4 != null) {
         var5.setToolTipText(var4);
      }

      this.a(var1, (Control)var5);
   }
}


#else

public class bN
{
   public bN() { }
   public bN(params object[] args) { }
   public bE ey = default;
   public void a(string var1, gs var2) { }
}

#endif

}
