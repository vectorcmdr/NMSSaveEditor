package nomanssave;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

class dK implements ComboBoxModel {
  eM hF;
  
  dK(dJ paramdJ) {}
  
  public int getSize() {
    return 1 + eM.getCount();
  }
  
  public eM F(int paramInt) {
    return (paramInt == 0) ? null : eM.S(paramInt - 1);
  }
  
  public void addListDataListener(ListDataListener paramListDataListener) {}
  
  public void removeListDataListener(ListDataListener paramListDataListener) {}
  
  public void setSelectedItem(Object paramObject) {
    this.hF = (eM)paramObject;
  }
  
  public Object getSelectedItem() {
    return this.hF;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\dK.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */