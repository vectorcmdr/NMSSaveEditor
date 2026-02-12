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

public class r : ActionListener {
   // $FF: synthetic field
   public p I;

   public r(p var1) {
      this.I = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      int[] var2 = p.b(this.I).GetSelectedRows();
      p.a(this.I, new List<object>());

      for(int var4 = 0; var4 < var2.Length; ++var4) {
         int var3 = p.b(this.I).convertRowIndexToModel(var2[var4]);
         p.c(this.I).Add((string)p.b(this.I).GetModel().GetValueAt(var3, 3));
      }

      this.I.SetVisible(false);
   }
}

}
