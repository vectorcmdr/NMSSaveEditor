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

public class gg {
   public eY nl;
   // $FF: synthetic field
   public gf nm;

   public gg(gf var1, eY var2) {
      this.nm = var1;
      this.nl = var2;
   }

   public string toString() {
      return this.nl.getValueAsString("ObjectID");
   }
}

}
