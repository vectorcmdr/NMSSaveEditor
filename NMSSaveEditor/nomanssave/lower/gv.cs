using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class gv {
   public string Name => getName();
   public int index;
   public eY qF;
   public gt qG;

   public static gv[] v(eY var0) {
      // PORT_TODO: eV var1 = var0.d("Multitools");
      // PORT_TODO: if (var1 != null && var1.Count != 0) {
         // PORT_TODO: List<object> var2 = new List<object>();

         // PORT_TODO: for(int var3 = 0; var3 < var1.Count; ++var3) {
            // PORT_TODO: eY var4 = var1.V(var3);
            // PORT_TODO: if (var4.d("Seed").ab(0)) {
               // PORT_TODO: var2.Add(new gv(var3, var4, var4.H("Store")));
            // PORT_TODO: }
         // PORT_TODO: }

         // PORT_TODO: return (gv[])var2.ToArray();
      // PORT_TODO: } else {
         // PORT_TODO: return new gv[]{new gw(var0, var0.H("WeaponInventory"))};
      // PORT_TODO: }
      return null;
   }

   public static gv b(eY var0, FileInfo var1) {
      // PORT_TODO: eV var2 = var0.d("Multitools");
      // PORT_TODO: if (var2 != null && var2.Count != 0) {
         // PORT_TODO: int var3 = -1;

         // PORT_TODO: eY var5;
         // PORT_TODO: for(int var4 = 0; var4 < var2.Count; ++var4) {
            // PORT_TODO: var5 = var2.V(var4);
            // PORT_TODO: if (!var5.d("Seed").ab(0)) {
               // PORT_TODO: var3 = var4;
               // PORT_TODO: break;
            // PORT_TODO: }
         // PORT_TODO: }

         // PORT_TODO: if (var3 < 0) {
            // PORT_TODO: throw new Exception("Weapon cannot be imported to current file!");
         // PORT_TODO: } else {
            // PORT_TODO: eY var14 = gR.az("multitool");
            // PORT_TODO: Exception var15 = null;
            // PORT_TODO: eV var6 = null;

            // PORT_TODO: try {
               // PORT_TODO: ff var7 = new ff(new FileStream((var1).ToString(), System.IO.FileMode.Open));

               // PORT_TODO: try {
                  // PORT_TODO: if (var14 == null) {
                     // PORT_TODO: var14 = var7.bK();
                  // PORT_TODO: } else {
                     // PORT_TODO: var14.c(var7.bK());
                  // PORT_TODO: }
               // PORT_TODO: } finally {
                  // PORT_TODO: if (var7 != null) {
                     // PORT_TODO: var7.Close();
                  // PORT_TODO: }

               // PORT_TODO: }
            // PORT_TODO: } catch (Exception var13) {
               // PORT_TODO: if (var15 == null) {
                  // PORT_TODO: var15 = var13;
               // PORT_TODO: } else if (var15 != var13) {
                  // PORT_TODO: var15.addSuppressed(var13);
               // PORT_TODO: }

               // PORT_TODO: throw var15;
            // PORT_TODO: }

            // PORT_TODO: var2.a(var3, var14);
            // PORT_TODO: var5 = var14.H("Store");
            // PORT_TODO: if (var5 == null) {
               // PORT_TODO: throw new Exception("Invalid weapon data");
            // PORT_TODO: } else {
               // PORT_TODO: var6 = var14.d("Seed");
               // PORT_TODO: if (var6 != null && var6.ab(0)) {
                  // PORT_TODO: return new gv(var3, var14, var5);
               // PORT_TODO: } else {
                  // PORT_TODO: throw new Exception("Invalid weapon data");
               // PORT_TODO: }
            // PORT_TODO: }
         // PORT_TODO: }
      // PORT_TODO: } else {
         // PORT_TODO: throw new Exception("Weapon cannot be imported to current file!");
      // PORT_TODO: }
      return default;
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
      object var3 = null;

      try {
         fj var4 = new fj(new FileStream((var1).ToString(), System.IO.FileMode.Open));

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
      // PORT_TODO: return this.qF.getValueAsString("Name");
      return default;
   }

   public void setName(string var1) {
      // PORT_TODO: this.qF.b("Name", (object)var1);
   }

   public string cT() {
      // PORT_TODO: return this.qF.getValueAsString("Resource.Filename");
      return default;
   }

   public void ag(string var1) {
      // PORT_TODO: this.qF.b("Resource.Filename", (object)var1);
   }

   public string cK() {
      // PORT_TODO: return this.qF.d("Seed").X(1);
      return default;
   }

   public void aa(string var1) {
      // PORT_TODO: this.qF.d("Seed").a(1, var1);
   }

   public string cW() {
      // PORT_TODO: return this.qF.getValueAsString("Store.Class.InventoryClass");
      return default;
   }

   public void aj(string var1) {
      // PORT_TODO: this.qF.b("Store.Class.InventoryClass", (object)var1);
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
      // PORT_TODO: this.qF.b("Seed", (object)(new eV(new object[]{Boolean.FALSE, "0x0"})));
   }

   public string toString() {
      string var1 = this.Name;
      return var1 != null && var1.Length != 0 ? var1 : "Multitool [" + this.index + "]";
   }
}



}
