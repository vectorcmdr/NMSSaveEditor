using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Xml;

namespace NMSSaveEditor
{



public class eM {
   public string Name => getName();
   public string id;
   public string name;
   public string description;
   public bool iE;
   public bool jY;
   public static List<object> kl = new List<object>();

   static eM() {
      Stream var0 = typeof(Application).GetManifestResourceStream("db/settlements.xml");
      if (var0 != null) {
         try {
            Document var1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(var0);
            Element var2 = var1.getDocumentElement();
            XmlNodeList var3 = var2.getChildNodes();

            for(int var4 = 0; var4 < var3.getLength(); ++var4) {
               Node var5 = var3.item(var4);
               if (var5 is Element && var5.getNodeName().Equals("perk")) {
                  kl.Add(new eM((Element)var5));
               }
            }
         } catch (Exception var6) {
         } catch (IOException var8) {
         }
      }

      kl.sort(new eN());
   }

   public eM(Element var1) {
      this.id = var1.getAttribute("id");
      this.name = var1.getAttribute("name");
      this.description = var1.getAttribute("description");
      this.iE = bool.Parse(var1.getAttribute("beneficial"));
      this.jY = bool.Parse(var1.getAttribute("procedural"));
   }

   public string getID() {
      return this.id;
   }

   public string getName() {
      return this.name;
   }

   public string getDescription() {
      return this.description;
   }

   public bool aW() {
      return this.iE;
   }

   public bool bb() {
      return this.jY;
   }

   public bool equals(object var1) {
      if (var1 is string) {
         return this.jY ? ((string)var1).StartsWith(this.id + "#") : ((string)var1).Equals(this.id);
      } else {
         return base.Equals(var1);
      }
   }

   public string toString() {
      return this.name;
   }

   public static int getCount() {
      return kl.Count;
   }

   public static eM S(int var0) {
      return (eM)kl[var0];
   }

   public static int w(string var0) {
      return kl.IndexOf(new eO(var0));
   }

   public static eM x(string var0) {
      int var1 = kl.IndexOf(new eO(var0));
      return var1 >= 0 ? (eM)kl[var1] : null;
   }
}



}
