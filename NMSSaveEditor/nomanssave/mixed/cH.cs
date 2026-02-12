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

public class cH : TextAction, ClipboardOwner {
   // $FF: synthetic field
   public cy gg;

   public cH(cy var1) {
      // base("Paste From Clipboard");
      this.gg = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      string var2 = cy.az();
      ((TextBox)var1.getSource()).replaceSelection(var2);
   }

   public void lostOwnership(object clipboard, Transferable contents) {}
}

}
