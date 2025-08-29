package nomanssave;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

abstract class fX {
  final fW mM;
  
  final File mV;
  
  final File mW;
  
  final fS mX;
  
  int mode;
  
  fX(fT paramfT, fV paramfV) {
    int i = fT.c(paramfT).size();
    for (byte b = 0; b < fT.c(paramfT).size(); b++) {
      int j = ((fW)fT.c(paramfT).get(b)).name.compareTo(paramfV.mM.name);
      if (j == 0) {
        fW fW1 = fT.c(paramfT).remove(b);
        fT.i(new File(fT.d(paramfT), fW1.mS));
      } 
      if (j >= 0) {
        i = b;
        break;
      } 
    } 
    this.mM = new fW(paramfT, paramfV.mM);
    File file = new File(fT.d(paramfT), this.mM.mS);
    if (!file.mkdir())
      throw new IOException("Unable to create container path"); 
    this.mV = new File(file, "container." + this.mM.mR);
    this.mX = new fS(new File(file, paramfV.mN));
    this.mW = new File(file, paramfV.mP);
    FileOutputStream fileOutputStream = new FileOutputStream(this.mV);
    try {
      paramfV.a(fileOutputStream);
    } finally {
      fileOutputStream.close();
    } 
    fT.c(paramfT).add(i, this.mM);
  }
  
  fX(fT paramfT, String paramString) {
    this.mM = fT.a(paramfT, paramString);
    File file1 = new File(fT.d(paramfT), this.mM.mS);
    if (!file1.isDirectory())
      throw new FileNotFoundException(this.mM.mS); 
    this.mV = new File(file1, "container." + this.mM.mR);
    hc.info(this.mM.filename);
    File file2 = null;
    File file3 = null;
    FileInputStream fileInputStream = new FileInputStream(this.mV);
    try {
      int i = hk.readInt(fileInputStream);
      hc.debug("  header: " + Integer.toHexString(i));
      int j = hk.readInt(fileInputStream);
      for (byte b = 0; b < j; b++) {
        String str1 = gc.d(fileInputStream);
        hc.debug("  name: " + str1);
        String str2 = gc.a(fileInputStream);
        hc.debug("  filename: " + str2);
        String str3 = gc.a(fileInputStream);
        if (!str2.equals(str3))
          hc.debug("  filename2: " + str3); 
        if (str1.equals("data")) {
          file2 = new File(file1, str2);
          if (!file2.exists())
            file2 = new File(file1, str3); 
        } 
        if (str1.equals("meta")) {
          file3 = new File(file1, str2);
          if (!file3.exists())
            file3 = new File(file1, str3); 
        } 
      } 
    } finally {
      fileInputStream.close();
    } 
    if (file2 == null || file3 == null)
      throw new FileNotFoundException("data/meta file missing"); 
    long l = file3.length() + file2.length();
    if (this.mM.mU != l)
      throw new IOException("data size mismatch: " + this.mM.mU); 
    this.mW = file2;
    this.mX = new fS(file3);
    this.mX.cn();
  }
  
  public String K() {
    return this.mM.filename;
  }
  
  private InputStream getInputStream() {
    InputStream inputStream = fT.b(new FileInputStream(this.mW), this.mX.ch());
    if (inputStream instanceof gX) {
      this.mode = fT.cv();
    } else if (inputStream instanceof hm) {
      this.mode = fT.cw();
    } else {
      this.mode = fT.cx();
    } 
    return inputStream;
  }
  
  private OutputStream getOutputStream() {
    FileOutputStream fileOutputStream = new FileOutputStream(this.mW);
    try {
      if (this.mode == fT.cv())
        return new gZ(fileOutputStream); 
      if (this.mode == fT.cw()) {
        fileOutputStream.write(fT.cy());
        return new ho(fileOutputStream);
      } 
      return new hb(fileOutputStream);
    } catch (IOException iOException) {
      try {
        fileOutputStream.close();
      } catch (IOException iOException1) {}
      throw iOException;
    } 
  }
  
  eY a(eG parameG) {
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
  
  byte[] ah(int paramInt) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    Exception exception1 = null;
    Exception exception2 = null;
  }
  
  void h(eY parameY) {
    byte[] arrayOfByte;
    boolean bool = (this.mode == fT.cw()) ? true : false;
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    Exception exception1 = null;
    Exception exception2 = null;
    try {
      fj fj = new fj(byteArrayOutputStream, bool ? 0 : 2);
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
    exception2 = null;
    Exception exception3 = null;
    try {
      OutputStream outputStream = getOutputStream();
      try {
        outputStream.write(arrayOfByte);
        if (bool) {
          outputStream.flush();
          outputStream.write(0);
        } 
      } finally {
        if (outputStream != null)
          outputStream.close(); 
      } 
    } finally {
      exception3 = null;
      if (exception2 == null) {
        exception2 = exception3;
      } else if (exception2 != exception3) {
        exception2.addSuppressed(exception3);
      } 
    } 
    this.mM.timestamp = System.currentTimeMillis();
    this.mM.mU = this.mW.length() + this.mX.length();
    fT.e(this.mL);
  }
  
  void a(String paramString, fn paramfn) {
    Properties properties = new Properties();
    properties.setProperty("MetaFile", this.mX.getName());
    properties.setProperty("DataFile", this.mW.getName());
    properties.setProperty("ContainerFile", this.mV.getName());
    if (paramfn != null)
      properties.setProperty("GameMode", paramfn.name()); 
    properties.setProperty("IndexData", this.mM.cz());
    String str = String.valueOf(paramString) + "." + System.currentTimeMillis() + ".zip";
    File file = new File(aH.cG, str);
    ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(file));
    try {
      ZipEntry zipEntry = new ZipEntry(this.mX.getName());
      zipOutputStream.putNextEntry(zipEntry);
      zipOutputStream.write(this.mX.co());
      zipEntry = new ZipEntry(this.mW.getName());
      zipOutputStream.putNextEntry(zipEntry);
      byte[] arrayOfByte = new byte[1024];
      FileInputStream fileInputStream = new FileInputStream(this.mW);
      try {
        int i;
        while ((i = fileInputStream.read(arrayOfByte)) > 0)
          zipOutputStream.write(arrayOfByte, 0, i); 
      } finally {
        fileInputStream.close();
      } 
      zipEntry = new ZipEntry(this.mV.getName());
      zipOutputStream.putNextEntry(zipEntry);
      fileInputStream = new FileInputStream(this.mV);
      try {
        int i;
        while ((i = fileInputStream.read(arrayOfByte)) > 0)
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
    file.setLastModified(this.mM.timestamp);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\fX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */