package nomanssave;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.text.TextAction;

class cG extends TextAction implements ClipboardOwner {
   // $FF: synthetic field
   final cy gg;

   public cG(cy var1) {
      super("Copy From Clipboard");
      this.gg = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      String var2 = ((JTextArea)var1.getSource()).getSelectedText();
      if (var2 != null) {
         cy.b(var2, this);
      }

   }

   public void lostOwnership(Clipboard var1, Transferable var2) {
   }
}
