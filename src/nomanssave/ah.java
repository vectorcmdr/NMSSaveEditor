package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ah implements ActionListener {
   // $FF: synthetic field
   final X bV;

   ah(X var1) {
      this.bV = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      gj var2 = (gj)X.k(this.bV).getSelectedItem();
      if (var2 != null) {
         if (X.h(this.bV).isSelected() ^ var2.cQ()) {
            var2.d(X.h(this.bV).isSelected());
         }

      }
   }
}
