package nomanssave;

import javax.swing.Icon;
import javax.swing.JOptionPane;

class A implements Runnable {
   // $FF: synthetic field
   final Application aZ;
   // $FF: synthetic field
   private final String bc;

   A(Application var1, String var2) {
      this.aZ = var1;
      this.bc = var2;
   }

   public void run() {
      JOptionPane.showOptionDialog(Application.h(this.aZ), this.bc, "Warning", 0, 2, (Icon)null, new Object[]{"OK"}, (Object)null);
   }
}
