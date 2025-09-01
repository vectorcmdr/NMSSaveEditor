package nomanssave;

import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class ev extends ArrayList {
   final String id;

   ev(Element var1) {
      this.id = var1.getAttribute("id");
      NodeList var2 = var1.getChildNodes();

      for(int var3 = 0; var3 < var2.getLength(); ++var3) {
         Node var4 = var2.item(var3);
         if (var4 instanceof Element && var4.getNodeName().equals("stacksize")) {
            this.add(new ew((Element)var4));
         }
      }

   }
}
