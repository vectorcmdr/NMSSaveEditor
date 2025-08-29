package nomanssave;

class dT extends G {
  dT(dN paramdN) {}
  
  protected String g(String paramString) {
    gH gH = (gH)dN.p(this.ia).getSelectedItem();
    if (gH == null)
      return ""; 
    paramString = paramString.trim();
    if (!paramString.equals(gH.getName())) {
      gH.setName(paramString);
      dN.b(this.ia).setText(paramString);
    } 
    return paramString;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\dT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */