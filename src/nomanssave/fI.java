package nomanssave;

import java.io.IOException;

public class fI {
  private static final int mg = 2001;
  
  private static final int mh = 2002;
  
  private static final int mi = 2003;
  
  private static final int mj = 2004;
  
  private static final int mk = 6;
  
  private static final int ml = 2004;
  
  private static final int mm = 384;
  
  private final int mn;
  
  private final int lM;
  
  private int mo;
  
  private byte[] data;
  
  private static final boolean ai(int paramInt) {
    return !(paramInt != 2001 && paramInt != 2002 && paramInt != 2003 && paramInt != 2004);
  }
  
  private fI(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfbyte) {
    this.mn = paramInt1;
    this.lM = paramInt2;
    this.mo = paramInt3;
    this.data = paramArrayOfbyte;
  }
  
  public int cc() {
    return this.lM;
  }
  
  public int cd() {
    return this.mo;
  }
  
  public boolean ce() {
    if (this.mo == 2001)
      return false; 
    if (this.data.length < 376) {
      byte[] arrayOfByte = new byte[376];
      System.arraycopy(this.data, 0, arrayOfByte, 0, this.data.length);
      this.data = arrayOfByte;
    } 
    this.mo = 2004;
    return true;
  }
  
  public byte[] cf() {
    return d(24, 32);
  }
  
  public void e(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte.length != 32)
      throw new IllegalArgumentException("SHA-256 must be 32 bytes"); 
    setBytes(24, paramArrayOfbyte);
  }
  
  public byte[] cg() {
    return d(8, 16);
  }
  
  public void f(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte.length != 16)
      throw new IllegalArgumentException("SpookyHash must be 16 bytes"); 
    setBytes(8, paramArrayOfbyte);
  }
  
  public int ch() {
    return getInt(56);
  }
  
  public void aj(int paramInt) {
    setInt(56, paramInt);
  }
  
  public int ci() {
    return getInt(60);
  }
  
  public void ak(int paramInt) {
    setInt(60, paramInt);
  }
  
  public int cj() {
    return getInt(76);
  }
  
  public void al(int paramInt) {
    setInt(76, paramInt);
  }
  
  public String ck() {
    switch (this.mo) {
      case 2002:
      case 2003:
      case 2004:
        return getString(88);
    } 
    return null;
  }
  
  public void Y(String paramString) {
    switch (this.mo) {
      case 2002:
      case 2003:
      case 2004:
        setString(216, paramString);
        return;
    } 
  }
  
  public String getDescription() {
    switch (this.mo) {
      case 2002:
      case 2003:
      case 2004:
        return getString(88);
    } 
    return null;
  }
  
  public void setDescription(String paramString) {
    switch (this.mo) {
      case 2002:
      case 2003:
      case 2004:
        setString(216, paramString);
        return;
    } 
  }
  
  public static fI am(int paramInt) {
    return new fI(6, paramInt, 2004, new byte[376]);
  }
  
  public static fI a(int paramInt, byte[] paramArrayOfbyte) {
    return a(paramInt, paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public static fI a(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3) {
    if (paramInt3 < 8 || paramInt3 % 4 != 0)
      throw new IOException("Invalid metadata length: " + paramInt3); 
    byte b = (paramInt3 == 104) ? 8 : 6;
    long[] arrayOfLong1 = a(paramArrayOfbyte, paramInt2, paramInt3);
    long l1 = 0L;
    int i;
    for (i = 0; i < b; i++) {
      l1 += 2654435769L;
      l1 &= 0xFFFFFFFFL;
    } 
    i = arrayOfLong1.length - 1;
    long l2 = (paramInt1 + 2) ^ 0x1422CB8CL;
    byte[] arrayOfByte1 = "NAESEVADNAYRTNRG".getBytes("US-ASCII");
    long[] arrayOfLong2 = g(arrayOfByte1);
    arrayOfLong2[0] = rotateLeft(l2, 13) * 5L + 3864292196L & 0xFFFFFFFFL;
    int j;
    for (j = 0; j < b; j++) {
      int k = (int)(l1 >>> 2L & 0x3L);
      long l3 = arrayOfLong1[0];
      int m = i;
      long l4 = 0L;
      for (int n = i; n > 0; n--) {
        l4 = l3 >> 3L ^ (arrayOfLong1[m - 1] & 0xFFFFFFFL) << 4L;
        l4 += l3 * 4L & 0xFFFFFFFFL ^ arrayOfLong1[m - 1] >> 5L;
        l4 ^= (arrayOfLong1[m - 1] ^ arrayOfLong2[n & 0x3 ^ k]) + (l3 ^ l1);
        arrayOfLong1[m] = arrayOfLong1[m] - l4 & 0xFFFFFFFFL;
        l3 = arrayOfLong1[m--];
      } 
      m = i;
      l4 = l3 >> 3L ^ (arrayOfLong1[m] & 0xFFFFFFFL) << 4L;
      l4 += l3 * 4L & 0xFFFFFFFFL ^ arrayOfLong1[m] >> 5L;
      l4 ^= (arrayOfLong1[m] ^ arrayOfLong2[k]) + (l3 ^ l1);
      arrayOfLong1[0] = arrayOfLong1[0] - l4 & 0xFFFFFFFFL;
      l1 += 1640531527L;
    } 
    if (arrayOfLong1[0] != 4008636094L)
      throw new IOException("Invalid metadata header: " + Long.toHexString(arrayOfLong1[0])); 
    j = (int)arrayOfLong1[1];
    if (!ai(j))
      throw new IOException("Invalid or unsupported format in metadata header: " + Integer.toHexString(j)); 
    byte[] arrayOfByte2 = a(arrayOfLong1, 2, arrayOfLong1.length - 2);
    return new fI(b, paramInt1, j, arrayOfByte2);
  }
  
  public byte[] encode() {
    long l1 = (this.lM + 2) ^ 0x1422CB8CL;
    byte[] arrayOfByte = "NAESEVADNAYRTNRG".getBytes("US-ASCII");
    long[] arrayOfLong1 = g(arrayOfByte);
    arrayOfLong1[0] = rotateLeft(l1, 13) * 5L + 3864292196L & 0xFFFFFFFFL;
    long[] arrayOfLong2 = g(this.data);
    long[] arrayOfLong3 = new long[2 + arrayOfLong2.length];
    arrayOfLong3[0] = 4008636094L;
    arrayOfLong3[1] = this.mo;
    System.arraycopy(arrayOfLong2, 0, arrayOfLong3, 2, arrayOfLong2.length);
    int i = arrayOfLong3.length - 1;
    long l2 = 0L;
    long l3 = 0L;
    for (byte b = 0; b < this.mn; b++) {
      l2 += -1640531527L;
      int j = (int)(l2 >> 2L & 0x3L);
      byte b1 = 0;
      long l = 0L;
      byte b2 = 0;
      while (b2 < i) {
        l = arrayOfLong3[b1 + 1] >> 3L ^ (l3 & 0xFFFFFFFL) << 4L;
        l += arrayOfLong3[b1 + 1] * 4L & 0xFFFFFFFFL ^ l3 >> 5L;
        l ^= (l3 ^ arrayOfLong1[b2 & 0x3 ^ j]) + (arrayOfLong3[b1 + 1] ^ l2);
        arrayOfLong3[b1] = arrayOfLong3[b1] + l & 0xFFFFFFFFL;
        l3 = arrayOfLong3[b1];
        b2++;
        b1++;
      } 
      l = arrayOfLong3[0] >> 3L ^ (l3 & 0xFFFFFFFL) << 4L;
      l += arrayOfLong3[0] * 4L & 0xFFFFFFFFL ^ l3 >> 5L;
      l ^= (l3 ^ arrayOfLong1[i & 0x3 ^ j]) + (arrayOfLong3[0] ^ l2);
      arrayOfLong3[i] = arrayOfLong3[i] + l & 0xFFFFFFFFL;
      l3 = arrayOfLong3[i];
    } 
    return a(arrayOfLong3, 0, arrayOfLong3.length);
  }
  
  private int getInt(int paramInt) {
    if (paramInt < 8 || paramInt % 4 != 0)
      throw new IllegalArgumentException("Invalid offset: " + paramInt); 
    paramInt -= 8;
    return this.data[paramInt] & 0xFF | (this.data[paramInt + 1] & 0xFF) << 8 | (this.data[paramInt + 2] & 0xFF) << 16 | (this.data[paramInt + 3] & 0xFF) << 24;
  }
  
  private void setInt(int paramInt1, int paramInt2) {
    if (paramInt1 < 8 || paramInt1 % 4 != 0)
      throw new IllegalArgumentException("Invalid offset: " + paramInt1); 
    paramInt1 -= 8;
    this.data[paramInt1] = (byte)(paramInt2 & 0xFF);
    this.data[paramInt1 + 1] = (byte)(paramInt2 >> 8 & 0xFF);
    this.data[paramInt1 + 2] = (byte)(paramInt2 >> 16 & 0xFF);
    this.data[paramInt1 + 3] = (byte)(paramInt2 >> 24 & 0xFF);
  }
  
  private String getString(int paramInt) {
    if (paramInt < 8 || paramInt % 4 != 0)
      throw new IllegalArgumentException("Invalid offset: " + paramInt); 
    paramInt -= 8;
    for (int i = paramInt; i < this.data.length; i++) {
      if (this.data[i] == 0)
        return new String(this.data, paramInt, i - paramInt); 
    } 
    return "";
  }
  
  private void setString(int paramInt, String paramString) {
    if (paramInt < 8 || paramInt % 4 != 0)
      throw new IllegalArgumentException("Invalid offset: " + paramInt); 
    paramInt -= 8;
    byte[] arrayOfByte = paramString.getBytes();
    System.arraycopy(arrayOfByte, 0, this.data, paramInt, arrayOfByte.length);
    paramInt += arrayOfByte.length;
    for (int i = 4 - arrayOfByte.length % 4; i > 0; i--)
      this.data[paramInt++] = 0; 
  }
  
  private byte[] d(int paramInt1, int paramInt2) {
    if (paramInt1 < 8 || paramInt1 % 4 != 0)
      throw new IllegalArgumentException("Invalid offset: " + paramInt1); 
    if (paramInt2 % 4 != 0)
      throw new IllegalArgumentException("Invalid length: " + paramInt2); 
    paramInt1 -= 8;
    byte[] arrayOfByte = new byte[paramInt2];
    System.arraycopy(this.data, paramInt1, arrayOfByte, 0, paramInt2);
    return arrayOfByte;
  }
  
  private void setBytes(int paramInt, byte[] paramArrayOfbyte) {
    if (paramInt < 8 || paramInt % 4 != 0)
      throw new IllegalArgumentException("Invalid offset: " + paramInt); 
    if (paramArrayOfbyte.length % 4 != 0)
      throw new IllegalArgumentException("Invalid length: " + paramArrayOfbyte.length); 
    paramInt -= 8;
    System.arraycopy(paramArrayOfbyte, 0, this.data, paramInt, paramArrayOfbyte.length);
  }
  
  public String toString() {
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("00000000    ");
    stringBuilder1.append("## ## ## ## ## ## ## ## ");
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("########");
    byte b1 = 8;
    for (byte b2 = 0; b2 < this.data.length; b2++) {
      if ((b2 + b1) % 16 == 0) {
        stringBuilder1.append(System.lineSeparator());
        String str;
        for (str = String.valueOf(Integer.toString((b2 + 1 + b1) / 16, 16)) + "0"; str.length() < 8; str = "0" + str);
        stringBuilder1.append(String.valueOf(str) + "    ");
      } 
      stringBuilder1.append(Integer.toString((this.data[b2] & 0xF0) >> 4, 16));
      stringBuilder1.append(Integer.toString(this.data[b2] & 0xF, 16));
      stringBuilder1.append(' ');
      if (this.data[b2] == 32) {
        stringBuilder2.append('.');
      } else if (this.data[b2] >= 32 && this.data[b2] < Byte.MAX_VALUE) {
        stringBuilder2.append((char)(this.data[b2] & 0xFF));
      } else {
        stringBuilder2.append('?');
      } 
      if ((b2 + b1) % 16 == 15) {
        stringBuilder1.append("   ");
        stringBuilder1.append(stringBuilder2);
        stringBuilder2 = new StringBuilder();
      } 
    } 
    if (stringBuilder2.length() > 0) {
      while (stringBuilder2.length() < 16) {
        stringBuilder1.append("   ");
        stringBuilder2.append(" ");
      } 
      stringBuilder1.append("   ");
      stringBuilder1.append(stringBuilder2);
    } 
    return stringBuilder1.toString();
  }
  
  private static long rotateLeft(long paramLong, int paramInt) {
    long l = (long)Math.pow(2.0D, (32 - paramInt)) - 1L;
    return (paramLong & l) << paramInt | paramLong >>> 32 - paramInt;
  }
  
  private static byte[] a(long[] paramArrayOflong, int paramInt1, int paramInt2) {
    byte[] arrayOfByte = new byte[paramInt2 * 4];
    for (byte b = 0; b < paramInt2; b++) {
      arrayOfByte[b * 4] = (byte)(int)(paramArrayOflong[paramInt1 + b] & 0xFFL);
      arrayOfByte[b * 4 + 1] = (byte)(int)(paramArrayOflong[paramInt1 + b] >> 8L & 0xFFL);
      arrayOfByte[b * 4 + 2] = (byte)(int)(paramArrayOflong[paramInt1 + b] >> 16L & 0xFFL);
      arrayOfByte[b * 4 + 3] = (byte)(int)(paramArrayOflong[paramInt1 + b] >> 24L & 0xFFL);
    } 
    return arrayOfByte;
  }
  
  private static long[] g(byte[] paramArrayOfbyte) {
    return a(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  private static long[] a(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    long[] arrayOfLong = new long[paramInt2 / 4];
    for (byte b = 0; b < paramInt2; b += 4)
      arrayOfLong[b / 4] = paramArrayOfbyte[paramInt1 + b] & 0xFFL | (paramArrayOfbyte[paramInt1 + b + 1] & 0xFFL) << 8L | (paramArrayOfbyte[paramInt1 + b + 2] & 0xFFL) << 16L | (paramArrayOfbyte[paramInt1 + b + 3] & 0xFFL) << 24L; 
    return arrayOfLong;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\fI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */