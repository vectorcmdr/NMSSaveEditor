package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class dr implements ActionListener {
   // $FF: synthetic field
   final dj hl;
   // $FF: synthetic field
   private final Application bv;

   dr(dj var1, Application var2) {
      this.hl = var1;
      this.bv = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      int var2 = dj.j(this.hl).getSelectedIndex();
      if (var2 >= 0 && var2 < dj.a(this.hl).length) {
         this.bv.a(dj.a(this.hl)[var2]);
      }
   }
}
