package nomanssave;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

class fL implements fs {
  final int lZ;
  
  final File ma;
  
  final String ms;
  
  final String mb;
  
  final fn be;
  
  final String mt;
  
  final String description;
  
  fL(fJ paramfJ, String paramString, int paramInt) {
    this.lZ = paramInt;
    this.ma = new File(aH.cG, paramString);
    ZipFile zipFile = new ZipFile(this.ma);
    try {
      ZipEntry zipEntry = zipFile.getEntry("saveinfo.txt");
      if (zipEntry == null)
        throw new IOException("Invalid backup file"); 
      Properties properties = new Properties();
      properties.load(zipFile.getInputStream(zipEntry));
      this.ms = properties.getProperty("ManifestFile");
      this.mb = properties.getProperty("StorageFile");
      if (this.ms == null || this.mb == null)
        throw new IOException("Invalid backup file"); 
      String str = properties.getProperty("GameMode");
      this.be = (str == null) ? null : fn.valueOf(str);
      this.mt = properties.getProperty("SaveName");
      this.description = properties.getProperty("Description");
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
  
  public String getName() {
    return this.mt;
  }
  
  public String getDescription() {
    return this.description;
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
    if (fJ.b(this.mr)[this.lZ] != null) {
      str = fJ.b(this.mr)[this.lZ].b(parameY);
    } else {
      fJ.b(this.mr)[this.lZ] = new fM(this.mr, this.lZ, parameY);
      str = (fJ.b(this.mr)[this.lZ]).filename;
    } 
    hc.info("Finished.");
    return str;
  }
  
  public String toString() {
    return this.ma.getName();
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\fL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */