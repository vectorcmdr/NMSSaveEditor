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

public class eI {
   public static int kf = 0;
   public static int kg = 1;
   public static int kh = 2;
   int type;
   string id;
   string name;
   private static List<object> ki = new List<object>();
   private static List<object> kj = new List<object>();
   private static List<object> kk = new List<object>();

   static eI() {
      Stream var0 = JavaCompat.GetResourceStream("db/rewards.xml");
      if (var0 != null) {
         try {
            XmlDocument var1 = JavaCompat.ParseXml(var0);
            XmlElement var2 = var1.DocumentElement;
            XmlNodeList var3 = var2.ChildNodes;

            for(int var4 = 0; var4 < var3.Count; ++var4) {
               XmlNode var5 = var3.Item(var4);
               if (var5 is XmlElement && var5.Name.Equals("season")) {
                  ki.Add(new eI((System.Xml.XmlElement)var5, 0));
               }

               if (var5 is XmlElement && var5.Name.Equals("twitch")) {
                  kj.Add(new eI((System.Xml.XmlElement)var5, 1));
               }

               if (var5 is XmlElement && var5.Name.Equals("platform")) {
                  kk.Add(new eI((System.Xml.XmlElement)var5, 2));
               }
            }
         } catch (ParserConfigurationException var6) {
         } catch (SAXException var7) {
         } catch (IOException var8) {
         }
      }

      ki.sort(new eJ());
      kj.sort(new eK());
      kk.sort(new eL());
   }

   private eI(XmlElement var1, int var2) {
      this.type = var2;
      this.id = var1.GetAttribute("id");
      this.name = var1.GetAttribute("name");
   }

   public string getID() {
      return this.id;
   }

   public string getName() {
      return this.name;
   }

   public static int bq() {
      return ki.Count;
   }

   public static eI P(int var0) {
      return (eI)ki.Get(var0);
   }

   public static int br() {
      return kj.Count;
   }

   public static eI Q(int var0) {
      return (eI)kj.Get(var0);
   }

   public static int bs() {
      return kk.Count;
   }

   public static eI R(int var0) {
      return (eI)kk.Get(var0);
   }

   public static Iterable bt() {
      return JavaCollections.UnmodifiableList(ki);
   }

   public static Iterable bu() {
      return JavaCollections.UnmodifiableList(kj);
   }
}

}
