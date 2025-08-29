package nomanssave;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

class bB implements ComboBoxModel {
  er eu;
  
  private bB(bl parambl) {}
  
  public int getSize() {
    return (bl.a(this.er) == null) ? 0 : (bl.a(this.er)).length;
  }
  
  public er v(int paramInt) {
    return (bl.a(this.er) == null) ? null : bl.a(this.er)[paramInt];
  }
  
  public void addListDataListener(ListDataListener paramListDataListener) {}
  
  public void removeListDataListener(ListDataListener paramListDataListener) {}
  
  public void setSelectedItem(Object paramObject) {
    this.eu = (er)paramObject;
    if (bl.b(this.er) >= 0) {
      er er1 = bl.c(this.er)[bl.b(this.er)].ar(0);
      if (this.eu != er1) {
        if (er1 != null) {
          int i = er1.aU().ordinal();
          int j = bl.c(this.er)[bl.b(this.er)].aq(i) - er1.aV();
          if (j < 0)
            j = 0; 
          bl.c(this.er)[bl.b(this.er)].e(i, j);
          bl.d(this.er)[i].setText(Integer.toString(j));
        } 
        if (this.eu == null) {
          bl.c(this.er)[bl.b(this.er)].a(0, null);
        } else {
          bl.c(this.er)[bl.b(this.er)].a(0, this.eu);
          int i = this.eu.aU().ordinal();
          int j = bl.c(this.er)[bl.b(this.er)].aq(i) + this.eu.aV();
          if (j < 0)
            j = 0; 
          bl.c(this.er)[bl.b(this.er)].e(i, j);
          bl.d(this.er)[i].setText(Integer.toString(j));
        } 
        bl.e(this.er).updateUI();
      } 
    } 
  }
  
  public Object getSelectedItem() {
    return this.eu;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\bB.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */