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

public class bk : ActionListener {
   // $FF: synthetic field
   public bd dP;
   // $FF: synthetic field
   public Application bv;

   public bk(bd var1, Application var2) {
      this.dP = var1;
      this.bv = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      if (bd.a(this.dP) != null) {
         this.bv.b(bd.a(this.dP));
      }
   }
}

}
