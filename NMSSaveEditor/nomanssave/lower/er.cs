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

public class er {
   string id;
   string name;
   gq iB;
   int iC;
   gr iD;
   bool iE;
   gr[] iF;
   private static List<object> iG = new List<object>();

   static er() {
      Stream var0 = JavaCompat.GetResourceStream("db/frigates.xml");
      if (var0 != null) {
         try {
            XmlDocument var1 = JavaCompat.ParseXml(var0);
            XmlElement var2 = var1.DocumentElement;
            XmlNodeList var3 = var2.ChildNodes;

            for(int var4 = 0; var4 < var3.Count; ++var4) {
               XmlNode var5 = var3.Item(var4);
               if (var5 is XmlElement && var5.Name.Equals("trait")) {
                  iG.Add(new er((System.Xml.XmlElement)var5));
               }
            }
         } catch (ParserConfigurationException var6) {
         } catch (SAXException var7) {
         } catch (IOException var8) {
         }
      }

      iG.sort(new es());
   }

   private er(XmlElement var1) {
      this.id = var1.GetAttribute("id");
      this.name = var1.GetAttribute("name");
      string var2 = var1.GetAttribute("type");
      this.iB = var2 == null ? null : gq.valueOf(var2);
      this.iC = int.Parse(var1.GetAttribute("strength"));
      var2 = var1.GetAttribute("primary");
      this.iD = var2 == null ? null : gr.an(var2);
      this.iE = bool.Parse(var1.GetAttribute("beneficial"));
      this.iF = n(var1.GetAttribute("secondary"));
   }

   private static gr[] n(string var0) {
      List<object> var1 = new List<object>();
      int var2 = 0;

      while(var2 < var0.Length) {
         int var4 = var0.IndexOf(",", var2);
         gr var3;
         if (var4 >= 0) {
            var3 = gr.an(var0.Substring(var2, var4));
            var2 = var4 + 1;
         } else {
            var3 = gr.an(var0.Substring(var2));
            var2 = var0.Length;
         }

         if (var3 != null) {
            var1.Add(var3);
         }
      }

      return (gr[])var1.ToArray(new gr[0]);
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

   public string toString() {
      string var1 = (this.iC > 0 ? "+" : "") + this.iC + (this.iB == gq.oY ? "%" : "");
      return this.name + " (" + var1 + " " + this.iB + ")";
   }

   public static er[] a(gr var0) {
      return (er[])iG.filter((var1) => {
         return var1.iD == var0;
      }).ToArray((var0x) => {
         return new er[var0x];
      });
   }

   public static er[] b(gr var0) {
      return (er[])iG.filter((var1) => {
         return Arrays.stream(var1.iF).anyMatch((var1x) => {
            return var1x == var0;
         });
      }).ToArray((var0x) => {
         return new er[var0x];
      });
   }

   public static er o(string var0) {
      int var1 = iG.IndexOf(new et(var0));
      return var1 >= 0 ? (er)iG.Get(var1) : null;
   }
}

}
