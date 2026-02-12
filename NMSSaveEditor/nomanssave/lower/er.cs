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
      Stream var0 = typeof(Application).GetManifestResourceStream("db/frigates.xml");
      if (var0 != null) {
         try {
            Document var1 = DocumentBuilderFactory.newInstance(", var2);
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

      return (gr[])var1.ToArray();
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
      }).ToArray() => {
         return new er[var0x];
      });
   }

   public static er[] b(gr var0) {
      return (er[])iG.stream().filter((var1) => {
         return Arrays.stream(var1.iF).anyMatch((var1x) => {
            return var1x == var0;
         });
      }).ToArray() => {
         return new er[var0x];
      });
   }

   public static er o(string var0) {
      int var1 = iG.IndexOf(new et(var0));
      return var1 >= 0 ? (er)iG[var1] : null;
   }
}



}
