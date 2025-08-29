package nomanssave;

public class hf {
  public static int b(String paramString, int paramInt1, int paramInt2) {
    paramString = paramString.trim();
    if (paramString.length() == 0)
      throw new RuntimeException("No digits found"); 
    long l = 0L;
    for (byte b = 0; b < paramString.length(); b++) {
      l *= 10L;
      char c = paramString.charAt(b);
      if (c >= '0' && c <= '9') {
        l += (c - 48);
      } else {
        throw new RuntimeException("Invalid digit: " + c);
      } 
      if (l > paramInt2)
        return paramInt2; 
    } 
    return (l < paramInt1) ? paramInt1 : (int)l;
  }
  
  public static long a(String paramString, long paramLong1, long paramLong2) {
    paramString = paramString.trim();
    if (paramString.length() == 0)
      throw new RuntimeException("No digits found"); 
    long l = 0L;
    for (byte b = 0; b < paramString.length(); b++) {
      l *= 10L;
      char c = paramString.charAt(b);
      if (c >= '0' && c <= '9') {
        l += (c - 48);
      } else {
        throw new RuntimeException("Invalid digit: " + c);
      } 
      if (l > paramLong2)
        return paramLong2; 
    } 
    return (l < paramLong1) ? paramLong1 : l;
  }
  
  public static double a(String paramString, double paramDouble1, double paramDouble2) {
    paramString = paramString.trim();
    double d = Double.parseDouble(paramString);
    return (d < paramDouble1) ? paramDouble1 : ((d > paramDouble2) ? paramDouble2 : d);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\hf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */