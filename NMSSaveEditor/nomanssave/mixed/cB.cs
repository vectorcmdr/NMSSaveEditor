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

public class cB : DocumentListener {
   // $FF: synthetic field
   public cy gg;

   public cB(cy var1) {
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

}
