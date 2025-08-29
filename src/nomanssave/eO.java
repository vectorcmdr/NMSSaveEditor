package nomanssave;

class eO {
  final String id;
  
  eO(String paramString) {
    this.id = paramString;
  }
  
  public boolean equals(Object paramObject) {
    eM eM = (eM)paramObject;
    return eM.jW ? this.id.startsWith(String.valueOf(eM.id) + "#") : this.id.equals(eM.id);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\eO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */