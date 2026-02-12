using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class eN : IComparer {
   public int a(eM var1, eM var2) {
      return var1.name.compareTo(var2.name);
   }
   public int compare(Object var1, Object var2) {
      return this.a((eM)var1, (eM)var2);
   }
}

}
