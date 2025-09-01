package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Z implements ActionListener {
   // $FF: synthetic field
   final X bV;
   // $FF: synthetic field
   private final Application bv;

   Z(X var1, Application var2) {
      this.bV = var1;
      this.bv = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      gj var2 = (gj)X.k(this.bV).getSelectedItem();
      if (var2 != null) {
         this.bv.a(var2);
      }

   }
}
