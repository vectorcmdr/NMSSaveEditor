package nomanssave;

class bI implements bK {
  bI(bE parambE) {}
  
  public String getID() {
    return "TWordsLearnt";
  }
  
  public boolean isSpecial() {
    return true;
  }
  
  public String ab() {
    return Integer.toString(bE.a(this.ey).b(eU.kr));
  }
  
  public void l(String paramString) {
    throw new RuntimeException("Cannot set words learnt");
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\bI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */