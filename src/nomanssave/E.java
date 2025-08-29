package nomanssave;

import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListDataListener;

class E implements ComboBoxModel {
  E(Application paramApplication) {}
  
  public int getSize() {
    return (Application.f(this.aZ)).length;
  }
  
  public fs n(int paramInt) {
    return Application.f(this.aZ)[paramInt];
  }
  
  public void addListDataListener(ListDataListener paramListDataListener) {}
  
  public void removeListDataListener(ListDataListener paramListDataListener) {}
  
  public void setSelectedItem(Object paramObject) {
    if (Application.i(this.aZ)) {
      Application.o(this.aZ).hidePopup();
      int i = JOptionPane.showConfirmDialog(Application.h(this.aZ), "Are you sure you want to load a different file and lose current changes?", "Save", 2);
      if (i != 0)
        return; 
      Application.f(this.aZ, false);
    } 
    byte b = -1;
    synchronized (Application.n(this.aZ)) {
      byte b1 = 0;
      for (byte b2 = 0; b2 < (Application.f(this.aZ)).length; b2++) {
        if (Application.f(this.aZ)[b2] == paramObject) {
          b = b1;
          Application.f(this.aZ)[b1++] = Application.f(this.aZ)[b2];
        } else if (!(Application.f(this.aZ)[b2] instanceof F)) {
          Application.f(this.aZ)[b1++] = Application.f(this.aZ)[b2];
        } 
      } 
      if (b1 < (Application.f(this.aZ)).length) {
        fs[] arrayOfFs = new fs[b1];
        System.arraycopy(Application.f(this.aZ), 0, arrayOfFs, 0, b1);
        Application.a(this.aZ, arrayOfFs);
      } 
    } 
    Application.b(this.aZ, b);
  }
  
  public Object getSelectedItem() {
    return (Application.e(this.aZ) < 0) ? null : Application.f(this.aZ)[Application.e(this.aZ)];
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\E.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */