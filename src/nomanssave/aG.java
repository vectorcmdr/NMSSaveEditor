package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class aG implements ActionListener {
   // $FF: synthetic field
   final aD cB;

   aG(aD var1) {
      this.cB = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      this.cB.setVisible(false);
   }
}
