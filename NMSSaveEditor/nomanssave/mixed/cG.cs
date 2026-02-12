using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class cG : TextAction, ClipboardOwner {
   public cy gg;

   public cG(cy var1) {
      base("Copy From Clipboard");
      this.gg = var1;
   }

   public void actionPerformed(EventArgs var1) {
      string var2 = ((TextBox)var1.getSource()).getSelectedText();
      if (var2 != null) {
         cy.b(var2, this);
      }

   }

   public void lostOwnership(object var1, Transferable var2) {
   }
}

}
