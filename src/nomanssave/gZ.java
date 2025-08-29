package nomanssave;

import java.io.FilterOutputStream;
import java.io.OutputStream;
import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;

public class gZ extends FilterOutputStream {
  private static final LZ4Factory sb = LZ4Factory.safeInstance();
  
  private final LZ4Compressor sc = sb.fastCompressor();
  
  private byte[] buffer = new byte[524288];
  
  private int sd = 0;
  
  private int se = 0;
  
  private int sf = 0;
  
  public gZ(OutputStream paramOutputStream) {
    super(paramOutputStream);
  }
  
  public void write(int paramInt) {
    if (this.sd == this.buffer.length)
      ek(); 
    this.buffer[this.sd++] = (byte)paramInt;
  }
  
  public void write(byte[] paramArrayOfbyte) {
    write(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    if (paramInt2 == this.buffer.length)
      ek(); 
    while (paramInt2 >= this.buffer.length - this.sd) {
      int i = this.buffer.length - this.sd;
      System.arraycopy(paramArrayOfbyte, paramInt1, this.buffer, this.sd, i);
      this.sd = this.buffer.length;
      ek();
      paramInt1 += i;
      paramInt2 -= i;
    } 
    if (paramInt2 > 0) {
      System.arraycopy(paramArrayOfbyte, paramInt1, this.buffer, this.sd, paramInt2);
      this.sd += paramInt2;
    } 
  }
  
  private void ek() {
    int i = this.sc.maxCompressedLength(this.sd);
    byte[] arrayOfByte1 = new byte[i];
    int j = this.sc.compress(this.buffer, 0, this.sd, arrayOfByte1, 0, i);
    byte[] arrayOfByte2 = new byte[16];
    arrayOfByte2[0] = -27;
    arrayOfByte2[1] = -95;
    arrayOfByte2[2] = -19;
    arrayOfByte2[3] = -2;
    arrayOfByte2[4] = (byte)(0xFF & j);
    arrayOfByte2[5] = (byte)(0xFF & j >> 8);
    arrayOfByte2[6] = (byte)(0xFF & j >> 16);
    arrayOfByte2[7] = (byte)(0xFF & j >> 24);
    arrayOfByte2[8] = (byte)(0xFF & this.sd);
    arrayOfByte2[9] = (byte)(0xFF & this.sd >> 8);
    arrayOfByte2[10] = (byte)(0xFF & this.sd >> 16);
    arrayOfByte2[11] = (byte)(0xFF & this.sd >> 24);
    this.out.write(arrayOfByte2);
    this.out.write(arrayOfByte1, 0, j);
    this.se += this.sd;
    this.sd = 0;
    this.sf += j + 16;
  }
  
  public int ch() {
    return this.se;
  }
  
  public int ci() {
    return this.sf;
  }
  
  public void flush() {
    if (this.sd > 0)
      ek(); 
    this.out.flush();
  }
  
  public void close() {
    try {
      if (this.sd > 0)
        ek(); 
    } finally {
      this.out.close();
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\gZ.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */