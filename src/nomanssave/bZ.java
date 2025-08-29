package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class bZ implements ActionListener {
  bZ(bS parambS, int paramInt1, int paramInt2) {}
  
  public void actionPerformed(ActionEvent paramActionEvent) {
    if (!bO.a(bS.j(this.fk)).dq())
      return; 
    if (!bO.a(bS.j(this.fk)).l(this.fl, this.fm))
      return; 
    bO.a(bS.j(this.fk)).m(this.fl, this.fm);
    bS.c(this.fk);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\bZ.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */