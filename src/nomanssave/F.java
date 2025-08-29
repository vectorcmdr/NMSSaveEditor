package nomanssave;

import java.io.IOException;

class F implements fs {
  final String filename;
  
  final long bd;
  
  final fn be;
  
  final eY bf;
  
  public F(Application paramApplication, String paramString, long paramLong, fn paramfn, eY parameY) {
    this.filename = paramString;
    this.bd = paramLong;
    this.be = paramfn;
    this.bf = parameY;
  }
  
  public String K() {
    return this.filename;
  }
  
  public fn L() {
    return this.be;
  }
  
  public long lastModified() {
    return this.bd;
  }
  
  public eY M() {
    return this.bf;
  }
  
  public String b(eY parameY) {
    throw new IOException("Save not supported!");
  }
  
  public String toString() {
    return this.filename;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\F.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */