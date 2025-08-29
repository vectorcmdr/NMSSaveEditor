package nomanssave;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

class cE extends WindowAdapter {
  cE(cy paramcy) {}
  
  public void windowClosing(WindowEvent paramWindowEvent) {
    boolean bool = true;
    if (cy.g(this.gg) && cy.d(this.gg) != null)
      try {
        String str = cy.c(this.gg).getText().trim();
        if (str.length() == 0 && JOptionPane.showConfirmDialog(this.gg, "The JSON data has been deleted, do you wish to apply these changes to the save file?", this.gg.getTitle(), 0) == 0) {
          cy.d(this.gg).remove();
        } else if (JOptionPane.showConfirmDialog(this.gg, "The JSON data has changed, do you wish to apply these changes to the save file?", this.gg.getTitle(), 0) == 0) {
          cy.d(this.gg).setText(str);
        } 
      } catch (eX eX) {
        JOptionPane.showOptionDialog(this.gg, "Error on line #" + eX.getLineNumber() + ": " + eX.getMessage(), "Error", 0, 0, null, new Object[] { "Cancel" }, null);
        cy.c(this.gg).setCaretPosition(eX.bD());
        cy.c(this.gg).requestFocus();
        bool = false;
      }  
    if (bool)
      this.gg.setVisible(false); 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\cE.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */