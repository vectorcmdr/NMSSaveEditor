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

public class bX : MouseAdapter {
   // $FF: synthetic field
   public bS fk;
   // $FF: synthetic field
   public int fl;
   // $FF: synthetic field
   public int fm;

   public bX(bS var1, int var2, int var3) {
      this.fk = var1;
      this.fl = var2;
      this.fm = var3;
   }

   public void mouseReleased(MouseEvent var1) {
      if (bO.a(bS.j(this.fk)).h(this.fl, this.fm) && !bO.a(bS.j(this.fk)).l(this.fl, this.fm)) {
         int var2 = UIManager.getInt("Inventory.gridSize");
         int var3 = this.fl + (int)Math.Floor((double)var1.getX() / (double)var2);
         int var4 = this.fm + (int)Math.Floor((double)var1.getY() / (double)var2);
         if (var3 >= 0 && var3 < bO.a(bS.j(this.fk)).Width) {
            if (var4 >= 0 && var4 < bO.a(bS.j(this.fk)).Height) {
               if (var3 != this.fl || var4 != this.fm) {
                  bS var5 = bO.a(bS.j(this.fk), var3, var4);
                  if (var5 != null && bS.e(var5) && !bS.f(var5)) {
                     if (var1.isControlDown()) {
                        bO.a(bS.j(this.fk)).a(this.fl, this.fm, var3, var4);
                     } else {
                        bO.a(bS.j(this.fk)).b(this.fl, this.fm, var3, var4);
                     }

                     bS.c(this.fk);
                     bS.c(var5);
                  }
               }
            }
         }
      }
   }

   public void mouseClicked(MouseEvent var1) {
      if (var1.getClickCount() == 2) {
         gu var2 = bO.a(bS.j(this.fk)).f(this.fl, this.fm);
         if (var2 != null) {
            cg.a((Container)bS.j(this.fk), (gQ)var2);
            bS.c(this.fk);
         }
      }

   }
}

}
