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

public class cF : AbstractAction {
   // $FF: synthetic field
   cy gg;

   cF(cy var1) {
      this.gg = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      int var2 = cy.c(this.gg).getSelectionStart();
      int var3 = cy.c(this.gg).getSelectionEnd();
      string var4 = var3 > var2 ? cy.c(this.gg).GetText().Substring(var2, var3) : null;
      aW.a(this.gg, var4);
   }
}

}
