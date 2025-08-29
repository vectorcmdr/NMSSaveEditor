package nomanssave;

class bJ extends G {
  final gs ez;
  
  final int type;
  
  bJ(bE parambE, gs paramgs, boolean paramBoolean) {
    this.ez = paramgs;
    switch (ad()[paramgs.ordinal()]) {
      case 24:
        this.type = 1;
        break;
      default:
        this.type = 0;
        break;
    } 
    setEnabled(paramBoolean);
  }
  
  protected String g(String paramString) {
    String str;
    if (bE.a(this.ey) == null)
      return ""; 
    switch (this.type) {
      case 0:
        str = Integer.toString(bE.a(this.ey).a(this.ez));
        break;
      case 1:
        str = Double.toString(bE.a(this.ey).b(this.ez));
        break;
      default:
        return "";
    } 
    if (paramString.equals(str))
      return paramString; 
    try {
      int i;
      double d;
      switch (this.type) {
        case 0:
          i = hf.b(paramString, 0, 2147483647);
          bE.a(this.ey).a(this.ez, i);
          paramString = Integer.toString(i);
          break;
        case 1:
          d = Double.parseDouble(paramString);
          bE.a(this.ey).a(this.ez, d);
          paramString = Double.toString(d);
          break;
      } 
      bE.a(this.ey, this.ez, paramString);
      if (this.ez == gs.pJ || this.ez == gs.pK) {
        i = bE.a(this.ey).a(gs.pJ) + bE.a(this.ey).a(gs.pK);
        bE.a(this.ey).a(gs.pP, i);
        bE.a(this.ey, gs.pP, Integer.toString(i));
      } 
      if (this.ez == gs.pG || this.ez == gs.pI || this.ez == gs.pH) {
        i = bE.a(this.ey).a(gs.pG) + bE.a(this.ey).a(gs.pI) + bE.a(this.ey).a(gs.pH);
        bE.a(this.ey).a(gs.pQ, i);
        bE.a(this.ey, gs.pQ, Integer.toString(i));
      } 
      return paramString;
    } catch (RuntimeException runtimeException) {
      return str;
    } 
  }
  
  void ac() {
    String str;
    if (bE.a(this.ey) == null) {
      str = "";
    } else {
      switch (this.type) {
        case 0:
          str = Integer.toString(bE.a(this.ey).a(this.ez));
          break;
        case 1:
          str = Double.toString(bE.a(this.ey).b(this.ez));
          break;
        default:
          str = "";
          break;
      } 
    } 
    setText(str);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\bJ.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */