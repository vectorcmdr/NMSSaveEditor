using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Xml;

namespace NMSSaveEditor
{

public class eu {
   private static List<object> iH = new List<object>();

   static eu() {
      Stream var0 = typeof(Application).GetManifestResourceStream("db/inventory.xml");
      if (var0 != null) {
         try {
            Document var1 = XmlDocument.newInstance().newDocumentBuilder().parse(var0);
            Element var2 = var1.getDocumentElement();
            XmlNodeList var3 = var2.getChildNodes();

            for(int var4 = 0; var4 < var3.getLength(); ++var4) {
               Node var5 = var3.item(var4);
               if (var5 is Element && var5.getNodeName().Equals("difficulty")) {
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
      IEnumerator var3 = iH.GetEnumerator();

      while(true) {
         ev var2;
         do {
            if (!var3.MoveNext()) {
               return null;
            }

            var2 = (ev)var3.Current;
         } while(!var2.id.Equals(var0));

         IEnumerator var5 = var2.GetEnumerator();

         while(var5.MoveNext()) {
            ew var4 = (ew)var5.Current;
            if (var4.iI.Equals(var1)) {
               return var4;
            }
         }
      }
   }
}

}
