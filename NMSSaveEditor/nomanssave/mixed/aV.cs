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

public class aV : ActionListener {
   // $FF: synthetic field
   aQ dr;

   aV(aQ var1) {
      this.dr = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      this.dr.SetVisible(false);
   }
}

}
