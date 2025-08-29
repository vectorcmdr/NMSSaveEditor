package nomanssave;

import java.util.function.Function;
import java.util.regex.Matcher;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class eA {
  final String id;
  
  final String name;
  
  final String jK;
  
  final String description;
  
  eA(Element paramElement) {
    this.id = paramElement.getAttribute("id");
    this.name = paramElement.getAttribute("name");
    this.jK = paramElement.getAttribute("subtitle");
    String str = null;
    NodeList nodeList = paramElement.getChildNodes();
    for (byte b = 0; b < nodeList.getLength(); b++) {
      Node node = nodeList.item(b);
      if (node instanceof Element) {
        paramElement = (Element)node;
        if (paramElement.getNodeName().equals("description"))
          str = ey.a(paramElement); 
      } 
    } 
    this.description = str;
  }
  
  private String a(String paramString, Function<String, String> paramFunction) {
    StringBuilder stringBuilder = new StringBuilder();
    int i = 0;
    Matcher matcher = ey.bn().matcher(paramString);
    while (matcher.find()) {
      stringBuilder.append(paramString.substring(i, matcher.start()));
      stringBuilder.append(paramFunction.apply(matcher.group(1)));
      i = matcher.end();
    } 
    stringBuilder.append(paramString.substring(i));
    return stringBuilder.toString();
  }
  
  String a(Function paramFunction) {
    return a(this.name, paramFunction);
  }
  
  String b(Function paramFunction) {
    return a(this.jK, paramFunction);
  }
  
  String c(Function paramFunction) {
    return (this.description == null) ? null : a(this.description, paramFunction);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\eA.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */