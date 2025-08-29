package nomanssave;

public enum gL implements gD {
  rq("Hauler", "MODELS/COMMON/SPACECRAFT/DROPSHIPS/DROPSHIP_PROC.SCENE.MBIN", 4),
  rr("Explorer", "MODELS/COMMON/SPACECRAFT/SCIENTIFIC/SCIENTIFIC_PROC.SCENE.MBIN", 4),
  rs("Shuttle", "MODELS/COMMON/SPACECRAFT/SHUTTLE/SHUTTLE_PROC.SCENE.MBIN", 4),
  rt("Fighter", "MODELS/COMMON/SPACECRAFT/FIGHTERS/FIGHTER_PROC.SCENE.MBIN", 4),
  ru("Exotic", "MODELS/COMMON/SPACECRAFT/S-CLASS/S-CLASS_PROC.SCENE.MBIN", 4),
  rv("Living", "MODELS/COMMON/SPACECRAFT/S-CLASS/BIOPARTS/BIOSHIP_PROC.SCENE.MBIN", 64),
  rw("Solar", "MODELS/COMMON/SPACECRAFT/SAILSHIP/SAILSHIP_PROC.SCENE.MBIN", 4),
  rx("Utopia Speeder", "MODELS/COMMON/SPACECRAFT/FIGHTERS/VRSPEEDER.SCENE.MBIN", 4),
  ry("Golden Vector", "MODELS/COMMON/SPACECRAFT/FIGHTERS/FIGHTERCLASSICGOLD.SCENE.MBIN", 4),
  rz("Horizon Vector NX (Switch)", "MODELS/COMMON/SPACECRAFT/FIGHTERS/FIGHTERSPECIALSWITCH.SCENE.MBIN", 4),
  rA("Robot", "MODELS/COMMON/SPACECRAFT/SENTINELSHIP/SENTINELSHIP_PROC.SCENE.MBIN", 256),
  rB("Starborn Runner", "MODELS/COMMON/SPACECRAFT/FIGHTERS/WRACER.SCENE.MBIN", 4);
  
  private String name;
  
  private String filename;
  
  private int rC;
  
  gL(String paramString1, String paramString2, int paramInt1) {
    this.name = paramString1;
    this.filename = paramString2;
    this.rC = paramInt1;
  }
  
  public String K() {
    return this.filename;
  }
  
  public int ea() {
    return this.rC;
  }
  
  public String toString() {
    return this.name;
  }
  
  public static gL aw(String paramString) {
    for (byte b = 0; b < (values()).length; b++) {
      if (paramString.equals((values()[b]).filename))
        return values()[b]; 
    } 
    return null;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\gL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */