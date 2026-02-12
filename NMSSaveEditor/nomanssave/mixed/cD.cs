using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class cD : ActionListener {
   cy gg;

   cD(cy var1) {
      this.gg = var1;
   }

   public void propertyChange(EventArgs var1) {
      aH.b("JSONEditor.Divider", (Integer)var1.getNewValue());
   }
}

}
