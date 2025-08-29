package nomanssave;

public class hh {
  private static final long su = 255L;
  
  private static final int sv = 12;
  
  private static final int sw = 96;
  
  private static final int sx = 48;
  
  private static final int sy = 12;
  
  private static final int sz = 192;
  
  private static final int sA = 96;
  
  private static final int sB = 24;
  
  private static final long sC = -2401053088876216593L;
  
  private final long sD;
  
  private final long sE;
  
  private static long a(byte[] paramArrayOfbyte, int paramInt) {
    return (paramArrayOfbyte[paramInt + 7] & 0xFFL) << 56L | (paramArrayOfbyte[paramInt + 6] & 0xFFL) << 48L | (paramArrayOfbyte[paramInt + 5] & 0xFFL) << 40L | (paramArrayOfbyte[paramInt + 4] & 0xFFL) << 32L | (paramArrayOfbyte[paramInt + 3] & 0xFFL) << 24L | (paramArrayOfbyte[paramInt + 2] & 0xFFL) << 16L | (paramArrayOfbyte[paramInt + 1] & 0xFFL) << 8L | paramArrayOfbyte[paramInt] & 0xFFL;
  }
  
  private static long b(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    long l = 0L;
    switch (paramInt2) {
      case 7:
        l += (paramArrayOfbyte[paramInt1 + 6] & 0xFFL) << 48L;
      case 6:
        l += (paramArrayOfbyte[paramInt1 + 5] & 0xFFL) << 40L;
      case 5:
        l += (paramArrayOfbyte[paramInt1 + 4] & 0xFFL) << 32L;
      case 4:
        l += (paramArrayOfbyte[paramInt1 + 3] & 0xFFL) << 24L;
      case 3:
        l += (paramArrayOfbyte[paramInt1 + 2] & 0xFFL) << 16L;
      case 2:
        l += (paramArrayOfbyte[paramInt1 + 1] & 0xFFL) << 8L;
      case 1:
        l += paramArrayOfbyte[paramInt1] & 0xFFL;
        break;
    } 
    return l;
  }
  
  private static void a(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, long[] paramArrayOflong) {
    long l1 = paramArrayOflong[0];
    long l2 = paramArrayOflong[1];
    long l3 = -2401053088876216593L;
    long l4 = -2401053088876216593L;
    int i = paramInt2;
    int j = paramInt1;
    while (i >= 32) {
      l3 += a(paramArrayOfbyte, j);
      l4 += a(paramArrayOfbyte, j + 8);
      l3 = l3 << 50L | l3 >>> 14L;
      l3 += l4;
      l1 ^= l3;
      l4 = l4 << 52L | l4 >>> 12L;
      l4 += l1;
      l2 ^= l4;
      l1 = l1 << 30L | l1 >>> 34L;
      l1 += l2;
      l3 ^= l1;
      l2 = l2 << 41L | l2 >>> 23L;
      l2 += l3;
      l4 ^= l2;
      l3 = l3 << 54L | l3 >>> 10L;
      l3 += l4;
      l1 ^= l3;
      l4 = l4 << 48L | l4 >>> 16L;
      l4 += l1;
      l2 ^= l4;
      l1 = l1 << 38L | l1 >>> 26L;
      l1 += l2;
      l3 ^= l1;
      l2 = l2 << 37L | l2 >>> 27L;
      l2 += l3;
      l4 ^= l2;
      l3 = l3 << 62L | l3 >>> 2L;
      l3 += l4;
      l1 ^= l3;
      l4 = l4 << 34L | l4 >>> 30L;
      l4 += l1;
      l2 ^= l4;
      l1 = l1 << 5L | l1 >>> 59L;
      l1 += l2;
      l3 ^= l1;
      l2 = l2 << 36L | l2 >>> 28L;
      l2 += l3;
      l4 ^= l2;
      l1 += a(paramArrayOfbyte, j + 16);
      l2 += a(paramArrayOfbyte, j + 24);
      j += 32;
      i -= 32;
    } 
    if (i >= 16) {
      l3 += a(paramArrayOfbyte, j);
      l4 += a(paramArrayOfbyte, j + 8);
      j += 16;
      i -= 16;
      l3 = l3 << 50L | l3 >>> 14L;
      l3 += l4;
      l1 ^= l3;
      l4 = l4 << 52L | l4 >>> 12L;
      l4 += l1;
      l2 ^= l4;
      l1 = l1 << 30L | l1 >>> 34L;
      l1 += l2;
      l3 ^= l1;
      l2 = l2 << 41L | l2 >>> 23L;
      l2 += l3;
      l4 ^= l2;
      l3 = l3 << 54L | l3 >>> 10L;
      l3 += l4;
      l1 ^= l3;
      l4 = l4 << 48L | l4 >>> 16L;
      l4 += l1;
      l2 ^= l4;
      l1 = l1 << 38L | l1 >>> 26L;
      l1 += l2;
      l3 ^= l1;
      l2 = l2 << 37L | l2 >>> 27L;
      l2 += l3;
      l4 ^= l2;
      l3 = l3 << 62L | l3 >>> 2L;
      l3 += l4;
      l1 ^= l3;
      l4 = l4 << 34L | l4 >>> 30L;
      l4 += l1;
      l2 ^= l4;
      l1 = l1 << 5L | l1 >>> 59L;
      l1 += l2;
      l3 ^= l1;
      l2 = l2 << 36L | l2 >>> 28L;
      l2 += l3;
      l4 ^= l2;
    } 
    l4 += paramInt2 << 56L;
    if (i >= 8) {
      l3 += a(paramArrayOfbyte, j);
      j += 8;
      i -= 8;
      if (i > 0)
        l4 += b(paramArrayOfbyte, j, i); 
    } else if (i > 0) {
      l3 += b(paramArrayOfbyte, j, i);
    } else {
      l3 += -2401053088876216593L;
      l4 += -2401053088876216593L;
    } 
    l4 ^= l3;
    l3 = l3 << 15L | l3 >>> 49L;
    l4 += l3;
    l1 ^= l4;
    l4 = l4 << 52L | l4 >>> 12L;
    l1 += l4;
    l2 ^= l1;
    l1 = l1 << 26L | l1 >>> 38L;
    l2 += l1;
    l3 ^= l2;
    l2 = l2 << 51L | l2 >>> 13L;
    l3 += l2;
    l4 ^= l3;
    l3 = l3 << 28L | l3 >>> 36L;
    l4 += l3;
    l1 ^= l4;
    l4 = l4 << 9L | l4 >>> 55L;
    l1 += l4;
    l2 ^= l1;
    l1 = l1 << 47L | l1 >>> 17L;
    l2 += l1;
    l3 ^= l2;
    l2 = l2 << 54L | l2 >>> 10L;
    l3 += l2;
    l4 ^= l3;
    l3 = l3 << 32L | l3 >>> 32L;
    l4 += l3;
    l1 ^= l4;
    l4 = l4 << 25L | l4 >>> 39L;
    l1 += l4;
    l2 ^= l1;
    l1 = l1 << 63L | l1 >>> 1L;
    l2 += l1;
    paramArrayOflong[0] = l1;
    paramArrayOflong[1] = l2;
  }
  
  public static long b(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, long[] paramArrayOflong) {
    if (paramInt2 < 192) {
      a(paramArrayOfbyte, paramInt1, paramInt2, paramArrayOflong);
      return paramArrayOflong[0];
    } 
    long l10 = paramArrayOflong[0];
    long l7 = l10;
    long l4 = l7;
    long l1 = l4;
    long l11 = paramArrayOflong[1];
    long l8 = l11;
    long l5 = l8;
    long l2 = l5;
    long l12 = -2401053088876216593L;
    long l9 = l12;
    long l6 = l9;
    long l3 = l6;
    int i = paramInt2;
    int j = paramInt1;
  }
  
  private static long a(CharSequence paramCharSequence, int paramInt) {
    return paramCharSequence.charAt(paramInt + 3) << 48L | paramCharSequence.charAt(paramInt + 2) << 32L | paramCharSequence.charAt(paramInt + 1) << 16L | paramCharSequence.charAt(paramInt);
  }
  
  private static long a(CharSequence paramCharSequence, int paramInt1, int paramInt2) {
    long l = 0L;
    switch (paramInt2) {
      case 3:
        l += paramCharSequence.charAt(paramInt1 + 2) << 32L;
      case 2:
        l += paramCharSequence.charAt(paramInt1 + 1) << 16L;
      case 1:
        l += paramCharSequence.charAt(paramInt1);
        break;
    } 
    return l;
  }
  
  private static void a(CharSequence paramCharSequence, int paramInt1, int paramInt2, long[] paramArrayOflong) {
    long l1 = paramArrayOflong[0];
    long l2 = paramArrayOflong[1];
    long l3 = -2401053088876216593L;
    long l4 = -2401053088876216593L;
    int i = paramInt2;
    int j = paramInt1;
    while (i >= 16) {
      l3 += a(paramCharSequence, j);
      l4 += a(paramCharSequence, j + 4);
      l3 = l3 << 50L | l3 >>> 14L;
      l3 += l4;
      l1 ^= l3;
      l4 = l4 << 52L | l4 >>> 12L;
      l4 += l1;
      l2 ^= l4;
      l1 = l1 << 30L | l1 >>> 34L;
      l1 += l2;
      l3 ^= l1;
      l2 = l2 << 41L | l2 >>> 23L;
      l2 += l3;
      l4 ^= l2;
      l3 = l3 << 54L | l3 >>> 10L;
      l3 += l4;
      l1 ^= l3;
      l4 = l4 << 48L | l4 >>> 16L;
      l4 += l1;
      l2 ^= l4;
      l1 = l1 << 38L | l1 >>> 26L;
      l1 += l2;
      l3 ^= l1;
      l2 = l2 << 37L | l2 >>> 27L;
      l2 += l3;
      l4 ^= l2;
      l3 = l3 << 62L | l3 >>> 2L;
      l3 += l4;
      l1 ^= l3;
      l4 = l4 << 34L | l4 >>> 30L;
      l4 += l1;
      l2 ^= l4;
      l1 = l1 << 5L | l1 >>> 59L;
      l1 += l2;
      l3 ^= l1;
      l2 = l2 << 36L | l2 >>> 28L;
      l2 += l3;
      l4 ^= l2;
      l1 += a(paramCharSequence, j + 8);
      l2 += a(paramCharSequence, j + 12);
      j += 16;
      i -= 16;
    } 
    if (i >= 8) {
      l3 += a(paramCharSequence, j);
      l4 += a(paramCharSequence, j + 4);
      j += 8;
      i -= 8;
      l3 = l3 << 50L | l3 >>> 14L;
      l3 += l4;
      l1 ^= l3;
      l4 = l4 << 52L | l4 >>> 12L;
      l4 += l1;
      l2 ^= l4;
      l1 = l1 << 30L | l1 >>> 34L;
      l1 += l2;
      l3 ^= l1;
      l2 = l2 << 41L | l2 >>> 23L;
      l2 += l3;
      l4 ^= l2;
      l3 = l3 << 54L | l3 >>> 10L;
      l3 += l4;
      l1 ^= l3;
      l4 = l4 << 48L | l4 >>> 16L;
      l4 += l1;
      l2 ^= l4;
      l1 = l1 << 38L | l1 >>> 26L;
      l1 += l2;
      l3 ^= l1;
      l2 = l2 << 37L | l2 >>> 27L;
      l2 += l3;
      l4 ^= l2;
      l3 = l3 << 62L | l3 >>> 2L;
      l3 += l4;
      l1 ^= l3;
      l4 = l4 << 34L | l4 >>> 30L;
      l4 += l1;
      l2 ^= l4;
      l1 = l1 << 5L | l1 >>> 59L;
      l1 += l2;
      l3 ^= l1;
      l2 = l2 << 36L | l2 >>> 28L;
      l2 += l3;
      l4 ^= l2;
    } 
    l4 += (paramInt2 << 1) << 56L;
    if (i >= 4) {
      l3 += a(paramCharSequence, j);
      j += 4;
      i -= 4;
      if (i > 0)
        l4 += a(paramCharSequence, j, i); 
    } else if (i > 0) {
      l3 += a(paramCharSequence, j, i);
    } else {
      l3 += -2401053088876216593L;
      l4 += -2401053088876216593L;
    } 
    l4 ^= l3;
    l3 = l3 << 15L | l3 >>> 49L;
    l4 += l3;
    l1 ^= l4;
    l4 = l4 << 52L | l4 >>> 12L;
    l1 += l4;
    l2 ^= l1;
    l1 = l1 << 26L | l1 >>> 38L;
    l2 += l1;
    l3 ^= l2;
    l2 = l2 << 51L | l2 >>> 13L;
    l3 += l2;
    l4 ^= l3;
    l3 = l3 << 28L | l3 >>> 36L;
    l4 += l3;
    l1 ^= l4;
    l4 = l4 << 9L | l4 >>> 55L;
    l1 += l4;
    l2 ^= l1;
    l1 = l1 << 47L | l1 >>> 17L;
    l2 += l1;
    l3 ^= l2;
    l2 = l2 << 54L | l2 >>> 10L;
    l3 += l2;
    l4 ^= l3;
    l3 = l3 << 32L | l3 >>> 32L;
    l4 += l3;
    l1 ^= l4;
    l4 = l4 << 25L | l4 >>> 39L;
    l1 += l4;
    l2 ^= l1;
    l1 = l1 << 63L | l1 >>> 1L;
    l2 += l1;
    paramArrayOflong[0] = l1;
    paramArrayOflong[1] = l2;
  }
  
  public static long b(CharSequence paramCharSequence, int paramInt1, int paramInt2, long[] paramArrayOflong) {
    if (paramInt2 < 96) {
      a(paramCharSequence, paramInt1, paramInt2, paramArrayOflong);
      return paramArrayOflong[0];
    } 
    long l10 = paramArrayOflong[0];
    long l7 = l10;
    long l4 = l7;
    long l1 = l4;
    long l11 = paramArrayOflong[1];
    long l8 = l11;
    long l5 = l8;
    long l2 = l5;
    long l12 = -2401053088876216593L;
    long l9 = l12;
    long l6 = l9;
    long l3 = l6;
    int i = paramInt2;
    int j = paramInt1;
  }
  
  private static void a(long[] paramArrayOflong1, int paramInt1, int paramInt2, long[] paramArrayOflong2) {
    long l1 = paramArrayOflong2[0];
    long l2 = paramArrayOflong2[1];
    long l3 = -2401053088876216593L;
    long l4 = -2401053088876216593L;
    int i = paramInt2;
    int j = paramInt1;
    while (i >= 4) {
      l3 += paramArrayOflong1[j];
      l4 += paramArrayOflong1[j + 1];
      l3 = l3 << 50L | l3 >>> 14L;
      l3 += l4;
      l1 ^= l3;
      l4 = l4 << 52L | l4 >>> 12L;
      l4 += l1;
      l2 ^= l4;
      l1 = l1 << 30L | l1 >>> 34L;
      l1 += l2;
      l3 ^= l1;
      l2 = l2 << 41L | l2 >>> 23L;
      l2 += l3;
      l4 ^= l2;
      l3 = l3 << 54L | l3 >>> 10L;
      l3 += l4;
      l1 ^= l3;
      l4 = l4 << 48L | l4 >>> 16L;
      l4 += l1;
      l2 ^= l4;
      l1 = l1 << 38L | l1 >>> 26L;
      l1 += l2;
      l3 ^= l1;
      l2 = l2 << 37L | l2 >>> 27L;
      l2 += l3;
      l4 ^= l2;
      l3 = l3 << 62L | l3 >>> 2L;
      l3 += l4;
      l1 ^= l3;
      l4 = l4 << 34L | l4 >>> 30L;
      l4 += l1;
      l2 ^= l4;
      l1 = l1 << 5L | l1 >>> 59L;
      l1 += l2;
      l3 ^= l1;
      l2 = l2 << 36L | l2 >>> 28L;
      l2 += l3;
      l4 ^= l2;
      l1 += paramArrayOflong1[j + 2];
      l2 += paramArrayOflong1[j + 3];
      j += 4;
      i -= 4;
    } 
    if (i >= 2) {
      l3 += paramArrayOflong1[j];
      l4 += paramArrayOflong1[j + 1];
      j += 2;
      i -= 2;
      l3 = l3 << 50L | l3 >>> 14L;
      l3 += l4;
      l1 ^= l3;
      l4 = l4 << 52L | l4 >>> 12L;
      l4 += l1;
      l2 ^= l4;
      l1 = l1 << 30L | l1 >>> 34L;
      l1 += l2;
      l3 ^= l1;
      l2 = l2 << 41L | l2 >>> 23L;
      l2 += l3;
      l4 ^= l2;
      l3 = l3 << 54L | l3 >>> 10L;
      l3 += l4;
      l1 ^= l3;
      l4 = l4 << 48L | l4 >>> 16L;
      l4 += l1;
      l2 ^= l4;
      l1 = l1 << 38L | l1 >>> 26L;
      l1 += l2;
      l3 ^= l1;
      l2 = l2 << 37L | l2 >>> 27L;
      l2 += l3;
      l4 ^= l2;
      l3 = l3 << 62L | l3 >>> 2L;
      l3 += l4;
      l1 ^= l3;
      l4 = l4 << 34L | l4 >>> 30L;
      l4 += l1;
      l2 ^= l4;
      l1 = l1 << 5L | l1 >>> 59L;
      l1 += l2;
      l3 ^= l1;
      l2 = l2 << 36L | l2 >>> 28L;
      l2 += l3;
      l4 ^= l2;
    } 
    l4 += (paramInt2 << 3) << 56L;
    if (i > 0) {
      l3 += paramArrayOflong1[j];
    } else {
      l3 += -2401053088876216593L;
      l4 += -2401053088876216593L;
    } 
    l4 ^= l3;
    l3 = l3 << 15L | l3 >>> 49L;
    l4 += l3;
    l1 ^= l4;
    l4 = l4 << 52L | l4 >>> 12L;
    l1 += l4;
    l2 ^= l1;
    l1 = l1 << 26L | l1 >>> 38L;
    l2 += l1;
    l3 ^= l2;
    l2 = l2 << 51L | l2 >>> 13L;
    l3 += l2;
    l4 ^= l3;
    l3 = l3 << 28L | l3 >>> 36L;
    l4 += l3;
    l1 ^= l4;
    l4 = l4 << 9L | l4 >>> 55L;
    l1 += l4;
    l2 ^= l1;
    l1 = l1 << 47L | l1 >>> 17L;
    l2 += l1;
    l3 ^= l2;
    l2 = l2 << 54L | l2 >>> 10L;
    l3 += l2;
    l4 ^= l3;
    l3 = l3 << 32L | l3 >>> 32L;
    l4 += l3;
    l1 ^= l4;
    l4 = l4 << 25L | l4 >>> 39L;
    l1 += l4;
    l2 ^= l1;
    l1 = l1 << 63L | l1 >>> 1L;
    l2 += l1;
    paramArrayOflong2[0] = l1;
    paramArrayOflong2[1] = l2;
  }
  
  public static long b(long[] paramArrayOflong1, int paramInt1, int paramInt2, long[] paramArrayOflong2) {
    if (paramInt2 < 24) {
      a(paramArrayOflong1, paramInt1, paramInt2, paramArrayOflong2);
      return paramArrayOflong2[0];
    } 
    long l10 = paramArrayOflong2[0];
    long l7 = l10;
    long l4 = l7;
    long l1 = l4;
    long l11 = paramArrayOflong2[1];
    long l8 = l11;
    long l5 = l8;
    long l2 = l5;
    long l12 = -2401053088876216593L;
    long l9 = l12;
    long l6 = l9;
    long l3 = l6;
    int i = paramInt1;
    int j = paramInt2;
  }
  
  public hh() {
    this(0L, 0L);
  }
  
  public hh(long paramLong1, long paramLong2) {
    this.sD = paramLong1;
    this.sE = paramLong2;
  }
  
  public static long a(byte[] paramArrayOfbyte, long[] paramArrayOflong) {
    return b(paramArrayOfbyte, 0, paramArrayOfbyte.length, paramArrayOflong);
  }
  
  public long[] c(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    long[] arrayOfLong = { this.sD, this.sE };
    b(paramArrayOfbyte, paramInt1, paramInt2, arrayOfLong);
    return arrayOfLong;
  }
  
  public long[] j(byte[] paramArrayOfbyte) {
    return c(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public static long a(CharSequence paramCharSequence, long[] paramArrayOflong) {
    return b(paramCharSequence, 0, paramCharSequence.length(), paramArrayOflong);
  }
  
  public long[] b(CharSequence paramCharSequence, int paramInt1, int paramInt2) {
    long[] arrayOfLong = { this.sD, this.sE };
    b(paramCharSequence, paramInt1, paramInt2, arrayOfLong);
    return arrayOfLong;
  }
  
  public long[] a(CharSequence paramCharSequence) {
    return b(paramCharSequence, 0, paramCharSequence.length());
  }
  
  public static long a(long[] paramArrayOflong1, long[] paramArrayOflong2) {
    return b(paramArrayOflong1, 0, paramArrayOflong1.length, paramArrayOflong2);
  }
  
  public long[] b(long[] paramArrayOflong, int paramInt1, int paramInt2) {
    long[] arrayOfLong = { this.sD, this.sE };
    b(paramArrayOflong, paramInt1, paramInt2, arrayOfLong);
    return arrayOfLong;
  }
  
  public long[] b(long[] paramArrayOflong) {
    return b(paramArrayOflong, 0, paramArrayOflong.length);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\hh.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */