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

public class eN : Comparator {
   public int a(eM var1, eM var2) {
      return var1.name.CompareTo(var2.name);
   }

   // $FF: synthetic method
   public int compare(Object var1, Object var2) {
      return this.a((eM)var1, (eM)var2);
   }
}

}
