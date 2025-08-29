package nomanssave;

public class gM {
  private final eV rE;
  
  private final eY rF;
  
  private final int index;
  
  public static gM[] D(eY parameY) {
    eV eV1 = parameY.d("SquadronUnlockedPilotSlots");
    eV eV2 = parameY.d("SquadronPilots");
    if (eV1 == null || eV2 == null)
      return new gM[0]; 
    gM[] arrayOfGM = new gM[Math.min(eV1.size(), eV2.size())];
    for (byte b = 0; b < arrayOfGM.length; b++)
      arrayOfGM[b] = new gM(eV1, eV2.V(b), b); 
    return arrayOfGM;
  }
  
  private gM(eV parameV, eY parameY, int paramInt) {
    this.rE = parameV;
    this.rF = parameY;
    this.index = paramInt;
  }
  
  public boolean isEnabled() {
    return this.rE.ab(this.index);
  }
  
  public void setEnabled(boolean paramBoolean) {
    this.rE.a(this.index, Boolean.valueOf(paramBoolean));
  }
  
  public boolean isValid() {
    return (this.rF.d("NPCResource.Seed").ab(0) && this.rF.d("ShipResource.Seed").ab(0));
  }
  
  public gy ed() {
    return gy.as(this.rF.getValueAsString("NPCResource.Filename"));
  }
  
  public void a(gy paramgy) {
    this.rF.b("NPCResource.Filename", paramgy.K());
  }
  
  public String ee() {
    eV eV1 = this.rF.d("NPCResource.Seed");
    return eV1.ab(0) ? eV1.X(1) : "";
  }
  
  public void ax(String paramString) {
    eV eV1 = this.rF.d("NPCResource.Seed");
    if (paramString == null || paramString.length() == 0) {
      eV1.a(0, Boolean.valueOf(false));
      eV1.a(1, "0x0");
    } else {
      eV1.a(0, Boolean.valueOf(true));
      eV1.a(1, paramString);
    } 
  }
  
  public gL ef() {
    return gL.aw(this.rF.getValueAsString("ShipResource.Filename"));
  }
  
  public void a(gL paramgL) {
    this.rF.b("ShipResource.Filename", paramgL.K());
  }
  
  public String eg() {
    eV eV1 = this.rF.d("ShipResource.Seed");
    return eV1.ab(0) ? eV1.X(1) : "";
  }
  
  public void ay(String paramString) {
    eV eV1 = this.rF.d("ShipResource.Seed");
    if (paramString == null || paramString.length() == 0) {
      eV1.a(0, Boolean.valueOf(false));
      eV1.a(1, "0x0");
    } else {
      eV1.a(0, Boolean.valueOf(true));
      eV1.a(1, paramString);
    } 
  }
  
  public int eh() {
    return this.rF.J("PilotRank");
  }
  
  public void aI(int paramInt) {
    this.rF.b("PilotRank", Integer.valueOf(paramInt));
  }
  
  public String toString() {
    return isEnabled() ? (isValid() ? ("Wingman " + this.index) : "EMPTY") : "LOCKED";
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\gM.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */