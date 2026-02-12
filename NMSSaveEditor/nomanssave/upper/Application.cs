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

public class Application {
   public static string VERSION = "1.19.14";
   public static string RELEASE = "BREACH";
   private static string J = "https://github.com/goatfungus/NMSSaveEditor";
   private static string K = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_";
   private static Application L;
   private static Dictionary<object, object> M = new Dictionary<object, object>();
   private Form N;
   private TabControl O;
   private Label P;
   private Label Q;
   private ComboBox R;
   private ComboBox S;
   private Label T;
   private Label U;
   private Label V;
   private Button W;
   private Button X;
   private Button Y;
   private ToolStripMenuItem Z;
   private ToolStripMenuItem aa;
   private ToolStripMenuItem ab;
   private List<object> ac;
   private ToolStripMenuItem ad;
   private static int ae = 0;
   private static int af = 1;
   private static int ag = 2;
   private static int ah = 3;
   private static int ai = 4;
   private static int aj = 5;
   private static int ak = 6;
   private static int al = 7;
   private static int am = 8;
   private static int an = 9;
   private static int ao = 10;
   private static int ap = 11;
   private static int aq = 12;
   private static int ar = 13;
   private aJ as;
   private dj at;
   private dN au;
   private eb av;
   private bd aw;
   private bl ax;
   private ep ay;
   private X az;
   private I aA;
   private dE aB;
   private ap aC;
   private bE aD;
   private c aE;
   private fq aF;
   private ft[] aG;
   private int aH;
   private fs[] aI;
   private int aJ;
   private eY aK;
   private bool aL;
   private fr aM;
   private eY aN;
   private bool aO;
   private bool aP;
   private bool aQ;
   private bool aR;
   private bool aS;
   private bool aT;
   private bool aU;
   private fe aV;
   private fe aW;
   private fR aX;
   // $FF: synthetic field
   private static int[] aY;

   public static string a(long var0) {
      SimpleDateFormat var2 = new SimpleDateFormat("h:mm a, E MMM d, yyyy");
      return var2.format(new Date(var0));
   }

   public static string b(long var0) {
      SimpleDateFormat var2 = new SimpleDateFormat("MMM d, HH:mm");
      return var2.format(new Date(var0));
   }

   private static string a(string var0, string var1) {
      if (var0 == null) {
         return var1;
      } else {
         StringBuffer var2 = new StringBuffer();

         for(int var4 = 0; var4 < var0.Length; ++var4) {
            char var3 = var0[var4);
            if ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_".IndexOf(var3) >= 0) {
               var2.append(var3);
            } else if (Character.isWhitespace(var3)) {
               var2.append('_');
            }
         }

         return var2.Length != 0 ? var2.ToString() : var1;
      }
   }

   public static Application e() {
      return L;
   }

   public static void main(string[] var0) {
      byte var1 = 0;
      bool var2;
      if (var0.length > var1 && var0[var1].Equals("-autoupdate")) {
         int var3 = var1 + 1;
         var2 = true;
      } else {
         var2 = false;
      }

      nomanssave.aH.init(!var2);
      hc.info("Starting Editor...");
      (new System.Threading.Thread(() => {
         cK.aA();
      })).start();
      JavaCompat.InvokeLater(new w(var2));
   }

   public static Image a(string var0) {
      Image var1 = (Image)M.Get(var0);
      if (var1 == null) {
         Stream var2 = JavaCompat.GetResourceStream("icons/" + var0);
         if (var2 != null) {
            try {
               var1 = JavaCompat.ImageRead(var2);
               M.Put(var0, var1);
            } catch (IOException var4) {
               hc.info("Error loading icon: " + var0);
            } catch (Exception var5) {
               hc.info("Error loading icon: " + var0);
            }
         }
      }

      return var1 == null ? null : new Image(var1);
   }

   public static Image a(string var0, int var1, int var2) {
      Image var3 = (Image)M.Get(var0);
      if (var3 == null) {
         Stream var4 = JavaCompat.GetResourceStream("icons/" + var0);
         if (var4 != null) {
            try {
               var3 = JavaCompat.ImageRead(var4);
               M.Put(var0, var3);
            } catch (IOException var6) {
               hc.info("Error loading icon: " + var0);
            } catch (Exception var7) {
               hc.info("Error loading icon: " + var0);
            }
         }
      }

      return var3 == null ? null : new Image(var3.getScaledInstance(var1, var2, 4));
   }

   private void f() {
      if (this.aR) {
         this.aR = false;
      }

      int var1;
      if (this.aS) {
         this.aS = false;
         var1 = this.aH < 0 ? -1 : this.aG[this.aH].getIndex();
         this.aG = (ft[])new List<object> {this.aF.bU()).filter((var1x) => {
            return var1x.getIndex() == var1 || !var1x.Length == 0;
         }).ToArray((var0) => {
            return new ft[var0];
         });
         this.aH = -1;

         for(int var2 = 0; var2 < this.aG.length; ++var2) {
            if (this.aG[var2].getIndex() == var1) {
               this.aH = var2;
               break;
            }
         }

         if (var1 >= 0 && this.aH < 0) {
            hc.warn("Slot " + (var1 + 1) + " was not reloaded!");
            this.aI = new fs[0];
            this.aJ = -1;
         }

         this.R.updateUI();
      }

      this.aT &= this.aH >= 0;
      if (this.aT) {
         this.aT = false;
         string var7 = this.aJ < 0 ? null : this.aI[this.aJ].K();
         long var8 = this.aJ < 0 ? Long.MIN_VALUE : this.aI[this.aJ].LastWriteTimeUtc.Ticks;
         fn var4 = this.aJ < 0 ? null : this.aI[this.aJ].L();
         this.aI = this.aG[this.aH].bX();
         this.aJ = -1;

         int var5;
         for(var5 = 0; var5 < this.aI.length; ++var5) {
            if (this.aI[var5].K().Equals(var7)) {
               this.aJ = var5;
               break;
            }
         }

         if (var7 != null && this.aJ < 0) {
            this.aU = false;
            var5 = MessageBox.Show("Save file has been deleted externally. Would you like to reload?\nNOTE: All changes made in the editor will be lost.".ToString(), "Reload File".ToString(), MessageBoxButtons.YesNo);
            if (var5 == 0) {
               this.aJ = 0;
               this.l();
            } else {
               fs[] var6 = new fs[this.aI.length + 1];
               var6[0] = new F(this, var7, var8, var4, this.aK);
               Array.Copy(this.aI, 0, var6, 1, this.aI.length);
               this.aI = var6;
               this.aJ = 0;
            }
         }

         this.S.updateUI();
      }

      this.aU &= this.aJ >= 0;
      if (this.aU) {
         this.aU = false;
         var1 = MessageBox.Show("Save file has been modified externally. Would you like to reload?\nNOTE: All changes made in the editor will be lost.".ToString(), "Reload File".ToString(), MessageBoxButtons.YesNo);
         if (var1 == 0) {
            this.l();
         } else {
            this.aL = true;
         }
      }

   }

   private Application(bool var1) {
      this.aQ = false;
      this.aR = false;
      this.aS = false;
      this.aT = false;
      this.aU = false;
      this.aV = (var1x, var2x, var3x) => {
         this.aO = true;
         this.aP = true;
         if (var3x == null) {
            hc.info("Removing " + var1x);
         } else {
            string var4;
            if (var3x is eY) {
               var4 = "[OBJECT]";
            } else if (var3x is eV) {
               var4 = "[ARRAY]";
            } else {
               var4 = var3x.ToString();
            }

            hc.info("Setting " + var1x + ": " + var4);
         }
      };
      this.aW = (var1x, var2x, var3x) => {
         this.aL = true;
         if (var1x.StartsWith("PlayerStateData.Multitools")) {
            int var4 = this.aK.J("PlayerStateData.ActiveMultioolIndex");
            if (var1x.StartsWith("PlayerStateData.Multitools[" + var4 + "].Store.")) {
               eY var5 = this.aK.H("PlayerStateData.Multitools[" + var4 + "].Store");
               this.aK.b("PlayerStateData.WeaponInventory", (Object)var5.bE());
            } else if (var1x.Equals("PlayerStateData.Multitools[" + var4 + "].Seed[1]")) {
               this.aK.b("PlayerStateData.CurrentWeapon.GenerationSeed[1]", var3x);
            } else if (var1x.Equals("PlayerStateData.Multitools[" + var4 + "].Resource.Filename")) {
               this.aK.b("PlayerStateData.CurrentWeapon.Filename", var3x);
            }
         }

         if (var3x == null) {
            hc.info("Removing " + var1x);
         } else {
            string var6;
            if (var3x is eY) {
               var6 = "OBJECT";
            } else if (var3x is eV) {
               var6 = "ARRAY[" + ((eV)var3x).Count + "]";
            } else {
               var6 = fh.b(var3x, false);
            }

            hc.info("Setting " + var1x + ": " + var6);
         }
      };
      this.aX = new u(this);
      string var2 = nomanssave.aH.getProperty("GameStorage");
      string var3 = nomanssave.aH.getProperty("GameSaveDir");
      this.aF = var3 == null ? null : fq.a(var2, new File(var3), this.aX);
      if (this.aF == null) {
         this.aG = new ft[0];
         this.aH = -1;
         this.aI = new fs[0];
         this.aJ = -1;
         this.aM = null;
         this.aN = null;
      } else {
         this.aG = this.aF.bV();
         this.aH = -1;
         this.aI = new fs[0];
         this.aJ = -1;
         if (var2 == null) {
            var2 = fq.c(this.aF);
            nomanssave.aH.setProperty("GameStorage", var2);
         }

         hc.debug("Storage: " + var2);
         hc.debug("Save Path: " + this.aF.bS().FullName);
         this.aM = null;
         this.aN = null;

         try {
            hc.info("Reading account data...");
            this.aM = this.aF.bT();
            this.aN = this.aM == null ? null : this.aM.M();
            if (this.aN != null) {
               this.aN.a(this.aV);
            }
         } catch (IOException var5) {
            hc.a("Error reading account data", var5);
         }
      }

      this.initialize();
      (new x(this, var1)).start();
   }

   public Form g() {
      return this.N;
   }

   public void a(gH var1) {
      FileInfo var2 = nomanssave.aH.cF;
      if (!var2.Exists && !var2.Create()) {
         var2 = nomanssave.aH.cD;
      }

      cT var3 = cT.aC();
      string var4 = a(var1.Name, "Ship");
      var3.setCurrentDirectory(var2);
      var3.setSelectedFile(new File(var2, var4));
      if (var3.showSaveDialog(this.N) == 0) {
         try {
            FileInfo var5 = var3.getSelectedFile();
            if (!var5.Name.EndsWith(".sh0")) {
               var5 = new File(var5.Directory, var5.Name + ".sh0");
            }

            var1.a(var5, var3.aw());
         } catch (Exception var6) {
            hc.a("Ship export error", var6);
            this.c("An error occured during export.");
         } catch (IOException var7) {
            hc.a("Ship export error", var7);
            this.c("An error occured during export.");
         }
      }

   }

   public void a(gv var1) {
      FileInfo var2 = nomanssave.aH.cF;
      if (!var2.Exists && !var2.Create()) {
         var2 = nomanssave.aH.cD;
      }

      cv var3 = cv.ax();
      string var4 = a(var1.Name, "Weapon");
      var3.setCurrentDirectory(var2);
      var3.setSelectedFile(new File(var2, var4));
      if (var3.showSaveDialog(this.N) == 0) {
         try {
            FileInfo var5 = var3.getSelectedFile();
            if (!var5.Name.EndsWith(".wp0")) {
               var5 = new File(var5.Directory, var5.Name + ".wp0");
            }

            var1.j(var5);
         } catch (Exception var6) {
            hc.a("Weapon export error", var6);
            this.c("An error occured during export.");
         } catch (IOException var7) {
            hc.a("Weapon export error", var7);
            this.c("An error occured during export.");
         }
      }

   }

   public void a(gj var1) {
      FileInfo var2 = nomanssave.aH.cF;
      if (!var2.Exists && !var2.Create()) {
         var2 = nomanssave.aH.cD;
      }

      string var3 = "." + var1.cL().ToString().ToLower();
      cp var4 = cp.at();
      string var5 = a(var1.Name, var1.cL().ToString());
      var4.setCurrentDirectory(var2);
      var4.setSelectedFile(new File(var2, var5));
      if (var4.showSaveDialog(this.N) == 0) {
         try {
            FileInfo var6 = var4.getSelectedFile();
            if (!var6.Name.EndsWith(var3)) {
               var6 = new File(var6.Directory, var6.Name + var3);
            }

            var1.j(var6);
         } catch (Exception var7) {
            hc.a("Companion export error", var7);
            this.c("An error occured during export.");
         } catch (IOException var8) {
            hc.a("Companion export error", var8);
            this.c("An error occured during export.");
         }
      }

   }

   public gH h() {
      eY var1;
      if (this.aK != null && (var1 = this.aK.H("PlayerStateData")) != null) {
         FileInfo var2 = nomanssave.aH.cF.Exists ? nomanssave.aH.cF : nomanssave.aH.cD;
         cT var3 = cT.aC();
         var3.setCurrentDirectory(var2);
         if (var3.showOpenDialog(this.N) == 0) {
            try {
               FileInfo var4 = var3.getSelectedFile();
               gH var5 = gH.c(var1, var4);
               this.aL = true;
               return var5;
            } catch (Exception var6) {
               hc.a("Ship import error", var6);
               this.c("An error occured during import.");
            } catch (IOException var7) {
               hc.a("Ship import error", var7);
               this.c("An error occured during import.");
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public gv i() {
      eY var1;
      if (this.aK != null && (var1 = this.aK.H("PlayerStateData")) != null) {
         FileInfo var2 = nomanssave.aH.cF.Exists ? nomanssave.aH.cF : nomanssave.aH.cD;
         cv var3 = cv.ax();
         var3.setCurrentDirectory(var2);
         if (var3.showOpenDialog(this.N) == 0) {
            try {
               FileInfo var4 = var3.getSelectedFile();
               gv var5 = gv.b(var1, var4);
               this.aL = true;
               return var5;
            } catch (Exception var6) {
               hc.a("Weapon import error", var6);
               this.c("An error occured during import.");
            } catch (IOException var7) {
               hc.a("Weapon import error", var7);
               this.c("An error occured during import.");
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public gj j() {
      eY var1;
      if (this.aK != null && (var1 = this.aK.H("PlayerStateData")) != null) {
         FileInfo var2 = nomanssave.aH.cF.Exists ? nomanssave.aH.cF : nomanssave.aH.cD;
         cp var3 = cp.at();
         var3.setCurrentDirectory(var2);
         if (var3.showOpenDialog(this.N) == 0) {
            try {
               FileInfo var4 = var3.getSelectedFile();
               gj var5 = gj.a(var1, var4);
               this.aL = true;
               return var5;
            } catch (Exception var6) {
               hc.a("Companion import error", var6);
               this.c("An error occured during import.");
            } catch (IOException var7) {
               hc.a("Companion import error", var7);
               this.c("An error occured during import.");
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public void a(gf var1) {
      FileInfo var2 = nomanssave.aH.cE;
      if (!var2.Exists && !var2.Create()) {
         var2 = nomanssave.aH.cD;
      }

      cl var3 = cl.ar();
      string var4 = a(var1.Name, "Base");
      var3.setCurrentDirectory(var2);
      var3.setSelectedFile(new File(var2, var4));
      if (var3.showSaveDialog(this.N) == 0) {
         try {
            FileInfo var5 = var3.getSelectedFile();
            if (!var5.Name.EndsWith(".pb3")) {
               var5 = new File(var5.Directory, var5.Name + ".pb3");
            }

            if (var5.Exists && MessageBox.Show("Are you sure you want to overwrite this existing backup file?".ToString(), "Confirm".ToString(), MessageBoxButtons.YesNo) != 0) {
               return;
            }

            gS.d(var1.cH(), var5);
         } catch (Exception var6) {
            hc.a("Base backup error", var6);
            this.c("An error occured during backup.");
         } catch (IOException var7) {
            hc.a("Base backup error", var7);
            this.c("An error occured during backup.");
         } catch (Exception var8) {
            hc.a("Base backup error", var8);
            this.c("An error occured during backup.");
         }
      }

   }

   public bool b(gf var1) {
      FileInfo var2 = nomanssave.aH.cE.Exists ? nomanssave.aH.cE : nomanssave.aH.cD;
      cl var3 = cl.ar();
      var3.setCurrentDirectory(var2);
      if (var3.showOpenDialog(this.N) == 0) {
         try {
            if (MessageBox.Show("Are you sure you want to overwrite your existing base?".ToString(), "Confirm".ToString(), MessageBoxButtons.YesNo) != 0) {
               return false;
            }

            FileInfo var4 = var3.getSelectedFile();
            gS.e(var1.cH(), var4);
            this.aL = true;
            return true;
         } catch (IOException var5) {
            hc.a("Base restore error", var5);
            this.c("An error occured during backup.");
         } catch (Exception var6) {
            hc.a("Base restore error", var6);
            this.c("An error occured during backup.");
         }
      }

      return false;
   }

   public void a(gm var1) {
      gn var2 = var1.cZ();
      if (var2 != null) {
         FileInfo var3 = nomanssave.aH.cE;
         if (!var3.Exists && !var3.Create()) {
            var3 = nomanssave.aH.cD;
         }

         cs var4 = cs.av();
         string var5 = a(var2.Name, "Freighter");
         var4.setCurrentDirectory(var3);
         var4.setSelectedFile(new File(var3, var5));
         if (var4.showSaveDialog(this.N) == 0) {
            try {
               FileInfo var6 = var4.getSelectedFile();
               if (!var6.Name.EndsWith(".fb3")) {
                  var6 = new File(var6.Directory, var6.Name + ".fb3");
               }

               if (var6.Exists && MessageBox.Show("Are you sure you want to overwrite this existing backup file?".ToString(), "Confirm".ToString(), MessageBoxButtons.YesNo) != 0) {
                  return;
               }

               Dictionary<object, object> var7 = new Dictionary<object, object>();
               var7.Put("HomeSeed", var1.cU());
               var7.Put("ResourceSeed", var1.cV());
               var7.Put("Name", var1.Name);
               var7.Put("TypeClass", var1.cW());
               var7.Put("Resource", var1.cT());
               var7.Put("FleetCoordination", var1.cY());
               var7.Put("Hyperdrive", var1.cX());
               eY var8 = this.aK.H("PlayerStateData");
               eY var9 = var8.H("FreighterInventory").bE();
               eY var10 = var8.H("FreighterInventory_TechOnly").bE();
               eY var11 = var8.H("FreighterInventory_Cargo").bE();
               if (!var4.aw()) {
                  eV var12 = var9.d("Slots");

                  int var13;
                  eY var14;
                  for(var13 = 0; var13 < var12.Count; ++var13) {
                     var14 = var12.V(var13);
                     if (!var14.getValueAsString("Type.InventoryType").Equals("Technology")) {
                        var12.ac(var13--);
                     }
                  }

                  var12 = var11.d("Slots");

                  for(var13 = 0; var13 < var12.Count; ++var13) {
                     var14 = var12.V(var13);
                     if (!var14.getValueAsString("Type.InventoryType").Equals("Technology")) {
                        var12.ac(var13--);
                     }
                  }
               }

               var7.Put("Inventory", var9);
               var7.Put("InventoryTech", var10);
               var7.Put("InventoryCargo", var11);
               gS.a(var2.cH(), (Map)var7, (File)var6);
            } catch (Exception var15) {
               hc.a("Freighter backup error", var15);
               this.c("An error occured during backup.");
            } catch (IOException var16) {
               hc.a("Freighter backup error", var16);
               this.c("An error occured during backup.");
            } catch (Exception var17) {
               hc.a("Freighter backup error", var17);
               this.c("An error occured during backup.");
            }
         }

      }
   }

   public bool b(gm var1) {
      gn var2 = var1.cZ();
      if (var2 == null) {
         return false;
      } else {
         FileInfo var3 = nomanssave.aH.cE.Exists ? nomanssave.aH.cE : nomanssave.aH.cD;
         cs var4 = cs.av();
         var4.setCurrentDirectory(var3);
         if (var4.showOpenDialog(this.N) == 0) {
            try {
               if (MessageBox.Show("Are you sure you want to overwrite your existing freighter?".ToString(), "Confirm".ToString(), MessageBoxButtons.YesNo) != 0) {
                  return false;
               }

               FileInfo var5 = var4.getSelectedFile();
               Dictionary<object, object> var6 = new Dictionary<object, object>();
               gS.b(var2.cH(), var6, var5);
               eY var7 = this.aK.H("PlayerStateData");
               bool var8 = false;
               IEnumerator<object> var10 = var6.Entries().GetEnumerator();

               while(var10.MoveNext()) {
                  Entry var9 = (Entry)var10.Current;
                  if (((string)var9.getKey()).Equals("HomeSeed")) {
                     var1.ah((string)var9.getValue());
                  }

                  if (((string)var9.getKey()).Equals("ResourceSeed")) {
                     var1.ai((string)var9.getValue());
                  }

                  if (((string)var9.getKey()).Equals("Name")) {
                     var1.setName((string)var9.getValue());
                  }

                  if (((string)var9.getKey()).Equals("TypeClass")) {
                     var1.aj((string)var9.getValue());
                  }

                  if (((string)var9.getKey()).Equals("Type")) {
                     var1.ag(go.valueOf((string)var9.getValue()).K());
                  }

                  if (((string)var9.getKey()).Equals("Resource")) {
                     var1.ag((string)var9.getValue());
                  }

                  if (((string)var9.getKey()).Equals("FleetCoordination")) {
                     var1.b(((Number)var9.getValue()).doubleValue());
                  }

                  if (((string)var9.getKey()).Equals("Hyperdrive")) {
                     var1.a(((Number)var9.getValue()).doubleValue());
                  }

                  if (((string)var9.getKey()).Equals("Inventory")) {
                     var7.b("FreighterInventory", (Object)((eY)var9.getValue()));
                     var8 = true;
                  }

                  if (((string)var9.getKey()).Equals("InventoryTech")) {
                     var7.b("FreighterInventory_TechOnly", (Object)((eY)var9.getValue()));
                     var8 = true;
                  }

                  if (((string)var9.getKey()).Equals("InventoryCargo")) {
                     var7.b("FreighterInventory_Cargo", (Object)((eY)var9.getValue()));
                     var8 = true;
                  }
               }

               if (var8) {
                  var1 = gm.p(var7);
               }

               this.aw.c(var1);
               this.aL = true;
               return true;
            } catch (IOException var11) {
               hc.a("Freighter restore error", var11);
               this.c("An error occured during restore.");
            } catch (Exception var12) {
               hc.a("Freighter restore error", var12);
               this.c("An error occured during restore.");
            }
         }

         return false;
      }
   }

   private void k() {
      FileInfo var1 = ej.b(this.aF == null ? null : this.aF.bS());
      if (var1 != null) {
         string var2;
         FileInfo var3;
         if (var1.IsDirectory()) {
            var3 = var1;
            var2 = null;
         } else {
            var3 = var1.Directory;
            var2 = var1.Name;
         }

         if (this.aF != null) {
            if (this.aF.bS().IsDirectory() && !this.aF.bS().Equals(var3)) {
               this.aF = null;
            } else if (this.aF.bS().IsFile() && !this.aF.bS().Equals(var1)) {
               this.aF = null;
            }
         }

         if (this.aF == null) {
            hc.info("Loading storage: " + var3.FullName);
            this.aF = fq.a(var3, this.aX);
         }

         if (this.aF == null) {
            this.aG = new ft[0];
            this.aH = -1;
            this.aI = new fs[0];
            this.aJ = -1;
            this.aK = null;
            this.aM = null;
            this.aN = null;
            this.ad.SetEnabled(false);
            this.O.SetEnabledAt(13, false);
            this.aE.a((eY)null);
            this.aO = false;
            this.P.SetText("(none)");
            this.Q.SetText("(none)");
         } else {
            string var4 = fq.c(this.aF);
            nomanssave.aH.setProperty("GameStorage", var4);
            nomanssave.aH.setProperty("GameSaveDir", this.aF.bS().FullName);
            hc.debug("Storage: " + var4);
            hc.debug("Save Path: " + this.aF.bS().FullName);
            this.aG = this.aF.bV();
            this.aH = -1;
            this.aI = new fs[0];
            this.aJ = -1;
            if (var2 != null) {
               label85:
               for(int var5 = 0; var5 < this.aG.length; ++var5) {
                  if (this.aF.W(var2) == this.aG[var5].getIndex()) {
                     this.aH = var5;
                     this.aI = this.aG[var5].bX();
                     int var6 = 0;

                     while(true) {
                        if (var6 >= this.aI.length) {
                           break label85;
                        }

                        if (var2.Equals(this.aI[var6].K())) {
                           this.aJ = var6;
                           break label85;
                        }

                        ++var6;
                     }
                  }
               }
            }

            this.aM = null;
            this.aN = null;

            try {
               this.aM = this.aF.bT();
               this.aN = this.aM == null ? null : this.aM.M();
               if (this.aN != null) {
                  this.aN.a(this.aV);
               }
            } catch (IOException var7) {
               hc.a("Error reading account data", var7);
            }

            this.ad.SetEnabled(this.aN != null);
            this.O.SetEnabledAt(13, this.aN != null);
            this.aE.a(this.aN);
            this.aO = false;
            this.P.SetText(var4);
            this.Q.SetText(this.aF.bS().FullName);
         }

         this.R.SetEnabled(true);
         this.S.SetEnabled(true);
         if (this.aJ > 0) {
            this.c("The save file you have selected is not the most recent.");
         }

         this.l();
      }

   }

   private void e(int var1) {
      this.aH = var1;
      if (this.aH < 0) {
         this.aI = new fs[0];
         this.aJ = -1;
      } else {
         this.aI = this.aG[this.aH].bX();
         this.aJ = this.aI.length > 0 ? 0 : -1;
      }

      this.l();
   }

   private void f(int var1) {
      this.aJ = var1;
      this.l();
   }

   public void b(string var1) {
      JavaCompat.InvokeLater(new z(this, var1));
   }

   public void c(string var1) {
      JavaCompat.InvokeLater(new A(this, var1));
   }

   private void l() {
      this.R.updateUI();
      this.S.updateUI();
      this.aL = false;
      this.aK = null;
      if (this.aJ < 0) {
         this.T.SetText("(no file selected)");
         this.U.SetText("(no file selected)");
         this.V.SetText("(no file selected)");
         if (this.aH >= 0) {
            hc.info("No current save file found for " + this.aG[this.aH]);
            this.b("Save file not found for " + this.aG[this.aH]);
         }
      } else {
         try {
            this.T.SetText(a(this.aI[this.aJ].LastWriteTimeUtc.Ticks));
            this.U.SetText(e(this.aI[this.aJ].Name));
            this.V.SetText(e(this.aI[this.aJ].getDescription()));
            hc.info("Reading save file...");
            hc.info("  Slot: " + this.aG[this.aH]);
            hc.info("  Filename: " + this.aI[this.aJ].K());

            try {
               this.aK = this.aI[this.aJ].M();
               this.aK.a(this.aW);
            } catch (eX var18) {
               hc.info("  Error parsing JSON: " + var18.getMessage());
            }

            hc.info("Finished.");
            this.aL = this.aI[this.aJ] is F;
         } catch (IOException var19) {
            hc.error("Could not load save file: " + this.aI[this.aJ].K(), var19);
            this.aK = null;
         }
      }

      this.O.SetSelectedIndex(0);
      eY var1;
      if (this.aK != null && (var1 = this.aK.H("PlayerStateData")) != null) {
         bool var20 = this.aF.bW();
         gz var21 = gz.w(var1);
         gv[] var4 = gv.v(var1);
         gB var5 = gB.x(var1);
         gH[] var6 = gH.C(var1);
         gC var7 = gC.y(var1);
         gM[] var8 = gM.D(var1);
         gm var9 = gm.p(var1);
         gp[] var10 = gp.q(var1);
         gO[] var11 = gO.E(var1);
         gE[] var12 = gE.z(var1);
         bool var13 = gj.n(var1);
         gj[] var14 = gj.o(var1);
         ge var15 = ge.m(var1);
         this.O.SetEnabledAt(1, var21 != null);
         this.as.a(var21);
         this.O.SetEnabledAt(2, var4.length > 0);
         this.at.a(var4, var5);
         this.O.SetEnabledAt(3, var6.length > 0);
         this.au.a(var6, var7);
         this.O.SetEnabledAt(4, var8.length > 0);
         this.av.a(var8);
         this.O.SetEnabledAt(5, var9 != null);
         this.aw.c(var9);
         this.O.SetEnabledAt(6, var9 != null);
         this.ax.a(var10);
         this.O.SetEnabledAt(7, var11.length > 0);
         this.ay.a(var11);
         this.O.SetEnabledAt(8, var13);
         this.az.a(var14);
         this.O.SetEnabledAt(9, var15 != null);
         this.aA.a(var15);
         this.O.SetEnabledAt(10, var12.length > 0);
         this.aB.a(var12);
         this.O.SetEnabledAt(11, var21 != null);
         this.aC.a(var21);
         this.O.SetEnabledAt(12, var21 != null);
         this.aD.a(var21);
         this.W.SetEnabled(!(this.aI[this.aJ] is F));
         this.X.SetEnabled(true);
         this.Y.SetEnabled(var20);
         this.Z.SetEnabled(true);
         this.aa.SetEnabled(true);
         this.ab.SetEnabled(var20);
         IEnumerator<object> var17 = this.ac.GetEnumerator();

         while(var17.MoveNext()) {
            ToolStripMenuItem var16 = (ToolStripMenuItem)var17.Current;
            var16.SetEnabled(true);
         }
      } else {
         this.W.SetEnabled(false);
         this.X.SetEnabled(false);
         this.Y.SetEnabled(false);
         this.Z.SetEnabled(false);
         this.aa.SetEnabled(false);
         this.ab.SetEnabled(false);
         IEnumerator<object> var3 = this.ac.GetEnumerator();

         while(var3.MoveNext()) {
            ToolStripMenuItem var2 = (ToolStripMenuItem)var3.Current;
            var2.SetEnabled(false);
         }

         this.as.a((gz)null);
         this.at.a((gv[])(new gv[0]), (gB)null);
         this.au.a((gH[])(new gH[0]), (gC)null);
         this.av.a(new gM[0]);
         this.aw.c((gm)null);
         this.ax.a(new gp[0]);
         this.ay.a(new gO[0]);
         this.az.a(new gj[0]);
         this.aA.a((ge)null);
         this.aB.a(new gE[0]);
         this.aC.a((gz)null);
         this.aD.a((gz)null);
         this.O.SetEnabledAt(1, false);
         this.O.SetEnabledAt(2, false);
         this.O.SetEnabledAt(3, false);
         this.O.SetEnabledAt(4, false);
         this.O.SetEnabledAt(5, false);
         this.O.SetEnabledAt(6, false);
         this.O.SetEnabledAt(7, false);
         this.O.SetEnabledAt(8, false);
         this.O.SetEnabledAt(9, false);
         this.O.SetEnabledAt(11, false);
         this.O.SetEnabledAt(12, false);
         if (this.aJ >= 0) {
            if (this.aK == null) {
               this.b("There was an error loading the file.");
            } else {
               this.b("Save file corrupted");
            }
         }
      }

   }

   private void m() {
      try {
         this.aM.k(this.aN);
         this.aO = false;
      } catch (Exception var2) {
         hc.a("Error saving account data", var2);
         this.c("An error occured saving the account data.");
      }

   }

   private void n() {
      if (this.aJ < 0) {
         this.b("No save file selected.");
      } else {
         try {
            hc.info("Formatting JSON...");
            string var1 = this.aI[this.aJ].b(this.aK);
            this.aI = this.aG[this.aH].bX();
            this.aJ = -1;

            for(int var2 = 0; var2 < this.aI.length; ++var2) {
               if (var1.Equals(this.aI[var2].K())) {
                  this.aJ = var2;
                  break;
               }
            }

            this.aL = false;
            this.R.updateUI();
            this.S.updateUI();
            if (this.aJ < 0) {
               this.T.SetText("(no file selected)");
               this.U.SetText("(no file selected)");
               this.V.SetText("(no file selected)");
            } else {
               this.T.SetText(a(this.aI[this.aJ].LastWriteTimeUtc.Ticks));
               this.U.SetText(e(this.aI[this.aJ].Name));
               this.V.SetText(e(this.aI[this.aJ].getDescription()));
            }

            hc.info("Finished.");
         } catch (IOException var3) {
            hc.error("Could not write save file: " + this.aI[this.aJ].K(), var3);
            this.b("There was an error saving the file.");
         }

      }
   }

   private void o() {
      int var1 = this.aG[this.aH].getIndex();
      int var2 = dz.a(this.N, this.aF.bU(), var1);
      if (var2 >= 0 && var2 != var1) {
         try {
            hc.info("Formatting JSON...");
            hc.info("Creating game slot...");
            string var3 = this.aF.a(var2, this.aK);
            this.aG = this.aF.bV();
            this.aH = -1;
            this.aI = new fs[0];
            this.aJ = -1;

            label38:
            for(int var4 = 0; var4 < this.aG.length; ++var4) {
               if (this.aF.W(var3) == this.aG[var4].getIndex()) {
                  this.aH = var4;
                  this.aI = this.aG[var4].bX();
                  int var5 = 0;

                  while(true) {
                     if (var5 >= this.aI.length) {
                        break label38;
                     }

                     if (var3.Equals(this.aI[var5].K())) {
                        this.aJ = var5;
                        break label38;
                     }

                     ++var5;
                  }
               }
            }

            this.aL = false;
            this.R.updateUI();
            this.S.updateUI();
            if (this.aJ < 0) {
               this.T.SetText("(no file selected)");
               this.U.SetText("(no file selected)");
               this.V.SetText("(no file selected)");
            } else {
               this.T.SetText(a(this.aI[this.aJ].LastWriteTimeUtc.Ticks));
               this.U.SetText(e(this.aI[this.aJ].Name));
               this.V.SetText(e(this.aI[this.aJ].getDescription()));
            }

            hc.info("Finished.");
         } catch (IOException var6) {
            hc.error("Could not write save file", var6);
            this.b("There was an error saving the file.");
            return;
         }
      }

   }

   public List<object> g(int var1) {
      List<object> var2 = new List<object>();
      gz var4 = this.as.X();
      if (var4 != null) {
         var2.AddRange((Collection)var4.cC().filter((var1x) => {
            return var1x.ay(var1);
         }).ToList());
      }

      gv[] var5 = this.at.aK();

      for(int var6 = 0; var6 < var5.length; ++var6) {
         gt var3;
         if ((var3 = var5[var6].dE()) != null && var3.ay(var1)) {
            var2.Add(var3);
         }
      }

      gH[] var10 = this.au.aO();

      for(int var7 = 0; var7 < var10.length; ++var7) {
         var2.AddRange((Collection)var10[var7].cC().filter((var1x) => {
            return var1x.ay(var1);
         }).ToList());
      }

      gm var11 = this.aw.Z();
      if (var11 != null) {
         var2.AddRange((Collection)var11.cC().filter((var1x) => {
            return var1x.ay(var1);
         }).ToList());
      }

      gO[] var8 = this.ay.aT();

      for(int var9 = 0; var9 < var8.length; ++var9) {
         var2.AddRange((Collection)var8[var9].cC().filter((var1x) => {
            return var1x.ay(var1);
         }).ToList());
      }

      ge var12 = this.aA.O();
      if (var12 != null) {
         var2.AddRange((Collection)var12.cC().filter((var1x) => {
            return var1x.ay(var1);
         }).ToList());
      }

      return var2;
   }

   private void p() {
      eY var1 = this.aK.H("PlayerStateData.UniverseAddress");
      hl var2 = hl.n(var1);
      if ((var2 = nomanssave.aj.a((Container)this.N, var2)) != null) {
         var2.aL(0);
         this.aK.b("PlayerStateData.UniverseAddress", (Object)var2.ew());
         this.aK.b("PlayerStateData.PreviousUniverseAddress", (Object)var1);
         this.aK.b("SpawnStateData.LastKnownPlayerState", (Object)"InShip");
         this.aL = true;
      }

   }

   private void q() {
      hc.info("Starting JSON Editor...");
      if (cy.a(this, this.aI[this.aJ].K(), this.aK)) {
         this.t();
      }

   }

   private void r() {
      hc.info("Starting JSON Editor...");
      if (cy.a(this, this.aM.K(), this.aN)) {
         try {
            this.aM.k(this.aN);
         } catch (IOException var2) {
            hc.a("JSON Save error", var2);
            this.c("An error occured saving the account data.");
         }
      }

   }

   private static void a(Form var0) {
      SwingUtilities.updateComponentTreeUI(var0);
      Form[] var4;
      int var3 = (var4 = var0.getOwnedWindows()).length;

      for(int var2 = 0; var2 < var3; ++var2) {
         Form var1 = var4[var2];
         a(var1);
      }

   }

   private void s() {
      if (nomanssave.aD.d(this.N)) {
         nomanssave.aH.V();
         a((Form)this.N);
      }

   }

   private void t() {
      this.aL = true;
      eY var1 = this.aK.H("PlayerStateData");
      gz var2 = gz.w(var1);
      gv[] var3 = gv.v(var1);
      gB var4 = gB.x(var1);
      gH[] var5 = gH.C(var1);
      gM[] var6 = gM.D(var1);
      gC var7 = gC.y(var1);
      gm var8 = gm.p(var1);
      gp[] var9 = gp.q(var1);
      gO[] var10 = gO.E(var1);
      gE[] var11 = gE.z(var1);
      bool var12 = gj.n(var1);
      gj[] var13 = gj.o(var1);
      ge var14 = ge.m(var1);
      this.O.SetEnabledAt(1, var2 != null);
      this.as.a(var2);
      this.O.SetEnabledAt(2, var3.length > 0);
      this.at.a(var3, var4);
      this.O.SetEnabledAt(3, var5.length > 0);
      this.au.a(var5, var7);
      this.O.SetEnabledAt(4, var6.length > 0);
      this.av.a(var6);
      this.O.SetEnabledAt(5, var8 != null);
      this.aw.c(var8);
      this.O.SetEnabledAt(6, var8 != null);
      this.ax.a(var9);
      this.O.SetEnabledAt(7, var10.length > 0);
      this.ay.a(var10);
      this.O.SetEnabledAt(8, var12);
      this.az.a(var13);
      this.O.SetEnabledAt(9, var14 != null);
      this.aA.a(var14);
      this.O.SetEnabledAt(10, var11.length > 0);
      this.aB.a(var11);
      this.O.SetEnabledAt(11, var2 != null);
      this.aC.a(var2);
      this.O.SetEnabledAt(12, var2 != null);
      this.aD.a(var2);
   }

   private void u() {
      hc.info("Exporting JSON...");
      cK var1 = cK.aA();
      string var2 = this.aI[this.aJ].K() + ".json";
      var1.setCurrentDirectory(nomanssave.aH.cF);
      var1.setSelectedFile(new File(nomanssave.aH.cF, var2));
      if (var1.showSaveDialog(this.N) == 0) {
         try {
            FileInfo var3 = var1.getSelectedFile();
            if (!var3.Name.EndsWith(".json")) {
               var3 = new File(var3.Directory, var3.Name + ".json");
            }

            if (var3.Exists && MessageBox.Show("Are you sure you want to overwrite this existing JSON file?".ToString(), "Confirm".ToString(), MessageBoxButtons.YesNo) != 0) {
               return;
            }

            this.aK.c(var3);
         } catch (IOException var4) {
            hc.a("JSON Export error", var4);
            this.c("An error occured exporting the save data.");
         }
      }

   }

   private void v() {
      hc.info("Importing JSON...");
      cK var1 = cK.aA();
      var1.setCurrentDirectory(nomanssave.aH.cF);
      if (var1.showOpenDialog(this.N) == 0) {
         try {
            if (MessageBox.Show("Are you sure you want to update your current save data?".ToString(), "Confirm".ToString(), MessageBoxButtons.YesNo) != 0) {
               return;
            }

            FileInfo var2 = var1.getSelectedFile();
            this.aK.d(var2);
            this.t();
         } catch (IOException var3) {
            hc.a("JSON Import error", var3);
            this.c("An error occured importing the save data.");
         }
      }

   }

   private void w() {
      this.as.w();
      this.at.w();
      this.au.w();
      this.aw.w();
      this.ay.w();
      this.aA.w();
   }

   private void x() {
      this.as.x();
      this.at.x();
      this.au.x();
      this.aw.x();
      this.ay.x();
      this.aA.x();
   }

   private void y() {
      this.as.y();
      this.at.y();
      this.au.y();
      this.aw.y();
      this.ay.y();
      this.aA.y();
   }

   private void z() {
      this.at.z();
      this.au.z();
   }

   private void A() {
      this.as.A();
      this.at.A();
      this.au.A();
      this.aw.A();
      this.ay.A();
      this.aA.A();
   }

   public void a(gt var1) {
      this.as.a(var1);
      this.at.a(var1);
      this.au.a(var1);
      this.aw.a(var1);
      this.ay.a(var1);
      this.aA.a(var1);
   }

   public void B() {
      this.aD.B();
   }

   public void C() {
      this.aD.C();
   }

   public eV d(string var1) {
      return this.aK.d(var1);
   }

   public bool D() {
      return this.aK.getValue("PlayerStateData.DifficultyState") != null;
   }

   public string E() {
      return this.aK.getValueAsString("PlayerStateData.DifficultyState.Settings.InventoryStackLimits.InventoryStackLimitsDifficulty");
   }

   public fn F() {
      string var1 = this.aK.getValueAsString("PlayerStateData.DifficultyState.Preset.DifficultyPresetType");
      fn var2;
      if (var1 != null) {
         fn[] var5;
         int var4 = (var5 = fn.Values).length;

         for(int var3 = 0; var3 < var4; ++var3) {
            var2 = var5[var3];
            if (var1.Equals(var2.ToString())) {
               return var2;
            }
         }
      }

      var2 = this.aI[this.aJ].L();
      if (var2 == fn.lr) {
         string var6 = this.aK.getValueAsString("PlayerStateData.SeasonData.GameMode.PresetGameMode");
         if ("Normal".Equals(var6)) {
            var2 = fn.lm;
         }

         if ("Creative".Equals(var6)) {
            var2 = fn.lo;
         }

         if ("Survival".Equals(var6)) {
            var2 = fn.ln;
         }

         if ("Ambient".Equals(var6)) {
            var2 = fn.lp;
         }

         if ("Permadeath".Equals(var6)) {
            var2 = fn.lq;
         }
      }

      return var2;
   }

   public void h(int var1) {
      eY var2 = this.aK.H("PlayerStateData");
      eV var3 = var2.d("Multitools");
      if (var3 != null && var3.Count != 0) {
         eY var4 = gR.az("multitool");
         if (var1 >= 0 && var1 < var3.Count && var4 != null) {
            var3.Remove(var1);
            var3.Add(var4);
            gB var5 = gB.x(var2);
            int var6 = var5.dU();
            if (var6 > 0 && var6 >= var1) {
               --var6;
               var5.aF(var6);
            }

            this.at.a(gv.v(var2), var5);
         }
      }
   }

   public void i(int var1) {
      eY var2 = this.aK.H("PlayerStateData");
      eV var3 = var2.d("ShipOwnership");
      if (var3 != null && var3.Count != 0) {
         eY var4 = gR.az("ship");
         if (var1 >= 0 && var1 < var3.Count && var4 != null) {
            var3.Remove(var1);
            var3.Add(var4);
            gC var5 = gC.y(var2);
            int var6 = var5.dV();
            if (var6 > 0 && var6 >= var1) {
               --var6;
               var5.aG(var6);
            }

            this.au.a(gH.C(var2), var5);
         }
      }
   }

   public void a(gl var1, int var2) {
      eY var3 = this.aK.H("PlayerStateData");
      eV var4;
      switch(I()[var1.ordinal()]) {
      case 1:
         var4 = var3.d("Pets");
         break;
      case 2:
         var4 = var3.d("Eggs");
         break;
      default:
         return;
      }

      if (var4 != null && var4.Count != 0) {
         eY var5 = gR.az("companion");
         if (var2 >= 0 && var2 < var4.Count && var5 != null) {
            var4.Remove(var2);
            var4.Add(var5);
            this.az.a(gj.o(var3));
         }
      }
   }

   public bool j(int var1) {
      eV var2 = this.aK.d("PlayerStateData.FleetExpeditions");

      for(int var3 = 0; var3 < var2.Count; ++var3) {
         eV var4 = var2.V(var3).d("AllFrigateIndices");
         if (var4.hasValue(new Integer(var1))) {
            return true;
         }
      }

      return false;
   }

   public gp[] k(int var1) {
      eV var2 = this.aK.d("PlayerStateData.FleetFrigates");
      eV var3 = this.aK.d("PlayerStateData.FleetExpeditions");

      int var4;
      for(var4 = 0; var4 < var3.Count; ++var4) {
         eV var5 = var3.V(var4).d("AllFrigateIndices");
         if (var5.hasValue(new Integer(var1))) {
            this.c("This frigate is currently on a mission and cannot be deleted!");
            return gp.d(var2);
         }
      }

      if (var2 != null && var1 < var2.Count) {
         var2.ac(var1);

         for(int var9 = 0; var9 < var3.Count; ++var9) {
            eY var12 = var3.V(var9);
            eV var7 = var12.d("ActiveFrigateIndices");

            int var10;
            for(var10 = 0; var10 < var7.Count; ++var10) {
               if ((var4 = var7.Y(var10)) > var1) {
                  var7.a(var10, var4 - 1);
               }
            }

            var7 = var12.d("DamagedFrigateIndices");

            for(var10 = 0; var10 < var7.Count; ++var10) {
               if ((var4 = var7.Y(var10)) > var1) {
                  var7.a(var10, var4 - 1);
               }
            }

            var7 = var12.d("DestroyedFrigateIndices");

            for(var10 = 0; var10 < var7.Count; ++var10) {
               if ((var4 = var7.Y(var10)) > var1) {
                  var7.a(var10, var4 - 1);
               }
            }

            var7 = var12.d("AllFrigateIndices");

            for(var10 = 0; var10 < var7.Count; ++var10) {
               if ((var4 = var7.Y(var10)) > var1) {
                  var7.a(var10, var4 - 1);
               }
            }

            eV var8 = var12.d("Events");

            for(var10 = 0; var10 < var7.Count; ++var10) {
               eY var6 = var8.V(var10);
               var7 = var6.d("AffectedFrigateIndices");

               int var11;
               for(var11 = 0; var11 < var7.Count; ++var11) {
                  if ((var4 = var7.Y(var11)) > var1) {
                     var7.a(var11, var4 - 1);
                  }
               }

               var7 = var6.d("RepairingFrigateIndices");

               for(var11 = 0; var11 < var7.Count; ++var11) {
                  if ((var4 = var7.Y(var11)) > var1) {
                     var7.a(var11, var4 - 1);
                  }
               }

               var7 = var6.d("AffectedFrigateResponses");

               for(var11 = 0; var11 < var7.Count; ++var11) {
                  if ((var4 = var7.Y(var11)) > var1) {
                     var7.a(var11, var4 - 1);
                  }
               }
            }
         }

         this.aL = true;
      }

      return gp.d(var2);
   }

   public gp[] a(int var1, string var2) {
      eV var3 = this.aK.d("PlayerStateData.FleetFrigates");
      if (var3 != null && var1 < var3.Count) {
         eY var4 = var3.V(var1).bE();
         var4.d("ResourceSeed").a(1, var2);
         var4.b("CustomName", (Object)"");
         var3.f(var4);
         this.aL = true;
      }

      return gp.d(var3);
   }

   private void G() {
      int var1 = this.aK.J("PlayerStateData.TotalPlayTime");
      int var2 = 0;
      eV var3 = this.aK.d("PlayerStateData.PersistentPlayerBases");

      int var4;
      eV var5;
      int var6;
      for(var4 = 0; var4 < var3.Count; ++var4) {
         var5 = var3.V(var4).d("Objects");

         for(var6 = 0; var6 < var5.Count; ++var6) {
            string var7 = var5.V(var6).getValueAsString("ObjectID");
            if ("^PLANTER".Equals(var7)) {
               ++var2;
            } else if ("^PLANTERMEGA".Equals(var7)) {
               ++var2;
            }
         }
      }

      var5 = this.aK.d("PlayerStateData.MaintenanceInteractions");

      for(var6 = 0; var6 < var5.Count; ++var6) {
         eY var11 = var5.V(var6);
         eV var8 = var11.d("InventoryContainer.Slots");

         for(int var9 = 0; var9 < var8.Count; ++var9) {
            eY var10 = var8.V(var9);
            if ("^MAINT_FARM5".Equals(var10.getValueAsString("Id"))) {
               if ((var4 = var10.J("MaxAmount")) > 0 && var10.J("Amount") < var4) {
                  var10.b("Amount", (Object)(new Integer(var4)));
               }

               var11.b("LastUpdateTimestamp", (Object)(new Integer(var1)));
               this.aL = true;
               --var2;
            }
         }
      }

   }

   private void initialize() {
      this.N = new Form();
      Image var1 = a("UI-FILEICON.PNG");
      if (var1 != null) {
         this.N.setIconImage(var1.getImage());
      }

      this.N.SetTitle("No Man's Sky Save Editor - 1.19.14 (BREACH)");
      Rectangle var2 = new Rectangle(100, 100, 1100, 720);
      var2.x = nomanssave.aH.a("MainFrame.X", 100);
      var2.y = nomanssave.aH.a("MainFrame.Y", 100);
      var2.width = nomanssave.aH.a("MainFrame.Width", 1000);
      var2.height = nomanssave.aH.a("MainFrame.Height", 700);
      this.N.SetBounds(var2);
      this.N.SetDefaultCloseOperation(3);
      this.N.AddWindowListener(new B(this));
      this.N.addComponentListener(new C(this));
      this.O = new TabControl(1);
      this.N.GetContentPane().Add(this.O, "Center");
      ba var3 = new ba(new int[]{nomanssave.aH.cH, nomanssave.aH.cI, 0});
      this.O.AddTab("Main", (Icon)null, var3, (string)null);
      var3.k("FileInfo Details");
      this.P = new Label();
      this.P.SetText(this.aF == null ? "" : fq.c(this.aF));
      var3.a("Storage", this.P, 2);
      this.Q = new Label();
      this.Q.SetText(this.aF == null ? "(no path selected)" : this.aF.bS().FullName);
      var3.a("Save Path", this.Q, 2);
      this.R = new ComboBox();
      this.R.SetModel(new D(this));
      this.R.SetEnabled(this.aF != null);
      var3.a("Game Slot", (JComponent)this.R);
      this.S = new ComboBox();
      this.S.setEditable(false);
      this.S.SetModel(new E(this));
      this.S.SetEnabled(this.aF != null);
      var3.a("Save File", (JComponent)this.S);
      this.T = new Label();
      this.T.SetText("(no file selected)");
      var3.a("Modified", this.T, 2);
      this.U = new Label();
      this.U.SetText("(no file selected)");
      var3.a("Save Name", this.U, 2);
      this.V = new Label();
      this.V.SetText("(no file selected)");
      var3.a("Description", this.V, 2);
      var3.Y();
      Panel var4 = new Panel();
      var4.SetLayout(new FlowLayout(0, 0, 0));
      this.W = new Button("Reload");
      this.W.SetEnabled(false);
      this.W.AddActionListener((var1x) => {
         this.l();
      });
      var4.Add(this.W);
      this.X = new Button("Save Changes");
      this.X.SetEnabled(false);
      this.X.AddActionListener((var1x) => {
         this.n();
      });
      var4.Add(this.X);
      this.Y = new Button("Save As");
      this.Y.SetEnabled(false);
      this.Y.AddActionListener((var1x) => {
         this.o();
      });
      var4.Add(this.Y);
      var3.a((string)null, var4, 2);
      this.as = new aJ(this);
      this.O.AddTab("Exosuit", (Icon)null, this.as, (string)null);
      this.O.SetEnabledAt(1, false);
      this.at = new dj(this);
      this.O.AddTab("Multitool", (Icon)null, this.at, (string)null);
      this.O.SetEnabledAt(2, false);
      this.au = new dN(this);
      this.O.AddTab("Ships", (Icon)null, this.au, (string)null);
      this.O.SetEnabledAt(3, false);
      this.av = new eb(this);
      this.O.AddTab("Squadron", (Icon)null, this.av, (string)null);
      this.O.SetEnabledAt(4, false);
      this.aw = new bd(this);
      this.O.AddTab("Freighter", (Icon)null, this.aw, (string)null);
      this.O.SetEnabledAt(5, false);
      this.ax = new bl(this);
      this.O.AddTab("Frigates", (Icon)null, this.ax, (string)null);
      this.O.SetEnabledAt(6, false);
      this.ay = new ep(this);
      this.O.AddTab("Vehicles", (Icon)null, this.ay, (string)null);
      this.O.SetEnabledAt(7, false);
      this.az = new X(this);
      this.O.AddTab("Companions", (Icon)null, this.az, (string)null);
      this.O.SetEnabledAt(8, false);
      this.aA = new I(this);
      this.O.AddTab("Bases & Storage", (Icon)null, this.aA, (string)null);
      this.O.SetEnabledAt(9, false);
      this.aB = new dE(this);
      this.O.AddTab("Settlements", (Icon)null, this.aB, (string)null);
      this.O.SetEnabledAt(10, false);
      this.aC = new ap(this);
      this.O.AddTab("Discovery", (Icon)null, this.aC, (string)null);
      this.O.SetEnabledAt(11, false);
      this.aD = new bE(this);
      this.O.AddTab("Milestones / Reputation", (Icon)null, this.aD, (string)null);
      this.O.SetEnabledAt(12, false);
      this.aE = new c(this);
      this.O.AddTab("Account", (Icon)null, this.aE, (string)null);
      this.O.SetEnabledAt(13, false);
      this.O.AddChangeListener((var1x) => {
         if (this.O.SelectedIndex == 12) {
            this.aD.aa();
         }

         if (this.aF != null && this.aO && this.aP) {
            int var2 = MessageBox.Show("Save account data?".ToString(), "Save".ToString(), MessageBoxButtons.YesNo);
            this.aP = var2 == 0;
            if (this.aP) {
               this.m();
            }
         }

      });
      MenuStrip var5 = new MenuStrip();
      this.N.setJMenuBar(var5);
      ToolStripMenuItem var6 = new ToolStripMenuItem("File");
      var5.Add(var6);
      ToolStripMenuItem var7 = new ToolStripMenuItem("Open File/Path");
      var7.SetAccelerator(Keys.getKeyStroke(79, 2));
      var7.AddActionListener((var1x) => {
         this.k();
      });
      var6.Add(var7);
      this.Z = new ToolStripMenuItem("Reload File");
      this.Z.SetEnabled(false);
      this.Z.SetAccelerator(Keys.getKeyStroke(82, 2));
      this.Z.AddActionListener((var1x) => {
         this.l();
      });
      var6.Add(this.Z);
      this.aa = new ToolStripMenuItem("Save File");
      this.aa.SetEnabled(false);
      this.aa.SetAccelerator(Keys.getKeyStroke(83, 2));
      this.aa.AddActionListener((var1x) => {
         Component var2 = this.N.getFocusOwner();
         if (var2 is G) {
            ((G)var2).N();
         }

         this.n();
      });
      var6.Add(this.aa);
      this.ab = new ToolStripMenuItem("Save FileInfo As");
      this.ab.SetEnabled(false);
      this.ab.AddActionListener((var1x) => {
         Component var2 = this.N.getFocusOwner();
         if (var2 is G) {
            ((G)var2).N();
         }

         this.o();
      });
      var6.Add(this.ab);
      var6.AddSeparator();
      ToolStripMenuItem var8 = new ToolStripMenuItem("Exit");
      var8.AddActionListener((var1x) => {
         if (this.aL || this.aO) {
            int var2 = MessageBox.Show("Save data before closing?".ToString(), "Save".ToString(), MessageBoxButtons.YesNo);
            if (var2 == 0) {
               if (this.aL) {
                  this.n();
               }

               if (this.aO) {
                  this.m();
               }
            }
         }

         this.N.Dispose();
      });
      var6.Add(var8);
      ToolStripMenuItem var9 = new ToolStripMenuItem("Edit");
      var5.Add(var9);
      this.ac = new List<object>();
      ToolStripMenuItem var10 = new ToolStripMenuItem("Edit Raw JSON");
      var10.AddActionListener((var1x) => {
         this.q();
      });
      var9.Add(var10);
      this.ac.Add(var10);
      ToolStripMenuItem var11 = new ToolStripMenuItem("Export JSON");
      var11.AddActionListener((var1x) => {
         this.u();
      });
      var9.Add(var11);
      this.ac.Add(var11);
      ToolStripMenuItem var12 = new ToolStripMenuItem("Import JSON");
      var12.AddActionListener((var1x) => {
         this.v();
      });
      var9.Add(var12);
      this.ac.Add(var12);
      ToolStripMenuItem var13 = new ToolStripMenuItem("Coordinate Viewer");
      var13.AddActionListener((var1x) => {
         this.p();
      });
      var9.Add(var13);
      this.ac.Add(var13);
      ToolStripMenuItem var14 = new ToolStripMenuItem("Test Mode");
      var14.setSelected(en.aS());
      var14.AddActionListener((var2x) => {
         bool var3 = var14.isSelected();
         if (var3) {
            int var4 = MessageBox.Show("This mode removes any restrictions imposed by the editor.\nUSE WITH CAUTION: Changes made in test mode may not work in game.".ToString(), "Test Mode".ToString(), MessageBoxButtons.YesNo);
            if (var4 == 2) {
               var14.setSelected(false);
               return;
            }
         }

         en.c(var3);
      });
      var9.Add(var14);
      var9.AddSeparator();
      ToolStripMenuItem var15 = new ToolStripMenuItem("Recharge All Technology");
      var15.AddActionListener((var1x) => {
         this.w();
      });
      var9.Add(var15);
      this.ac.Add(var15);
      ToolStripMenuItem var16 = new ToolStripMenuItem("Refill All Stacks");
      var16.AddActionListener((var1x) => {
         this.x();
      });
      var9.Add(var16);
      this.ac.Add(var16);
      ToolStripMenuItem var17 = new ToolStripMenuItem("Recharge Base Planters");
      var17.AddActionListener((var1x) => {
         this.G();
      });
      var9.Add(var17);
      this.ac.Add(var17);
      ToolStripMenuItem var18 = new ToolStripMenuItem("Expand All Inventories");
      var18.AddActionListener((var1x) => {
         this.A();
      });
      var9.Add(var18);
      this.ac.Add(var18);
      ToolStripMenuItem var19 = new ToolStripMenuItem("Enable All Slots");
      var19.AddActionListener((var1x) => {
         this.y();
      });
      var9.Add(var19);
      this.ac.Add(var19);
      ToolStripMenuItem var20 = new ToolStripMenuItem("Repair All Slots / Technology");
      var20.AddActionListener((var1x) => {
         this.z();
      });
      var9.Add(var20);
      this.ac.Add(var20);
      var9.AddSeparator();
      this.ad = new ToolStripMenuItem("Edit Account JSON");
      this.ad.AddActionListener((var1x) => {
         this.r();
      });
      var9.Add(this.ad);
      IEnumerator<object> var22 = this.ac.GetEnumerator();

      while(var22.MoveNext()) {
         ToolStripMenuItem var21 = (ToolStripMenuItem)var22.Current;
         var21.SetEnabled(false);
      }

      this.ad.SetEnabled(false);
      ToolStripMenuItem var25 = new ToolStripMenuItem("View");
      var5.Add(var25);
      ToolStripMenuItem var26 = new ToolStripMenuItem("Settings");
      var26.AddActionListener((var1x) => {
         this.s();
      });
      var25.Add(var26);
      var5.Add(Box.createHorizontalGlue());
      ToolStripMenuItem var23 = new ToolStripMenuItem("Help");
      var5.Add(var23);
      ToolStripMenuItem var24 = new ToolStripMenuItem("About");
      var24.AddActionListener((var1x) => {
         a.a(this.N);
      });
      var23.Add(var24);
      if (this.aF == null) {
         JavaCompat.InvokeLater(new v(this));
      } else if (this.aN != null) {
         this.ad.SetEnabled(true);
         this.O.SetEnabledAt(13, true);
         this.aE.a(this.aN);
         this.aO = false;
      }

      this.N.Pack();
   }

   private static string e(string var0) {
      return var0 == null ? "(unknown)" : var0;
   }

   // $FF: synthetic method
   static bool a(Application var0) {
      return var0.aQ;
   }

   // $FF: synthetic method
   static fq b(Application var0) {
      return var0.aF;
   }

   // $FF: synthetic method
   static void a(Application var0, bool var1) {
      var0.aR = var1;
   }

   // $FF: synthetic method
   static void b(Application var0, bool var1) {
      var0.aS = var1;
   }

   // $FF: synthetic method
   static int c(Application var0) {
      return var0.aH;
   }

   // $FF: synthetic method
   static ft[] d(Application var0) {
      return var0.aG;
   }

   // $FF: synthetic method
   static void c(Application var0, bool var1) {
      var0.aT = var1;
   }

   // $FF: synthetic method
   static int e(Application var0) {
      return var0.aJ;
   }

   // $FF: synthetic method
   static fs[] f(Application var0) {
      return var0.aI;
   }

   // $FF: synthetic method
   static void d(Application var0, bool var1) {
      var0.aU = var1;
   }

   // $FF: synthetic method
   Application(bool var1, Application var2) {
      // Constructor chain: base(var1)
   }

   // $FF: synthetic method
   static void g(Application var0) {
      L = var0;
   }

   // $FF: synthetic method
   static Application H() {
      return L;
   }

   // $FF: synthetic method
   static Form h(Application var0) {
      return var0.N;
   }

   // $FF: synthetic method
   static int[] I() {
      int[] var10000 = aY;
      if (var10000 != null) {
         return var10000;
      } else {
         int[] var0 = new int[gl.Values.length];

         try {
            var0[gl.oG.ordinal()] = 2;
         } catch (NoSuchFieldError var2) {
         }

         try {
            var0[gl.oF.ordinal()] = 1;
         } catch (NoSuchFieldError var1) {
         }

         aY = var0;
         return var0;
      }
   }

   // $FF: synthetic method
   static bool i(Application var0) {
      return var0.aL;
   }

   // $FF: synthetic method
   static bool j(Application var0) {
      return var0.aO;
   }

   // $FF: synthetic method
   static void k(Application var0) {
      var0.n();
   }

   // $FF: synthetic method
   static void l(Application var0) {
      var0.m();
   }

   // $FF: synthetic method
   static void e(Application var0, bool var1) {
      var0.aQ = var1;
   }

   // $FF: synthetic method
   static void m(Application var0) {
      var0.f();
   }

   // $FF: synthetic method
   static ComboBox n(Application var0) {
      return var0.R;
   }

   // $FF: synthetic method
   static void f(Application var0, bool var1) {
      var0.aL = var1;
   }

   // $FF: synthetic method
   static void a(Application var0, int var1) {
      var0.e(var1);
   }

   // $FF: synthetic method
   static ComboBox o(Application var0) {
      return var0.S;
   }

   // $FF: synthetic method
   static void a(Application var0, fs[] var1) {
      var0.aI = var1;
   }

   // $FF: synthetic method
   static void b(Application var0, int var1) {
      var0.f(var1);
   }

   // $FF: synthetic method
   static void p(Application var0) {
      var0.k();
   }
}

}
