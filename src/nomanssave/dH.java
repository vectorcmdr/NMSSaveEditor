package nomanssave;

class dH extends G {
  dH(dE paramdE) {}
  
  protected String g(String paramString) {
    gE gE = (gE)dE.a(this.hE).getSelectedItem();
    if (gE == null)
      return ""; 
    try {
      paramString = hg.aB(paramString).toString();
      if (!paramString.equals(gE.cK()))
        gE.aa(paramString); 
      return paramString;
    } catch (RuntimeException runtimeException) {
      return gE.cK();
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\dH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */