using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Xml;

namespace NMSSaveEditor
{



public class eS {
   public string Text => getText();
   public string id;
   public string text;
   public Dictionary<object, object> kp;
   public static List<object> kq = new List<object>();

   static eS() {
      Stream var0 = typeof(Application).GetManifestResourceStream("db/words.xml");
      if (var0 != null) {
         try {
            Document var1 = DocumentBuilderFactory.newInstance(object>();
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
      return (eU)this.kp[var1];
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
      return (eS)kq[var0];
   }

   public static Iterable by() {
      return new List<object>(kq);
   }
}



}
