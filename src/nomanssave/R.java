package nomanssave;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

class R implements FocusListener {
  R(Q paramQ) {}
  
  public void focusGained(FocusEvent paramFocusEvent) {}
  
  public void focusLost(FocusEvent paramFocusEvent) {
    int i;
    try {
      i = Integer.parseInt(Q.a(this.bD).getText());
      if (i % 250 != 0)
        i = (int)Math.round(i / 250.0D); 
      if (i < Q.b(this.bD))
        i = Q.b(this.bD); 
    } catch (RuntimeException runtimeException) {
      i = (Q.c(this.bD)).bE;
    } 
    Q.a(this.bD).setText(Integer.toString(i));
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\R.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */