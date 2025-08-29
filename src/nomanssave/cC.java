package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

class cC implements ActionListener {
  cC(cy paramcy) {}
  
  public void actionPerformed(ActionEvent paramActionEvent) {
    String str = cy.c(this.gg).getText().trim();
    try {
      cy.d(this.gg).setText(str);
      ((cI)cy.e(this.gg).getModel()).a(cy.d(this.gg));
      cy.e(this.gg).setSelectionRow(0);
      cy.e(this.gg).setVisible(true);
      cy.f(this.gg).setVisible(false);
    } catch (eX eX) {
      JOptionPane.showOptionDialog(this.gg, "Error on line #" + eX.getLineNumber() + ": " + eX.getMessage(), "Error", 0, 0, null, new Object[] { "Cancel" }, null);
      cy.c(this.gg).setCaretPosition(eX.bD());
      cy.c(this.gg).requestFocus();
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\cC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */