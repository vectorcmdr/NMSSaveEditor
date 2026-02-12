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

public class dJ : ComboBox {
   // $FF: synthetic field
   public dE hE;

   public dJ(dE var1) {
      this.hE = var1;
      this.SetModel(new dK(this));
      this.setRenderer(new dL(this));
   }
}

}
