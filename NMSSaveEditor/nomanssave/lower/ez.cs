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

public class ez {
   public string id;
   public int jK;
   // $FF: synthetic field
   public ey jL;

   public ez(ey var1, XmlElement var2) {
      this.jL = var1;
      this.id = var2.GetAttribute("id");
      this.jK = int.Parse(var2.GetAttribute("quantity"));
   }

   public string getID() {
      return this.id;
   }

   public int bo() {
      return this.jK;
   }
}

}
