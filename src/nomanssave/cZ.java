package nomanssave;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

class cZ implements ComboBoxModel {
  private gg gQ = null;
  
  cZ(cY paramcY) {}
  
  public int getSize() {
    return cY.a(this.gR).size();
  }
  
  public gg C(int paramInt) {
    return cY.a(this.gR).get(paramInt);
  }
  
  public void addListDataListener(ListDataListener paramListDataListener) {}
  
  public void removeListDataListener(ListDataListener paramListDataListener) {}
  
  public void setSelectedItem(Object paramObject) {
    this.gQ = (gg)paramObject;
  }
  
  public Object getSelectedItem() {
    return this.gQ;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\cZ.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */