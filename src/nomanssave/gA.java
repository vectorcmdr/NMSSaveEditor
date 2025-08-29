package nomanssave;

public class gA {
  private final eS rb;
  
  private gA(gz paramgz, eS parameS) {
    this.rb = parameS;
  }
  
  public String getID() {
    return this.rb.getID();
  }
  
  public boolean c(eU parameU) {
    for (String str : this.rb.bw()) {
      if (this.rb.z(str) == parameU)
        return gz.a(this.rc, str, parameU.ordinal()); 
    } 
    return false;
  }
  
  public void a(eU parameU, boolean paramBoolean) {
    for (String str : this.rb.bw()) {
      if (this.rb.z(str) == parameU)
        gz.a(this.rc, str, parameU.ordinal(), paramBoolean); 
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\gA.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */