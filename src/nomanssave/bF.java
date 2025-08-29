package nomanssave;

class bF implements bK {
  bF(bE parambE) {}
  
  public String getID() {
    return "ExtremeSurvival";
  }
  
  public boolean isSpecial() {
    return false;
  }
  
  public String ab() {
    return Double.toString(bE.a(this.ey).dT());
  }
  
  public void l(String paramString) {
    double d = Double.parseDouble(paramString);
    bE.a(this.ey).g(d);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\bF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */