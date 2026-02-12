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

public class dr : ActionListener {
   // $FF: synthetic field
   dj hl;
   // $FF: synthetic field
   private Application bv;

   dr(dj var1, Application var2) {
      this.hl = var1;
      this.bv = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      int var2 = dj.j(this.hl).SelectedIndex;
      if (var2 >= 0 && var2 < dj.a(this.hl).length) {
         this.bv.a(dj.a(this.hl)[var2]);
      }
   }
}

}
