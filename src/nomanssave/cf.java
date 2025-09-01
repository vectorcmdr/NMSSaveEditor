package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class cf implements ActionListener {
   // $FF: synthetic field
   final bS fk;
   // $FF: synthetic field
   private final int fl;
   // $FF: synthetic field
   private final int fm;

   cf(bS var1, int var2, int var3) {
      this.fk = var1;
      this.fl = var2;
      this.fm = var3;
   }

   public void actionPerformed(ActionEvent var1) {
      gu var2 = bO.a(bS.j(this.fk)).f(this.fl, this.fm);
      if (var2 != null) {
         var2.c(0.0D);
         var2.e(true);
         bS.c(this.fk);
      }

   }
}
