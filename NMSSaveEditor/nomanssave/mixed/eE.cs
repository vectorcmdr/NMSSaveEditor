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
         eF var2 = (eF)this[(0);
         return var2.key.Equals(var1) || var2.name.Equals(var1);
      }
   }

   public eF t(string var1) {
      IEnumerator var3 = this.GetEnumerator();

      while(var3.MoveNext()) {
         eF var2 = (eF)var3.Current;
         if (var2.key.Equals(var1)) {
            return var2;
         }
      }

      return null;
   }

   public eF u(string var1) {
      IEnumerator var3 = this.GetEnumerator();

      while(var3.MoveNext()) {
         eF var2 = (eF)var3.Current;
         if (var2.name.Equals(var1)) {
            return var2;
         }
      }

      return null;
   }

   public eF v(string var1) {
      IEnumerator var3 = this.GetEnumerator();

      while(var3.MoveNext()) {
         eF var2 = (eF)var3.Current;
         if (var2.name.Equals(var1)) {
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
