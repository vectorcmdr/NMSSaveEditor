using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

class eT : IComparer {
   public int a(eS var1, eS var2) {
      return var1.text.CompareTo(var2.text);
   }
   public int compare(object var1, object var2) {
      return this.a((eS)var1, (eS)var2);
   }
}

}
