package nomanssave;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

class cB implements DocumentListener {
   // $FF: synthetic field
   final cy gg;

   cB(cy var1) {
      this.gg = var1;
   }

   public void insertUpdate(DocumentEvent var1) {
      cy.a(this.gg, true);
   }

   public void removeUpdate(DocumentEvent var1) {
      cy.a(this.gg, true);
   }

   public void changedUpdate(DocumentEvent var1) {
   }
}
