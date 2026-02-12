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

public class gb : Comparator {
   // $FF: synthetic field
   public fZ nb;

   public gb(fZ var1) {
      this.nb = var1;
   }

   public int a(fs var1, fs var2) {
      long var3 = var2.LastWriteTimeUtc.Ticks - var1.LastWriteTimeUtc.Ticks;
      if (var3 < -2147483648L) {
         return int.MinValue;
      } else {
         return var3 > 2147483647L ? int.MaxValue : (int)var3;
      }
   }

   // $FF: synthetic method
   public int compare(Object var1, Object var2) {
      return this.a((fs)var1, (fs)var2);
   }
}

}
