package nomanssave;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
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
   private final HashMap kp;
   private static final List kq = new ArrayList();

   static {
      InputStream var0 = Application.class.getResourceAsStream("db/words.xml");
      if (var0 != null) {
         try {
            Document var1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(var0);
            Element var2 = var1.getDocumentElement();
            NodeList var3 = var2.getChildNodes();

            for(int var4 = 0; var4 < var3.getLength(); ++var4) {
               Node var5 = var3.item(var4);
               if (var5 instanceof Element && var5.getNodeName().equals("word")) {
                  kq.add(new eS((Element)var5));
               }
            }
         } catch (ParserConfigurationException var6) {
         } catch (SAXException var7) {
         } catch (IOException var8) {
         }
      }

      kq.sort(new eT());
   }

   private eS(Element var1) {
      this.id = var1.getAttribute("id");
      this.text = var1.getAttribute("text");
      this.kp = new HashMap();
      NodeList var2 = var1.getElementsByTagName("group");

      for(int var3 = 0; var3 < var2.getLength(); ++var3) {
         Element var4 = (Element)var2.item(var3);
         String var5 = var4.getAttribute("group");
         eU var6 = eU.C(var4.getAttribute("race"));
         if (var6 != null) {
            this.kp.put(var5, var6);
         }
      }

   }

   public String getID() {
      return this.id;
   }

   public String getText() {
      return this.text;
   }

   public Iterable bw() {
      return Collections.unmodifiableSet(this.kp.keySet());
   }

   public eU z(String var1) {
      return (eU)this.kp.get(var1);
   }

   public boolean a(eU var1) {
      return this.kp.containsValue(var1);
   }

   public static eS A(String var0) {
      Iterator var2 = kq.iterator();

      while(var2.hasNext()) {
         eS var1 = (eS)var2.next();
         if (var1.id.equals(var0)) {
            return var1;
         }
      }

      return null;
   }

   public static eS B(String var0) {
      Iterator var2 = kq.iterator();

      while(var2.hasNext()) {
         eS var1 = (eS)var2.next();
         if (var1.kp.containsKey(var0)) {
            return var1;
         }
      }

      return null;
   }

   public static int bx() {
      return kq.size();
   }

   public static eS T(int var0) {
      return (eS)kq.get(var0);
   }

   public static Iterable by() {
      return Collections.unmodifiableList(kq);
   }
}
