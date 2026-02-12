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

public class dg : ActionListener {
   // $FF: synthetic field
   public dd gW;

   public dg(dd var1) {
      this.gW = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      dd.a(this.gW, dd.b(this.gW).SelectedIndex);
      this.gW.SetVisible(false);
   }
}

}
