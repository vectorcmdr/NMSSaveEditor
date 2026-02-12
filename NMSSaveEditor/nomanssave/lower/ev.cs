using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;

namespace NMSSaveEditor
{

class ev : List<object> {
   string id;

   ev(Element var1) {
      this.id = var1.getAttribute("id");
      XmlNodeList var2 = var1.getChildNodes();

      for(int var3 = 0; var3 < var2.getLength(); ++var3) {
         Node var4 = var2.item(var3);
         if (var4 is Element && var4.getNodeName().Equals("stacksize")) {
            this.Add(new ew((Element)var4));
         }
      }

   }
}

}
