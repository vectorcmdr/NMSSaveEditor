package nomanssave;

class M extends G {
  M(I paramI) {}
  
  protected String g(String paramString) {
    gf gf = (gf)I.j(this.bt).getSelectedItem();
    if (gf == null)
      return ""; 
    paramString = paramString.trim();
    if (!paramString.equals(gf.getName())) {
      gf.setName(paramString);
      I.f(this.bt).setText(paramString);
    } 
    return paramString;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\M.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */