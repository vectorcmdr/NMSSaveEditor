package nomanssave;

public enum eU {
  kp("Gek"),
  kq("Vy'keen"),
  kr("Korvax"),
  ks("Robot"),
  kt("Atlas"),
  ku("Diplomats"),
  kv("Exotics"),
  kw("None"),
  kx("Autophage");
  
  private String name;
  
  eU(String paramString1) {
    this.name = paramString1;
  }
  
  public String toString() {
    return this.name;
  }
  
  public static eU C(String paramString) {
    eU[] arrayOfEU;
    int i = (arrayOfEU = values()).length;
    for (byte b = 0; b < i; b++) {
      eU eU1 = arrayOfEU[b];
      if (eU1.name().equals(paramString))
        return eU1; 
    } 
    return null;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\eU.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */