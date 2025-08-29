package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class T implements ActionListener {
  T(Q paramQ) {}
  
  public void actionPerformed(ActionEvent paramActionEvent) {
    Q.a(this.bD, new W(Integer.parseInt(Q.a(this.bD).getText()), Integer.parseInt(Q.d(this.bD).getText())));
    this.bD.setVisible(false);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\T.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */