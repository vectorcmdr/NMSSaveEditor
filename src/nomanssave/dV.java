package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class dV implements ActionListener {
  dV(dN paramdN, Application paramApplication) {}
  
  public void actionPerformed(ActionEvent paramActionEvent) {
    gH gH = (gH)dN.p(this.ia).getSelectedItem();
    if (gH == null)
      return; 
    eV eV = this.bv.d("PlayerStateData.ShipUsesLegacyColours");
    if (eV == null)
      return; 
    if ((dN.f(this.ia).isSelected() ^ eV.ab(gH.getIndex())) != 0)
      eV.a(gH.getIndex(), Boolean.valueOf(dN.f(this.ia).isSelected())); 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\dV.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */