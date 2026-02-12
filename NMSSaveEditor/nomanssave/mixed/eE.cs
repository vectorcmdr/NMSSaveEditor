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

public class eE : List<object> {
   private eE() {
   }

   public bool add(string var1, string var2) {
      return this.Add(new eF(var1, var2));
   }

   public bool s(string var1) {
      if (this.Count == 0) {
         return false;
      } else {
         eF var2 = (eF)this.Get(0);
         return var2.key.Equals(var1) || var2.name.Equals(var1);
      }
   }

   public eF t(string var1) {
      IEnumerator<object> var3 = this.GetEnumerator();

      while(var3.MoveNext()) {
         eF var2 = (eF)var3.Current;
         if (var2.key.Equals(var1)) {
            return var2;
         }
      }

      return null;
   }

   public eF u(string var1) {
      IEnumerator<object> var3 = this.GetEnumerator();

      while(var3.MoveNext()) {
         eF var2 = (eF)var3.Current;
         if (var2.name.Equals(var1)) {
            return var2;
         }
      }

      return null;
   }

   public eF v(string var1) {
      IEnumerator<object> var3 = this.GetEnumerator();

      while(var3.MoveNext()) {
         eF var2 = (eF)var3.Current;
         if (var2.name.Equals(var1)) {
            return var2;
         }
      }

      return null;
   }

   // $FF: synthetic method
   eE(eE var1) {
      // Constructor chain: base()
   }

   // $FF: synthetic method
   eE(eE var1, eE var2) {
      // Constructor chain: base()
   }
}

}
