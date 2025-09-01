package nomanssave;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class eM {
   final String id;
   final String name;
   final String description;
   final boolean iE;
   final boolean jY;
   private static final List kl = new ArrayList();

   static {
      InputStream var0 = Application.class.getResourceAsStream("db/settlements.xml");
      if (var0 != null) {
         try {
            Document var1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(var0);
            Element var2 = var1.getDocumentElement();
            NodeList var3 = var2.getChildNodes();

            for(int var4 = 0; var4 < var3.getLength(); ++var4) {
               Node var5 = var3.item(var4);
               if (var5 instanceof Element && var5.getNodeName().equals("perk")) {
                  kl.add(new eM((Element)var5));
               }
            }
         } catch (ParserConfigurationException var6) {
         } catch (SAXException var7) {
         } catch (IOException var8) {
         }
      }

      kl.sort(new eN());
   }

   private eM(Element var1) {
      this.id = var1.getAttribute("id");
      this.name = var1.getAttribute("name");
      this.description = var1.getAttribute("description");
      this.iE = Boolean.parseBoolean(var1.getAttribute("beneficial"));
      this.jY = Boolean.parseBoolean(var1.getAttribute("procedural"));
   }

   public String getID() {
      return this.id;
   }

   public String getName() {
      return this.name;
   }

   public String getDescription() {
      return this.description;
   }

   public boolean aW() {
      return this.iE;
   }

   public boolean bb() {
      return this.jY;
   }

   public boolean equals(Object var1) {
      if (var1 instanceof String) {
         return this.jY ? ((String)var1).startsWith(this.id + "#") : ((String)var1).equals(this.id);
      } else {
         return super.equals(var1);
      }
   }

   public String toString() {
      return this.name;
   }

   public static int getCount() {
      return kl.size();
   }

   public static eM S(int var0) {
      return (eM)kl.get(var0);
   }

   public static int w(String var0) {
      return kl.indexOf(new eO(var0));
   }

   public static eM x(String var0) {
      int var1 = kl.indexOf(new eO(var0));
      return var1 >= 0 ? (eM)kl.get(var1) : null;
   }
}
