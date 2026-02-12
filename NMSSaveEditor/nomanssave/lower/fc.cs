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

public abstract class fc {
   fc kN;
   // $FF: synthetic field
   eY kL;

   fc(eY var1, fc var2) {
      this.kL = var1;
      this.kN = var2;
   }

   abstract Object a(Class var1, bool var2);

   abstract Object getValue();

   abstract Object a(Object var1, bool var2);

   abstract Object bG();

   abstract eY e(eY var1);
}

}
