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

public class eA {
   public string id;
   public string name;
   public string jM;
   public string description;

   public eA(XmlElement var1) {
      this.id = var1.GetAttribute("id");
      this.name = var1.GetAttribute("name");
      this.jM = var1.GetAttribute("subtitle");
      string var2 = null;
      XmlNodeList var3 = var1.ChildNodes;
       for(int var5 = 0; var5 < var3.Count; ++var5) {
         XmlNode var4 = var3[var5];
         if (var4 is Element) {
            var1 = (XmlElement)var4;
            if (var1.Name.Equals("description")) {
               var2 = ey.a(var1);
            }
         }
      }
       this.description = var2;
   }

   public string a(string var1, Function var2) {
      StringBuilder var3 = new StringBuilder();
      int var4 = 0;

      for(Matcher var5 = ey.bn().Match(var1); var5.Success; var4 = var5.end()) {
         var3.Append(var1.Substring(var4, var5.start()));
         var3.Append((string)var2.apply(var5.Groups[1]));
      }

      var3.Append(var1.Substring(var4));
      return var3.ToString();
   }

   public string a(Function var1) {
      return this.a(this.name, var1);
   }

   public string b(Function var1) {
      return this.a(this.jM, var1);
   }

   public string c(Function var1) {
      return this.description == null ? null : this.a(this.description, var1);
   }
}

}
