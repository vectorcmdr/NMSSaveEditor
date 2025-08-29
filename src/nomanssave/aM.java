package nomanssave;

class aM extends G {
  aM(aJ paramaJ) {}
  
  protected String g(String paramString) {
    if (aJ.a(this.dj) == null)
      return ""; 
    int i = aJ.a(this.dj).dO();
    try {
      int j = hf.b(paramString, 0, 100);
      if (j != i)
        aJ.a(this.dj).aD(j); 
      return Integer.toString(j);
    } catch (RuntimeException runtimeException) {
      return Integer.toString(i);
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\aM.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */