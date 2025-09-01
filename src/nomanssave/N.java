package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class N implements ActionListener {
   // $FF: synthetic field
   final I bt;
   // $FF: synthetic field
   private final Application bv;

   N(I var1, Application var2) {
      this.bt = var1;
      this.bv = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      gf var2 = (gf)I.j(this.bt).getSelectedItem();
      if (var2 != null) {
         this.bv.a(var2);
      }

   }
}
