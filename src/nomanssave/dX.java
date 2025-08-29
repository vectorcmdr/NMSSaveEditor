package nomanssave;

class dX extends G {
  dX(dN paramdN) {}
  
  protected String g(String paramString) {
    if (dN.o(this.ia) == null)
      return ""; 
    int i = dN.o(this.ia).dN();
    try {
      int j = hf.b(paramString, 1, 200);
      if (j != i)
        dN.o(this.ia).aC(j); 
      return Integer.toString(j);
    } catch (RuntimeException runtimeException) {
      return Integer.toString(i);
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\dX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */