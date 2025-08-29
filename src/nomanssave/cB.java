package nomanssave;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

class cB implements DocumentListener {
  cB(cy paramcy) {}
  
  public void insertUpdate(DocumentEvent paramDocumentEvent) {
    cy.a(this.gg, true);
  }
  
  public void removeUpdate(DocumentEvent paramDocumentEvent) {
    cy.a(this.gg, true);
  }
  
  public void changedUpdate(DocumentEvent paramDocumentEvent) {}
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\cB.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */