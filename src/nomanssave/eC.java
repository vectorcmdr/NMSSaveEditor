package nomanssave;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class eC {
  private static eD[] jQ = new eD[2];
  
  private final eD jR;
  
  private final eE jS;
  
  static {
    jQ[0] = c("db/jsonmap.txt", "NMS 5.21 (savegame)");
    jQ[1] = c("db/jsonmapac.txt", "NMS 5.21 (account)");
  }
  
  public static void main(String[] paramArrayOfString) {
    for (byte b = 0; b < jQ.length; b++) {
      if (jQ[b] != null)
        for (eF eF : jQ[b]) {
          String str = hashName(eF.name);
          if (!eF.key.equals(str))
            System.out.println(String.valueOf(eF.name) + " = " + eF.key + " incorrect, should be " + str); 
        }  
    } 
  }
  
  private static String hashName(String paramString) {
    long[] arrayOfLong = { 8268756125562466087L, 8268756125562466087L };
    hh.a(paramString.getBytes("UTF-8"), arrayOfLong);
    String str = "0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxy";
    long l = 0xFFFFFFFFL & arrayOfLong[0] >> 32L;
    l = l % 68L << 32L | 0xFFFFFFFFL & arrayOfLong[0];
    int i = (int)(l % 68L);
    int j = (int)((0x7FFFFFFFFFFL & arrayOfLong[0] >> 21L) % 68L);
    int k = (int)((0x3FFFFFL & arrayOfLong[0] >> 42L) % 68L);
    return new String(new char[] { str.charAt(i), str.charAt(j), str.charAt(k) });
  }
  
  public static eC a(eG parameG, String paramString) {
    eD eD1 = jQ[parameG.ordinal()];
    return (eD1 != null && eD1.s(paramString)) ? new eC(eD1) : null;
  }
  
  private static eD c(String paramString1, String paramString2) {
    InputStream inputStream = Application.class.getResourceAsStream(paramString1);
    if (inputStream == null)
      return null; 
    try {
      return new eD(inputStream, paramString2, null);
    } catch (IOException iOException) {
      hc.error("Could not load key mapping file: " + paramString1, iOException);
      return null;
    } 
  }
  
  private eC(eD parameD) {
    this.jR = parameD;
    this.jS = new eE(null, null);
  }
  
  public Map bp() {
    return this.jS.stream().collect((Collector)Collectors.toMap(parameF -> parameF.key, parameF -> parameF.name));
  }
  
  public String q(String paramString) {
    String str;
    eF eF;
    if ((eF = this.jS.t(paramString)) != null) {
      str = eF.name;
    } else if ((eF = this.jR.t(paramString)) != null) {
      str = eF.name;
    } else {
      if ((eF = this.jR.v(paramString)) != null) {
        str = eF.name;
      } else {
        hc.warn("  name mapping not found: " + paramString);
        str = paramString;
      } 
      this.jS.add(paramString, str);
    } 
    return str;
  }
  
  public String r(String paramString) {
    String str;
    eF eF;
    if ((eF = this.jS.u(paramString)) != null) {
      str = eF.key;
    } else if ((eF = this.jR.u(paramString)) != null) {
      str = eF.key;
    } else {
      str = paramString;
      if (this.jR.t(str) == null)
        hc.warn("  reverse mapping not found: " + paramString); 
    } 
    return str;
  }
  
  public String toString() {
    return this.jR.toString();
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\eC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */