package nomanssave;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

class cD implements PropertyChangeListener {
  cD(cy paramcy) {}
  
  public void propertyChange(PropertyChangeEvent paramPropertyChangeEvent) {
    aH.b("JSONEditor.Divider", ((Integer)paramPropertyChangeEvent.getNewValue()).intValue());
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\cD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */