package nomanssave;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class gE {
  private final int index;
  
  private final eY bf;
  
  public static gE[] z(eY parameY) {
    eV eV1 = parameY.d("TeleportEndpoints");
    List list = (List)eV1.bB().filter(parameY -> "Settlement".equals(parameY.getValueAsString("TeleporterType"))).map(parameY -> hl.n(parameY.H("UniverseAddress"))).collect(Collectors.toList());
    eV eV2 = parameY.d("SettlementStatesV2");
    if (eV2 == null || eV2.size() == 0)
      return new gE[0]; 
    ArrayList<gE> arrayList = new ArrayList();
    for (byte b = 0; b < eV2.size(); b++) {
      eY eY1 = eV2.V(b);
      hl hl = hl.n(eY1.getValue("UniverseAddress"));
      if (list.contains(hl))
        arrayList.add(new gE(b, eY1)); 
    } 
    return arrayList.<gE>toArray(new gE[0]);
  }
  
  private gE(int paramInt, eY parameY) {
    this.index = paramInt;
    this.bf = parameY;
  }
  
  public int getIndex() {
    return this.index;
  }
  
  public String getName() {
    return this.bf.getValueAsString("Name");
  }
  
  public void setName(String paramString) {
    this.bf.b("Name", paramString);
  }
  
  public int aq(int paramInt) {
    return this.bf.d("Stats").Y(paramInt);
  }
  
  public void e(int paramInt1, int paramInt2) {
    this.bf.d("Stats").a(paramInt1, Integer.valueOf(paramInt2));
  }
  
  public int a(gG paramgG) {
    return this.bf.d("Stats").Y(paramgG.ordinal());
  }
  
  public void a(gG paramgG, int paramInt) {
    this.bf.d("Stats").a(paramgG.ordinal(), Integer.valueOf(paramInt));
  }
  
  public int dW() {
    return this.bf.d("Perks").size();
  }
  
  public String aH(int paramInt) {
    return this.bf.d("Perks").X(paramInt);
  }
  
  public void c(int paramInt, String paramString) {
    this.bf.d("Perks").a(paramInt, paramString);
  }
  
  public String cK() {
    return this.bf.I("SeedValue");
  }
  
  public void aa(String paramString) {
    this.bf.b("SeedValue", paramString);
  }
  
  public gF[] dX() {
    eV eV = this.bf.d("ProductionState");
    if (eV == null)
      return new gF[0]; 
    ArrayList<gF> arrayList = new ArrayList();
    for (byte b = 0; b < eV.size(); b++) {
      eY eY1 = eV.V(b);
      arrayList.add(new gF(this, eY1, null));
    } 
    return arrayList.<gF>toArray(new gF[0]);
  }
  
  public String toString() {
    return getName();
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\gE.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */