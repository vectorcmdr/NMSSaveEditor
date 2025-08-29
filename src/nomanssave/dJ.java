package nomanssave;

import javax.swing.JComboBox;

class dJ extends JComboBox {
  dJ(dE paramdE) {
    setModel(new dK(this));
    setRenderer(new dL(this));
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\dJ.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */