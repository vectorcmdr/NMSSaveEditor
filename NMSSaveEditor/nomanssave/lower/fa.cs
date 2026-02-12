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

public class fa {
   eY kM = new eY();

   public fa d(string var1, Object var2) {
      if (var1 == null) {
         throw new NullReferenceException();
      } else if (!eY.bF().Match(var1).Matches()) {
         throw new Exception("Invalid name: " + var1);
      } else if (var2 != null && !fh.a(var2.GetType())) {
         throw new Exception("Unsupported type: " + var2.GetType().Name);
      } else {
         this.kM.a(var1, var2);
         return this;
      }
   }

   public eY bH() {
      return this.kM;
   }
}

}
