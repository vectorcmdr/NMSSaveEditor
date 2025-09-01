package nomanssave;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class er {
   final String id;
   final String name;
   final gq iB;
   final int iC;
   final gr iD;
   final boolean iE;
   final gr[] iF;
   private static final List iG = new ArrayList();

   static {
      InputStream var0 = Application.class.getResourceAsStream("db/frigates.xml");
      if (var0 != null) {
         try {
            Document var1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(var0);
            Element var2 = var1.getDocumentElement();
            NodeList var3 = var2.getChildNodes();

            for(int var4 = 0; var4 < var3.getLength(); ++var4) {
               Node var5 = var3.item(var4);
               if (var5 instanceof Element && var5.getNodeName().equals("trait")) {
                  iG.add(new er((Element)var5));
               }
            }
         } catch (ParserConfigurationException var6) {
         } catch (SAXException var7) {
         } catch (IOException var8) {
         }
      }

      iG.sort(new es());
   }

   private er(Element var1) {
      this.id = var1.getAttribute("id");
      this.name = var1.getAttribute("name");
      String var2 = var1.getAttribute("type");
      this.iB = var2 == null ? null : gq.valueOf(var2);
      this.iC = Integer.parseInt(var1.getAttribute("strength"));
      var2 = var1.getAttribute("primary");
      this.iD = var2 == null ? null : gr.an(var2);
      this.iE = Boolean.parseBoolean(var1.getAttribute("beneficial"));
      this.iF = n(var1.getAttribute("secondary"));
   }

   private static gr[] n(String var0) {
      ArrayList var1 = new ArrayList();
      int var2 = 0;

      while(var2 < var0.length()) {
         int var4 = var0.indexOf(",", var2);
         gr var3;
         if (var4 >= 0) {
            var3 = gr.an(var0.substring(var2, var4));
            var2 = var4 + 1;
         } else {
            var3 = gr.an(var0.substring(var2));
            var2 = var0.length();
         }

         if (var3 != null) {
            var1.add(var3);
         }
      }

      return (gr[])var1.toArray(new gr[0]);
   }

   public String getID() {
      return this.id;
   }

   public String getName() {
      return this.name;
   }

   public gq aU() {
      return this.iB;
   }

   public int aV() {
      return this.iC * this.iB.di();
   }

   public boolean aW() {
      return this.iE;
   }

   public String toString() {
      String var1 = (this.iC > 0 ? "+" : "") + this.iC + (this.iB == gq.oY ? "%" : "");
      return this.name + " (" + var1 + " " + this.iB + ")";
   }

   public static er[] a(gr var0) {
      return (er[])iG.stream().filter((var1) -> {
         return var1.iD == var0;
      }).toArray((var0x) -> {
         return new er[var0x];
      });
   }

   public static er[] b(gr var0) {
      return (er[])iG.stream().filter((var1) -> {
         return Arrays.stream(var1.iF).anyMatch((var1x) -> {
            return var1x == var0;
         });
      }).toArray((var0x) -> {
         return new er[var0x];
      });
   }

   public static er o(String var0) {
      int var1 = iG.indexOf(new et(var0));
      return var1 >= 0 ? (er)iG.get(var1) : null;
   }
}
