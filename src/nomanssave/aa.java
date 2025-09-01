package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class aa implements ActionListener {
   // $FF: synthetic field
   final X bV;
   // $FF: synthetic field
   private final Application bv;

   aa(X var1, Application var2) {
      this.bV = var1;
      this.bv = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      gj var2 = this.bv.j();
      if (var2 != null) {
         int var3 = -1;
         gj[] var4 = new gj[X.a(this.bV).length + 1];

         for(int var5 = 0; var5 < X.a(this.bV).length; ++var5) {
            if (X.a(this.bV)[var5].getIndex() < var2.getIndex()) {
               var4[var5] = X.a(this.bV)[var5];
            } else {
               var4[var5 + 1] = X.a(this.bV)[var5];
               if (var3 < 0) {
                  var3 = var5;
               }
            }
         }

         if (var3 < 0) {
            var3 = X.a(this.bV).length;
         }

         var4[var3] = var2;
         X.a(this.bV, var4);
         hc.info("Imported " + var2.cL().name().toLowerCase() + ": " + var2.getIndex());
         X.k(this.bV).setSelectedIndex(var3);
         X.k(this.bV).updateUI();
      }

   }
}
