package nomanssave;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class fJ implements fq {
  private static final Pattern lT = Pattern.compile("save(\\d*)\\.hg");
  
  private static final Pattern lU = Pattern.compile("backup(\\d*)\\.\\d*\\.zip");
  
  private final File lV;
  
  private final fR lC;
  
  private fK mp;
  
  private fM[] mq;
  
  fJ(File paramFile, fR paramfR) {
    this.lV = paramFile;
    this.lC = paramfR;
    try {
      this.mp = new fK(this);
    } catch (FileNotFoundException fileNotFoundException) {
    
    } catch (IOException iOException) {
      hc.a("cannot read file metadata: mf_accountdata.hg", iOException);
    } 
    this.mq = new fM[30];
    for (byte b = 0; b < this.mq.length; b++) {
      try {
        this.mq[b] = new fM(this, b);
      } catch (FileNotFoundException fileNotFoundException) {
      
      } catch (IOException iOException) {
        hc.a("cannot read file metadata: mf_save" + ((b == 0) ? "" : Integer.toString(b + 1)) + ".hg", iOException);
      } 
    } 
    fl.a(this, paramFile);
  }
  
  protected void finalize() {
    fl.b(this);
  }
  
  public void X(String paramString) {
    if (paramString.equals("accountdata.hg")) {
      try {
        this.mp = new fK(this);
        hc.info("Account data reloaded from storage.");
      } catch (FileNotFoundException fileNotFoundException) {
        this.mp = null;
        hc.info("Account data deleted from storage.");
      } catch (IOException iOException) {
        this.mp = null;
        hc.a("cannot read file metadata: mf_accountdata.hg", iOException);
      } 
      this.lC.a(this);
    } 
    Matcher matcher = lT.matcher(paramString);
    if (matcher.matches()) {
      byte b = (matcher.group(1).length() == 0) ? 0 : (Integer.parseInt(matcher.group(1)) - 1);
      try {
        this.mq[b] = new fM(this, b);
        hc.info("Save file reloaded from storage: " + paramString);
      } catch (FileNotFoundException fileNotFoundException) {
        this.mq[b] = null;
        hc.info("Save file deleted from storage: " + paramString);
      } catch (IOException iOException) {
        this.mq[b] = null;
        hc.a("cannot read file metadata: mf_save" + (!b ? "" : Integer.toString(b + 1)) + ".hg", iOException);
      } 
      this.lC.a(this, b / 2, paramString);
    } 
  }
  
  public File bS() {
    return this.lV;
  }
  
  public fr bT() {
    return this.mp;
  }
  
  public ft[] bU() {
    ft[] arrayOfFt = new ft[15];
    for (byte b = 0; b < 15; b++)
      arrayOfFt[b] = new fN(this, b); 
    return arrayOfFt;
  }
  
  public int W(String paramString) {
    Matcher matcher = lT.matcher(paramString);
    if (!matcher.matches())
      return -1; 
    byte b = (matcher.group(1).length() == 0) ? 0 : (Integer.parseInt(matcher.group(1)) - 1);
    return b / 2;
  }
  
  public boolean bW() {
    return true;
  }
  
  public String a(int paramInt, eY parameY) {
    if (this.mq[paramInt * 2] != null) {
      this.mq[paramInt * 2].cm();
      this.mq[paramInt * 2] = null;
    } 
    if (this.mq[paramInt * 2 + 1] != null) {
      this.mq[paramInt * 2 + 1].cm();
      this.mq[paramInt * 2 + 1] = null;
    } 
    this.mq[paramInt * 2] = new fM(this, paramInt * 2, parameY);
    return (this.mq[paramInt * 2]).filename;
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
  
  private static byte[] c(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    byteArrayOutputStream.write(paramArrayOfbyte1, 0, paramArrayOfbyte1.length);
    byteArrayOutputStream.write(paramArrayOfbyte2, 0, paramArrayOfbyte2.length);
    long[] arrayOfLong1 = { 96176015842230784L, -8446744073709551617L };
    hh.a(byteArrayOutputStream.toByteArray(), arrayOfLong1);
    long[] arrayOfLong2 = new long[4];
    arrayOfLong2[0] = arrayOfLong1[0] & 0xFFFFFFFFL;
    arrayOfLong2[1] = arrayOfLong1[0] >>> 32L & 0xFFFFFFFFL;
    arrayOfLong2[2] = arrayOfLong1[1] & 0xFFFFFFFFL;
    arrayOfLong2[3] = arrayOfLong1[1] >>> 32L & 0xFFFFFFFFL;
    return a(arrayOfLong2, 0, 4);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\fJ.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */