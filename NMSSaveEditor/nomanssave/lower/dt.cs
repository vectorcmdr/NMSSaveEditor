using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class dt : ba {
   public Application eR;
   public Panel eS;

public dt(Application var1) : base(aH.cJ, 0) {
      this.eR = var1;
      this.k("Production");
      this.eS = new Panel();
      this.eS.SuspendLayout(); // TODO: set layout new GridBagLayout());
      this.a((Control)this.eS);
      /* TODO: port from Java - addPropertyChangeListener for lookAndFeel */
   }

   public void aL() {
      lock(this.eS.getTreeLock()) {
         Component[] var5;
         int var4 = (var5 = this.eS.getComponents()).Length;
         int var3 = 0;

         while(true) {
            if (var3 >= var4) {
               break;
            }

            Component var2 = var5[var3];
            du var6 = (du)var2;
            du.c(var6);
            ++var3;
         }
      }

      this.eS.PerformLayout();
      this.eS.Refresh();
   }

   public void a(gF[] var1) {
      lock(this.eS.getTreeLock()) {
         this.eS.RemoveAll();
         int var3 = 0;

         while(true) {
            if (var3 >= var1.Length) {
               break;
            }

            du var4 = new du(this, var1[var3], (du)null);
            GridBagConstraints var5 = new GridBagConstraints();
            var5.fill = 1;
            var5.insets = new Padding(10, 10, 10, 10);
            var5.gridx = var3 % 3;
            var5.gridy = var3 / 3;
            this.eS.Add(var4, var5);
            ++var3;
         }
      }

      this.eS.PerformLayout();
      this.eS.Refresh();
   }

   public void a(du var1) {
      ey var2 = h.a(this, 28160);
      if (var2 != null) {
         var1.hm.m(var2.aZ());
         var1.hm.aA(0);
         du.c(var1);
      }

   }

   public void b(du var1) {
      ey var2 = ey.d(var1.hm.dz());
      if (var2 == null) {
         this.eR.c("Item details not found!");
      } else {
         List<object> var3 = this.eR.g(3584);
         int var4 = dd.a(this, var3, -1);
         if (var4 != -1) {
            gt var5 = (gt)var3[var4];
            int var6 = var1.hm.dA();
            ey var7 = ey.d(var1.hm.dz());
            var6 = var5.a(var7, var6);
            var1.hm.aA(var6);
            du.c(var1);
            this.eR.a(var5);
         }

      }
   }
   public static void a(dt var0, du var1) {
      var0.a(var1);
   }
   public static void b(dt var0, du var1) {
      var0.b(var1);
   }
}



}
