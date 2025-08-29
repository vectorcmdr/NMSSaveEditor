package nomanssave;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

class bM implements FocusListener {
  bM(bL parambL, bK parambK) {}
  
  public void focusGained(FocusEvent paramFocusEvent) {}
  
  public void focusLost(FocusEvent paramFocusEvent) {
    if (bE.a(bL.a(this.eC)) == null)
      return; 
    JTextField jTextField = (JTextField)paramFocusEvent.getComponent();
    String str1 = this.eD.ab();
    String str2 = jTextField.getText();
    if (!str2.equals(str1))
      try {
        this.eD.l(str2);
      } catch (RuntimeException runtimeException) {
        jTextField.setText(str1);
      }  
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\bM.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */