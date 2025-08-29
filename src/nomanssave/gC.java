package nomanssave;

public class gC {
  private final eY oG;
  
  public static gC y(eY parameY) {
    return new gC(parameY);
  }
  
  private gC(eY parameY) {
    this.oG = parameY;
  }
  
  public int dV() {
    return this.oG.J("PrimaryShip");
  }
  
  public void aG(int paramInt) {
    this.oG.b("PrimaryShip", Integer.valueOf(paramInt));
  }
  
  public int dM() {
    return this.oG.J("ShipHealth");
  }
  
  public void aB(int paramInt) {
    this.oG.b("ShipHealth", new Integer(paramInt));
  }
  
  public int dN() {
    return this.oG.J("ShipShield");
  }
  
  public void aC(int paramInt) {
    this.oG.b("ShipShield", new Integer(paramInt));
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\gC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */