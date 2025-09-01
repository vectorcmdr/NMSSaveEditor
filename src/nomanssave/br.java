package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class br implements ActionListener {
   // $FF: synthetic field
   final bl er;
   // $FF: synthetic field
   private final Application bv;

   br(bl var1, Application var2) {
      this.er = var1;
      this.bv = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      if (bl.b(this.er) >= 0) {
         String var2 = hg.eo().toString();
         bl.a(this.er, this.bv.a(bl.c(this.er)[bl.b(this.er)].getIndex(), var2));
         bl.g(this.er).setEnabled(bl.c(this.er).length < 30 || en.aS());
         bl.e(this.er).updateUI();
      }
   }
}
