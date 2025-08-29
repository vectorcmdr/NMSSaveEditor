package nomanssave;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

class S implements FocusListener {
  S(Q paramQ) {}
  
  public void focusGained(FocusEvent paramFocusEvent) {}
  
  public void focusLost(FocusEvent paramFocusEvent) {
    int i;
    try {
      i = Integer.parseInt(Q.d(this.bD).getText());
      if (i < Q.e(this.bD))
        i = Q.e(this.bD); 
    } catch (RuntimeException runtimeException) {
      i = (Q.c(this.bD)).bF;
    } 
    Q.d(this.bD).setText(Integer.toString(i));
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\S.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */