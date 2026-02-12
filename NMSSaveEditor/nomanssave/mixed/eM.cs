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

public class eM {
   public string id;
   public string name;
   public string description;
   public bool iE;
   public bool jY;
   public static List<object> kl = new List<object>();

   static eM() {
      Stream var0 = JavaCompat.GetResourceStream("db/settlements.xml");
      if (var0 != null) {
         try {
            XmlDocument var1 = JavaCompat.ParseXml(var0);
            XmlElement var2 = var1.DocumentElement;
            XmlNodeList var3 = var2.ChildNodes;
             for(int var4 = 0; var4 < var3.Count; ++var4) {
               XmlNode var5 = var3.Item(var4);
               if (var5 is XmlElement && var5.Name.Equals("perk")) {
                  kl.Add(new eM((XmlElement)var5));
               }
            }
         } catch (ParserConfigurationException var6) {
         } catch (SAXException var7) {
         } catch (IOException var8) {
         }
      }
       kl.sort(new eN());
   }

   public eM(XmlElement var1) {
      this.id = var1.GetAttribute("id");
      this.name = var1.GetAttribute("name");
      this.description = var1.GetAttribute("description");
      this.iE = bool.Parse(var1.GetAttribute("beneficial"));
      this.jY = bool.Parse(var1.GetAttribute("procedural"));
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

   public bool equals(Object var1) {
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
      return (eM)kl.Get(var0);
   }

   public static int w(string var0) {
      return kl.IndexOf(new eO(var0));
   }

   public static eM x(string var0) {
      int var1 = kl.IndexOf(new eO(var0));
      return var1 >= 0 ? (eM)kl.Get(var1) : null;
   }
}

}
