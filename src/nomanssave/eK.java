package nomanssave;

import java.util.Comparator;

class eK implements Comparator {
   public int a(eI var1, eI var2) {
      return var1.name.compareTo(var2.name);
   }

   // $FF: synthetic method
   public int compare(Object var1, Object var2) {
      return this.a((eI)var1, (eI)var2);
   }
}
