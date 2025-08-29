package nomanssave;

public class gF implements gQ {
  private final eY kK;
  
  private gF(gE paramgE, eY parameY) {
    this.kK = parameY;
  }
  
  public boolean isValid() {
    String str = this.kK.getValueAsString("ElementId");
    return (str != null && str.length() > 1);
  }
  
  public String getType() {
    return "Product";
  }
  
  public Object dz() {
    return this.kK.getValue("ElementId");
  }
  
  public void m(Object paramObject) {
    this.kK.b("ElementId", paramObject);
    this.kK.b("LastChangeTimestamp", Integer.valueOf((int)(System.currentTimeMillis() / 1000L)));
  }
  
  public int dA() {
    return this.kK.J("Amount");
  }
  
  public void aA(int paramInt) {
    this.kK.b("Amount", Integer.valueOf(paramInt));
    this.kK.b("LastChangeTimestamp", Integer.valueOf((int)(System.currentTimeMillis() / 1000L)));
  }
  
  public int dB() {
    return 999;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\gF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */