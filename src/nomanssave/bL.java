package nomanssave;

import javax.swing.JTextField;

class bL extends JTextField {
   final bK eB;
   // $FF: synthetic field
   final bE ey;

   bL(bE var1, bK var2, boolean var3) {
      this.ey = var1;
      this.eB = var2;
      this.setEnabled(var3);
      this.addFocusListener(new bM(this, var2));
   }

   void ac() {
      String var1;
      if (bE.a(this.ey) == null) {
         var1 = "";
      } else {
         var1 = this.eB.ab();
      }

      this.setText(var1);
   }

   // $FF: synthetic method
   static bE a(bL var0) {
      return var0.ey;
   }
}
