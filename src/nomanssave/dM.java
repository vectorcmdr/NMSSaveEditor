package nomanssave;

class dM extends G {
  private final gG hH;
  
  private dM(dE paramdE, gG paramgG) {
    this.hH = paramgG;
  }
  
  protected String g(String paramString) {
    gE gE = (gE)dE.a(this.hE).getSelectedItem();
    if (gE == null)
      return ""; 
    int i = gE.aq(this.hH.ordinal());
    try {
      int j = hf.b(paramString, 0, this.hH.dY());
      if (j != i)
        gE.e(this.hH.ordinal(), j); 
      return Integer.toString(j);
    } catch (RuntimeException runtimeException) {
      return Integer.toString(i);
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\dM.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */