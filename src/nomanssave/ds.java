package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ds implements ActionListener {
   // $FF: synthetic field
   final dj hl;
   // $FF: synthetic field
   private final Application bv;

   ds(dj var1, Application var2) {
      this.hl = var1;
      this.bv = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      gv var2 = this.bv.i();
      if (var2 != null) {
         gv[] var3 = new gv[dj.a(this.hl).length + 1];
         int var4 = -1;

         for(int var5 = 0; var5 < dj.a(this.hl).length; ++var5) {
            if (dj.a(this.hl)[var5].getIndex() < var2.getIndex()) {
               var3[var5] = dj.a(this.hl)[var5];
            } else {
               var3[var5 + 1] = dj.a(this.hl)[var5];
               if (var4 < 0) {
                  var4 = var5;
               }
            }
         }

         if (var4 < 0) {
            var4 = dj.a(this.hl).length;
         }

         var3[var4] = var2;
         dj.a(this.hl, var3);
         dj.j(this.hl).setSelectedIndex(var4);
         dj.j(this.hl).updateUI();
      }

   }
}
