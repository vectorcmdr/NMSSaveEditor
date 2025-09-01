package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class dg implements ActionListener {
   // $FF: synthetic field
   final dd gW;

   dg(dd var1) {
      this.gW = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      dd.a(this.gW, dd.b(this.gW).getSelectedIndex());
      this.gW.setVisible(false);
   }
}
