package nomanssave;

class dG extends G {
  dG(dE paramdE) {}
  
  protected String g(String paramString) {
    gE gE = (gE)dE.a(this.hE).getSelectedItem();
    if (gE == null)
      return ""; 
    paramString = paramString.trim();
    if (!paramString.equals(gE.getName())) {
      gE.setName(paramString);
      dE.c(this.hE).setText(paramString);
    } 
    return paramString;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\dG.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */