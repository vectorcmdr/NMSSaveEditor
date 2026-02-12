using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class eJ : IComparer {
   public int a(eI var1, eI var2) {
      return var1.name.compareTo(var2.name);
   }
   public int compare(Object var1, Object var2) {
      return this.a((eI)var1, (eI)var2);
   }
}

}
