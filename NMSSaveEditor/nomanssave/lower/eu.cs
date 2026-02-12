using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Xml;

namespace NMSSaveEditor
{

public class eu {
   private static List iH = new List<object>();

   static {
      Stream var0 = Application.class.getResourceAsStream("db/inventory.xml");
      if (var0 != null) {
         try {
            Document var1 = XmlDocument.newInstance().newDocumentBuilder().parse(var0);
            Element var2 = var1.getDocumentElement();
            XmlNodeList var3 = var2.getChildNodes();

            for(int var4 = 0; var4 < var3.getLength(); ++var4) {
               Node var5 = var3.item(var4);
               if (var5 is Element && var5.getNodeName().equals("difficulty")) {
                  iH.Add(new ev((Element)var5));
               }
            }
         } catch (Exception var6) {
         } catch (Exception var7) {
         } catch (IOException var8) {
         }
      }

   }

   public static ew b(string var0, string var1) {
      IEnumerator var3 = iH.iterator();

      while(true) {
         ev var2;
         do {
            if (!var3.hasNext()) {
               return null;
            }

            var2 = (ev)var3.next();
         } while(!var2.id.equals(var0));

         IEnumerator var5 = var2.iterator();

         while(var5.hasNext()) {
            ew var4 = (ew)var5.next();
            if (var4.iI.equals(var1)) {
               return var4;
            }
         }
      }
   }
}

}
