using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class cE : object {
   cy gg;

   public cE(cy var1) {
      this.gg = var1;
   }

   public void windowClosing(FormClosedEventArgs var1) {
      bool var2 = true;
      if (cy.g(this.gg) && cy.d(this.gg) != null) {
         try {
            string var3 = cy.c(this.gg).Text.Trim();
            if (var3.length() == 0 && MessageBox.Show(this.gg, "The JSON data has been deleted, do you wish to apply these changes to the save file?", this.gg.Text, 0) == 0) {
               cy.d(this.gg).Remove();
            } else if (MessageBox.Show(this.gg, "The JSON data has changed, do you wish to apply these changes to the save file?", this.gg.Text, 0) == 0) {
               cy.d(this.gg).Text = (var3);
            }
         } catch (eX var4) {
            MessageBox.showOptionDialog(this.gg, "Error on line #" + var4.getLineNumber() + ": " + var4.getMessage(), "Error", 0, 0, (Icon)null, new object[]{"Cancel"}, (object)null);
            cy.c(this.gg).setCaretPosition(var4.bD());
            cy.c(this.gg).Focus();
            var2 = false;
         }
      }

      if (var2) {
         this.gg.Hide();
      }

   }
}

}
