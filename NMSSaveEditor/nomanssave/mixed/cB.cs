using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class cB : ActionListener {
   public cy gg;

   public cB(cy var1) {
      this.gg = var1;
   }

   public void insertUpdate(EventArgs var1) {
      cy.a(this.gg, true);
   }

   public void removeUpdate(EventArgs var1) {
      cy.a(this.gg, true);
   }

   public void changedUpdate(EventArgs var1) {
   }
}



}
