package nomanssave;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class ck extends WindowAdapter {
   // $FF: synthetic field
   final cg fF;

   ck(cg var1) {
      this.fF = var1;
   }

   public void windowClosing(WindowEvent var1) {
      cg.e(this.fF).N();
      cg.f(this.fF).N();
   }
}
