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
   public static final int kf = 0;
   public static final int kg = 1;
   public static final int kh = 2;
   final int type;
   final String id;
   final String name;
   private static final List ki = new ArrayList();
   private static final List kj = new ArrayList();
   private static final List kk = new ArrayList();

   static {
      InputStream var0 = Application.class.getResourceAsStream("db/rewards.xml");
      if (var0 != null) {
         try {
            Document var1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(var0);
            Element var2 = var1.getDocumentElement();
            NodeList var3 = var2.getChildNodes();

            for(int var4 = 0; var4 < var3.getLength(); ++var4) {
               Node var5 = var3.item(var4);
               if (var5 instanceof Element && var5.getNodeName().equals("season")) {
                  ki.add(new eI((Element)var5, 0));
               }

               if (var5 instanceof Element && var5.getNodeName().equals("twitch")) {
                  kj.add(new eI((Element)var5, 1));
               }

               if (var5 instanceof Element && var5.getNodeName().equals("platform")) {
                  kk.add(new eI((Element)var5, 2));
               }
            }
         } catch (ParserConfigurationException var6) {
         } catch (SAXException var7) {
         } catch (IOException var8) {
         }
      }

      ki.sort(new eJ());
      kj.sort(new eK());
      kk.sort(new eL());
   }

   private eI(Element var1, int var2) {
      this.type = var2;
      this.id = var1.getAttribute("id");
      this.name = var1.getAttribute("name");
   }

   public String getID() {
      return this.id;
   }

   public String getName() {
      return this.name;
   }

   public static int bq() {
      return ki.size();
   }

   public static eI P(int var0) {
      return (eI)ki.get(var0);
   }

   public static int br() {
      return kj.size();
   }

   public static eI Q(int var0) {
      return (eI)kj.get(var0);
   }

   public static int bs() {
      return kk.size();
   }

   public static eI R(int var0) {
      return (eI)kk.get(var0);
   }

   public static Iterable bt() {
      return Collections.unmodifiableList(ki);
   }

   public static Iterable bu() {
      return Collections.unmodifiableList(kj);
   }
}
