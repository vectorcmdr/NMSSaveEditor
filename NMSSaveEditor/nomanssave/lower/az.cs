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

public class az : ActionListener {
   // $FF: synthetic field
   ap cu;

   az(ap var1) {
      this.cu = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      IEnumerator<object> var4 = eS.by().GetEnumerator();

      while(var4.MoveNext()) {
         eS var3 = (eS)var4.Current;
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
