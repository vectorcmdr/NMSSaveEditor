package nomanssave;

public class gW {
  public static void i(byte[] paramArrayOfbyte) {
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("Data: " + paramArrayOfbyte.length);
    stringBuilder1.append(System.lineSeparator());
    stringBuilder1.append("  ");
    StringBuilder stringBuilder2 = new StringBuilder();
    for (byte b = 0; b < paramArrayOfbyte.length; b++) {
      stringBuilder1.append(Integer.toString((paramArrayOfbyte[b] & 0xF0) >> 4, 16));
      stringBuilder1.append(Integer.toString(paramArrayOfbyte[b] & 0xF, 16));
      if (paramArrayOfbyte[b] >= 32 && paramArrayOfbyte[b] < Byte.MAX_VALUE) {
        stringBuilder2.append((char)(paramArrayOfbyte[b] & 0xFF));
      } else {
        stringBuilder2.append('?');
      } 
      if (b % 16 == 15) {
        stringBuilder1.append("  ");
        stringBuilder1.append(stringBuilder2);
        stringBuilder1.append(System.lineSeparator());
        stringBuilder1.append("  ");
        stringBuilder2 = new StringBuilder();
      } 
    } 
    if (stringBuilder2.length() > 0) {
      while (stringBuilder2.length() < 16) {
        stringBuilder1.append("  ");
        stringBuilder2.append(" ");
      } 
      stringBuilder1.append("  ");
      stringBuilder1.append(stringBuilder2);
    } 
    System.out.println(stringBuilder1.toString());
  }
  
  public static void a(long[] paramArrayOflong) {
    byte[] arrayOfByte = new byte[paramArrayOflong.length * 4];
    for (byte b = 0; b < paramArrayOflong.length; b++) {
      arrayOfByte[b * 4 + 3] = (byte)(int)(paramArrayOflong[b] >> 24L & 0xFFL);
      arrayOfByte[b * 4 + 2] = (byte)(int)(paramArrayOflong[b] >> 16L & 0xFFL);
      arrayOfByte[b * 4 + 1] = (byte)(int)(paramArrayOflong[b] >> 8L & 0xFFL);
      arrayOfByte[b * 4] = (byte)(int)(paramArrayOflong[b] & 0xFFL);
    } 
    i(arrayOfByte);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\gW.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */