using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class gt {
   public string Name => getName();
   public static int pW = 1;
   public static int pX = 2;
   public static int pY = 4;
   public static int pZ = 8;
   public static int qa = 16;
   public static int qb = 32;
   public static int qc = 64;
   public static int qd = 128;
   public static int qe = 256;
   public static int qf = 324;
   public static int qg = 176;
   public static int qh = 260;
   public static int qi = 511;
   public static int qj = 512;
   public static int qk = 1024;
   public static int ql = 2048;
   public static int qm = 3584;
   public static int qn = 8192;
   public static int qo = 16384;
   public static int qp = 32768;
   public static int qq = 8;
   public static int qr = 6;
   public Function qs;
   public eY qt;
   public int r;
   public bool qu;
   public bool qv;
   public bool qw;
   public bool qx;
   public int width;
   public int height;
   public int bE;
   public int bF;
   public eY[][] qy;
   public bool[][] qz;
   public static int[] qA;
   public static int[] qB;
   public static int[] qC;

   public static int a(ex var0) {
      switch(dw()[var0.ordinal()]) {
      case 18:
         return 3584;
      case 19:
      case 25:
      default:
         return 1536;
      case 20:
         return 4;
      case 21:
      case 22:
         return 2;
      case 23:
      case 24:
         return 1;
      case 26:
      case 27:
         return 8;
      case 28:
      case 29:
         return 16;
      case 30:
      case 31:
         return 32;
      case 32:
      case 33:
         return 64;
      case 34:
         return 324;
      case 35:
         return 176;
      case 36:
         return 256;
      case 37:
      case 38:
         return 260;
      case 39:
      case 40:
         return 128;
      }
   }

   public gt(Function var1, eY var2, int var3, int var4, int var5, bool var6, bool var7) {
      this(var1, var2, var3, var4, var5, var6, var7, true, true);
   }

   public gt(Function var1, eY var2, int var3, int var4, int var5, bool var6, bool var7, bool var8, bool var9) {
      this.qs = var1;
      this.qt = var2;
      this.r = var3;
      this.qu = var6;
      this.qv = var7;
      this.qw = var8;
      this.qx = var9;
      this.width = var2.J("Width");
      this.height = var2.J("Height");
      ew var10 = eu.b(Application.e().E(), var2.getValueAsString("StackSizeGroup.InventoryStackSizeGroup"));
      if (var10 != null) {
         this.bE = var10.aX();
         this.bF = var10.aY();
      } else {
         switch(dx()[Application.e().F().ordinal()]) {
         case 1:
         case 3:
         case 4:
         default:
            this.bE = 9999;
            this.bF = var2.c("ProductMaxStorageMultiplier", 10);
            break;
         case 2:
         case 5:
            this.bE = 250 * var2.c("SubstanceMaxStorageMultiplier", 2);
            this.bF = var2.c("ProductMaxStorageMultiplier", 10);
         }
      }

      int var11 = Math.Max(this.height, var5);
      int var12 = Math.Max(this.width, var4);
      this.qy = new eY[var11][];
      this.qz = new bool[var11][];

      for(int var13 = 0; var13 < var11; ++var13) {
         this.qy[var13] = new eY[var12];
         this.qz[var13] = new bool[var12];
      }

      eV var19 = var2.d("ValidSlotIndices");

      int var17;
      for(int var14 = 0; var14 < var19.Count; ++var14) {
         eY var15 = var19.V(var14);
         int var16 = var15.J("X");
         var17 = var15.J("Y");
         if (var16 >= 0 && var16 < var12 && var17 >= 0 && var17 < var11) {
            this.qz[var17][var16] = true;
         }
      }

      eV var20 = var2.d("Slots");

      for(int var21 = 0; var21 < var20.Count; ++var21) {
         eY var22 = var20.V(var21);
         var17 = var22.J("Index.X");
         int var18 = var22.J("Index.Y");
         if (var17 >= 0 && var17 < var12 && var18 >= 0 && var18 < var11) {
            this.qy[var18][var17] = var22;
         }
      }

   }

   public string getSimpleName() {
      string[] var1 = (string[])this.qs.apply(this);
      if (var1.Length == 0) {
         return "Unknown";
      } else {
         return var1.Length == 1 ? var1[0] : var1[1];
      }
   }

   public string getFullName() {
      string[] var1 = (string[])this.qs.apply(this);
      if (var1.Length == 0) {
         return "Unknown";
      } else {
         return var1.Length == 1 ? var1[0] : var1[0] + " - " + var1[1];
      }
   }

   public string getName() {
      return this.qt.getValueAsString("Name");
   }

   public void setName(string var1) {
      if (var1 == null) {
         var1 = "";
      }

      this.qt.b("Name", (object)var1);
   }

   public int dj() {
      return this.r;
   }

   public bool ay(int var1) {
      return (this.dj() & var1) != 0;
   }

   public bool dk() {
      return this.qv;
   }

   public int getWidth() {
      return this.width;
   }

   public int getHeight() {
      return this.height;
   }

   public bool ao(string var1) {
      eV var2 = this.qt.d("BaseStatValues");
      if (var2 == null) {
         return false;
      } else {
         for(int var3 = 0; var3 < var2.Count; ++var3) {
            eY var4 = var2.V(var3);
            if (var4.getValueAsString("BaseStatID").Equals(var1)) {
               return true;
            }
         }

         return false;
      }
   }

   public double ak(string var1) {
      eV var2 = this.qt.d("BaseStatValues");
      if (var2 == null) {
         return 0.0D;
      } else {
         for(int var3 = 0; var3 < var2.Count; ++var3) {
            eY var4 = var2.V(var3);
            if (var4.getValueAsString("BaseStatID").Equals(var1)) {
               return var4.L("Value");
            }
         }

         return 0.0D;
      }
   }

   public void d(string var1, double var2) {
      eV var4 = this.qt.d("BaseStatValues");
      if (var4 == null) {
         throw new Exception("Could not set base stat");
      } else {
         bool var5 = false;

         for(int var6 = 0; var6 < var4.Count; ++var6) {
            eY var7 = var4.V(var6);
            if (var7.getValueAsString("BaseStatID").Equals(var1)) {
               var7.b("Value", (object)var2);
               var5 = true;
               break;
            }
         }

         if (!var5) {
            eY var8 = new eY();
            var8.b("BaseStatID", (object)var1);
            var8.b("Value", (object)var2);
            var4.f(var8);
            var5 = true;
         }

      }
   }

   public bool ap(string var1) {
      eV var2 = this.qt.d("BaseStatValues");
      if (var2 == null) {
         return false;
      } else {
         for(int var3 = 0; var3 < var2.Count; ++var3) {
            eY var4 = var2.V(var3);
            if (var4.getValueAsString("BaseStatID").Equals(var1)) {
               var2.ac(var3);
               return true;
            }
         }

         return false;
      }
   }

   public Size getSize() {
      return new Size(this.width, this.height);
   }

   public bool a(Size var1) {
      int var2 = 1;
      int var3 = 1;

      int var4;
      for(var4 = 0; var4 < this.qy.Length; ++var4) {
         for(int var5 = 0; var5 < this.qy[var4].Length; ++var5) {
            if (this.qz[var4][var5]) {
               var2 = Math.Max(var2, var5 + 1);
               var3 = Math.Max(var3, var4 + 1);
            }
         }
      }

      if (var1.width < var2) {
         throw new Exception("Cannot resize less than min width");
      } else if (var1.height < var3) {
         throw new Exception("Cannot resize less than min height");
      } else {
         if (en.aS()) {
            if (var1.width > this.qz[0].Length) {
               for(var4 = 0; var4 < this.qy.Length; ++var4) {
                  eY[] var9 = new eY[var1.width];
                  bool[] var6 = new bool[var1.width];
                  Array.Copy(this.qy[var4], 0, var9, 0, this.qz[var4].Length);
                  Array.Copy(this.qz[var4], 0, var6, 0, this.qz[var4].Length);
                  this.qy[var4] = var9;
                  this.qz[var4] = var6;
               }
            }

            if (var1.height > this.qz.Length) {
               eY[][] var7 = new eY[var1.height][];
               bool[][] var10 = new bool[var1.height][];
               Array.Copy(this.qy, 0, var7, 0, this.qz.Length);
               Array.Copy(this.qz, 0, var10, 0, this.qz.Length);

               for(int var11 = this.qz.Length; var11 < var1.height; ++var11) {
                  var7[var11] = new eY[var1.width];
                  var10[var11] = new bool[var1.width];
               }

               this.qy = var7;
               this.qz = var10;
            }
         } else {
            if (var1.width > this.qz[0].Length) {
               throw new Exception("Cannot resize width greater than " + this.qz[0].Length);
            }

            if (var1.height > this.qz.Length) {
               throw new Exception("Cannot resize height greater than " + this.qz.Length);
            }
         }

         bool var8 = false;
         if (this.width != var1.width) {
            this.width = var1.width;
            this.qt.b("Width", (object)(((int)(this.width))));
            var8 = true;
         }

         if (this.height != var1.height) {
            this.height = var1.height;
            this.qt.b("Height", (object)(((int)(this.height))));
            var8 = true;
         }

         return var8;
      }
   }

   public bool dl() {
      bool var1 = false;
      if (this.width < this.qz[0].Length) {
         this.width = this.qz[0].Length;
         this.qt.b("Width", (object)(((int)(this.width))));
         var1 = true;
      }

      if (this.height < this.qz.Length) {
         this.height = this.qz.Length;
         this.qt.b("Height", (object)(((int)(this.height))));
         var1 = true;
      }

      return var1;
   }

   public Size dm() {
      int var1 = 1;
      int var2 = 1;

      for(int var3 = 0; var3 < this.qy.Length; ++var3) {
         for(int var4 = 0; var4 < this.qy[var3].Length; ++var4) {
            if (this.qz[var3][var4]) {
               var1 = Math.Max(var1, var4 + 1);
               var2 = Math.Max(var2, var3 + 1);
            }
         }
      }

      return new Size(var1, var2);
   }

   public Size dn() {
      return new Size(this.qz[0].Length, this.qz.Length);
   }

   public void a(int var1, int var2, int var3, int var4) {
      if (!this.qz[var2][var1]) {
         throw new Exception("Old slot not enabled");
      } else if (!this.qz[var4][var3]) {
         throw new Exception("New slot not enabled");
      } else {
         eV var5 = this.qt.d("Slots");
         if (this.qy[var4][var3] != null) {
            var5.g(this.qy[var4][var3]);
         }

         if (this.qy[var2][var1] == null) {
            this.qy[var4][var3] = null;
         } else {
            eY var6 = this.qy[var2][var1].bE();
            var6.b("Index.X", (object)var3);
            var6.b("Index.Y", (object)var4);
            var5.f(var6);
            this.qy[var4][var3] = var6;
         }

      }
   }

   public void b(int var1, int var2, int var3, int var4) {
      if (!this.qz[var2][var1]) {
         throw new Exception("Old slot not enabled");
      } else if (!this.qz[var4][var3]) {
         throw new Exception("New slot not enabled");
      } else {
         eY var5 = this.qy[var2][var1];
         eY var6 = this.qy[var4][var3];
         if (var5 != null && var6 != null && var5.getValue("Id").Equals(var6.getValue("Id"))) {
            int var7 = var6.J("MaxAmount");
            int var8 = var5.J("Amount") + var6.J("Amount");
            if (var8 <= var7) {
               var6.b("Amount", (object)var8);
               this.g(var1, var2);
            } else {
               var6.b("Amount", (object)var7);
               var5.b("Amount", (object)(var8 - var7));
            }

         } else {
            if (var5 != null) {
               var5.b("Index", (new fa()).d("X", var3).d("Y", var4).bH());
            }

            this.qy[var4][var3] = var5;
            if (var6 != null) {
               var6.b("Index", (new fa()).d("X", var1).d("Y", var2).bH());
            }

            this.qy[var2][var1] = var6;
         }
      }
   }

   public void c(int var1, int var2, int var3, int var4) {
      if (!this.qz[var2][var1]) {
         throw new Exception("Old slot not enabled");
      } else if (!this.qz[var4][var3]) {
         throw new Exception("New slot not enabled");
      } else {
         eY var5 = this.qy[var2][var1];
         eY var6 = this.qy[var4][var3];
         if (var5 != null) {
            var5.b("Index", (new fa()).d("X", var3).d("Y", var4).bH());
         }

         this.qy[var4][var3] = var5;
         if (var6 != null) {
            var6.b("Index", (new fa()).d("X", var1).d("Y", var2).bH());
         }

         this.qy[var2][var1] = var6;
      }
   }

   public gu f(int var1, int var2) {
      return this.qy[var2][var1] == null ? null : new gu(this, this.qy[var2][var1], (gu)null);
   }

   public void az(int var1) {
      eV var2 = this.qt.d("Slots");

      for(int var3 = 0; var3 < var2.Count; ++var3) {
         eY var4 = var2.V(var3);
         if (var4.getValueAsString("Type.InventoryType").Equals("Technology")) {
            ey var5 = ey.d(var4.getValue("Id"));
            if (var5 == null || (a(var5.bc()) & var1) == 0) {
               int var6 = var4.J("Index.X");
               int var7 = var4.J("Index.Y");
               if (var7 < this.qy.Length && var6 < this.qy[var7].Length) {
                  this.qy[var7][var6] = null;
               }

               var2.ac(var3--);
            }
         }
      }

   }

   public bool g(int var1, int var2) {
      if (this.qy[var2][var1] == null) {
         return false;
      } else {
         eV var3 = this.qt.d("Slots");

         for(int var4 = 0; var4 < var3.Count; ++var4) {
            eY var5 = var3.V(var4);
            if (var1 == var5.J("Index.X") && var2 == var5.J("Index.Y")) {
               var3.ac(var4);
            }
         }

         this.qy[var2][var1] = null;
         return true;
      }
   }

   public bool a(int var1, int var2, gt var3) {
      if (this.qy[var2][var1] == null) {
         return false;
      } else {
         string var4 = this.qy[var2][var1].getValueAsString("Type.InventoryType");
         object var5 = this.qy[var2][var1].getValue("Id");
         int var6 = this.qy[var2][var1].J("Amount");
         double var7 = this.qy[var2][var1].L("DamageFactor");
         bool var9 = this.qy[var2][var1].M("FullyInstalled");
         bool var10 = false;
         int var11;
         int var12;
         int var13;
         if (var4.Equals("Technology")) {
            var11 = this.qy[var2][var1].J("MaxAmount");

            for(var12 = 0; var12 < var3.qy.Length; ++var12) {
               for(var13 = 0; var13 < var3.qy[var12].Length; ++var13) {
                  if (var3.qy[var12][var13] == null && var3.qz[var12][var13] && !var3.l(var13, var12)) {
                     var3.a(var13, var12, var4, var5, var6, var11, var7, var9);
                     var6 = 0;
                     var10 = true;
                     break;
                  }
               }

               if (var6 == 0) {
                  break;
               }
            }
         }

         int var14;
         int var15;
         if (var6 > 0 && !var4.Equals("Technology")) {
            for(var11 = 0; var11 < var3.qy.Length; ++var11) {
               for(var12 = 0; var12 < var3.qy[var11].Length; ++var12) {
                  if (var3.qy[var11][var12] != null && var4.Equals(var3.qy[var11][var12].getValueAsString("Type.InventoryType")) && var5.Equals(var3.qy[var11][var12].getValue("Id"))) {
                     var13 = var3.qy[var11][var12].J("Amount");
                     var14 = var3.qy[var11][var12].J("MaxAmount");
                     if (var13 < var14) {
                        var15 = var6 > var14 - var13 ? var14 - var13 : var6;
                        hc.info("  added to existing stack: " + var15);
                        var3.qy[var11][var12].b("Amount", (object)(((int)(var13 + var15))));
                        var6 -= var15;
                        var10 = true;
                        if (var6 == 0) {
                           break;
                        }
                     }
                  }
               }

               if (var6 == 0) {
                  break;
               }
            }
         }

         if (var6 > 0 && !var4.Equals("Technology")) {
            ey var16 = ey.d(this.qy[var2][var1].getValue("Id"));
            if (var4.Equals("Technology")) {
               var12 = this.qy[var2][var1].J("MaxAmount");
            } else if (var4.Equals("Substance")) {
               if (var16 == null) {
                  var12 = var3.bE;
               } else {
                  var12 = Math.Max(1, var3.bE * var16.bj());
               }
            } else if (var4.Equals("Product")) {
               if (var16 == null) {
                  var12 = var3.bF;
               } else {
                  var12 = Math.Max(1, var3.bF * var16.bj());
               }
            } else {
               var12 = 1;
            }

            for(var13 = 0; var13 < var3.qy.Length; ++var13) {
               for(var14 = 0; var14 < var3.qy[var13].Length; ++var14) {
                  if (var3.qy[var13][var14] == null && var3.qz[var13][var14] && !var3.l(var14, var13)) {
                     var15 = var6 > var12 ? var12 : var6;
                     hc.info("  new stack: " + var15);
                     var3.a(var14, var13, var4, var5, var15, var12, var7, var9);
                     var6 -= var15;
                     var10 = true;
                     if (var6 == 0) {
                        break;
                     }
                  }
               }

               if (var6 == 0) {
                  break;
               }
            }
         }

         if (!var10) {
            return false;
         } else {
            if (var6 == 0) {
               eV var17 = this.qt.d("Slots");

               for(var12 = 0; var12 < var17.Count; ++var12) {
                  eY var18 = var17.V(var12);
                  if (var1 == var18.J("Index.X") && var2 == var18.J("Index.Y")) {
                     var17.ac(var12);
                  }
               }

               this.qy[var2][var1] = null;
            } else {
               hc.info("  remainder: " + var6);
               this.qy[var2][var1].b("Amount", (object)(((int)(var6))));
            }

            return true;
         }
      }
   }

   public int a(ey var1, int var2) {
      int var3;
      string var4;
      switch(dy()[var1.ba().ordinal()]) {
      case 1:
         return var2;
      case 2:
         var3 = Math.Max(1, this.bF * var1.bj());
         var4 = "Product";
         break;
      case 3:
         var3 = Math.Max(1, this.bE * var1.bj());
         var4 = "Substance";
         break;
      case 4:
         var3 = Math.Max(1, this.bF * var1.bj());
         var4 = "Techbox";
         break;
      default:
         return var2;
      }

      int var6;
      int var7;
      int var8;
      if (var2 > 0) {
         for(int var5 = 0; var5 < this.qy.Length; ++var5) {
            for(var6 = 0; var6 < this.qy[var5].Length; ++var6) {
               if (this.qy[var5][var6] != null && var4.Equals(this.qy[var5][var6].getValueAsString("Type.InventoryType")) && var1.getID().Equals(this.qy[var5][var6].getValue("Id"))) {
                  var7 = this.qy[var5][var6].J("Amount");
                  var8 = this.qy[var5][var6].J("MaxAmount");
                  if (var7 < var8) {
                     int var9 = var2 > var8 - var7 ? var8 - var7 : var2;
                     hc.info("  added to existing stack: " + var9);
                     this.qy[var5][var6].b("Amount", (object)(((int)(var7 + var9))));
                     var2 -= var9;
                     if (var2 == 0) {
                        break;
                     }
                  }
               }
            }

            if (var2 == 0) {
               break;
            }
         }
      }

      if (var2 > 0) {
         object var10 = var1.aZ();

         for(var6 = 0; var6 < this.qy.Length; ++var6) {
            for(var7 = 0; var7 < this.qy[var6].Length; ++var7) {
               if (this.qy[var6][var7] == null && this.qz[var6][var7] && !this.l(var7, var6)) {
                  var8 = var2 > var3 ? var3 : var2;
                  hc.info("  new stack: " + var8);
                  this.a(var7, var6, var4, var10, var8, var3, 0.0D, true);
                  var2 -= var8;
                  if (var2 == 0) {
                     break;
                  }
               }
            }

            if (var2 == 0) {
               break;
            }
         }
      }

      return var2;
   }

   public bool a(int var1, int var2, ey var3) {
      if (this.qy[var2][var1] != null) {
         return false;
      } else {
         int var4;
         int var5;
         switch(dy()[var3.ba().ordinal()]) {
         case 1:
            Integer var6 = var3.bf();
            if (var6 == null) {
               var4 = -1;
               var5 = 1;
            } else {
               var4 = var6;
               var5 = var6;
            }
            break;
         case 2:
            var5 = Math.Max(1, this.bF * var3.bj());
            var4 = var5;
            break;
         case 3:
            var5 = Math.Max(1, this.bE * var3.bj());
            var4 = var5;
            break;
         case 4:
            var5 = Math.Max(1, this.bF * var3.bj());
            var4 = var5;
            break;
         default:
            return false;
         }

         object var7 = var3.aZ();
         this.a(var1, var2, var3.ba().ToString(), var7, var4, var5, 0.0D, true);
         return true;
      }
   }

   public void a(int var1, int var2, string var3, object var4, int var5, int var6, double var7, bool var9) {
      eV var10 = this.qt.d("Slots");
      eY var11 = gR.az("slot");
      var11.b("Type.InventoryType", (object)var3);
      var11.b("Id", var4);
      var11.b("Amount", (object)(((int)(var5))));
      var11.b("MaxAmount", (object)(((int)(var6))));
      var11.b("DamageFactor", (object)(new Double(var7)));
      var11.b("FullyInstalled", (object)(new Boolean(var9)));
      var11.b("Index.X", (object)var1);
      var11.b("Index.Y", (object)var2);
      var10.f(var11);
      this.qy[var2][var1] = var11;
   }

   public bool aq(string var1) {
      for(int var2 = 0; var2 < this.qy.Length; ++var2) {
         for(int var3 = 0; var3 < this.qy[var2].Length; ++var3) {
            if (this.qy[var2][var3] != null && var1.Equals(this.qy[var2][var3].getValue("Type"))) {
               return true;
            }
         }
      }

      return false;
   }

   public bool h(int var1, int var2) {
      return this.qz[var2][var1];
   }

   public void i(int var1, int var2) {
      if (!this.qz[var2][var1]) {
         eY var3 = new eY();
         var3.b("X", (object)var1);
         var3.b("Y", (object)var2);
         this.qt.d("ValidSlotIndices").f(var3);
         this.qz[var2][var1] = true;
      }

   }

   public void j(int var1, int var2) {
      if (this.qz[var2][var1]) {
         if (this.qy[var2][var1] != null) {
            throw new Exception("Cannot disable slot in use");
         }

         eV var3 = this.qt.d("ValidSlotIndices");

         for(int var4 = 0; var4 < var3.Count; ++var4) {
            eY var5 = var3.V(var4);
            if (var1 == var5.J("X") && var2 == var5.J("Y")) {
               var3.ac(var4);
            }
         }

         this.qz[var2][var1] = false;
      }

   }

   public bool @do() {
      return this.qu;
   }

   public bool dp() {
      return this.qw;
   }

   public bool dq() {
      return this.qx;
   }

   public bool k(int var1, int var2) {
      eV var3 = this.qt.d("SpecialSlots");

      for(int var4 = 0; var4 < var3.Count; ++var4) {
         eY var5 = var3.V(var4);
         if (var5.J("Index.X") == var1 && var5.J("Index.Y") == var2) {
            return "TechBonus".Equals(var5.getValueAsString("Type.InventorySpecialSlotType"));
         }
      }

      return false;
   }

   public void a(int var1, int var2, bool var3) {
      eV var4 = this.qt.d("SpecialSlots");

      for(int var5 = 0; var5 < var4.Count; ++var5) {
         eY var6 = var4.V(var5);
         if (var6.J("Index.X") == var1 && var6.J("Index.Y") == var2) {
            if (!var3) {
               var4.ac(var5);
            } else {
               var6.b("Type.InventorySpecialSlotType", (object)"TechBonus");
            }

            return;
         }
      }

      if (var3) {
         eY var7 = gR.az("specialSlot");
         var7.b("Type.InventorySpecialSlotType", (object)"TechBonus");
         var7.b("Index.X", (object)var1);
         var7.b("Index.Y", (object)var2);
         var4.f(var7);
      }

   }

   public bool dr() {
      bool[][] var1 = new bool[this.height][this.width];
      eV var2 = this.qt.d("SpecialSlots");

      int var5;
      for(int var3 = 0; var3 < var2.Count; ++var3) {
         eY var4 = var2.V(var3);
         var5 = var4.J("Index.X");
         int var6 = var4.J("Index.Y");
         if (var5 < this.width && var6 < this.height) {
            var1[var6][var5] = "TechBonus".Equals(var4.getValueAsString("Type.InventorySpecialSlotType"));
         }
      }

      bool var7 = false;

      for(int var8 = 0; var8 < this.height; ++var8) {
         for(var5 = 0; var5 < this.width; ++var5) {
            if (!var1[var8][var5]) {
               eY var9 = gR.az("specialSlot");
               var9.b("Type.InventorySpecialSlotType", (object)"TechBonus");
               var9.b("Index.X", (object)var5);
               var9.b("Index.Y", (object)var8);
               var2.f(var9);
               var7 = true;
            }
         }
      }

      return var7;
   }

   public bool l(int var1, int var2) {
      eV var3 = this.qt.d("SpecialSlots");

      for(int var5 = 0; var5 < var3.Count; ++var5) {
         eY var6 = var3.V(var5);
         if ("Broken".Equals(var6.getValueAsString("Type.InventorySpecialSlotType")) && var1 == var6.J("Index.X") && var2 == var6.J("Index.Y")) {
            return true;
         }

         if ("BlockedByBrokenTech".Equals(var6.getValueAsString("Type.InventorySpecialSlotType")) && var1 == var6.J("Index.X") && var2 == var6.J("Index.Y")) {
            gu var4;
            if ((var4 = this.f(var1, var2)) != null && var4.dC() != 0.0D) {
               return true;
            }

            hc.info(this.getFullName() + " slot[" + var1 + "," + var2 + "] appears to be broken, ignoring");
            return false;
         }
      }

      return false;
   }

   public void m(int var1, int var2) {
      eV var3 = this.qt.d("SpecialSlots");

      for(int var5 = 0; var5 < var3.Count; ++var5) {
         eY var6 = var3.V(var5);
         if ("Broken".Equals(var6.getValueAsString("Type.InventorySpecialSlotType")) && var1 == var6.J("Index.X") && var2 == var6.J("Index.Y")) {
            var3.ac(var5);
         }

         if ("BlockedByBrokenTech".Equals(var6.getValueAsString("Type.InventorySpecialSlotType")) && var1 == var6.J("Index.X") && var2 == var6.J("Index.Y")) {
            gu var4;
            if ((var4 = this.f(var1, var2)) != null && var4.dC() != 0.0D) {
               this.g(var1, var2);
            }

            var3.ac(var5);
         }
      }

   }

   public bool ds() {
      bool var1 = false;
      eV var3 = this.qt.d("SpecialSlots");

      int var4;
      for(var4 = 0; var4 < var3.Count; ++var4) {
         eY var5 = var3.V(var4);
         if ("Broken".Equals(var5.getValueAsString("Type.InventorySpecialSlotType"))) {
            var3.ac(var4--);
            var1 = true;
         }

         if ("BlockedByBrokenTech".Equals(var5.getValueAsString("Type.InventorySpecialSlotType"))) {
            gu var2;
            if ((var2 = this.f(var5.J("Index.X"), var5.J("Index.Y"))) != null && var2.dC() != 0.0D) {
               this.g(var5.J("Index.X"), var5.J("Index.Y"));
            }

            var3.ac(var4--);
            var1 = true;
         }
      }

      for(var4 = 0; var4 < this.qy.Length; ++var4) {
         for(int var6 = 0; var6 < this.qy[var4].Length; ++var6) {
            if (this.qy[var4][var6] != null && this.qy[var4][var6].L("DamageFactor") != 0.0D) {
               this.qy[var4][var6].b("DamageFactor", (object)(new Double(0.0D)));
               this.qy[var4][var6].b("FullyInstalled", (object)(new Boolean(true)));
               var1 = true;
            }
         }
      }

      return var1;
   }

   public static string l(object var0) {
      StringBuilder var1 = new StringBuilder();
      var1.Append(' ');
      if (var0 is fg) {
         var1.Append(((fg)var0).bP());
      } else if (var0 != null) {
         var1.Append(var0.ToString());
      }

      if (var1.length() > 10) {
         var1.delete(10, var1.length());
      }

      while(var1.length() < 11) {
         var1.Append(' ');
      }

      return var1.ToString();
   }

   public void a(StreamWriter var1) {
      var1.print("\t|");

      int var2;
      for(var2 = 0; var2 < this.qy[0].Length; ++var2) {
         var1.print("-----------|");
      }

      var1.println();

      for(var2 = 0; var2 < this.qy.Length; ++var2) {
         var1.print("\t|");

         int var3;
         for(var3 = 0; var3 < this.qy[0].Length; ++var3) {
            if (!this.qz[var2][var3]) {
               var1.print("###########|");
            } else if (this.qy[var2][var3] != null) {
               var1.print(l(r_str(this.qy[var2][var3])) + "|");
            } else {
               var1.print("           |");
            }
         }

         var1.println();
         var1.print("\t|");

         for(var3 = 0; var3 < this.qy[var2].Length; ++var3) {
            if (!this.qz[var2][var3]) {
               var1.print("###########|");
            } else if (this.qy[var2][var3] != null) {
               var1.print(l(s(this.qy[var2][var3])) + "|");
            } else {
               var1.print("           |");
            }
         }

         var1.println();
         var1.print("\t|");

         for(var3 = 0; var3 < this.qy[0].Length; ++var3) {
            if (!this.qz[var2][var3]) {
               var1.print("###########|");
            } else if (this.qy[var2][var3] != null) {
               int var4 = t(this.qy[var2][var3]);
               if (var4 < 0) {
                  var1.print("           |");
               } else {
                  var1.print(l(Integer.toString(var4) + "/" + Integer.toString(u(this.qy[var2][var3]))) + "|");
               }
            } else {
               var1.print("           |");
            }
         }

         var1.println();
         var1.print("\t|");

         for(var3 = 0; var3 < this.qy[0].Length; ++var3) {
            var1.print("-----------|");
         }

         var1.println();
      }

   }

   public static string r_str(eY var0) {
      return var0.getValueAsString("Type.InventoryType");
   }

   public static object s(eY var0) {
      return var0.getValue("Id");
   }

   public static int t(eY var0) {
      return var0.J("Amount");
   }

   public static int u(eY var0) {
      return var0.J("MaxAmount");
   }

   public bool dt() {
      bool var2 = false;

      for(int var3 = 0; var3 < this.qy.Length; ++var3) {
         for(int var4 = 0; var4 < this.qy[var3].Length; ++var4) {
            int var1;
            if (this.qy[var3][var4] != null && "Technology".Equals(this.qy[var3][var4].getValueAsString("Type.InventoryType")) && this.qy[var3][var4].J("Amount") >= 0 && (var1 = this.qy[var3][var4].J("MaxAmount")) > 0) {
               this.qy[var3][var4].b("Amount", (object)(((int)(var1))));
               var2 = true;
            }
         }
      }

      return var2;
   }

   public bool du() {
      bool var2 = false;

      for(int var3 = 0; var3 < this.qy.Length; ++var3) {
         for(int var4 = 0; var4 < this.qy[var3].Length; ++var4) {
            int var1;
            if (this.qy[var3][var4] != null && !"Technology".Equals(this.qy[var3][var4].getValueAsString("Type.InventoryType")) && (var1 = this.qy[var3][var4].J("MaxAmount")) > 1) {
               this.qy[var3][var4].b("Amount", (object)(((int)(var1))));
               var2 = true;
            }
         }
      }

      return var2;
   }

   public bool dv() {
      bool var1 = false;

      for(int var2 = 0; var2 < this.height; ++var2) {
         for(int var3 = 0; var3 < this.width; ++var3) {
            if (!this.qz[var2][var3]) {
               eY var4 = new eY();
               var4.b("X", (object)var3);
               var4.b("Y", (object)var2);
               this.qt.d("ValidSlotIndices").f(var4);
               this.qz[var2][var3] = true;
               var1 = true;
            }
         }
      }

      return var1;
   }

   public string toString() {
      return this.getFullName();
   }
   public static int[] dw() {
      int[] var10000 = qA;
      if (var10000 != null) {
         return var10000;
      } else {
         int[] var0 = new int[ex.Values.Length];

         try {
            var0[ex.jq.ordinal()] = 32;
         } catch (NoSuchFieldError var43) {
         }

         try {
            var0[ex.js.ordinal()] = 34;
         } catch (NoSuchFieldError var42) {
         }

         try {
            var0[ex.jv.ordinal()] = 37;
         } catch (NoSuchFieldError var41) {
         }

         try {
            var0[ex.jt.ordinal()] = 35;
         } catch (NoSuchFieldError var40) {
         }

         try {
            var0[ex.jo.ordinal()] = 30;
         } catch (NoSuchFieldError var39) {
         }

         try {
            var0[ex.iX.ordinal()] = 13;
         } catch (NoSuchFieldError var38) {
         }

         try {
            var0[ex.iN.ordinal()] = 3;
         } catch (NoSuchFieldError var37) {
         }

         try {
            var0[ex.iT.ordinal()] = 9;
         } catch (NoSuchFieldError var36) {
         }

         try {
            var0[ex.iU.ordinal()] = 10;
         } catch (NoSuchFieldError var35) {
         }

         try {
            var0[ex.jA.ordinal()] = 42;
         } catch (NoSuchFieldError var34) {
         }

         try {
            var0[ex.iW.ordinal()] = 12;
         } catch (NoSuchFieldError var33) {
         }

         try {
            var0[ex.iZ.ordinal()] = 15;
         } catch (NoSuchFieldError var32) {
         }

         try {
            var0[ex.iQ.ordinal()] = 6;
         } catch (NoSuchFieldError var31) {
         }

         try {
            var0[ex.ja.ordinal()] = 16;
         } catch (NoSuchFieldError var30) {
         }

         try {
            var0[ex.iR.ordinal()] = 7;
         } catch (NoSuchFieldError var29) {
         }

         try {
            var0[ex.jc.ordinal()] = 18;
         } catch (NoSuchFieldError var28) {
         }

         try {
            var0[ex.iP.ordinal()] = 5;
         } catch (NoSuchFieldError var27) {
         }

         try {
            var0[ex.jk.ordinal()] = 26;
         } catch (NoSuchFieldError var26) {
         }

         try {
            var0[ex.iL.ordinal()] = 1;
         } catch (NoSuchFieldError var25) {
         }

         try {
            var0[ex.jz.ordinal()] = 41;
         } catch (NoSuchFieldError var24) {
         }

         try {
            var0[ex.jx.ordinal()] = 39;
         } catch (NoSuchFieldError var23) {
         }

         try {
            var0[ex.iM.ordinal()] = 2;
         } catch (NoSuchFieldError var22) {
         }

         try {
            var0[ex.jj.ordinal()] = 25;
         } catch (NoSuchFieldError var21) {
         }

         try {
            var0[ex.jb.ordinal()] = 17;
         } catch (NoSuchFieldError var20) {
         }

         try {
            var0[ex.jr.ordinal()] = 33;
         } catch (NoSuchFieldError var19) {
         }

         try {
            var0[ex.jw.ordinal()] = 38;
         } catch (NoSuchFieldError var18) {
         }

         try {
            var0[ex.jp.ordinal()] = 31;
         } catch (NoSuchFieldError var17) {
         }

         try {
            var0[ex.jB.ordinal()] = 43;
         } catch (NoSuchFieldError var16) {
         }

         try {
            var0[ex.jl.ordinal()] = 27;
         } catch (NoSuchFieldError var15) {
         }

         try {
            var0[ex.jy.ordinal()] = 40;
         } catch (NoSuchFieldError var14) {
         }

         try {
            var0[ex.iY.ordinal()] = 14;
         } catch (NoSuchFieldError var13) {
         }

         try {
            var0[ex.ji.ordinal()] = 24;
         } catch (NoSuchFieldError var12) {
         }

         try {
            var0[ex.jn.ordinal()] = 29;
         } catch (NoSuchFieldError var11) {
         }

         try {
            var0[ex.jg.ordinal()] = 22;
         } catch (NoSuchFieldError var10) {
         }

         try {
            var0[ex.ju.ordinal()] = 36;
         } catch (NoSuchFieldError var9) {
         }

         try {
            var0[ex.je.ordinal()] = 20;
         } catch (NoSuchFieldError var8) {
         }

         try {
            var0[ex.iS.ordinal()] = 8;
         } catch (NoSuchFieldError var7) {
         }

         try {
            var0[ex.iO.ordinal()] = 4;
         } catch (NoSuchFieldError var6) {
         }

         try {
            var0[ex.jh.ordinal()] = 23;
         } catch (NoSuchFieldError var5) {
         }

         try {
            var0[ex.jd.ordinal()] = 19;
         } catch (NoSuchFieldError var4) {
         }

         try {
            var0[ex.iV.ordinal()] = 11;
         } catch (NoSuchFieldError var3) {
         }

         try {
            var0[ex.jm.ordinal()] = 28;
         } catch (NoSuchFieldError var2) {
         }

         try {
            var0[ex.jf.ordinal()] = 21;
         } catch (NoSuchFieldError var1) {
         }

         qA = var0;
         return var0;
      }
   }
   public static int[] dx() {
      int[] var10000 = qB;
      if (var10000 != null) {
         return var10000;
      } else {
         int[] var0 = new int[fn.Values.Length];

         try {
            var0[fn.lp.ordinal()] = 4;
         } catch (NoSuchFieldError var8) {
         }

         try {
            var0[fn.lo.ordinal()] = 3;
         } catch (NoSuchFieldError var7) {
         }

         try {
            var0[fn.lt.ordinal()] = 8;
         } catch (NoSuchFieldError var6) {
         }

         try {
            var0[fn.lr.ordinal()] = 6;
         } catch (NoSuchFieldError var5) {
         }

         try {
            var0[fn.lm.ordinal()] = 1;
         } catch (NoSuchFieldError var4) {
         }

         try {
            var0[fn.lq.ordinal()] = 5;
         } catch (NoSuchFieldError var3) {
         }

         try {
            var0[fn.ls.ordinal()] = 7;
         } catch (NoSuchFieldError var2) {
         }

         try {
            var0[fn.ln.ordinal()] = 2;
         } catch (NoSuchFieldError var1) {
         }

         qB = var0;
         return var0;
      }
   }
   public static int[] dy() {
      int[] var10000 = qC;
      if (var10000 != null) {
         return var10000;
      } else {
         int[] var0 = new int[eB.Values.Length];

         try {
            var0[eB.jO.ordinal()] = 2;
         } catch (NoSuchFieldError var4) {
         }

         try {
            var0[eB.jP.ordinal()] = 3;
         } catch (NoSuchFieldError var3) {
         }

         try {
            var0[eB.jQ.ordinal()] = 4;
         } catch (NoSuchFieldError var2) {
         }

         try {
            var0[eB.jN.ordinal()] = 1;
         } catch (NoSuchFieldError var1) {
         }

         qC = var0;
         return var0;
      }
   }
}

}
