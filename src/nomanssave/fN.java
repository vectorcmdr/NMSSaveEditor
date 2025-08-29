package nomanssave;

import java.util.ArrayList;

class fN implements ft {
  final int lR;
  
  fN(fJ paramfJ, int paramInt) {
    this.lR = paramInt;
  }
  
  public int getIndex() {
    return this.lR;
  }
  
  public boolean isEmpty() {
    return (fJ.b(this.mr)[this.lR * 2] == null && fJ.b(this.mr)[this.lR * 2 + 1] == null);
  }
  
  public fs[] bX() {
    hc.info("Loading saves for Slot " + (this.lR + 1) + "...");
    ArrayList<fM> arrayList = new ArrayList();
    if (fJ.b(this.mr)[this.lR * 2] != null)
      arrayList.add(fJ.b(this.mr)[this.lR * 2]); 
    if (fJ.b(this.mr)[this.lR * 2 + 1] != null)
      arrayList.add(fJ.b(this.mr)[this.lR * 2 + 1]); 
    aH.cG.listFiles(new fO(this, arrayList));
    arrayList.sort(new fP(this));
    return arrayList.<fs>toArray(new fs[0]);
  }
  
  public fn L() {
    long l = Long.MIN_VALUE;
    fn fn = null;
    if (fJ.b(this.mr)[this.lR * 2] != null) {
      fn = fJ.b(this.mr)[this.lR * 2].L();
      l = fJ.b(this.mr)[this.lR * 2].lastModified();
    } 
    if (fJ.b(this.mr)[this.lR * 2 + 1] != null) {
      long l1 = fJ.b(this.mr)[this.lR * 2 + 1].lastModified();
      if (l1 > l) {
        fn = fJ.b(this.mr)[this.lR * 2 + 1].L();
        l = l1;
      } 
    } 
    return fn;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Slot " + (this.lR + 1) + " - ");
    long l = Long.MIN_VALUE;
    String str = null;
    fn fn = null;
    if (fJ.b(this.mr)[this.lR * 2] != null) {
      fn = fJ.b(this.mr)[this.lR * 2].L();
      l = fJ.b(this.mr)[this.lR * 2].lastModified();
      str = fJ.b(this.mr)[this.lR * 2].getName();
    } 
    if (fJ.b(this.mr)[this.lR * 2 + 1] != null) {
      long l1 = fJ.b(this.mr)[this.lR * 2 + 1].lastModified();
      if (l1 > l) {
        fn = fJ.b(this.mr)[this.lR * 2 + 1].L();
        l = l1;
        str = fJ.b(this.mr)[this.lR * 2 + 1].getName();
      } 
    } 
    if (fn != null) {
      stringBuilder.append(fn.toString());
      if (str != null) {
        stringBuilder.append(" - " + str);
      } else {
        stringBuilder.append(" - " + Application.b(l));
      } 
    } else {
      stringBuilder.append("[EMPTY]");
    } 
    return stringBuilder.toString();
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\fN.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */