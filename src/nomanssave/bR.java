package nomanssave;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class bR implements ActionListener {
   // $FF: synthetic field
   final bO eX;

   bR(bO var1) {
      this.eX = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      if (bO.a(this.eX) != null) {
         Dimension var2 = aQ.a(this.eX, bO.a(this.eX).getSize(), bO.a(this.eX).dm(), bO.a(this.eX).dn());
         if (var2 != null && bO.a(this.eX).a(var2)) {
            bO.c(this.eX);
         }

      }
   }
}
