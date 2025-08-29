package nomanssave;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class eI {
  public static final int kd = 0;
  
  public static final int ke = 1;
  
  public static final int kf = 2;
  
  final int type;
  
  final String id;
  
  final String name;
  
  private static final List kg = new ArrayList();
  
  private static final List kh = new ArrayList();
  
  private static final List ki = new ArrayList();
  
  static {
    InputStream inputStream = Application.class.getResourceAsStream("db/rewards.xml");
    if (inputStream != null)
      try {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
        Element element = document.getDocumentElement();
        NodeList nodeList = element.getChildNodes();
        for (byte b = 0; b < nodeList.getLength(); b++) {
          Node node = nodeList.item(b);
          if (node instanceof Element && node.getNodeName().equals("season"))
            kg.add(new eI((Element)node, 0)); 
          if (node instanceof Element && node.getNodeName().equals("twitch"))
            kh.add(new eI((Element)node, 1)); 
          if (node instanceof Element && node.getNodeName().equals("platform"))
            ki.add(new eI((Element)node, 2)); 
        } 
      } catch (ParserConfigurationException parserConfigurationException) {
      
      } catch (SAXException sAXException) {
      
      } catch (IOException iOException) {} 
    kg.sort(new eJ());
    kh.sort(new eK());
    ki.sort(new eL());
  }
  
  private eI(Element paramElement, int paramInt) {
    this.type = paramInt;
    this.id = paramElement.getAttribute("id");
    this.name = paramElement.getAttribute("name");
  }
  
  public String getID() {
    return this.id;
  }
  
  public String getName() {
    return this.name;
  }
  
  public static int bq() {
    return kg.size();
  }
  
  public static eI P(int paramInt) {
    return kg.get(paramInt);
  }
  
  public static int br() {
    return kh.size();
  }
  
  public static eI Q(int paramInt) {
    return kh.get(paramInt);
  }
  
  public static int bs() {
    return ki.size();
  }
  
  public static eI R(int paramInt) {
    return ki.get(paramInt);
  }
  
  public static Iterable bt() {
    return Collections.unmodifiableList(kg);
  }
  
  public static Iterable bu() {
    return Collections.unmodifiableList(kh);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\eI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */