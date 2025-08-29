package nomanssave;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

class fH {
  final File mf;
  
  byte[] lI;
  
  fH(fA paramfA, String paramString, boolean paramBoolean) {
    this.mf = new File(fA.a(paramfA), paramString);
    if (paramBoolean) {
      FileInputStream fileInputStream = new FileInputStream(this.mf);
      try {
        this.lI = new byte[112];
        hk.readFully(fileInputStream, this.lI);
        for (byte b = 0; b < (fA.bY()).length; b++) {
          if (this.lI[b] != fA.bY()[b])
            throw new IOException("Invalid header"); 
        } 
      } finally {
        fileInputStream.close();
      } 
    } 
  }
  
  byte[] readBytes() {
    long l = (0xFFL & this.lI[95]) << 24L | (0xFFL & this.lI[94]) << 16L | (0xFFL & this.lI[93]) << 8L | 0xFFL & this.lI[92];
    FileInputStream fileInputStream = new FileInputStream(new File(fA.a(this.lY), K()));
    try {
      byte[] arrayOfByte = new byte[(int)l];
      fileInputStream.skip(112L);
      hk.readFully(fileInputStream, arrayOfByte);
      return arrayOfByte;
    } finally {
      fileInputStream.close();
    } 
  }
  
  byte[] ah(int paramInt) {
    long l = (0xFFL & this.lI[95]) << 24L | (0xFFL & this.lI[94]) << 16L | (0xFFL & this.lI[93]) << 8L | 0xFFL & this.lI[92];
    FileInputStream fileInputStream = new FileInputStream(new File(fA.a(this.lY), K()));
    try {
      paramInt = (int)Math.min(paramInt, l);
      byte[] arrayOfByte = new byte[paramInt];
      fileInputStream.skip(112L);
      hk.readFully(fileInputStream, arrayOfByte);
      return arrayOfByte;
    } finally {
      fileInputStream.close();
    } 
  }
  
  void writeBytes(byte[] paramArrayOfbyte) {
    this.lI[92] = (byte)paramArrayOfbyte.length;
    this.lI[93] = (byte)(paramArrayOfbyte.length >> 8);
    this.lI[94] = (byte)(paramArrayOfbyte.length >> 16);
    this.lI[95] = (byte)(paramArrayOfbyte.length >> 24);
    FileOutputStream fileOutputStream = new FileOutputStream(new File(fA.a(this.lY), K()));
    try {
      fileOutputStream.write(this.lI);
      fileOutputStream.write(paramArrayOfbyte);
    } finally {
      fileOutputStream.close();
    } 
  }
  
  void a(String paramString1, fn paramfn, String paramString2, String paramString3) {
    Properties properties = new Properties();
    properties.setProperty("StorageFile", this.mf.getName());
    properties.setProperty("LastModified", Long.toString(this.mf.lastModified()));
    if (paramfn != null)
      properties.setProperty("GameMode", paramfn.name()); 
    if (paramString2 != null)
      properties.setProperty("SaveName", paramString2); 
    if (paramString3 != null)
      properties.setProperty("Description", paramString3); 
    String str = String.valueOf(paramString1) + "." + System.currentTimeMillis() + ".zip";
    File file = new File(aH.cG, str);
    ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(file));
    try {
      byte[] arrayOfByte = new byte[4096];
      ZipEntry zipEntry = new ZipEntry(this.mf.getName());
      zipOutputStream.putNextEntry(zipEntry);
      FileInputStream fileInputStream = new FileInputStream(this.mf);
      try {
        int i;
        while ((i = fileInputStream.read(arrayOfByte)) >= 0)
          zipOutputStream.write(arrayOfByte, 0, i); 
      } finally {
        fileInputStream.close();
      } 
      zipEntry = new ZipEntry("saveinfo.txt");
      zipOutputStream.putNextEntry(zipEntry);
      properties.store(zipOutputStream, "");
    } finally {
      zipOutputStream.close();
    } 
    file.setLastModified(this.mf.lastModified());
  }
  
  public String K() {
    return this.mf.getName();
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\fH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */