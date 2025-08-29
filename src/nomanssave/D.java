package nomanssave;

import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListDataListener;

class D implements ComboBoxModel {
  D(Application paramApplication) {}
  
  public int getSize() {
    return (Application.d(this.aZ)).length;
  }
  
  public ft m(int paramInt) {
    return Application.d(this.aZ)[paramInt];
  }
  
  public void addListDataListener(ListDataListener paramListDataListener) {}
  
  public void removeListDataListener(ListDataListener paramListDataListener) {}
  
  public void setSelectedItem(Object paramObject) {
    if (Application.i(this.aZ)) {
      Application.n(this.aZ).hidePopup();
      int i = JOptionPane.showConfirmDialog(Application.h(this.aZ), "Save data before switching slots?", "Save", 1);
      if (i == 0) {
        Application.k(this.aZ);
      } else {
        if (i == 2)
          return; 
        Application.f(this.aZ, false);
      } 
    } 
    byte b = -1;
    synchronized (Application.n(this.aZ)) {
      for (byte b1 = 0; b1 < (Application.d(this.aZ)).length; b1++) {
        if (Application.d(this.aZ)[b1] == paramObject) {
          b = b1;
          break;
        } 
      } 
    } 
    Application.a(this.aZ, b);
  }
  
  public Object getSelectedItem() {
    return (Application.c(this.aZ) < 0) ? null : Application.d(this.aZ)[Application.c(this.aZ)];
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\D.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */