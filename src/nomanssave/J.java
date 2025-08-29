package nomanssave;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

class J implements ComboBoxModel {
  gh bs = null;
  
  J(I paramI) {}
  
  public int getSize() {
    return (I.a(this.bt) == null) ? 0 : I.a(this.bt).cD().size();
  }
  
  public gh o(int paramInt) {
    return (I.a(this.bt) == null) ? null : I.a(this.bt).cD().get(paramInt);
  }
  
  public void addListDataListener(ListDataListener paramListDataListener) {}
  
  public void removeListDataListener(ListDataListener paramListDataListener) {}
  
  public void setSelectedItem(Object paramObject) {
    this.bs = (gh)paramObject;
    if (this.bs == null) {
      I.b(this.bt).setText("");
      I.c(this.bt).setText("");
      I.c(this.bt).setEnabled(false);
    } else {
      gy gy = this.bs.cJ();
      I.b(this.bt).setText((gy == null) ? "" : gy.toString());
      I.c(this.bt).setText(this.bs.cK());
      I.c(this.bt).setEnabled(true);
    } 
  }
  
  public Object getSelectedItem() {
    return this.bs;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\J.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */