package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class bV implements ActionListener {
  bV(bS parambS, int paramInt1, int paramInt2) {}
  
  public void actionPerformed(ActionEvent paramActionEvent) {
    gu gu = bO.a(bS.j(this.fk)).f(this.fl, this.fm);
    if (gu != null && gu.dA() >= 0 && gu.dB() > 0) {
      gu.aA(gu.dB());
      bS.c(this.fk);
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\bV.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */