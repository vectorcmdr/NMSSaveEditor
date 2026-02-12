using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class az : ActionListener {
   ap cu;

   public az(ap var1) {
      this.cu = var1;
   }

   public void actionPerformed(EventArgs var1) {
      IEnumerator var4 = eS.by().GetEnumerator();

      while(var4.MoveNext()) {
         eS var3 = (eS)var4.Current;
         gA var2 = ap.i(this.cu).a(var3);
         var2.a(eU.kr, false);
         var2.a(eU.ks, false);
         var2.a(eU.kt, false);
         var2.a(eU.kv, false);
         var2.a(eU.kz, false);
      }

      ap.j(this.cu).Refresh();
   }
}

}
