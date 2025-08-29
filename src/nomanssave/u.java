package nomanssave;

class u implements fR {
  u(Application paramApplication) {}
  
  public void a(fq paramfq) {
    if (!Application.a(this.aZ) || Application.b(this.aZ) != paramfq)
      return; 
    Application.a(this.aZ, true);
  }
  
  public void a(fq paramfq, int paramInt, String paramString) {
    if (!Application.a(this.aZ) || Application.b(this.aZ) != paramfq)
      return; 
    Application.b(this.aZ, true);
    if (Application.c(this.aZ) >= 0 && Application.d(this.aZ)[Application.c(this.aZ)].getIndex() == paramInt) {
      Application.c(this.aZ, true);
      if (Application.e(this.aZ) >= 0 && Application.f(this.aZ)[Application.e(this.aZ)].K().equals(paramString))
        Application.d(this.aZ, true); 
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssav\\u.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */