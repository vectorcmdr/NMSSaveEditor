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

public class ed : ActionListener {
   // $FF: synthetic field
   public ec ik;
   // $FF: synthetic field
   public int il;

   public ed(ec var1, int var2) {
      this.ik = var1;
      this.il = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      if (ec.a(this.ik).isSelected() ^ eb.a(ec.h(this.ik))[this.il].Enabled) {
         bool var2 = ec.a(this.ik).isSelected();
         eb.a(ec.h(this.ik))[this.il].SetEnabled(var2);
         ec.b(this.ik).SetEnabled(var2);
         ec.c(this.ik).SetEnabled(var2);
         ec.d(this.ik).SetEnabled(var2);
         ec.e(this.ik).SetEnabled(var2);
         ec.f(this.ik).SetEnabled(var2);
      }
    }
}

}
