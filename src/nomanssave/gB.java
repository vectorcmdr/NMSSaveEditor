package nomanssave;

public class gB {
  private final eY oG;
  
  public static gB x(eY parameY) {
    return new gB(parameY);
  }
  
  private gB(eY parameY) {
    this.oG = parameY;
  }
  
  public int dU() {
    return this.oG.J("ActiveMultioolIndex");
  }
  
  public void aF(int paramInt) {
    eY eY1 = this.oG.H("Multitools[" + paramInt + "]");
    if (eY1 == null || !eY1.M("Seed[0]"))
      throw new RuntimeException("Cannot set current multitool"); 
    this.oG.b("ActiveMultioolIndex", Integer.valueOf(paramInt));
    this.oG.b("WeaponInventory", eY1.H("Store").bE());
    this.oG.b("CurrentWeapon.GenerationSeed[1]", eY1.I("Seed[1]"));
    this.oG.b("CurrentWeapon.Filename", eY1.getValueAsString("Resource.Filename"));
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\gB.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */