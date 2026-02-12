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

public class dR : ActionListener {
   // $FF: synthetic field
   public dN ia;
   // $FF: synthetic field
   public Application bv;

   public dR(dN var1, Application var2) {
      this.ia = var1;
      this.bv = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      int var2 = dN.p(this.ia).SelectedIndex;
      if (var2 >= 0 && var2 < dN.a(this.ia).length) {
         this.bv.a(dN.a(this.ia)[var2]);
      }
   }
}

}
