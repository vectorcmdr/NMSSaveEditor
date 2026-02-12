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

public class H : FocusListener {
   // $FF: synthetic field
   G bg;

   H(G var1) {
      this.bg = var1;
   }

   public void focusGained(FocusEvent var1) {
   }

   public void focusLost(FocusEvent var1) {
      this.bg.N();
   }
}

}
