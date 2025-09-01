package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class dV implements ActionListener {
   // $FF: synthetic field
   final dN ia;
   // $FF: synthetic field
   private final Application bv;

   dV(dN var1, Application var2) {
      this.ia = var1;
      this.bv = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      gH var2 = (gH)dN.p(this.ia).getSelectedItem();
      if (var2 != null) {
         eV var3 = this.bv.d("PlayerStateData.ShipUsesLegacyColours");
         if (var3 != null) {
            if (dN.f(this.ia).isSelected() ^ var3.ab(var2.getIndex())) {
               var3.a(var2.getIndex(), dN.f(this.ia).isSelected());
            }

         }
      }
   }
}
