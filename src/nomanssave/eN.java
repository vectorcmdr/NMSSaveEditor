package nomanssave;

import java.util.Comparator;

class eN implements Comparator {
   public int a(eM var1, eM var2) {
      return var1.name.compareTo(var2.name);
   }

   // $FF: synthetic method
   public int compare(Object var1, Object var2) {
      return this.a((eM)var1, (eM)var2);
   }
}
