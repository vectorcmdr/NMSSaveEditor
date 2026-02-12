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

public class cE : WindowAdapter {
   // $FF: synthetic field
   public cy gg;

   public cE(cy var1) {
      this.gg = var1;
   }

   public void windowClosing(WindowEvent var1) {
      bool var2 = true;
      if (cy.g(this.gg) && cy.d(this.gg) != null) {
         try {
            string var3 = cy.c(this.gg).GetText().Trim();
            if (var3.Length == 0 && JOptionPane.showConfirmDialog(this.gg, "The JSON data has been deleted, do you wish to apply these changes to the save file?", this.gg.Text, 0) == 0) {
               cy.d(this.gg).Remove();
            } else if (JOptionPane.showConfirmDialog(this.gg, "The JSON data has changed, do you wish to apply these changes to the save file?", this.gg.Text, 0) == 0) {
               cy.d(this.gg).SetText(var3);
            }
         } catch (eX var4) {
            JavaCompat.ShowOptionDialog(this.gg, "Error on line #" + var4.getLineNumber() + ": " + var4.getMessage(), "Error", 0, 0, (Icon)null, new Object[]{"Cancel"}, (Object)null);
            cy.c(this.gg).setCaretPosition(var4.bD());
            cy.c(this.gg).Focus();
            var2 = false;
         }
      }

      if (var2) {
         this.gg.SetVisible(false);
      }

   }
}

}
