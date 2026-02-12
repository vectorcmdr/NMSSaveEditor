using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;

namespace NMSSaveEditor
{

public class fq {
   int lz = 15;

   static string c(long var0) {
      int var2 = (int)(var0 % 60L);
      var0 /= 60L;
      int var3 = (int)(var0 % 60L);
      var0 /= 60L;
      int var4 = (int)var0;
      StringBuilder var5 = new StringBuilder();
      var5.Append(var4);
      var5.Append(':');
      if (var3 < 10) {
         var5.Append(0);
      }

      var5.Append(var3);
      return var5.ToString();
   }

   static string c(fq var0) {
      if (var0 is fJ) {
         return "Steam";
      } else if (var0 is fT) {
         return "Xbox Game Pass";
      } else {
         return var0 is fA ? "PS4 - Save Wizard" : null;
      }
   }

   static fq a(FileInfo var0, fR var1) {
      if (!var0.Exists) {
         return null;
      } else {
         try {
            if (var0.Attributes.HasFlag(FileAttributes.Directory)) {
               if (var0.listFiles((var0x) => {
                  return var0x.Name.Equals("containers.index");
               }).Length > 0) {
                  return new fT(var0, var1);
               }

               if (var0.listFiles((var0x) => {
                  return var0x.Name.Equals("accountdata.hg") || Pattern.Matches("save\\d*.hg", var0x.Name.ToLower());
               }).Length > 0) {
                  return new fJ(var0, var1);
               }

               if (var0.listFiles((var0x) => {
                  return Pattern.Matches("savedata\\d{2}.hg", var0x.Name.ToLower());
               }).Length > 0) {
                  return new fA(var0, var1);
               }
            } else {
               if (var0.Name.Equals("containers.index")) {
                  return new fT(var0.Directory, var1);
               }

               if (var0.Name.Equals("accountdata.hg") || Pattern.Matches("save\\d*.hg", var0.Name.ToLower())) {
                  return new fJ(var0.Directory, var1);
               }

               if (Pattern.Matches("savedata\\d{2}.hg", var0.Name.ToLower())) {
                  return new fA(var0.Directory, var1);
               }
            }
         } catch (IOException var3) {
            hc.error("cannot load storage", var3);
         }

         return null;
      }
   }

   static fq a(string var0, FileInfo var1, fR var2) {
      if (!var1.Exists) {
         return null;
      } else if (var0 == null) {
         return a(var1, var2);
      } else {
         try {
            if ("Steam".Equals(var0)) {
               return new fJ(var1, var2);
            }

            if ("Xbox Game Pass".Equals(var0)) {
               return new fT(var1, var2);
            }

            if ("PS4 - Save Wizard".Equals(var0)) {
               return new fA(var1, var2);
            }
         } catch (IOException var4) {
            hc.error("cannot load storage", var4);
         }

         return null;
      }
   }

   public virtual FileInfo bS() { return null; }

   public virtual fr bT() { return null; }

   public virtual ft[] bU() { return new ft[0]; }
   public ft[] bV() {
      return (ft[])new List<object>(new object[]{this.bU()}).Where((var0) => {
         return var0.Count != 0;
      }).ToArray((var0) => {
         return new ft[var0];
      });
   }

   public virtual int W(string var1) { return 0; }
   public bool bW() {
      return false;
   }
   public string a(int var1, eY var2) {
      throw new IOException("cannot create slot " + (var1 + 1));
   }

   public virtual void X(string var1) { }
}

}
