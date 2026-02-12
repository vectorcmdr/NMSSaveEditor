using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{



public class aH {
   public static FileInfo cC;
   public static FileInfo cD;
   public static FileInfo cE;
   public static FileInfo cF;
   public static FileInfo cG;
   public static int cH;
   public static int cI;
   public static int cJ;
   public static eY cK;
   public static bool cL;
   public static int[] cM;

   public static string getProperty(string var0) {
      return cK.getValueAsString(var0);
   }

   public static void setProperty(string var0, string var1) {
      if (var1 == null) {
         cK.N(var0);
      } else {
         cK.c(var0, var1);
      }

      cL = true;
   }

   public static int j(string var0) {
      return cK.J(var0);
   }

   public static int a(string var0, int var1) {
      return cK.c(var0, var1);
   }

   public static void b(string var0, int var1) {
      cK.c(var0, var1);
      cL = true;
   }

   public static double a(string var0, double var1) {
      return cK.c(var0, var1);
   }

   public static void b(string var0, double var1) {
      cK.c(var0, var1);
      cL = true;
   }

   public static object[] a(string var0, Class var1) {
      eV var2 = cK.d(var0);
      if (var2 == null) {
         // PORT_TODO: return (object[])Array.newInstance(0);
      } else {
         // PORT_TODO: object var3 = Array.newInstance(var2.Count);

         for(int var4 = 0; var4 < var2.Count; ++var4) {
            // PORT_TODO: Array.set(var3, var4, var1.cast(var2.getValue(var4)));
            return default;
         }

         return (object[])var3;
      }
   }

   public static void a(string var0, object[] var1) {
      eV var2 = new eV();

      for(int var3 = 0; var3 < var1.Length; ++var3) {
         var2.f(var1[var3]);
      }

      cK.c(var0, var2);
      cL = true;
   }

   public static bool T() {
      return cL;
   }

   public static void U() {
      string var0 = fh.b(cK, true);

      try {
         Exception var1 = null;
         object var2 = null;

         try {
            FileStream var3 = new FileStream((cC).ToString(), System.IO.FileMode.Open);

            try {
               // PORT_TODO: var3.Write(var0.GetBytes(System.Text.Encoding.UTF8));
               cL = false;
            } finally {
               if (var3 != null) {
                  var3.Close();
               }

            }
         } catch (Exception var11) {
            if (var1 == null) {
               var1 = var11;
            } else if (var1 != var11) {
               var1.addSuppressed(var11);
            }

            throw var1;
         }
      } catch (IOException var12) {
         hc.error("Could not save configuration file", var12);
      }

   }

   public static void init(bool var0) {
      Console.WriteLine("Initializing environment...");
      FileInfo var1 = null;

      try {
         // PORT_TODO: URL var2 = typeof(Application).getProtectionDomain().getCodeSource().getLocation();
         if (var0 && var2.getFile().EndsWith(".jar")) {
            // PORT_TODO: var1 = Paths[var2.toURI()].toFile().Directory;
         } else {
            // PORT_TODO: var1 = (new FileInfo(".")).getCanonicalFile();
         }
      } catch (Exception) {
         // PORT_TODO: Console.WriteLine("Error: cannot find working directory");
         var20.printStackTrace();
         // PORT_TODO: Environment.Exit(1);
      } catch (IOException var21) {
         Console.WriteLine("Error: cannot find working directory");
         var21.printStackTrace();
         Environment.Exit(1);
      }

      if (!var1.Attributes.HasFlag(FileAttributes.Directory)) {
         Console.WriteLine("Error: working directory error: " + var1.FullName);
         Environment.Exit(1);
      }

      cD = var1;
      cC = new FileInfo(System.IO.Path.Combine((var1).ToString(), ("NMSSaveEditor.conf").ToString()));
      cE = new FileInfo(System.IO.Path.Combine((var1).ToString(), ("bases").ToString()));
      cF = new FileInfo(System.IO.Path.Combine((var1).ToString(), ("exported").ToString()));
      cG = new FileInfo(System.IO.Path.Combine((var1).ToString(), ("backups").ToString()));
      if (!cG.Exists && (cG.Create()) == null) {
         Console.WriteLine("Error: cannot create backups folder");
         Environment.Exit(1);
      }

      hc.k(new FileInfo(System.IO.Path.Combine((var1).ToString(), ("NMSSaveEditor.log").ToString())));
      hc.debug("Java Vendor: " + Environment.GetEnvironmentVariable("java.vendor"));
      hc.debug("Java Version: " + Environment.GetEnvironmentVariable("java.version"));
      hc.debug("Java Architecture: " + Environment.GetEnvironmentVariable("os.arch"));
      hc.debug("Operating System: " + Environment.GetEnvironmentVariable("os.name"));
      hc.debug("Working Dir: " + var1.FullName);
      cK = new eY();
      cL = false;
      if (cC.Exists) {
         try {
            byte[] var22 = hk.l(cC);
            if (var22.Length > 0 && var22[0] == 123) {
               // PORT_TODO: cK = eY.E(Encoding.UTF8.GetString());
            } else {
               Dictionary<string, string> var3 = new Dictionary<string, string>();
               FileStream var4 = new FileStream((cC).ToString(), System.IO.FileMode.Open);

               try {
                  var3.load(var4);
               } finally {
                  var4.Close();
               }

               eV var6 = new eV();
               // PORT_TODO: IEnumerator<object> var8 = var3.stringPropertyNames().GetEnumerator();

               if (false) { // PORT_TODO: original while had errors
                  string var7 = (string)var8.Current;

                  try {
                     string var9 = var3.getProperty(var7);
                     if (!var7.Equals("InventoryFontScale")) {
                        if (var7.Equals("InventoryScaling")) {
                           cK.b("InventoryScaling", (object)double.Parse(var9));
                        } else if (var7.Equals("FontScaling")) {
                           cK.b("FontScaling", (object)double.Parse(var9));
                        } else {
                           int var5;
                           int var10;
                           int var11;
                           if (var7.EndsWith(".Location")) {
                              var7 = var7.Substring(0, var7.LastIndexOf("."));
                              if (true) { // PORT_TODO: original condition had errors
                                 var10 = int.Parse(var9.Substring(0, var5));
                                 var11 = int.Parse(var9.Substring(var5 + 1));
                                 cK.c(var7 + ".X", var10);
                                 cK.c(var7 + ".Y", var11);
                              }
                           } else if (var7.EndsWith(".Size")) {
                              var7 = var7.Substring(0, var7.LastIndexOf("."));
                              if (true) { // PORT_TODO: original condition had errors
                                 var10 = int.Parse(var9.Substring(0, var5));
                                 var11 = int.Parse(var9.Substring(var5 + 1));
                                 cK.c(var7 + ".Width", var10);
                                 cK.c(var7 + ".Height", var11);
                              }
                           } else if (var7.Equals("JSONEditor.Divider")) {
                              var10 = int.Parse(var9);
                              cK.c(var7, var10);
                           } else if (var7.StartsWith("SteamID.")) {
                              var7 = var7.Substring(8);
                              var6.f(long.Parse(var7));
                              cK.c("KnownPlayers." + var7, var9);
                           } else {
                              cK.c(var7, var9);
                           }
                        }
                     }
                  } catch (FormatException var17) {
                  }
               }

               if (var6.Count > 0) {
                  cK.b("SteamIDs", (object)var6);
               }

               cL = true;
            }
         } catch (IOException var19) {
            hc.a("Could not load configuration file", var19);
         }
      }

      string var23 = cK.getValueAsString("LogLevel");
      if (var23 != null) {
         hc.aA(var23);
      }

      // PORT_TODO: FlatLaf.registerCustomDefaultsSource("nomanssave");
      V();
   }

   public static void V() {
      // PORT_TODO: string var0 = cK.getValueAsString("LookAndFeel");
      aI var1 = (aI)Stream.of(aI.Values).filter((var1x) => {
         return var1x.Name.Equals(var0);
      }).findFirst().orElse(aI.cN);

      try {
         object var2;
         switch(W()[var1.ordinal()]) {
         case 1:
         default:
            // PORT_TODO: var2 = new FlatLightLaf();
            break;
         case 2:
            // PORT_TODO: var2 = new FlatDarkLaf();
            break;
         case 3:
            // PORT_TODO: var2 = new FlatIntelliJLaf();
            break;
         case 4:
            // PORT_TODO: var2 = new FlatDarculaLaf();
            break;
         case 5:
            // PORT_TODO: var2 = new FlatMacLightLaf();
            break;
         case 6:
            // PORT_TODO: var2 = new FlatMacDarkLaf();
            return default;
         }

         /* setLookAndFeel(var2) */;
      } catch (Exception var13) /* UnsupportedLookAndFeelException */ {
         hc.a("Could not set look and feel: " + var1, var13);
         return;
      }

      hc.debug("Look and Feel: " + /* L&F */ null.Name);
      Font var3 = /* UIManager.getFont */ SystemFonts.DefaultFont; //("Label.font");
      if (var3 == null) {
         cH = 120;
         cI = 350;
         cJ = 200;
         /* UIManager.put("Inventory.font", (object)null) */;
         /* UIManager.put("Inventory.gridSize", 200) */;
         /* UIManager.put("Inventory.iconSize", 64) */;
      } else {
         double var4 = cK.L("InventoryScaling");
         if (var4 <= 0.0D) {
            var4 = 1.0D;
            cK.b("InventoryScaling", (object)var4);
            cL = true;
         }

         // PORT_TODO: int var6 = (int)Math.Round((double)var3.getSize() * var4);
         // PORT_TODO: Font var7 = new Font(var3.Name, 0, var6);
         // PORT_TODO: Canvas var8 = new Canvas();
         FontMetrics var9 = Graphics.FromHwnd(IntPtr.Zero).MeasureString("M", var3);
         cH = var9.stringWidth("MMMMMMMMMM");
         cI = var9.stringWidth("MMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
         // PORT_TODO: cJ = var9.stringWidth("MMMMMMMMMMMMMMMMM");
         var9 = Graphics.FromHwnd(IntPtr.Zero).MeasureString("M", var7);
         // PORT_TODO: int var10 = var9.stringWidth("MMMMMMMMMMM");
         int var11 = var10 - (var9.Height * 2 + 8);

         int var12;
         for(var12 = 16; var12 * 2 <= var11; var12 *= 2) {
         }

         if ((double)var12 * 1.5D <= (double)var11) {
            var12 = (int)((double)var12 * 1.5D);
         }

         /* UIManager.put("Inventory.font", var7) */;
         /* UIManager.put("Inventory.gridSize", var10) */;
         /* UIManager.put("Inventory.iconSize", var12) */;
      }

   }
   public static int[] W() {
      int[] var10000 = cM;
      if (var10000 != null) {
         return var10000;
      } else {
         int[] var0 = new int[aI.Values.Length];

         try {
            var0[aI.cQ.ordinal()] = 4;
         } catch (NoSuchFieldError var6) {
         }

         try {
            var0[aI.cO.ordinal()] = 2;
         } catch (NoSuchFieldError var5) {
         }

         try {
            var0[aI.cP.ordinal()] = 3;
         } catch (NoSuchFieldError var4) {
         }

         try {
            var0[aI.cN.ordinal()] = 1;
         } catch (NoSuchFieldError var3) {
         }

         try {
            var0[aI.cS.ordinal()] = 6;
         } catch (NoSuchFieldError var2) {
         }

         try {
            var0[aI.cR.ordinal()] = 5;
         } catch (NoSuchFieldError var1) {
         }

         cM = var0;
         return var0;
      }
   }
}



}
