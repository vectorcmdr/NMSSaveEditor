package nomanssave;

import java.util.Collections;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

class dO implements ComboBoxModel {
  private gH hZ = null;
  
  dO(dN paramdN, Application paramApplication) {}
  
  public int getSize() {
    return (dN.a(this.ia) == null) ? 0 : (dN.a(this.ia)).length;
  }
  
  public gH G(int paramInt) {
    return dN.a(this.ia)[paramInt];
  }
  
  public void addListDataListener(ListDataListener paramListDataListener) {}
  
  public void removeListDataListener(ListDataListener paramListDataListener) {}
  
  public void setSelectedItem(Object paramObject) {
    this.hZ = (gH)paramObject;
    if (this.hZ == null) {
      dN.b(this.ia).setText("");
      dN.c(this.ia).setSelectedIndex(-1);
      dN.d(this.ia).setSelectedIndex(-1);
      dN.e(this.ia).setText("");
      dN.f(this.ia).setSelected(false);
      dN.f(this.ia).setEnabled(false);
      dN.g(this.ia).setEnabled(false);
      dN.h(this.ia).setText("");
      dN.i(this.ia).setText("");
      dN.j(this.ia).setText("");
      dN.k(this.ia).setText("");
      dN.l(this.ia).a(Collections.emptyList());
      return;
    } 
    dN.b(this.ia).setText(this.hZ.getName());
    dN.c(this.ia).m(this.hZ.cT());
    dN.d(this.ia).m(this.hZ.cW());
    dN.e(this.ia).setText(this.hZ.cK());
    eV eV = this.bv.d("PlayerStateData.ShipUsesLegacyColours");
    dN.f(this.ia).setSelected((eV != null && eV.ab(this.hZ.getIndex())));
    dN.f(this.ia).setEnabled(true);
    dN.g(this.ia).setEnabled(true);
    dN.h(this.ia).setText(Double.toString(this.hZ.dF()));
    dN.i(this.ia).setText(Double.toString(this.hZ.eb()));
    dN.j(this.ia).setText(Double.toString(this.hZ.cX()));
    dN.k(this.ia).setText(Double.toString(this.hZ.ec()));
    dN.l(this.ia).a(this.hZ.cC());
    dN.m(this.ia).setEnabled(false);
    dN.n(this.ia).setEnabled(false);
    if (dN.o(this.ia) != null)
      for (byte b = 0; b < (dN.a(this.ia)).length; b++) {
        if (this.hZ == dN.a(this.ia)[b] && b == dN.o(this.ia).dV()) {
          dN.m(this.ia).setEnabled(true);
          dN.n(this.ia).setEnabled(true);
        } 
      }  
  }
  
  public Object getSelectedItem() {
    return this.hZ;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\dO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */