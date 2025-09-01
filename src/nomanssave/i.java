package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JOptionPane;

class i implements ActionListener {
   // $FF: synthetic field
   final h z;

   i(h var1) {
      this.z = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      String var2 = h.a(this.z).getText();
      h.a(this.z, ey.b(h.b(this.z), var2));
      h.c(this.z);
      if (h.d(this.z).size() == 0) {
         JOptionPane.showOptionDialog(this.z, "Item not found.", "Warning", 0, 2, (Icon)null, new Object[]{"OK"}, (Object)null);
      }

   }
}
