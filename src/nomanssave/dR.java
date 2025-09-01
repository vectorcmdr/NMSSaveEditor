package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class dR implements ActionListener {
   // $FF: synthetic field
   final dN ia;
   // $FF: synthetic field
   private final Application bv;

   dR(dN var1, Application var2) {
      this.ia = var1;
      this.bv = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      int var2 = dN.p(this.ia).getSelectedIndex();
      if (var2 >= 0 && var2 < dN.a(this.ia).length) {
         this.bv.a(dN.a(this.ia)[var2]);
      }
   }
}
