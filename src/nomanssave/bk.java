package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class bk implements ActionListener {
   // $FF: synthetic field
   final bd dP;
   // $FF: synthetic field
   private final Application bv;

   bk(bd var1, Application var2) {
      this.dP = var1;
      this.bv = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      if (bd.a(this.dP) != null) {
         this.bv.b(bd.a(this.dP));
      }
   }
}
