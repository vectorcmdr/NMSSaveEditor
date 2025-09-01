package nomanssave;

import java.util.ArrayList;
import java.util.Iterator;

class eE extends ArrayList {
   private eE() {
   }

   public boolean add(String var1, String var2) {
      return this.add(new eF(var1, var2));
   }

   public boolean s(String var1) {
      if (this.size() == 0) {
         return false;
      } else {
         eF var2 = (eF)this.get(0);
         return var2.key.equals(var1) || var2.name.equals(var1);
      }
   }

   public eF t(String var1) {
      Iterator var3 = this.iterator();

      while(var3.hasNext()) {
         eF var2 = (eF)var3.next();
         if (var2.key.equals(var1)) {
            return var2;
         }
      }

      return null;
   }

   public eF u(String var1) {
      Iterator var3 = this.iterator();

      while(var3.hasNext()) {
         eF var2 = (eF)var3.next();
         if (var2.name.equals(var1)) {
            return var2;
         }
      }

      return null;
   }

   public eF v(String var1) {
      Iterator var3 = this.iterator();

      while(var3.hasNext()) {
         eF var2 = (eF)var3.next();
         if (var2.name.equalsIgnoreCase(var1)) {
            return var2;
         }
      }

      return null;
   }

   // $FF: synthetic method
   eE(eE var1) {
      this();
   }

   // $FF: synthetic method
   eE(eE var1, eE var2) {
      this();
   }
}
