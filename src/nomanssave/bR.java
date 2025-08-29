package nomanssave;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class bR implements ActionListener {
  bR(bO parambO) {}
  
  public void actionPerformed(ActionEvent paramActionEvent) {
    if (bO.a(this.eX) != null) {
      Dimension dimension = aQ.a(this.eX, bO.a(this.eX).getSize(), bO.a(this.eX).dm(), bO.a(this.eX).dn());
      if (dimension != null && bO.a(this.eX).a(dimension))
        bO.c(this.eX); 
      return;
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\bR.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */