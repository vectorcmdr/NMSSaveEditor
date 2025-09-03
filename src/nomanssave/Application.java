package nomanssave;

import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class Application {
   public static final String VERSION = "1.19.2";
   public static final String RELEASE = "VOYAGERS";
   private static final String J = "https://github.com/goatfungus/NMSSaveEditor";
   private static final String K = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_";
   private static Application L;
   private static HashMap M = new HashMap();
   private JFrame N;
   private JTabbedPane O;
   private JLabel P;
   private JLabel Q;
   private JComboBox R;
   private JComboBox S;
   private JLabel T;
   private JLabel U;
   private JLabel V;
   private JButton W;
   private JButton X;
   private JButton Y;
   private JMenuItem Z;
   private JMenuItem aa;
   private JMenuItem ab;
   private List ac;
   private JMenuItem ad;
   private static final int ae = 0;
   private static final int af = 1;
   private static final int ag = 2;
   private static final int ah = 3;
   private static final int ai = 4;
   private static final int aj = 5;
   private static final int ak = 6;
   private static final int al = 7;
   private static final int am = 8;
   private static final int an = 9;
   private static final int ao = 10;
   private static final int ap = 11;
   private static final int aq = 12;
   private static final int ar = 13;
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
   private boolean aL;
   private fr aM;
   private eY aN;
   private boolean aO;
   private boolean aP;
   private boolean aQ;
   private boolean aR;
   private boolean aS;
   private boolean aT;
   private boolean aU;
   private fe aV;
   private fe aW;
   private fR aX;
   // $FF: synthetic field
   private static int[] aY;

   public static String a(long var0) {
      SimpleDateFormat var2 = new SimpleDateFormat("h:mm a, E MMM d, yyyy");
      return var2.format(new Date(var0));
   }

   public static String b(long var0) {
      SimpleDateFormat var2 = new SimpleDateFormat("MMM d, HH:mm");
      return var2.format(new Date(var0));
   }

   private static String a(String var0, String var1) {
      if (var0 == null) {
         return var1;
      } else {
         StringBuffer var2 = new StringBuffer();

         for(int var4 = 0; var4 < var0.length(); ++var4) {
            char var3 = var0.charAt(var4);
            if ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_".indexOf(var3) >= 0) {
               var2.append(var3);
            } else if (Character.isWhitespace(var3)) {
               var2.append('_');
            }
         }

         return var2.length() != 0 ? var2.toString() : var1;
      }
   }

   public static Application e() {
      return L;
   }

   public static void main(String[] var0) {
      byte var1 = 0;
      boolean var2;
      if (var0.length > var1 && var0[var1].equals("-autoupdate")) {
         int var3 = var1 + 1;
         var2 = true;
      } else {
         var2 = false;
      }

      nomanssave.aH.init(!var2);
      hc.info("Starting Editor...");
      (new Thread(() -> {
         cK.aA();
      })).start();
      EventQueue.invokeLater(new w(var2));
   }

   public static ImageIcon a(String var0) {
      BufferedImage var1 = (BufferedImage)M.get(var0);
      if (var1 == null) {
         InputStream var2 = Application.class.getResourceAsStream("icons/" + var0);
         if (var2 != null) {
            try {
               var1 = ImageIO.read(var2);
               M.put(var0, var1);
            } catch (IOException var4) {
               hc.info("Error loading icon: " + var0);
            } catch (RuntimeException var5) {
               hc.info("Error loading icon: " + var0);
            }
         }
      }

      return var1 == null ? null : new ImageIcon(var1);
   }

   public static ImageIcon a(String var0, int var1, int var2) {
      BufferedImage var3 = (BufferedImage)M.get(var0);
      if (var3 == null) {
         InputStream var4 = Application.class.getResourceAsStream("icons/" + var0);
         if (var4 != null) {
            try {
               var3 = ImageIO.read(var4);
               M.put(var0, var3);
            } catch (IOException var6) {
               hc.info("Error loading icon: " + var0);
            } catch (RuntimeException var7) {
               hc.info("Error loading icon: " + var0);
            }
         }
      }

      return var3 == null ? null : new ImageIcon(var3.getScaledInstance(var1, var2, 4));
   }

   private void f() {
      if (this.aR) {
         this.aR = false;
      }

      int var1;
      if (this.aS) {
         this.aS = false;
         var1 = this.aH < 0 ? -1 : this.aG[this.aH].getIndex();
         this.aG = (ft[])Arrays.asList(this.aF.bU()).stream().filter((var1x) -> {
            return var1x.getIndex() == var1 || !var1x.isEmpty();
         }).toArray((var0) -> {
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
         String var7 = this.aJ < 0 ? null : this.aI[this.aJ].K();
         long var8 = this.aJ < 0 ? Long.MIN_VALUE : this.aI[this.aJ].lastModified();
         fn var4 = this.aJ < 0 ? null : this.aI[this.aJ].L();
         this.aI = this.aG[this.aH].bX();
         this.aJ = -1;

         int var5;
         for(var5 = 0; var5 < this.aI.length; ++var5) {
            if (this.aI[var5].K().equals(var7)) {
               this.aJ = var5;
               break;
            }
         }

         if (var7 != null && this.aJ < 0) {
            this.aU = false;
            var5 = JOptionPane.showConfirmDialog(this.N, "Save file has been deleted externally. Would you like to reload?\nNOTE: All changes made in the editor will be lost.", "Reload File", 0);
            if (var5 == 0) {
               this.aJ = 0;
               this.l();
            } else {
               fs[] var6 = new fs[this.aI.length + 1];
               var6[0] = new F(this, var7, var8, var4, this.aK);
               System.arraycopy(this.aI, 0, var6, 1, this.aI.length);
               this.aI = var6;
               this.aJ = 0;
            }
         }

         this.S.updateUI();
      }

      this.aU &= this.aJ >= 0;
      if (this.aU) {
         this.aU = false;
         var1 = JOptionPane.showConfirmDialog(this.N, "Save file has been modified externally. Would you like to reload?\nNOTE: All changes made in the editor will be lost.", "Reload File", 0);
         if (var1 == 0) {
            this.l();
         } else {
            this.aL = true;
         }
      }

   }

   private Application(boolean var1) {
      this.aQ = false;
      this.aR = false;
      this.aS = false;
      this.aT = false;
      this.aU = false;
      this.aV = (var1x, var2x, var3x) -> {
         this.aO = true;
         this.aP = true;
         if (var3x == null) {
            hc.info("Removing " + var1x);
         } else {
            String var4;
            if (var3x instanceof eY) {
               var4 = "[OBJECT]";
            } else if (var3x instanceof eV) {
               var4 = "[ARRAY]";
            } else {
               var4 = var3x.toString();
            }

            hc.info("Setting " + var1x + ": " + var4);
         }
      };
      this.aW = (var1x, var2x, var3x) -> {
         this.aL = true;
         if (var1x.startsWith("PlayerStateData.Multitools")) {
            int var4 = this.aK.J("PlayerStateData.ActiveMultioolIndex");
            if (var1x.startsWith("PlayerStateData.Multitools[" + var4 + "].Store.")) {
               eY var5 = this.aK.H("PlayerStateData.Multitools[" + var4 + "].Store");
               this.aK.b("PlayerStateData.WeaponInventory", (Object)var5.bE());
            } else if (var1x.equals("PlayerStateData.Multitools[" + var4 + "].Seed[1]")) {
               this.aK.b("PlayerStateData.CurrentWeapon.GenerationSeed[1]", var3x);
            } else if (var1x.equals("PlayerStateData.Multitools[" + var4 + "].Resource.Filename")) {
               this.aK.b("PlayerStateData.CurrentWeapon.Filename", var3x);
            }
         }

         if (var3x == null) {
            hc.info("Removing " + var1x);
         } else {
            String var6;
            if (var3x instanceof eY) {
               var6 = "OBJECT";
            } else if (var3x instanceof eV) {
               var6 = "ARRAY[" + ((eV)var3x).size() + "]";
            } else {
               var6 = fh.b(var3x, false);
            }

            hc.info("Setting " + var1x + ": " + var6);
         }
      };
      this.aX = new u(this);
      String var2 = nomanssave.aH.getProperty("GameStorage");
      String var3 = nomanssave.aH.getProperty("GameSaveDir");
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
         hc.debug("Save Path: " + this.aF.bS().getAbsolutePath());
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

   public JFrame g() {
      return this.N;
   }

   public void a(gH var1) {
      File var2 = nomanssave.aH.cF;
      if (!var2.exists() && !var2.mkdir()) {
         var2 = nomanssave.aH.cD;
      }

      cT var3 = cT.aC();
      String var4 = a(var1.getName(), "Ship");
      var3.setCurrentDirectory(var2);
      var3.setSelectedFile(new File(var2, var4));
      if (var3.showSaveDialog(this.N) == 0) {
         try {
            File var5 = var3.getSelectedFile();
            if (!var5.getName().endsWith(".sh0")) {
               var5 = new File(var5.getParentFile(), var5.getName() + ".sh0");
            }

            var1.a(var5, var3.aw());
         } catch (RuntimeException var6) {
            hc.a("Ship export error", var6);
            this.c("An error occured during export.");
         } catch (IOException var7) {
            hc.a("Ship export error", var7);
            this.c("An error occured during export.");
         }
      }

   }

   public void a(gv var1) {
      File var2 = nomanssave.aH.cF;
      if (!var2.exists() && !var2.mkdir()) {
         var2 = nomanssave.aH.cD;
      }

      cv var3 = cv.ax();
      String var4 = a(var1.getName(), "Weapon");
      var3.setCurrentDirectory(var2);
      var3.setSelectedFile(new File(var2, var4));
      if (var3.showSaveDialog(this.N) == 0) {
         try {
            File var5 = var3.getSelectedFile();
            if (!var5.getName().endsWith(".wp0")) {
               var5 = new File(var5.getParentFile(), var5.getName() + ".wp0");
            }

            var1.j(var5);
         } catch (RuntimeException var6) {
            hc.a("Weapon export error", var6);
            this.c("An error occured during export.");
         } catch (IOException var7) {
            hc.a("Weapon export error", var7);
            this.c("An error occured during export.");
         }
      }

   }

   public void a(gj var1) {
      File var2 = nomanssave.aH.cF;
      if (!var2.exists() && !var2.mkdir()) {
         var2 = nomanssave.aH.cD;
      }

      String var3 = "." + var1.cL().name().toLowerCase();
      cp var4 = cp.at();
      String var5 = a(var1.getName(), var1.cL().name());
      var4.setCurrentDirectory(var2);
      var4.setSelectedFile(new File(var2, var5));
      if (var4.showSaveDialog(this.N) == 0) {
         try {
            File var6 = var4.getSelectedFile();
            if (!var6.getName().endsWith(var3)) {
               var6 = new File(var6.getParentFile(), var6.getName() + var3);
            }

            var1.j(var6);
         } catch (RuntimeException var7) {
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
         File var2 = nomanssave.aH.cF.exists() ? nomanssave.aH.cF : nomanssave.aH.cD;
         cT var3 = cT.aC();
         var3.setCurrentDirectory(var2);
         if (var3.showOpenDialog(this.N) == 0) {
            try {
               File var4 = var3.getSelectedFile();
               gH var5 = gH.c(var1, var4);
               this.aL = true;
               return var5;
            } catch (RuntimeException var6) {
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
         File var2 = nomanssave.aH.cF.exists() ? nomanssave.aH.cF : nomanssave.aH.cD;
         cv var3 = cv.ax();
         var3.setCurrentDirectory(var2);
         if (var3.showOpenDialog(this.N) == 0) {
            try {
               File var4 = var3.getSelectedFile();
               gv var5 = gv.b(var1, var4);
               this.aL = true;
               return var5;
            } catch (RuntimeException var6) {
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
         File var2 = nomanssave.aH.cF.exists() ? nomanssave.aH.cF : nomanssave.aH.cD;
         cp var3 = cp.at();
         var3.setCurrentDirectory(var2);
         if (var3.showOpenDialog(this.N) == 0) {
            try {
               File var4 = var3.getSelectedFile();
               gj var5 = gj.a(var1, var4);
               this.aL = true;
               return var5;
            } catch (RuntimeException var6) {
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
      File var2 = nomanssave.aH.cE;
      if (!var2.exists() && !var2.mkdir()) {
         var2 = nomanssave.aH.cD;
      }

      cl var3 = cl.ar();
      String var4 = a(var1.getName(), "Base");
      var3.setCurrentDirectory(var2);
      var3.setSelectedFile(new File(var2, var4));
      if (var3.showSaveDialog(this.N) == 0) {
         try {
            File var5 = var3.getSelectedFile();
            if (!var5.getName().endsWith(".pb3")) {
               var5 = new File(var5.getParentFile(), var5.getName() + ".pb3");
            }

            if (var5.exists() && JOptionPane.showConfirmDialog(this.N, "Are you sure you want to overwrite this existing backup file?", "Confirm", 2) != 0) {
               return;
            }

            gS.d(var1.cH(), var5);
         } catch (RuntimeException var6) {
            hc.a("Base backup error", var6);
            this.c("An error occured during backup.");
         } catch (IOException var7) {
            hc.a("Base backup error", var7);
            this.c("An error occured during backup.");
         } catch (GeneralSecurityException var8) {
            hc.a("Base backup error", var8);
            this.c("An error occured during backup.");
         }
      }

   }

   public boolean b(gf var1) {
      File var2 = nomanssave.aH.cE.exists() ? nomanssave.aH.cE : nomanssave.aH.cD;
      cl var3 = cl.ar();
      var3.setCurrentDirectory(var2);
      if (var3.showOpenDialog(this.N) == 0) {
         try {
            if (JOptionPane.showConfirmDialog(this.N, "Are you sure you want to overwrite your existing base?", "Confirm", 2) != 0) {
               return false;
            }

            File var4 = var3.getSelectedFile();
            gS.e(var1.cH(), var4);
            this.aL = true;
            return true;
         } catch (IOException var5) {
            hc.a("Base restore error", var5);
            this.c("An error occured during backup.");
         } catch (GeneralSecurityException var6) {
            hc.a("Base restore error", var6);
            this.c("An error occured during backup.");
         }
      }

      return false;
   }

   public void a(gm var1) {
      gn var2 = var1.cZ();
      if (var2 != null) {
         File var3 = nomanssave.aH.cE;
         if (!var3.exists() && !var3.mkdir()) {
            var3 = nomanssave.aH.cD;
         }

         cs var4 = cs.av();
         String var5 = a(var2.getName(), "Freighter");
         var4.setCurrentDirectory(var3);
         var4.setSelectedFile(new File(var3, var5));
         if (var4.showSaveDialog(this.N) == 0) {
            try {
               File var6 = var4.getSelectedFile();
               if (!var6.getName().endsWith(".fb3")) {
                  var6 = new File(var6.getParentFile(), var6.getName() + ".fb3");
               }

               if (var6.exists() && JOptionPane.showConfirmDialog(this.N, "Are you sure you want to overwrite this existing backup file?", "Confirm", 2) != 0) {
                  return;
               }

               HashMap var7 = new HashMap();
               var7.put("HomeSeed", var1.cU());
               var7.put("ResourceSeed", var1.cV());
               var7.put("Name", var1.getName());
               var7.put("TypeClass", var1.cW());
               var7.put("Resource", var1.cT());
               var7.put("FleetCoordination", var1.cY());
               var7.put("Hyperdrive", var1.cX());
               eY var8 = this.aK.H("PlayerStateData");
               eY var9 = var8.H("FreighterInventory").bE();
               eY var10 = var8.H("FreighterInventory_TechOnly").bE();
               eY var11 = var8.H("FreighterInventory_Cargo").bE();
               if (!var4.aw()) {
                  eV var12 = var9.d("Slots");

                  int var13;
                  eY var14;
                  for(var13 = 0; var13 < var12.size(); ++var13) {
                     var14 = var12.V(var13);
                     if (!var14.getValueAsString("Type.InventoryType").equals("Technology")) {
                        var12.ac(var13--);
                     }
                  }

                  var12 = var11.d("Slots");

                  for(var13 = 0; var13 < var12.size(); ++var13) {
                     var14 = var12.V(var13);
                     if (!var14.getValueAsString("Type.InventoryType").equals("Technology")) {
                        var12.ac(var13--);
                     }
                  }
               }

               var7.put("Inventory", var9);
               var7.put("InventoryTech", var10);
               var7.put("InventoryCargo", var11);
               gS.a(var2.cH(), (Map)var7, (File)var6);
            } catch (RuntimeException var15) {
               hc.a("Freighter backup error", var15);
               this.c("An error occured during backup.");
            } catch (IOException var16) {
               hc.a("Freighter backup error", var16);
               this.c("An error occured during backup.");
            } catch (GeneralSecurityException var17) {
               hc.a("Freighter backup error", var17);
               this.c("An error occured during backup.");
            }
         }

      }
   }

   public boolean b(gm var1) {
      gn var2 = var1.cZ();
      if (var2 == null) {
         return false;
      } else {
         File var3 = nomanssave.aH.cE.exists() ? nomanssave.aH.cE : nomanssave.aH.cD;
         cs var4 = cs.av();
         var4.setCurrentDirectory(var3);
         if (var4.showOpenDialog(this.N) == 0) {
            try {
               if (JOptionPane.showConfirmDialog(this.N, "Are you sure you want to overwrite your existing freighter?", "Confirm", 2) != 0) {
                  return false;
               }

               File var5 = var4.getSelectedFile();
               HashMap var6 = new HashMap();
               gS.b(var2.cH(), var6, var5);
               eY var7 = this.aK.H("PlayerStateData");
               boolean var8 = false;
               Iterator var10 = var6.entrySet().iterator();

               while(var10.hasNext()) {
                  Entry var9 = (Entry)var10.next();
                  if (((String)var9.getKey()).equals("HomeSeed")) {
                     var1.ah((String)var9.getValue());
                  }

                  if (((String)var9.getKey()).equals("ResourceSeed")) {
                     var1.ai((String)var9.getValue());
                  }

                  if (((String)var9.getKey()).equals("Name")) {
                     var1.setName((String)var9.getValue());
                  }

                  if (((String)var9.getKey()).equals("TypeClass")) {
                     var1.aj((String)var9.getValue());
                  }

                  if (((String)var9.getKey()).equals("Type")) {
                     var1.ag(go.valueOf((String)var9.getValue()).K());
                  }

                  if (((String)var9.getKey()).equals("Resource")) {
                     var1.ag((String)var9.getValue());
                  }

                  if (((String)var9.getKey()).equals("FleetCoordination")) {
                     var1.b(((Number)var9.getValue()).doubleValue());
                  }

                  if (((String)var9.getKey()).equals("Hyperdrive")) {
                     var1.a(((Number)var9.getValue()).doubleValue());
                  }

                  if (((String)var9.getKey()).equals("Inventory")) {
                     var7.b("FreighterInventory", (Object)((eY)var9.getValue()));
                     var8 = true;
                  }

                  if (((String)var9.getKey()).equals("InventoryTech")) {
                     var7.b("FreighterInventory_TechOnly", (Object)((eY)var9.getValue()));
                     var8 = true;
                  }

                  if (((String)var9.getKey()).equals("InventoryCargo")) {
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
            } catch (GeneralSecurityException var12) {
               hc.a("Freighter restore error", var12);
               this.c("An error occured during restore.");
            }
         }

         return false;
      }
   }

   private void k() {
      File var1 = ej.b(this.aF == null ? null : this.aF.bS());
      if (var1 != null) {
         String var2;
         File var3;
         if (var1.isDirectory()) {
            var3 = var1;
            var2 = null;
         } else {
            var3 = var1.getParentFile();
            var2 = var1.getName();
         }

         if (this.aF != null) {
            if (this.aF.bS().isDirectory() && !this.aF.bS().equals(var3)) {
               this.aF = null;
            } else if (this.aF.bS().isFile() && !this.aF.bS().equals(var1)) {
               this.aF = null;
            }
         }

         if (this.aF == null) {
            hc.info("Loading storage: " + var3.getAbsolutePath());
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
            this.ad.setEnabled(false);
            this.O.setEnabledAt(13, false);
            this.aE.a((eY)null);
            this.aO = false;
            this.P.setText("(none)");
            this.Q.setText("(none)");
         } else {
            String var4 = fq.c(this.aF);
            nomanssave.aH.setProperty("GameStorage", var4);
            nomanssave.aH.setProperty("GameSaveDir", this.aF.bS().getAbsolutePath());
            hc.debug("Storage: " + var4);
            hc.debug("Save Path: " + this.aF.bS().getAbsolutePath());
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

                        if (var2.equals(this.aI[var6].K())) {
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

            this.ad.setEnabled(this.aN != null);
            this.O.setEnabledAt(13, this.aN != null);
            this.aE.a(this.aN);
            this.aO = false;
            this.P.setText(var4);
            this.Q.setText(this.aF.bS().getAbsolutePath());
         }

         this.R.setEnabled(true);
         this.S.setEnabled(true);
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

   public void b(String var1) {
      EventQueue.invokeLater(new z(this, var1));
   }

   public void c(String var1) {
      EventQueue.invokeLater(new A(this, var1));
   }

   private void l() {
      this.R.updateUI();
      this.S.updateUI();
      this.aL = false;
      this.aK = null;
      if (this.aJ < 0) {
         this.T.setText("(no file selected)");
         this.U.setText("(no file selected)");
         this.V.setText("(no file selected)");
         if (this.aH >= 0) {
            hc.info("No current save file found for " + this.aG[this.aH]);
            this.b("Save file not found for " + this.aG[this.aH]);
         }
      } else {
         try {
            this.T.setText(a(this.aI[this.aJ].lastModified()));
            this.U.setText(e(this.aI[this.aJ].getName()));
            this.V.setText(e(this.aI[this.aJ].getDescription()));
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
            this.aL = this.aI[this.aJ] instanceof F;
         } catch (IOException var19) {
            hc.error("Could not load save file: " + this.aI[this.aJ].K(), var19);
            this.aK = null;
         }
      }

      this.O.setSelectedIndex(0);
      eY var1;
      if (this.aK != null && (var1 = this.aK.H("PlayerStateData")) != null) {
         boolean var20 = this.aF.bW();
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
         boolean var13 = gj.n(var1);
         gj[] var14 = gj.o(var1);
         ge var15 = ge.m(var1);
         this.O.setEnabledAt(1, var21 != null);
         this.as.a(var21);
         this.O.setEnabledAt(2, var4.length > 0);
         this.at.a(var4, var5);
         this.O.setEnabledAt(3, var6.length > 0);
         this.au.a(var6, var7);
         this.O.setEnabledAt(4, var8.length > 0);
         this.av.a(var8);
         this.O.setEnabledAt(5, var9 != null);
         this.aw.c(var9);
         this.O.setEnabledAt(6, var9 != null);
         this.ax.a(var10);
         this.O.setEnabledAt(7, var11.length > 0);
         this.ay.a(var11);
         this.O.setEnabledAt(8, var13);
         this.az.a(var14);
         this.O.setEnabledAt(9, var15 != null);
         this.aA.a(var15);
         this.O.setEnabledAt(10, var12.length > 0);
         this.aB.a(var12);
         this.O.setEnabledAt(11, var21 != null);
         this.aC.a(var21);
         this.O.setEnabledAt(12, var21 != null);
         this.aD.a(var21);
         this.W.setEnabled(!(this.aI[this.aJ] instanceof F));
         this.X.setEnabled(true);
         this.Y.setEnabled(var20);
         this.Z.setEnabled(true);
         this.aa.setEnabled(true);
         this.ab.setEnabled(var20);
         Iterator var17 = this.ac.iterator();

         while(var17.hasNext()) {
            JMenuItem var16 = (JMenuItem)var17.next();
            var16.setEnabled(true);
         }
      } else {
         this.W.setEnabled(false);
         this.X.setEnabled(false);
         this.Y.setEnabled(false);
         this.Z.setEnabled(false);
         this.aa.setEnabled(false);
         this.ab.setEnabled(false);
         Iterator var3 = this.ac.iterator();

         while(var3.hasNext()) {
            JMenuItem var2 = (JMenuItem)var3.next();
            var2.setEnabled(false);
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
            String var1 = this.aI[this.aJ].b(this.aK);
            this.aI = this.aG[this.aH].bX();
            this.aJ = -1;

            for(int var2 = 0; var2 < this.aI.length; ++var2) {
               if (var1.equals(this.aI[var2].K())) {
                  this.aJ = var2;
                  break;
               }
            }

            this.aL = false;
            this.R.updateUI();
            this.S.updateUI();
            if (this.aJ < 0) {
               this.T.setText("(no file selected)");
               this.U.setText("(no file selected)");
               this.V.setText("(no file selected)");
            } else {
               this.T.setText(a(this.aI[this.aJ].lastModified()));
               this.U.setText(e(this.aI[this.aJ].getName()));
               this.V.setText(e(this.aI[this.aJ].getDescription()));
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
            String var3 = this.aF.a(var2, this.aK);
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

                     if (var3.equals(this.aI[var5].K())) {
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
               this.T.setText("(no file selected)");
               this.U.setText("(no file selected)");
               this.V.setText("(no file selected)");
            } else {
               this.T.setText(a(this.aI[this.aJ].lastModified()));
               this.U.setText(e(this.aI[this.aJ].getName()));
               this.V.setText(e(this.aI[this.aJ].getDescription()));
            }

            hc.info("Finished.");
         } catch (IOException var6) {
            hc.error("Could not write save file", var6);
            this.b("There was an error saving the file.");
            return;
         }
      }

   }

   public List g(int var1) {
      ArrayList var2 = new ArrayList();
      gz var4 = this.as.X();
      if (var4 != null) {
         var2.addAll((Collection)var4.cC().stream().filter((var1x) -> {
            return var1x.ay(var1);
         }).collect(Collectors.toList()));
      }

      gv[] var5 = this.at.aK();

      for(int var6 = 0; var6 < var5.length; ++var6) {
         gt var3;
         if ((var3 = var5[var6].dE()) != null && var3.ay(var1)) {
            var2.add(var3);
         }
      }

      gH[] var10 = this.au.aO();

      for(int var7 = 0; var7 < var10.length; ++var7) {
         var2.addAll((Collection)var10[var7].cC().stream().filter((var1x) -> {
            return var1x.ay(var1);
         }).collect(Collectors.toList()));
      }

      gm var11 = this.aw.Z();
      if (var11 != null) {
         var2.addAll((Collection)var11.cC().stream().filter((var1x) -> {
            return var1x.ay(var1);
         }).collect(Collectors.toList()));
      }

      gO[] var8 = this.ay.aT();

      for(int var9 = 0; var9 < var8.length; ++var9) {
         var2.addAll((Collection)var8[var9].cC().stream().filter((var1x) -> {
            return var1x.ay(var1);
         }).collect(Collectors.toList()));
      }

      ge var12 = this.aA.O();
      if (var12 != null) {
         var2.addAll((Collection)var12.cC().stream().filter((var1x) -> {
            return var1x.ay(var1);
         }).collect(Collectors.toList()));
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

   private static void a(Window var0) {
      SwingUtilities.updateComponentTreeUI(var0);
      Window[] var4;
      int var3 = (var4 = var0.getOwnedWindows()).length;

      for(int var2 = 0; var2 < var3; ++var2) {
         Window var1 = var4[var2];
         a(var1);
      }

   }

   private void s() {
      if (nomanssave.aD.d(this.N)) {
         nomanssave.aH.V();
         a((Window)this.N);
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
      boolean var12 = gj.n(var1);
      gj[] var13 = gj.o(var1);
      ge var14 = ge.m(var1);
      this.O.setEnabledAt(1, var2 != null);
      this.as.a(var2);
      this.O.setEnabledAt(2, var3.length > 0);
      this.at.a(var3, var4);
      this.O.setEnabledAt(3, var5.length > 0);
      this.au.a(var5, var7);
      this.O.setEnabledAt(4, var6.length > 0);
      this.av.a(var6);
      this.O.setEnabledAt(5, var8 != null);
      this.aw.c(var8);
      this.O.setEnabledAt(6, var8 != null);
      this.ax.a(var9);
      this.O.setEnabledAt(7, var10.length > 0);
      this.ay.a(var10);
      this.O.setEnabledAt(8, var12);
      this.az.a(var13);
      this.O.setEnabledAt(9, var14 != null);
      this.aA.a(var14);
      this.O.setEnabledAt(10, var11.length > 0);
      this.aB.a(var11);
      this.O.setEnabledAt(11, var2 != null);
      this.aC.a(var2);
      this.O.setEnabledAt(12, var2 != null);
      this.aD.a(var2);
   }

   private void u() {
      hc.info("Exporting JSON...");
      cK var1 = cK.aA();
      String var2 = this.aI[this.aJ].K() + ".json";
      var1.setCurrentDirectory(nomanssave.aH.cF);
      var1.setSelectedFile(new File(nomanssave.aH.cF, var2));
      if (var1.showSaveDialog(this.N) == 0) {
         try {
            File var3 = var1.getSelectedFile();
            if (!var3.getName().endsWith(".json")) {
               var3 = new File(var3.getParentFile(), var3.getName() + ".json");
            }

            if (var3.exists() && JOptionPane.showConfirmDialog(this.N, "Are you sure you want to overwrite this existing JSON file?", "Confirm", 2) != 0) {
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
            if (JOptionPane.showConfirmDialog(this.N, "Are you sure you want to update your current save data?", "Confirm", 2) != 0) {
               return;
            }

            File var2 = var1.getSelectedFile();
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

   public eV d(String var1) {
      return this.aK.d(var1);
   }

   public boolean D() {
      return this.aK.getValue("PlayerStateData.DifficultyState") != null;
   }

   public String E() {
      return this.aK.getValueAsString("PlayerStateData.DifficultyState.Settings.InventoryStackLimits.InventoryStackLimitsDifficulty");
   }

   public fn F() {
      String var1 = this.aK.getValueAsString("PlayerStateData.DifficultyState.Preset.DifficultyPresetType");
      fn var2;
      if (var1 != null) {
         fn[] var5;
         int var4 = (var5 = fn.values()).length;

         for(int var3 = 0; var3 < var4; ++var3) {
            var2 = var5[var3];
            if (var1.equalsIgnoreCase(var2.name())) {
               return var2;
            }
         }
      }

      var2 = this.aI[this.aJ].L();
      if (var2 == fn.lr) {
         String var6 = this.aK.getValueAsString("PlayerStateData.SeasonData.GameMode.PresetGameMode");
         if ("Normal".equals(var6)) {
            var2 = fn.lm;
         }

         if ("Creative".equals(var6)) {
            var2 = fn.lo;
         }

         if ("Survival".equals(var6)) {
            var2 = fn.ln;
         }

         if ("Ambient".equals(var6)) {
            var2 = fn.lp;
         }

         if ("Permadeath".equals(var6)) {
            var2 = fn.lq;
         }
      }

      return var2;
   }

   public void h(int var1) {
      eY var2 = this.aK.H("PlayerStateData");
      eV var3 = var2.d("Multitools");
      if (var3 != null && var3.size() != 0) {
         eY var4 = gR.az("multitool");
         if (var1 >= 0 && var1 < var3.size() && var4 != null) {
            var3.remove(var1);
            var3.add(var4);
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
      if (var3 != null && var3.size() != 0) {
         eY var4 = gR.az("ship");
         if (var1 >= 0 && var1 < var3.size() && var4 != null) {
            var3.remove(var1);
            var3.add(var4);
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

      if (var4 != null && var4.size() != 0) {
         eY var5 = gR.az("companion");
         if (var2 >= 0 && var2 < var4.size() && var5 != null) {
            var4.remove(var2);
            var4.add(var5);
            this.az.a(gj.o(var3));
         }
      }
   }

   public boolean j(int var1) {
      eV var2 = this.aK.d("PlayerStateData.FleetExpeditions");

      for(int var3 = 0; var3 < var2.size(); ++var3) {
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
      for(var4 = 0; var4 < var3.size(); ++var4) {
         eV var5 = var3.V(var4).d("AllFrigateIndices");
         if (var5.hasValue(new Integer(var1))) {
            this.c("This frigate is currently on a mission and cannot be deleted!");
            return gp.d(var2);
         }
      }

      if (var2 != null && var1 < var2.size()) {
         var2.ac(var1);

         for(int var9 = 0; var9 < var3.size(); ++var9) {
            eY var12 = var3.V(var9);
            eV var7 = var12.d("ActiveFrigateIndices");

            int var10;
            for(var10 = 0; var10 < var7.size(); ++var10) {
               if ((var4 = var7.Y(var10)) > var1) {
                  var7.a(var10, var4 - 1);
               }
            }

            var7 = var12.d("DamagedFrigateIndices");

            for(var10 = 0; var10 < var7.size(); ++var10) {
               if ((var4 = var7.Y(var10)) > var1) {
                  var7.a(var10, var4 - 1);
               }
            }

            var7 = var12.d("DestroyedFrigateIndices");

            for(var10 = 0; var10 < var7.size(); ++var10) {
               if ((var4 = var7.Y(var10)) > var1) {
                  var7.a(var10, var4 - 1);
               }
            }

            var7 = var12.d("AllFrigateIndices");

            for(var10 = 0; var10 < var7.size(); ++var10) {
               if ((var4 = var7.Y(var10)) > var1) {
                  var7.a(var10, var4 - 1);
               }
            }

            eV var8 = var12.d("Events");

            for(var10 = 0; var10 < var7.size(); ++var10) {
               eY var6 = var8.V(var10);
               var7 = var6.d("AffectedFrigateIndices");

               int var11;
               for(var11 = 0; var11 < var7.size(); ++var11) {
                  if ((var4 = var7.Y(var11)) > var1) {
                     var7.a(var11, var4 - 1);
                  }
               }

               var7 = var6.d("RepairingFrigateIndices");

               for(var11 = 0; var11 < var7.size(); ++var11) {
                  if ((var4 = var7.Y(var11)) > var1) {
                     var7.a(var11, var4 - 1);
                  }
               }

               var7 = var6.d("AffectedFrigateResponses");

               for(var11 = 0; var11 < var7.size(); ++var11) {
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

   public gp[] a(int var1, String var2) {
      eV var3 = this.aK.d("PlayerStateData.FleetFrigates");
      if (var3 != null && var1 < var3.size()) {
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
      for(var4 = 0; var4 < var3.size(); ++var4) {
         var5 = var3.V(var4).d("Objects");

         for(var6 = 0; var6 < var5.size(); ++var6) {
            String var7 = var5.V(var6).getValueAsString("ObjectID");
            if ("^PLANTER".equals(var7)) {
               ++var2;
            } else if ("^PLANTERMEGA".equals(var7)) {
               ++var2;
            }
         }
      }

      var5 = this.aK.d("PlayerStateData.MaintenanceInteractions");

      for(var6 = 0; var6 < var5.size(); ++var6) {
         eY var11 = var5.V(var6);
         eV var8 = var11.d("InventoryContainer.Slots");

         for(int var9 = 0; var9 < var8.size(); ++var9) {
            eY var10 = var8.V(var9);
            if ("^MAINT_FARM5".equals(var10.getValueAsString("Id"))) {
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
      this.N = new JFrame();
      ImageIcon var1 = a("UI-FILEICON.PNG");
      if (var1 != null) {
         this.N.setIconImage(var1.getImage());
      }

      this.N.setTitle("No Man's Sky Save Editor - 1.19.2 (VOYAGERS)");
      Rectangle var2 = new Rectangle(100, 100, 1100, 720);
      var2.x = nomanssave.aH.a("MainFrame.X", 100);
      var2.y = nomanssave.aH.a("MainFrame.Y", 100);
      var2.width = nomanssave.aH.a("MainFrame.Width", 1000);
      var2.height = nomanssave.aH.a("MainFrame.Height", 700);
      this.N.setBounds(var2);
      this.N.setDefaultCloseOperation(3);
      this.N.addWindowListener(new B(this));
      this.N.addComponentListener(new C(this));
      this.O = new JTabbedPane(1);
      this.N.getContentPane().add(this.O, "Center");
      ba var3 = new ba(new int[]{nomanssave.aH.cH, nomanssave.aH.cI, 0});
      this.O.addTab("Main", (Icon)null, var3, (String)null);
      var3.k("File Details");
      this.P = new JLabel();
      this.P.setText(this.aF == null ? "" : fq.c(this.aF));
      var3.a("Storage", this.P, 2);
      this.Q = new JLabel();
      this.Q.setText(this.aF == null ? "(no path selected)" : this.aF.bS().getAbsolutePath());
      var3.a("Save Path", this.Q, 2);
      this.R = new JComboBox();
      this.R.setModel(new D(this));
      this.R.setEnabled(this.aF != null);
      var3.a("Game Slot", (JComponent)this.R);
      this.S = new JComboBox();
      this.S.setEditable(false);
      this.S.setModel(new E(this));
      this.S.setEnabled(this.aF != null);
      var3.a("Save File", (JComponent)this.S);
      this.T = new JLabel();
      this.T.setText("(no file selected)");
      var3.a("Modified", this.T, 2);
      this.U = new JLabel();
      this.U.setText("(no file selected)");
      var3.a("Save Name", this.U, 2);
      this.V = new JLabel();
      this.V.setText("(no file selected)");
      var3.a("Description", this.V, 2);
      var3.Y();
      JPanel var4 = new JPanel();
      var4.setLayout(new FlowLayout(0, 0, 0));
      this.W = new JButton("Reload");
      this.W.setEnabled(false);
      this.W.addActionListener((var1x) -> {
         this.l();
      });
      var4.add(this.W);
      this.X = new JButton("Save Changes");
      this.X.setEnabled(false);
      this.X.addActionListener((var1x) -> {
         this.n();
      });
      var4.add(this.X);
      this.Y = new JButton("Save As");
      this.Y.setEnabled(false);
      this.Y.addActionListener((var1x) -> {
         this.o();
      });
      var4.add(this.Y);
      var3.a((String)null, var4, 2);
      this.as = new aJ(this);
      this.O.addTab("Exosuit", (Icon)null, this.as, (String)null);
      this.O.setEnabledAt(1, false);
      this.at = new dj(this);
      this.O.addTab("Multitool", (Icon)null, this.at, (String)null);
      this.O.setEnabledAt(2, false);
      this.au = new dN(this);
      this.O.addTab("Ships", (Icon)null, this.au, (String)null);
      this.O.setEnabledAt(3, false);
      this.av = new eb(this);
      this.O.addTab("Squadron", (Icon)null, this.av, (String)null);
      this.O.setEnabledAt(4, false);
      this.aw = new bd(this);
      this.O.addTab("Freighter", (Icon)null, this.aw, (String)null);
      this.O.setEnabledAt(5, false);
      this.ax = new bl(this);
      this.O.addTab("Frigates", (Icon)null, this.ax, (String)null);
      this.O.setEnabledAt(6, false);
      this.ay = new ep(this);
      this.O.addTab("Vehicles", (Icon)null, this.ay, (String)null);
      this.O.setEnabledAt(7, false);
      this.az = new X(this);
      this.O.addTab("Companions", (Icon)null, this.az, (String)null);
      this.O.setEnabledAt(8, false);
      this.aA = new I(this);
      this.O.addTab("Bases & Storage", (Icon)null, this.aA, (String)null);
      this.O.setEnabledAt(9, false);
      this.aB = new dE(this);
      this.O.addTab("Settlements", (Icon)null, this.aB, (String)null);
      this.O.setEnabledAt(10, false);
      this.aC = new ap(this);
      this.O.addTab("Discovery", (Icon)null, this.aC, (String)null);
      this.O.setEnabledAt(11, false);
      this.aD = new bE(this);
      this.O.addTab("Milestones / Reputation", (Icon)null, this.aD, (String)null);
      this.O.setEnabledAt(12, false);
      this.aE = new c(this);
      this.O.addTab("Account", (Icon)null, this.aE, (String)null);
      this.O.setEnabledAt(13, false);
      this.O.addChangeListener((var1x) -> {
         if (this.O.getSelectedIndex() == 12) {
            this.aD.aa();
         }

         if (this.aF != null && this.aO && this.aP) {
            int var2 = JOptionPane.showConfirmDialog(this.N, "Save account data?", "Save", 0);
            this.aP = var2 == 0;
            if (this.aP) {
               this.m();
            }
         }

      });
      JMenuBar var5 = new JMenuBar();
      this.N.setJMenuBar(var5);
      JMenu var6 = new JMenu("File");
      var5.add(var6);
      JMenuItem var7 = new JMenuItem("Open File/Path");
      var7.setAccelerator(KeyStroke.getKeyStroke(79, 2));
      var7.addActionListener((var1x) -> {
         this.k();
      });
      var6.add(var7);
      this.Z = new JMenuItem("Reload File");
      this.Z.setEnabled(false);
      this.Z.setAccelerator(KeyStroke.getKeyStroke(82, 2));
      this.Z.addActionListener((var1x) -> {
         this.l();
      });
      var6.add(this.Z);
      this.aa = new JMenuItem("Save File");
      this.aa.setEnabled(false);
      this.aa.setAccelerator(KeyStroke.getKeyStroke(83, 2));
      this.aa.addActionListener((var1x) -> {
         Component var2 = this.N.getFocusOwner();
         if (var2 instanceof G) {
            ((G)var2).N();
         }

         this.n();
      });
      var6.add(this.aa);
      this.ab = new JMenuItem("Save File As");
      this.ab.setEnabled(false);
      this.ab.addActionListener((var1x) -> {
         Component var2 = this.N.getFocusOwner();
         if (var2 instanceof G) {
            ((G)var2).N();
         }

         this.o();
      });
      var6.add(this.ab);
      var6.addSeparator();
      JMenuItem var8 = new JMenuItem("Exit");
      var8.addActionListener((var1x) -> {
         if (this.aL || this.aO) {
            int var2 = JOptionPane.showConfirmDialog(this.N, "Save data before closing?", "Save", 0);
            if (var2 == 0) {
               if (this.aL) {
                  this.n();
               }

               if (this.aO) {
                  this.m();
               }
            }
         }

         this.N.dispose();
      });
      var6.add(var8);
      JMenu var9 = new JMenu("Edit");
      var5.add(var9);
      this.ac = new ArrayList();
      JMenuItem var10 = new JMenuItem("Edit Raw JSON");
      var10.addActionListener((var1x) -> {
         this.q();
      });
      var9.add(var10);
      this.ac.add(var10);
      JMenuItem var11 = new JMenuItem("Export JSON");
      var11.addActionListener((var1x) -> {
         this.u();
      });
      var9.add(var11);
      this.ac.add(var11);
      JMenuItem var12 = new JMenuItem("Import JSON");
      var12.addActionListener((var1x) -> {
         this.v();
      });
      var9.add(var12);
      this.ac.add(var12);
      JMenuItem var13 = new JMenuItem("Coordinate Viewer");
      var13.addActionListener((var1x) -> {
         this.p();
      });
      var9.add(var13);
      this.ac.add(var13);
      JCheckBoxMenuItem var14 = new JCheckBoxMenuItem("Test Mode");
      var14.setSelected(en.aS());
      var14.addActionListener((var2x) -> {
         boolean var3 = var14.isSelected();
         if (var3) {
            int var4 = JOptionPane.showConfirmDialog(this.N, "This mode removes any restrictions imposed by the editor.\nUSE WITH CAUTION: Changes made in test mode may not work in game.", "Test Mode", 2);
            if (var4 == 2) {
               var14.setSelected(false);
               return;
            }
         }

         en.c(var3);
      });
      var9.add(var14);
      var9.addSeparator();
      JMenuItem var15 = new JMenuItem("Recharge All Technology");
      var15.addActionListener((var1x) -> {
         this.w();
      });
      var9.add(var15);
      this.ac.add(var15);
      JMenuItem var16 = new JMenuItem("Refill All Stacks");
      var16.addActionListener((var1x) -> {
         this.x();
      });
      var9.add(var16);
      this.ac.add(var16);
      JMenuItem var17 = new JMenuItem("Recharge Base Planters");
      var17.addActionListener((var1x) -> {
         this.G();
      });
      var9.add(var17);
      this.ac.add(var17);
      JMenuItem var18 = new JMenuItem("Expand All Inventories");
      var18.addActionListener((var1x) -> {
         this.A();
      });
      var9.add(var18);
      this.ac.add(var18);
      JMenuItem var19 = new JMenuItem("Enable All Slots");
      var19.addActionListener((var1x) -> {
         this.y();
      });
      var9.add(var19);
      this.ac.add(var19);
      JMenuItem var20 = new JMenuItem("Repair All Slots / Technology");
      var20.addActionListener((var1x) -> {
         this.z();
      });
      var9.add(var20);
      this.ac.add(var20);
      var9.addSeparator();
      this.ad = new JMenuItem("Edit Account JSON");
      this.ad.addActionListener((var1x) -> {
         this.r();
      });
      var9.add(this.ad);
      Iterator var22 = this.ac.iterator();

      while(var22.hasNext()) {
         JMenuItem var21 = (JMenuItem)var22.next();
         var21.setEnabled(false);
      }

      this.ad.setEnabled(false);
      JMenu var25 = new JMenu("View");
      var5.add(var25);
      JMenuItem var26 = new JMenuItem("Settings");
      var26.addActionListener((var1x) -> {
         this.s();
      });
      var25.add(var26);
      var5.add(Box.createHorizontalGlue());
      JMenu var23 = new JMenu("Help");
      var5.add(var23);
      JMenuItem var24 = new JMenuItem("About");
      var24.addActionListener((var1x) -> {
         a.a(this.N);
      });
      var23.add(var24);
      if (this.aF == null) {
         EventQueue.invokeLater(new v(this));
      } else if (this.aN != null) {
         this.ad.setEnabled(true);
         this.O.setEnabledAt(13, true);
         this.aE.a(this.aN);
         this.aO = false;
      }

      this.N.pack();
   }

   private static String e(String var0) {
      return var0 == null ? "(unknown)" : var0;
   }

   // $FF: synthetic method
   static boolean a(Application var0) {
      return var0.aQ;
   }

   // $FF: synthetic method
   static fq b(Application var0) {
      return var0.aF;
   }

   // $FF: synthetic method
   static void a(Application var0, boolean var1) {
      var0.aR = var1;
   }

   // $FF: synthetic method
   static void b(Application var0, boolean var1) {
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
   static void c(Application var0, boolean var1) {
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
   static void d(Application var0, boolean var1) {
      var0.aU = var1;
   }

   // $FF: synthetic method
   Application(boolean var1, Application var2) {
      this(var1);
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
   static JFrame h(Application var0) {
      return var0.N;
   }

   // $FF: synthetic method
   static int[] I() {
      int[] var10000 = aY;
      if (var10000 != null) {
         return var10000;
      } else {
         int[] var0 = new int[gl.values().length];

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
   static boolean i(Application var0) {
      return var0.aL;
   }

   // $FF: synthetic method
   static boolean j(Application var0) {
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
   static void e(Application var0, boolean var1) {
      var0.aQ = var1;
   }

   // $FF: synthetic method
   static void m(Application var0) {
      var0.f();
   }

   // $FF: synthetic method
   static JComboBox n(Application var0) {
      return var0.R;
   }

   // $FF: synthetic method
   static void f(Application var0, boolean var1) {
      var0.aL = var1;
   }

   // $FF: synthetic method
   static void a(Application var0, int var1) {
      var0.e(var1);
   }

   // $FF: synthetic method
   static JComboBox o(Application var0) {
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
