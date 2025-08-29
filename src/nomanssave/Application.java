package nomanssave;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
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
import javax.swing.event.ChangeEvent;

public class Application {
  public static final String VERSION = "1.18.1";
  
  public static final String RELEASE = "RELICS";
  
  private static final String J = "https://github.com/goatfungus/NMSSaveEditor";
  
  private static final String K = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_";
  
  private static Application L;
  
  private static HashMap M = new HashMap<>();
  
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
  
  private boolean aQ = false;
  
  private boolean aR = false;
  
  private boolean aS = false;
  
  private boolean aT = false;
  
  private boolean aU = false;
  
  private fe aV;
  
  private fe aW;
  
  private fR aX;
  
  public static String a(long paramLong) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a, E MMM d, yyyy");
    return simpleDateFormat.format(new Date(paramLong));
  }
  
  public static String b(long paramLong) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM d, HH:mm");
    return simpleDateFormat.format(new Date(paramLong));
  }
  
  private static String a(String paramString1, String paramString2) {
    if (paramString1 == null)
      return paramString2; 
    StringBuffer stringBuffer = new StringBuffer();
    for (byte b = 0; b < paramString1.length(); b++) {
      char c1 = paramString1.charAt(b);
      if ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_".indexOf(c1) >= 0) {
        stringBuffer.append(c1);
      } else if (Character.isWhitespace(c1)) {
        stringBuffer.append('_');
      } 
    } 
    return (stringBuffer.length() != 0) ? stringBuffer.toString() : paramString2;
  }
  
  public static Application e() {
    return L;
  }
  
  public static void main(String[] paramArrayOfString) {
    boolean bool;
    byte b = 0;
    if (paramArrayOfString.length > b && paramArrayOfString[b].equals("-autoupdate")) {
      b++;
      bool = true;
    } else {
      bool = false;
    } 
    aH.init(!bool);
    hc.info("Starting Editor...");
    (new Thread(() -> cK.aA())).start();
    EventQueue.invokeLater(new w(bool));
  }
  
  public static ImageIcon a(String paramString) {
    BufferedImage bufferedImage = (BufferedImage)M.get(paramString);
    if (bufferedImage == null) {
      InputStream inputStream = Application.class.getResourceAsStream("icons/" + paramString);
      if (inputStream != null)
        try {
          bufferedImage = ImageIO.read(inputStream);
          M.put(paramString, bufferedImage);
        } catch (IOException iOException) {
          hc.info("Error loading icon: " + paramString);
        } catch (RuntimeException runtimeException) {
          hc.info("Error loading icon: " + paramString);
        }  
    } 
    return (bufferedImage == null) ? null : new ImageIcon(bufferedImage);
  }
  
  public static ImageIcon a(String paramString, int paramInt1, int paramInt2) {
    BufferedImage bufferedImage = (BufferedImage)M.get(paramString);
    if (bufferedImage == null) {
      InputStream inputStream = Application.class.getResourceAsStream("icons/" + paramString);
      if (inputStream != null)
        try {
          bufferedImage = ImageIO.read(inputStream);
          M.put(paramString, bufferedImage);
        } catch (IOException iOException) {
          hc.info("Error loading icon: " + paramString);
        } catch (RuntimeException runtimeException) {
          hc.info("Error loading icon: " + paramString);
        }  
    } 
    return (bufferedImage == null) ? null : new ImageIcon(bufferedImage.getScaledInstance(paramInt1, paramInt2, 4));
  }
  
  private void f() {
    if (this.aR)
      this.aR = false; 
    if (this.aS) {
      this.aS = false;
      byte b1 = (this.aH < 0) ? -1 : this.aG[this.aH].getIndex();
      this.aG = (ft[])Arrays.<ft>asList(this.aF.bU()).stream().filter(paramft -> !(paramft.getIndex() != paramInt && paramft.isEmpty())).toArray(paramInt -> new ft[paramInt]);
      this.aH = -1;
      for (byte b2 = 0; b2 < this.aG.length; b2++) {
        if (this.aG[b2].getIndex() == b1) {
          this.aH = b2;
          break;
        } 
      } 
      if (b1 && this.aH < 0) {
        hc.warn("Slot " + (b1 + 1) + " was not reloaded!");
        this.aI = new fs[0];
        this.aJ = -1;
      } 
      this.R.updateUI();
    } 
    this.aT &= (this.aH >= 0) ? 1 : 0;
    if (this.aT) {
      this.aT = false;
      String str = (this.aJ < 0) ? null : this.aI[this.aJ].K();
      long l = (this.aJ < 0) ? Long.MIN_VALUE : this.aI[this.aJ].lastModified();
      fn fn = (this.aJ < 0) ? null : this.aI[this.aJ].L();
      this.aI = this.aG[this.aH].bX();
      this.aJ = -1;
      int i;
      for (i = 0; i < this.aI.length; i++) {
        if (this.aI[i].K().equals(str)) {
          this.aJ = i;
          break;
        } 
      } 
      if (str != null && this.aJ < 0) {
        this.aU = false;
        i = JOptionPane.showConfirmDialog(this.N, "Save file has been deleted externally. Would you like to reload?\nNOTE: All changes made in the editor will be lost.", "Reload File", 0);
        if (i == 0) {
          this.aJ = 0;
          l();
        } else {
          fs[] arrayOfFs = new fs[this.aI.length + 1];
          arrayOfFs[0] = new F(this, str, l, fn, this.aK);
          System.arraycopy(this.aI, 0, arrayOfFs, 1, this.aI.length);
          this.aI = arrayOfFs;
          this.aJ = 0;
        } 
      } 
      this.S.updateUI();
    } 
    this.aU &= (this.aJ >= 0) ? 1 : 0;
    if (this.aU) {
      this.aU = false;
      int i = JOptionPane.showConfirmDialog(this.N, "Save file has been modified externally. Would you like to reload?\nNOTE: All changes made in the editor will be lost.", "Reload File", 0);
      if (i == 0) {
        l();
      } else {
        this.aL = true;
      } 
    } 
  }
  
  private Application(boolean paramBoolean) {
    this.aV = ((paramString, paramObject1, paramObject2) -> {
        String str;
        this.aO = true;
        this.aP = true;
        if (paramObject2 == null) {
          hc.info("Removing " + paramString);
          return;
        } 
        if (paramObject2 instanceof eY) {
          str = "[OBJECT]";
        } else if (paramObject2 instanceof eV) {
          str = "[ARRAY]";
        } else {
          str = paramObject2.toString();
        } 
        hc.info("Setting " + paramString + ": " + str);
      });
    this.aW = ((paramString, paramObject1, paramObject2) -> {
        String str;
        this.aL = true;
        if (paramString.startsWith("PlayerStateData.Multitools")) {
          int i = this.aK.J("PlayerStateData.ActiveMultioolIndex");
          if (paramString.startsWith("PlayerStateData.Multitools[" + i + "].Store.")) {
            eY eY1 = this.aK.H("PlayerStateData.Multitools[" + i + "].Store");
            this.aK.b("PlayerStateData.WeaponInventory", eY1.bE());
          } else if (paramString.equals("PlayerStateData.Multitools[" + i + "].Seed[1]")) {
            this.aK.b("PlayerStateData.CurrentWeapon.GenerationSeed[1]", paramObject2);
          } else if (paramString.equals("PlayerStateData.Multitools[" + i + "].Resource.Filename")) {
            this.aK.b("PlayerStateData.CurrentWeapon.Filename", paramObject2);
          } 
        } 
        if (paramObject2 == null) {
          hc.info("Removing " + paramString);
          return;
        } 
        if (paramObject2 instanceof eY) {
          str = "OBJECT";
        } else if (paramObject2 instanceof eV) {
          str = "ARRAY[" + ((eV)paramObject2).size() + "]";
        } else {
          str = fh.b(paramObject2, false);
        } 
        hc.info("Setting " + paramString + ": " + str);
      });
    this.aX = new u(this);
    String str1 = aH.getProperty("GameStorage");
    String str2 = aH.getProperty("GameSaveDir");
    this.aF = (str2 == null) ? null : fq.a(str1, new File(str2), this.aX);
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
      if (str1 == null) {
        str1 = fq.c(this.aF);
        aH.setProperty("GameStorage", str1);
      } 
      hc.debug("Storage: " + str1);
      hc.debug("Save Path: " + this.aF.bS().getAbsolutePath());
      this.aM = null;
      this.aN = null;
      try {
        hc.info("Reading account data...");
        this.aM = this.aF.bT();
        this.aN = (this.aM == null) ? null : this.aM.M();
        if (this.aN != null)
          this.aN.a(this.aV); 
      } catch (IOException iOException) {
        hc.a("Error reading account data", iOException);
      } 
    } 
    initialize();
    (new x(this, paramBoolean)).start();
  }
  
  public JFrame g() {
    return this.N;
  }
  
  public void a(gH paramgH) {
    File file = aH.cF;
    if (!file.exists() && !file.mkdir())
      file = aH.cD; 
    cT cT = cT.aC();
    String str = a(paramgH.getName(), "Ship");
    cT.setCurrentDirectory(file);
    cT.setSelectedFile(new File(file, str));
    if (cT.showSaveDialog(this.N) == 0)
      try {
        File file1 = cT.getSelectedFile();
        if (!file1.getName().endsWith(".sh0"))
          file1 = new File(file1.getParentFile(), String.valueOf(file1.getName()) + ".sh0"); 
        paramgH.a(file1, cT.aw());
      } catch (RuntimeException runtimeException) {
        hc.a("Ship export error", runtimeException);
        c("An error occured during export.");
      } catch (IOException iOException) {
        hc.a("Ship export error", iOException);
        c("An error occured during export.");
      }  
  }
  
  public void a(gv paramgv) {
    File file = aH.cF;
    if (!file.exists() && !file.mkdir())
      file = aH.cD; 
    cv cv = cv.ax();
    String str = a(paramgv.getName(), "Weapon");
    cv.setCurrentDirectory(file);
    cv.setSelectedFile(new File(file, str));
    if (cv.showSaveDialog(this.N) == 0)
      try {
        File file1 = cv.getSelectedFile();
        if (!file1.getName().endsWith(".wp0"))
          file1 = new File(file1.getParentFile(), String.valueOf(file1.getName()) + ".wp0"); 
        paramgv.j(file1);
      } catch (RuntimeException runtimeException) {
        hc.a("Weapon export error", runtimeException);
        c("An error occured during export.");
      } catch (IOException iOException) {
        hc.a("Weapon export error", iOException);
        c("An error occured during export.");
      }  
  }
  
  public void a(gj paramgj) {
    File file = aH.cF;
    if (!file.exists() && !file.mkdir())
      file = aH.cD; 
    String str1 = "." + paramgj.cL().name().toLowerCase();
    cp cp = cp.at();
    String str2 = a(paramgj.getName(), paramgj.cL().name());
    cp.setCurrentDirectory(file);
    cp.setSelectedFile(new File(file, str2));
    if (cp.showSaveDialog(this.N) == 0)
      try {
        File file1 = cp.getSelectedFile();
        if (!file1.getName().endsWith(str1))
          file1 = new File(file1.getParentFile(), String.valueOf(file1.getName()) + str1); 
        paramgj.j(file1);
      } catch (RuntimeException runtimeException) {
        hc.a("Companion export error", runtimeException);
        c("An error occured during export.");
      } catch (IOException iOException) {
        hc.a("Companion export error", iOException);
        c("An error occured during export.");
      }  
  }
  
  public gH h() {
    eY eY1;
    if (this.aK == null || (eY1 = this.aK.H("PlayerStateData")) == null)
      return null; 
    File file = aH.cF.exists() ? aH.cF : aH.cD;
    cT cT = cT.aC();
    cT.setCurrentDirectory(file);
    if (cT.showOpenDialog(this.N) == 0)
      try {
        File file1 = cT.getSelectedFile();
        gH gH = gH.c(eY1, file1);
        this.aL = true;
        return gH;
      } catch (RuntimeException runtimeException) {
        hc.a("Ship import error", runtimeException);
        c("An error occured during import.");
      } catch (IOException iOException) {
        hc.a("Ship import error", iOException);
        c("An error occured during import.");
      }  
    return null;
  }
  
  public gv i() {
    eY eY1;
    if (this.aK == null || (eY1 = this.aK.H("PlayerStateData")) == null)
      return null; 
    File file = aH.cF.exists() ? aH.cF : aH.cD;
    cv cv = cv.ax();
    cv.setCurrentDirectory(file);
    if (cv.showOpenDialog(this.N) == 0)
      try {
        File file1 = cv.getSelectedFile();
        gv gv = gv.b(eY1, file1);
        this.aL = true;
        return gv;
      } catch (RuntimeException runtimeException) {
        hc.a("Weapon import error", runtimeException);
        c("An error occured during import.");
      } catch (IOException iOException) {
        hc.a("Weapon import error", iOException);
        c("An error occured during import.");
      }  
    return null;
  }
  
  public gj j() {
    eY eY1;
    if (this.aK == null || (eY1 = this.aK.H("PlayerStateData")) == null)
      return null; 
    File file = aH.cF.exists() ? aH.cF : aH.cD;
    cp cp = cp.at();
    cp.setCurrentDirectory(file);
    if (cp.showOpenDialog(this.N) == 0)
      try {
        File file1 = cp.getSelectedFile();
        gj gj = gj.a(eY1, file1);
        this.aL = true;
        return gj;
      } catch (RuntimeException runtimeException) {
        hc.a("Companion import error", runtimeException);
        c("An error occured during import.");
      } catch (IOException iOException) {
        hc.a("Companion import error", iOException);
        c("An error occured during import.");
      }  
    return null;
  }
  
  public void a(gf paramgf) {
    File file = aH.cE;
    if (!file.exists() && !file.mkdir())
      file = aH.cD; 
    cl cl = cl.ar();
    String str = a(paramgf.getName(), "Base");
    cl.setCurrentDirectory(file);
    cl.setSelectedFile(new File(file, str));
    if (cl.showSaveDialog(this.N) == 0)
      try {
        File file1 = cl.getSelectedFile();
        if (!file1.getName().endsWith(".pb3"))
          file1 = new File(file1.getParentFile(), String.valueOf(file1.getName()) + ".pb3"); 
        if (file1.exists() && JOptionPane.showConfirmDialog(this.N, "Are you sure you want to overwrite this existing backup file?", "Confirm", 2) != 0)
          return; 
        gS.d(paramgf.cH(), file1);
      } catch (RuntimeException runtimeException) {
        hc.a("Base backup error", runtimeException);
        c("An error occured during backup.");
      } catch (IOException iOException) {
        hc.a("Base backup error", iOException);
        c("An error occured during backup.");
      } catch (GeneralSecurityException generalSecurityException) {
        hc.a("Base backup error", generalSecurityException);
        c("An error occured during backup.");
      }  
  }
  
  public boolean b(gf paramgf) {
    File file = aH.cE.exists() ? aH.cE : aH.cD;
    cl cl = cl.ar();
    cl.setCurrentDirectory(file);
    if (cl.showOpenDialog(this.N) == 0)
      try {
        if (JOptionPane.showConfirmDialog(this.N, "Are you sure you want to overwrite your existing base?", "Confirm", 2) != 0)
          return false; 
        File file1 = cl.getSelectedFile();
        gS.e(paramgf.cH(), file1);
        this.aL = true;
        return true;
      } catch (IOException iOException) {
        hc.a("Base restore error", iOException);
        c("An error occured during backup.");
      } catch (GeneralSecurityException generalSecurityException) {
        hc.a("Base restore error", generalSecurityException);
        c("An error occured during backup.");
      }  
    return false;
  }
  
  public void a(gm paramgm) {
    gn gn = paramgm.cZ();
    if (gn == null)
      return; 
    File file = aH.cE;
    if (!file.exists() && !file.mkdir())
      file = aH.cD; 
    cs cs = cs.av();
    String str = a(gn.getName(), "Freighter");
    cs.setCurrentDirectory(file);
    cs.setSelectedFile(new File(file, str));
    if (cs.showSaveDialog(this.N) == 0)
      try {
        File file1 = cs.getSelectedFile();
        if (!file1.getName().endsWith(".fb3"))
          file1 = new File(file1.getParentFile(), String.valueOf(file1.getName()) + ".fb3"); 
        if (file1.exists() && JOptionPane.showConfirmDialog(this.N, "Are you sure you want to overwrite this existing backup file?", "Confirm", 2) != 0)
          return; 
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("HomeSeed", paramgm.cU());
        hashMap.put("ResourceSeed", paramgm.cV());
        hashMap.put("Name", paramgm.getName());
        hashMap.put("TypeClass", paramgm.cW());
        hashMap.put("Resource", paramgm.cT());
        hashMap.put("FleetCoordination", Double.valueOf(paramgm.cY()));
        hashMap.put("Hyperdrive", Double.valueOf(paramgm.cX()));
        eY eY1 = this.aK.H("PlayerStateData");
        eY eY2 = eY1.H("FreighterInventory").bE();
        eY eY3 = eY1.H("FreighterInventory_TechOnly").bE();
        eY eY4 = eY1.H("FreighterInventory_Cargo").bE();
        if (!cs.aw()) {
          eV eV = eY2.d("Slots");
          byte b;
          for (b = 0; b < eV.size(); b++) {
            eY eY5 = eV.V(b);
            if (!eY5.getValueAsString("Type.InventoryType").equals("Technology"))
              eV.ac(b--); 
          } 
          eV = eY4.d("Slots");
          for (b = 0; b < eV.size(); b++) {
            eY eY5 = eV.V(b);
            if (!eY5.getValueAsString("Type.InventoryType").equals("Technology"))
              eV.ac(b--); 
          } 
        } 
        hashMap.put("Inventory", eY2);
        hashMap.put("InventoryTech", eY3);
        hashMap.put("InventoryCargo", eY4);
        gS.a(gn.cH(), hashMap, file1);
      } catch (RuntimeException runtimeException) {
        hc.a("Freighter backup error", runtimeException);
        c("An error occured during backup.");
      } catch (IOException iOException) {
        hc.a("Freighter backup error", iOException);
        c("An error occured during backup.");
      } catch (GeneralSecurityException generalSecurityException) {
        hc.a("Freighter backup error", generalSecurityException);
        c("An error occured during backup.");
      }  
  }
  
  public boolean b(gm paramgm) {
    gn gn = paramgm.cZ();
    if (gn == null)
      return false; 
    File file = aH.cE.exists() ? aH.cE : aH.cD;
    cs cs = cs.av();
    cs.setCurrentDirectory(file);
    if (cs.showOpenDialog(this.N) == 0)
      try {
        if (JOptionPane.showConfirmDialog(this.N, "Are you sure you want to overwrite your existing freighter?", "Confirm", 2) != 0)
          return false; 
        File file1 = cs.getSelectedFile();
        HashMap<Object, Object> hashMap = new HashMap<>();
        gS.b(gn.cH(), hashMap, file1);
        eY eY1 = this.aK.H("PlayerStateData");
        boolean bool = false;
        for (Map.Entry<Object, Object> entry : hashMap.entrySet()) {
          if (((String)entry.getKey()).equals("HomeSeed"))
            paramgm.ah((String)entry.getValue()); 
          if (((String)entry.getKey()).equals("ResourceSeed"))
            paramgm.ai((String)entry.getValue()); 
          if (((String)entry.getKey()).equals("Name"))
            paramgm.setName((String)entry.getValue()); 
          if (((String)entry.getKey()).equals("TypeClass"))
            paramgm.aj((String)entry.getValue()); 
          if (((String)entry.getKey()).equals("Type"))
            paramgm.ag(go.valueOf((String)entry.getValue()).K()); 
          if (((String)entry.getKey()).equals("Resource"))
            paramgm.ag((String)entry.getValue()); 
          if (((String)entry.getKey()).equals("FleetCoordination"))
            paramgm.b(((Number)entry.getValue()).doubleValue()); 
          if (((String)entry.getKey()).equals("Hyperdrive"))
            paramgm.a(((Number)entry.getValue()).doubleValue()); 
          if (((String)entry.getKey()).equals("Inventory")) {
            eY1.b("FreighterInventory", entry.getValue());
            bool = true;
          } 
          if (((String)entry.getKey()).equals("InventoryTech")) {
            eY1.b("FreighterInventory_TechOnly", entry.getValue());
            bool = true;
          } 
          if (((String)entry.getKey()).equals("InventoryCargo")) {
            eY1.b("FreighterInventory_Cargo", entry.getValue());
            bool = true;
          } 
        } 
        if (bool)
          paramgm = gm.p(eY1); 
        this.aw.c(paramgm);
        this.aL = true;
        return true;
      } catch (IOException iOException) {
        hc.a("Freighter restore error", iOException);
        c("An error occured during restore.");
      } catch (GeneralSecurityException generalSecurityException) {
        hc.a("Freighter restore error", generalSecurityException);
        c("An error occured during restore.");
      }  
    return false;
  }
  
  private void k() {
    File file = ej.b((this.aF == null) ? null : this.aF.bS());
    if (file != null) {
      String str;
      File file1;
      if (file.isDirectory()) {
        file1 = file;
        str = null;
      } else {
        file1 = file.getParentFile();
        str = file.getName();
      } 
      if (this.aF != null)
        if (this.aF.bS().isDirectory() && !this.aF.bS().equals(file1)) {
          this.aF = null;
        } else if (this.aF.bS().isFile() && !this.aF.bS().equals(file)) {
          this.aF = null;
        }  
      if (this.aF == null) {
        hc.info("Loading storage: " + file1.getAbsolutePath());
        this.aF = fq.a(file1, this.aX);
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
        this.aE.a(null);
        this.aO = false;
        this.P.setText("(none)");
        this.Q.setText("(none)");
      } else {
        String str1 = fq.c(this.aF);
        aH.setProperty("GameStorage", str1);
        aH.setProperty("GameSaveDir", this.aF.bS().getAbsolutePath());
        hc.debug("Storage: " + str1);
        hc.debug("Save Path: " + this.aF.bS().getAbsolutePath());
        this.aG = this.aF.bV();
        this.aH = -1;
        this.aI = new fs[0];
        this.aJ = -1;
        if (str != null)
          for (byte b = 0; b < this.aG.length; b++) {
            if (this.aF.W(str) == this.aG[b].getIndex()) {
              this.aH = b;
              this.aI = this.aG[b].bX();
              for (byte b1 = 0; b1 < this.aI.length; b1++) {
                if (str.equals(this.aI[b1].K())) {
                  this.aJ = b1;
                  break;
                } 
              } 
              break;
            } 
          }  
        this.aM = null;
        this.aN = null;
        try {
          this.aM = this.aF.bT();
          this.aN = (this.aM == null) ? null : this.aM.M();
          if (this.aN != null)
            this.aN.a(this.aV); 
        } catch (IOException iOException) {
          hc.a("Error reading account data", iOException);
        } 
        this.ad.setEnabled((this.aN != null));
        this.O.setEnabledAt(13, (this.aN != null));
        this.aE.a(this.aN);
        this.aO = false;
        this.P.setText(str1);
        this.Q.setText(this.aF.bS().getAbsolutePath());
      } 
      this.R.setEnabled(true);
      this.S.setEnabled(true);
      if (this.aJ > 0)
        c("The save file you have selected is not the most recent."); 
      l();
    } 
  }
  
  private void e(int paramInt) {
    this.aH = paramInt;
    if (this.aH < 0) {
      this.aI = new fs[0];
      this.aJ = -1;
    } else {
      this.aI = this.aG[this.aH].bX();
      this.aJ = (this.aI.length > 0) ? 0 : -1;
    } 
    l();
  }
  
  private void f(int paramInt) {
    this.aJ = paramInt;
    l();
  }
  
  public void b(String paramString) {
    EventQueue.invokeLater(new z(this, paramString));
  }
  
  public void c(String paramString) {
    EventQueue.invokeLater(new A(this, paramString));
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
        b("Save file not found for " + this.aG[this.aH]);
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
        } catch (eX eX) {
          hc.info("  Error parsing JSON: " + eX.getMessage());
        } 
        hc.info("Finished.");
        this.aL = this.aI[this.aJ] instanceof F;
      } catch (IOException iOException) {
        hc.error("Could not load save file: " + this.aI[this.aJ].K(), iOException);
        this.aK = null;
      } 
    } 
    this.O.setSelectedIndex(0);
    eY eY1;
    if (this.aK == null || (eY1 = this.aK.H("PlayerStateData")) == null) {
      this.W.setEnabled(false);
      this.X.setEnabled(false);
      this.Y.setEnabled(false);
      this.Z.setEnabled(false);
      this.aa.setEnabled(false);
      this.ab.setEnabled(false);
      for (JMenuItem jMenuItem : this.ac)
        jMenuItem.setEnabled(false); 
      this.as.a((gz)null);
      this.at.a(new gv[0], (gB)null);
      this.au.a(new gH[0], (gC)null);
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
      if (this.aJ >= 0)
        if (this.aK == null) {
          b("There was an error loading the file.");
        } else {
          b("Save file corrupted");
        }  
    } else {
      boolean bool1 = this.aF.bW();
      gz gz = gz.w(eY1);
      gv[] arrayOfGv = gv.v(eY1);
      gB gB = gB.x(eY1);
      gH[] arrayOfGH = gH.C(eY1);
      gC gC = gC.y(eY1);
      gM[] arrayOfGM = gM.D(eY1);
      gm gm = gm.p(eY1);
      gp[] arrayOfGp = gp.q(eY1);
      gO[] arrayOfGO = gO.E(eY1);
      gE[] arrayOfGE = gE.z(eY1);
      boolean bool2 = gj.n(eY1);
      gj[] arrayOfGj = gj.o(eY1);
      ge ge = ge.m(eY1);
      this.O.setEnabledAt(1, (gz != null));
      this.as.a(gz);
      this.O.setEnabledAt(2, (arrayOfGv.length > 0));
      this.at.a(arrayOfGv, gB);
      this.O.setEnabledAt(3, (arrayOfGH.length > 0));
      this.au.a(arrayOfGH, gC);
      this.O.setEnabledAt(4, (arrayOfGM.length > 0));
      this.av.a(arrayOfGM);
      this.O.setEnabledAt(5, (gm != null));
      this.aw.c(gm);
      this.O.setEnabledAt(6, (gm != null));
      this.ax.a(arrayOfGp);
      this.O.setEnabledAt(7, (arrayOfGO.length > 0));
      this.ay.a(arrayOfGO);
      this.O.setEnabledAt(8, bool2);
      this.az.a(arrayOfGj);
      this.O.setEnabledAt(9, (ge != null));
      this.aA.a(ge);
      this.O.setEnabledAt(10, (arrayOfGE.length > 0));
      this.aB.a(arrayOfGE);
      this.O.setEnabledAt(11, (gz != null));
      this.aC.a(gz);
      this.O.setEnabledAt(12, (gz != null));
      this.aD.a(gz);
      this.W.setEnabled(!(this.aI[this.aJ] instanceof F));
      this.X.setEnabled(true);
      this.Y.setEnabled(bool1);
      this.Z.setEnabled(true);
      this.aa.setEnabled(true);
      this.ab.setEnabled(bool1);
      for (JMenuItem jMenuItem : this.ac)
        jMenuItem.setEnabled(true); 
    } 
  }
  
  private void m() {
    try {
      this.aM.k(this.aN);
      this.aO = false;
    } catch (Exception exception) {
      hc.a("Error saving account data", exception);
      c("An error occured saving the account data.");
    } 
  }
  
  private void n() {
    if (this.aJ < 0) {
      b("No save file selected.");
      return;
    } 
    try {
      hc.info("Formatting JSON...");
      String str = this.aI[this.aJ].b(this.aK);
      this.aI = this.aG[this.aH].bX();
      this.aJ = -1;
      for (byte b = 0; b < this.aI.length; b++) {
        if (str.equals(this.aI[b].K())) {
          this.aJ = b;
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
    } catch (IOException iOException) {
      hc.error("Could not write save file: " + this.aI[this.aJ].K(), iOException);
      b("There was an error saving the file.");
    } 
  }
  
  private void o() {
    int i = this.aG[this.aH].getIndex();
    int j = dz.a(this.N, this.aF.bU(), i);
    if (j >= 0 && j != i)
      try {
        hc.info("Formatting JSON...");
        hc.info("Creating game slot...");
        String str = this.aF.a(j, this.aK);
        this.aG = this.aF.bV();
        this.aH = -1;
        this.aI = new fs[0];
        this.aJ = -1;
        for (byte b = 0; b < this.aG.length; b++) {
          if (this.aF.W(str) == this.aG[b].getIndex()) {
            this.aH = b;
            this.aI = this.aG[b].bX();
            for (byte b1 = 0; b1 < this.aI.length; b1++) {
              if (str.equals(this.aI[b1].K())) {
                this.aJ = b1;
                break;
              } 
            } 
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
      } catch (IOException iOException) {
        hc.error("Could not write save file", iOException);
        b("There was an error saving the file.");
        return;
      }  
  }
  
  public List g(int paramInt) {
    ArrayList<gt> arrayList = new ArrayList();
    gz gz = this.as.X();
    if (gz != null)
      arrayList.addAll((Collection)gz.cC().stream().filter(paramgt -> paramgt.ay(paramInt)).collect(Collectors.toList())); 
    gv[] arrayOfGv = this.at.aK();
    for (byte b1 = 0; b1 < arrayOfGv.length; b1++) {
      gt gt;
      if ((gt = arrayOfGv[b1].dE()) != null && gt.ay(paramInt))
        arrayList.add(gt); 
    } 
    gH[] arrayOfGH = this.au.aO();
    for (byte b2 = 0; b2 < arrayOfGH.length; b2++)
      arrayList.addAll((Collection<? extends gt>)arrayOfGH[b2].cC().stream().filter(paramgt -> paramgt.ay(paramInt)).collect(Collectors.toList())); 
    gm gm = this.aw.Z();
    if (gm != null)
      arrayList.addAll((Collection<? extends gt>)gm.cC().stream().filter(paramgt -> paramgt.ay(paramInt)).collect(Collectors.toList())); 
    gO[] arrayOfGO = this.ay.aT();
    for (byte b3 = 0; b3 < arrayOfGO.length; b3++)
      arrayList.addAll((Collection<? extends gt>)arrayOfGO[b3].cC().stream().filter(paramgt -> paramgt.ay(paramInt)).collect(Collectors.toList())); 
    ge ge = this.aA.O();
    if (ge != null)
      arrayList.addAll((Collection<? extends gt>)ge.cC().stream().filter(paramgt -> paramgt.ay(paramInt)).collect(Collectors.toList())); 
    return arrayList;
  }
  
  private void p() {
    eY eY1 = this.aK.H("PlayerStateData.UniverseAddress");
    hl hl = hl.n(eY1);
    if ((hl = aj.a(this.N, hl)) != null) {
      hl.aL(0);
      this.aK.b("PlayerStateData.UniverseAddress", hl.ew());
      this.aK.b("PlayerStateData.PreviousUniverseAddress", eY1);
      this.aK.b("SpawnStateData.LastKnownPlayerState", "InShip");
      this.aL = true;
    } 
  }
  
  private void q() {
    hc.info("Starting JSON Editor...");
    if (cy.a(this, this.aI[this.aJ].K(), this.aK))
      t(); 
  }
  
  private void r() {
    hc.info("Starting JSON Editor...");
    if (cy.a(this, this.aM.K(), this.aN))
      try {
        this.aM.k(this.aN);
      } catch (IOException iOException) {
        hc.a("JSON Save error", iOException);
        c("An error occured saving the account data.");
      }  
  }
  
  private static void a(Window paramWindow) {
    SwingUtilities.updateComponentTreeUI(paramWindow);
    Window[] arrayOfWindow;
    int i = (arrayOfWindow = paramWindow.getOwnedWindows()).length;
    for (byte b = 0; b < i; b++) {
      Window window = arrayOfWindow[b];
      a(window);
    } 
  }
  
  private void s() {
    if (aD.d(this.N)) {
      aH.V();
      a(this.N);
    } 
  }
  
  private void t() {
    this.aL = true;
    eY eY1 = this.aK.H("PlayerStateData");
    gz gz = gz.w(eY1);
    gv[] arrayOfGv = gv.v(eY1);
    gB gB = gB.x(eY1);
    gH[] arrayOfGH = gH.C(eY1);
    gM[] arrayOfGM = gM.D(eY1);
    gC gC = gC.y(eY1);
    gm gm = gm.p(eY1);
    gp[] arrayOfGp = gp.q(eY1);
    gO[] arrayOfGO = gO.E(eY1);
    gE[] arrayOfGE = gE.z(eY1);
    boolean bool = gj.n(eY1);
    gj[] arrayOfGj = gj.o(eY1);
    ge ge = ge.m(eY1);
    this.O.setEnabledAt(1, (gz != null));
    this.as.a(gz);
    this.O.setEnabledAt(2, (arrayOfGv.length > 0));
    this.at.a(arrayOfGv, gB);
    this.O.setEnabledAt(3, (arrayOfGH.length > 0));
    this.au.a(arrayOfGH, gC);
    this.O.setEnabledAt(4, (arrayOfGM.length > 0));
    this.av.a(arrayOfGM);
    this.O.setEnabledAt(5, (gm != null));
    this.aw.c(gm);
    this.O.setEnabledAt(6, (gm != null));
    this.ax.a(arrayOfGp);
    this.O.setEnabledAt(7, (arrayOfGO.length > 0));
    this.ay.a(arrayOfGO);
    this.O.setEnabledAt(8, bool);
    this.az.a(arrayOfGj);
    this.O.setEnabledAt(9, (ge != null));
    this.aA.a(ge);
    this.O.setEnabledAt(10, (arrayOfGE.length > 0));
    this.aB.a(arrayOfGE);
    this.O.setEnabledAt(11, (gz != null));
    this.aC.a(gz);
    this.O.setEnabledAt(12, (gz != null));
    this.aD.a(gz);
  }
  
  private void u() {
    hc.info("Exporting JSON...");
    cK cK = cK.aA();
    String str = String.valueOf(this.aI[this.aJ].K()) + ".json";
    cK.setCurrentDirectory(aH.cF);
    cK.setSelectedFile(new File(aH.cF, str));
    if (cK.showSaveDialog(this.N) == 0)
      try {
        File file = cK.getSelectedFile();
        if (!file.getName().endsWith(".json"))
          file = new File(file.getParentFile(), String.valueOf(file.getName()) + ".json"); 
        if (file.exists() && JOptionPane.showConfirmDialog(this.N, "Are you sure you want to overwrite this existing JSON file?", "Confirm", 2) != 0)
          return; 
        this.aK.c(file);
      } catch (IOException iOException) {
        hc.a("JSON Export error", iOException);
        c("An error occured exporting the save data.");
      }  
  }
  
  private void v() {
    hc.info("Importing JSON...");
    cK cK = cK.aA();
    cK.setCurrentDirectory(aH.cF);
    if (cK.showOpenDialog(this.N) == 0)
      try {
        if (JOptionPane.showConfirmDialog(this.N, "Are you sure you want to update your current save data?", "Confirm", 2) != 0)
          return; 
        File file = cK.getSelectedFile();
        this.aK.d(file);
        t();
      } catch (IOException iOException) {
        hc.a("JSON Import error", iOException);
        c("An error occured importing the save data.");
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
  
  public void a(gt paramgt) {
    this.as.a(paramgt);
    this.at.a(paramgt);
    this.au.a(paramgt);
    this.aw.a(paramgt);
    this.ay.a(paramgt);
    this.aA.a(paramgt);
  }
  
  public void B() {
    this.aD.B();
  }
  
  public void C() {
    this.aD.C();
  }
  
  public eV d(String paramString) {
    return this.aK.d(paramString);
  }
  
  public boolean D() {
    return (this.aK.getValue("PlayerStateData.DifficultyState") != null);
  }
  
  public String E() {
    return this.aK.getValueAsString("PlayerStateData.DifficultyState.Settings.InventoryStackLimits.InventoryStackLimitsDifficulty");
  }
  
  public fn F() {
    String str = this.aK.getValueAsString("PlayerStateData.DifficultyState.Preset.DifficultyPresetType");
    if (str != null) {
      fn[] arrayOfFn;
      int i = (arrayOfFn = fn.values()).length;
      for (byte b = 0; b < i; b++) {
        fn fn1 = arrayOfFn[b];
        if (str.equalsIgnoreCase(fn1.name()))
          return fn1; 
      } 
    } 
    fn fn = this.aI[this.aJ].L();
    if (fn == fn.lp) {
      String str1 = this.aK.getValueAsString("PlayerStateData.SeasonData.GameMode.PresetGameMode");
      if ("Normal".equals(str1))
        fn = fn.lk; 
      if ("Creative".equals(str1))
        fn = fn.lm; 
      if ("Survival".equals(str1))
        fn = fn.ll; 
      if ("Ambient".equals(str1))
        fn = fn.ln; 
      if ("Permadeath".equals(str1))
        fn = fn.lo; 
    } 
    return fn;
  }
  
  public void h(int paramInt) {
    eY eY1 = this.aK.H("PlayerStateData");
    eV eV = eY1.d("Multitools");
    if (eV == null || eV.size() == 0)
      return; 
    eY eY2 = gR.az("multitool");
    if (paramInt < 0 || paramInt >= eV.size() || eY2 == null)
      return; 
    eV.remove(paramInt);
    eV.add(eY2);
    gB gB = gB.x(eY1);
    int i = gB.dU();
    if (i > 0 && i >= paramInt)
      gB.aF(--i); 
    this.at.a(gv.v(eY1), gB);
  }
  
  public void i(int paramInt) {
    eY eY1 = this.aK.H("PlayerStateData");
    eV eV = eY1.d("ShipOwnership");
    if (eV == null || eV.size() == 0)
      return; 
    eY eY2 = gR.az("ship");
    if (paramInt < 0 || paramInt >= eV.size() || eY2 == null)
      return; 
    eV.remove(paramInt);
    eV.add(eY2);
    gC gC = gC.y(eY1);
    int i = gC.dV();
    if (i > 0 && i >= paramInt)
      gC.aG(--i); 
    this.au.a(gH.C(eY1), gC);
  }
  
  public void a(gl paramgl, int paramInt) {
    eV eV;
    eY eY1 = this.aK.H("PlayerStateData");
    switch (I()[paramgl.ordinal()]) {
      case 2:
        eV = eY1.d("Eggs");
        break;
      case 1:
        eV = eY1.d("Pets");
        break;
      default:
        return;
    } 
    if (eV == null || eV.size() == 0)
      return; 
    eY eY2 = gR.az("companion");
    if (paramInt < 0 || paramInt >= eV.size() || eY2 == null)
      return; 
    eV.remove(paramInt);
    eV.add(eY2);
    this.az.a(gj.o(eY1));
  }
  
  public boolean j(int paramInt) {
    eV eV = this.aK.d("PlayerStateData.FleetExpeditions");
    for (byte b = 0; b < eV.size(); b++) {
      eV eV1 = eV.V(b).d("AllFrigateIndices");
      if (eV1.hasValue(new Integer(paramInt)))
        return true; 
    } 
    return false;
  }
  
  public gp[] k(int paramInt) {
    eV eV1 = this.aK.d("PlayerStateData.FleetFrigates");
    eV eV2 = this.aK.d("PlayerStateData.FleetExpeditions");
    int i;
    for (i = 0; i < eV2.size(); i++) {
      eV eV = eV2.V(i).d("AllFrigateIndices");
      if (eV.hasValue(new Integer(paramInt))) {
        c("This frigate is currently on a mission and cannot be deleted!");
        return gp.d(eV1);
      } 
    } 
    if (eV1 != null && paramInt < eV1.size()) {
      eV1.ac(paramInt);
      for (byte b = 0; b < eV2.size(); b++) {
        eY eY1 = eV2.V(b);
        eV eV3 = eY1.d("ActiveFrigateIndices");
        byte b1;
        for (b1 = 0; b1 < eV3.size(); b1++) {
          if ((i = eV3.Y(b1)) > paramInt)
            eV3.a(b1, Integer.valueOf(i - 1)); 
        } 
        eV3 = eY1.d("DamagedFrigateIndices");
        for (b1 = 0; b1 < eV3.size(); b1++) {
          if ((i = eV3.Y(b1)) > paramInt)
            eV3.a(b1, Integer.valueOf(i - 1)); 
        } 
        eV3 = eY1.d("DestroyedFrigateIndices");
        for (b1 = 0; b1 < eV3.size(); b1++) {
          if ((i = eV3.Y(b1)) > paramInt)
            eV3.a(b1, Integer.valueOf(i - 1)); 
        } 
        eV3 = eY1.d("AllFrigateIndices");
        for (b1 = 0; b1 < eV3.size(); b1++) {
          if ((i = eV3.Y(b1)) > paramInt)
            eV3.a(b1, Integer.valueOf(i - 1)); 
        } 
        eV eV4 = eY1.d("Events");
        for (b1 = 0; b1 < eV3.size(); b1++) {
          eY eY2 = eV4.V(b1);
          eV3 = eY2.d("AffectedFrigateIndices");
          byte b2;
          for (b2 = 0; b2 < eV3.size(); b2++) {
            if ((i = eV3.Y(b2)) > paramInt)
              eV3.a(b2, Integer.valueOf(i - 1)); 
          } 
          eV3 = eY2.d("RepairingFrigateIndices");
          for (b2 = 0; b2 < eV3.size(); b2++) {
            if ((i = eV3.Y(b2)) > paramInt)
              eV3.a(b2, Integer.valueOf(i - 1)); 
          } 
          eV3 = eY2.d("AffectedFrigateResponses");
          for (b2 = 0; b2 < eV3.size(); b2++) {
            if ((i = eV3.Y(b2)) > paramInt)
              eV3.a(b2, Integer.valueOf(i - 1)); 
          } 
        } 
      } 
      this.aL = true;
    } 
    return gp.d(eV1);
  }
  
  public gp[] a(int paramInt, String paramString) {
    eV eV = this.aK.d("PlayerStateData.FleetFrigates");
    if (eV != null && paramInt < eV.size()) {
      eY eY1 = eV.V(paramInt).bE();
      eY1.d("ResourceSeed").a(1, paramString);
      eY1.b("CustomName", "");
      eV.f(eY1);
      this.aL = true;
    } 
    return gp.d(eV);
  }
  
  private void G() {
    int i = this.aK.J("PlayerStateData.TotalPlayTime");
    byte b1 = 0;
    eV eV1 = this.aK.d("PlayerStateData.PersistentPlayerBases");
    int j;
    for (j = 0; j < eV1.size(); j++) {
      eV eV = eV1.V(j).d("Objects");
      for (byte b = 0; b < eV.size(); b++) {
        String str = eV.V(b).getValueAsString("ObjectID");
        if ("^PLANTER".equals(str)) {
          b1++;
        } else if ("^PLANTERMEGA".equals(str)) {
          b1++;
        } 
      } 
    } 
    eV eV2 = this.aK.d("PlayerStateData.MaintenanceInteractions");
    for (byte b2 = 0; b2 < eV2.size(); b2++) {
      eY eY1 = eV2.V(b2);
      eV eV = eY1.d("InventoryContainer.Slots");
      for (byte b = 0; b < eV.size(); b++) {
        eY eY2 = eV.V(b);
        if ("^MAINT_FARM5".equals(eY2.getValueAsString("Id"))) {
          if ((j = eY2.J("MaxAmount")) > 0 && eY2.J("Amount") < j)
            eY2.b("Amount", new Integer(j)); 
          eY1.b("LastUpdateTimestamp", new Integer(i));
          this.aL = true;
          b1--;
        } 
      } 
    } 
  }
  
  private void initialize() {
    this.N = new JFrame();
    ImageIcon imageIcon = a("UI-FILEICON.PNG");
    if (imageIcon != null)
      this.N.setIconImage(imageIcon.getImage()); 
    this.N.setTitle("No Man's Sky Save Editor - 1.18.1 (RELICS)");
    Rectangle rectangle = new Rectangle(100, 100, 1100, 720);
    rectangle.x = aH.a("MainFrame.X", 100);
    rectangle.y = aH.a("MainFrame.Y", 100);
    rectangle.width = aH.a("MainFrame.Width", 1000);
    rectangle.height = aH.a("MainFrame.Height", 700);
    this.N.setBounds(rectangle);
    this.N.setDefaultCloseOperation(3);
    this.N.addWindowListener(new B(this));
    this.N.addComponentListener(new C(this));
    this.O = new JTabbedPane(1);
    this.N.getContentPane().add(this.O, "Center");
    ba ba = new ba(new int[] { aH.cH, aH.cI, 0 });
    this.O.addTab("Main", null, ba, null);
    ba.k("File Details");
    this.P = new JLabel();
    this.P.setText((this.aF == null) ? "" : fq.c(this.aF));
    ba.a("Storage", this.P, 2);
    this.Q = new JLabel();
    this.Q.setText((this.aF == null) ? "(no path selected)" : this.aF.bS().getAbsolutePath());
    ba.a("Save Path", this.Q, 2);
    this.R = new JComboBox();
    this.R.setModel(new D(this));
    this.R.setEnabled((this.aF != null));
    ba.a("Game Slot", this.R);
    this.S = new JComboBox();
    this.S.setEditable(false);
    this.S.setModel(new E(this));
    this.S.setEnabled((this.aF != null));
    ba.a("Save File", this.S);
    this.T = new JLabel();
    this.T.setText("(no file selected)");
    ba.a("Modified", this.T, 2);
    this.U = new JLabel();
    this.U.setText("(no file selected)");
    ba.a("Save Name", this.U, 2);
    this.V = new JLabel();
    this.V.setText("(no file selected)");
    ba.a("Description", this.V, 2);
    ba.Y();
    JPanel jPanel = new JPanel();
    jPanel.setLayout(new FlowLayout(0, 0, 0));
    this.W = new JButton("Reload");
    this.W.setEnabled(false);
    this.W.addActionListener(paramActionEvent -> l());
    jPanel.add(this.W);
    this.X = new JButton("Save Changes");
    this.X.setEnabled(false);
    this.X.addActionListener(paramActionEvent -> n());
    jPanel.add(this.X);
    this.Y = new JButton("Save As");
    this.Y.setEnabled(false);
    this.Y.addActionListener(paramActionEvent -> o());
    jPanel.add(this.Y);
    ba.a((String)null, jPanel, 2);
    this.as = new aJ(this);
    this.O.addTab("Exosuit", null, this.as, null);
    this.O.setEnabledAt(1, false);
    this.at = new dj(this);
    this.O.addTab("Multitool", null, this.at, null);
    this.O.setEnabledAt(2, false);
    this.au = new dN(this);
    this.O.addTab("Ships", null, this.au, null);
    this.O.setEnabledAt(3, false);
    this.av = new eb(this);
    this.O.addTab("Squadron", null, this.av, null);
    this.O.setEnabledAt(4, false);
    this.aw = new bd(this);
    this.O.addTab("Freighter", null, this.aw, null);
    this.O.setEnabledAt(5, false);
    this.ax = new bl(this);
    this.O.addTab("Frigates", null, this.ax, null);
    this.O.setEnabledAt(6, false);
    this.ay = new ep(this);
    this.O.addTab("Vehicles", null, this.ay, null);
    this.O.setEnabledAt(7, false);
    this.az = new X(this);
    this.O.addTab("Companions", null, this.az, null);
    this.O.setEnabledAt(8, false);
    this.aA = new I(this);
    this.O.addTab("Bases & Storage", null, this.aA, null);
    this.O.setEnabledAt(9, false);
    this.aB = new dE(this);
    this.O.addTab("Settlements", null, this.aB, null);
    this.O.setEnabledAt(10, false);
    this.aC = new ap(this);
    this.O.addTab("Discovery", null, this.aC, null);
    this.O.setEnabledAt(11, false);
    this.aD = new bE(this);
    this.O.addTab("Milestones / Reputation", null, this.aD, null);
    this.O.setEnabledAt(12, false);
    this.aE = new c(this);
    this.O.addTab("Account", null, this.aE, null);
    this.O.setEnabledAt(13, false);
    this.O.addChangeListener(paramChangeEvent -> {
          if (this.O.getSelectedIndex() == 12)
            this.aD.aa(); 
          if (this.aF != null && this.aO && this.aP) {
            int i = JOptionPane.showConfirmDialog(this.N, "Save account data?", "Save", 0);
            this.aP = (i == 0);
            if (this.aP)
              m(); 
          } 
        });
    JMenuBar jMenuBar = new JMenuBar();
    this.N.setJMenuBar(jMenuBar);
    JMenu jMenu1 = new JMenu("File");
    jMenuBar.add(jMenu1);
    JMenuItem jMenuItem1 = new JMenuItem("Open File/Path");
    jMenuItem1.setAccelerator(KeyStroke.getKeyStroke(79, 2));
    jMenuItem1.addActionListener(paramActionEvent -> k());
    jMenu1.add(jMenuItem1);
    this.Z = new JMenuItem("Reload File");
    this.Z.setEnabled(false);
    this.Z.setAccelerator(KeyStroke.getKeyStroke(82, 2));
    this.Z.addActionListener(paramActionEvent -> l());
    jMenu1.add(this.Z);
    this.aa = new JMenuItem("Save File");
    this.aa.setEnabled(false);
    this.aa.setAccelerator(KeyStroke.getKeyStroke(83, 2));
    this.aa.addActionListener(paramActionEvent -> {
          Component component = this.N.getFocusOwner();
          if (component instanceof G)
            ((G)component).N(); 
          n();
        });
    jMenu1.add(this.aa);
    this.ab = new JMenuItem("Save File As");
    this.ab.setEnabled(false);
    this.ab.addActionListener(paramActionEvent -> {
          Component component = this.N.getFocusOwner();
          if (component instanceof G)
            ((G)component).N(); 
          o();
        });
    jMenu1.add(this.ab);
    jMenu1.addSeparator();
    JMenuItem jMenuItem2 = new JMenuItem("Exit");
    jMenuItem2.addActionListener(paramActionEvent -> {
          if (this.aL || this.aO) {
            int i = JOptionPane.showConfirmDialog(this.N, "Save data before closing?", "Save", 0);
            if (i == 0) {
              if (this.aL)
                n(); 
              if (this.aO)
                m(); 
            } 
          } 
          this.N.dispose();
        });
    jMenu1.add(jMenuItem2);
    JMenu jMenu2 = new JMenu("Edit");
    jMenuBar.add(jMenu2);
    this.ac = new ArrayList();
    JMenuItem jMenuItem3 = new JMenuItem("Edit Raw JSON");
    jMenuItem3.addActionListener(paramActionEvent -> q());
    jMenu2.add(jMenuItem3);
    this.ac.add(jMenuItem3);
    JMenuItem jMenuItem4 = new JMenuItem("Export JSON");
    jMenuItem4.addActionListener(paramActionEvent -> u());
    jMenu2.add(jMenuItem4);
    this.ac.add(jMenuItem4);
    JMenuItem jMenuItem5 = new JMenuItem("Import JSON");
    jMenuItem5.addActionListener(paramActionEvent -> v());
    jMenu2.add(jMenuItem5);
    this.ac.add(jMenuItem5);
    JMenuItem jMenuItem6 = new JMenuItem("Coordinate Viewer");
    jMenuItem6.addActionListener(paramActionEvent -> p());
    jMenu2.add(jMenuItem6);
    this.ac.add(jMenuItem6);
    JCheckBoxMenuItem jCheckBoxMenuItem = new JCheckBoxMenuItem("Test Mode");
    jCheckBoxMenuItem.setSelected(en.aS());
    jCheckBoxMenuItem.addActionListener(paramActionEvent -> {
          boolean bool = paramJCheckBoxMenuItem.isSelected();
          if (bool) {
            int i = JOptionPane.showConfirmDialog(this.N, "This mode removes any restrictions imposed by the editor.\nUSE WITH CAUTION: Changes made in test mode may not work in game.", "Test Mode", 2);
            if (i == 2) {
              paramJCheckBoxMenuItem.setSelected(false);
              return;
            } 
          } 
          en.c(bool);
        });
    jMenu2.add(jCheckBoxMenuItem);
    jMenu2.addSeparator();
    JMenuItem jMenuItem7 = new JMenuItem("Recharge All Technology");
    jMenuItem7.addActionListener(paramActionEvent -> w());
    jMenu2.add(jMenuItem7);
    this.ac.add(jMenuItem7);
    JMenuItem jMenuItem8 = new JMenuItem("Refill All Stacks");
    jMenuItem8.addActionListener(paramActionEvent -> x());
    jMenu2.add(jMenuItem8);
    this.ac.add(jMenuItem8);
    JMenuItem jMenuItem9 = new JMenuItem("Recharge Base Planters");
    jMenuItem9.addActionListener(paramActionEvent -> G());
    jMenu2.add(jMenuItem9);
    this.ac.add(jMenuItem9);
    JMenuItem jMenuItem10 = new JMenuItem("Expand All Inventories");
    jMenuItem10.addActionListener(paramActionEvent -> A());
    jMenu2.add(jMenuItem10);
    this.ac.add(jMenuItem10);
    JMenuItem jMenuItem11 = new JMenuItem("Enable All Slots");
    jMenuItem11.addActionListener(paramActionEvent -> y());
    jMenu2.add(jMenuItem11);
    this.ac.add(jMenuItem11);
    JMenuItem jMenuItem12 = new JMenuItem("Repair All Slots / Technology");
    jMenuItem12.addActionListener(paramActionEvent -> z());
    jMenu2.add(jMenuItem12);
    this.ac.add(jMenuItem12);
    jMenu2.addSeparator();
    this.ad = new JMenuItem("Edit Account JSON");
    this.ad.addActionListener(paramActionEvent -> r());
    jMenu2.add(this.ad);
    for (JMenuItem jMenuItem : this.ac)
      jMenuItem.setEnabled(false); 
    this.ad.setEnabled(false);
    JMenu jMenu3 = new JMenu("View");
    jMenuBar.add(jMenu3);
    JMenuItem jMenuItem13 = new JMenuItem("Settings");
    jMenuItem13.addActionListener(paramActionEvent -> s());
    jMenu3.add(jMenuItem13);
    jMenuBar.add(Box.createHorizontalGlue());
    JMenu jMenu4 = new JMenu("Help");
    jMenuBar.add(jMenu4);
    JMenuItem jMenuItem14 = new JMenuItem("About");
    jMenuItem14.addActionListener(paramActionEvent -> a.a(this.N));
    jMenu4.add(jMenuItem14);
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
  
  private static String e(String paramString) {
    return (paramString == null) ? "(unknown)" : paramString;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\Application.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */