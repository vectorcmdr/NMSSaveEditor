package nomanssave;

import java.util.Arrays;
import java.util.stream.Stream;

public class eV {
  private static final int kz = 10;
  
  private static final int kA = 10;
  
  int length = 0;
  
  Object[] values = new Object[10];
  
  Object kB;
  
  public static eV D(String paramString) {
    return fh.R(paramString);
  }
  
  public eV() {}
  
  public eV(Object... paramVarArgs) {
    for (byte b = 0; b < this.length; b++) {
      if (paramVarArgs[b] != null && !fh.a(paramVarArgs[b].getClass()))
        throw new RuntimeException("Unsupported type: " + paramVarArgs[b].getClass().getSimpleName()); 
      this.values[b] = paramVarArgs[b];
      fh.a(paramVarArgs[b], this);
    } 
  }
  
  void e(Object paramObject) {
    if (this.values.length == this.length) {
      Object[] arrayOfObject = new Object[this.length + 10];
      System.arraycopy(this.values, 0, arrayOfObject, 0, this.length);
      this.values = arrayOfObject;
    } 
    this.values[this.length] = paramObject;
    fh.a(paramObject, this);
    this.length++;
  }
  
  Object U(int paramInt) {
    return this.values[paramInt];
  }
  
  public String bz() {
    return fh.a(this, System.lineSeparator(), true);
  }
  
  public String toString() {
    return fh.a(this, (String)null, false);
  }
  
  public eV bA() {
    eV eV1 = new eV();
    eV1.values = new Object[this.values.length];
    for (byte b = 0; b < this.length; b++) {
      if (this.values[b] instanceof eY) {
        eV1.values[b] = ((eY)this.values[b]).bE();
        fh.a(eV1.values[b], eV1);
      } else if (this.values[b] instanceof eV) {
        eV1.values[b] = ((eV)this.values[b]).bA();
        fh.a(eV1.values[b], eV1);
      } else {
        eV1.values[b] = this.values[b];
      } 
    } 
    eV1.length = this.length;
    return eV1;
  }
  
  public int size() {
    return this.length;
  }
  
  public int indexOf(Object paramObject) {
    if (paramObject == null)
      throw new NullPointerException(); 
    for (byte b = 0; b < this.length; b++) {
      if (paramObject.equals(this.values[b]))
        return b; 
    } 
    return -1;
  }
  
  public Object get(int paramInt) {
    if (paramInt < 0 || paramInt >= this.length)
      throw new IndexOutOfBoundsException(); 
    return this.values[paramInt];
  }
  
  public Object set(int paramInt, Object paramObject) {
    if (paramInt < 0 || paramInt >= this.length)
      throw new IndexOutOfBoundsException(); 
    if (paramObject != null && !fh.a(paramObject.getClass()))
      throw new RuntimeException("Unsupported type: " + paramObject.getClass().getSimpleName()); 
    Object object = this.values[paramInt];
    fh.i(object);
    this.values[paramInt] = paramObject;
    fh.a(paramObject, this);
    firePropertyChange("[" + paramInt + "]", object, paramObject);
    return object;
  }
  
  public void add(Object paramObject) {
    if (paramObject != null && !fh.a(paramObject.getClass()))
      throw new RuntimeException("Unsupported type: " + paramObject.getClass().getSimpleName()); 
    eV eV1 = new eV();
    if (this.values.length == this.length) {
      eV1.values = this.values;
      eV1.length = this.length;
      Object[] arrayOfObject = new Object[this.length + 10];
      System.arraycopy(this.values, 0, arrayOfObject, 0, this.length);
      this.values = arrayOfObject;
    } else {
      eV1.values = new Object[this.values.length];
      System.arraycopy(this.values, 0, eV1.values, 0, this.length);
      eV1.length = this.length;
    } 
    this.values[this.length] = paramObject;
    fh.a(paramObject, this);
    this.length++;
    firePropertyChange("", eV1, this);
  }
  
  public void add(int paramInt, Object paramObject) {
    if (paramInt < 0 || paramInt > this.length)
      throw new IndexOutOfBoundsException(); 
    if (paramObject != null && !fh.a(paramObject.getClass()))
      throw new RuntimeException("Unsupported type: " + paramObject.getClass().getSimpleName()); 
    eV eV1 = new eV();
    if (this.values.length == this.length) {
      eV1.values = this.values;
      eV1.length = this.length;
      Object[] arrayOfObject = new Object[this.length + 10];
      System.arraycopy(this.values, 0, arrayOfObject, 0, this.length);
      this.values = arrayOfObject;
    } else {
      eV1.values = new Object[this.values.length];
      System.arraycopy(this.values, 0, eV1.values, 0, this.length);
      eV1.length = this.length;
    } 
    for (int i = this.length; i > paramInt; i++)
      this.values[i] = this.values[i - 1]; 
    this.values[paramInt] = paramObject;
    fh.a(paramObject, this);
    this.length++;
    firePropertyChange("", eV1, this);
  }
  
  public Object remove(int paramInt) {
    if (paramInt < 0 || paramInt >= this.length)
      throw new IndexOutOfBoundsException(); 
    eV eV1 = new eV();
    eV1.values = new Object[this.values.length];
    System.arraycopy(this.values, 0, eV1.values, 0, this.length);
    eV1.length = this.length;
    Object object = this.values[paramInt];
    fh.i(object);
    for (int i = paramInt; i < this.length - 1; i++)
      this.values[i] = this.values[i + 1]; 
    this.length--;
    firePropertyChange("", eV1, this);
    return object;
  }
  
  public void clear() {
    eV eV1 = new eV();
    eV1.values = new Object[this.values.length];
    System.arraycopy(this.values, 0, eV1.values, 0, this.length);
    eV1.length = this.length;
    for (byte b = 0; b < this.length; b++)
      fh.i(this.values[b]); 
    this.length = 0;
    firePropertyChange("", eV1, this);
  }
  
  void a(Object paramObject1, String paramString, Object paramObject2, Object paramObject3) {
    for (byte b = 0; b < this.length; b++) {
      if (paramObject1 == this.values[b]) {
        firePropertyChange("[" + b + "]" + paramString, paramObject2, paramObject3);
        return;
      } 
    } 
  }
  
  private void firePropertyChange(String paramString, Object paramObject1, Object paramObject2) {
    if (this.kB instanceof eY)
      ((eY)this.kB).a(this, paramString, paramObject1, paramObject2); 
    if (this.kB instanceof eV)
      ((eV)this.kB).a(this, paramString, paramObject1, paramObject2); 
  }
  
  public Object getValue(int paramInt) {
    return get(paramInt);
  }
  
  public eY V(int paramInt) {
    return (eY)getValue(paramInt);
  }
  
  public eV W(int paramInt) {
    return (eV)getValue(paramInt);
  }
  
  public String X(int paramInt) {
    Object object = getValue(paramInt);
    return (object instanceof fg) ? object.toString() : (String)object;
  }
  
  public int Y(int paramInt) {
    Object object = getValue(paramInt);
    return (object == null) ? 0 : ((Number)object).intValue();
  }
  
  public long Z(int paramInt) {
    Object object = getValue(paramInt);
    return (object == null) ? 0L : ((Number)object).longValue();
  }
  
  public double aa(int paramInt) {
    Object object = getValue(paramInt);
    return (object == null) ? 0.0D : ((Number)object).doubleValue();
  }
  
  public boolean ab(int paramInt) {
    Object object = getValue(paramInt);
    return (object == null) ? false : ((Boolean)object).booleanValue();
  }
  
  public void a(int paramInt, Object paramObject) {
    set(paramInt, paramObject);
  }
  
  public void f(Object paramObject) {
    add(paramObject);
  }
  
  public boolean hasValue(Object paramObject) {
    return (indexOf(paramObject) >= 0);
  }
  
  public boolean ac(int paramInt) {
    remove(paramInt);
    return true;
  }
  
  public boolean g(Object paramObject) {
    int i = indexOf(paramObject);
    if (i < 0)
      return false; 
    remove(i);
    return true;
  }
  
  public Stream bB() {
    eY[] arrayOfEY = new eY[this.length];
    byte b1 = 0;
    for (byte b2 = 0; b2 < this.length; b2++) {
      if (this.values[b2] instanceof eY)
        arrayOfEY[b1++] = (eY)this.values[b2]; 
    } 
    return Arrays.stream(arrayOfEY, 0, b1);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\eV.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */