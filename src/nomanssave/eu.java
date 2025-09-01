package nomanssave;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class eu {
   private static List iH = new ArrayList();

   static {
      InputStream var0 = Application.class.getResourceAsStream("db/inventory.xml");
      if (var0 != null) {
         try {
            Document var1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(var0);
            Element var2 = var1.getDocumentElement();
            NodeList var3 = var2.getChildNodes();

            for(int var4 = 0; var4 < var3.getLength(); ++var4) {
               Node var5 = var3.item(var4);
               if (var5 instanceof Element && var5.getNodeName().equals("difficulty")) {
                  iH.add(new ev((Element)var5));
               }
            }
         } catch (ParserConfigurationException var6) {
         } catch (SAXException var7) {
         } catch (IOException var8) {
         }
      }

   }

   public static ew b(String var0, String var1) {
      Iterator var3 = iH.iterator();

      while(true) {
         ev var2;
         do {
            if (!var3.hasNext()) {
               return null;
            }

            var2 = (ev)var3.next();
         } while(!var2.id.equals(var0));

         Iterator var5 = var2.iterator();

         while(var5.hasNext()) {
            ew var4 = (ew)var5.next();
            if (var4.iI.equals(var1)) {
               return var4;
            }
         }
      }
   }
}
