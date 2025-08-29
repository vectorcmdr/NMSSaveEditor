package nomanssave;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

class dF implements ComboBoxModel {
  private gE hD = null;
  
  dF(dE paramdE) {}
  
  public int getSize() {
    return (dE.b(this.hE) == null) ? 0 : (dE.b(this.hE)).length;
  }
  
  public gE E(int paramInt) {
    return dE.b(this.hE)[paramInt];
  }
  
  public void addListDataListener(ListDataListener paramListDataListener) {}
  
  public void removeListDataListener(ListDataListener paramListDataListener) {}
  
  public void setSelectedItem(Object paramObject) {
    this.hD = (gE)paramObject;
    if (this.hD == null) {
      dE.c(this.hE).setText("");
      dE.d(this.hE).setText("");
      for (byte b = 0; b < (dE.e(this.hE)).length; b++)
        dE.e(this.hE)[b].setText(""); 
      dE.f(this.hE).a(new gF[0]);
    } else {
      dE.c(this.hE).setText(this.hD.getName());
      dE.d(this.hE).setText(this.hD.cK());
      for (byte b = 0; b < (dE.e(this.hE)).length; b++)
        dE.e(this.hE)[b].setText(Integer.toString(this.hD.aq(b))); 
      dE.f(this.hE).a(this.hD.dX());
    } 
    dE.g(this.hE).revalidate();
  }
  
  public Object getSelectedItem() {
    return this.hD;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\dF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */