package nomanssave;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

class l implements ComboBoxModel {
  private ey C = null;
  
  l(h paramh) {}
  
  public int getSize() {
    return h.i(this.z).size();
  }
  
  public ey d(int paramInt) {
    return h.i(this.z).get(paramInt);
  }
  
  public void addListDataListener(ListDataListener paramListDataListener) {}
  
  public void removeListDataListener(ListDataListener paramListDataListener) {}
  
  public void setSelectedItem(Object paramObject) {
    this.C = (ey)paramObject;
  }
  
  public Object getSelectedItem() {
    return this.C;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\l.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */