package nomanssave;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.FontMetrics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Properties;
import java.util.stream.Stream;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class aH {
   private static File cC;
   public static File cD;
   public static File cE;
   public static File cF;
   public static File cG;
   public static int cH;
   public static int cI;
   public static int cJ;
   private static eY cK;
   private static boolean cL;
   // $FF: synthetic field
   private static int[] cM;

   public static String getProperty(String var0) {
      return cK.getValueAsString(var0);
   }

   public static void setProperty(String var0, String var1) {
      if (var1 == null) {
         cK.N(var0);
      } else {
         cK.c(var0, var1);
      }

      cL = true;
   }

   public static int j(String var0) {
      return cK.J(var0);
   }

   public static int a(String var0, int var1) {
      return cK.c(var0, var1);
   }

   public static void b(String var0, int var1) {
      cK.c(var0, var1);
      cL = true;
   }

   public static double a(String var0, double var1) {
      return cK.c(var0, var1);
   }

   public static void b(String var0, double var1) {
      cK.c(var0, var1);
      cL = true;
   }

   public static Object[] a(String var0, Class var1) {
      eV var2 = cK.d(var0);
      if (var2 == null) {
         return (Object[])Array.newInstance(var1, 0);
      } else {
         Object var3 = Array.newInstance(var1, var2.size());

         for(int var4 = 0; var4 < var2.size(); ++var4) {
            Array.set(var3, var4, var1.cast(var2.getValue(var4)));
         }

         return (Object[])var3;
      }
   }

   public static void a(String var0, Object[] var1) {
      eV var2 = new eV();

      for(int var3 = 0; var3 < var1.length; ++var3) {
         var2.f(var1[var3]);
      }

      cK.c(var0, var2);
      cL = true;
   }

   static boolean T() {
      return cL;
   }

   static void U() {
      String var0 = fh.b(cK, true);

      try {
         Throwable var1 = null;
         Object var2 = null;

         try {
            FileOutputStream var3 = new FileOutputStream(cC);

            try {
               var3.write(var0.getBytes("UTF-8"));
               cL = false;
            } finally {
               if (var3 != null) {
                  var3.close();
               }

            }
         } catch (Throwable var11) {
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

   public static void init(boolean var0) {
      System.out.println("Initializing environment...");
      File var1 = null;

      try {
         URL var2 = Application.class.getProtectionDomain().getCodeSource().getLocation();
         if (var0 && var2.getFile().endsWith(".jar")) {
            var1 = Paths.get(var2.toURI()).toFile().getParentFile();
         } else {
            var1 = (new File(".")).getCanonicalFile();
         }
      } catch (URISyntaxException var20) {
         System.out.println("Error: cannot find working directory");
         var20.printStackTrace();
         System.exit(1);
      } catch (IOException var21) {
         System.out.println("Error: cannot find working directory");
         var21.printStackTrace();
         System.exit(1);
      }

      if (!var1.isDirectory()) {
         System.out.println("Error: working directory error: " + var1.getAbsolutePath());
         System.exit(1);
      }

      cD = var1;
      cC = new File(var1, "NMSSaveEditor.conf");
      cE = new File(var1, "bases");
      cF = new File(var1, "exported");
      cG = new File(var1, "backups");
      if (!cG.exists() && !cG.mkdir()) {
         System.out.println("Error: cannot create backups folder");
         System.exit(1);
      }

      hc.k(new File(var1, "NMSSaveEditor.log"));
      hc.debug("Java Vendor: " + System.getProperty("java.vendor"));
      hc.debug("Java Version: " + System.getProperty("java.version"));
      hc.debug("Java Architecture: " + System.getProperty("os.arch"));
      hc.debug("Operating System: " + System.getProperty("os.name"));
      hc.debug("Working Dir: " + var1.getAbsolutePath());
      cK = new eY();
      cL = false;
      if (cC.exists()) {
         try {
            byte[] var22 = hk.l(cC);
            if (var22.length > 0 && var22[0] == 123) {
               cK = eY.E(new String(var22, "UTF-8"));
            } else {
               Properties var3 = new Properties();
               FileInputStream var4 = new FileInputStream(cC);

               try {
                  var3.load(var4);
               } finally {
                  var4.close();
               }

               eV var6 = new eV();
               Iterator var8 = var3.stringPropertyNames().iterator();

               while(var8.hasNext()) {
                  String var7 = (String)var8.next();

                  try {
                     String var9 = var3.getProperty(var7);
                     if (!var7.equals("InventoryFontScale")) {
                        if (var7.equals("InventoryScaling")) {
                           cK.b("InventoryScaling", (Object)Double.parseDouble(var9));
                        } else if (var7.equals("FontScaling")) {
                           cK.b("FontScaling", (Object)Double.parseDouble(var9));
                        } else {
                           int var5;
                           int var10;
                           int var11;
                           if (var7.endsWith(".Location")) {
                              var7 = var7.substring(0, var7.lastIndexOf("."));
                              if ((var5 = var9.indexOf(44)) > 0) {
                                 var10 = Integer.parseInt(var9.substring(0, var5));
                                 var11 = Integer.parseInt(var9.substring(var5 + 1));
                                 cK.c(var7 + ".X", var10);
                                 cK.c(var7 + ".Y", var11);
                              }
                           } else if (var7.endsWith(".Size")) {
                              var7 = var7.substring(0, var7.lastIndexOf("."));
                              if ((var5 = var9.indexOf(44)) > 0) {
                                 var10 = Integer.parseInt(var9.substring(0, var5));
                                 var11 = Integer.parseInt(var9.substring(var5 + 1));
                                 cK.c(var7 + ".Width", var10);
                                 cK.c(var7 + ".Height", var11);
                              }
                           } else if (var7.equals("JSONEditor.Divider")) {
                              var10 = Integer.parseInt(var9);
                              cK.c(var7, var10);
                           } else if (var7.startsWith("SteamID.")) {
                              var7 = var7.substring(8);
                              var6.f(Long.parseLong(var7));
                              cK.c("KnownPlayers." + var7, var9);
                           } else {
                              cK.c(var7, var9);
                           }
                        }
                     }
                  } catch (NumberFormatException var17) {
                  }
               }

               if (var6.size() > 0) {
                  cK.b("SteamIDs", (Object)var6);
               }

               cL = true;
            }
         } catch (IOException var19) {
            hc.a("Could not load configuration file", var19);
         }
      }

      String var23 = cK.getValueAsString("LogLevel");
      if (var23 != null) {
         hc.aA(var23);
      }

      FlatLaf.registerCustomDefaultsSource("nomanssave");
      V();
   }

   public static void V() {
      String var0 = cK.getValueAsString("LookAndFeel");
      aI var1 = (aI)Stream.of(aI.values()).filter((var1x) -> {
         return var1x.name().equalsIgnoreCase(var0);
      }).findFirst().orElse(aI.cN);

      try {
         Object var2;
         switch(W()[var1.ordinal()]) {
         case 1:
         default:
            var2 = new FlatLightLaf();
            break;
         case 2:
            var2 = new FlatDarkLaf();
            break;
         case 3:
            var2 = new FlatIntelliJLaf();
            break;
         case 4:
            var2 = new FlatDarculaLaf();
            break;
         case 5:
            var2 = new FlatMacLightLaf();
            break;
         case 6:
            var2 = new FlatMacDarkLaf();
         }

         UIManager.setLookAndFeel((LookAndFeel)var2);
      } catch (UnsupportedLookAndFeelException var13) {
         hc.a("Could not set look and feel: " + var1, var13);
         return;
      }

      hc.debug("Look and Feel: " + UIManager.getLookAndFeel().getName());
      Font var3 = UIManager.getFont("Label.font");
      if (var3 == null) {
         cH = 120;
         cI = 350;
         cJ = 200;
         UIManager.put("Inventory.font", (Object)null);
         UIManager.put("Inventory.gridSize", 200);
         UIManager.put("Inventory.iconSize", 64);
      } else {
         double var4 = cK.L("InventoryScaling");
         if (var4 <= 0.0D) {
            var4 = 1.0D;
            cK.b("InventoryScaling", (Object)var4);
            cL = true;
         }

         int var6 = (int)Math.round((double)var3.getSize() * var4);
         Font var7 = new Font(var3.getName(), 0, var6);
         Canvas var8 = new Canvas();
         FontMetrics var9 = var8.getFontMetrics(var3);
         cH = var9.stringWidth("MMMMMMMMMM");
         cI = var9.stringWidth("MMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
         cJ = var9.stringWidth("MMMMMMMMMMMMMMMMM");
         var9 = var8.getFontMetrics(var7);
         int var10 = var9.stringWidth("MMMMMMMMMMM");
         int var11 = var10 - (var9.getHeight() * 2 + 8);

         int var12;
         for(var12 = 16; var12 * 2 <= var11; var12 *= 2) {
         }

         if ((double)var12 * 1.5D <= (double)var11) {
            var12 = (int)((double)var12 * 1.5D);
         }

         UIManager.put("Inventory.font", var7);
         UIManager.put("Inventory.gridSize", var10);
         UIManager.put("Inventory.iconSize", var12);
      }

   }

   // $FF: synthetic method
   static int[] W() {
      int[] var10000 = cM;
      if (var10000 != null) {
         return var10000;
      } else {
         int[] var0 = new int[aI.values().length];

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
