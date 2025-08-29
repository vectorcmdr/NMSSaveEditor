package nomanssave;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

class j implements ComboBoxModel {
  private eB A = null;
  
  j(h paramh) {}
  
  public int getSize() {
    return h.e(this.z).size();
  }
  
  public eB b(int paramInt) {
    return h.e(this.z).get(paramInt);
  }
  
  public void addListDataListener(ListDataListener paramListDataListener) {}
  
  public void removeListDataListener(ListDataListener paramListDataListener) {}
  
  public void setSelectedItem(Object paramObject) {
    this.A = (eB)paramObject;
    h.f(this.z);
  }
  
  public Object getSelectedItem() {
    return this.A;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\j.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */