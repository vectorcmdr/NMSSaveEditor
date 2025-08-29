package nomanssave;

class bN extends ba {
  bN(bE parambE) {
    super(new int[] { aH.cJ, 0 });
  }
  
  void a(String paramString, gs paramgs) {
    a(paramString, paramgs, true, (String)null);
  }
  
  void a(String paramString1, gs paramgs, boolean paramBoolean, String paramString2) {
    bJ bJ = new bJ(this.ey, paramgs, paramBoolean);
    if (paramString2 != null)
      bJ.setToolTipText(paramString2); 
    a(paramString1, bJ);
  }
  
  void a(String paramString, bK parambK) {
    a(paramString, parambK, true, (String)null);
  }
  
  void a(String paramString1, bK parambK, boolean paramBoolean, String paramString2) {
    bL bL = new bL(this.ey, parambK, paramBoolean);
    if (paramString2 != null)
      bL.setToolTipText(paramString2); 
    a(paramString1, bL);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\bN.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */