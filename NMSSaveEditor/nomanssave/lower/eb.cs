using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class eb : em {
   private ec[] ib;
   private gM[] ic;
   private static readonly gy[] ie;

   static eb() {
      ie = new gy[]{gy.qR, gy.qS, gy.qT};
   }

   eb(Application var1) {
      GridBagLayout var2 = new GridBagLayout();
      var2.columnWidths = new int[]{aH.cI, aH.cI, aH.cI, aH.cI, 0};
      var2.rowHeights = new int[3];
      var2.columnWeights = new double[]{0.0D, 0.0D, 0.0D, 0.0D, Double.MIN_VALUE};
      var2.rowWeights = new double[]{0.0D, 0.0D, Double.MIN_VALUE};
      this.SuspendLayout(); // TODO: set layout var2);
      this.ic = new gM[0];
      this.ib = new ec[0];
   }

   void a(gM[] var1) {
      this.ic = var1;

      for(int var2 = var1.Length; var2 < this.ib.Length; ++var2) {
         this.Remove(this.ib[var2]);
      }

      byte var6;
      if (var1.Length <= 4) {
         var6 = 2;
      } else if (var1.Length <= 6) {
         var6 = 3;
      } else {
         var6 = 4;
      }

      ec[] var4 = new ec[var1.Length];
      Array.Copy(this.ib, 0, var4, 0, Math.Min(var1.Length, this.ib.Length));

      int var5;
      for(var5 = this.ib.Length; var5 < var1.Length; ++var5) {
         var4[var5] = new ec(this, var5);
         GridBagConstraints var3 = new GridBagConstraints();
         var3.insets = new Padding(10, 10, 0, 0);
         var3.fill = 2;
         var3.anchor = 11;
         var3.gridx = var5 % var6;
         var3.gridy = var5 / var6;
         this.Add(var4[var5], var3);
      }

      this.ib = var4;

      for(var5 = 0; var5 < var1.Length; ++var5) {
         ec.g(this.ib[var5]);
      }

      this.Refresh();
   }
   static gM[] a(eb var0) {
      return var0.ic;
   }
   static gy[] aP() {
      return ie;
   }
}

}
