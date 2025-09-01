package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JOptionPane;

class am implements ActionListener {
   // $FF: synthetic field
   final aj cg;

   am(aj var1) {
      this.cg = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      int var2 = aj.b(this.cg).getSelectedIndex();
      if (var2 < 0) {
         JOptionPane.showOptionDialog(this.cg, "Invalid galaxy selected, please try again.", "Error", 0, 0, (Icon)null, new Object[]{"Cancel"}, (Object)null);
      } else {
         if (JOptionPane.showOptionDialog(this.cg, "This will warp your character and ship to the specified system (not the portal itself).", "Confirm", 2, 1, (Icon)null, new String[]{"OK", "Cancel"}, (Object)null) == 0) {
            aj.a(this.cg, true);
            this.cg.setVisible(false);
         }

      }
   }
}
