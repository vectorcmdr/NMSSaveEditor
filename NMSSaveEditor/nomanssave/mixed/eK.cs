using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class eK : IComparer<object> {
   public int a(eI var1, eI var2) {
      return var1.name.CompareTo(var2.name);
   }
   public int Compare(object var1, object var2) {
      return this.a((eI)var1, (eI)var2);
   }
}

}
