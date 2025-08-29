package nomanssave;

class cQ {
  final String value;
  
  cQ(cN paramcN, String paramString) {
    this.value = paramString;
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject instanceof String) ? this.value.equals(paramObject) : ((paramObject instanceof cS) ? this.value.equals(((cS)paramObject).filename) : super.equals(paramObject));
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\cQ.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */