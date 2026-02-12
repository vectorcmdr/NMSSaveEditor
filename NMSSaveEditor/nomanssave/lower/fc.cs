using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
abstract class fc {
   fc kN;
   eY kL;

   fc(eY var1, fc var2) {
      this.kL = var1;
      this.kN = var2;
   }

   protected abstract object a(Class var1, bool var2);

   protected abstract object getValue();

   protected abstract object a(object var1, bool var2);

   protected abstract object bG();

   protected abstract eY e(eY var1);
}

}
