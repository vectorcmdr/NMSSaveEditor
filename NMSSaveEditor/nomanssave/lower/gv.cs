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

public class gv {
   public int index;
   public eY qF;
   public gt qG;

   public static gv[] v(eY var0) {
      eV var1 = var0.d("Multitools");
      if (var1 != null && var1.Count != 0) {
         List<object> var2 = new List<object>();
          for(int var3 = 0; var3 < var1.Count; ++var3) {
            eY var4 = var1.V(var3);
            if (var4.d("Seed").ab(0)) {
               var2.Add(new gv(var3, var4, var4.H("Store")));
            }
         }
          return (gv[])var2.ToArray(new gv[0]);
      } else {
         return new gv[]{new gw(var0, var0.H("WeaponInventory"))};
      }
   }

   public static gv b(eY var0, FileInfo var1) {
      eV var2 = var0.d("Multitools");
      if (var2 != null && var2.Count != 0) {
         int var3 = -1;
          eY var5;
         for(int var4 = 0; var4 < var2.Count; ++var4) {
            var5 = var2.V(var4);
            if (!var5.d("Seed").ab(0)) {
               var3 = var4;
               break;
            }
         }
          if (var3 < 0) {
            throw new Exception("Weapon cannot be imported to current file!");
         } else {
            eY var14 = gR.az("multitool");
            Exception var15 = null;
            eV var6 = null;
             try {
               ff var7 = new ff(new FileStream(var1));
                try {
                  if (var14 == null) {
                     var14 = var7.bK();
                  } else {
                     var14.c(var7.bK());
                  }
               } finally {
                  if (var7 != null) {
                     var7.Close();
                  }
                }
            } catch (Exception var13) {
               if (var15 == null) {
                  var15 = var13;
               } else if (var15 != var13) {
                  var15.addSuppressed(var13);
               }
                throw var15;
            }
             var2.a(var3, var14);
            var5 = var14.H("Store");
            if (var5 == null) {
               throw new Exception("Invalid weapon data");
            } else {
               var6 = var14.d("Seed");
               if (var6 != null && var6.ab(0)) {
                  return new gv(var3, var14, var5);
               } else {
                  throw new Exception("Invalid weapon data");
               }
            }
         }
      } else {
         throw new Exception("Weapon cannot be imported to current file!");
      }
   }

   public static Function b(gv var0) {
      return (var1) => {
         string var2 = var0.Name;
         if (var2 == null || var2.Length == 0) {
            var2 = "Multitool [" + var0.index + "]";
         }
          return new string[]{var2};
      };
   }

   public gv(int var1, eY var2, eY var3) {
      this.index = var1;
      this.qF = var2;
      byte var4 = 8;
      byte var5 = 6;
      if (Application.e().D()) {
         var4 = 10;
         var5 = 10;
      }

      this.qG = new gt(b(this), var3, 2, var4, var5, true, true);
   }

   public void j(FileInfo var1) {
      Exception var2 = null;
      Object var3 = null;
       try {
         fj var4 = new fj(new FileStream(var1));
          try {
            eY var5 = this.qF.bE();
            var4.h(var5);
         } finally {
            if (var4 != null) {
               var4.Close();
            }
          }
       } catch (Exception var11) {
         if (var2 == null) {
            var2 = var11;
         } else if (var2 != var11) {
            var2.addSuppressed(var11);
         }
          throw var2;
      }
   }

   public int getIndex() {
      return this.index;
   }

   public string getName() {
      return this.qF.getValueAsString("Name");
   }

   public void setName(string var1) {
      this.qF.b("Name", (Object)var1);
   }

   public string cT() {
      return this.qF.getValueAsString("Resource.Filename");
   }

   public void ag(string var1) {
      this.qF.b("Resource.Filename", (Object)var1);
   }

   public string cK() {
      return this.qF.d("Seed").X(1);
   }

   public void aa(string var1) {
      this.qF.d("Seed").a(1, var1);
   }

   public string cW() {
      return this.qF.getValueAsString("Store.Class.InventoryClass");
   }

   public void aj(string var1) {
      this.qF.b("Store.Class.InventoryClass", (Object)var1);
   }

   public gt dE() {
      return this.qG;
   }

   public double ak(string var1) {
      return this.qG.ak(var1);
   }

   public void d(string var1, double var2) {
      this.qG.d(var1, var2);
   }

   public double dF() {
      return this.ak("^WEAPON_DAMAGE");
   }

   public void d(double var1) {
      this.d("^WEAPON_DAMAGE", var1);
   }

   public double dG() {
      return this.ak("^WEAPON_MINING");
   }

   public void e(double var1) {
      this.d("^WEAPON_MINING", var1);
   }

   public double dH() {
      return this.ak("^WEAPON_SCAN");
   }

   public void f(double var1) {
      this.d("^WEAPON_SCAN", var1);
   }

   public void cm() {
      this.qF.b("Seed", (Object)(new eV(new Object[]{Boolean.FALSE, "0x0"})));
   }

   public string toString() {
      string var1 = this.Name;
      return var1 != null && var1.Length != 0 ? var1 : "Multitool [" + this.index + "]";
   }
}

}
