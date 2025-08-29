package nomanssave;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class gV {
  private static double[] a(eY parameY, String paramString) {
    eV eV = parameY.d(paramString);
    if (eV.size() != 3)
      throw new RuntimeException("Invalid " + paramString + " coordinates"); 
    return new double[] { eV.aa(0), eV.aa(1), eV.aa(2) };
  }
  
  private static void a(eY parameY, String paramString, double[] paramArrayOfdouble) {
    parameY.b(paramString, new eV(new Object[] { new Double(Double.isNaN(paramArrayOfdouble[0]) ? 0.0D : paramArrayOfdouble[0]), new Double(Double.isNaN(paramArrayOfdouble[1]) ? 0.0D : paramArrayOfdouble[1]), new Double(Double.isNaN(paramArrayOfdouble[2]) ? 0.0D : paramArrayOfdouble[2]) }));
  }
  
  public static boolean F(eY parameY) {
    return b(parameY, "^BUILDSIGNAL");
  }
  
  public static boolean b(eY parameY, String paramString) {
    eV eV = parameY.d("Objects");
    eY eY1 = null;
    eY eY2 = null;
    for (byte b = 0; b < eV.size(); b++) {
      eY eY3 = eV.V(b);
      if ("^BASE_FLAG".equals(eY3.getValueAsString("ObjectID"))) {
        if (eY1 == null) {
          eY1 = eY3;
        } else {
          hc.warn("  multiple base computers found");
          return false;
        } 
      } else if (paramString.equals(eY3.getValueAsString("ObjectID"))) {
        if (eY2 == null) {
          eY2 = eY3;
        } else {
          hc.warn("  multiple " + paramString + " objects found");
          return false;
        } 
      } 
    } 
    if (eY1 == null) {
      hc.warn("  no base computer found");
      return false;
    } 
    if (eY2 == null) {
      hc.warn("  no " + paramString + " object found");
      return false;
    } 
    a(parameY, eY1, eY2);
    return true;
  }
  
  public static List G(eY parameY) {
    ArrayList<eY> arrayList = new ArrayList();
    boolean bool = false;
    eV eV = parameY.d("Objects");
    for (byte b = 0; b < eV.size(); b++) {
      eY eY1 = eV.V(b);
      String str = eY1.getValueAsString("ObjectID");
      if ("^BASE_FLAG".equals(eY1.getValueAsString("ObjectID"))) {
        bool = true;
      } else if ("^BUILDSIGNAL".equals(str)) {
        arrayList.add(eY1);
      } else if ("^BP_ANALYSER".equals(str)) {
        arrayList.add(eY1);
      } else if ("^BUILDBEACON".equals(str)) {
        arrayList.add(eY1);
      } 
    } 
    return bool ? arrayList : Collections.emptyList();
  }
  
  public static boolean a(eY parameY1, eY parameY2) {
    eV eV = parameY1.d("Objects");
    boolean bool = false;
    eY eY1 = null;
    for (byte b = 0; b < eV.size(); b++) {
      eY eY2 = eV.V(b);
      if ("^BASE_FLAG".equals(eY2.getValueAsString("ObjectID"))) {
        if (eY1 == null) {
          eY1 = eY2;
        } else {
          hc.warn("  multiple base computers found");
          return false;
        } 
      } else if (eY2 == parameY2) {
        bool = true;
      } 
    } 
    if (eY1 == null) {
      hc.warn("  no base computer found");
      return false;
    } 
    if (!bool) {
      hc.warn("  replacement object found");
      return false;
    } 
    a(parameY1, eY1, parameY2);
    return true;
  }
  
  private static void a(eY parameY1, eY parameY2, eY parameY3) {
    double[] arrayOfDouble1 = a(parameY1, "Position");
    double[] arrayOfDouble2 = a(parameY1, "Forward");
    double[] arrayOfDouble3 = a(parameY3, "Position");
    gT gT = new gT(arrayOfDouble1, arrayOfDouble2);
    double[] arrayOfDouble4 = gT.c(arrayOfDouble3);
    arrayOfDouble4[0] = arrayOfDouble4[0] + arrayOfDouble1[0];
    arrayOfDouble4[1] = arrayOfDouble4[1] + arrayOfDouble1[1];
    arrayOfDouble4[2] = arrayOfDouble4[2] + arrayOfDouble1[2];
    a(parameY1, "Position", arrayOfDouble4);
    arrayOfDouble4 = a(parameY2, "At");
    double[] arrayOfDouble5 = a(parameY3, "At");
    a(parameY2, "At", arrayOfDouble5);
    a(parameY3, "At", arrayOfDouble4);
    arrayOfDouble4 = new double[] { -arrayOfDouble3[0], -arrayOfDouble3[1], -arrayOfDouble3[2] };
    a(parameY3, "Position", arrayOfDouble4);
    eV eV = parameY1.d("Objects");
    for (byte b = 0; b < eV.size(); b++) {
      eY eY1 = eV.V(b);
      if (eY1 != parameY2 && eY1 != parameY3) {
        arrayOfDouble4 = a(eY1, "Position");
        arrayOfDouble4[0] = arrayOfDouble4[0] - arrayOfDouble3[0];
        arrayOfDouble4[1] = arrayOfDouble4[1] - arrayOfDouble3[1];
        arrayOfDouble4[2] = arrayOfDouble4[2] - arrayOfDouble3[2];
        a(eY1, "Position", arrayOfDouble4);
      } 
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\gV.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */