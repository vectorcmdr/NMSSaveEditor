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

public class di : ActionListener {
   // $FF: synthetic field
   public dd gW;

   public di(dd var1) {
      this.gW = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      this.gW.SetVisible(false);
   }
}

}
