using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class es : IComparer {
   public int a(er var1, er var2) {
      return var1.name.CompareTo(var2.name);
   }
   public int compare(object var1, object var2) {
      return this.a((er)var1, (er)var2);
   }
}

}
