using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class es : IComparer<object> {
   public int a(er var1, er var2) {
      return var1.name.CompareTo(var2.name);
   }
   public int Compare(object var1, object var2) {
      return this.a((er)var1, (er)var2);
   }
}

}
