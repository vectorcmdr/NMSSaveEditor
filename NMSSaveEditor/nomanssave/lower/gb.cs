using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class gb : IComparer {
   fZ nb;

   gb(fZ var1) {
      this.nb = var1;
   }

   public int a(fs var1, fs var2) {
      long var3 = var2.lastModified() - var1.lastModified();
      if (var3 < -2147483648L) {
         return int.MinValue;
      } else {
         return var3 > 2147483647L ? int.MaxValue : (int)var3;
      }
   }
   public int compare(Object var1, Object var2) {
      return this.a((fs)var1, (fs)var2);
   }
}

}
