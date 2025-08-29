package nomanssave;

import java.awt.EventQueue;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class eY {
  private static final int kz = 10;
  
  private static final int kA = 10;
  
  private static final Pattern kF = Pattern.compile("[^\"\\.\\[\\]]+");
  
  int length = 0;
  
  String[] names = new String[10];
  
  Object[] values = new Object[10];
  
  Object kB;
  
  fe kG;
  
  Map kH = new HashMap<>();
  
  private static final Pattern kI = Pattern.compile("([^\\.\\[\\]]+)|(?:\\.([^\\.\\[\\]]+))|(?:\\[(\\d+)\\])");
  
  public static eY E(String paramString) {
    return fh.Q(paramString);
  }
  
  public void b(String paramString, Function paramFunction) {
    this.kH.put(paramString, paramFunction);
  }
  
  void a(String paramString, Object paramObject) {
    for (byte b = 0; b < this.length; b++) {
      if (this.names[b].equals(paramString))
        throw new RuntimeException("duplicate key: " + paramString); 
    } 
    if (this.values.length == this.length) {
      String[] arrayOfString = new String[this.length + 10];
      Object[] arrayOfObject = new Object[this.length + 10];
      System.arraycopy(this.names, 0, arrayOfString, 0, this.length);
      System.arraycopy(this.values, 0, arrayOfObject, 0, this.length);
      this.names = arrayOfString;
      this.values = arrayOfObject;
    } 
    this.names[this.length] = paramString;
    this.values[this.length] = paramObject;
    fh.a(paramObject, this);
    this.length++;
  }
  
  public String bz() {
    return fh.a(this, System.lineSeparator(), true);
  }
  
  public String toString() {
    return fh.a(this, (String)null, false);
  }
  
  public eY bE() {
    eY eY1 = new eY();
    eY1.names = new String[this.values.length];
    eY1.values = new Object[this.values.length];
    System.arraycopy(this.names, 0, eY1.names, 0, this.length);
    for (byte b = 0; b < this.length; b++) {
      if (this.values[b] instanceof eY) {
        eY1.values[b] = ((eY)this.values[b]).bE();
        fh.a(eY1.values[b], eY1);
      } else if (this.values[b] instanceof eV) {
        eY1.values[b] = ((eV)this.values[b]).bA();
        fh.a(eY1.values[b], eY1);
      } else {
        eY1.values[b] = this.values[b];
      } 
    } 
    eY1.length = this.length;
    return eY1;
  }
  
  public int size() {
    return this.length;
  }
  
  public List names() {
    String[] arrayOfString = new String[this.length];
    System.arraycopy(this.names, 0, arrayOfString, 0, this.length);
    return Arrays.asList(arrayOfString);
  }
  
  public boolean contains(String paramString) {
    if (paramString == null)
      throw new NullPointerException(); 
    if (!kF.matcher(paramString).matches())
      throw new RuntimeException("Invalid name: " + paramString); 
    for (byte b = 0; b < this.length; b++) {
      if (paramString.equals(this.names[b]))
        return true; 
    } 
    return false;
  }
  
  public Object get(String paramString) {
    if (paramString == null)
      throw new NullPointerException(); 
    if (!kF.matcher(paramString).matches())
      throw new RuntimeException("Invalid name: " + paramString); 
    for (byte b = 0; b < this.length; b++) {
      if (paramString.equals(this.names[b]))
        return this.values[b]; 
    } 
    return null;
  }
  
  public Object put(String paramString, Object paramObject) {
    if (paramString == null)
      throw new NullPointerException(); 
    if (!kF.matcher(paramString).matches())
      throw new RuntimeException("Invalid name: " + paramString); 
    if (paramObject != null && !fh.a(paramObject.getClass()))
      throw new RuntimeException("Unsupported type: " + paramObject.getClass().getSimpleName()); 
    for (byte b = 0; b < this.length; b++) {
      if (paramString.equals(this.names[b])) {
        Object object = this.values[b];
        fh.i(object);
        this.values[b] = paramObject;
        fh.a(paramObject, this);
        firePropertyChange(paramString, object, paramObject);
        return object;
      } 
    } 
    if (this.values.length == this.length) {
      String[] arrayOfString = new String[this.length + 10];
      Object[] arrayOfObject = new Object[this.length + 10];
      System.arraycopy(this.names, 0, arrayOfString, 0, this.length);
      System.arraycopy(this.values, 0, arrayOfObject, 0, this.length);
      this.names = arrayOfString;
      this.values = arrayOfObject;
    } 
    this.names[this.length] = paramString;
    this.values[this.length] = paramObject;
    fh.a(paramObject, this);
    this.length++;
    firePropertyChange(paramString, null, paramObject);
    return null;
  }
  
  public Object F(String paramString) {
    if (paramString == null)
      throw new NullPointerException(); 
    if (!kF.matcher(paramString).matches())
      throw new RuntimeException("Invalid name: " + paramString); 
    for (byte b = 0; b < this.length; b++) {
      if (paramString.equals(this.names[b])) {
        Object object = this.values[b];
        fh.i(object);
        while (++b < this.length) {
          this.names[b - 1] = this.names[b];
          this.values[b - 1] = this.values[b];
          b++;
        } 
        this.length--;
        firePropertyChange(paramString, object, null);
        return object;
      } 
    } 
    return null;
  }
  
  public void c(eY parameY) {
    if (parameY == null)
      throw new NullPointerException(); 
    for (byte b = 0; b < parameY.length; b++) {
      boolean bool = false;
      for (byte b1 = 0; b1 < this.length; b1++) {
        if (parameY.names[b].equals(this.names[b1])) {
          Object object = this.values[b1];
          fh.i(object);
          if (object instanceof eY && parameY.values[b] instanceof eY) {
            ((eY)object).c((eY)parameY.values[b]);
            this.values[b1] = object;
          } else {
            this.values[b1] = parameY.values[b];
          } 
          fh.a(this.values[b1], this);
          bool = true;
        } 
      } 
      if (!bool) {
        if (this.values.length == this.length) {
          String[] arrayOfString = new String[this.length + 10];
          Object[] arrayOfObject = new Object[this.length + 10];
          System.arraycopy(this.names, 0, arrayOfString, 0, this.length);
          System.arraycopy(this.values, 0, arrayOfObject, 0, this.length);
          this.names = arrayOfString;
          this.values = arrayOfObject;
        } 
        this.names[this.length] = parameY.names[b];
        this.values[this.length] = parameY.values[b];
        fh.a(this.values[this.length], this);
        this.length++;
      } 
    } 
    firePropertyChange("", null, this);
  }
  
  int indexOf(String paramString) {
    for (byte b = 0; b < this.length; b++) {
      if (paramString.equals(this.names[b]))
        return b; 
    } 
    return -1;
  }
  
  Object set(int paramInt, Object paramObject) {
    Object object = this.values[paramInt];
    fh.i(object);
    this.values[paramInt] = paramObject;
    fh.a(paramObject, this);
    firePropertyChange(this.names[paramInt], object, null);
    return object;
  }
  
  Object remove(int paramInt) {
    String str = this.names[paramInt];
    Object object = this.values[paramInt];
    fh.i(object);
    for (int i = paramInt + 1; i < this.length; i++) {
      this.names[i - 1] = this.names[i];
      this.values[i - 1] = this.values[i];
    } 
    this.length--;
    firePropertyChange(str, object, null);
    return object;
  }
  
  public void clear() {
    for (byte b = 0; b < this.length; b++) {
      fh.i(this.values[b]);
      firePropertyChange(this.names[b], this.values[b], null);
    } 
    this.length = 0;
  }
  
  public void a(fe paramfe) {
    this.kG = paramfe;
  }
  
  void a(Object paramObject1, String paramString, Object paramObject2, Object paramObject3) {
    for (byte b = 0; b < this.length; b++) {
      if (paramObject1 == this.values[b]) {
        firePropertyChange(String.valueOf(this.names[b]) + paramString, paramObject2, paramObject3);
        return;
      } 
    } 
  }
  
  private void firePropertyChange(String paramString, Object paramObject1, Object paramObject2) {
    if (this.kG != null)
      EventQueue.invokeLater(() -> this.kG.propertyChanged(paramString, paramObject1, paramObject2)); 
    String str = (paramString.length() == 0) ? "" : ("." + paramString);
    if (this.kB instanceof eY)
      ((eY)this.kB).a(this, str, paramObject1, paramObject2); 
    if (this.kB instanceof eV)
      ((eV)this.kB).a(this, str, paramObject1, paramObject2); 
  }
  
  private fc G(String paramString) {
    for (Map.Entry entry : this.kH.entrySet()) {
      if (paramString.equals(entry.getKey())) {
        paramString = ((Function<eY, String>)entry.getValue()).apply(this);
        break;
      } 
      if (paramString.startsWith(String.valueOf(entry.getKey()) + ".") || paramString.startsWith(String.valueOf(entry.getKey()) + "[")) {
        paramString = String.valueOf(((Function<eY, String>)entry.getValue()).apply(this)) + paramString.substring(((String)entry.getKey()).length());
        break;
      } 
    } 
    Matcher matcher = kI.matcher(paramString);
    if (matcher.find() && matcher.start() == 0) {
      eZ eZ;
      int i = matcher.end();
      if (matcher.group(1) != null) {
        fb fb = new fb(this, matcher.group(1), null);
      } else if (matcher.group(3) != null) {
        eZ = new eZ(this, Integer.parseInt(matcher.group(3)), null);
      } else {
        throw new RuntimeException("Invalid path");
      } 
      while (matcher.find() && matcher.start() == i) {
        fb fb;
        i = matcher.end();
        if (matcher.group(2) != null) {
          fb = new fb(this, matcher.group(2), eZ);
          continue;
        } 
        if (matcher.group(3) != null) {
          eZ = new eZ(this, Integer.parseInt(matcher.group(3)), fb);
          continue;
        } 
        throw new RuntimeException("Invalid path");
      } 
      if (matcher.hitEnd())
        return eZ; 
    } 
    throw new RuntimeException("Invalid path");
  }
  
  public Object getValue(String paramString) {
    try {
      return G(paramString).getValue();
    } catch (fd fd) {
      hc.debug("Path not found: " + paramString);
      return null;
    } catch (RuntimeException runtimeException) {
      hc.warn("Error getting value: " + paramString + ", " + runtimeException.getMessage());
      return null;
    } 
  }
  
  public eY H(String paramString) {
    return (eY)getValue(paramString);
  }
  
  public eV d(String paramString) {
    return (eV)getValue(paramString);
  }
  
  public String getValueAsString(String paramString) {
    Object object = getValue(paramString);
    return (object instanceof fg) ? object.toString() : (String)object;
  }
  
  public String I(String paramString) {
    Object object = getValue(paramString);
    if (object == null)
      return ""; 
    if (object instanceof Number) {
      String str;
      for (str = Long.toHexString(((Number)object).longValue()); str.length() < 16; str = "0" + str);
      return "0x" + str.toUpperCase();
    } 
    return (String)getValue(paramString);
  }
  
  public int J(String paramString) {
    Object object = getValue(paramString);
    return (object == null) ? 0 : ((Number)object).intValue();
  }
  
  public int c(String paramString, int paramInt) {
    Object object = getValue(paramString);
    return (object == null) ? paramInt : ((Number)object).intValue();
  }
  
  public long K(String paramString) {
    Object object = getValue(paramString);
    return (object == null) ? 0L : ((Number)object).longValue();
  }
  
  public long a(String paramString, long paramLong) {
    Object object = getValue(paramString);
    return (object == null) ? paramLong : ((Number)object).longValue();
  }
  
  public double L(String paramString) {
    Object object = getValue(paramString);
    return (object == null) ? 0.0D : ((Number)object).doubleValue();
  }
  
  public double c(String paramString, double paramDouble) {
    Object object = getValue(paramString);
    return (object == null) ? paramDouble : ((Number)object).doubleValue();
  }
  
  public boolean M(String paramString) {
    Object object = getValue(paramString);
    return (object == null) ? false : ((Boolean)object).booleanValue();
  }
  
  public boolean a(String paramString, boolean paramBoolean) {
    Object object = getValue(paramString);
    return (object == null) ? paramBoolean : ((Boolean)object).booleanValue();
  }
  
  public Object b(String paramString, Object paramObject) {
    return G(paramString).a(paramObject, false);
  }
  
  public Object c(String paramString, Object paramObject) {
    return G(paramString).a(paramObject, true);
  }
  
  public Object N(String paramString) {
    try {
      return G(paramString).bG();
    } catch (fd fd) {
      hc.debug("Path not found: " + paramString);
      return null;
    } catch (RuntimeException runtimeException) {
      hc.warn("Error getting value: " + paramString + ", " + runtimeException.getMessage());
      return null;
    } 
  }
  
  public eY b(String paramString, eY parameY) {
    return G(paramString).e(parameY);
  }
  
  public void d(eY parameY) {
    if (parameY == null)
      throw new NullPointerException(); 
    if (parameY.kB != null)
      throw new RuntimeException("Object must not have a parent"); 
    clear();
    this.length = parameY.length;
    this.names = new String[parameY.length];
    this.values = new Object[parameY.length];
    System.arraycopy(parameY.names, 0, this.names, 0, this.length);
    for (byte b = 0; b < this.length; b++) {
      if (parameY.values[b] instanceof eY) {
        this.values[b] = ((eY)parameY.values[b]).bE();
        fh.a(this.values[b], this);
      } else if (parameY.values[b] instanceof eV) {
        this.values[b] = ((eV)parameY.values[b]).bA();
        fh.a(this.values[b], this);
      } else {
        this.values[b] = parameY.values[b];
      } 
      firePropertyChange(this.names[b], null, this.values[b]);
    } 
  }
  
  public void c(File paramFile) {
    Exception exception1 = null;
    Exception exception2 = null;
  }
  
  public void d(File paramFile) {
    Exception exception1 = null;
    Exception exception2 = null;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\eY.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */