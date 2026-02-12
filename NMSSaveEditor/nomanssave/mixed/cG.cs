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

public class cG : TextAction, ClipboardOwner {
   // $FF: synthetic field
   public cy gg;

   public cG(cy var1) {
      // base("Copy From Clipboard");
      this.gg = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      string var2 = ((TextBox)var1.getSource()).getSelectedText();
      if (var2 != null) {
         cy.b(var2, this);
      }

   }

   public void lostOwnership(object clipboard, Transferable contents) {}
}

}
