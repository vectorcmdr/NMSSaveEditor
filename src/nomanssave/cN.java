package nomanssave;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

public class cN extends JComboBox {
  private final boolean gm;
  
  private final Enum[] gn;
  
  private final List go;
  
  private cR gp;
  
  private Object gq;
  
  private static final Color gr = Color.RED;
  
  private static final Color gs = new Color(255, 100, 100);
  
  public cN(Class<?> paramClass) {
    this.gm = gD.class.isAssignableFrom(paramClass);
    this.gn = (Enum[])paramClass.getEnumConstants();
    this.go = new ArrayList();
    setModel(new cO(this, paramClass));
    setRenderer(new cP(this));
  }
  
  public void m(String paramString) {
    Enum enum_ = null;
    if (paramString != null) {
      Enum[] arrayOfEnum;
      int i = (arrayOfEnum = this.gn).length;
      for (byte b = 0; b < i; b++) {
        Enum enum_1 = arrayOfEnum[b];
        if (this.gm) {
          if (((gD)enum_1).K().equals(paramString)) {
            enum_ = enum_1;
            break;
          } 
        } else if (enum_1.name().equals(paramString)) {
          enum_ = enum_1;
          break;
        } 
      } 
      if (enum_ == null) {
        int j = this.go.indexOf(new cQ(this, paramString));
        if (j >= 0) {
          enum_ = this.go.get(j);
        } else {
          enum_ = this.gm ? (Enum)new cS(this, paramString) : (Enum)paramString;
          this.go.add(enum_);
        } 
      } 
    } 
    this.gq = enum_;
    selectedItemChanged();
    updateUI();
  }
  
  public void a(cR paramcR) {
    this.gp = paramcR;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\cN.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */