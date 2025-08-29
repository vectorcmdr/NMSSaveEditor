package nomanssave;

class cS implements gD {
  final String filename;
  
  final int index;
  
  cS(cN paramcN, String paramString) {
    this.filename = paramString;
    this.index = cN.a(paramcN).size() + 1;
  }
  
  public String K() {
    return this.filename;
  }
  
  public String toString() {
    return "Unknown " + this.index;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\cS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */