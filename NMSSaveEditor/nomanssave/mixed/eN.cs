using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class eN : IComparer {
   public int a(eM var1, eM var2) {
      return var1.name.CompareTo(var2.name);
   }
   public int compare(object var1, object var2) {
      return this.a((eM)var1, (eM)var2);
   }
}

}
