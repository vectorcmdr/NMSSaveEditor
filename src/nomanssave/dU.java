package nomanssave;

class dU extends G {
  dU(dN paramdN) {}
  
  protected String g(String paramString) {
    gH gH = (gH)dN.p(this.ia).getSelectedItem();
    if (gH == null)
      return ""; 
    try {
      paramString = hg.aB(paramString).toString();
      if (!paramString.equals(gH.cK()))
        gH.aa(paramString); 
      return paramString;
    } catch (RuntimeException runtimeException) {
      return gH.cK();
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\dU.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */