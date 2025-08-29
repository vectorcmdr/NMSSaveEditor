package nomanssave;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class gX extends FilterInputStream {
  private ha rX;
  
  private int rY;
  
  public gX(InputStream paramInputStream, byte[] paramArrayOfbyte) {
    super(paramInputStream);
    int i = 0xFF & paramArrayOfbyte[4] | (0xFF & paramArrayOfbyte[5]) << 8 | (0xFF & paramArrayOfbyte[6]) << 16 | (0xFF & paramArrayOfbyte[7]) << 24;
    int j = 0xFF & paramArrayOfbyte[8] | (0xFF & paramArrayOfbyte[9]) << 8 | (0xFF & paramArrayOfbyte[10]) << 16 | (0xFF & paramArrayOfbyte[11]) << 24;
    this.rX = new ha(new gY(this, i, null), j);
    this.rY = 1;
  }
  
  public int getFrameCount() {
    return this.rY;
  }
  
  private boolean ej() {
    byte[] arrayOfByte = new byte[16];
    int i = this.in.read(arrayOfByte, 0, 16);
    if (i < 0) {
      this.rX = null;
      return false;
    } 
    if (i < 16)
      throw new IOException("Short read " + i); 
    if ((0xFF & arrayOfByte[0]) == 229 && (0xFF & arrayOfByte[1]) == 161 && (0xFF & arrayOfByte[2]) == 237 && (0xFF & arrayOfByte[3]) == 254) {
      int j = 0xFF & arrayOfByte[4] | (0xFF & arrayOfByte[5]) << 8 | (0xFF & arrayOfByte[6]) << 16 | (0xFF & arrayOfByte[7]) << 24;
      int k = 0xFF & arrayOfByte[8] | (0xFF & arrayOfByte[9]) << 8 | (0xFF & arrayOfByte[10]) << 16 | (0xFF & arrayOfByte[11]) << 24;
      this.rX = new ha(new gY(this, j, null), k);
      this.rY++;
      return true;
    } 
    throw new IOException("Invalid header");
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


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\gX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */