package nomanssave;

import java.io.IOException;

class fD extends fH implements fs {
  final int lM;
  
  fn mc;
  
  fD(fA paramfA, int paramInt) {
    super(paramfA, "savedata" + ((paramInt < 8) ? "0" : "") + Integer.toString(paramInt + 2) + ".hg", true);
    this.lM = paramInt;
    try {
      String str = new String(ah(65536));
      this.mc = fn.T(str);
    } catch (IOException iOException) {
      hc.a("Could not read game mode from " + this.mf.getName(), iOException);
    } 
  }
  
  fD(fA paramfA, int paramInt, byte[] paramArrayOfbyte, eY parameY) {
    super(paramfA, "savedata" + ((paramInt < 8) ? "0" : "") + Integer.toString(paramInt + 2) + ".hg", false);
    this.lM = paramInt;
    this.lI = paramArrayOfbyte;
    this.mc = fn.i(parameY);
    writeBytes(fA.l(parameY));
  }
  
  public fn L() {
    return this.mc;
  }
  
  public eY M() {
    return fA.b(readBytes(), eG.jT);
  }
  
  public String b(eY parameY) {
    a((this.lM == 0) ? "ps4_backup" : ("ps4_backup" + (this.lM + 1)), this.mc, getName(), getDescription());
    writeBytes(fA.l(parameY));
    return K();
  }
  
  public long lastModified() {
    return this.mf.lastModified();
  }
  
  public String toString() {
    return K();
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\fD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */