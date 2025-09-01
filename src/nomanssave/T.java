package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class T implements ActionListener {
   // $FF: synthetic field
   final Q bD;

   T(Q var1) {
      this.bD = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      Q.a(this.bD, new W(Integer.parseInt(Q.a(this.bD).getText()), Integer.parseInt(Q.d(this.bD).getText())));
      this.bD.setVisible(false);
   }
}
