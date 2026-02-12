using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class cH : TextAction, ClipboardOwner {
   cy gg;

   public cH(cy var1) {
      base("Paste From Clipboard");
      this.gg = var1;
   }

   public void actionPerformed(EventArgs var1) {
      string var2 = cy.az();
      ((TextBox)var1.getSource()).replaceSelection(var2);
   }

   public void lostOwnership(object var1, Transferable var2) {
   }
}

}
