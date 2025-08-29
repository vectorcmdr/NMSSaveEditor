package nomanssave;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

class fQ {
  final String filename;
  
  final int lM;
  
  fI mv;
  
  fQ(fJ paramfJ, String paramString, int paramInt, boolean paramBoolean) {
    this.filename = paramString;
    this.lM = paramInt;
    if (paramBoolean) {
      FileInputStream fileInputStream = new FileInputStream(new File(fJ.a(paramfJ), "mf_" + paramString));
      try {
        hc.info("Reading metadata for " + paramString);
        byte[] arrayOfByte = new byte[1024];
        int m = fileInputStream.read(arrayOfByte);
        this.mv = fI.a(paramInt, arrayOfByte, 0, m);
      } finally {
        fileInputStream.close();
      } 
      int i = this.mv.ch();
      if (i != 0)
        hc.debug("  DecompressedSize: " + i); 
      int j = this.mv.ci();
      if (j != 0)
        hc.debug("  CompressedSize: " + j); 
      int k = this.mv.cj();
      if (k != 0)
        hc.info("  TotalPlayTime: " + fq.c(k)); 
    } else {
      hc.info("Creating new metadata for " + paramString);
      this.mv = fI.am(paramInt);
    } 
  }
  
  public String K() {
    return this.filename;
  }
  
  public long lastModified() {
    return (new File(fJ.a(this.mr), "mf_" + this.filename)).lastModified();
  }
  
  eY a(eG parameG) {
    gX gX;
    BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(fJ.a(this.mr), this.filename)));
    try {
      byte[] arrayOfByte = new byte[16];
      bufferedInputStream.mark(arrayOfByte.length);
      hk.readFully(bufferedInputStream, arrayOfByte);
      if ((0xFF & arrayOfByte[0]) == 229 && (0xFF & arrayOfByte[1]) == 161 && (0xFF & arrayOfByte[2]) == 237 && (0xFF & arrayOfByte[3]) == 254) {
        gX = new gX(bufferedInputStream, arrayOfByte);
      } else {
        gX.reset();
      } 
      Exception exception1 = null;
      Exception exception2 = null;
    } finally {
      gX.close();
    } 
  }
  
  byte[] ah(int paramInt) {
    gX gX;
    FileInputStream fileInputStream = new FileInputStream(new File(fJ.a(this.mr), this.filename));
    try {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      byte[] arrayOfByte = new byte[1024];
      hk.readFully(fileInputStream, arrayOfByte, 0, 16);
      if ((0xFF & arrayOfByte[0]) == 229 && (0xFF & arrayOfByte[1]) == 161 && (0xFF & arrayOfByte[2]) == 237 && (0xFF & arrayOfByte[3]) == 254) {
        gX = new gX(fileInputStream, arrayOfByte);
      } else {
        byteArrayOutputStream.write(arrayOfByte, 0, 16);
      } 
      int i;
      while ((i = gX.read(arrayOfByte)) >= 0) {
        byteArrayOutputStream.write(arrayOfByte, 0, i);
        if (byteArrayOutputStream.size() >= paramInt)
          break; 
      } 
      return byteArrayOutputStream.toByteArray();
    } finally {
      gX.close();
    } 
  }
  
  void a(String paramString1, fn paramfn, String paramString2, String paramString3) {
    File file1 = new File(fJ.a(this.mr), "mf_" + this.filename);
    File file2 = new File(fJ.a(this.mr), this.filename);
    Properties properties = new Properties();
    properties.setProperty("ArchiveNumber", Integer.toString(this.lM));
    properties.setProperty("ManifestFile", "mf_" + this.filename);
    properties.setProperty("StorageFile", this.filename);
    properties.setProperty("LastModified", Long.toString(file1.lastModified()));
    if (paramfn != null)
      properties.setProperty("GameMode", paramfn.name()); 
    if (paramString2 != null)
      properties.setProperty("SaveName", paramString2); 
    if (paramString3 != null)
      properties.setProperty("Description", paramString3); 
    String str = String.valueOf(paramString1) + "." + System.currentTimeMillis() + ".zip";
    File file3 = new File(aH.cG, str);
    ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(file3));
    try {
      byte[] arrayOfByte = new byte[1024];
      ZipEntry zipEntry = new ZipEntry(file1.getName());
      zipOutputStream.putNextEntry(zipEntry);
      FileInputStream fileInputStream1 = new FileInputStream(file1);
      try {
        int i;
        while ((i = fileInputStream1.read(arrayOfByte)) >= 0)
          zipOutputStream.write(arrayOfByte, 0, i); 
      } finally {
        fileInputStream1.close();
      } 
      zipEntry = new ZipEntry(file2.getName());
      zipOutputStream.putNextEntry(zipEntry);
      FileInputStream fileInputStream2 = new FileInputStream(file2);
      try {
        int i;
        while ((i = fileInputStream2.read(arrayOfByte)) >= 0)
          zipOutputStream.write(arrayOfByte, 0, i); 
      } finally {
        fileInputStream2.close();
      } 
      zipEntry = new ZipEntry("saveinfo.txt");
      zipOutputStream.putNextEntry(zipEntry);
      properties.store(zipOutputStream, "");
    } finally {
      zipOutputStream.close();
    } 
    file3.setLastModified(file1.lastModified());
  }
  
  void a(eY parameY, boolean paramBoolean) {
    byte[] arrayOfByte1;
    int i;
    gZ gZ;
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    Exception exception1 = null;
    Exception exception2 = null;
    try {
      fj fj = new fj(byteArrayOutputStream, 2);
      try {
        fj.h(parameY);
      } finally {
        if (fj != null)
          fj.close(); 
      } 
    } finally {
      exception2 = null;
      if (exception1 == null) {
        exception1 = exception2;
      } else if (exception1 != exception2) {
        exception1.addSuppressed(exception2);
      } 
    } 
    FileOutputStream fileOutputStream2 = new FileOutputStream(new File(fJ.a(this.mr), this.filename));
    try {
      if (paramBoolean)
        gZ = new gZ(fileOutputStream2); 
      gZ.write(arrayOfByte1);
      if (paramBoolean)
        i = gZ.ci(); 
    } finally {
      gZ.close();
    } 
    if (!this.mv.ce())
      hc.warn("Metadata version could not be upgraded"); 
    byte[] arrayOfByte2 = new byte[32];
    byte[] arrayOfByte3 = new byte[16];
    if (!paramBoolean)
      try {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        arrayOfByte2 = messageDigest.digest(arrayOfByte1);
        arrayOfByte3 = fJ.d(arrayOfByte2, arrayOfByte1);
      } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
        hc.a("Error generating SHA-256 hash", noSuchAlgorithmException);
      }  
    this.mv.e(arrayOfByte2);
    this.mv.f(arrayOfByte3);
    this.mv.ak(i);
    this.mv.aj(arrayOfByte1.length);
    FileOutputStream fileOutputStream1 = new FileOutputStream(new File(fJ.a(this.mr), "mf_" + this.filename));
    try {
      fileOutputStream1.write(this.mv.encode());
    } finally {
      fileOutputStream1.close();
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\fQ.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */