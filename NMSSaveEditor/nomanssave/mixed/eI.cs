using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Xml;

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
      Stream var0 = typeof(Application).GetManifestResourceStream("db/rewards.xml");
      if (var0 != null) {
         try {
            Document var1 = XmlDocument.newInstance().newDocumentBuilder().parse(var0);
            Element var2 = var1.getDocumentElement();
            XmlNodeList var3 = var2.getChildNodes();

            for(int var4 = 0; var4 < var3.getLength(); ++var4) {
               Node var5 = var3.item(var4);
               if (var5 is Element && var5.getNodeName().Equals("season")) {
                  ki.Add(new eI((Element)var5, 0));
               }

               if (var5 is Element && var5.getNodeName().Equals("twitch")) {
                  kj.Add(new eI((Element)var5, 1));
               }

               if (var5 is Element && var5.getNodeName().Equals("platform")) {
                  kk.Add(new eI((Element)var5, 2));
               }
            }
         } catch (Exception var6) {
         } catch (Exception var7) {
         } catch (IOException var8) {
         }
      }

      ki.sort(new eJ());
      kj.sort(new eK());
      kk.sort(new eL());
   }

   private eI(Element var1, int var2) {
      this.type = var2;
      this.id = var1.getAttribute("id");
      this.name = var1.getAttribute("name");
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

   public static Iterable bt() {
      return new List<object>(ki);
   }

   public static Iterable bu() {
      return new List<object>(kj);
   }
}

}
