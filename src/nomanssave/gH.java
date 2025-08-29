package nomanssave;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class gH {
  private final int index;
  
  private final eY rn;
  
  private final List gT;
  
  public static gH[] C(eY parameY) {
    eV eV = parameY.d("ShipOwnership");
    if (eV == null || eV.size() == 0)
      return new gH[0]; 
    ArrayList<gH> arrayList = new ArrayList();
    for (byte b = 0; b < eV.size(); b++) {
      eY eY1 = eV.V(b);
      if (eY1.d("Resource.Seed").ab(0))
        arrayList.add(new gH(b, eY1, eY1.H("Inventory"), eY1.H("Inventory_TechOnly"), eY1.H("Inventory_Cargo"))); 
    } 
    return arrayList.<gH>toArray(new gH[0]);
  }
  
  public static gH c(eY parameY, File paramFile) {
    eY eY2;
    eV eV1 = parameY.d("ShipOwnership");
    if (eV1 == null || eV1.size() == 0)
      throw new RuntimeException("Ship cannot be imported to current file!"); 
    byte b = -1;
    for (byte b1 = 0; b1 < eV1.size(); b1++) {
      eY eY3 = eV1.V(b1);
      if (!eY3.d("Resource.Seed").ab(0)) {
        b = b1;
        break;
      } 
    } 
    if (b < 0)
      throw new RuntimeException("Ship cannot be imported to current file!"); 
    eY eY1 = gR.az("ship");
    Exception exception1 = null;
    Exception exception2 = null;
    try {
      ff ff = new ff(new FileInputStream(paramFile));
      try {
        if (eY1 == null) {
          eY1 = ff.bK();
        } else {
          eY1.c(ff.bK());
        } 
      } finally {
        if (ff != null)
          ff.close(); 
      } 
    } finally {
      exception2 = null;
      if (exception1 == null) {
        exception1 = exception2;
      } else if (exception1 != exception2) {
        exception1.addSuppressed(exception2);
      } 
    } 
    eV eV2 = eY1.d("Resource.Seed");
    if (eV2 == null || !eV2.ab(0))
      throw new RuntimeException("Invalid ship data"); 
    eV1.a(b, eY1);
    return new gH(b, eY1, eY2, eY1.H("Inventory_TechOnly"), eY1.H("Inventory_Cargo"));
  }
  
  private static Function a(gH paramgH, String[] paramArrayOfString) {
    return paramgt -> {
        String str = paramgH.getName();
        if (str == null || str.length() == 0)
          str = "Ship [" + paramgH.index + "]"; 
        return new String[] { str, paramArrayOfString[paramgH.dZ() ? 1 : 0] };
      };
  }
  
  private gH(int paramInt, eY parameY1, eY parameY2, eY parameY3, eY parameY4) {
    String[] arrayOfString1;
    String[] arrayOfString3;
    this.index = paramInt;
    this.rn = parameY1;
    String[] arrayOfString2 = { "Technology", "Organ Chamber" };
    char c = Character.MIN_VALUE;
    byte b1 = 8;
    byte b2 = 6;
    byte b3 = 8;
    byte b4 = 6;
    boolean bool = Application.e().D();
    if (bool) {
      b1 = 10;
      b2 = 12;
      b3 = 10;
      b4 = 6;
      arrayOfString1 = new String[] { "Cargo", "Storage Sacs" };
      arrayOfString3 = new String[] { "Unused", "Unused" };
      parameY4 = null;
    } else {
      c = '฀';
      arrayOfString1 = new String[] { "General", "Storage Sacs" };
      arrayOfString3 = new String[] { "Cargo", "Inflated Sacs" };
    } 
    ArrayList<gI> arrayList = new ArrayList();
    arrayList.add(new gI(this, a(this, arrayOfString1), parameY2, 0, b1, b2, false, true, bool, paramInt));
    if (parameY3 != null)
      arrayList.add(new gJ(this, a(this, arrayOfString2), parameY3, 0, b3, b4, true, true, paramInt)); 
    if (parameY4 != null)
      arrayList.add(new gK(this, a(this, arrayOfString3), parameY4, c, 8, 6, false, true, paramInt)); 
    this.gT = Collections.unmodifiableList(arrayList);
  }
  
  public void a(File paramFile, boolean paramBoolean) {
    Exception exception1 = null;
    Exception exception2 = null;
  }
  
  public int getIndex() {
    return this.index;
  }
  
  public String getName() {
    return this.rn.getValueAsString("Name");
  }
  
  public void setName(String paramString) {
    this.rn.b("Name", paramString);
  }
  
  public boolean dZ() {
    return (gL.aw(cT()) == gL.rv);
  }
  
  private int ea() {
    gL gL = gL.aw(cT());
    return (gL == null) ? 4 : gL.ea();
  }
  
  public String cT() {
    return this.rn.getValueAsString("Resource.Filename");
  }
  
  public void ag(String paramString) {
    this.rn.b("Resource.Filename", paramString);
    gL gL = gL.aw(paramString);
    this.gT.stream().forEach(paramgt -> paramgt.az((paramgL == null) ? 4 : paramgL.ea()));
    if (gL == gL.rv) {
      d("^ALIEN_SHIP", 1.0D);
      av("^ROBOT_SHIP");
    } else if (gL == gL.rA) {
      av("^ALIEN_SHIP");
      d("^ROBOT_SHIP", 1.0D);
    } else {
      av("^ALIEN_SHIP");
      av("^ROBOT_SHIP");
    } 
  }
  
  public String cK() {
    return this.rn.d("Resource.Seed").X(1);
  }
  
  public void aa(String paramString) {
    this.rn.d("Resource.Seed").a(1, paramString);
  }
  
  public void cm() {
    this.rn.b("Resource.Filename", "");
    this.rn.d("Resource.Seed").a(0, Boolean.FALSE);
    this.rn.d("Resource.Seed").a(1, "0x0");
  }
  
  public String cW() {
    return this.rn.getValueAsString("Inventory.Class.InventoryClass");
  }
  
  public void aj(String paramString) {
    this.rn.b("Inventory.Class.InventoryClass", paramString);
    eY eY1 = this.rn.H("Inventory_TechOnly.Class");
    if (eY1 != null)
      eY1.b("InventoryClass", paramString); 
    eY1 = this.rn.H("Inventory_Cargo.Class");
    if (eY1 != null)
      eY1.b("InventoryClass", paramString); 
  }
  
  public List cC() {
    return this.gT;
  }
  
  private double ak(String paramString) {
    return ((gt)this.gT.get(0)).ak(paramString);
  }
  
  private void d(String paramString, double paramDouble) {
    this.gT.stream().forEach(paramgt -> paramgt.d(paramString, paramDouble));
  }
  
  private void av(String paramString) {
    this.gT.stream().forEach(paramgt -> {
        
        });
  }
  
  public double dF() {
    return ak("^SHIP_DAMAGE");
  }
  
  public void d(double paramDouble) {
    d("^SHIP_DAMAGE", paramDouble);
  }
  
  public double eb() {
    return ak("^SHIP_SHIELD");
  }
  
  public void h(double paramDouble) {
    d("^SHIP_SHIELD", paramDouble);
  }
  
  public double cX() {
    return ak("^SHIP_HYPERDRIVE");
  }
  
  public void a(double paramDouble) {
    d("^SHIP_HYPERDRIVE", paramDouble);
  }
  
  public double ec() {
    return ak("^SHIP_AGILE");
  }
  
  public void i(double paramDouble) {
    d("^SHIP_AGILE", paramDouble);
  }
  
  public String toString() {
    String str = getName();
    if (str != null && str.length() != 0)
      return str; 
    gL gL = gL.aw(cT());
    return (gL == null) ? ("Unknown [" + this.index + "]") : (gL + " [" + this.index + "]");
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\gH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */