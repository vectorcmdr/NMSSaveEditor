using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

class az : EventHandler {
   ap cu;

   az(ap var1) {
      this.cu = var1;
   }

   public void actionPerformed(EventArgs var1) {
      IEnumerator var4 = eS.by().iterator();

      while(var4.hasNext()) {
         eS var3 = (eS)var4.next();
         gA var2 = ap.i(this.cu).a(var3);
         var2.a(eU.kr, false);
         var2.a(eU.ks, false);
         var2.a(eU.kt, false);
         var2.a(eU.kv, false);
         var2.a(eU.kz, false);
      }

      ap.j(this.cu).updateUI();
   }
}

}
