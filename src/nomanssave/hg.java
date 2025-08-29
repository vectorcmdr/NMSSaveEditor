package nomanssave;

import java.security.SecureRandom;

public class hg {
  private static final SecureRandom ss = new SecureRandom();
  
  private final long st;
  
  public static hg aB(String paramString) {
    paramString = paramString.trim();
    if (!paramString.startsWith("0x"))
      throw new RuntimeException("Invalid seed: " + paramString); 
    long l = Long.parseUnsignedLong(paramString.substring(2), 16);
    return new hg(l);
  }
  
  public static hg eo() {
    return new hg(ss.nextLong());
  }
  
  public hg(long paramLong) {
    this.st = paramLong;
  }
  
  public String toString() {
    return "0x" + Long.toHexString(this.st).toUpperCase();
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\hg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */