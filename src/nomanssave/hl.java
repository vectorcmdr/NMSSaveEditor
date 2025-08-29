package nomanssave;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class hl {
  private static final Pattern sK = Pattern.compile("0x([0-9a-fA-F]{1,16})");
  
  private static final Pattern sL = Pattern.compile("[0-9a-fA-F]{12}");
  
  private static final Pattern sM = Pattern.compile("([0-9a-fA-F]{4}):([0-9a-fA-F]{4}):([0-9a-fA-F]{4}):([0-9a-fA-F]{4})");
  
  private int sN;
  
  private int sO;
  
  private int sP;
  
  private int sQ;
  
  private int sR;
  
  private int sS;
  
  private static long aE(String paramString) {
    long l = 0L;
    for (byte b = 0; b < paramString.length(); b++) {
      char c = paramString.charAt(b);
      l <<= 4L;
      if (c >= 'A' && c <= 'F') {
        l |= (c - 55);
      } else if (c >= 'a' && c <= 'f') {
        l |= (c - 87);
      } else {
        l |= (c - 48);
      } 
    } 
    return l;
  }
  
  private static int a(long paramLong, int paramInt) {
    int i = -1 >>> 32 - paramInt;
    int j = Integer.MIN_VALUE >>> 32 - paramInt;
    int k = (int)(paramLong & i);
    if ((k & j) == j)
      k |= i ^ 0xFFFFFFFF; 
    return k;
  }
  
  private static int b(long paramLong, int paramInt) {
    int i = -1 >>> 32 - paramInt;
    return (int)(paramLong & i);
  }
  
  public static hl e(String paramString, int paramInt) {
    Matcher matcher;
    if ((matcher = sM.matcher(paramString)).matches()) {
      long l1 = aE(matcher.group(1)) - 2047L;
      if (l1 > 2047L)
        throw new RuntimeException("Invalid galactic coordinates"); 
      long l2 = aE(matcher.group(2)) - 127L;
      if (l2 > 127L)
        throw new RuntimeException("Invalid galactic coordinates"); 
      long l3 = aE(matcher.group(3)) - 2047L;
      if (l3 > 2047L)
        throw new RuntimeException("Invalid galactic coordinates"); 
      long l4 = aE(matcher.group(4));
      if (l4 > 65535L)
        throw new RuntimeException("Invalid galactic coordinates"); 
      return new hl((int)(l4 >> 12L & 0xFL), (int)(l4 & 0xFFFL), paramInt, (int)l2, (int)l3, (int)l1);
    } 
    if ((matcher = sL.matcher(paramString)).matches()) {
      long l = aE(paramString);
      int i = b(l >> 44L, 4);
      int j = b(l >> 32L, 12);
      int k = a(l >> 24L, 8);
      int m = a(l >> 12L, 12);
      int n = a(l, 12);
      return new hl(i, j, paramInt, k, m, n);
    } 
    throw new RuntimeException("Unable to decode value");
  }
  
  public static hl n(Object paramObject) {
    if (paramObject == null)
      return null; 
    if (paramObject instanceof Number) {
      long l = ((Number)paramObject).longValue();
      return new hl(l);
    } 
    if (paramObject instanceof String) {
      String str = (String)paramObject;
      Matcher matcher;
      if ((matcher = sK.matcher(str)).matches()) {
        long l = aE(matcher.group(1));
        return new hl(l);
      } 
      return e(str, 0);
    } 
    if (paramObject instanceof eY) {
      eY eY = (eY)paramObject;
      if (eY.contains("GalacticAddress"))
        return new hl((eY)paramObject); 
    } 
    return null;
  }
  
  private hl(eY parameY) {
    this.sN = parameY.c("GalacticAddress.PlanetIndex", 0);
    this.sO = parameY.c("GalacticAddress.SolarSystemIndex", 0);
    this.sP = parameY.c("RealityIndex", 0);
    this.sQ = parameY.c("GalacticAddress.VoxelY", 0);
    this.sR = parameY.c("GalacticAddress.VoxelZ", 0);
    this.sS = parameY.c("GalacticAddress.VoxelX", 0);
  }
  
  public hl(long paramLong) {
    this.sN = b(paramLong >> 52L, 12);
    this.sO = b(paramLong >> 40L, 12);
    this.sP = b(paramLong >> 32L, 8);
    this.sQ = a(paramLong >> 24L, 8);
    this.sR = a(paramLong >> 12L, 12);
    this.sS = a(paramLong >> 0L, 12);
  }
  
  private hl(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    this.sN = paramInt1;
    this.sO = paramInt2;
    this.sP = paramInt3;
    this.sQ = paramInt4;
    this.sR = paramInt5;
    this.sS = paramInt6;
  }
  
  public int eq() {
    return this.sN;
  }
  
  public void aL(int paramInt) {
    if (paramInt < 0 || paramInt > 15)
      throw new RuntimeException("Invalid planet index: " + paramInt); 
    this.sN = paramInt;
  }
  
  public int er() {
    return this.sO;
  }
  
  public void aM(int paramInt) {
    if (paramInt < 0 || paramInt > 4095)
      throw new RuntimeException("Invalid solar system index: " + paramInt); 
    this.sO = paramInt;
  }
  
  public int es() {
    return this.sP;
  }
  
  public void aN(int paramInt) {
    if (paramInt < 0 || paramInt > 255)
      throw new RuntimeException("Invalid reality index: " + paramInt); 
    this.sP = paramInt;
  }
  
  public int et() {
    return this.sQ;
  }
  
  public void aO(int paramInt) {
    if (paramInt < -127 || paramInt > 127)
      throw new RuntimeException("Invalid voxelY coordinate: " + paramInt); 
    this.sQ = paramInt;
  }
  
  public int eu() {
    return this.sR;
  }
  
  public void aP(int paramInt) {
    if (paramInt < -2047 || paramInt > 2047)
      throw new RuntimeException("Invalid voxelZ coordinate: " + paramInt); 
    this.sR = paramInt;
  }
  
  public int ev() {
    return this.sS;
  }
  
  public void aQ(int paramInt) {
    if (paramInt < -2047 || paramInt > 2047)
      throw new RuntimeException("Invalid voxelX coordinate: " + paramInt); 
    this.sS = paramInt;
  }
  
  public eY ew() {
    return (new fa()).d("RealityIndex", Integer.valueOf(this.sP)).d("GalacticAddress", (new fa()).d("VoxelX", Integer.valueOf(this.sS)).d("VoxelY", Integer.valueOf(this.sQ)).d("VoxelZ", Integer.valueOf(this.sR)).d("SolarSystemIndex", Integer.valueOf(this.sO)).d("PlanetIndex", Integer.valueOf(this.sN)).bH()).bH();
  }
  
  public long ex() {
    return (this.sN & 0xFL) << 52L | (this.sO & 0xFFFL) << 40L | (this.sP & 0xFFL) << 32L | (this.sQ & 0xFFL) << 24L | (this.sR & 0xFFFL) << 12L | this.sS & 0xFFFL;
  }
  
  public String ey() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(Integer.toString(this.sN & 0xF, 16));
    stringBuilder.append(Integer.toString(this.sO & 0xFFF, 16));
    while (stringBuilder.length() < 4)
      stringBuilder.insert(1, '0'); 
    stringBuilder.append(Integer.toString(this.sQ & 0xFF, 16));
    while (stringBuilder.length() < 6)
      stringBuilder.insert(4, '0'); 
    stringBuilder.append(Integer.toString(this.sR & 0xFFF, 16));
    while (stringBuilder.length() < 9)
      stringBuilder.insert(6, '0'); 
    stringBuilder.append(Integer.toString(this.sS & 0xFFF, 16));
    while (stringBuilder.length() < 12)
      stringBuilder.insert(9, '0'); 
    return stringBuilder.toString().toUpperCase();
  }
  
  public String ez() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(Integer.toString(this.sS + 2047, 16));
    while (stringBuilder.length() < 4)
      stringBuilder.insert(0, '0'); 
    stringBuilder.append(':');
    stringBuilder.append(Integer.toString(this.sQ + 127, 16));
    while (stringBuilder.length() < 9)
      stringBuilder.insert(5, '0'); 
    stringBuilder.append(':');
    stringBuilder.append(Integer.toString(this.sR + 2047, 16));
    while (stringBuilder.length() < 14)
      stringBuilder.insert(10, '0'); 
    stringBuilder.append(':');
    stringBuilder.append(Integer.toString(this.sN << 12 | this.sO, 16));
    while (stringBuilder.length() < 19)
      stringBuilder.insert(15, '0'); 
    return stringBuilder.toString().toUpperCase();
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject instanceof hl) {
      hl hl1 = (hl)paramObject;
      return (this.sN != hl1.sN) ? false : ((this.sO != hl1.sO) ? false : ((this.sP != hl1.sP) ? false : ((this.sQ != hl1.sQ) ? false : ((this.sR != hl1.sR) ? false : (!(this.sS != hl1.sS))))));
    } 
    return false;
  }
  
  public int hashCode() {
    return (int)ex();
  }
  
  public String toString() {
    return "0x" + Long.toString(ex(), 16);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\hl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */