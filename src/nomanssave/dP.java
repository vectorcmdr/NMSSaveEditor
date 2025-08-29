package nomanssave;

class dP extends G {
  dP(dN paramdN) {}
  
  protected String g(String paramString) {
    gH gH = (gH)dN.p(this.ia).getSelectedItem();
    if (gH == null)
      return ""; 
    double d = gH.ec();
    try {
      double d1 = hf.a(paramString, 0.0D, 1000.0D);
      if (d1 != d)
        gH.i(d1); 
      return Double.toString(d1);
    } catch (RuntimeException runtimeException) {
      return Double.toString(d);
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\dP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */