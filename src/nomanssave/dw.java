package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class dw implements ActionListener {
   // $FF: synthetic field
   final du hp;
   // $FF: synthetic field
   private final gF hq;

   dw(du var1, gF var2) {
      this.hp = var1;
      this.hq = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      if (this.hq != null) {
         dt.a(du.d(this.hp), this.hp);
      }

   }
}
