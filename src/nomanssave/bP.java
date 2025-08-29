package nomanssave;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

class bP implements ComboBoxModel {
  bP(bO parambO) {}
  
  public int getSize() {
    return bO.d(this.eX).size();
  }
  
  public gt w(int paramInt) {
    return bO.d(this.eX).get(paramInt);
  }
  
  public void addListDataListener(ListDataListener paramListDataListener) {}
  
  public void removeListDataListener(ListDataListener paramListDataListener) {}
  
  public void setSelectedItem(Object paramObject) {
    bO.a(this.eX, (gt)paramObject);
    bO.e(this.eX).setVisible((bO.a(this.eX) == null) ? false : (!(!en.aS() && !bO.a(this.eX).dk())));
    bO.c(this.eX);
  }
  
  public Object getSelectedItem() {
    return bO.a(this.eX);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\bP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */