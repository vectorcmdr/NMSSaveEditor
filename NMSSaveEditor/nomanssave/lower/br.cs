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

public class br : ActionListener {
   // $FF: synthetic field
   public bl er;
   // $FF: synthetic field
   public Application bv;

   public br(bl var1, Application var2) {
      this.er = var1;
      this.bv = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      if (bl.b(this.er) >= 0) {
         string var2 = hg.eo().ToString();
         bl.a(this.er, this.bv.a(bl.c(this.er)[bl.b(this.er)].getIndex(), var2));
         bl.g(this.er).SetEnabled(bl.c(this.er).Length < 30 || en.aS());
         bl.e(this.er).updateUI();
      }
   }
}

}
