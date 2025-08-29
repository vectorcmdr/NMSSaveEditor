package nomanssave;

import java.util.function.Function;

class gI extends gt {
  gI(gH paramgH, Function paramFunction, eY parameY, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt4) {
    super(paramFunction, parameY, paramInt1, paramInt2, paramInt3, paramBoolean1, paramBoolean2);
  }
  
  public int dj() {
    return this.rp ? 3584 : (0xE00 | gH.b(this.ro));
  }
  
  public String toString() {
    return this.ro.dZ() ? ("Ship " + this.il + " - Storage Sacs") : super.toString();
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\gI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */