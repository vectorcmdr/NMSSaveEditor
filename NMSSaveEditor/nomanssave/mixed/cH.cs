using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class cH : TextAction, ClipboardOwner {
   public cy gg;

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


#else

public class cH
{
   public cH() { }
   public cH(params object[] args) { }
   public cy gg = default;
   public void actionPerformed(EventArgs var1) { }
   public void lostOwnership(object var1, Transferable var2) { }
}

#endif

}
