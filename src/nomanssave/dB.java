package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

class dB implements ActionListener {
  dB(dz paramdz) {}
  
  public void actionPerformed(ActionEvent paramActionEvent) {
    int i = dz.b(this.hu).getSelectedIndex();
    if (i >= 0 && !dz.a(this.hu)[i].isEmpty() && JOptionPane.showConfirmDialog(this.hu, "You are about to overwrite this save slot, are you sure you want to do this?", "Warning", 2) != 0)
      return; 
    dz.a(this.hu, i);
    this.hu.setVisible(false);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\dB.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */