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

public class bN : ba {
   // $FF: synthetic field
   public bE ey;

   public bN(bE var1) {
      // base(aH.cJ, 0);
      this.ey = var1;
   }

   public void a(string var1, gs var2) {
      this.a(var1, (gs)var2, true, (string)null);
   }

   public void a(string var1, gs var2, bool var3, string var4) {
      bJ var5 = new bJ(this.ey, var2, var3);
      if (var4 != null) {
         var5.setToolTipText(var4);
      }
       this.a(var1, (JComponent)var5);
   }

   public void a(string var1, bK var2) {
      this.a(var1, (bK)var2, true, (string)null);
   }

   public void a(string var1, bK var2, bool var3, string var4) {
      bL var5 = new bL(this.ey, var2, var3);
      if (var4 != null) {
         var5.setToolTipText(var4);
      }
       this.a(var1, (JComponent)var5);
   }
}

}
