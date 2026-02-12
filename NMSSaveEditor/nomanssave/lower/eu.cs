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

public class eu {
   private static List<object> iH = new List<object>();

   static eu() {
      Stream var0 = JavaCompat.GetResourceStream("db/inventory.xml");
      if (var0 != null) {
         try {
            XmlDocument var1 = JavaCompat.ParseXml(var0);
            XmlElement var2 = var1.DocumentElement;
            XmlNodeList var3 = var2.ChildNodes;

            for(int var4 = 0; var4 < var3.Count; ++var4) {
               XmlNode var5 = var3.Item(var4);
               if (var5 is XmlElement && var5.Name.Equals("difficulty")) {
                  iH.Add(new ev((System.Xml.XmlElement)var5));
               }
            }
         } catch (ParserConfigurationException var6) {
         } catch (SAXException var7) {
         } catch (IOException var8) {
         }
      }

   }

   public static ew b(string var0, string var1) {
      IEnumerator<object> var3 = iH.GetEnumerator();

      while(true) {
         ev var2;
         do {
            if (!var3.MoveNext()) {
               return null;
            }

            var2 = (ev)var3.Current;
         } while(!var2.id.Equals(var0));

         IEnumerator<object> var5 = var2.GetEnumerator();

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
