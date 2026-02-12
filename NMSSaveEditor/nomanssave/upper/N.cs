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

public class N : ActionListener {
   // $FF: synthetic field
   I bt;
   // $FF: synthetic field
   private Application bv;

   N(I var1, Application var2) {
      this.bt = var1;
      this.bv = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      gf var2 = (gf)I.j(this.bt).SelectedItem;
      if (var2 != null) {
         this.bv.a(var2);
      }

   }
}

}
