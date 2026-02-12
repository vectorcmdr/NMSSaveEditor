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

public class da : ActionListener {
   // $FF: synthetic field
   cY gR;

   da(cY var1) {
      this.gR = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      cY.a(this.gR, cY.b(this.gR).SelectedIndex);
      this.gR.SetVisible(false);
   }
}

}
