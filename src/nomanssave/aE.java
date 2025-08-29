package nomanssave;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

class aE implements ComboBoxModel {
  aI cA = null;
  
  aE(aD paramaD) {}
  
  public int getSize() {
    return (aI.values()).length;
  }
  
  public aI t(int paramInt) {
    return aI.values()[paramInt];
  }
  
  public void addListDataListener(ListDataListener paramListDataListener) {}
  
  public void removeListDataListener(ListDataListener paramListDataListener) {}
  
  public void setSelectedItem(Object paramObject) {
    this.cA = (aI)paramObject;
  }
  
  public Object getSelectedItem() {
    return this.cA;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\aE.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */