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

public class s : ActionListener {
   // $FF: synthetic field
   p I;

   s(p var1) {
      this.I = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      this.I.SetVisible(false);
   }
}

}
