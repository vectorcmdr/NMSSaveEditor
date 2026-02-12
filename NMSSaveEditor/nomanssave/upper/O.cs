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

public class O : ActionListener {
   // $FF: synthetic field
   public I bt;
   // $FF: synthetic field
   public Application bv;

   public O(I var1, Application var2) {
      this.bt = var1;
      this.bv = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      gf var2 = (gf)I.j(this.bt).SelectedItem;
      if (var2 != null && this.bv.b(var2)) {
         I.e(this.bt).SetText(Convert.ToString(var2.cG()));
      }

   }
}

}
