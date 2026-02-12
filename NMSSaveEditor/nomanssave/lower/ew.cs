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

public class ew {
   public string iI;
   public int iJ;
   public int iK;

   public ew(XmlElement var1) {
      this.iI = var1.GetAttribute("group");
      this.iJ = int.Parse(var1.GetAttribute("substance"));
      this.iK = int.Parse(var1.GetAttribute("product"));
   }

   public int aX() {
      return this.iJ;
   }

   public int aY() {
      return this.iK;
   }
}

}
