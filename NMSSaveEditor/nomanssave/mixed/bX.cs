using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class bX : object {
   public bS fk;
   public int fl;
   public int fm;

   public bX(bS var1, int var2, int var3) {
      this.fk = var1;
      this.fl = var2;
      this.fm = var3;
   }

   public void mouseReleased(MouseEventArgs var1) {
      if (bO.a(bS.j(this.fk)).h(this.fl, this.fm) && !bO.a(bS.j(this.fk)).l(this.fl, this.fm)) {
         int var2 = 0 /* UIManager.getInt("Inventory.gridSize") */;
         // PORT_TODO: int var3 = this.fl + (int)Math.Floor((double)var1.Left / (double)var2);
         // PORT_TODO: int var4 = this.fm + (int)Math.Floor((double)var1.Top / (double)var2);
         if (true) { // PORT_TODO: original condition had errors
            if (true) { // PORT_TODO: original condition had errors
               if (true) { // PORT_TODO: original condition had errors
                  // PORT_TODO: bS var5 = bO.a(bS.j(this.fk), var3, var4);
                  // PORT_TODO: if (var5 != null && bS.e(var5) && !bS.f(var5)) {
      // PORT_TODO: bS var5 = null; // PORT_TODO: stub declaration
                     // PORT_TODO: if (true) { // PORT_TODO: original condition had errors
                        // PORT_TODO: bO.a(bS.j(this.fk)).a(this.fl, this.fm, var3, var4);
                     // PORT_TODO: } else {
                        // PORT_TODO: bO.a(bS.j(this.fk)).b(this.fl, this.fm, var3, var4);
                     // PORT_TODO: }

// PORT_TODO: 
                     // PORT_TODO: bS.c(this.fk);
                     // PORT_TODO: bS.c(var5);
                  // PORT_TODO: }
               }
            }
         }
      }
   }

   public void mouseClicked(MouseEventArgs var1) {
      if (var1.getClickCount() == 2) {
         gu var2 = bO.a(bS.j(this.fk)).f(this.fl, this.fm);
         if (var2 != null) {
      // PORT_TODO: // PORT_TODO: cg.a((Container)bS.j(this.fk), (gQ)var2);
            bS.c(this.fk);
         }
      }

   }
}



}
