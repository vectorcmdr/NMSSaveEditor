using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Xml;

namespace NMSSaveEditor
{

public class er {
   public string id;
   public string name;
   gq iB;
   public int iC;
   gr iD;
   public bool iE;
   gr[] iF;
   public static List<object> iG = new List<object>();

   static er() {
      Stream var0 = typeof(Application).GetManifestResourceStream("db/frigates.xml");
      if (var0 != null) {
         try {
            Document var1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(var0);
            Element var2 = var1.getDocumentElement();
            XmlNodeList var3 = var2.getChildNodes();

            for(int var4 = 0; var4 < var3.getLength(); ++var4) {
               Node var5 = var3.item(var4);
               if (var5 is Element && var5.getNodeName().Equals("trait")) {
                  iG.Add(new er((Element)var5));
               }
            }
         } catch (Exception var6) {
         } catch (Exception var7) {
         } catch (IOException var8) {
         }
      }

      iG.sort(new es());
   }

   public er(Element var1) {
      this.id = var1.getAttribute("id");
      this.name = var1.getAttribute("name");
      string var2 = var1.getAttribute("type");
      this.iB = var2 == null ? null : gq.valueOf(var2);
      this.iC = int.Parse(var1.getAttribute("strength"));
      var2 = var1.getAttribute("primary");
      this.iD = var2 == null ? null : gr.an(var2);
      this.iE = Boolean.parseBoolean(var1.getAttribute("beneficial"));
      this.iF = n(var1.getAttribute("secondary"));
   }

   public static gr[] n(string var0) {
      List<object> var1 = new List<object>();
      int var2 = 0;

      while(var2 < var0.length()) {
         int var4 = var0.IndexOf(",", var2);
         gr var3;
         if (var4 >= 0) {
            var3 = gr.an(var0.Substring(var2, var4));
            var2 = var4 + 1;
         } else {
            var3 = gr.an(var0.Substring(var2));
            var2 = var0.length();
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
      return (er[])iG.stream().filter((var1) => {
         return var1.iD == var0;
      }).ToArray((var0x) => {
         return new er[var0x];
      });
   }

   public static er[] b(gr var0) {
      return (er[])iG.stream().filter((var1) => {
         return Arrays.stream(var1.iF).anyMatch((var1x) => {
            return var1x == var0;
         });
      }).ToArray((var0x) => {
         return new er[var0x];
      });
   }

   public static er o(string var0) {
      int var1 = iG.IndexOf(new et(var0));
      return var1 >= 0 ? (er)iG[var1] : null;
   }
}

}
