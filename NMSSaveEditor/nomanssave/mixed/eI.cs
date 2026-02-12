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
      Stream var0 = typeof(Application).GetManifestResourceStream("db/rewards.xml");
      if (var0 != null) {
         try {
            Document var1 = DocumentBuilderFactory.newInstance(0));
               }

               if (var5 is Element && var5.getNodeName().Equals("twitch")) {
                  kj.Add(new eI((Element)var5, 1));
               }

               if (var5 is Element && var5.getNodeName().Equals("platform")) {
                  kk.Add(new eI((Element)var5, 2));
               }
            }
         } catch (Exception var6) {
         } catch (IOException var8) {
         }
      }

      ki.sort(new eJ());
      kj.sort(new eK());
      kk.sort(new eL());
   }

   public eI(Element var1, int var2) {
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
