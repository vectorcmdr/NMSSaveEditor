package nomanssave;

import java.io.ByteArrayOutputStream;
import java.util.List;
import org.w3c.dom.Element;

public class eR extends ey {
  final String ka;
  
  final eA kl;
  
  eR(eQ parameQ, Element paramElement) {
    super(paramElement.getAttribute("id"));
    this.ka = paramElement.hasAttribute("icon") ? paramElement.getAttribute("icon") : null;
    this.kl = ey.p(paramElement.getAttribute("template"));
  }
  
  public Object aZ() {
    return M(this.km.jW ? (int)Math.floor(Math.random() * 100000.0D) : 0);
  }
  
  public Object M(int paramInt) {
    if (this.id.length() != 13 || this.id.charAt(0) != '^')
      throw new RuntimeException("Cannot create ID: invalid string"); 
    if (paramInt < 0 || paramInt >= 100000)
      throw new RuntimeException("Cannot create ID: invalid proc"); 
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    byteArrayOutputStream.write(94);
    int i;
    for (i = 0; i < 6; i++) {
      int j = "0123456789ABCDEFabcdef".indexOf(this.id.charAt(i * 2 + 1));
      int k = "0123456789ABCDEFabcdef".indexOf(this.id.charAt(i * 2 + 2));
      if (j < 0 || k < 0)
        throw new RuntimeException("Cannot create ID: invalid hex"); 
      if (j >= 16)
        j -= 6; 
      if (k >= 16)
        k -= 6; 
      byteArrayOutputStream.write(j << 4 | k);
    } 
    byteArrayOutputStream.write(35);
    for (i = 100000; i > 1; i /= 10) {
      int j = paramInt * 10 / i % 10;
      byteArrayOutputStream.write("0123456789ABCDEFabcdef".charAt(j));
    } 
    return new fg(byteArrayOutputStream.toByteArray());
  }
  
  public eB ba() {
    return eB.jM;
  }
  
  public boolean bb() {
    return this.km.jW;
  }
  
  private String y(String paramString) {
    return "NAME".equals(paramString) ? this.km.name : ("TECH_DESC".equals(paramString) ? this.km.description : paramString);
  }
  
  public String getName() {
    return this.kl.a(this::y);
  }
  
  public ex bc() {
    return ex.jd;
  }
  
  public boolean bd() {
    return false;
  }
  
  public boolean be() {
    return false;
  }
  
  public Integer bf() {
    return null;
  }
  
  public String bg() {
    return this.kl.b(this::y);
  }
  
  public boolean bh() {
    return false;
  }
  
  public String bi() {
    return this.ka;
  }
  
  public int bj() {
    return 0;
  }
  
  public String getDescription() {
    return this.kl.c(this::y);
  }
  
  public List bk() {
    return this.km.kc;
  }
  
  public String toString() {
    return (this.km.name.length() == 0) ? this.id : this.km.name;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\eR.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */