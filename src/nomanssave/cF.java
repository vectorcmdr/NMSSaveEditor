package nomanssave;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

class cF extends AbstractAction {
   // $FF: synthetic field
   final cy gg;

   cF(cy var1) {
      this.gg = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      int var2 = cy.c(this.gg).getSelectionStart();
      int var3 = cy.c(this.gg).getSelectionEnd();
      String var4 = var3 > var2 ? cy.c(this.gg).getText().substring(var2, var3) : null;
      aW.a(this.gg, var4);
   }
}
