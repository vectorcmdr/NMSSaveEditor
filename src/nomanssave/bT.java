package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class bT implements ActionListener {
  bT(bS parambS, int paramInt1, int paramInt2) {}
  
  public void actionPerformed(ActionEvent paramActionEvent) {
    if (!bO.a(bS.j(this.fk)).dp() && !en.aS())
      return; 
    if (bS.b(this.fk).isSelected()) {
      bO.a(bS.j(this.fk)).i(this.fl, this.fm);
    } else {
      if (bO.a(bS.j(this.fk)).f(this.fl, this.fm) != null) {
        bS.b(this.fk).setSelected(true);
        bO.b(bS.j(this.fk)).c("Cannot disable slots that are in use!");
        return;
      } 
      bO.a(bS.j(this.fk)).j(this.fl, this.fm);
    } 
    bS.c(this.fk);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\bT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */