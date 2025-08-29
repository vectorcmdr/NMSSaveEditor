package nomanssave;

import javax.swing.JTextField;

class bL extends JTextField {
  final bK eB;
  
  bL(bE parambE, bK parambK, boolean paramBoolean) {
    this.eB = parambK;
    setEnabled(paramBoolean);
    addFocusListener(new bM(this, parambK));
  }
  
  void ac() {
    String str;
    if (bE.a(this.ey) == null) {
      str = "";
    } else {
      str = this.eB.ab();
    } 
    setText(str);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\bL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */