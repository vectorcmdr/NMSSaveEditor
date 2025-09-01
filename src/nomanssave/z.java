package nomanssave;

import javax.swing.Icon;
import javax.swing.JOptionPane;

class z implements Runnable {
   // $FF: synthetic field
   final Application aZ;
   // $FF: synthetic field
   private final String bc;

   z(Application var1, String var2) {
      this.aZ = var1;
      this.bc = var2;
   }

   public void run() {
      JOptionPane.showOptionDialog(Application.h(this.aZ), this.bc, "Error", 0, 0, (Icon)null, new Object[]{"Cancel"}, (Object)null);
   }
}
