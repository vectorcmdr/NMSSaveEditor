package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ed implements ActionListener {
   // $FF: synthetic field
   final ec ik;
   // $FF: synthetic field
   private final int il;

   ed(ec var1, int var2) {
      this.ik = var1;
      this.il = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      if (ec.a(this.ik).isSelected() ^ eb.a(ec.h(this.ik))[this.il].isEnabled()) {
         boolean var2 = ec.a(this.ik).isSelected();
         eb.a(ec.h(this.ik))[this.il].setEnabled(var2);
         ec.b(this.ik).setEnabled(var2);
         ec.c(this.ik).setEnabled(var2);
         ec.d(this.ik).setEnabled(var2);
         ec.e(this.ik).setEnabled(var2);
         ec.f(this.ik).setEnabled(var2);
      }

   }
}
