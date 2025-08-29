package nomanssave;

import java.io.IOException;
import java.util.regex.Matcher;

class fY extends fX implements fs {
  final int lM;
  
  fn mc;
  
  String mY;
  
  fY(fT paramfT, int paramInt) {
    super(paramfT, "Slot" + (paramInt / 2 + 1) + ((paramInt % 2 == 0) ? "Auto" : "Manual"));
    this.lM = paramInt;
    try {
      String str = new String(ah(1048576));
      Matcher matcher = fT.cl().matcher(str);
      if (matcher.find())
        this.mY = matcher.group(3); 
      this.mc = fn.T(str);
    } catch (IOException iOException) {
      hc.a("Could not read game mode from " + this.mM.name, iOException);
    } 
  }
  
  fY(fT paramfT, fV paramfV, eY parameY) {
    super(paramfT, paramfV);
    this.lM = paramfV.lZ;
    this.mX.a(paramfV.mO);
    int i = fT.ao(parameY.J("Version"));
    if (i != 0)
      this.mX.setVersion(i); 
    this.mY = parameY.getValueAsString("CommonStateData.SaveName");
    if (this.mY != null)
      this.mX.Y(this.mY); 
    this.mc = fn.i(parameY);
    long l = parameY.K("PlayerStateData.TotalPlayTime");
    if (l != 0L)
      this.mX.d(l); 
    h(parameY);
  }
  
  public fn L() {
    return this.mc;
  }
  
  public long lastModified() {
    return this.mM.timestamp;
  }
  
  public eY M() {
    return a(eG.jT);
  }
  
  public String b(eY parameY) {
    a((this.lM == 0) ? "wgsbackup" : ("wgsbackup" + (this.lM + 1)), this.mc);
    int i = fT.ao(parameY.J("Version"));
    if (i != 0)
      this.mX.setVersion(i); 
    this.mY = parameY.getValueAsString("CommonStateData.SaveName");
    if (this.mY != null)
      this.mX.Y(this.mY); 
    this.mc = fn.i(parameY);
    long l = parameY.K("PlayerStateData.TotalPlayTime");
    if (l != 0L)
      this.mX.d(l); 
    h(parameY);
    return this.mM.filename;
  }
  
  public String toString() {
    return this.mM.name;
  }
  
  public String getName() {
    return this.mY;
  }
  
  public String getDescription() {
    return this.mX.getDescription();
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\fY.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */