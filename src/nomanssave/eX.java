package nomanssave;

import java.io.IOException;

public class eX extends IOException {
  final int kD;
  
  final int kE;
  
  eX(String paramString) {
    this(paramString, 1, 0);
  }
  
  eX(String paramString, int paramInt1, int paramInt2) {
    super(paramString);
    this.kD = paramInt1;
    this.kE = paramInt2;
  }
  
  eX(String paramString, IOException paramIOException, int paramInt1, int paramInt2) {
    super(paramString, paramIOException);
    this.kD = paramInt1;
    this.kE = paramInt2;
  }
  
  public int getLineNumber() {
    return this.kD;
  }
  
  public int bD() {
    return this.kE;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\eX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */