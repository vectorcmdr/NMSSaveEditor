package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class aX implements ActionListener {
   // $FF: synthetic field
   final aW dy;
   // $FF: synthetic field
   private final cy dz;

   aX(aW var1, cy var2) {
      this.dy = var1;
      this.dz = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      String var2 = aW.a(this.dy).getText();
      if (var2.length() > 0) {
         this.dz.a(var2, aW.b(this.dy).isSelected(), aW.c(this.dy).isSelected(), aW.d(this.dy).isSelected());
      }

   }
}
