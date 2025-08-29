package nomanssave;

class aO extends G {
  aO(aJ paramaJ) {}
  
  protected String g(String paramString) {
    if (aJ.a(this.dj) == null)
      return ""; 
    long l = aJ.a(this.dj).dK();
    try {
      long l1 = hf.a(paramString, 0L, 4294967295L);
      if (l1 != l)
        aJ.a(this.dj).f(l1); 
      return Long.toString(l1);
    } catch (RuntimeException runtimeException) {
      return Long.toString(l);
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\aO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */