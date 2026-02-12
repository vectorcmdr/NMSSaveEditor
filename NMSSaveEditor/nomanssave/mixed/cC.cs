using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class cC : ActionListener {
   public cy gg;

   public cC(cy var1) {
      this.gg = var1;
   }

   public void actionPerformed(EventArgs var1) {
      string var2 = cy.c(this.gg).Text.Trim();

      try {
         cy.d(this.gg).Text = (var2);
      // PORT_TODO: // PORT_TODO: ((cI)cy.e(this.gg).DataSource).a(cy.d(this.gg));
         cy.e(this.gg).setSelectionRow(0);
         cy.e(this.gg).Show();
         cy.f(this.gg).Hide();
      } catch (eX var4) {
         MessageBox.Show("Error on line #" + var4.getLineNumber() + ": " + var4.getMessage(), "Error");
         cy.c(this.gg).setCaretPosition(var4.bD());
         cy.c(this.gg).Focus();
      }

   }
}



}
