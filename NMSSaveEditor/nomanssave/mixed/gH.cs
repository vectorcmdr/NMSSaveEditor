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

public class gH {
   public int index;
   public eY rp;
   public List<object> gT;

   public static gH[] C(eY var0) {
      eV var1 = var0.d("ShipOwnership");
      if (var1 != null && var1.Count != 0) {
         List<object> var2 = new List<object>();

         for(int var3 = 0; var3 < var1.Count; ++var3) {
            eY var4 = var1.V(var3);
            if (var4.d("Resource.Seed").ab(0)) {
               var2.Add(new gH(var3, var4, var4.H("Inventory"), var4.H("Inventory_TechOnly"), var4.H("Inventory_Cargo")));
            }
         }

         return (gH[])var2.ToArray(new gH[0]);
      } else {
         return new gH[0];
      }
   }

   public static gH c(eY var0, FileInfo var1) {
      eV var2 = var0.d("ShipOwnership");
      if (var2 != null && var2.Count != 0) {
         int var3 = -1;

         eY var5;
         for(int var4 = 0; var4 < var2.Count; ++var4) {
            var5 = var2.V(var4);
            if (!var5.d("Resource.Seed").ab(0)) {
               var3 = var4;
               break;
            }
         }

         if (var3 < 0) {
            throw new Exception("Ship cannot be imported to current file!");
         } else {
            eY var14 = gR.az("ship");
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

            var5 = var14.H("Inventory");
            if (var5 == null) {
               throw new Exception("Invalid ship data");
            } else {
               var6 = var14.d("Resource.Seed");
               if (var6 != null && var6.ab(0)) {
                  var2.a(var3, var14);
                  return new gH(var3, var14, var5, var14.H("Inventory_TechOnly"), var14.H("Inventory_Cargo"));
               } else {
                  throw new Exception("Invalid ship data");
               }
            }
         }
      } else {
         throw new Exception("Ship cannot be imported to current file!");
      }
   }

   public static Function a(gH var0, string[] var1) {
      return (var2) => {
         string var3 = var0.Name;
         if (var3 == null || var3.Length == 0) {
            var3 = "Ship [" + var0.index + "]";
         }

         return new string[]{var3, var1[var0.dZ() ? 1 : 0]};
      };
   }

   public gH(int var1, eY var2, eY var3, eY var4, eY var5) {
      this.index = var1;
      this.rp = var2;
      string[] var7 = new string[]{"Technology", "Organ Chamber"};
      short var9 = 0;
      byte var10 = 8;
      byte var11 = 6;
      byte var12 = 8;
      byte var13 = 6;
      bool var14 = Application.e().D();
      string[] var6;
      string[] var8;
      if (var14) {
         var10 = 10;
         var11 = 12;
         var12 = 10;
         var13 = 6;
         var6 = new string[]{"Cargo", "Storage Sacs"};
         var8 = new string[]{"Unused", "Unused"};
         var5 = null;
      } else {
         var9 = 3584;
         var6 = new string[]{"General", "Storage Sacs"};
         var8 = new string[]{"Cargo", "Inflated Sacs"};
      }

      List<object> var15 = new List<object>();
      var15.Add(new gI(this, a(this, var6), var3, 0, var10, var11, false, true, var14, var1));
      if (var4 != null) {
         var15.Add(new gJ(this, a(this, var7), var4, 0, var12, var13, true, true, var1));
      }

      if (var5 != null) {
         var15.Add(new gK(this, a(this, var8), var5, var9, 8, 6, false, true, var1));
      }

      this.gT = JavaCollections.UnmodifiableList(var15);
   }

   public void a(FileInfo var1, bool var2) {
      Exception var3 = null;
      Object var4 = null;

      try {
         fj var5 = new fj(new FileStream(var1));

         try {
            eY var6 = this.rp.bE();
            if (!var2) {
               eV var7 = var6.d("Inventory.Slots");

               int var8;
               eY var9;
               for(var8 = 0; var8 < var7.Count; ++var8) {
                  var9 = var7.V(var8);
                  if (!var9.getValueAsString("Type.InventoryType").Equals("Technology")) {
                     var7.ac(var8--);
                  }
               }

               var7 = var6.d("Inventory_Cargo.Slots");

               for(var8 = 0; var8 < var7.Count; ++var8) {
                  var9 = var7.V(var8);
                  if (!var9.getValueAsString("Type.InventoryType").Equals("Technology")) {
                     var7.ac(var8--);
                  }
               }
            }

            var5.h(var6);
         } finally {
            if (var5 != null) {
               var5.Close();
            }

         }

      } catch (Exception var15) {
         if (var3 == null) {
            var3 = var15;
         } else if (var3 != var15) {
            var3.addSuppressed(var15);
         }

         throw var3;
      }
   }

   public int getIndex() {
      return this.index;
   }

   public string getName() {
      return this.rp.getValueAsString("Name");
   }

   public void setName(string var1) {
      this.rp.b("Name", (Object)var1);
   }

   public bool dZ() {
      return gL.aw(this.cT()) == gL.rx;
   }

   public int ea() {
      gL var1 = gL.aw(this.cT());
      return var1 == null ? 4 : var1.ea();
   }

   public string cT() {
      return this.rp.getValueAsString("Resource.Filename");
   }

   public void ag(string var1) {
      this.rp.b("Resource.Filename", (Object)var1);
      gL var2 = gL.aw(var1);
      this.gT.forEach((var1x) => {
         var1x.az(var2 == null ? 4 : var2.ea());
      });
      if (var2 == gL.rx) {
         this.d("^ALIEN_SHIP", 1.0D);
         this.av("^ROBOT_SHIP");
      } else if (var2 == gL.rC) {
         this.av("^ALIEN_SHIP");
         this.d("^ROBOT_SHIP", 1.0D);
      } else {
         this.av("^ALIEN_SHIP");
         this.av("^ROBOT_SHIP");
      }

   }

   public string cK() {
      return this.rp.d("Resource.Seed").X(1);
   }

   public void aa(string var1) {
      this.rp.d("Resource.Seed").a(1, var1);
   }

   public void cm() {
      this.rp.b("Resource.Filename", (Object)"");
      this.rp.d("Resource.Seed").a(0, Boolean.FALSE);
      this.rp.d("Resource.Seed").a(1, "0x0");
   }

   public string cW() {
      return this.rp.getValueAsString("Inventory.Class.InventoryClass");
   }

   public void aj(string var1) {
      this.rp.b("Inventory.Class.InventoryClass", (Object)var1);
      eY var2 = this.rp.H("Inventory_TechOnly.Class");
      if (var2 != null) {
         var2.b("InventoryClass", (Object)var1);
      }

      var2 = this.rp.H("Inventory_Cargo.Class");
      if (var2 != null) {
         var2.b("InventoryClass", (Object)var1);
      }

   }

   public List<object> cC() {
      return this.gT;
   }

   public double ak(string var1) {
      return ((gt)this.gT.Get(0)).ak(var1);
   }

   public void d(string var1, double var2) {
      this.gT.forEach((var3) => {
         var3.d(var1, var2);
      });
   }

   public void av(string var1) {
      this.gT.forEach((var1x) => {
         var1x.ap(var1);
      });
   }

   public double dF() {
      return this.ak("^SHIP_DAMAGE");
   }

   public void d(double var1) {
      this.d("^SHIP_DAMAGE", var1);
   }

   public double eb() {
      return this.ak("^SHIP_SHIELD");
   }

   public void h(double var1) {
      this.d("^SHIP_SHIELD", var1);
   }

   public double cX() {
      return this.ak("^SHIP_HYPERDRIVE");
   }

   public void a(double var1) {
      this.d("^SHIP_HYPERDRIVE", var1);
   }

   public double ec() {
      return this.ak("^SHIP_AGILE");
   }

   public void i(double var1) {
      this.d("^SHIP_AGILE", var1);
   }

   public string toString() {
      string var1 = this.Name;
      if (var1 != null && var1.Length != 0) {
         return var1;
      } else {
         gL var2 = gL.aw(this.cT());
         return var2 == null ? "Unknown [" + this.index + "]" : var2 + " [" + this.index + "]";
      }
   }

   // $FF: synthetic method
   static int b(gH var0) {
      return var0.ea();
   }
}

}
