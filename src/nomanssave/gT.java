package nomanssave;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class gT {
  static final int rQ = 12;
  
  private static final double rR = 0.1D;
  
  private final double[] rS;
  
  private final double[] rT;
  
  private final double[] rU;
  
  private static double[] b(double[] paramArrayOfdouble) {
    double d = Math.sqrt(paramArrayOfdouble[0] * paramArrayOfdouble[0] + paramArrayOfdouble[1] * paramArrayOfdouble[1] + paramArrayOfdouble[2] * paramArrayOfdouble[2]);
    if (d < 0.1D)
      throw new RuntimeException("vector cannot be normalized"); 
    return new double[] { paramArrayOfdouble[0] / d, paramArrayOfdouble[1] / d, paramArrayOfdouble[2] / d };
  }
  
  public gT() {
    this.rU = new double[] { 0.0D, 0.0D, 1.0D };
    this.rT = new double[] { 0.0D, 1.0D, 0.0D };
    this.rS = new double[] { 1.0D, 0.0D, 0.0D };
  }
  
  public gT(double[] paramArrayOfdouble1, double[] paramArrayOfdouble2) {
    double d = Math.sqrt(paramArrayOfdouble1[0] * paramArrayOfdouble1[0] + paramArrayOfdouble1[1] * paramArrayOfdouble1[1] + paramArrayOfdouble1[2] * paramArrayOfdouble1[2]);
    if (d < 0.1D) {
      if (paramArrayOfdouble2[0] != 0.0D || paramArrayOfdouble2[1] != 0.0D || paramArrayOfdouble2[2] != 1.0D)
        throw new RuntimeException("Unable to calculate base structures"); 
      this.rU = new double[] { 0.0D, 0.0D, 1.0D };
      this.rT = new double[] { 0.0D, 1.0D, 0.0D };
      this.rS = new double[] { 1.0D, 0.0D, 0.0D };
    } else {
      paramArrayOfdouble1 = new double[] { paramArrayOfdouble1[0] / d, paramArrayOfdouble1[1] / d, paramArrayOfdouble1[2] / d };
      paramArrayOfdouble2 = b(paramArrayOfdouble2);
      double d1 = paramArrayOfdouble2[0] * paramArrayOfdouble1[0] + paramArrayOfdouble2[1] * paramArrayOfdouble1[1] + paramArrayOfdouble2[2] * paramArrayOfdouble1[2];
      this.rU = paramArrayOfdouble2;
      this.rT = b(new double[] { paramArrayOfdouble1[0] - d1 * paramArrayOfdouble2[0], paramArrayOfdouble1[1] - d1 * paramArrayOfdouble2[1], paramArrayOfdouble1[2] - d1 * paramArrayOfdouble2[2] });
      this.rS = b(new double[] { this.rT[1] * paramArrayOfdouble2[2] - this.rT[2] * paramArrayOfdouble2[1], this.rT[2] * paramArrayOfdouble2[0] - this.rT[0] * paramArrayOfdouble2[2], this.rT[0] * paramArrayOfdouble2[1] - this.rT[1] * paramArrayOfdouble2[0] });
    } 
  }
  
  private static double[] a(double paramDouble, double[] paramArrayOfdouble1, double[] paramArrayOfdouble2) {
    double d1 = Math.cos(paramDouble);
    double d2 = -Math.sin(paramDouble);
    double d3 = paramArrayOfdouble2[0];
    double d4 = paramArrayOfdouble2[1];
    double d5 = paramArrayOfdouble2[2];
    double[][] arrayOfDouble = new double[3][3];
    arrayOfDouble[0][0] = d3 * d3 * (1.0D - d1) + d1;
    arrayOfDouble[0][1] = d3 * d4 * (1.0D - d1) + d5 * d2;
    arrayOfDouble[0][2] = d3 * d5 * (1.0D - d1) - d4 * d2;
    arrayOfDouble[1][0] = d3 * d4 * (1.0D - d1) - d5 * d2;
    arrayOfDouble[1][1] = d4 * d4 * (1.0D - d1) + d1;
    arrayOfDouble[1][2] = d4 * d5 * (1.0D - d1) + d3 * d2;
    arrayOfDouble[2][0] = d3 * d5 * (1.0D - d1) + d4 * d2;
    arrayOfDouble[2][1] = d4 * d5 * (1.0D - d1) - d3 * d2;
    arrayOfDouble[2][2] = d5 * d5 * (1.0D - d1) + d1;
    double d6 = paramArrayOfdouble1[0] * arrayOfDouble[0][0] + paramArrayOfdouble1[1] * arrayOfDouble[1][0] + paramArrayOfdouble1[2] * arrayOfDouble[2][0];
    double d7 = paramArrayOfdouble1[0] * arrayOfDouble[0][1] + paramArrayOfdouble1[1] * arrayOfDouble[1][1] + paramArrayOfdouble1[2] * arrayOfDouble[2][1];
    double d8 = paramArrayOfdouble1[0] * arrayOfDouble[0][2] + paramArrayOfdouble1[1] * arrayOfDouble[1][2] + paramArrayOfdouble1[2] * arrayOfDouble[2][2];
    double d9 = Math.sqrt(d6 * d6 + d7 * d7 + d8 * d8);
    return new double[] { d6 / d9, d7 / d9, d8 / d9 };
  }
  
  public double[] a(gU paramgU) {
    if (paramgU.rV.equals("fr"))
      return a(paramgU.rW, this.rU, this.rS); 
    if (paramgU.rV.equals("fu"))
      return a(paramgU.rW, this.rU, this.rT); 
    if (paramgU.rV.equals("ur"))
      return a(paramgU.rW, this.rT, this.rS); 
    if (paramgU.rV.equals("uf"))
      return a(paramgU.rW, this.rT, this.rU); 
    if (paramgU.rV.equals("ru"))
      return a(paramgU.rW, this.rS, this.rT); 
    if (paramgU.rV.equals("rf"))
      return a(paramgU.rW, this.rS, this.rU); 
    throw new RuntimeException("Unsupported rotation axis");
  }
  
  public double[] c(double[] paramArrayOfdouble) {
    double d1 = paramArrayOfdouble[0] * this.rS[0] + paramArrayOfdouble[1] * this.rT[0] + paramArrayOfdouble[2] * this.rU[0];
    double d2 = paramArrayOfdouble[0] * this.rS[1] + paramArrayOfdouble[1] * this.rT[1] + paramArrayOfdouble[2] * this.rU[1];
    double d3 = paramArrayOfdouble[0] * this.rS[2] + paramArrayOfdouble[1] * this.rT[2] + paramArrayOfdouble[2] * this.rU[2];
    return new double[] { d1, d2, d3 };
  }
  
  public double[] d(double[] paramArrayOfdouble) {
    double d1 = paramArrayOfdouble[0] * this.rS[0] + paramArrayOfdouble[1] * this.rS[1] + paramArrayOfdouble[2] * this.rS[2];
    double d2 = paramArrayOfdouble[0] * this.rT[0] + paramArrayOfdouble[1] * this.rT[1] + paramArrayOfdouble[2] * this.rT[2];
    double d3 = paramArrayOfdouble[0] * this.rU[0] + paramArrayOfdouble[1] * this.rU[1] + paramArrayOfdouble[2] * this.rU[2];
    return new double[] { d1, d2, d3 };
  }
  
  private void a(double[] paramArrayOfdouble, StringBuffer paramStringBuffer1, StringBuffer paramStringBuffer2, StringBuffer paramStringBuffer3, int paramInt) {
    int i = paramStringBuffer1.length();
    paramStringBuffer1.append(a(paramArrayOfdouble[0], paramInt));
    paramStringBuffer2.append(a(paramArrayOfdouble[1], paramInt));
    paramStringBuffer3.append(a(paramArrayOfdouble[2], paramInt));
    int j = Math.max(Math.max(paramStringBuffer1.length(), paramStringBuffer2.length()), paramStringBuffer3.length());
    while (paramStringBuffer1.length() < j)
      paramStringBuffer1.insert(i, ' '); 
    while (paramStringBuffer2.length() < j)
      paramStringBuffer2.insert(i, ' '); 
    while (paramStringBuffer3.length() < j)
      paramStringBuffer3.insert(i, ' '); 
  }
  
  private void a(StringBuffer paramStringBuffer1, StringBuffer paramStringBuffer2, StringBuffer paramStringBuffer3, int paramInt) {
    int i = Math.max(Math.max(paramStringBuffer1.length(), paramStringBuffer2.length()), paramStringBuffer3.length());
    while (paramStringBuffer1.length() < i)
      paramStringBuffer1.append(' '); 
    while (paramStringBuffer2.length() < i)
      paramStringBuffer2.append(' '); 
    while (paramStringBuffer3.length() < i)
      paramStringBuffer3.append(' '); 
    paramStringBuffer1.append("| ");
    paramStringBuffer2.append("| ");
    paramStringBuffer3.append("| ");
    a(this.rS, paramStringBuffer1, paramStringBuffer2, paramStringBuffer3, paramInt);
    paramStringBuffer1.append(' ');
    paramStringBuffer2.append(' ');
    paramStringBuffer3.append(' ');
    a(this.rT, paramStringBuffer1, paramStringBuffer2, paramStringBuffer3, paramInt);
    paramStringBuffer1.append(' ');
    paramStringBuffer2.append(' ');
    paramStringBuffer3.append(' ');
    a(this.rU, paramStringBuffer1, paramStringBuffer2, paramStringBuffer3, paramInt);
    paramStringBuffer1.append(" |");
    paramStringBuffer2.append(" |");
    paramStringBuffer3.append(" |");
  }
  
  public String toString() {
    return toString(12);
  }
  
  public String toString(int paramInt) {
    StringBuffer stringBuffer1 = new StringBuffer();
    StringBuffer stringBuffer2 = new StringBuffer();
    StringBuffer stringBuffer3 = new StringBuffer();
    a(stringBuffer1, stringBuffer2, stringBuffer3, paramInt);
    StringBuffer stringBuffer4 = new StringBuffer();
    stringBuffer4.append(stringBuffer1).append("\n");
    stringBuffer4.append(stringBuffer2).append("\n");
    stringBuffer4.append(stringBuffer3).append("\n");
    return stringBuffer4.toString();
  }
  
  static String e(double[] paramArrayOfdouble) {
    return a(paramArrayOfdouble, 12);
  }
  
  static String a(double[] paramArrayOfdouble, int paramInt) {
    return "[ " + b(paramArrayOfdouble[0], paramInt) + " , " + b(paramArrayOfdouble[1], paramInt) + " , " + b(paramArrayOfdouble[2], paramInt) + " ]";
  }
  
  static String f(double[] paramArrayOfdouble) {
    return b(paramArrayOfdouble, 12);
  }
  
  static String b(double[] paramArrayOfdouble, int paramInt) {
    return "[ " + b(paramArrayOfdouble[0], paramInt) + " , " + b(paramArrayOfdouble[1], paramInt) + " , " + b(paramArrayOfdouble[2], paramInt) + " , " + b(paramArrayOfdouble[3], paramInt) + " ]";
  }
  
  static String a(double paramDouble, int paramInt) {
    if (Double.isInfinite(paramDouble))
      return "Infinite"; 
    if (Double.isNaN(paramDouble))
      return "NaN"; 
    BigDecimal bigDecimal = new BigDecimal(paramDouble);
    bigDecimal = bigDecimal.setScale(paramInt, RoundingMode.HALF_UP);
    return bigDecimal.toPlainString();
  }
  
  static String b(double paramDouble, int paramInt) {
    if (Double.isInfinite(paramDouble))
      return "Infinite"; 
    if (Double.isNaN(paramDouble))
      return "NaN"; 
    BigDecimal bigDecimal = new BigDecimal(paramDouble);
    bigDecimal = bigDecimal.setScale(paramInt, RoundingMode.HALF_UP);
    String str = bigDecimal.toPlainString();
    if (paramInt <= 0)
      return str; 
    while (str.endsWith("0") && !str.endsWith(".0"))
      str = str.substring(0, str.length() - 1); 
    return str;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\gT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */