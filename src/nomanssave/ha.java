package nomanssave;

import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ha extends FilterInputStream {
  private boolean sg;
  
  private byte[] buffer;
  
  private int sh;
  
  private int sd;
  
  private int si;
  
  private boolean eof;
  
  public ha(InputStream paramInputStream, int paramInt) {
    super(paramInputStream);
    if (paramInt == 0) {
      this.sg = true;
      this.buffer = new byte[1048576];
    } else {
      this.sg = false;
      this.buffer = new byte[paramInt];
    } 
    this.sd = 0;
    this.sh = 0;
    this.eof = false;
  }
  
  private void aJ(int paramInt) {
    if (this.sd + paramInt > this.buffer.length) {
      if (!this.sg)
        throw new IOException("buffer exceeded"); 
      int i = this.buffer.length;
      while (true) {
        i += 1048576;
        if (this.sd + paramInt <= i) {
          byte[] arrayOfByte = new byte[i];
          System.arraycopy(this.buffer, 0, arrayOfByte, 0, this.sd);
          this.buffer = arrayOfByte;
          break;
        } 
      } 
    } 
  }
  
  private boolean el() {
    if (this.eof)
      return false; 
    int i = super.read();
    if (i < 0) {
      if (this.sg) {
        this.eof = true;
        return false;
      } 
      throw new EOFException("Unexpected end of stream");
    } 
    int j = i >> 4;
    int k = i & 0xF;
    if (j == 15)
      do {
        i = super.read();
        if (i < 0)
          throw new EOFException("Unexpected end of literal length"); 
        j += i;
      } while (i == 255); 
    if (j > 0) {
      int i1 = j;
      aJ(i1);
      while ((i = super.read(this.buffer, this.sd, i1)) > 0) {
        this.sd += i;
        i1 -= i;
        if (i1 == 0)
          break; 
      } 
      if (i1 > 0)
        throw new EOFException("Unexpected end of literal value"); 
    } 
    if (this.sd == this.buffer.length && !this.sg) {
      this.eof = true;
      return true;
    } 
    int m = super.read();
    if (m < 0) {
      if (this.sg) {
        this.eof = true;
        return true;
      } 
      throw new EOFException("Unexpected end of offset");
    } 
    int n = super.read();
    if (n < 0)
      throw new EOFException("Unexpected end of offset"); 
    m |= n << 8;
    if (k == 15)
      do {
        i = super.read();
        if (i < 0)
          throw new EOFException("Unexpected end of literal length"); 
        k += i;
      } while (i == 255); 
    k += 4;
    if (m == 0)
      throw new EOFException("Offset is zero!"); 
    if (m > this.sd)
      throw new EOFException("Buffer too small"); 
    aJ(k);
    if (k > m) {
      int i1 = this.sd - m;
      while (true) {
        System.arraycopy(this.buffer, i1, this.buffer, this.sd, m);
        k -= m;
        this.sd += m;
        if (k <= m) {
          System.arraycopy(this.buffer, i1, this.buffer, this.sd, k);
          this.sd += k;
          this.si = Math.max(this.si, m);
          return true;
        } 
      } 
    } 
    System.arraycopy(this.buffer, this.sd - m, this.buffer, this.sd, k);
    this.sd += k;
    this.si = Math.max(this.si, m);
    return true;
  }
  
  public int available() {
    return (this.sh == this.sd && !el()) ? 0 : (this.sd - this.sh);
  }
  
  public int read() {
    return (this.sh == this.sd && !el()) ? -1 : (0xFF & this.buffer[this.sh++]);
  }
  
  public int read(byte[] paramArrayOfbyte) {
    return read(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    if (this.sh == this.sd && !el())
      return -1; 
    int i = Math.min(paramInt2 - paramInt1, this.sd - this.sh);
    System.arraycopy(this.buffer, this.sh, paramArrayOfbyte, paramInt1, i);
    this.sh += i;
    return i;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\ha.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */