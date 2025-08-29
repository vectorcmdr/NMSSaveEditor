package nomanssave;

class bA extends G {
  private final int index;
  
  private bA(bl parambl, int paramInt) {
    this.index = paramInt;
  }
  
  protected String g(String paramString) {
    if (bl.b(this.er) < 0)
      return ""; 
    int i = bl.c(this.er)[bl.b(this.er)].aq(this.index);
    try {
      int j = hf.b(paramString, 0, 50);
      if (j != i)
        bl.c(this.er)[bl.b(this.er)].e(this.index, j); 
      return Integer.toString(j);
    } catch (RuntimeException runtimeException) {
      return Integer.toString(i);
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\bA.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */