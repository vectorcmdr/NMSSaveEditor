package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

class dq implements ActionListener {
   // $FF: synthetic field
   final dj hl;
   // $FF: synthetic field
   private final Application bv;

   dq(dj var1, Application var2) {
      this.hl = var1;
      this.bv = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      int var2 = dj.j(this.hl).getSelectedIndex();
      if (var2 >= 0 && var2 < dj.a(this.hl).length) {
         if (dj.a(this.hl).length == 1) {
            this.bv.c("You cannot delete the only multitool you have!");
         } else if (JOptionPane.showConfirmDialog(this.hl, "Are you sure you want to delete this multitool?\nAll technology in the multitool will be lost!", "Delete", 2) == 0) {
            this.bv.h(dj.a(this.hl)[var2].getIndex());
         }
      }
   }
}
