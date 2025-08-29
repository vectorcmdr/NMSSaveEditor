package nomanssave;

import java.io.FilterOutputStream;
import java.io.OutputStream;
import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;

public class hb extends FilterOutputStream {
  private static final LZ4Factory sb = LZ4Factory.safeInstance();
  
  private static final int sj = 65536;
  
  private final LZ4Compressor sc = sb.fastCompressor();
  
  private byte[] buffer = new byte[65536];
  
  private int sd = 0;
  
  private int sf = 0;
  
  public hb(OutputStream paramOutputStream) {
    super(paramOutputStream);
  }
  
  private void aK(int paramInt) {
    if (this.sd + paramInt > this.buffer.length) {
      paramInt = this.buffer.length + paramInt;
      int i = (this.buffer.length + paramInt) / 65536;
      if ((this.buffer.length + paramInt) % 65536 > 0)
        i++; 
      i *= 65536;
      byte[] arrayOfByte = new byte[i];
      System.arraycopy(this.buffer, 0, arrayOfByte, 0, this.sd);
      this.buffer = arrayOfByte;
    } 
  }
  
  public void write(int paramInt) {
    aK(1);
    this.buffer[this.sd++] = (byte)paramInt;
  }
  
  public void write(byte[] paramArrayOfbyte) {
    write(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    aK(paramInt2);
    System.arraycopy(paramArrayOfbyte, paramInt1, this.buffer, this.sd, paramInt2);
    this.sd += paramInt2;
  }
  
  public int ch() {
    return this.sd;
  }
  
  public int ci() {
    return this.sf;
  }
  
  public void flush() {
    this.out.flush();
  }
  
  public void close() {
    try {
      if (this.sd > 0) {
        int i = this.sc.maxCompressedLength(this.sd);
        byte[] arrayOfByte = new byte[i];
        this.sf = this.sc.compress(this.buffer, 0, this.sd, arrayOfByte, 0, i);
        this.out.write(arrayOfByte, 0, this.sf);
      } 
    } finally {
      this.out.close();
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\hb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */