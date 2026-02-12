using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class Application {
   public static string VERSION = "1.19.14";
   public static string RELEASE = "BREACH";
   public static string J = "https://github.com/goatfungus/NMSSaveEditor";
   public static string K = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_";
   public static Application L;
   public static Dictionary<object, object> M = new Dictionary<object, object>();
   public Form N;
   public TabControl O;
   public Label P;
   public Label Q;
   public ComboBox R;
   public ComboBox S;
   public Label T;
   public Label U;
   public Label V;
   public Button W;
   public Button X;
   public Button Y;
   public ToolStripMenuItem Z;
   public ToolStripMenuItem aa;
   public ToolStripMenuItem ab;
   public List<object> ac;
   public ToolStripMenuItem ad;
   public static int ae = 0;
   public static int af = 1;
   public static int ag = 2;
   public static int ah = 3;
   public static int ai = 4;
   public static int aj = 5;
   public static int ak = 6;
   public static int al = 7;
   public static int am = 8;
   public static int an = 9;
   public static int ao = 10;
   public static int ap = 11;
   public static int aq = 12;
   public static int ar = 13;
   public aJ @as;
   public dj at;
   public dN au;
   public eb av;
   public bd aw;
   public bl ax;
   public ep ay;
   public X az;
   public I aA;
   public dE aB;
   public ap aC;
   public bE aD;
   public c aE;
   public fq aF;
   public ft[] aG;
   public int aH;
   public fs[] aI;
   public int aJ;
   public eY aK;
   public bool aL;
   public fr aM;
   public eY aN;
   public bool aO;
   public bool aP;
   public bool aQ;
   public bool aR;
   public bool aS;
   public bool aT;
   public bool aU;
   public fe aV;
   public fe aW;
   public fR aX;
   public static int[] aY;

   public static string a(long var0) {
      string var2 = "h:mm a, E MMM d, yyyy";
      return var0.ToString();
   }

   public static string b(long var0) {
      string var2 = "MMM d, HH:mm";
      return var0.ToString();
   }

   public static string a(string var0, string var1) {
      if (var0 == null) {
         return var1;
      } else {
         StringBuilder var2 = new StringBuilder();

         for(int var4 = 0; var4 < var0.Length; ++var4) {
            char var3 = var0[var4];
            if ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_".IndexOf(var3) >= 0) {
               var2.Append(var3);
            } else if (char.IsWhiteSpace(var3)) {
               var2.Append('_');
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
      if (var0.Length > var1 && var0[var1].Equals("-autoupdate")) {
         int var3 = var1 + 1;
         var2 = true;
      } else {
         var2 = false;
      }

      NMSSaveEditor.aH.init(!var2);
      hc.info("Starting Editor...");
      (new Thread(() => {
         cK.aA();
      })).Start();
      System.Windows.Forms.Application.Run(new w(var2));
   }

   public static Image a(string var0) {
      Bitmap var1 = (Bitmap)M[var0];
      if (var1 == null) {
         Stream var2 = typeof(Application).GetManifestResourceStream("icons/" + var0);
         if (var2 != null) {
            try {
               var1 = (Bitmap)Image.FromStream(var2);
               M.Put(var0, var1);
            } catch (IOException var4) {
               hc.info("Error loading icon: " + var0);
               hc.info("Error loading icon: " + var0);
            }
         }
      }

      return var1 == null ? null : var1;
   }

   public static Image a(string var0, int var1, int var2) {
      Bitmap var3 = (Bitmap)M[var0];
      if (var3 == null) {
         Stream var4 = typeof(Application).GetManifestResourceStream("icons/" + var0);
         if (var4 != null) {
            try {
               var3 = (Bitmap)Image.FromStream(var4);
               M.Put(var0, var3);
            } catch (IOException var6) {
               hc.info("Error loading icon: " + var0);
               hc.info("Error loading icon: " + var0);
            }
         }
      }

      return var3 == null ? null : new Bitmap(var3, var1, var2, 4);
   }

   public void f() {
      if (this.aR) {
         this.aR = false;
      }

      int var1;
      if (this.aS) {
         this.aS = false;
         var1 = this.aH < 0 ? -1 : this.aG[this.aH].getIndex();
         this.aG = this.aF.bV(); /* TODO: port from Java - was stream().filter().ToArray() */
         this.aH = -1;

         for(int var2 = 0; var2 < this.aG.Length; ++var2) {
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

         this.R.Refresh();
      }

      this.aT &= this.aH >= 0;
      if (this.aT) {
         this.aT = false;
         string var7 = this.aJ < 0 ? null : this.aI[this.aJ].K();
         long var8 = this.aJ < 0 ? long.MinValue : this.aI[this.aJ].LastWriteTimeUtc.Ticks;
         fn var4 = this.aJ < 0 ? null : this.aI[this.aJ].L();
         this.aI = this.aG[this.aH].bX();
         this.aJ = -1;

         int var5;
         for(var5 = 0; var5 < this.aI.Length; ++var5) {
            if (this.aI[var5].K().Equals(var7)) {
               this.aJ = var5;
               break;
            }
         }

         if (var7 != null && this.aJ < 0) {
            this.aU = false;
            var5 = (int)(int)MessageBox.Show(this.N, "Save file has been deleted externally. Would you like to reload?\nNOTE: All changes made in the editor will be lost.", "Reload FileInfo", 0);
            if (var5 == 0) {
               this.aJ = 0;
               this.l();
            } else {
               fs[] var6 = new fs[this.aI.Length + 1];
               var6[0] = new F(this, var7, var8, var4, this.aK);
               Array.Copy(this.aI, 0, var6, 1, this.aI.Length);
               this.aI = var6;
               this.aJ = 0;
            }
         }

         this.S.Refresh();
      }

      this.aU &= this.aJ >= 0;
      if (this.aU) {
         this.aU = false;
         var1 = (int)(int)MessageBox.Show(this.N, "Save file has been modified externally. Would you like to reload?\nNOTE: All changes made in the editor will be lost.", "Reload FileInfo", 0);
         if (var1 == 0) {
            this.l();
         } else {
            this.aL = true;
         }
      }

   }

   public Application(bool var1) {
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
               this.aK.b("PlayerStateData.WeaponInventory", (object)var5.bE());
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
      string var2 = NMSSaveEditor.aH.getProperty("GameStorage");
      string var3 = NMSSaveEditor.aH.getProperty("GameSaveDir");
      this.aF = var3 == null ? null : fq.a(var2, new FileInfo(var3), this.aX);
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
            NMSSaveEditor.aH.setProperty("GameStorage", var2);
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
      (new x(this, var1)).Start();
   }

   public Form g() {
      return this.N;
   }

   public void a(gH var1) {
      FileInfo var2 = NMSSaveEditor.aH.cF;
      if (!var2.Exists && (var2.Create()) == null) {
         var2 = NMSSaveEditor.aH.cD;
      }

      cT var3 = cT.aC();
      string var4 = a(var1.Name, "Ship");
      var3.setCurrentDirectory(var2.FullName.FullName);
      var3.setSelectedFile(new FileInfo(System.IO.Path.Combine((var2).ToString(), (var4).ToString())));
      if (var3.showSaveDialog(this.N) == 0) {
         try {
            FileInfo var5 = new FileInfo(new FileInfo(var3.getSelectedFile()));
            if (!var5.Name.EndsWith(".sh0")) {
               var5 = new FileInfo(System.IO.Path.Combine((var5.Directory).ToString(), (var5.Name + ".sh0").ToString()));
            }

            var1.a(var5, var3.aw());
         } catch (Exception var6) {
            hc.a("Ship export error", var6);
            this.c("An error occured during export.");
      // // } catch (IOException var7) {
            hc.a("Ship export error", var7);
            this.c("An error occured during export.");
         }
      }

   }

   public void a(gv var1) {
      FileInfo var2 = NMSSaveEditor.aH.cF;
      if (!var2.Exists && (var2.Create()) == null) {
         var2 = NMSSaveEditor.aH.cD;
      }

      cv var3 = cv.ax();
      string var4 = a(var1.Name, "Weapon");
      var3.setCurrentDirectory(var2.FullName.FullName);
      var3.setSelectedFile(new FileInfo(System.IO.Path.Combine((var2).ToString(), (var4).ToString())));
      if (var3.showSaveDialog(this.N) == 0) {
         try {
            FileInfo var5 = new FileInfo(new FileInfo(var3.getSelectedFile()));
            if (!var5.Name.EndsWith(".wp0")) {
               var5 = new FileInfo(System.IO.Path.Combine((var5.Directory).ToString(), (var5.Name + ".wp0").ToString()));
            }

            var1.j(var5);
         } catch (Exception var6) {
            hc.a("Weapon export error", var6);
            this.c("An error occured during export.");
      // // } catch (IOException var7) {
            hc.a("Weapon export error", var7);
            this.c("An error occured during export.");
         }
      }

   }

   public void a(gj var1) {
      FileInfo var2 = NMSSaveEditor.aH.cF;
      if (!var2.Exists && (var2.Create()) == null) {
         var2 = NMSSaveEditor.aH.cD;
      }

      string var3 = "." + var1.cL().Name.ToLower();
      cp var4 = cp.at();
      string var5 = a(var1.Name, var1.cL().Name);
      var4.setCurrentDirectory(var2.FullName.FullName);
      var4.setSelectedFile(new FileInfo(System.IO.Path.Combine((var2).ToString(), (var5).ToString())));
      if (var4.showSaveDialog(this.N) == 0) {
         try {
            FileInfo var6 = new FileInfo(new FileInfo(var4.getSelectedFile()));
            if (!var6.Name.EndsWith(var3)) {
               var6 = new FileInfo(System.IO.Path.Combine((var6.Directory).ToString(), (var6.Name + var3).ToString()));
            }

            var1.j(var6);
         } catch (Exception var7) {
            hc.a("Companion export error", var7);
            this.c("An error occured during export.");
      // // } catch (IOException var8) {
            hc.a("Companion export error", var8);
            this.c("An error occured during export.");
         }
      }

   }

   public gH h() {
      eY var1;
      if (this.aK != null && (var1 = this.aK.H("PlayerStateData")) != null) {
         FileInfo var2 = NMSSaveEditor.aH.cF.Exists ? NMSSaveEditor.aH.cF : NMSSaveEditor.aH.cD;
         cT var3 = cT.aC();
         var3.setCurrentDirectory(var2.FullName.FullName);
         if (var3.showOpenDialog(this.N) == 0) {
            try {
               FileInfo var4 = new FileInfo(new FileInfo(var3.getSelectedFile()));
               gH var5 = gH.c(var1, var4);
               this.aL = true;
               return var5;
            } catch (Exception var6) {
               hc.a("Ship import error", var6);
               this.c("An error occured during import.");
      // // } catch (IOException var7) {
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
         FileInfo var2 = NMSSaveEditor.aH.cF.Exists ? NMSSaveEditor.aH.cF : NMSSaveEditor.aH.cD;
         cv var3 = cv.ax();
         var3.setCurrentDirectory(var2.FullName.FullName);
         if (var3.showOpenDialog(this.N) == 0) {
            try {
               FileInfo var4 = new FileInfo(new FileInfo(var3.getSelectedFile()));
               gv var5 = gv.b(var1, var4);
               this.aL = true;
               return var5;
            } catch (Exception var6) {
               hc.a("Weapon import error", var6);
               this.c("An error occured during import.");
      // // } catch (IOException var7) {
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
         FileInfo var2 = NMSSaveEditor.aH.cF.Exists ? NMSSaveEditor.aH.cF : NMSSaveEditor.aH.cD;
         cp var3 = cp.at();
         var3.setCurrentDirectory(var2.FullName.FullName);
         if (var3.showOpenDialog(this.N) == 0) {
            try {
               FileInfo var4 = new FileInfo(new FileInfo(var3.getSelectedFile()));
               gj var5 = gj.a(var1, var4);
               this.aL = true;
               return var5;
            } catch (Exception var6) {
               hc.a("Companion import error", var6);
               this.c("An error occured during import.");
      // // } catch (IOException var7) {
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
      FileInfo var2 = NMSSaveEditor.aH.cE;
      if (!var2.Exists && (var2.Create()) == null) {
         var2 = NMSSaveEditor.aH.cD;
      }

      cl var3 = cl.ar();
      string var4 = a(var1.Name, "Base");
      var3.setCurrentDirectory(var2.FullName.FullName);
      var3.setSelectedFile(new FileInfo(System.IO.Path.Combine((var2).ToString(), (var4).ToString())));
      if (var3.showSaveDialog(this.N) == 0) {
         try {
            FileInfo var5 = new FileInfo(new FileInfo(var3.getSelectedFile()));
            if (!var5.Name.EndsWith(".pb3")) {
               var5 = new FileInfo(System.IO.Path.Combine((var5.Directory).ToString(), (var5.Name + ".pb3").ToString()));
            }

            if (var5.Exists && MessageBox.Show(this.N, "Are you sure you want to overwrite this existing backup file?", "Confirm", 2) != 0) {
               return;
            }

            gS.d(var1.cH(), var5);
         } catch (Exception var6) {
            hc.a("Base backup error", var6);
            this.c("An error occured during backup.");
      // // } catch (IOException var7) {
            hc.a("Base backup error", var7);
            this.c("An error occured during backup.");
            hc.a("Base backup error", var8);
            this.c("An error occured during backup.");
         }
      }

   }

   public bool b(gf var1) {
      FileInfo var2 = NMSSaveEditor.aH.cE.Exists ? NMSSaveEditor.aH.cE : NMSSaveEditor.aH.cD;
      cl var3 = cl.ar();
      var3.setCurrentDirectory(var2.FullName.FullName);
      if (var3.showOpenDialog(this.N) == 0) {
         try {
            if (MessageBox.Show(this.N, "Are you sure you want to overwrite your existing base?", "Confirm", 2) != 0) {
               return false;
            }

            FileInfo var4 = new FileInfo(new FileInfo(var3.getSelectedFile()));
            gS.e(var1.cH(), var4);
            this.aL = true;
            return true;
         } catch (IOException var5) {
            hc.a("Base restore error", var5);
            this.c("An error occured during backup.");
            hc.a("Base restore error", var6);
            this.c("An error occured during backup.");
         }
      }

      return false;
   }

   public void a(gm var1) {
      gn var2 = var1.cZ();
      if (var2 != null) {
         FileInfo var3 = NMSSaveEditor.aH.cE;
         if (!var3.Exists && (var3.Create()) == null) {
            var3 = NMSSaveEditor.aH.cD;
         }

         cs var4 = cs.av();
         string var5 = a(var2.Name, "Freighter");
         var4.setCurrentDirectory(var3.FullName.FullName);
         var4.setSelectedFile(new FileInfo(System.IO.Path.Combine((var3).ToString(), (var5).ToString())));
         if (var4.showSaveDialog(this.N) == 0) {
            try {
               FileInfo var6 = new FileInfo(new FileInfo(var4.getSelectedFile()));
               if (!var6.Name.EndsWith(".fb3")) {
                  var6 = new FileInfo(System.IO.Path.Combine((var6.Directory).ToString(), (var6.Name + ".fb3").ToString()));
               }

               if (var6.Exists && MessageBox.Show(this.N, "Are you sure you want to overwrite this existing backup file?", "Confirm", 2) != 0) {
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
               gS.a(var2.cH(), (Dictionary<object, object>)var7, (FileInfo)var6);
            } catch (Exception var15) {
               hc.a("Freighter backup error", var15);
               this.c("An error occured during backup.");
      // // } catch (IOException var16) {
               hc.a("Freighter backup error", var16);
               this.c("An error occured during backup.");
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
         FileInfo var3 = NMSSaveEditor.aH.cE.Exists ? NMSSaveEditor.aH.cE : NMSSaveEditor.aH.cD;
         cs var4 = cs.av();
         var4.setCurrentDirectory(var3.FullName.FullName);
         if (var4.showOpenDialog(this.N) == 0) {
            try {
               if (MessageBox.Show(this.N, "Are you sure you want to overwrite your existing freighter?", "Confirm", 2) != 0) {
                  return false;
               }

               FileInfo var5 = new FileInfo(new FileInfo(var4.getSelectedFile()));
               Dictionary<object, object> var6 = new Dictionary<object, object>();
               gS.b(var2.cH(), var6, var5);
               eY var7 = this.aK.H("PlayerStateData");
               bool var8 = false;
               IEnumerator<object> var10 = var6.entrySet().GetEnumerator();

               while(var10.MoveNext()) {
                  KeyValuePair<object, object> var9 = (KeyValuePair<object, object>)var10.Current;
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
                     var7.b("FreighterInventory", (object)((eY)var9.getValue()));
                     var8 = true;
                  }

                  if (((string)var9.getKey()).Equals("InventoryTech")) {
                     var7.b("FreighterInventory_TechOnly", (object)((eY)var9.getValue()));
                     var8 = true;
                  }

                  if (((string)var9.getKey()).Equals("InventoryCargo")) {
                     var7.b("FreighterInventory_Cargo", (object)((eY)var9.getValue()));
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
               hc.a("Freighter restore error", var12);
               this.c("An error occured during restore.");
            }
         }

         return false;
      }
   }

   public void k() {
      FileInfo var1 = ej.b(this.aF == null ? null : this.aF.bS());
      if (var1 != null) {
         string var2;
         FileInfo var3;
         if (var1.Attributes.HasFlag(FileAttributes.Directory)) {
            var3 = var1;
            var2 = null;
         } else {
            var3 = var1.Directory;
            var2 = var1.Name;
         }

         if (this.aF != null) {
            if (this.aF.bS().Attributes.HasFlag(FileAttributes.Directory) && !this.aF.bS().Equals(var3)) {
               this.aF = null;
            } else if (this.aF.bS().Exists && !this.aF.bS().Equals(var1)) {
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
            this.ad.Enabled = (false);
            this.O.setEnabledAt(13, false);
            this.aE.a((eY)null);
            this.aO = false;
            this.P.Text = ("(none)");
            this.Q.Text = ("(none)");
         } else {
            string var4 = fq.c(this.aF);
            NMSSaveEditor.aH.setProperty("GameStorage", var4);
            NMSSaveEditor.aH.setProperty("GameSaveDir", this.aF.bS().FullName);
            hc.debug("Storage: " + var4);
            hc.debug("Save Path: " + this.aF.bS().FullName);
            this.aG = this.aF.bV();
            this.aH = -1;
            this.aI = new fs[0];
            this.aJ = -1;
            if (var2 != null) {
               label85:
               for(int var5 = 0; var5 < this.aG.Length; ++var5) {
                  if (this.aF.W(var2) == this.aG[var5].getIndex()) {
                     this.aH = var5;
                     this.aI = this.aG[var5].bX();
                     int var6 = 0;

                     while(true) {
                        if (var6 >= this.aI.Length) {
                           goto endLabel85;
                        }

                        if (var2.Equals(this.aI[var6].K())) {
                           this.aJ = var6;
                           goto endLabel85;
                        }

                        ++var6;
                     }
                  }
               }
               endLabel85: ;
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

            this.ad.Enabled = (this.aN != null);
            this.O.setEnabledAt(13, this.aN != null);
            this.aE.a(this.aN);
            this.aO = false;
            this.P.Text = (var4);
            this.Q.Text = (this.aF.bS().FullName);
         }

         this.R.Enabled = (true);
         this.S.Enabled = (true);
         if (this.aJ > 0) {
            this.c("The save file you have selected is not the most recent.");
         }

         this.l();
      }

   }

   public void e(int var1) {
      this.aH = var1;
      if (this.aH < 0) {
         this.aI = new fs[0];
         this.aJ = -1;
      } else {
         this.aI = this.aG[this.aH].bX();
         this.aJ = this.aI.Length > 0 ? 0 : -1;
      }

      this.l();
   }

   public void f(int var1) {
      this.aJ = var1;
      this.l();
   }

   public void b(string var1) {
      System.Windows.Forms.Application.Run(new z(this, var1));
   }

   public void c(string var1) {
      System.Windows.Forms.Application.Run(new A(this, var1));
   }

   public void l() {
      this.R.Refresh();
      this.S.Refresh();
      this.aL = false;
      this.aK = null;
      if (this.aJ < 0) {
         this.T.Text = ("(no file selected)");
         this.U.Text = ("(no file selected)");
         this.V.Text = ("(no file selected)");
         if (this.aH >= 0) {
            hc.info("No current save file found for " + this.aG[this.aH]);
            this.b("Save file not found for " + this.aG[this.aH]);
         }
      } else {
         try {
            this.T.Text = (a(this.aI[this.aJ].LastWriteTimeUtc.Ticks));
            this.U.Text = (e(this.aI[this.aJ].Name));
            this.V.Text = (e(this.aI[this.aJ].getDescription()));
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

      this.O.SelectedIndex = (0);
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
         this.O.setEnabledAt(1, var21 != null);
         this.@as.a(var21);
         this.O.setEnabledAt(2, var4.Length > 0);
         this.at.a(var4, var5);
         this.O.setEnabledAt(3, var6.Length > 0);
         this.au.a(var6, var7);
         this.O.setEnabledAt(4, var8.Length > 0);
         this.av.a(var8);
         this.O.setEnabledAt(5, var9 != null);
         this.aw.c(var9);
         this.O.setEnabledAt(6, var9 != null);
         this.ax.a(var10);
         this.O.setEnabledAt(7, var11.Length > 0);
         this.ay.a(var11);
         this.O.setEnabledAt(8, var13);
         this.az.a(var14);
         this.O.setEnabledAt(9, var15 != null);
         this.aA.a(var15);
         this.O.setEnabledAt(10, var12.Length > 0);
         this.aB.a(var12);
         this.O.setEnabledAt(11, var21 != null);
         this.aC.a(var21);
         this.O.setEnabledAt(12, var21 != null);
         this.aD.a(var21);
         this.W.Enabled = (!(this.aI[this.aJ] is F));
         this.X.Enabled = (true);
         this.Y.Enabled = (var20);
         this.Z.Enabled = (true);
         this.aa.Enabled = (true);
         this.ab.Enabled = (var20);
         IEnumerator<object> var17 = this.ac.GetEnumerator();

         while(var17.MoveNext()) {
            ToolStripMenuItem var16 = (ToolStripMenuItem)var17.Current;
            var16.Enabled = (true);
         }
      } else {
         this.W.Enabled = (false);
         this.X.Enabled = (false);
         this.Y.Enabled = (false);
         this.Z.Enabled = (false);
         this.aa.Enabled = (false);
         this.ab.Enabled = (false);
         IEnumerator<object> var3 = this.ac.GetEnumerator();

         while(var3.MoveNext()) {
            ToolStripMenuItem var2 = (ToolStripMenuItem)var3.Current;
            var2.Enabled = (false);
         }

         this.@as.a((gz)null);
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
         this.O.setEnabledAt(1, false);
         this.O.setEnabledAt(2, false);
         this.O.setEnabledAt(3, false);
         this.O.setEnabledAt(4, false);
         this.O.setEnabledAt(5, false);
         this.O.setEnabledAt(6, false);
         this.O.setEnabledAt(7, false);
         this.O.setEnabledAt(8, false);
         this.O.setEnabledAt(9, false);
         this.O.setEnabledAt(11, false);
         this.O.setEnabledAt(12, false);
         if (this.aJ >= 0) {
            if (this.aK == null) {
               this.b("There was an error loading the file.");
            } else {
               this.b("Save file corrupted");
            }
         }
      }

   }

   public void m() {
      try {
         this.aM.k(this.aN);
         this.aO = false;
      } catch (Exception var2) {
         hc.a("Error saving account data", var2);
         this.c("An error occured saving the account data.");
      }

   }

   public void n() {
      if (this.aJ < 0) {
         this.b("No save file selected.");
      } else {
         try {
            hc.info("Formatting JSON...");
            string var1 = this.aI[this.aJ].b(this.aK);
            this.aI = this.aG[this.aH].bX();
            this.aJ = -1;

            for(int var2 = 0; var2 < this.aI.Length; ++var2) {
               if (var1.Equals(this.aI[var2].K())) {
                  this.aJ = var2;
                  break;
               }
            }

            this.aL = false;
            this.R.Refresh();
            this.S.Refresh();
            if (this.aJ < 0) {
               this.T.Text = ("(no file selected)");
               this.U.Text = ("(no file selected)");
               this.V.Text = ("(no file selected)");
            } else {
               this.T.Text = (a(this.aI[this.aJ].LastWriteTimeUtc.Ticks));
               this.U.Text = (e(this.aI[this.aJ].Name));
               this.V.Text = (e(this.aI[this.aJ].getDescription()));
            }

            hc.info("Finished.");
         } catch (IOException var3) {
            hc.error("Could not write save file: " + this.aI[this.aJ].K(), var3);
            this.b("There was an error saving the file.");
         }

      }
   }

   public void o() {
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
            for(int var4 = 0; var4 < this.aG.Length; ++var4) {
               if (this.aF.W(var3) == this.aG[var4].getIndex()) {
                  this.aH = var4;
                  this.aI = this.aG[var4].bX();
                  int var5 = 0;

                  while(true) {
                     if (var5 >= this.aI.Length) {
                        goto endLabel38;
                     }

                     if (var3.Equals(this.aI[var5].K())) {
                        this.aJ = var5;
                        goto endLabel38;
                     }

                     ++var5;
                  }
               }
            }
            endLabel38: ;

            this.aL = false;
            this.R.Refresh();
            this.S.Refresh();
            if (this.aJ < 0) {
               this.T.Text = ("(no file selected)");
               this.U.Text = ("(no file selected)");
               this.V.Text = ("(no file selected)");
            } else {
               this.T.Text = (a(this.aI[this.aJ].LastWriteTimeUtc.Ticks));
               this.U.Text = (e(this.aI[this.aJ].Name));
               this.V.Text = (e(this.aI[this.aJ].getDescription()));
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
      gz var4 = this.@as.X();
      if (var4 != null) {
         var2.AddRange((ICollection)var4.cC().stream().filter((var1x) => {
            return var1x.ay(var1);
         }).collect(Collectors.toList()));
      }

      gv[] var5 = this.at.aK();

      for(int var6 = 0; var6 < var5.Length; ++var6) {
         gt var3;
         if ((var3 = var5[var6].dE()) != null && var3.ay(var1)) {
            var2.Add(var3);
         }
      }

      gH[] var10 = this.au.aO();

      for(int var7 = 0; var7 < var10.Length; ++var7) {
         var2.AddRange((ICollection)var10[var7].cC().stream().filter((var1x) => {
            return var1x.ay(var1);
         }).collect(Collectors.toList()));
      }

      gm var11 = this.aw.Z();
      if (var11 != null) {
         var2.AddRange((ICollection)var11.cC().stream().filter((var1x) => {
            return var1x.ay(var1);
         }).collect(Collectors.toList()));
      }

      gO[] var8 = this.ay.aT();

      for(int var9 = 0; var9 < var8.Length; ++var9) {
         var2.AddRange((ICollection)var8[var9].cC().stream().filter((var1x) => {
            return var1x.ay(var1);
         }).collect(Collectors.toList()));
      }

      ge var12 = this.aA.O();
      if (var12 != null) {
         var2.AddRange((ICollection)var12.cC().stream().filter((var1x) => {
            return var1x.ay(var1);
         }).collect(Collectors.toList()));
      }

      return var2;
   }

   public void p() {
      eY var1 = this.aK.H("PlayerStateData.UniverseAddress");
      hl var2 = hl.n(var1);
      if (true) {
         var2.aL(0);
         this.aK.b("PlayerStateData.UniverseAddress", (object)var2.ew());
         this.aK.b("PlayerStateData.PreviousUniverseAddress", (object)var1);
         this.aK.b("SpawnStateData.LastKnownPlayerState", (object)"InShip");
         this.aL = true;
      }

   }

   public void q() {
      hc.info("Starting JSON Editor...");
      if (cy.a(this, this.aI[this.aJ].K(), this.aK)) {
         this.t();
      }

   }

   public void r() {
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

   public static void a(Window var0) {
      Control.updateComponentTreeUI(var0);
      Window[] var4;
      int var3 = (var4 = var0.getOwnedWindows()).Length;

      for(int var2 = 0; var2 < var3; ++var2) {
         Window var1 = var4[var2];
         a(var1);
      }

   }

   public void s() {
      if (NMSSaveEditor.aD.d(this.N)) {
         NMSSaveEditor.aH.V();
         a((Window)this.N);
      }

   }

   public void t() {
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
      this.O.setEnabledAt(1, var2 != null);
      this.@as.a(var2);
      this.O.setEnabledAt(2, var3.Length > 0);
      this.at.a(var3, var4);
      this.O.setEnabledAt(3, var5.Length > 0);
      this.au.a(var5, var7);
      this.O.setEnabledAt(4, var6.Length > 0);
      this.av.a(var6);
      this.O.setEnabledAt(5, var8 != null);
      this.aw.c(var8);
      this.O.setEnabledAt(6, var8 != null);
      this.ax.a(var9);
      this.O.setEnabledAt(7, var10.Length > 0);
      this.ay.a(var10);
      this.O.setEnabledAt(8, var12);
      this.az.a(var13);
      this.O.setEnabledAt(9, var14 != null);
      this.aA.a(var14);
      this.O.setEnabledAt(10, var11.Length > 0);
      this.aB.a(var11);
      this.O.setEnabledAt(11, var2 != null);
      this.aC.a(var2);
      this.O.setEnabledAt(12, var2 != null);
      this.aD.a(var2);
   }

   public void u() {
      hc.info("Exporting JSON...");
      cK var1 = cK.aA();
      string var2 = this.aI[this.aJ].K() + ".json";
      var1.setCurrentDirectory(NMSSaveEditor.aH.cF);
      var1.setSelectedFile(new FileInfo(System.IO.Path.Combine((NMSSaveEditor.aH.cF).ToString(), (var2).ToString())));
      if (var1.showSaveDialog(this.N) == 0) {
         try {
            FileInfo var3 = new FileInfo(new FileInfo(var1.getSelectedFile()));
            if (!var3.Name.EndsWith(".json")) {
               var3 = new FileInfo(System.IO.Path.Combine((var3.Directory).ToString(), (var3.Name + ".json").ToString()));
            }

            if (var3.Exists && MessageBox.Show(this.N, "Are you sure you want to overwrite this existing JSON file?", "Confirm", 2) != 0) {
               return;
            }

            this.aK.c(var3);
         } catch (IOException var4) {
            hc.a("JSON Export error", var4);
            this.c("An error occured exporting the save data.");
         }
      }

   }

   public void v() {
      hc.info("Importing JSON...");
      cK var1 = cK.aA();
      var1.setCurrentDirectory(NMSSaveEditor.aH.cF);
      if (var1.showOpenDialog(this.N) == 0) {
         try {
            if (MessageBox.Show(this.N, "Are you sure you want to update your current save data?", "Confirm", 2) != 0) {
               return;
            }

            FileInfo var2 = new FileInfo(new FileInfo(var1.getSelectedFile()));
            this.aK.d(var2);
            this.t();
         } catch (IOException var3) {
            hc.a("JSON Import error", var3);
            this.c("An error occured importing the save data.");
         }
      }

   }

   public void w() {
      this.@as.w();
      this.at.w();
      this.au.w();
      this.aw.w();
      this.ay.w();
      this.aA.w();
   }

   public void x() {
      this.@as.x();
      this.at.x();
      this.au.x();
      this.aw.x();
      this.ay.x();
      this.aA.x();
   }

   public void y() {
      this.@as.y();
      this.at.y();
      this.au.y();
      this.aw.y();
      this.ay.y();
      this.aA.y();
   }

   public void z() {
      this.at.z();
      this.au.z();
   }

   public void A() {
      this.@as.A();
      this.at.A();
      this.au.A();
      this.aw.A();
      this.ay.A();
      this.aA.A();
   }

   public void a(gt var1) {
      this.@as.a(var1);
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
      // PORT_TODO: int var4 = (var5 = fn.Values).Length;

         for(int var3 = 0; var3 < var4; ++var3) {
            var2 = var5[var3];
            if (var1.Equals(var2.Name)) {
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
         if (var4.hasValue(((int)(var1)))) {
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
         if (var5.hasValue(((int)(var1)))) {
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
         var4.b("CustomName", (object)"");
         var3.f(var4);
         this.aL = true;
      }

      return gp.d(var3);
   }

   public void G() {
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
                  var10.b("Amount", (object)(((int)(var4))));
               }

               var11.b("LastUpdateTimestamp", (object)(((int)(var1))));
               this.aL = true;
               --var2;
            }
         }
      }

   }

   public void initialize() {
      this.N = new Form();
      Image var1 = a("UI-FILEICON.PNG");
      if (var1 != null) {
         this.N/* setIconImage */(var1.getImage());
      }

      this.N.Text = ("No Man's Sky Save Editor - 1.19.14 (BREACH)");
      Rectangle var2 = new Rectangle(100, 100, 1100, 720);
      // PORT_TODO: var2.x = NMSSaveEditor.aH.a("MainFrame.X", 100);
      // PORT_TODO: var2.y = NMSSaveEditor.aH.a("MainFrame.Y", 100);
      // PORT_TODO: var2.width = NMSSaveEditor.aH.a("MainFrame.Width", 1000);
      // PORT_TODO: var2.height = NMSSaveEditor.aH.a("MainFrame.Height", 700);
      this.N.Bounds = new Rectangle(var2);
      this.N/* setDefaultCloseOperation */(3);
      // this.N.addWindowListener - use FormClosing event instead
      this.N.addComponentListener(new C(this));
      this.O = new TabControl();
      this.N.Controls.Add(this.O);
      ba var3 = new ba(new int[]{NMSSaveEditor.aH.cH, NMSSaveEditor.aH.cI, 0});
      // PORT_TODO: this.O.TabPages.Add(new TabPage("Main") {{ Tag = var3 }});
      var3.k("FileInfo Details");
      this.P = new Label();
      this.P.Text = (this.aF == null ? "" : fq.c(this.aF));
      var3.a("Storage", this.P, 2);
      this.Q = new Label();
      this.Q.Text = (this.aF == null ? "(no path selected)" : this.aF.bS().FullName);
      var3.a("Save Path", this.Q, 2);
      this.R = new ComboBox();
      this.R.DataSource = (new D(this));
      this.R.Enabled = (this.aF != null);
      var3.a("Game Slot", (Control)this.R);
      this.S = new ComboBox();
      this.S.setEditable(false);
      this.S.DataSource = (new E(this));
      this.S.Enabled = (this.aF != null);
      var3.a("Save FileInfo", (Control)this.S);
      this.T = new Label();
      this.T.Text = ("(no file selected)");
      var3.a("Modified", this.T, 2);
      this.U = new Label();
      this.U.Text = ("(no file selected)");
      var3.a("Save Name", this.U, 2);
      this.V = new Label();
      this.V.Text = ("(no file selected)");
      var3.a("Description", this.V, 2);
      var3.Y();
      Panel var4 = new Panel();
      var4.SuspendLayout(); // TODO: set layout new FlowLayoutPanel(0, 0, 0));
      this.W = new Button() { Text = "Reload" };
      this.W.Enabled = (false);
      // PORT_TODO: this.W.Click += ((var1x) => {
      // this.l();
      // });
      var4.Add(this.W);
      this.X = new Button() { Text = "Save Changes" };
      this.X.Enabled = (false);
      // PORT_TODO: this.X.Click += ((var1x) => {
      // this.n();
      // });
      var4.Add(this.X);
      this.Y = new Button() { Text = "Save As" };
      this.Y.Enabled = (false);
      // PORT_TODO: this.Y.Click += ((var1x) => {
      // this.o();
      // });
      var4.Add(this.Y);
      var3.a(null, var4, 2);
      this.@as = new aJ(this);
      // PORT_TODO: this.O.TabPages.Add(new TabPage("Exosuit") {{ Tag = this.@as }});
      this.O.setEnabledAt(1, false);
      this.at = new dj(this);
      // PORT_TODO: this.O.TabPages.Add(new TabPage("Multitool") {{ Tag = this.at }});
      this.O.setEnabledAt(2, false);
      this.au = new dN(this);
      // PORT_TODO: this.O.TabPages.Add(new TabPage("Ships") {{ Tag = this.au }});
      this.O.setEnabledAt(3, false);
      this.av = new eb(this);
      // PORT_TODO: this.O.TabPages.Add(new TabPage("Squadron") {{ Tag = this.av }});
      this.O.setEnabledAt(4, false);
      this.aw = new bd(this);
      // PORT_TODO: this.O.TabPages.Add(new TabPage("Freighter") {{ Tag = this.aw }});
      this.O.setEnabledAt(5, false);
      this.ax = new bl(this);
      // PORT_TODO: this.O.TabPages.Add(new TabPage("Frigates") {{ Tag = this.ax }});
      this.O.setEnabledAt(6, false);
      this.ay = new ep(this);
      // PORT_TODO: this.O.TabPages.Add(new TabPage("Vehicles") {{ Tag = this.ay }});
      this.O.setEnabledAt(7, false);
      this.az = new X(this);
      // PORT_TODO: this.O.TabPages.Add(new TabPage("Companions") {{ Tag = this.az }});
      this.O.setEnabledAt(8, false);
      this.aA = new I(this);
      // PORT_TODO: this.O.TabPages.Add(new TabPage("Bases & Storage") {{ Tag = this.aA }});
      this.O.setEnabledAt(9, false);
      this.aB = new dE(this);
      // PORT_TODO: this.O.TabPages.Add(new TabPage("Settlements") {{ Tag = this.aB }});
      this.O.setEnabledAt(10, false);
      this.aC = new ap(this);
      // PORT_TODO: this.O.TabPages.Add(new TabPage("Discovery") {{ Tag = this.aC }});
      this.O.setEnabledAt(11, false);
      this.aD = new bE(this);
      // PORT_TODO: this.O.TabPages.Add(new TabPage("Milestones / Reputation") {{ Tag = this.aD }});
      this.O.setEnabledAt(12, false);
      this.aE = new c(this);
      // PORT_TODO: this.O.TabPages.Add(new TabPage("Account") {{ Tag = this.aE }});
      this.O.setEnabledAt(13, false);
      this.O.addChangeListener((var1x) => {
         if (this.O.SelectedIndex == 12) {
            this.aD.aa();
         }

         if (this.aF != null && this.aO && this.aP) {
            int var2 = MessageBox.Show(this.N, "Save account data?", "Save", 0);
            this.aP = var2 == 0;
            if (this.aP) {
               this.m();
            }
         }

      });
      MenuStrip var5 = new MenuStrip();
      this.N.setJMenuBar(var5);
      ToolStripMenuItem var6 = new ToolStripMenuItem("FileInfo");
      var5.Add(var6);
      ToolStripMenuItem var7 = new ToolStripMenuItem("Open FileInfo/Path");
      var7; /* TODO: .setAccelerator(KeyStroke.getKeyStroke(79, 2)); */
      // PORT_TODO: var7.Click += ((var1x) => {
      // this.k();
      // });
      var6.Add(var7);
      this.Z = new ToolStripMenuItem("Reload FileInfo");
      this.Z.Enabled = (false);
      this.Z; /* TODO: .setAccelerator(KeyStroke.getKeyStroke(82, 2)); */
      // PORT_TODO: this.Z.Click += ((var1x) => {
      // this.l();
      // });
      var6.Add(this.Z);
      this.aa = new ToolStripMenuItem("Save FileInfo");
      this.aa.Enabled = (false);
      this.aa; /* TODO: .setAccelerator(KeyStroke.getKeyStroke(83, 2)); */
      // PORT_TODO: this.aa.Click += ((var1x) => {
      // Component var2 = this.N.getFocusOwner();
      // if (var2 is G) {
      // ((G)var2).N();
      // }

      // this.n();
      // });
      var6.Add(this.aa);
      this.ab = new ToolStripMenuItem("Save FileInfo As");
      this.ab.Enabled = (false);
      // PORT_TODO: this.ab.Click += ((var1x) => {
      // Component var2 = this.N.getFocusOwner();
      // if (var2 is G) {
      // ((G)var2).N();
      // }

      // this.o();
      // });
      var6.Add(this.ab);
      var6.addSeparator();
      ToolStripMenuItem var8 = new ToolStripMenuItem("Exit");
      // PORT_TODO: var8.Click += ((var1x) => {
      // if (this.aL || this.aO) {
      // int var2 = MessageBox.Show(this.N, "Save data before closing?", "Save", 0);
      // if (var2 == 0) {
      // if (this.aL) {
      // this.n();
      // }

      // if (this.aO) {
      // this.m();
      // }
      // }
      // }

      // this.N.Dispose();
      // });
      var6.Add(var8);
      ToolStripMenuItem var9 = new ToolStripMenuItem("Edit");
      var5.Add(var9);
      this.ac = new List<object>();
      ToolStripMenuItem var10 = new ToolStripMenuItem("Edit Raw JSON");
      // PORT_TODO: var10.Click += ((var1x) => {
      // this.q();
      // });
      var9.Add(var10);
      this.ac.Add(var10);
      ToolStripMenuItem var11 = new ToolStripMenuItem("Export JSON");
      // PORT_TODO: var11.Click += ((var1x) => {
      // this.u();
      // });
      var9.Add(var11);
      this.ac.Add(var11);
      ToolStripMenuItem var12 = new ToolStripMenuItem("Import JSON");
      // PORT_TODO: var12.Click += ((var1x) => {
      // this.v();
      // });
      var9.Add(var12);
      this.ac.Add(var12);
      ToolStripMenuItem var13 = new ToolStripMenuItem("Coordinate Viewer");
      // PORT_TODO: var13.Click += ((var1x) => {
      // this.p();
      // });
      var9.Add(var13);
      this.ac.Add(var13);
      ToolStripMenuItem var14 = new ToolStripMenuItem("Test Mode");
      var14.Checked = (en.aS());
      // PORT_TODO: var14.Click += ((var2x) => {
      // bool var3 = var14.Checked;
      // if (var3) {
      // int var4 = MessageBox.Show(this.N, "This mode removes any restrictions imposed by the editor.\nUSE WITH CAUTION: Changes made in test mode may not work in game.", "Test Mode", 2);
      // if (var4 == 2) {
      // var14.Checked = (false);
      // return;
      // }
      // }

      // en.c(var3);
      // });
      var9.Add(var14);
      var9.addSeparator();
      ToolStripMenuItem var15 = new ToolStripMenuItem("Recharge All Technology");
      // PORT_TODO: var15.Click += ((var1x) => {
      // this.w();
      // });
      var9.Add(var15);
      this.ac.Add(var15);
      ToolStripMenuItem var16 = new ToolStripMenuItem("Refill All Stacks");
      // PORT_TODO: var16.Click += ((var1x) => {
      // this.x();
      // });
      var9.Add(var16);
      this.ac.Add(var16);
      ToolStripMenuItem var17 = new ToolStripMenuItem("Recharge Base Planters");
      // PORT_TODO: var17.Click += ((var1x) => {
      // this.G();
      // });
      var9.Add(var17);
      this.ac.Add(var17);
      ToolStripMenuItem var18 = new ToolStripMenuItem("Expand All Inventories");
      // PORT_TODO: var18.Click += ((var1x) => {
      // this.A();
      // });
      var9.Add(var18);
      this.ac.Add(var18);
      ToolStripMenuItem var19 = new ToolStripMenuItem("Enable All Slots");
      // PORT_TODO: var19.Click += ((var1x) => {
      // this.y();
      // });
      var9.Add(var19);
      this.ac.Add(var19);
      ToolStripMenuItem var20 = new ToolStripMenuItem("Repair All Slots / Technology");
      // PORT_TODO: var20.Click += ((var1x) => {
      // this.z();
      // });
      var9.Add(var20);
      this.ac.Add(var20);
      var9.addSeparator();
      this.ad = new ToolStripMenuItem("Edit Account JSON");
      // PORT_TODO: this.ad.Click += ((var1x) => {
      // this.r();
      // });
      var9.Add(this.ad);
      IEnumerator<object> var22 = this.ac.GetEnumerator();

      while(var22.MoveNext()) {
         ToolStripMenuItem var21 = (ToolStripMenuItem)var22.Current;
         var21.Enabled = (false);
      }

      this.ad.Enabled = (false);
      ToolStripMenuItem var25 = new ToolStripMenuItem("View");
      var5.Add(var25);
      ToolStripMenuItem var26 = new ToolStripMenuItem("Settings");
      // PORT_TODO: var26.Click += ((var1x) => {
      // this.s();
      // });
      var25.Add(var26);
      var5.Add(Control.createHorizontalGlue());
      ToolStripMenuItem var23 = new ToolStripMenuItem("Help");
      var5.Add(var23);
      ToolStripMenuItem var24 = new ToolStripMenuItem("About");
      // PORT_TODO: var24.Click += ((var1x) => {
      // a.a(this.N);
      // });
      var23.Add(var24);
      if (this.aF == null) {
         System.Windows.Forms.Application.Run(new v(this));
      } else if (this.aN != null) {
         this.ad.Enabled = (true);
         this.O.setEnabledAt(13, true);
         this.aE.a(this.aN);
         this.aO = false;
      }

      this.N.PerformLayout();
   }

   public static string e(string var0) {
      return var0 == null ? "(unknown)" : var0;
   }
   public static bool a(Application var0) {
      return var0.aQ;
   }
   public static fq b(Application var0) {
      return var0.aF;
   }
   public static void a(Application var0, bool var1) {
      var0.aR = var1;
   }
   public static void b(Application var0, bool var1) {
      var0.aS = var1;
   }
   public static int c(Application var0) {
      return var0.aH;
   }
   public static ft[] d(Application var0) {
      return var0.aG;
   }
   public static void c(Application var0, bool var1) {
      var0.aT = var1;
   }
   public static int e(Application var0) {
      return var0.aJ;
   }
   public static fs[] f(Application var0) {
      return var0.aI;
   }
   public static void d(Application var0, bool var1) {
      var0.aU = var1;
   }
   public Application(bool var1, Application var2) {
      // PORT_TODO: this(var1);
   }
   public static void g(Application var0) {
      L = var0;
   }
   public static Application H() {
      return L;
   }
   public static Form h(Application var0) {
      return var0.N;
   }
   public static int[] I() {
      int[] var10000 = aY;
      if (var10000 != null) {
         return var10000;
      } else {
         int[] var0 = new int[gl.Values.Length];

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
   public static bool i(Application var0) {
      return var0.aL;
   }
   public static bool j(Application var0) {
      return var0.aO;
   }
   public static void k(Application var0) {
      var0.n();
   }
   public static void l(Application var0) {
      var0.m();
   }
   public static void e(Application var0, bool var1) {
      var0.aQ = var1;
   }
   public static void m(Application var0) {
      var0.f();
   }
   public static ComboBox n(Application var0) {
      return var0.R;
   }
   public static void f(Application var0, bool var1) {
      var0.aL = var1;
   }
   public static void a(Application var0, int var1) {
      var0.e(var1);
   }
   public static ComboBox o(Application var0) {
      return var0.S;
   }
   public static void a(Application var0, fs[] var1) {
      var0.aI = var1;
   }
   public static void b(Application var0, int var1) {
      var0.f(var1);
   }
   public static void p(Application var0) {
      var0.k();
   }
}



}
