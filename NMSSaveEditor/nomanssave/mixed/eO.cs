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

public class eO {
   string id;

   eO(string var1) {
      this.id = var1;
   }

   public bool equals(Object var1) {
      eM var2 = (eM)var1;
      return var2.jY ? this.id.StartsWith(var2.id + "#") : this.id.Equals(var2.id);
   }
}

}
