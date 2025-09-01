package nomanssave;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class dv implements ActionListener {
   // $FF: synthetic field
   final du hp;
   // $FF: synthetic field
   private final gF hq;

   dv(du var1, gF var2) {
      this.hp = var1;
      this.hq = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      if (this.hq != null) {
         cg.a((Container)du.d(this.hp), (gQ)this.hq);
         du.c(this.hp);
      }

   }
}
