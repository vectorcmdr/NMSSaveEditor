package nomanssave;

class eZ extends fc {
  final int index;
  
  eZ(eY parameY, int paramInt, fc paramfc) {
    super(parameY, paramfc);
    this.index = paramInt;
  }
  
  Object a(Class<Object> paramClass, boolean paramBoolean) {
    if (this.kL == null)
      throw new RuntimeException("Unexpected path"); 
    eV eV = (eV)this.kL.a(eV.class, paramBoolean);
    if (this.index < 0 || this.index > eV.length)
      throw new RuntimeException("Array index out of bounds"); 
    if (this.index == eV.length) {
      Object object;
      if (!paramBoolean)
        throw new fd(null); 
      try {
        object = paramClass.newInstance();
      } catch (InstantiationException|IllegalAccessException instantiationException) {
        throw new RuntimeException("Unexpected error", instantiationException);
      } 
      eV.add(object);
      return object;
    } 
    if (paramClass.isInstance(eV.values[this.index]))
      return paramClass.cast(eV.values[this.index]); 
    throw new RuntimeException("Unexpected path");
  }
  
  Object getValue() {
    if (this.kL == null)
      throw new RuntimeException("Unexpected path"); 
    eV eV = (eV)this.kL.a(eV.class, false);
    return eV.get(this.index);
  }
  
  Object a(Object paramObject, boolean paramBoolean) {
    if (this.kL == null)
      throw new RuntimeException("Unexpected path"); 
    eV eV = (eV)this.kL.a(eV.class, paramBoolean);
    if (this.index == eV.length) {
      eV.add(paramObject);
      return null;
    } 
    return eV.set(this.index, paramObject);
  }
  
  Object bG() {
    if (this.kL == null)
      throw new RuntimeException("Unexpected path"); 
    eV eV = (eV)this.kL.a(eV.class, false);
    return eV.remove(this.index);
  }
  
  eY e(eY parameY) {
    if (this.kL == null)
      throw new RuntimeException("Unexpected path"); 
    eV eV = (eV)this.kL.a(eV.class, false);
    Object object = eV.get(this.index);
    if (object == null) {
      eV.set(this.index, parameY);
      return null;
    } 
    if (object instanceof eY) {
      ((eY)object).c(parameY);
      return (eY)object;
    } 
    throw new RuntimeException("Unsupported type: " + object.getClass().getSimpleName());
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\eZ.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */