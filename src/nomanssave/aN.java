package nomanssave;

class aN extends G {
  aN(aJ paramaJ, Application paramApplication) {}
  
  protected String g(String paramString) {
    if (aJ.a(this.dj) == null)
      return ""; 
    long l = aJ.a(this.dj).dJ();
    try {
      long l1 = hf.a(paramString, 0L, 4294967295L);
      if (l1 != l) {
        aJ.a(this.dj).e(l1);
        this.bv.C();
      } 
      return Long.toString(l1);
    } catch (RuntimeException runtimeException) {
      return Long.toString(l);
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\aN.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */