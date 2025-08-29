package nomanssave;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.text.TextAction;

class cG extends TextAction implements ClipboardOwner {
  public cG(cy paramcy) {
    super("Copy From Clipboard");
  }
  
  public void actionPerformed(ActionEvent paramActionEvent) {
    String str = ((JTextArea)paramActionEvent.getSource()).getSelectedText();
    if (str != null)
      cy.b(str, this); 
  }
  
  public void lostOwnership(Clipboard paramClipboard, Transferable paramTransferable) {}
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\cG.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */