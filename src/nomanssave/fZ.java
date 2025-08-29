package nomanssave;

import java.util.ArrayList;

class fZ implements ft {
  final int lR;
  
  fZ(fT paramfT, int paramInt) {
    this.lR = paramInt;
  }
  
  public int getIndex() {
    return this.lR;
  }
  
  public boolean isEmpty() {
    return (fT.b(this.mL)[this.lR * 2] == null && fT.b(this.mL)[this.lR * 2 + 1] == null);
  }
  
  public fs[] bX() {
    hc.info("Loading saves for Slot " + (this.lR + 1) + "...");
    ArrayList<fY> arrayList = new ArrayList();
    if (fT.b(this.mL)[this.lR * 2] != null)
      arrayList.add(fT.b(this.mL)[this.lR * 2]); 
    if (fT.b(this.mL)[this.lR * 2 + 1] != null)
      arrayList.add(fT.b(this.mL)[this.lR * 2 + 1]); 
    aH.cG.listFiles(new ga(this, arrayList));
    arrayList.sort(new gb(this));
    return arrayList.<fs>toArray(new fs[0]);
  }
  
  public fn L() {
    long l = Long.MIN_VALUE;
    fn fn = null;
    if (fT.b(this.mL)[this.lR * 2] != null) {
      fn = fT.b(this.mL)[this.lR * 2].L();
      l = fT.b(this.mL)[this.lR * 2].lastModified();
    } 
    if (fT.b(this.mL)[this.lR * 2 + 1] != null) {
      long l1 = fT.b(this.mL)[this.lR * 2 + 1].lastModified();
      if (l1 > l) {
        fn = fT.b(this.mL)[this.lR * 2 + 1].L();
        l = l1;
      } 
    } 
    return fn;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Slot " + (this.lR + 1) + " - ");
    long l = Long.MIN_VALUE;
    fn fn = null;
    if (fT.b(this.mL)[this.lR * 2] != null) {
      fn = fT.b(this.mL)[this.lR * 2].L();
      l = fT.b(this.mL)[this.lR * 2].lastModified();
    } 
    if (fT.b(this.mL)[this.lR * 2 + 1] != null) {
      long l1 = fT.b(this.mL)[this.lR * 2 + 1].lastModified();
      if (l1 > l) {
        fn = fT.b(this.mL)[this.lR * 2 + 1].L();
        l = l1;
      } 
    } 
    if (fn != null) {
      stringBuilder.append(fn.toString());
      stringBuilder.append(" - " + Application.b(l));
    } else {
      stringBuilder.append("[EMPTY]");
    } 
    return stringBuilder.toString();
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\fZ.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */