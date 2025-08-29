package nomanssave;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

class fC implements fs {
  final int lZ;
  
  final File ma;
  
  final String mb;
  
  final byte[] lI;
  
  final fn be;
  
  fC(fA paramfA, String paramString, int paramInt) {
    this.lZ = paramInt;
    this.ma = new File(aH.cG, paramString);
    ZipFile zipFile = new ZipFile(this.ma);
    try {
      ZipEntry zipEntry = zipFile.getEntry("saveinfo.txt");
      if (zipEntry == null)
        throw new IOException("Invalid backup file"); 
      Properties properties = new Properties();
      properties.load(zipFile.getInputStream(zipEntry));
      this.mb = properties.getProperty("StorageFile");
      if (this.mb == null)
        throw new IOException("Invalid backup file"); 
      String str = properties.getProperty("GameMode");
      this.be = (str == null) ? null : fn.valueOf(str);
      zipEntry = zipFile.getEntry(this.mb);
      InputStream inputStream = zipFile.getInputStream(zipEntry);
      try {
        this.lI = new byte[112];
        hk.readFully(inputStream, this.lI);
        for (byte b = 0; b < (fA.bY()).length; b++) {
          if (this.lI[b] != fA.bY()[b])
            throw new IOException("Invalid header"); 
        } 
      } finally {
        inputStream.close();
      } 
    } catch (NumberFormatException numberFormatException) {
      throw new IOException("Invalid backup file");
    } finally {
      zipFile.close();
    } 
  }
  
  public fn L() {
    return this.be;
  }
  
  public String K() {
    return this.ma.getName();
  }
  
  public long lastModified() {
    return this.ma.lastModified();
  }
  
  public eY M() {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    Exception exception1 = null;
    Exception exception2 = null;
  }
  
  public String b(eY parameY) {
    String str;
    hc.info("Writing new save file...");
    if (fA.b(this.lY)[this.lZ] != null) {
      str = fA.b(this.lY)[this.lZ].b(parameY);
    } else {
      fA.b(this.lY)[this.lZ] = new fD(this.lY, this.lZ, this.lI, parameY);
      str = fA.b(this.lY)[this.lZ].K();
    } 
    hc.info("Finished.");
    return str;
  }
  
  public String toString() {
    return this.ma.getName();
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\fC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */