package nomanssave;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

class he extends OutputStream {
  final PrintStream sp;
  
  final String sq;
  
  final ByteArrayOutputStream sr;
  
  he(PrintStream paramPrintStream, String paramString) {
    this.sp = paramPrintStream;
    this.sq = paramString;
    this.sr = new ByteArrayOutputStream();
  }
  
  public void write(int paramInt) {
    this.sp.write(paramInt);
    this.sr.write(paramInt);
    if (paramInt == 10) {
      if (hc.en() != null)
        synchronized (hc.en()) {
          hc.en().write(this.sq.getBytes());
          hc.en().write(this.sr.toByteArray());
        }  
      this.sr.reset();
    } 
  }
  
  public void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    if (this.sp != null)
      this.sp.write(paramArrayOfbyte, paramInt1, paramInt2); 
    for (byte b = 0; b < paramInt2; b++) {
      if (paramArrayOfbyte[paramInt1 + b] == 10) {
        this.sr.write(paramArrayOfbyte, paramInt1, b + 1);
        if (hc.en() != null)
          synchronized (hc.en()) {
            hc.en().write(this.sq.getBytes());
            hc.en().write(this.sr.toByteArray());
          }  
        this.sr.reset();
        paramInt2 -= b + 1;
        paramInt1 = b + 1;
        b = -1;
      } 
    } 
    this.sr.write(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public void flush() {
    if (this.sr.size() > 0) {
      this.sr.write(System.lineSeparator().getBytes());
      if (hc.en() != null)
        synchronized (hc.en()) {
          hc.en().write(this.sq.getBytes());
          hc.en().write(this.sr.toByteArray());
        }  
      this.sr.reset();
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\he.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */