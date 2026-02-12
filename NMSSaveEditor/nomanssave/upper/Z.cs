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

public class Z : ActionListener {
   // $FF: synthetic field
   public X bV;
   // $FF: synthetic field
   public Application bv;

   public Z(X var1, Application var2) {
      this.bV = var1;
      this.bv = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      gj var2 = (gj)X.k(this.bV).SelectedItem;
      if (var2 != null) {
         this.bv.a(var2);
      }

   }
}

}
