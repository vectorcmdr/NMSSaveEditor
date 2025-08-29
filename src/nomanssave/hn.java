package nomanssave;

import java.io.IOException;
import java.io.InputStream;

class hn extends InputStream {
  private int rZ;
  
  private hn(hm paramhm, int paramInt) {
    this.rZ = paramInt;
  }
  
  public int read() {
    if (this.rZ == 0)
      return -1; 
    int i = hm.a(this.sT).read();
    if (i < 0)
      throw new IOException("short read"); 
    this.rZ--;
    return i;
  }
  
  public int read(byte[] paramArrayOfbyte) {
    return read(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    if (this.rZ == 0)
      return -1; 
    if (paramInt2 > this.rZ)
      paramInt2 = this.rZ; 
    paramInt2 = hm.a(this.sT).read(paramArrayOfbyte, paramInt1, paramInt2);
    if (paramInt2 <= 0)
      throw new IOException("short read"); 
    this.rZ -= paramInt2;
    return paramInt2;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\hn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */