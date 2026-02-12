using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Xml;

namespace NMSSaveEditor
{

class eA {
   string id;
   string name;
   string jM;
   string description;

   eA(Element var1) {
      this.id = var1.getAttribute("id");
      this.name = var1.getAttribute("name");
      this.jM = var1.getAttribute("subtitle");
      string var2 = null;
      XmlNodeList var3 = var1.getChildNodes();

      for(int var5 = 0; var5 < var3.getLength(); ++var5) {
         Node var4 = var3.item(var5);
         if (var4 is Element) {
            var1 = (Element)var4;
            if (var1.getNodeName().equals("description")) {
               var2 = ey.a(var1);
            }
         }
      }

      this.description = var2;
   }

   private string a(string var1, Function var2) {
      StringBuilder var3 = new StringBuilder();
      int var4 = 0;

      for(Matcher var5 = ey.bn().matcher(var1); var5.find(); var4 = var5.end()) {
         var3.append(var1.Substring(var4, var5.start()));
         var3.append((string)var2.apply(var5.group(1)));
      }

      var3.append(var1.Substring(var4));
      return var3.ToString();
   }

   string a(Function var1) {
      return this.a(this.name, var1);
   }

   string b(Function var1) {
      return this.a(this.jM, var1);
   }

   string c(Function var1) {
      return this.description == null ? null : this.a(this.description, var1);
   }
}

}
