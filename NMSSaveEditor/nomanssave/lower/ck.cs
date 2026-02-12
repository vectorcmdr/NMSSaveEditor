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

public class ck : WindowAdapter {
   // $FF: synthetic field
   cg fF;

   ck(cg var1) {
      this.fF = var1;
   }

   public void windowClosing(WindowEvent var1) {
      cg.e(this.fF).N();
      cg.f(this.fF).N();
   }
}

}
