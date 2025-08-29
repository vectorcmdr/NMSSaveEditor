package nomanssave;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

class aS implements FocusListener {
  aS(aQ paramaQ) {}
  
  public void focusGained(FocusEvent paramFocusEvent) {}
  
  public void focusLost(FocusEvent paramFocusEvent) {
    int i;
    try {
      i = Integer.parseInt(aQ.e(this.dr).getText());
      if (i != (aQ.b(this.dr)).height)
        if (i < (aQ.c(this.dr)).height) {
          i = (aQ.c(this.dr)).height;
        } else if (i > (aQ.d(this.dr)).height && !en.aS()) {
          i = (aQ.d(this.dr)).height;
        }  
    } catch (RuntimeException runtimeException) {
      i = (aQ.b(this.dr)).height;
    } 
    aQ.e(this.dr).setText(Integer.toString(i));
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\aS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */