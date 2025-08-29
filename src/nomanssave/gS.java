package nomanssave;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class gS {
  private static byte[] ly = new byte[] { 78, 77, 83, 66 };
  
  private static byte[] rP = new byte[] { 
      50, -99, -78, -55, 92, 88, -34, 74, -57, 17, 
      57, -108, -94, Byte.MAX_VALUE, 97, -79 };
  
  private static double[] a(eY parameY, String paramString) {
    eV eV = parameY.d(paramString);
    if (eV.size() != 3)
      throw new RuntimeException("Invalid " + paramString + " coordinates"); 
    return new double[] { eV.aa(0), eV.aa(1), eV.aa(2) };
  }
  
  private static void a(eY parameY, String paramString, double[] paramArrayOfdouble) {
    parameY.b(paramString, new eV(new Object[] { new Double(Double.isNaN(paramArrayOfdouble[0]) ? 0.0D : paramArrayOfdouble[0]), new Double(Double.isNaN(paramArrayOfdouble[1]) ? 0.0D : paramArrayOfdouble[1]), new Double(Double.isNaN(paramArrayOfdouble[2]) ? 0.0D : paramArrayOfdouble[2]) }));
  }
  
  public static void d(eY parameY, File paramFile) {
    a(parameY, Collections.emptyMap(), paramFile);
  }
  
  public static void a(eY parameY, Map paramMap, File paramFile) {
    CipherOutputStream cipherOutputStream;
    int i = parameY.J("BaseVersion");
    eV eV = parameY.d("Objects").bA();
    if (i < 3) {
      double[] arrayOfDouble1 = a(parameY, "Position");
      double[] arrayOfDouble2 = a(parameY, "Forward");
      gT gT = new gT(arrayOfDouble1, arrayOfDouble2);
      for (byte b = 0; b < eV.size(); b++) {
        eY eY1 = eV.V(b);
        double[] arrayOfDouble3 = a(eY1, "Position");
        double[] arrayOfDouble4 = a(eY1, "Up");
        double[] arrayOfDouble5 = a(eY1, "At");
        a(eY1, "Position", gT.d(arrayOfDouble3));
        a(eY1, "Up", gT.d(arrayOfDouble4));
        a(eY1, "At", gT.d(arrayOfDouble5));
      } 
    } 
    int j = parameY.J("UserData");
    SecretKeySpec secretKeySpec = new SecretKeySpec(rP, "AES");
    byte[] arrayOfByte = new byte[16];
    (new SecureRandom()).nextBytes(arrayOfByte);
    IvParameterSpec ivParameterSpec = new IvParameterSpec(arrayOfByte);
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    cipher.init(1, secretKeySpec, ivParameterSpec);
    FileOutputStream fileOutputStream = new FileOutputStream(paramFile);
    try {
      fileOutputStream.write(ly);
      fileOutputStream.write(new byte[] { 0, 5 });
      fileOutputStream.write(arrayOfByte);
      cipherOutputStream = new CipherOutputStream(fileOutputStream, cipher);
      cipherOutputStream.write(new byte[] { 84, 82, 85, 69 });
      hk.a(cipherOutputStream, j);
      byte[] arrayOfByte1 = fj.b(eV);
      hk.a(cipherOutputStream, arrayOfByte1.length);
      cipherOutputStream.write(arrayOfByte1);
      for (Map.Entry entry : paramMap.entrySet()) {
        arrayOfByte1 = ((String)entry.getKey()).getBytes();
        if (arrayOfByte1.length > 255)
          continue; 
        cipherOutputStream.write(arrayOfByte1.length);
        cipherOutputStream.write(arrayOfByte1);
        arrayOfByte1 = fj.j(entry.getValue());
        hk.a(cipherOutputStream, arrayOfByte1.length);
        cipherOutputStream.write(arrayOfByte1);
      } 
      cipherOutputStream.flush();
    } finally {
      cipherOutputStream.close();
    } 
  }
  
  public static void e(eY parameY, File paramFile) {
    b(parameY, Collections.emptyMap(), paramFile);
  }
  
  public static void b(eY parameY, Map<String, Object> paramMap, File paramFile) {
    int i;
    CipherInputStream cipherInputStream;
    eV eV;
    FileInputStream fileInputStream = new FileInputStream(paramFile);
    try {
      byte[] arrayOfByte1 = new byte[8];
      if (fileInputStream.read(arrayOfByte1) != 8)
        throw new IOException("short read"); 
      if (arrayOfByte1[0] != ly[0] || arrayOfByte1[1] != ly[1] || arrayOfByte1[2] != ly[2] || arrayOfByte1[3] != ly[3])
        throw new IOException("invalid base file"); 
      i = (arrayOfByte1[4] & 0xFF) << 8 | arrayOfByte1[5] & 0xFF;
      switch (i) {
        case 2:
          throw new IOException("unsupported base file");
        case 3:
        case 4:
        case 5:
          break;
        default:
          throw new IOException("invalid base file");
      } 
      byte[] arrayOfByte2 = new byte[16];
      if (fileInputStream.read(arrayOfByte2) != 16)
        throw new IOException("short read"); 
      SecretKeySpec secretKeySpec = new SecretKeySpec(rP, "AES");
      IvParameterSpec ivParameterSpec = new IvParameterSpec(arrayOfByte2);
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      cipher.init(2, secretKeySpec, ivParameterSpec);
      cipherInputStream = new CipherInputStream(fileInputStream, cipher);
      if (cipherInputStream.read(arrayOfByte1, 0, 4) != 4)
        throw new IOException("short read"); 
      if (arrayOfByte1[0] != 84 || arrayOfByte1[1] != 82 || arrayOfByte1[2] != 85 || arrayOfByte1[3] != 69)
        throw new IOException("invalid base file"); 
      if (i < 5) {
        int k;
        if ((k = cipherInputStream.read()) < 0)
          throw new IOException("short read"); 
        int m;
        if ((m = cipherInputStream.read()) < 0)
          throw new IOException("short read"); 
        int n;
        if ((n = cipherInputStream.read()) < 0)
          throw new IOException("short read"); 
        int i1;
        if ((i1 = cipherInputStream.read()) < 0)
          throw new IOException("short read"); 
        parameY.b("UserData", Integer.valueOf(k << 24 | m << 16 | n << 8 | i1));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] arrayOfByte = new byte[8096];
        int i2;
        while ((i2 = cipherInputStream.read(arrayOfByte)) >= 0)
          byteArrayOutputStream.write(arrayOfByte, 0, i2); 
        eV = ff.c(byteArrayOutputStream.toByteArray());
      } else {
        parameY.b("UserData", Integer.valueOf(hk.readInt(cipherInputStream)));
        int k = hk.readInt(cipherInputStream);
        byte[] arrayOfByte = new byte[k];
        hk.readFully(cipherInputStream, arrayOfByte);
        eV = ff.c(arrayOfByte);
        while ((k = cipherInputStream.read()) >= 0) {
          arrayOfByte = new byte[k];
          hk.readFully(cipherInputStream, arrayOfByte);
          String str = new String(arrayOfByte);
          k = hk.readInt(cipherInputStream);
          arrayOfByte = new byte[k];
          hk.readFully(cipherInputStream, arrayOfByte);
          Object object = ff.a(arrayOfByte);
          paramMap.put(str, object);
        } 
      } 
    } finally {
      cipherInputStream.close();
    } 
    long l = parameY.K("LastUpdateTimestamp");
    int j;
    for (j = 0; j < eV.size(); j++) {
      eY eY1 = eV.V(j);
      eY1.put("Timestamp", new Long(l));
    } 
    if (i == 3) {
      for (j = 0; j < eV.size(); j++) {
        eY eY1 = eV.V(j);
        double[] arrayOfDouble1 = a(eY1, "Position");
        arrayOfDouble1[0] = arrayOfDouble1[0] + 3.0D;
        arrayOfDouble1[2] = arrayOfDouble1[2] + 3.0D;
        a(arrayOfDouble1);
        a(eY1, "Position", arrayOfDouble1);
        double[] arrayOfDouble2 = a(eY1, "Up");
        a(arrayOfDouble2);
        a(eY1, "Up", arrayOfDouble2);
        double[] arrayOfDouble3 = a(eY1, "At");
        a(arrayOfDouble3);
        a(eY1, "At", arrayOfDouble3);
      } 
      j = parameY.J("UserData");
      eV.add(0, a("^BASE_FLAG", l, j, new double[] { 0.0D, 0.0D, 0.0D }, new double[] { 0.0D, 1.0D, 0.0D }, new double[] { 0.0D, 0.0D, 1.0D }));
      eV.add(1, a("^MAINROOM", l, j, new double[] { -3.0D, 0.0D, 3.0D }, new double[] { 0.0D, 1.0D, 0.0D }, new double[] { 0.0D, 0.0D, -1.0D }));
      eV.add(2, a("^TELEPORTER", l, j, new double[] { 0.0D, 0.0D, 6.0D }, new double[] { 0.0D, 1.0D, 0.0D }, new double[] { -0.7071069478988647D, 0.0D, -0.7071067094802856D }));
      eV.add(3, a("^BUILDDOOR", l, j, new double[] { -9.005859375D, 0.2421875D, 2.98828125D }, new double[] { 0.0D, 1.0D, 0.0D }, new double[] { -1.0D, 0.0D, 0.0D }));
      eV.add(4, a("^BUILDRAMP", l, j, new double[] { -10.724609375D, 0.296875D, 2.98828125D }, new double[] { -0.2588191032409668D, 0.9659259915351868D, 2.9802322387695312E-8D }, new double[] { -0.9659258127212524D, -0.2588191628456116D, -3.2782554626464844E-7D }));
      eV.add(5, a("^BUILDWINDOW", l, j, new double[] { -7.248046875D, 0.5D, -1.25D }, new double[] { 0.0D, 1.0D, 0.0D }, new double[] { -0.7071069478988647D, 0.0D, -0.7071067094802856D }));
      eV.add(6, a("^BUILDWINDOW", l, j, new double[] { -7.248046875D, 0.5D, 7.25D }, new double[] { 0.0D, 1.0D, 0.0D }, new double[] { -0.7071069478988647D, 0.0D, 0.7071067094802856D }));
      eV.add(7, a("^BUILDWINDOW", l, j, new double[] { 1.248046875D, 0.5D, -1.25D }, new double[] { 0.0D, 1.0D, 0.0D }, new double[] { 0.7071069478988647D, 0.0D, -0.7071067094802856D }));
    } 
    if (i < 5)
      parameY.b("BaseVersion", Integer.valueOf(3)); 
    parameY.b("Objects", eV);
  }
  
  private static void a(double[] paramArrayOfdouble) {
    double d = paramArrayOfdouble[0];
    paramArrayOfdouble[0] = -paramArrayOfdouble[2];
    paramArrayOfdouble[2] = d;
  }
  
  private static eY a(String paramString, long paramLong, int paramInt, double[] paramArrayOfdouble1, double[] paramArrayOfdouble2, double[] paramArrayOfdouble3) {
    eY eY = new eY();
    eY.put("Timestamp", new Long(paramLong));
    eY.put("ObjectID", paramString);
    eY.put("UserData", Integer.valueOf(paramInt));
    eY.put("Position", new eV(new Object[] { new Double(paramArrayOfdouble1[0]), new Double(paramArrayOfdouble1[1]), new Double(paramArrayOfdouble1[2]) }));
    eY.put("Up", new eV(new Object[] { new Double(paramArrayOfdouble2[0]), new Double(paramArrayOfdouble2[1]), new Double(paramArrayOfdouble2[2]) }));
    eY.put("At", new eV(new Object[] { new Double(paramArrayOfdouble3[0]), new Double(paramArrayOfdouble3[1]), new Double(paramArrayOfdouble3[2]) }));
    eY.put("Message", "");
    return eY;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\gS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */