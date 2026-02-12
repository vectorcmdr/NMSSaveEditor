using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Xml;

namespace NMSSaveEditor
{



public class eI {
   public string Name => getName();
   public static int kf = 0;
   public static int kg = 1;
   public static int kh = 2;
   public int type;
   public string id;
   public string name;
   public static List<object> ki = new List<object>();
   public static List<object> kj = new List<object>();
   public static List<object> kk = new List<object>();

   static eI() {
      Stream var0 = typeof(Application).Assembly.GetManifestResourceStream("NMSSaveEditor.Resources.db.rewards.xml");
      if (var0 != null) {
         try {
            XmlDocument doc = new XmlDocument();
            doc.Load(var0);
            XmlElement var2 = doc.DocumentElement;
            XmlNodeList var3 = var2.ChildNodes;

            for(int var4 = 0; var4 < var3.Count; ++var4) {
               XmlNode var5 = var3[var4];
               if (var5 is XmlElement && var5.Name.Equals("season")) {
                  ki.Add(new eI((XmlElement)var5, 0));
               }

               if (var5 is XmlElement && var5.Name.Equals("twitch")) {
                  kj.Add(new eI((XmlElement)var5, 1));
               }

               if (var5 is XmlElement && var5.Name.Equals("platform")) {
                  kk.Add(new eI((XmlElement)var5, 2));
               }
            }
         } catch (Exception) { }
      }

      ki.Sort(new eJ());
      kj.Sort(new eK());
      kk.Sort(new eL());
   }

   public eI(XmlElement var1, int var2) {
      this.type = var2;
      this.id = ((XmlElement)var1).GetAttribute("id");
      this.name = ((XmlElement)var1).GetAttribute("name");
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
      return (eI)ki[var0];
   }

   public static int br() {
      return kj.Count;
   }

   public static eI Q(int var0) {
      return (eI)kj[var0];
   }

   public static int bs() {
      return kk.Count;
   }

   public static eI R(int var0) {
      return (eI)kk[var0];
   }

   public static IEnumerable<object> bt() {
      return new List<object>(ki);
   }

   public static IEnumerable<object> bu() {
      return new List<object>(kj);
   }
}



}
