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

public class bj : ActionListener {
   // $FF: synthetic field
   bd dP;
   // $FF: synthetic field
   private Application bv;

   bj(bd var1, Application var2) {
      this.dP = var1;
      this.bv = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      if (bd.a(this.dP) != null) {
         this.bv.a(bd.a(this.dP));
      }
   }
}

}
