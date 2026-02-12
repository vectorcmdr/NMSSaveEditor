using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class fG : IComparer<object> {
   fE mf;

   public fG(fE var1) {
      this.mf = var1;
   }

   public int a(fs var1, fs var2) {
      long var3 = var2.LastWriteTimeUtc.Ticks - var1.LastWriteTimeUtc.Ticks;
      if (var3 < -2147483648L) {
         return int.MinValue;
      } else {
         return var3 > 2147483647L ? int.MaxValue : (int)var3;
      }
   }
   public int Compare(object var1, object var2) {
      return this.a((fs)var1, (fs)var2);
   }
}

}
