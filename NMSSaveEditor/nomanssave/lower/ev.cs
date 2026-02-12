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

public class ev : List<object> {
   public string id;

   public ev(XmlElement var1) {
      this.id = var1.GetAttribute("id");
      XmlNodeList var2 = var1.ChildNodes;
       for(int var3 = 0; var3 < var2.Count; ++var3) {
         XmlNode var4 = var2[var3];
         if (var4 is XmlElement && var4.Name.Equals("stacksize")) {
            this.Add(new ew((XmlElement)var4));
         }
      }
    }
}

}
