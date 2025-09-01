package nomanssave;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.text.TextAction;

class cH extends TextAction implements ClipboardOwner {
   // $FF: synthetic field
   final cy gg;

   public cH(cy var1) {
      super("Paste From Clipboard");
      this.gg = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      String var2 = cy.az();
      ((JTextArea)var1.getSource()).replaceSelection(var2);
   }

   public void lostOwnership(Clipboard var1, Transferable var2) {
   }
}
