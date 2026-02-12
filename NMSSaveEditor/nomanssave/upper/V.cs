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

public class V : ActionListener {
   // $FF: synthetic field
   Q bD;

   V(Q var1) {
      this.bD = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      this.bD.SetVisible(false);
   }
}

}
