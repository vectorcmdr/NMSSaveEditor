package nomanssave;

class cJ {
  final cJ gi;
  
  final int gj;
  
  final String name;
  
  Object value;
  
  cJ(cy paramcy, cJ paramcJ, int paramInt, String paramString, Object paramObject) {
    this.gi = paramcJ;
    this.value = paramObject;
    this.name = paramString;
    this.gj = paramInt;
  }
  
  boolean isLeaf() {
    return (this.value == null) ? true : ((this.value instanceof eY) ? false : (!(this.value instanceof eV)));
  }
  
  int getChildCount() {
    return (this.value == null) ? 0 : ((this.value instanceof eY) ? ((eY)this.value).names().size() : ((this.value instanceof eV) ? ((eV)this.value).size() : 0));
  }
  
  Object x(int paramInt) {
    if (this.value == null)
      throw new RuntimeException("No children for null"); 
    if (this.value instanceof eY) {
      String str = ((eY)this.value).names().get(paramInt);
      Object object = ((eY)this.value).getValue(str);
      return new cJ(this.gg, this, paramInt, str, object);
    } 
    if (this.value instanceof eV) {
      Object object = ((eV)this.value).getValue(paramInt);
      return new cJ(this.gg, this, paramInt, "[" + paramInt + "]", object);
    } 
    throw new RuntimeException("No children for " + this.value.getClass().getName());
  }
  
  int indexOf(Object paramObject) {
    return (paramObject instanceof cJ && ((cJ)paramObject).gi == this) ? ((cJ)paramObject).gj : -1;
  }
  
  public String toString() {
    return this.name;
  }
  
  public String getText() {
    return fh.a(this.value, 1, paramCharacter -> (paramCharacter.charValue() < ''));
  }
  
  public void setText(String paramString) {
    if (this.gi == null) {
      this.value = eY.E(paramString);
      cy.b(this.gg).d((eY)this.value);
    } else {
      this.value = fh.P(paramString);
      if (this.gi.value instanceof eY) {
        ((eY)this.gi.value).b(this.name, this.value);
      } else if (this.gi.value instanceof eV) {
        ((eV)this.gi.value).a(this.gj, this.value);
      } 
    } 
    cy.a(this.gg, false);
    cy.b(this.gg, true);
  }
  
  public void remove() {
    if (this.gi == null)
      throw new RuntimeException("Cannot remove root node"); 
    this.value = null;
    if (this.gi.value instanceof eY) {
      ((eY)this.gi.value).N(this.name);
    } else if (this.gi.value instanceof eV) {
      ((eV)this.gi.value).ac(this.gj);
    } 
    cy.a(this.gg, false);
    cy.b(this.gg, true);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\cJ.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */