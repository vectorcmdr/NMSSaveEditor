using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class cF : AbstractAction {
   public cy gg;

   public cF(cy var1) {
      this.gg = var1;
   }

   public void actionPerformed(EventArgs var1) {
      int var2 = cy.c(this.gg).getSelectionStart();
      int var3 = cy.c(this.gg).getSelectionEnd();
      string var4 = var3 > var2 ? cy.c(this.gg).Text.Substring(var2, var3) : null;
      aW.a(this.gg, var4);
   }
}


#else

public class cF
{
   public cF() { }
   public cF(params object[] args) { }
   public cy gg = default;
   public void actionPerformed(EventArgs var1) { }
}

#endif

}
