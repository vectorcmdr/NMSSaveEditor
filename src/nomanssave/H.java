package nomanssave;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

class H implements FocusListener {
  H(G paramG) {}
  
  public void focusGained(FocusEvent paramFocusEvent) {}
  
  public void focusLost(FocusEvent paramFocusEvent) {
    this.bg.N();
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\H.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */