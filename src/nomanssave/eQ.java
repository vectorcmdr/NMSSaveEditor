package nomanssave;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class eQ extends ey {
  private static final String gc = "0123456789ABCDEFabcdef";
  
  final boolean jW;
  
  final String name;
  
  final ex jX;
  
  final boolean special;
  
  final Integer jY;
  
  final String jK;
  
  final String ka;
  
  final int kb;
  
  final String description;
  
  final List kc;
  
  final eR kk;
  
  eQ(Element paramElement, boolean paramBoolean) {
    super(paramElement.getAttribute("id"));
    this.jW = paramBoolean;
    this.name = paramElement.getAttribute("name");
    if (paramBoolean) {
      this.jX = ex.valueOf("PROC_" + paramElement.getAttribute("category"));
    } else {
      this.jX = ex.valueOf(paramElement.getAttribute("category"));
    } 
    this.special = paramElement.hasAttribute("special") ? Boolean.valueOf(paramElement.getAttribute("special")).booleanValue() : false;
    this.jY = paramElement.hasAttribute("chargeable") ? new Integer(paramElement.getAttribute("chargeable")) : null;
    this.jK = paramElement.getAttribute("subtitle");
    this.ka = paramElement.hasAttribute("icon") ? paramElement.getAttribute("icon") : null;
    if (paramElement.hasAttribute("multiplier")) {
      this.kb = Integer.parseInt(paramElement.getAttribute("multiplier"));
    } else {
      this.kb = 0;
    } 
    String str = null;
    ArrayList<ez> arrayList = new ArrayList();
    eR eR1 = null;
    NodeList nodeList = paramElement.getChildNodes();
    for (byte b = 0; b < nodeList.getLength(); b++) {
      Node node = nodeList.item(b);
      if (node instanceof Element) {
        Element element = (Element)node;
        if (element.getNodeName().equals("description")) {
          str = a(element);
        } else if (element.getNodeName().equals("requirement")) {
          arrayList.add(new ez(this, element));
        } else if (element.getNodeName().equals("techbox")) {
          eR1 = new eR(this, element);
        } 
      } 
    } 
    this.description = str;
    this.kc = Collections.unmodifiableList(arrayList);
    this.kk = eR1;
  }
  
  public eB ba() {
    return eB.jL;
  }
  
  public boolean bb() {
    return this.jW;
  }
  
  public String getName() {
    return this.name;
  }
  
  public ex bc() {
    return this.jX;
  }
  
  public boolean bd() {
    return (!this.jW && this.jX != ex.ja && this.jX != ex.iZ);
  }
  
  public boolean be() {
    return (!this.jW && this.special);
  }
  
  public Integer bf() {
    return this.jY;
  }
  
  public String bg() {
    return this.jK;
  }
  
  public boolean bh() {
    return false;
  }
  
  public String bi() {
    return this.ka;
  }
  
  public int bj() {
    return this.kb;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public List bk() {
    return this.kc;
  }
  
  public eR bv() {
    return this.kk;
  }
  
  public String toString() {
    return (this.name.length() == 0) ? this.id : this.name;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\eQ.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */