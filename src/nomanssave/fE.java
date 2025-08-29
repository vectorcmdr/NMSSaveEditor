package nomanssave;

import java.util.ArrayList;

class fE implements ft {
  final int lR;
  
  fE(fA paramfA, int paramInt) {
    this.lR = paramInt;
  }
  
  public int getIndex() {
    return this.lR;
  }
  
  public boolean isEmpty() {
    return (fA.b(this.lY)[this.lR * 2] == null && fA.b(this.lY)[this.lR * 2 + 1] == null);
  }
  
  public fs[] bX() {
    hc.info("Loading saves for Slot " + (this.lR + 1) + "...");
    ArrayList<fD> arrayList = new ArrayList();
    if (fA.b(this.lY)[this.lR * 2] != null)
      arrayList.add(fA.b(this.lY)[this.lR * 2]); 
    if (fA.b(this.lY)[this.lR * 2 + 1] != null)
      arrayList.add(fA.b(this.lY)[this.lR * 2 + 1]); 
    aH.cG.listFiles(new fF(this, arrayList));
    arrayList.sort(new fG(this));
    return arrayList.<fs>toArray(new fs[0]);
  }
  
  public fn L() {
    long l = Long.MIN_VALUE;
    fn fn = null;
    if (fA.b(this.lY)[this.lR * 2] != null) {
      fn = fA.b(this.lY)[this.lR * 2].L();
      l = fA.b(this.lY)[this.lR * 2].lastModified();
    } 
    if (fA.b(this.lY)[this.lR * 2 + 1] != null) {
      long l1 = fA.b(this.lY)[this.lR * 2 + 1].lastModified();
      if (l1 > l) {
        fn = fA.b(this.lY)[this.lR * 2 + 1].L();
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
    if (fA.b(this.lY)[this.lR * 2] != null) {
      fn = fA.b(this.lY)[this.lR * 2].L();
      l = fA.b(this.lY)[this.lR * 2].lastModified();
    } 
    if (fA.b(this.lY)[this.lR * 2 + 1] != null) {
      long l1 = fA.b(this.lY)[this.lR * 2 + 1].lastModified();
      if (l1 > l) {
        fn = fA.b(this.lY)[this.lR * 2 + 1].L();
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


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\fE.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */