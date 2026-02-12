using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class ay : ActionListener {
   ap cu;

   public ay(ap var1) {
      this.cu = var1;
   }

   public void actionPerformed(EventArgs var1) {
      IEnumerator var4 = eS.by().GetEnumerator();

      while(var4.MoveNext()) {
         eS var3 = (eS)var4.Current;
         gA var2 = ap.i(this.cu).a(var3);
         if (var3.a(eU.kr)) {
            var2.a(eU.kr, true);
         }

         if (var3.a(eU.ks)) {
            var2.a(eU.ks, true);
         }

         if (var3.a(eU.kt)) {
            var2.a(eU.kt, true);
         }

         if (var3.a(eU.kv)) {
            var2.a(eU.kv, true);
         }

         if (var3.a(eU.kz)) {
            var2.a(eU.kz, true);
         }
      }

      ap.j(this.cu).Refresh();
   }
}

}
