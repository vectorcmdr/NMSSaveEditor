package nomanssave;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

class L implements ComboBoxModel {
  gf bu = null;
  
  L(I paramI) {}
  
  public int getSize() {
    return (I.a(this.bt) == null) ? 0 : I.a(this.bt).cE().size();
  }
  
  public gf p(int paramInt) {
    return (I.a(this.bt) == null) ? null : I.a(this.bt).cE().get(paramInt);
  }
  
  public void addListDataListener(ListDataListener paramListDataListener) {}
  
  public void removeListDataListener(ListDataListener paramListDataListener) {}
  
  public void setSelectedItem(Object paramObject) {
    this.bu = (gf)paramObject;
    if (this.bu == null) {
      I.e(this.bt).setText("");
      I.f(this.bt).setText("");
      I.f(this.bt).setEnabled(false);
      I.g(this.bt).setEnabled(false);
      I.h(this.bt).setEnabled(false);
      I.i(this.bt).setEnabled(false);
    } else {
      I.e(this.bt).setText(Integer.toString(this.bu.cG()));
      I.f(this.bt).setText(this.bu.getName());
      I.f(this.bt).setEnabled(true);
      I.g(this.bt).setEnabled(true);
      I.h(this.bt).setEnabled(true);
      I.i(this.bt).setEnabled(true);
    } 
  }
  
  public Object getSelectedItem() {
    return this.bu;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\L.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */