package nomanssave;

class aL extends G {
  aL(aJ paramaJ) {}
  
  protected String g(String paramString) {
    if (aJ.a(this.dj) == null)
      return ""; 
    int i = aJ.a(this.dj).dN();
    try {
      int j = hf.b(paramString, 0, 100);
      if (j != i)
        aJ.a(this.dj).aC(j); 
      return Integer.toString(j);
    } catch (RuntimeException runtimeException) {
      return Integer.toString(i);
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\aL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */