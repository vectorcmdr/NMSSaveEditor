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

public class bc : PropertyChangeListener {
   // $FF: synthetic field
   ba dB;
   // $FF: synthetic field
   private Button dD;
   // $FF: synthetic field
   private G dC;

   bc(ba var1, Button var2, G var3) {
      this.dB = var1;
      this.dD = var2;
      this.dC = var3;
   }

   public void propertyChange(PropertyChangeEvent var1) {
      this.dD.SetEnabled(this.dC.Enabled);
   }
}

}
