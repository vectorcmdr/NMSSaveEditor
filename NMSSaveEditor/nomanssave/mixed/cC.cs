using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class cC : EventHandler {
   cy gg;

   cC(cy var1) {
      this.gg = var1;
   }

   public void actionPerformed(EventArgs var1) {
      string var2 = cy.c(this.gg).getText().Trim();

      try {
         cy.d(this.gg).setText(var2);
         ((cI)cy.e(this.gg).getModel()).a(cy.d(this.gg));
         cy.e(this.gg).setSelectionRow(0);
         cy.e(this.gg).setVisible(true);
         cy.f(this.gg).setVisible(false);
      } catch (eX var4) {
         MessageBox.showOptionDialog(this.gg, "Error on line #" + var4.getLineNumber() + ": " + var4.getMessage(), "Error", 0, 0, (Icon)null, new Object[]{"Cancel"}, (Object)null);
         cy.c(this.gg).setCaretPosition(var4.bD());
         cy.c(this.gg).requestFocus();
      }

   }
}

}
