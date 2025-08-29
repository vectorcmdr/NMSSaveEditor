package nomanssave;

import java.io.IOException;
import java.io.InputStream;

class gY extends InputStream {
  private int rZ;
  
  private gY(gX paramgX, int paramInt) {
    this.rZ = paramInt;
  }
  
  public int read() {
    if (this.rZ == 0)
      return -1; 
    int i = gX.a(this.sa).read();
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
    paramInt2 = gX.a(this.sa).read(paramArrayOfbyte, paramInt1, paramInt2);
    if (paramInt2 <= 0)
      throw new IOException("short read"); 
    this.rZ -= paramInt2;
    return paramInt2;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\gY.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */