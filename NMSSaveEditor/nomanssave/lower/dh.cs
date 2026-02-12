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

public class dh : ActionListener {
   // $FF: synthetic field
   dd gW;

   dh(dd var1) {
      this.gW = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      this.gW.SetVisible(false);
   }
}

}
