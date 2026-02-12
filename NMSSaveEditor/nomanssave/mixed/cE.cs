using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class cE : object {
   cy gg;

   cE(cy var1) {
      this.gg = var1;
   }

   public void windowClosing(FormClosedEventArgs var1) {
      bool var2 = true;
      if (cy.g(this.gg) && cy.d(this.gg) != null) {
         try {
            string var3 = cy.c(this.gg).getText().Trim();
            if (var3.length() == 0 && MessageBox.showConfirmDialog(this.gg, "The JSON data has been deleted, do you wish to apply these changes to the save file?", this.gg.getTitle(), 0) == 0) {
               cy.d(this.gg).Remove();
            } else if (MessageBox.showConfirmDialog(this.gg, "The JSON data has changed, do you wish to apply these changes to the save file?", this.gg.getTitle(), 0) == 0) {
               cy.d(this.gg).setText(var3);
            }
         } catch (eX var4) {
            MessageBox.showOptionDialog(this.gg, "Error on line #" + var4.getLineNumber() + ": " + var4.getMessage(), "Error", 0, 0, (Icon)null, new Object[]{"Cancel"}, (Object)null);
            cy.c(this.gg).setCaretPosition(var4.bD());
            cy.c(this.gg).requestFocus();
            var2 = false;
         }
      }

      if (var2) {
         this.gg.setVisible(false);
      }

   }
}

}
