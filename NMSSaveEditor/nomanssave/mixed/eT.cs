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

public class eT : Comparator {
   public int a(eS var1, eS var2) {
      return var1.text.CompareTo(var2.text);
   }

   // $FF: synthetic method
   public int compare(Object var1, Object var2) {
      return this.a((eS)var1, (eS)var2);
   }
}

}
