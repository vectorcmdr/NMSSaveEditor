using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Xml;

namespace NMSSaveEditor
{

public abstract class ey {
   public static int jD = 0;
   public static int jE = 1;
   public static int jF = 2;
   public static int jG = 3;
   string id;
   private static Pattern jH = Pattern.compile("%(\\w+)%");
   private static List<object> jI;
   private static List<object> jJ;

   static ey() {
      Element var0 = null;
      Stream var1 = typeof(Application).GetManifestResourceStream("db/items.xml");
      if (var1 != null) {
         try {
            Document var2 = XmlDocument.newInstance().newDocumentBuilder().parse(var1);
            var0 = var2.getDocumentElement();
         } catch (Exception var7) {
         } catch (Exception var8) {
         } catch (IOException var9) {
         }
      }

      List<object> var10 = new List<object>();
      if (var0 != null) {
         XmlNodeList var3 = var0.getChildNodes();

         for(int var4 = 0; var4 < var3.getLength(); ++var4) {
            Node var5 = var3.item(var4);
            if (var5 is Element && var5.getNodeName().Equals("product-template")) {
               var10.Add(new eA((Element)var5));
            }
         }
      }

      jI = new List<object>(var10);
      List<object> var11 = new List<object>();
      if (var0 != null) {
         XmlNodeList var12 = var0.getChildNodes();

         for(int var14 = 0; var14 < var12.getLength(); ++var14) {
            Node var6 = var12.item(var14);
            if (var6 is Element && var6.getNodeName().Equals("substance")) {
               var11.Add(new eP((Element)var6));
            } else if (var6 is Element && var6.getNodeName().Equals("product")) {
               var11.Add(new eH((Element)var6, false));
            } else if (var6 is Element && var6.getNodeName().Equals("procedural-product")) {
               var11.Add(new eH((Element)var6, true));
            } else if (var6 is Element && var6.getNodeName().Equals("technology")) {
               var11.Add(new eQ((Element)var6, false));
            } else if (var6 is Element && var6.getNodeName().Equals("procedural-technology")) {
               var11.Add(new eQ((Element)var6, true));
            }
         }
      }

      List<object> var13 = (List<object>)var11.stream().filter((var0x) => {
         return var0x is eQ;
      }).map(typeof(eQ).cast).map((var0x) => {
         return var0x.bv();
      }).filter((var0x) => {
         return var0x != null;
      }).collect(Collectors.toList());
      var11.AddRange(var13);
      var13.sort((var0x, var1x) => {
         return var0x.Name.CompareTo(var1x.Name);
      });
      jJ = new List<object>(var11);
   }

   ey(string var1) {
      this.id = var1;
   }

   public string getID() {
      return this.id;
   }

   private static string L(int var0) {
      StringBuilder var1 = new StringBuilder();
      var1.Append(Integer.toString(var0));

      while(var1.length() < 5) {
         var1.Insert(0, '0');
      }

      return var1.ToString();
   }

   public object aZ() {
      if (this.id.length() >= 2 && this.id[0] == '^') {
         if (this.bb()) {
            int var1 = (int)Math.Floor(new Random().NextDouble() * 100000.0D);
            return this.id + "#" + L(var1);
         } else {
            return this.id;
         }
      } else {
         throw new Exception("Cannot create ID: invalid string");
      }
   }

   public object M(int var1) {
      if (this.id.length() >= 2 && this.id[0] == '^') {
         if (this.bb()) {
            if (var1 >= 0 && var1 < 100000) {
               return this.id + "#" + L(var1);
            } else {
               throw new Exception("Cannot create ID: invalid proc");
            }
         } else if (var1 != 0) {
            throw new Exception("Cannot create ID: invalid proc");
         } else {
            return this.id;
         }
      } else {
         throw new Exception("Cannot create ID: invalid string");
      }
   }

   public abstract eB ba();

   public abstract bool bb();

   public abstract string getName();

   public abstract ex bc();

   public abstract bool bd();

   public abstract bool be();

   public abstract Integer bf();

   public abstract string bg();

   public abstract bool bh();

   public abstract string bi();

   public Image N(int var1) {
      string var2 = this.bi();
      switch(var1) {
      case 0:
         return var2 == null ? null : Application.a(var2);
      case 1:
         return var2 == null ? null : Application.a(var2, 40, 40);
      case 2:
         return var2 == null ? null : Application.a(var2, 80, 80);
      case 3:
         return var2 == null ? null : Application.a(var2, 20, 20);
      default:
         return null;
      }
   }

   public Image c(int var1, int var2) {
      string var3 = this.bi();
      return var3 == null ? null : Application.a(var3, var1, var2);
   }

   public abstract int bj();

   public abstract string getDescription();

   public abstract List<object> bk();

   public string toString() {
      return this.id;
   }

   static string a(Element var0) {
      if (var0 == null) {
         throw new ArgumentException();
      } else {
         XmlNodeList var1 = var0.getChildNodes();
         StringBuilder var2 = new StringBuilder();
         bool var3 = false;

         for(int var5 = 0; var5 < var1.getLength(); ++var5) {
            Node var4 = var1.item(var5);
            if (var4.getNodeType() == 3) {
               var2.Append(var4.getNodeValue());
               var3 = true;
            }
         }

         if (!var3) {
            return null;
         } else {
            return var2.ToString();
         }
      }
   }

   private static List<object> O(int var0) {
      List<object> var1 = new List<object>();
      bool var2 = (var0 & 16384) == 0;
      if ((var0 & 4) == 4) {
         var1.Add(ex.je);
         var1.Add(ex.js);
         var1.Add(ex.jv);
         if (var2) {
            var1.Add(ex.jw);
         }
      }

      if ((var0 & 64) == 64) {
         var1.Add(ex.jq);
         var1.Add(ex.js);
         if (var2) {
            var1.Add(ex.jr);
         }
      }

      if ((var0 & 256) == 256) {
         var1.Add(ex.ju);
         var1.Add(ex.js);
         var1.Add(ex.jv);
         if (var2) {
            var1.Add(ex.jw);
         }
      }

      if ((var0 & 2) == 2) {
         var1.Add(ex.jf);
         if (var2) {
            var1.Add(ex.jg);
         }
      }

      if ((var0 & 1) == 1) {
         var1.Add(ex.jh);
         if (var2) {
            var1.Add(ex.ji);
         }
      }

      if ((var0 & 8) == 8) {
         var1.Add(ex.jk);
         if (var2) {
            var1.Add(ex.jl);
         }
      }

      if ((var0 & 16) == 16) {
         var1.Add(ex.jm);
         var1.Add(ex.jt);
         if (var2) {
            var1.Add(ex.jn);
         }
      }

      if ((var0 & 32) == 32) {
         var1.Add(ex.jo);
         var1.Add(ex.jt);
         if (var2) {
            var1.Add(ex.jp);
         }
      }

      if ((var0 & 128) == 128) {
         var1.Add(ex.jx);
         var1.Add(ex.jt);
         if (var2) {
            var1.Add(ex.jy);
         }
      }

      bool var3 = (var0 & '耀') != 0;
      if ((var0 & 1024) == 1024) {
         if (var3) {
            var1.Add(ex.iL);
            var1.Add(ex.iP);
            var1.Add(ex.iQ);
            var1.Add(ex.iS);
         } else {
            var1.Add(ex.iL);
            var1.Add(ex.iM);
            var1.Add(ex.iN);
            var1.Add(ex.iO);
            var1.Add(ex.iP);
            var1.Add(ex.iQ);
            var1.Add(ex.iR);
            var1.Add(ex.iS);
         }
      }

      if ((var0 & 512) == 512) {
         if (var3) {
            var1.Add(ex.iT);
            var1.Add(ex.iU);
         } else {
            var1.Add(ex.iT);
            var1.Add(ex.iU);
            var1.Add(ex.iV);
            var1.Add(ex.iW);
            var1.Add(ex.iZ);
            var1.Add(ex.jb);
            if (var2) {
               var1.Add(ex.iY);
            }

            if ((var0 & 8192) == 0) {
               var1.Add(ex.jd);
            }
         }
      }

      if ((var0 & 2048) == 2048) {
         var1.Add(ex.jc);
      }

      return var1;
   }

   public static List<object> b(int var0, string var1) {
      string var2 = var1.ToUpper();
      return (List<object>)jJ.stream().filter((var2x) => {
         return var2x.Name.ToUpper().IndexOf(var2) >= 0 && O(var0).Contains(var2x.bc());
      }).collect(Collectors.toList());
   }

   public static List<object> bl() {
      return (List<object>)jJ.stream().filter((var0) => {
         return var0 is eQ && !var0.bb() && var0.bc() != ex.jz;
      }).collect(Collectors.toList());
   }

   public static List<object> bm() {
      return (List<object>)jJ.stream().filter((var0) => {
         return var0 is eH && !var0.bb();
      }).collect(Collectors.toList());
   }

   public static ey d(object var0) {
      string var1 = var0 is fg ? ((fg)var0).bP() : var0.ToString();
      return (ey)jJ.stream().filter((var2) => {
         return !var2.bb() && !(var2 is eR) ? var0.Equals(var2.id) : var1.StartsWith(var2.id + "#");
      }).findFirst().orElse((object)null);
   }

   static eA p(string var0) {
      return (eA)jI.stream().filter((var1) => {
         return var0.Equals(var1.id);
      }).findFirst().orElse((object)null);
   }
   static Pattern bn() {
      return jH;
   }
}

}