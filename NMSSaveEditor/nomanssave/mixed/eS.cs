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

public class eS {
   public string id;
   public string text;
   public Dictionary<object, object> kp;
   public static List<object> kq = new List<object>();

   static eS() {
      Stream var0 = JavaCompat.GetResourceStream("db/words.xml");
      if (var0 != null) {
         try {
            XmlDocument var1 = JavaCompat.ParseXml(var0);
            XmlElement var2 = var1.DocumentElement;
            XmlNodeList var3 = var2.ChildNodes;

            for(int var4 = 0; var4 < var3.Count; ++var4) {
               XmlNode var5 = var3.Item(var4);
               if (var5 is XmlElement && var5.Name.Equals("word")) {
                  kq.Add(new eS((System.Xml.XmlElement)var5));
               }
            }
         } catch (ParserConfigurationException var6) {
         } catch (SAXException var7) {
         } catch (IOException var8) {
         }
      }

      kq.sort(new eT());
   }

   public eS(XmlElement var1) {
      this.id = var1.GetAttribute("id");
      this.text = var1.GetAttribute("text");
      this.kp = new Dictionary<object, object>();
      XmlNodeList var2 = var1.GetElementsByTagName("group");

      for(int var3 = 0; var3 < var2.Count; ++var3) {
         XmlElement var4 = (System.Xml.XmlElement)var2.Item(var3);
         string var5 = var4.GetAttribute("group");
         eU var6 = eU.C(var4.GetAttribute("race"));
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
      return (eU)this.kp.Get(var1);
   }

   public bool a(eU var1) {
      return this.kp.ContainsValue(var1);
   }

   public static eS A(string var0) {
      IEnumerator<object> var2 = kq.GetEnumerator();

      while(var2.MoveNext()) {
         eS var1 = (eS)var2.Current;
         if (var1.id.Equals(var0)) {
            return var1;
         }
      }

      return null;
   }

   public static eS B(string var0) {
      IEnumerator<object> var2 = kq.GetEnumerator();

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
      return (eS)kq.Get(var0);
   }

   public static Iterable by() {
      return JavaCollections.UnmodifiableList(kq);
   }
}

}
