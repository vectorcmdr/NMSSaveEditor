package nomanssave;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

class aR implements FocusListener {
  aR(aQ paramaQ) {}
  
  public void focusGained(FocusEvent paramFocusEvent) {}
  
  public void focusLost(FocusEvent paramFocusEvent) {
    int i;
    try {
      i = Integer.parseInt(aQ.a(this.dr).getText());
      if (i != (aQ.b(this.dr)).width)
        if (i < (aQ.c(this.dr)).width) {
          i = (aQ.c(this.dr)).width;
        } else if (i > (aQ.d(this.dr)).width && !en.aS()) {
          i = (aQ.d(this.dr)).width;
        }  
    } catch (RuntimeException runtimeException) {
      i = (aQ.b(this.dr)).width;
    } 
    aQ.a(this.dr).setText(Integer.toString(i));
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\aR.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */