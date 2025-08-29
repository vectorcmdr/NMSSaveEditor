package nomanssave;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

class fV implements fs {
  final fW mM;
  
  final int lZ;
  
  final File ma;
  
  final String mN;
  
  final fS mO;
  
  final String mP;
  
  final String mQ;
  
  final fn be;
  
  fV(fT paramfT, String paramString, int paramInt) {
    this.lZ = paramInt;
    this.ma = new File(aH.cG, paramString);
    ZipFile zipFile = new ZipFile(this.ma);
    try {
      ZipEntry zipEntry = zipFile.getEntry("saveinfo.txt");
      if (zipEntry == null)
        throw new IOException("Invalid backup file"); 
      Properties properties = new Properties();
      properties.load(zipFile.getInputStream(zipEntry));
      this.mN = properties.getProperty("MetaFile");
      this.mP = properties.getProperty("DataFile");
      this.mQ = properties.getProperty("ContainerFile");
      String str1 = properties.getProperty("IndexData");
      if (this.mN == null || this.mP == null || this.mQ == null || str1 == null)
        throw new IOException("Invalid backup file"); 
      String str2 = properties.getProperty("GameMode");
      this.be = (str2 == null) ? null : fn.valueOf(str2);
      this.mM = new fW(paramfT, str1);
      zipEntry = zipFile.getEntry(this.mN);
      if (zipEntry == null)
        throw new IOException("Invalid backup file"); 
      this.mO = new fS(null);
      this.mO.read(zipFile.getInputStream(zipEntry));
    } catch (NumberFormatException numberFormatException) {
      throw new IOException("Invalid backup file");
    } finally {
      zipFile.close();
    } 
  }
  
  void a(FileOutputStream paramFileOutputStream) {
    ZipFile zipFile = new ZipFile(this.ma);
    try {
      ZipEntry zipEntry = zipFile.getEntry(this.mQ);
      if (zipEntry == null)
        throw new IOException("Invalid backup file"); 
      InputStream inputStream = zipFile.getInputStream(zipEntry);
      try {
        byte[] arrayOfByte = new byte[1024];
        int i;
        while ((i = inputStream.read(arrayOfByte)) > 0)
          paramFileOutputStream.write(arrayOfByte, 0, i); 
      } finally {
        inputStream.close();
      } 
    } finally {
      zipFile.close();
    } 
  }
  
  public String K() {
    return this.mM.filename;
  }
  
  public fn L() {
    return this.be;
  }
  
  public eY M() {
    Exception exception1 = null;
    Exception exception2 = null;
    try {
    
    } finally {
      exception2 = null;
      if (exception1 == null) {
        exception1 = exception2;
      } else if (exception1 != exception2) {
        exception1.addSuppressed(exception2);
      } 
    } 
  }
  
  public String b(eY parameY) {
    String str;
    hc.info("Writing new save file...");
    if (fT.b(this.mL)[this.lZ] != null) {
      (fT.b(this.mL)[this.lZ]).mX.a(this.mO);
      str = fT.b(this.mL)[this.lZ].b(parameY);
    } else {
      fT.b(this.mL)[this.lZ] = new fY(this.mL, this, parameY);
      str = fT.b(this.mL)[this.lZ].K();
    } 
    hc.info("Finished.");
    return str;
  }
  
  public long lastModified() {
    return this.mM.timestamp;
  }
  
  public String toString() {
    return this.ma.getName();
  }
  
  public String getName() {
    return this.mO.getName();
  }
  
  public String getDescription() {
    return this.mO.getDescription();
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\fV.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */