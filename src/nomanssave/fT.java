package nomanssave;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class fT implements fq {
  private static final byte[] ly = "HGSAVEV2\000".getBytes();
  
  private static final Pattern lT = Pattern.compile("Slot(\\d+)((Auto)|(Manual))");
  
  private static final Pattern lU = Pattern.compile("wgsbackup(\\d*)\\.\\d*\\.zip");
  
  static final String mA = "containers.index";
  
  private final File lV;
  
  private final fR lC;
  
  private fU mB;
  
  private fY[] mC;
  
  private int header;
  
  private int lJ;
  
  private String name;
  
  private int lK;
  
  private int lP;
  
  private int lQ;
  
  private String mD;
  
  private int mE;
  
  private int mF;
  
  private List mG;
  
  private static final Pattern mH = Pattern.compile("\"((?:<h0)|(?:CommonStateData))\":\\{\"((?:Pk4)|(?:SaveName))\":\"([^\"]+)\"");
  
  private static int mI = 1;
  
  private static int mJ = 2;
  
  private static int mK = 3;
  
  fT(File paramFile, fR paramfR) {
    this.lV = paramFile.isDirectory() ? paramFile : paramFile.getParentFile();
    this.lC = paramfR;
    cr();
    try {
      this.mB = new fU(this);
    } catch (FileNotFoundException fileNotFoundException) {
    
    } catch (IOException iOException) {
      hc.a("Cannot read account data", iOException);
    } 
    this.mC = new fY[30];
    for (byte b = 0; b < this.mC.length; b++) {
      try {
        this.mC[b] = new fY(this, b);
      } catch (FileNotFoundException fileNotFoundException) {
      
      } catch (IOException iOException) {
        hc.a("Cannot read file data", iOException);
      } 
    } 
    fl.a(this, this.lV);
  }
  
  protected void finalize() {
    fl.b(this);
  }
  
  public void X(String paramString) {
    paramString.equals("containers.index");
  }
  
  public File bS() {
    return this.lV;
  }
  
  private void cr() {
    hc.info("Reading Container Index");
    FileInputStream fileInputStream = new FileInputStream(new File(this.lV, "containers.index"));
    try {
      this.header = hk.readInt(fileInputStream);
      hc.debug("  header: " + this.header);
      int i = hk.readInt(fileInputStream);
      hc.debug("  count: " + i);
      this.lJ = hk.readInt(fileInputStream);
      if (this.lJ != 0)
        hc.debug("  unknown1: " + this.lJ); 
      this.name = gc.c(fileInputStream);
      hc.debug("  name: " + this.name);
      this.lK = hk.readInt(fileInputStream);
      if (this.lK != 0)
        hc.debug("  unknown2: " + this.lK); 
      this.lP = hk.readInt(fileInputStream);
      if (this.lP != 0)
        hc.debug("  unknown3: " + this.lP); 
      this.lQ = hk.readInt(fileInputStream);
      if (this.lQ != 0)
        hc.debug("  unknown4: " + this.lQ); 
      this.mD = gc.c(fileInputStream);
      hc.debug("  appid: " + this.mD);
      this.mE = hk.readInt(fileInputStream);
      if (this.mE != 0)
        hc.debug("  unknown5: " + this.mE); 
      this.mF = hk.readInt(fileInputStream);
      if (this.mF != 0)
        hc.debug("  unknown6: " + this.mF); 
      this.mG = new ArrayList();
      for (byte b = 0; b < i; b++)
        this.mG.add(new fW(this, fileInputStream)); 
      if (fileInputStream.read() >= 0)
        throw new IOException("Invalid footer"); 
    } finally {
      fileInputStream.close();
    } 
  }
  
  private void cs() {
    FileOutputStream fileOutputStream = new FileOutputStream(new File(this.lV, "containers.index"));
    try {
      hk.a(fileOutputStream, this.header);
      hk.a(fileOutputStream, this.mG.size());
      hk.a(fileOutputStream, this.lJ);
      gc.b(fileOutputStream, this.name);
      hk.a(fileOutputStream, this.lK);
      hk.a(fileOutputStream, this.lP);
      hk.a(fileOutputStream, this.lQ);
      gc.b(fileOutputStream, this.mD);
      hk.a(fileOutputStream, this.mE);
      hk.a(fileOutputStream, this.mF);
      for (fW fW : this.mG)
        fW.write(fileOutputStream); 
    } finally {
      fileOutputStream.close();
    } 
  }
  
  private fW Z(String paramString) {
    for (fW fW : this.mG) {
      if (fW.name.equals(paramString))
        return fW; 
    } 
    throw new FileNotFoundException(paramString);
  }
  
  private String ct() {
    while (true) {
      boolean bool = true;
      String str = gc.cA();
      for (fW fW : this.mG)
        bool &= fW.mS.equals(str); 
      File file = new File(this.lV, str);
      bool &= file.exists();
      if (bool) {
        if (!file.mkdir())
          throw new FileNotFoundException(str); 
        return str;
      } 
    } 
  }
  
  public fr bT() {
    return this.mB;
  }
  
  public ft[] bU() {
    ft[] arrayOfFt = new ft[15];
    for (byte b = 0; b < 15; b++)
      arrayOfFt[b] = new fZ(this, b); 
    return arrayOfFt;
  }
  
  public int W(String paramString) {
    Matcher matcher = lT.matcher(paramString);
    return !matcher.matches() ? -1 : Integer.parseInt(matcher.group(1));
  }
  
  private static int an(int paramInt) {
    return 0x7FFF0000 & paramInt | (0xE00 & paramInt) >> 9;
  }
  
  private static boolean h(File paramFile) {
    File[] arrayOfFile = paramFile.listFiles();
    if (arrayOfFile != null) {
      File[] arrayOfFile1;
      int i = (arrayOfFile1 = arrayOfFile).length;
      for (byte b = 0; b < i; b++) {
        File file = arrayOfFile1[b];
        h(file);
      } 
    } 
    return paramFile.delete();
  }
  
  private static InputStream a(InputStream paramInputStream, int paramInt) {
    try {
      boolean bool = true;
      if (!paramInputStream.markSupported())
        paramInputStream = new BufferedInputStream(paramInputStream); 
      paramInputStream.mark(ly.length);
      byte[] arrayOfByte1 = new byte[ly.length];
      hk.readFully(paramInputStream, arrayOfByte1);
      for (byte b = 0; b < ly.length; b++) {
        if (arrayOfByte1[b] != ly[b]) {
          bool = false;
          break;
        } 
      } 
      if (bool)
        return new hm(paramInputStream); 
      paramInputStream.reset();
      byte[] arrayOfByte2 = new byte[16];
      paramInputStream.mark(arrayOfByte2.length);
      hk.readFully(paramInputStream, arrayOfByte2);
      if ((0xFF & arrayOfByte2[0]) == 229 && (0xFF & arrayOfByte2[1]) == 161 && (0xFF & arrayOfByte2[2]) == 237 && (0xFF & arrayOfByte2[3]) == 254)
        return new gX(paramInputStream, arrayOfByte2); 
      paramInputStream.reset();
      return new ha(paramInputStream, paramInt);
    } catch (IOException iOException) {
      try {
        paramInputStream.close();
      } catch (IOException iOException1) {}
      throw iOException;
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\fT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */