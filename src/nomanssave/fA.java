package nomanssave;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class fA implements fq {
  private static final byte[] ly = "NOMANSKY".getBytes();
  
  private static final Pattern lT = Pattern.compile("savedata(\\d{2})\\.hg", 2);
  
  private static final Pattern lU = Pattern.compile("ps4_backup(\\d*)\\.\\d*\\.zip", 2);
  
  private final File lV;
  
  private final fR lC;
  
  private fB lW;
  
  private fD[] lX;
  
  fA(File paramFile, fR paramfR) {
    this.lV = paramFile;
    this.lC = paramfR;
    try {
      this.lW = new fB(this);
    } catch (FileNotFoundException fileNotFoundException) {
    
    } catch (IOException iOException) {
      hc.a("cannot read file metadata: savedata00.hg", iOException);
    } 
    this.lX = new fD[30];
    for (byte b = 0; b < this.lX.length; b++) {
      try {
        this.lX[b] = new fD(this, b);
      } catch (FileNotFoundException fileNotFoundException) {
      
      } catch (IOException iOException) {
        int i = b + 2;
        String str = "savedata" + ((i < 10) ? "0" : "") + Integer.toString(i) + ".hg";
        hc.a("cannot read file metadata: " + str, iOException);
      } 
    } 
    fl.a(this, paramFile);
  }
  
  private static eY a(byte[] paramArrayOfbyte, eG parameG) {
    Exception exception1 = null;
    Exception exception2 = null;
    try {
    
    } finally {
      exception2 = null;
      if (exception1 == null) {
        exception1 = exception2;
      } else if (exception1 != exception2) {
        exception1.addSuppressed(exception2);
      } 
    } 
  }
  
  private static byte[] g(eY parameY) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    Exception exception1 = null;
    Exception exception2 = null;
  }
  
  public File bS() {
    return this.lV;
  }
  
  public fr bT() {
    return this.lW;
  }
  
  public ft[] bU() {
    ft[] arrayOfFt = new ft[15];
    for (byte b = 0; b < 15; b++)
      arrayOfFt[b] = new fE(this, b); 
    return arrayOfFt;
  }
  
  public int W(String paramString) {
    Matcher matcher = lT.matcher(paramString);
    if (!matcher.matches())
      return -1; 
    int i = Integer.parseInt(matcher.group(1)) - 2;
    return (i >= 0) ? (i / 2) : -1;
  }
  
  public void X(String paramString) {
    Matcher matcher = lT.matcher(paramString);
    if (matcher.matches()) {
      int i = Integer.parseInt(matcher.group(1)) - 2;
      if (i == -2) {
        try {
          this.lW = new fB(this);
          hc.info("Account data reloaded from storage.");
        } catch (FileNotFoundException fileNotFoundException) {
          this.lW = null;
          hc.info("Account data deleted from storage.");
        } catch (IOException iOException) {
          this.lW = null;
          hc.a("cannot read file metadata: " + paramString, iOException);
        } 
        this.lC.a(this);
      } else if (i >= 0) {
        try {
          this.lX[i] = new fD(this, i);
          hc.info("Save file reloaded from storage: " + paramString);
        } catch (FileNotFoundException fileNotFoundException) {
          this.lX[i] = null;
          hc.info("Save file deleted from storage: " + paramString);
        } catch (IOException iOException) {
          this.lX[i] = null;
          hc.a("cannot read file metadata: " + paramString, iOException);
        } 
        this.lC.a(this, i / 2, paramString);
      } 
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\fA.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */