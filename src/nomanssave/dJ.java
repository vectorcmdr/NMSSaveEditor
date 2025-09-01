package nomanssave;

import javax.swing.JComboBox;

class dJ extends JComboBox {
   // $FF: synthetic field
   final dE hE;

   dJ(dE var1) {
      this.hE = var1;
      this.setModel(new dK(this));
      this.setRenderer(new dL(this));
   }
}
