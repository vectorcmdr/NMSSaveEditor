using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Xml;

namespace NMSSaveEditor
{

public class eS {
   string id;
   string text;
   private Dictionary<object, object> kp;
   private static List<object> kq = new List<object>();

   static eS() {
      Stream var0 = typeof(Application).GetManifestResourceStream("db/words.xml");
      if (var0 != null) {
         try {
            Document var1 = XmlDocument.newInstance().newDocumentBuilder().parse(var0);
            Element var2 = var1.getDocumentElement();
            XmlNodeList var3 = var2.getChildNodes();

            for(int var4 = 0; var4 < var3.getLength(); ++var4) {
               Node var5 = var3.item(var4);
               if (var5 is Element && var5.getNodeName().Equals("word")) {
                  kq.Add(new eS((Element)var5));
               }
            }
         } catch (Exception var6) {
         } catch (Exception var7) {
         } catch (IOException var8) {
         }
      }

      kq.sort(new eT());
   }

   private eS(Element var1) {
      this.id = var1.getAttribute("id");
      this.text = var1.getAttribute("text");
      this.kp = new Dictionary<object, object>();
      XmlNodeList var2 = var1.getElementsByTagName("group");

      for(int var3 = 0; var3 < var2.getLength(); ++var3) {
         Element var4 = (Element)var2.item(var3);
         string var5 = var4.getAttribute("group");
         eU var6 = eU.C(var4.getAttribute("race"));
         if (var6 != null) {
            this.kp.Put(var5, var6);
         }
      }

   }

   public string getID() {
      return this.id;
   }

   public string getText() {
      return this.text;
   }

   public Iterable bw() {
      return Collections.unmodifiableSet(this.kp.Keys);
   }

   public eU z(string var1) {
      return (eU)this.kp[(var1);
   }

   public bool a(eU var1) {
      return this.kp.ContainsValue(var1);
   }

   public static eS A(string var0) {
      IEnumerator var2 = kq.GetEnumerator();

      while(var2.MoveNext()) {
         eS var1 = (eS)var2.Current;
         if (var1.id.Equals(var0)) {
            return var1;
         }
      }

      return null;
   }

   public static eS B(string var0) {
      IEnumerator var2 = kq.GetEnumerator();

      while(var2.MoveNext()) {
         eS var1 = (eS)var2.Current;
         if (var1.kp.ContainsKey(var0)) {
            return var1;
         }
      }

      return null;
   }

   public static int bx() {
      return kq.Count;
   }

   public static eS T(int var0) {
      return (eS)kq[(var0);
   }

   public static Iterable by() {
      return new List<object>(kq);
   }
}

}
