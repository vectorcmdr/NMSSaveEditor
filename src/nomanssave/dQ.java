package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

class dQ implements ActionListener {
  dQ(dN paramdN, Application paramApplication) {}
  
  public void actionPerformed(ActionEvent paramActionEvent) {
    int i = dN.p(this.ia).getSelectedIndex();
    if (i < 0 || i >= (dN.a(this.ia)).length)
      return; 
    if ((dN.a(this.ia)).length == 1) {
      this.bv.c("You cannot delete the only ship you have!");
      return;
    } 
    if (JOptionPane.showConfirmDialog(this.ia, "Are you sure you want to delete this ship?\nAll items and technology in the ship inventory will be lost!", "Delete", 2) != 0)
      return; 
    this.bv.i(dN.a(this.ia)[i].getIndex());
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\dQ.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */