package nomanssave;

import java.util.function.Function;
import java.util.regex.Matcher;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class eA {
   final String id;
   final String name;
   final String jM;
   final String description;

   eA(Element var1) {
      this.id = var1.getAttribute("id");
      this.name = var1.getAttribute("name");
      this.jM = var1.getAttribute("subtitle");
      String var2 = null;
      NodeList var3 = var1.getChildNodes();

      for(int var5 = 0; var5 < var3.getLength(); ++var5) {
         Node var4 = var3.item(var5);
         if (var4 instanceof Element) {
            var1 = (Element)var4;
            if (var1.getNodeName().equals("description")) {
               var2 = ey.a(var1);
            }
         }
      }

      this.description = var2;
   }

   private String a(String var1, Function var2) {
      StringBuilder var3 = new StringBuilder();
      int var4 = 0;

      for(Matcher var5 = ey.bn().matcher(var1); var5.find(); var4 = var5.end()) {
         var3.append(var1.substring(var4, var5.start()));
         var3.append((String)var2.apply(var5.group(1)));
      }

      var3.append(var1.substring(var4));
      return var3.toString();
   }

   String a(Function var1) {
      return this.a(this.name, var1);
   }

   String b(Function var1) {
      return this.a(this.jM, var1);
   }

   String c(Function var1) {
      return this.description == null ? null : this.a(this.description, var1);
   }
}
