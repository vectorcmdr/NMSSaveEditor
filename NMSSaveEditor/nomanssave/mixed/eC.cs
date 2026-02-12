using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class eC {
   public static eD[] jS = new eD[2];
   public eD jT;
   public eE jU;

   static eC() {
      jS[0] = c("db/jsonmap.txt", "NMS 5.21 (savegame)");
      jS[1] = c("db/jsonmapac.txt", "NMS 5.21 (account)");
   }

   public static void main(string[] var0) {
      for(int var1 = 0; var1 < jS.Length; ++var1) {
         if (jS[var1] != null) {
            IEnumerator<object> var3 = jS[var1].GetEnumerator();

            while(var3.MoveNext()) {
               eF var2 = (eF)var3.Current;
               string var4 = hashName(var2.name);
               if (!var2.key.Equals(var4)) {
                  Console.WriteLine(var2.name + " = " + var2.key + " incorrect, should be " + var4);
               }
            }
         }
      }

   }

   public static string hashName(string var0) {
      long[] var1 = new long[]{8268756125562466087L, 8268756125562466087L};
      // PORT_TODO: hh.a(var0.GetBytes(System.Text.Encoding.UTF8), var1);
      string var2 = "0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxy";
      long var3 = 4294967295L & var1[0] >> 32;
      var3 = var3 % 68L << 32 | 4294967295L & var1[0];
      int var5 = (int)(var3 % 68L);
      int var6 = (int)((8796093022207L & var1[0] >> 21) % 68L);
      int var7 = (int)((4194303L & var1[0] >> 42) % 68L);
      return new string(new char[]{var2[var5], var2[var6], var2[var7]});
   }

   public static eC a(eG var0, string var1) {
      eD var2 = jS[var0.ordinal()];
      return var2 != null && var2.s(var1) ? new eC(var2) : null;
   }

   public static eD c(string var0, string var1) {
      Stream var2 = typeof(Application).GetManifestResourceStream(var0);
      if (var2 == null) {
         return null;
      } else {
         try {
            return new eD(var2, var1, (eD)null);
         } catch (IOException var4) {
            hc.error("Could not load key mapping file: " + var0, var4);
            return null;
         }
      }
   }

   public eC(eD var1) {
      this.jT = var1;
      this.jU = new eE((eE)null, (eE)null);
   }

   public Dictionary<object, object> bp() {
      return (Dictionary<object, object>)this.jU.stream().collect(Collectors.toMap((var0) => {
         return var0.key;
      }, (var0) => {
         return var0.name;
      }));
   }

   public string q(string var1) {
      string var2;
      eF var3;
      if ((var3 = this.jU.t(var1)) != null) {
         var2 = var3.name;
      } else if ((var3 = this.jT.t(var1)) != null) {
         var2 = var3.name;
      } else {
         if ((var3 = this.jT.v(var1)) != null) {
            var2 = var3.name;
         } else {
            hc.warn("  name mapping not found: " + var1);
            var2 = var1;
         }

         // PORT_TODO: this.jU.Add(var1, var2);
      }

      return var2;
   }

   public string r(string var1) {
      string var2;
      eF var3;
      if ((var3 = this.jU.u(var1)) != null) {
         var2 = var3.key;
      } else if ((var3 = this.jT.u(var1)) != null) {
         var2 = var3.key;
      } else {
         var2 = var1;
         if (this.jT.t(var1) == null) {
            hc.warn("  reverse mapping not found: " + var1);
         }
      }

      return var2;
   }

   public string toString() {
      return this.jT.ToString();
   }
}



}
