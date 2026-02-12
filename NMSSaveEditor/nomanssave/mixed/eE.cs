using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class eE : List<object> {
   private eE() {
   }

   public bool add(string var1, string var2) {
      return this.Add(new eF(var1, var2));
   }

   public bool s(string var1) {
      if (this.Count == 0) {
         return false;
      } else {
         eF var2 = (eF)this.get(0);
         return var2.key.equals(var1) || var2.name.equals(var1);
      }
   }

   public eF t(string var1) {
      IEnumerator var3 = this.iterator();

      while(var3.hasNext()) {
         eF var2 = (eF)var3.next();
         if (var2.key.equals(var1)) {
            return var2;
         }
      }

      return null;
   }

   public eF u(string var1) {
      IEnumerator var3 = this.iterator();

      while(var3.hasNext()) {
         eF var2 = (eF)var3.next();
         if (var2.name.equals(var1)) {
            return var2;
         }
      }

      return null;
   }

   public eF v(string var1) {
      IEnumerator var3 = this.iterator();

      while(var3.hasNext()) {
         eF var2 = (eF)var3.next();
         if (var2.name.equalsIgnoreCase(var1)) {
            return var2;
         }
      }

      return null;
   }
   eE(eE var1) {
      this();
   }
   eE(eE var1, eE var2) {
      this();
   }
}

}
