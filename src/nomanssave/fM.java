package nomanssave;

import java.io.File;
import java.io.IOException;

class fM extends fQ implements fs {
  fn mc;
  
  fM(fJ paramfJ, int paramInt) {
    super(paramfJ, (paramInt == 0) ? "save.hg" : ("save" + (paramInt + 1) + ".hg"), paramInt, true);
    try {
      String str = new String(ah(65536));
      this.mc = fn.T(str);
    } catch (IOException iOException) {
      hc.a("Could not read game mode from " + this.filename, iOException);
    } 
  }
  
  fM(fJ paramfJ, int paramInt, eY parameY) {
    super(paramfJ, (paramInt == 0) ? "save.hg" : ("save" + (paramInt + 1) + ".hg"), paramInt, false);
    this.mc = fn.i(parameY);
    a(parameY, true);
  }
  
  public fn L() {
    return this.mc;
  }
  
  public eY M() {
    return a(eG.jT);
  }
  
  void cm() {
    a((this.lM == 0) ? "backup" : ("backup" + (this.lM + 1)), this.mc, getName(), getDescription());
    (new File(fJ.a(this.mr), this.filename)).delete();
    (new File(fJ.a(this.mr), "mf_" + this.filename)).delete();
  }
  
  public String b(eY parameY) {
    a((this.lM == 0) ? "backup" : ("backup" + (this.lM + 1)), this.mc, getName(), getDescription());
    this.mv.Y(parameY.getValueAsString("CommonStateData.SaveName"));
    this.mc = fn.i(parameY);
    this.mv.al((int)parameY.K("CommonStateData.TotalPlayTime"));
    a(parameY, true);
    return this.filename;
  }
  
  public String toString() {
    return this.filename;
  }
  
  public String getName() {
    return this.mv.ck();
  }
  
  public String getDescription() {
    return this.mv.getDescription();
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\fM.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */