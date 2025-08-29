package nomanssave;

import java.util.ArrayList;

class eE extends ArrayList {
  private eE() {}
  
  public boolean add(String paramString1, String paramString2) {
    return add((E)new eF(paramString1, paramString2));
  }
  
  public boolean s(String paramString) {
    if (size() == 0)
      return false; 
    eF eF = (eF)get(0);
    return !(!eF.key.equals(paramString) && !eF.name.equals(paramString));
  }
  
  public eF t(String paramString) {
    for (eF eF : this) {
      if (eF.key.equals(paramString))
        return eF; 
    } 
    return null;
  }
  
  public eF u(String paramString) {
    for (eF eF : this) {
      if (eF.name.equals(paramString))
        return eF; 
    } 
    return null;
  }
  
  public eF v(String paramString) {
    for (eF eF : this) {
      if (eF.name.equalsIgnoreCase(paramString))
        return eF; 
    } 
    return null;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\eE.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */