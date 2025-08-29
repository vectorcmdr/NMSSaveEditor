package nomanssave;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class eM {
  final String id;
  
  final String name;
  
  final String description;
  
  final boolean iE;
  
  final boolean jW;
  
  private static final List kj = new ArrayList();
  
  static {
    InputStream inputStream = Application.class.getResourceAsStream("db/settlements.xml");
    if (inputStream != null)
      try {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
        Element element = document.getDocumentElement();
        NodeList nodeList = element.getChildNodes();
        for (byte b = 0; b < nodeList.getLength(); b++) {
          Node node = nodeList.item(b);
          if (node instanceof Element && node.getNodeName().equals("perk"))
            kj.add(new eM((Element)node)); 
        } 
      } catch (ParserConfigurationException parserConfigurationException) {
      
      } catch (SAXException sAXException) {
      
      } catch (IOException iOException) {} 
    kj.sort(new eN());
  }
  
  private eM(Element paramElement) {
    this.id = paramElement.getAttribute("id");
    this.name = paramElement.getAttribute("name");
    this.description = paramElement.getAttribute("description");
    this.iE = Boolean.parseBoolean(paramElement.getAttribute("beneficial"));
    this.jW = Boolean.parseBoolean(paramElement.getAttribute("procedural"));
  }
  
  public String getID() {
    return this.id;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public boolean aW() {
    return this.iE;
  }
  
  public boolean bb() {
    return this.jW;
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject instanceof String) ? (this.jW ? ((String)paramObject).startsWith(String.valueOf(this.id) + "#") : ((String)paramObject).equals(this.id)) : super.equals(paramObject);
  }
  
  public String toString() {
    return this.name;
  }
  
  public static int getCount() {
    return kj.size();
  }
  
  public static eM S(int paramInt) {
    return kj.get(paramInt);
  }
  
  public static int w(String paramString) {
    return kj.indexOf(new eO(paramString));
  }
  
  public static eM x(String paramString) {
    int i = kj.indexOf(new eO(paramString));
    return (i >= 0) ? kj.get(i) : null;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\eM.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */