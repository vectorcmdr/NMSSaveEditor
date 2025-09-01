package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class aU implements ActionListener {
   // $FF: synthetic field
   final aQ dr;

   aU(aQ var1) {
      this.dr = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      this.dr.setVisible(false);
   }
}
