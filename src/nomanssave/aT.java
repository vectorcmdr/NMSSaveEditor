package nomanssave;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class aT implements ActionListener {
  aT(aQ paramaQ) {}
  
  public void actionPerformed(ActionEvent paramActionEvent) {
    aQ.a(this.dr, new Dimension(Integer.parseInt(aQ.a(this.dr).getText()), Integer.parseInt(aQ.e(this.dr).getText())));
    this.dr.setVisible(false);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\aT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */