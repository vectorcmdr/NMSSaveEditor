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

public class aT : ActionListener {
   // $FF: synthetic field
   public aQ dr;

   public aT(aQ var1) {
      this.dr = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      aQ.a(this.dr, new Size(int.Parse(aQ.a(this.dr).GetText()), int.Parse(aQ.e(this.dr).GetText())));
      this.dr.SetVisible(false);
   }
}

}
