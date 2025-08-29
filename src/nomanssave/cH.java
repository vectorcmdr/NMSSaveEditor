package nomanssave;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.text.TextAction;

class cH extends TextAction implements ClipboardOwner {
  public cH(cy paramcy) {
    super("Paste From Clipboard");
  }
  
  public void actionPerformed(ActionEvent paramActionEvent) {
    String str = cy.az();
    ((JTextArea)paramActionEvent.getSource()).replaceSelection(str);
  }
  
  public void lostOwnership(Clipboard paramClipboard, Transferable paramTransferable) {}
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\cH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */