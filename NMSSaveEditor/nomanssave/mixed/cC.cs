using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using System.Globalization;

namespace NMSSaveEditor
{

public class cC : ActionListener {
   // $FF: synthetic field
   cy gg;

   cC(cy var1) {
      this.gg = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      string var2 = cy.c(this.gg).GetText().Trim();

      try {
         cy.d(this.gg).SetText(var2);
         ((cI)cy.e(this.gg).GetModel()).a(cy.d(this.gg));
         cy.e(this.gg).setSelectionRow(0);
         cy.e(this.gg).SetVisible(true);
         cy.f(this.gg).SetVisible(false);
      } catch (eX var4) {
         JavaCompat.ShowOptionDialog(this.gg, "Error on line #" + var4.getLineNumber() + ": " + var4.getMessage(), "Error", 0, 0, (Icon)null, new Object[]{"Cancel"}, (Object)null);
         cy.c(this.gg).setCaretPosition(var4.bD());
         cy.c(this.gg).Focus();
      }

   }
}

}
