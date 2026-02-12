using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Xml;

namespace NMSSaveEditor
{



public class er {
   public string Name => getName();
   public string id;
   public string name;
   public gq iB;
   public int iC;
   public gr iD;
   public bool iE;
   public gr[] iF;
   public static List<object> iG = new List<object>();

   static er() {
      Stream var0 = typeof(Application).Assembly.GetManifestResourceStream("NMSSaveEditor.Resources.db.frigates.xml");
      if (var0 != null) {
         try {
            XmlDocument doc = new XmlDocument();
            doc.Load(var0);
            XmlElement var2 = doc.DocumentElement;
            XmlNodeList var3 = var2.ChildNodes;

            for(int var4 = 0; var4 < var3.Count; ++var4) {
               XmlNode var5 = var3[var4];
               if (var5 is XmlElement && var5.Name.Equals("trait")) {
                  iG.Add(new er((XmlElement)var5));
               }
            }
         } catch (Exception) { }
      }

      iG.Sort(new es());
   }

   public er(XmlElement var1) {
      this.id = ((XmlElement)var1).GetAttribute("id");
      this.name = ((XmlElement)var1).GetAttribute("name");
      string var2 = ((XmlElement)var1).GetAttribute("type");
      this.iB = var2 == null ? null : gq.valueOf(var2);
      this.iC = int.Parse(((XmlElement)var1).GetAttribute("strength"));
      var2 = ((XmlElement)var1).GetAttribute("primary");
      this.iD = var2 == null ? null : gr.an(var2);
      this.iE = bool.Parse(((XmlElement)var1).GetAttribute("beneficial"));
      this.iF = n(((XmlElement)var1).GetAttribute("secondary"));
   }

   public static gr[] n(string var0) {
      List<object> var1 = new List<object>();
      int var2 = 0;

      while(var2 < var0.Length) {
         int var4 = var0.IndexOf(",", var2);
         gr var3;
         if (var4 >= 0) {
            var3 = gr.an(var0.Substring(var2, var4 - var2));
            var2 = var4 + 1;
         } else {
            var3 = gr.an(var0.Substring(var2));
            var2 = var0.Length;
         }

         if (var3 != null) {
            var1.Add(var3);
         }
      }

      return var1.Cast<gr>().ToArray();
   }

   public string getID() {
      return this.id;
   }

   public string getName() {
      return this.name;
   }

   public gq aU() {
      return this.iB;
   }

   public int aV() {
      return this.iC * this.iB.di();
   }

   public bool aW() {
      return this.iE;
   }

   public override string ToString() {
      string var1 = (this.iC > 0 ? "+" : "") + this.iC + (this.iB == gq.oY ? "%" : "");
      return this.name + " (" + var1 + " " + this.iB + ")";
   }

   public static er[] a(gr var0) {
      return iG.Cast<er>().Where((var1) => {
         return var1.iD == var0;
      }).ToArray();
   }

   public static er[] b(gr var0) {
      return iG.Cast<er>().Where((var1) => {
         return var1.iF.Any((var1x) => {
            return var1x == var0;
         });
      }).ToArray();
   }

   public static er o(string var0) {
      int var1 = iG.IndexOf(new et(var0));
      return var1 >= 0 ? (er)iG[var1] : null;
   }
}



}
