package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class bz implements ActionListener {
   // $FF: synthetic field
   final bl er;

   bz(bl var1) {
      this.er = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      if (bl.b(this.er) >= 0) {
         hc.info("Repairing frigate damage");
         bl.c(this.er)[bl.b(this.er)].aw(0);
         bl.c(this.er)[bl.b(this.er)].ax(0);
         bl.y(this.er).setText("");
         bl.z(this.er).setVisible(false);
      }
   }
}
