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

public class eK : Comparator {
   public int a(eI var1, eI var2) {
      return var1.name.CompareTo(var2.name);
   }

   // $FF: synthetic method
   public int compare(Object var1, Object var2) {
      return this.a((eI)var1, (eI)var2);
   }
}

}
