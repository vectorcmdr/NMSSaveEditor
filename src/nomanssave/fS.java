package nomanssave;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public class fS {
  private final File mf;
  
  private int lJ;
  
  private int version;
  
  private long mw;
  
  private int mx;
  
  private int my;
  
  private byte[] mz;
  
  private String name;
  
  private String description;
  
  private int lK;
  
  fS(File paramFile) {
    this.mf = paramFile;
  }
  
  void cn() {
    Exception exception1 = null;
    Exception exception2 = null;
  }
  
  void read(InputStream paramInputStream) {
    this.lJ = hk.readInt(paramInputStream);
    if (this.lJ != 0)
      hc.debug("  unknown1: " + Integer.toHexString(this.lJ)); 
    this.version = hk.readInt(paramInputStream);
    if (this.version != 0)
      hc.info("  version: " + this.version); 
    this.mw = hk.f(paramInputStream);
    if (this.mw != 0L)
      hc.info("  totalPlayTime: " + fq.c(this.mw)); 
    if (this.lJ == 1) {
      this.mx = hk.readInt(paramInputStream);
      if (this.mx != 0)
        hc.debug("  decompressed: " + this.mx); 
      this.my = 0;
      this.mz = new byte[128];
      hk.readFully(paramInputStream, this.mz);
    } else {
      this.mx = 0;
      this.my = hk.readInt(paramInputStream);
      if (this.my != 0)
        hc.debug("  compressed: " + this.my); 
      this.mz = null;
      this.name = gc.e(paramInputStream);
      if (this.name.length() != 0)
        hc.debug("  name: " + this.name); 
      this.description = gc.e(paramInputStream);
      if (this.description.length() != 0)
        hc.debug("  description: " + this.description); 
    } 
    this.lK = hk.readInt(paramInputStream);
    if (this.lK != 0)
      hc.debug("  unknown2: " + Integer.toHexString(this.lK)); 
  }
  
  void write() {
    Exception exception1 = null;
    Exception exception2 = null;
  }
  
  void write(OutputStream paramOutputStream) {
    hk.a(paramOutputStream, this.lJ);
    hk.a(paramOutputStream, this.version);
    hk.b(paramOutputStream, this.mw);
    if (this.mz != null) {
      hk.a(paramOutputStream, this.mx);
      paramOutputStream.write(this.mz);
    } else {
      hk.a(paramOutputStream, this.my);
      gc.c(paramOutputStream, this.name);
      gc.c(paramOutputStream, this.description);
    } 
    hk.a(paramOutputStream, this.lK);
  }
  
  byte[] co() {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    write(byteArrayOutputStream);
    return byteArrayOutputStream.toByteArray();
  }
  
  boolean cp() {
    return (this.mz == null);
  }
  
  int getVersion() {
    return this.version;
  }
  
  void setVersion(int paramInt) {
    this.version = paramInt;
  }
  
  int ch() {
    return this.mx;
  }
  
  void aj(int paramInt) {
    if (this.mz != null)
      this.mx = paramInt; 
  }
  
  int ci() {
    return this.my;
  }
  
  void ak(int paramInt) {
    if (this.mz == null)
      this.my = paramInt; 
  }
  
  String ck() {
    return this.name;
  }
  
  void Y(String paramString) {
    this.name = paramString;
  }
  
  String getDescription() {
    return this.description;
  }
  
  long cq() {
    return this.mw;
  }
  
  void d(long paramLong) {
    this.mw = paramLong;
  }
  
  String getName() {
    return this.mf.getName();
  }
  
  long length() {
    return this.mf.length();
  }
  
  void a(fS paramfS) {
    this.lJ = paramfS.lJ;
    this.version = paramfS.version;
    this.mw = paramfS.mw;
    this.my = paramfS.my;
    this.mx = paramfS.mx;
    this.mz = paramfS.mz;
    this.name = paramfS.name;
    this.description = paramfS.description;
    this.lK = paramfS.lK;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\fS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */