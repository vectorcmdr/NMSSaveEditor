package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class bT implements ActionListener {
   // $FF: synthetic field
   final bS fk;
   // $FF: synthetic field
   private final int fl;
   // $FF: synthetic field
   private final int fm;

   bT(bS var1, int var2, int var3) {
      this.fk = var1;
      this.fl = var2;
      this.fm = var3;
   }

   public void actionPerformed(ActionEvent var1) {
      if (bO.a(bS.j(this.fk)).dp() || en.aS()) {
         if (bS.b(this.fk).isSelected()) {
            bO.a(bS.j(this.fk)).i(this.fl, this.fm);
         } else {
            if (bO.a(bS.j(this.fk)).f(this.fl, this.fm) != null) {
               bS.b(this.fk).setSelected(true);
               bO.b(bS.j(this.fk)).c("Cannot disable slots that are in use!");
               return;
            }

            bO.a(bS.j(this.fk)).j(this.fl, this.fm);
         }

         bS.c(this.fk);
      }
   }
}
