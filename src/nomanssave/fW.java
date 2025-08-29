package nomanssave;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

class fW {
  final String name;
  
  final String filename;
  
  final String id;
  
  int mR;
  
  final int lJ;
  
  String mS;
  
  long timestamp;
  
  final long mT;
  
  long mU;
  
  fW(fT paramfT, InputStream paramInputStream) {
    this.name = gc.c(paramInputStream);
    hc.info("  " + this.name);
    this.filename = gc.c(paramInputStream);
    hc.debug("    filename: " + this.filename);
    this.id = gc.c(paramInputStream);
    hc.debug("    id: " + this.id);
    this.mR = paramInputStream.read();
    if (this.mR < 0)
      throw new IOException("short read"); 
    hc.debug("    suffix: " + this.mR);
    this.lJ = hk.readInt(paramInputStream);
    if (this.lJ != 0)
      hc.debug("    unknown1: " + Integer.toHexString(this.lJ)); 
    this.mS = gc.a(paramInputStream);
    hc.debug("    containerPath: " + this.mS);
    this.timestamp = gc.b(paramInputStream);
    hc.debug("    timestamp: " + new Date(this.timestamp));
    this.mT = hk.f(paramInputStream);
    if (this.mT != 0L)
      hc.debug("    unknown2: " + Long.toHexString(this.mT)); 
    this.mU = hk.f(paramInputStream);
    hc.debug("    totalSize: " + this.mU);
  }
  
  fW(fT paramfT, String paramString) {
    this(paramfT, new ByteArrayInputStream(hk.aD(paramString)));
  }
  
  fW(fT paramfT, fW paramfW) {
    this.name = paramfW.name;
    this.filename = paramfW.filename;
    this.id = paramfW.id;
    this.mR = paramfW.mR;
    this.lJ = paramfW.lJ;
    this.mS = fT.a(paramfT);
    this.timestamp = paramfW.timestamp;
    this.mT = paramfW.mT;
    this.mU = paramfW.mU;
  }
  
  void write(OutputStream paramOutputStream) {
    gc.b(paramOutputStream, this.name);
    gc.b(paramOutputStream, this.filename);
    gc.b(paramOutputStream, this.id);
    paramOutputStream.write(this.mR);
    hk.a(paramOutputStream, this.lJ);
    gc.a(paramOutputStream, this.mS);
    gc.a(paramOutputStream, this.timestamp);
    hk.b(paramOutputStream, this.mT);
    hk.b(paramOutputStream, this.mU);
  }
  
  String cz() {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    write(byteArrayOutputStream);
    return hk.k(byteArrayOutputStream.toByteArray());
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\fW.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */