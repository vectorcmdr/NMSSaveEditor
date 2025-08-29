package nomanssave;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

class k implements ComboBoxModel {
  private ex B = null;
  
  k(h paramh) {}
  
  public int getSize() {
    return h.g(this.z).size();
  }
  
  public ex c(int paramInt) {
    return h.g(this.z).get(paramInt);
  }
  
  public void addListDataListener(ListDataListener paramListDataListener) {}
  
  public void removeListDataListener(ListDataListener paramListDataListener) {}
  
  public void setSelectedItem(Object paramObject) {
    this.B = (ex)paramObject;
    h.h(this.z);
  }
  
  public Object getSelectedItem() {
    return this.B;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\k.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */