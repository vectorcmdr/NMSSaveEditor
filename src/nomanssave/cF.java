package nomanssave;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

class cF extends AbstractAction {
  cF(cy paramcy) {}
  
  public void actionPerformed(ActionEvent paramActionEvent) {
    int i = cy.c(this.gg).getSelectionStart();
    int j = cy.c(this.gg).getSelectionEnd();
    String str = (j > i) ? cy.c(this.gg).getText().substring(i, j) : null;
    aW.a(this.gg, str);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\cF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */