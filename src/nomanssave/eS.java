package nomanssave;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class eS {
  final String id;
  
  final String text;
  
  private final HashMap kn;
  
  private static final List ko = new ArrayList();
  
  static {
    InputStream inputStream = Application.class.getResourceAsStream("db/words.xml");
    if (inputStream != null)
      try {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
        Element element = document.getDocumentElement();
        NodeList nodeList = element.getChildNodes();
        for (byte b = 0; b < nodeList.getLength(); b++) {
          Node node = nodeList.item(b);
          if (node instanceof Element && node.getNodeName().equals("word"))
            ko.add(new eS((Element)node)); 
        } 
      } catch (ParserConfigurationException parserConfigurationException) {
      
      } catch (SAXException sAXException) {
      
      } catch (IOException iOException) {} 
    ko.sort(new eT());
  }
  
  private eS(Element paramElement) {
    this.id = paramElement.getAttribute("id");
    this.text = paramElement.getAttribute("text");
    this.kn = new HashMap<>();
    NodeList nodeList = paramElement.getElementsByTagName("group");
    for (byte b = 0; b < nodeList.getLength(); b++) {
      Element element = (Element)nodeList.item(b);
      String str = element.getAttribute("group");
      eU eU = eU.C(element.getAttribute("race"));
      if (eU != null)
        this.kn.put(str, eU); 
    } 
  }
  
  public String getID() {
    return this.id;
  }
  
  public String getText() {
    return this.text;
  }
  
  public Iterable bw() {
    return Collections.unmodifiableSet(this.kn.keySet());
  }
  
  public eU z(String paramString) {
    return (eU)this.kn.get(paramString);
  }
  
  public boolean a(eU parameU) {
    return this.kn.containsValue(parameU);
  }
  
  public static eS A(String paramString) {
    for (eS eS1 : ko) {
      if (eS1.id.equals(paramString))
        return eS1; 
    } 
    return null;
  }
  
  public static eS B(String paramString) {
    for (eS eS1 : ko) {
      if (eS1.kn.containsKey(paramString))
        return eS1; 
    } 
    return null;
  }
  
  public static int bx() {
    return ko.size();
  }
  
  public static eS T(int paramInt) {
    return ko.get(paramInt);
  }
  
  public static Iterable by() {
    return Collections.unmodifiableList(ko);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\eS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */