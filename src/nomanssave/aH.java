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
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
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
  
  public static String getProperty(String paramString) {
    return cK.getValueAsString(paramString);
  }
  
  public static void setProperty(String paramString1, String paramString2) {
    if (paramString2 == null) {
      cK.N(paramString1);
    } else {
      cK.c(paramString1, paramString2);
    } 
    cL = true;
  }
  
  public static int j(String paramString) {
    return cK.J(paramString);
  }
  
  public static int a(String paramString, int paramInt) {
    return cK.c(paramString, paramInt);
  }
  
  public static void b(String paramString, int paramInt) {
    cK.c(paramString, Integer.valueOf(paramInt));
    cL = true;
  }
  
  public static double a(String paramString, double paramDouble) {
    return cK.c(paramString, paramDouble);
  }
  
  public static void b(String paramString, double paramDouble) {
    cK.c(paramString, Double.valueOf(paramDouble));
    cL = true;
  }
  
  public static Object[] a(String paramString, Class<?> paramClass) {
    eV eV = cK.d(paramString);
    if (eV == null)
      return (Object[])Array.newInstance(paramClass, 0); 
    Object object = Array.newInstance(paramClass, eV.size());
    for (byte b = 0; b < eV.size(); b++)
      Array.set(object, b, paramClass.cast(eV.getValue(b))); 
    return (Object[])object;
  }
  
  public static void a(String paramString, Object[] paramArrayOfObject) {
    eV eV = new eV();
    for (byte b = 0; b < paramArrayOfObject.length; b++)
      eV.f(paramArrayOfObject[b]); 
    cK.c(paramString, eV);
    cL = true;
  }
  
  static boolean T() {
    return cL;
  }
  
  static void U() {
    String str = fh.b(cK, true);
    try {
      Exception exception2;
      Exception exception1 = null;
    } catch (IOException iOException) {
      hc.error("Could not save configuration file", iOException);
    } 
  }
  
  public static void init(boolean paramBoolean) {
    System.out.println("Initializing environment...");
    File file = null;
    try {
      URL uRL = Application.class.getProtectionDomain().getCodeSource().getLocation();
      if (paramBoolean && uRL.getFile().endsWith(".jar")) {
        file = Paths.get(uRL.toURI()).toFile().getParentFile();
      } else {
        file = (new File(".")).getCanonicalFile();
      } 
    } catch (URISyntaxException uRISyntaxException) {
      System.out.println("Error: cannot find working directory");
      uRISyntaxException.printStackTrace();
      System.exit(1);
    } catch (IOException iOException) {
      System.out.println("Error: cannot find working directory");
      iOException.printStackTrace();
      System.exit(1);
    } 
    if (!file.isDirectory()) {
      System.out.println("Error: working directory error: " + file.getAbsolutePath());
      System.exit(1);
    } 
    cD = file;
    cC = new File(file, "NMSSaveEditor.conf");
    cE = new File(file, "bases");
    cF = new File(file, "exported");
    cG = new File(file, "backups");
    if (!cG.exists() && !cG.mkdir()) {
      System.out.println("Error: cannot create backups folder");
      System.exit(1);
    } 
    hc.k(new File(file, "NMSSaveEditor.log"));
    hc.debug("Java Vendor: " + System.getProperty("java.vendor"));
    hc.debug("Java Version: " + System.getProperty("java.version"));
    hc.debug("Java Architecture: " + System.getProperty("os.arch"));
    hc.debug("Operating System: " + System.getProperty("os.name"));
    hc.debug("Working Dir: " + file.getAbsolutePath());
    cK = new eY();
    cL = false;
    if (cC.exists())
      try {
        byte[] arrayOfByte = hk.l(cC);
        if (arrayOfByte.length > 0 && arrayOfByte[0] == 123) {
          cK = eY.E(new String(arrayOfByte, "UTF-8"));
        } else {
          Properties properties = new Properties();
          FileInputStream fileInputStream = new FileInputStream(cC);
          try {
            properties.load(fileInputStream);
          } finally {
            fileInputStream.close();
          } 
          eV eV = new eV();
          for (String str1 : properties.stringPropertyNames()) {
            try {
              String str2 = properties.getProperty(str1);
              if (!str1.equals("InventoryFontScale")) {
                if (str1.equals("InventoryScaling")) {
                  cK.b("InventoryScaling", Double.valueOf(Double.parseDouble(str2)));
                  continue;
                } 
                if (str1.equals("FontScaling")) {
                  cK.b("FontScaling", Double.valueOf(Double.parseDouble(str2)));
                  continue;
                } 
                if (str1.endsWith(".Location")) {
                  str1 = str1.substring(0, str1.lastIndexOf("."));
                  int i;
                  if ((i = str2.indexOf(',')) > 0) {
                    int j = Integer.parseInt(str2.substring(0, i));
                    int k = Integer.parseInt(str2.substring(i + 1));
                    cK.c(String.valueOf(str1) + ".X", Integer.valueOf(j));
                    cK.c(String.valueOf(str1) + ".Y", Integer.valueOf(k));
                  } 
                  continue;
                } 
                if (str1.endsWith(".Size")) {
                  str1 = str1.substring(0, str1.lastIndexOf("."));
                  int i;
                  if ((i = str2.indexOf(',')) > 0) {
                    int j = Integer.parseInt(str2.substring(0, i));
                    int k = Integer.parseInt(str2.substring(i + 1));
                    cK.c(String.valueOf(str1) + ".Width", Integer.valueOf(j));
                    cK.c(String.valueOf(str1) + ".Height", Integer.valueOf(k));
                  } 
                  continue;
                } 
                if (str1.equals("JSONEditor.Divider")) {
                  int i = Integer.parseInt(str2);
                  cK.c(str1, Integer.valueOf(i));
                  continue;
                } 
                if (str1.startsWith("SteamID.")) {
                  str1 = str1.substring(8);
                  eV.f(Long.valueOf(Long.parseLong(str1)));
                  cK.c("KnownPlayers." + str1, str2);
                  continue;
                } 
                cK.c(str1, str2);
              } 
            } catch (NumberFormatException numberFormatException) {}
          } 
          if (eV.size() > 0)
            cK.b("SteamIDs", eV); 
          cL = true;
        } 
      } catch (IOException iOException) {
        hc.a("Could not load configuration file", iOException);
      }  
    String str = cK.getValueAsString("LogLevel");
    if (str != null)
      hc.aA(str); 
    FlatLaf.registerCustomDefaultsSource("nomanssave");
    V();
  }
  
  public static void V() {
    String str = cK.getValueAsString("LookAndFeel");
    aI aI = Stream.<aI>of(aI.values()).filter(paramaI -> paramaI.name().equalsIgnoreCase(paramString)).findFirst().orElse(aI.cN);
    try {
      FlatLightLaf flatLightLaf;
      FlatDarkLaf flatDarkLaf;
      FlatIntelliJLaf flatIntelliJLaf;
      FlatDarculaLaf flatDarculaLaf;
      FlatMacDarkLaf flatMacDarkLaf;
      FlatMacLightLaf flatMacLightLaf;
      switch (W()[aI.ordinal()]) {
        default:
          flatLightLaf = new FlatLightLaf();
          break;
        case 2:
          flatDarkLaf = new FlatDarkLaf();
          break;
        case 3:
          flatIntelliJLaf = new FlatIntelliJLaf();
          break;
        case 4:
          flatDarculaLaf = new FlatDarculaLaf();
          break;
        case 6:
          flatMacDarkLaf = new FlatMacDarkLaf();
          break;
        case 5:
          flatMacLightLaf = new FlatMacLightLaf();
          break;
      } 
      UIManager.setLookAndFeel((LookAndFeel)flatMacLightLaf);
    } catch (UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
      hc.a("Could not set look and feel: " + aI, unsupportedLookAndFeelException);
      return;
    } 
    hc.debug("Look and Feel: " + UIManager.getLookAndFeel().getName());
    Font font = UIManager.getFont("Label.font");
    if (font == null) {
      cH = 120;
      cI = 350;
      cJ = 200;
      UIManager.put("Inventory.font", null);
      UIManager.put("Inventory.gridSize", Integer.valueOf(200));
      UIManager.put("Inventory.iconSize", Integer.valueOf(64));
    } else {
      double d = cK.L("InventoryScaling");
      if (d <= 0.0D) {
        d = 1.0D;
        cK.b("InventoryScaling", Double.valueOf(d));
        cL = true;
      } 
      int i = (int)Math.round(font.getSize() * d);
      Font font1 = new Font(font.getName(), 0, i);
      Canvas canvas = new Canvas();
      FontMetrics fontMetrics = canvas.getFontMetrics(font);
      cH = fontMetrics.stringWidth("MMMMMMMMMM");
      cI = fontMetrics.stringWidth("MMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
      cJ = fontMetrics.stringWidth("MMMMMMMMMMMMMMMMM");
      fontMetrics = canvas.getFontMetrics(font1);
      int j = fontMetrics.stringWidth("MMMMMMMMMMM");
      int k = j - fontMetrics.getHeight() * 2 + 8;
      int m;
      for (m = 16; m * 2 <= k; m *= 2);
      if (m * 1.5D <= k)
        m = (int)(m * 1.5D); 
      UIManager.put("Inventory.font", font1);
      UIManager.put("Inventory.gridSize", Integer.valueOf(j));
      UIManager.put("Inventory.iconSize", Integer.valueOf(m));
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\aH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */