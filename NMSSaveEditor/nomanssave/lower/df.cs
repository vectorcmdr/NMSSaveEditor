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

public class df : MouseAdapter {
   // $FF: synthetic field
   dd gW;

   df(dd var1) {
      this.gW = var1;
   }

   public void mouseClicked(MouseEvent var1) {
      if (var1.getClickCount() == 2) {
         dd.a(this.gW, dd.b(this.gW).SelectedIndex);
         this.gW.SetVisible(false);
      }

   }
}

}
