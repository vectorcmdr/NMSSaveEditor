package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JOptionPane;

class ak implements ActionListener {
   // $FF: synthetic field
   final aj cg;

   ak(aj var1) {
      this.cg = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      try {
         aj.a(this.cg, hl.e(aj.a(this.cg).getText().trim(), aj.b(this.cg).getSelectedIndex()));
         aj.c(this.cg);
      } catch (RuntimeException var3) {
         JOptionPane.showOptionDialog(this.cg, "Invalid address value, please try again.", "Error", 0, 0, (Icon)null, new Object[]{"Cancel"}, (Object)null);
      }
   }
}
