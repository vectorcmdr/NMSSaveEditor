package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

class dB implements ActionListener {
   // $FF: synthetic field
   final dz hu;

   dB(dz var1) {
      this.hu = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      int var2 = dz.b(this.hu).getSelectedIndex();
      if (var2 < 0 || dz.a(this.hu)[var2].isEmpty() || JOptionPane.showConfirmDialog(this.hu, "You are about to overwrite this save slot, are you sure you want to do this?", "Warning", 2) == 0) {
         dz.a(this.hu, var2);
         this.hu.setVisible(false);
      }
   }
}
