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

public class aG : ActionListener {
   // $FF: synthetic field
   public aD cB;

   public aG(aD var1) {
      this.cB = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      this.cB.SetVisible(false);
   }
}

}
