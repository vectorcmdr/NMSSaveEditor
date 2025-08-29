package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class dR implements ActionListener {
  dR(dN paramdN, Application paramApplication) {}
  
  public void actionPerformed(ActionEvent paramActionEvent) {
    int i = dN.p(this.ia).getSelectedIndex();
    if (i < 0 || i >= (dN.a(this.ia)).length)
      return; 
    this.bv.a(dN.a(this.ia)[i]);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\dR.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */