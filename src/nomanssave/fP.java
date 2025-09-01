package nomanssave;

import java.util.Comparator;

class fP implements Comparator {
   // $FF: synthetic field
   final fN mw;

   fP(fN var1) {
      this.mw = var1;
   }

   public int a(fs var1, fs var2) {
      long var3 = var2.lastModified() - var1.lastModified();
      if (var3 < -2147483648L) {
         return Integer.MIN_VALUE;
      } else {
         return var3 > 2147483647L ? Integer.MAX_VALUE : (int)var3;
      }
   }

   // $FF: synthetic method
   public int compare(Object var1, Object var2) {
      return this.a((fs)var1, (fs)var2);
   }
}
