package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class bW implements ActionListener {
   // $FF: synthetic field
   final bS fk;
   // $FF: synthetic field
   private final int fl;
   // $FF: synthetic field
   private final int fm;

   bW(bS var1, int var2, int var3) {
      this.fk = var1;
      this.fl = var2;
      this.fm = var3;
   }

   public void actionPerformed(ActionEvent var1) {
      if (bO.a(bS.j(this.fk)).g(this.fl, this.fm)) {
         bO.a(bS.j(this.fk)).g(this.fl, this.fm);
         bS.c(this.fk);
      }

   }
}
