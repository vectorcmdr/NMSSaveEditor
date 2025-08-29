package nomanssave;

class dZ extends G {
  dZ(dN paramdN) {}
  
  protected String g(String paramString) {
    gH gH = (gH)dN.p(this.ia).getSelectedItem();
    if (gH == null)
      return ""; 
    double d = gH.eb();
    try {
      double d1 = hf.a(paramString, 0.0D, 1000.0D);
      if (d1 != d)
        gH.h(d1); 
      return Double.toString(d1);
    } catch (RuntimeException runtimeException) {
      return Double.toString(d);
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\dZ.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */