using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class es : IComparer {
   public int a(er var1, er var2) {
      return var1.name.compareTo(var2.name);
   }
   public int compare(Object var1, Object var2) {
      return this.a((er)var1, (er)var2);
   }
}

}
