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

public class T : ActionListener {
   // $FF: synthetic field
   Q bD;

   T(Q var1) {
      this.bD = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      Q.a(this.bD, new W(int.Parse(Q.a(this.bD).GetText()), int.Parse(Q.d(this.bD).GetText())));
      this.bD.SetVisible(false);
   }
}

}
