package nomanssave;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class hk {
  private static final String sJ = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
  
  public static String k(byte[] paramArrayOfbyte) {
    StringBuilder stringBuilder = new StringBuilder();
    byte b;
    for (b = 0; b + 3 <= paramArrayOfbyte.length; b += 3) {
      int i = (0xFF & paramArrayOfbyte[b]) << 16 | (0xFF & paramArrayOfbyte[b + 1]) << 8 | 0xFF & paramArrayOfbyte[b + 2];
      stringBuilder.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((0xFC0000 & i) >> 18));
      stringBuilder.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((0x3F000 & i) >> 12));
      stringBuilder.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((0xFC0 & i) >> 6));
      stringBuilder.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(0x3F & i));
    } 
    if (b + 2 == paramArrayOfbyte.length) {
      int i = (0xFF & paramArrayOfbyte[b]) << 16 | (0xFF & paramArrayOfbyte[b + 1]) << 8;
      stringBuilder.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((0xFC0000 & i) >> 18));
      stringBuilder.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((0x3F000 & i) >> 12));
      stringBuilder.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((0xFC0 & i) >> 6));
    } 
    if (b + 1 == paramArrayOfbyte.length) {
      int i = (0xFF & paramArrayOfbyte[b]) << 16;
      stringBuilder.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((0xFC0000 & i) >> 18));
      stringBuilder.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((0x3F000 & i) >> 12));
    } 
    return stringBuilder.toString();
  }
  
  public static byte[] aD(String paramString) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    byte b;
    for (b = 0; b + 4 <= paramString.length(); b += 4) {
      int i = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(paramString.charAt(b));
      int j = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(paramString.charAt(b + 1));
      int k = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(paramString.charAt(b + 2));
      int m = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(paramString.charAt(b + 3));
      if (i < 0 || j < 0 || k < 0 || m < 0)
        throw new RuntimeException("Invalid base64 character"); 
      byteArrayOutputStream.write(i << 2 | j >> 4);
      byteArrayOutputStream.write((0xF & j) << 4 | k >> 2);
      byteArrayOutputStream.write((0x3 & k) << 6 | m);
    } 
    if (b + 3 == paramString.length()) {
      int i = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(paramString.charAt(b));
      int j = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(paramString.charAt(b + 1));
      int k = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(paramString.charAt(b + 2));
      if (i < 0 || j < 0 || k < 0)
        throw new RuntimeException("Invalid base64 character"); 
      byteArrayOutputStream.write(i << 2 | j >> 4);
      byteArrayOutputStream.write((0xF & j) << 4 | k >> 2);
    } 
    if (b + 2 == paramString.length()) {
      int i = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(paramString.charAt(b));
      int j = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(paramString.charAt(b + 1));
      if (i < 0 || j < 0)
        throw new RuntimeException("Invalid base64 character"); 
      byteArrayOutputStream.write(i << 2 | j >> 4);
    } 
    if (b + 1 == paramString.length())
      throw new RuntimeException("Unfinished base64 data"); 
    return byteArrayOutputStream.toByteArray();
  }
  
  public static int readInt(InputStream paramInputStream) {
    byte[] arrayOfByte = new byte[4];
    readFully(paramInputStream, arrayOfByte);
    return (0xFF & arrayOfByte[3]) << 24 | (0xFF & arrayOfByte[2]) << 16 | (0xFF & arrayOfByte[1]) << 8 | 0xFF & arrayOfByte[0];
  }
  
  public static void a(OutputStream paramOutputStream, int paramInt) {
    paramOutputStream.write(0xFF & paramInt);
    paramOutputStream.write(0xFF & paramInt >> 8);
    paramOutputStream.write(0xFF & paramInt >> 16);
    paramOutputStream.write(0xFF & paramInt >> 24);
  }
  
  public static long f(InputStream paramInputStream) {
    byte[] arrayOfByte = new byte[8];
    readFully(paramInputStream, arrayOfByte);
    return (0xFFL & arrayOfByte[7]) << 56L | (0xFFL & arrayOfByte[6]) << 48L | (0xFFL & arrayOfByte[5]) << 40L | (0xFFL & arrayOfByte[4]) << 32L | (0xFFL & arrayOfByte[3]) << 24L | (0xFFL & arrayOfByte[2]) << 16L | (0xFFL & arrayOfByte[1]) << 8L | 0xFFL & arrayOfByte[0];
  }
  
  public static void b(OutputStream paramOutputStream, long paramLong) {
    paramOutputStream.write((int)(0xFFL & paramLong));
    paramOutputStream.write((int)(0xFFL & paramLong >> 8L));
    paramOutputStream.write((int)(0xFFL & paramLong >> 16L));
    paramOutputStream.write((int)(0xFFL & paramLong >> 24L));
    paramOutputStream.write((int)(0xFFL & paramLong >> 32L));
    paramOutputStream.write((int)(0xFFL & paramLong >> 40L));
    paramOutputStream.write((int)(0xFFL & paramLong >> 48L));
    paramOutputStream.write((int)(0xFFL & paramLong >> 56L));
  }
  
  public static byte[] l(File paramFile) {
    FileInputStream fileInputStream = new FileInputStream(paramFile);
    try {
      return g(fileInputStream);
    } finally {
      fileInputStream.close();
    } 
  }
  
  public static byte[] g(InputStream paramInputStream) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte[4096];
    int i;
    while ((i = paramInputStream.read(arrayOfByte)) >= 0)
      byteArrayOutputStream.write(arrayOfByte, 0, i); 
    return byteArrayOutputStream.toByteArray();
  }
  
  public static void readFully(InputStream paramInputStream, byte[] paramArrayOfbyte) {
    readFully(paramInputStream, paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public static void readFully(InputStream paramInputStream, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    int i;
    while (paramInt2 > 0 && (i = paramInputStream.read(paramArrayOfbyte, paramInt1, paramInt2)) > 0) {
      paramInt1 += i;
      paramInt2 -= i;
    } 
    if (paramInt2 != 0)
      throw new IOException("short read"); 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\hk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */