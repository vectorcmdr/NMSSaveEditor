package nomanssave;

class aK extends G {
  aK(aJ paramaJ) {}
  
  protected String g(String paramString) {
    if (aJ.a(this.dj) == null)
      return ""; 
    int i = aJ.a(this.dj).dM();
    try {
      int j = hf.b(paramString, 1, 200);
      if (j != i)
        aJ.a(this.dj).aB(j); 
      return Integer.toString(j);
    } catch (RuntimeException runtimeException) {
      return Integer.toString(i);
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\aK.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */