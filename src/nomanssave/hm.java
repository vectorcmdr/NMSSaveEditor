package nomanssave;

import java.io.FilterInputStream;
import java.io.InputStream;

public class hm extends FilterInputStream {
  private ha rX;
  
  private int rY;
  
  public hm(InputStream paramInputStream) {
    super(paramInputStream);
    byte[] arrayOfByte = new byte[8];
    hk.readFully(paramInputStream, arrayOfByte);
    int i = 0xFF & arrayOfByte[0] | (0xFF & arrayOfByte[1]) << 8 | (0xFF & arrayOfByte[2]) << 16 | (0xFF & arrayOfByte[3]) << 24;
    int j = 0xFF & arrayOfByte[4] | (0xFF & arrayOfByte[5]) << 8 | (0xFF & arrayOfByte[6]) << 16 | (0xFF & arrayOfByte[7]) << 24;
    this.rX = new ha(new hn(this, j, null), i);
    this.rY = 1;
  }
  
  public int getFrameCount() {
    return this.rY;
  }
  
  private boolean ej() {
    byte[] arrayOfByte = new byte[8];
    hk.readFully(this.in, arrayOfByte);
    int i = 0xFF & arrayOfByte[0] | (0xFF & arrayOfByte[1]) << 8 | (0xFF & arrayOfByte[2]) << 16 | (0xFF & arrayOfByte[3]) << 24;
    int j = 0xFF & arrayOfByte[4] | (0xFF & arrayOfByte[5]) << 8 | (0xFF & arrayOfByte[6]) << 16 | (0xFF & arrayOfByte[7]) << 24;
    this.rX = new ha(new hn(this, j, null), i);
    this.rY++;
    return true;
  }
  
  public int read() {
    return (this.rX == null || (this.rX.available() == 0 && !ej())) ? -1 : this.rX.read();
  }
  
  public int read(byte[] paramArrayOfbyte) {
    return read(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    return (this.rX == null || (this.rX.available() == 0 && !ej())) ? -1 : this.rX.read(paramArrayOfbyte, paramInt1, paramInt2);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\hm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */