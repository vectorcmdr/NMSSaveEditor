package nomanssave;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class gO {
  private final int index;
  
  private final eY rL;
  
  private final List gT;
  
  public static gO[] E(eY parameY) {
    eV eV = parameY.d("VehicleOwnership");
    if (eV == null || eV.size() == 0)
      return new gO[0]; 
    ArrayList<gO> arrayList = new ArrayList();
    for (byte b = 0; b < eV.size(); b++) {
      eY eY3 = eV.V(b);
      if (b != 4)
        arrayList.add(new gO(b, eY3, eY3.H("Inventory"), eY3.H("Inventory_TechOnly"))); 
    } 
    eY eY1 = parameY.H("FishPlatformLayout");
    eY eY2 = parameY.H("FishPlatformInventory");
    if (eY1 != null && eY2 != null)
      arrayList.add(new gO(eY1, eY2)); 
    return arrayList.<gO>toArray(new gO[0]);
  }
  
  private static Function a(gO paramgO, String paramString) {
    return paramgt -> new String[] { paramgO.getType(), paramString };
  }
  
  private gO(int paramInt, eY parameY1, eY parameY2, eY parameY3) {
    byte b1;
    String str;
    this.index = paramInt;
    this.rL = parameY1;
    if (paramInt == 5) {
      b1 = 32;
    } else if (paramInt == 6) {
      b1 = 128;
    } else {
      b1 = 16;
    } 
    boolean bool = Application.e().D();
    byte b2 = 8;
    byte b3 = 6;
    byte b4 = 8;
    byte b5 = 6;
    if (bool) {
      b2 = 10;
      b3 = 5;
      b4 = 10;
      b5 = 3;
      str = "Cargo";
    } else {
      str = "General";
    } 
    ArrayList<gP> arrayList = new ArrayList();
    arrayList.add(new gP(this, a(this, str), parameY2, 0, b2, b3, false, false, false, false, bool, b1));
    if (parameY3 != null)
      arrayList.add(new gt(a(this, "Technology"), parameY3, b1, b4, b5, true, false, false, false)); 
    this.gT = Collections.unmodifiableList(arrayList);
  }
  
  private gO(eY parameY1, eY parameY2) {
    this.index = 1000;
    this.rL = parameY1;
    parameY2.a((paramString, paramObject1, paramObject2) -> {
          if ("ValidSlotIndices".equals(paramString) && paramObject2 instanceof eV) {
            int i = ((eV)paramObject2).size();
            parameY.b("Slots", Integer.valueOf(i));
          } 
        });
    byte b1 = 8;
    byte b2 = 6;
    ArrayList<gt> arrayList = new ArrayList();
    arrayList.add(new gt(a(this, "Cold Storage"), parameY2, 2048, b1, b2, false, false, true, false));
    this.gT = Collections.unmodifiableList(arrayList);
  }
  
  public String getType() {
    return (this.index == 0) ? "Roamer" : ((this.index == 1) ? "Nomad" : ((this.index == 2) ? "Colossus" : ((this.index == 3) ? "Pilgrim" : ((this.index == 5) ? "Nautilon" : ((this.index == 6) ? "Minotaur" : ((this.index == 1000) ? "Skiff" : ("Vehicle " + this.index)))))));
  }
  
  public List cC() {
    return this.gT;
  }
  
  public String toString() {
    return getType();
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\gO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */