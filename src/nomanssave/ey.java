package nomanssave;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public abstract class ey {
   public static final int jD = 0;
   public static final int jE = 1;
   public static final int jF = 2;
   public static final int jG = 3;
   final String id;
   private static final Pattern jH = Pattern.compile("%(\\w+)%");
   private static final List jI;
   private static final List jJ;

   static {
      Element var0 = null;
      InputStream var1 = Application.class.getResourceAsStream("db/items.xml");
      if (var1 != null) {
         try {
            Document var2 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(var1);
            var0 = var2.getDocumentElement();
         } catch (ParserConfigurationException var7) {
         } catch (SAXException var8) {
         } catch (IOException var9) {
         }
      }

      ArrayList var10 = new ArrayList();
      if (var0 != null) {
         NodeList var3 = var0.getChildNodes();

         for(int var4 = 0; var4 < var3.getLength(); ++var4) {
            Node var5 = var3.item(var4);
            if (var5 instanceof Element && var5.getNodeName().equals("product-template")) {
               var10.add(new eA((Element)var5));
            }
         }
      }

      jI = Collections.unmodifiableList(var10);
      ArrayList var11 = new ArrayList();
      if (var0 != null) {
         NodeList var12 = var0.getChildNodes();

         for(int var14 = 0; var14 < var12.getLength(); ++var14) {
            Node var6 = var12.item(var14);
            if (var6 instanceof Element && var6.getNodeName().equals("substance")) {
               var11.add(new eP((Element)var6));
            } else if (var6 instanceof Element && var6.getNodeName().equals("product")) {
               var11.add(new eH((Element)var6, false));
            } else if (var6 instanceof Element && var6.getNodeName().equals("procedural-product")) {
               var11.add(new eH((Element)var6, true));
            } else if (var6 instanceof Element && var6.getNodeName().equals("technology")) {
               var11.add(new eQ((Element)var6, false));
            } else if (var6 instanceof Element && var6.getNodeName().equals("procedural-technology")) {
               var11.add(new eQ((Element)var6, true));
            }
         }
      }

      List var13 = (List)var11.stream().filter((var0x) -> {
         return var0x instanceof eQ;
      }).map(eQ.class::cast).map((var0x) -> {
         return var0x.bv();
      }).filter((var0x) -> {
         return var0x != null;
      }).collect(Collectors.toList());
      var11.addAll(var13);
      var13.sort((var0x, var1x) -> {
         return var0x.getName().compareTo(var1x.getName());
      });
      jJ = Collections.unmodifiableList(var11);
   }

   ey(String var1) {
      this.id = var1;
   }

   public final String getID() {
      return this.id;
   }

   private static String L(int var0) {
      StringBuilder var1 = new StringBuilder();
      var1.append(Integer.toString(var0));

      while(var1.length() < 5) {
         var1.insert(0, '0');
      }

      return var1.toString();
   }

   public Object aZ() {
      if (this.id.length() >= 2 && this.id.charAt(0) == '^') {
         if (this.bb()) {
            int var1 = (int)Math.floor(Math.random() * 100000.0D);
            return this.id + "#" + L(var1);
         } else {
            return this.id;
         }
      } else {
         throw new RuntimeException("Cannot create ID: invalid string");
      }
   }

   public Object M(int var1) {
      if (this.id.length() >= 2 && this.id.charAt(0) == '^') {
         if (this.bb()) {
            if (var1 >= 0 && var1 < 100000) {
               return this.id + "#" + L(var1);
            } else {
               throw new RuntimeException("Cannot create ID: invalid proc");
            }
         } else if (var1 != 0) {
            throw new RuntimeException("Cannot create ID: invalid proc");
         } else {
            return this.id;
         }
      } else {
         throw new RuntimeException("Cannot create ID: invalid string");
      }
   }

   public abstract eB ba();

   public abstract boolean bb();

   public abstract String getName();

   public abstract ex bc();

   public abstract boolean bd();

   public abstract boolean be();

   public abstract Integer bf();

   public abstract String bg();

   public abstract boolean bh();

   public abstract String bi();

   public final ImageIcon N(int var1) {
      String var2 = this.bi();
      switch(var1) {
      case 0:
         return var2 == null ? null : Application.a(var2);
      case 1:
         return var2 == null ? null : Application.a(var2, 40, 40);
      case 2:
         return var2 == null ? null : Application.a(var2, 80, 80);
      case 3:
         return var2 == null ? null : Application.a(var2, 20, 20);
      default:
         return null;
      }
   }

   public final ImageIcon c(int var1, int var2) {
      String var3 = this.bi();
      return var3 == null ? null : Application.a(var3, var1, var2);
   }

   public abstract int bj();

   public abstract String getDescription();

   public abstract List bk();

   public String toString() {
      return this.id;
   }

   static String a(Element var0) {
      if (var0 == null) {
         throw new IllegalArgumentException();
      } else {
         NodeList var1 = var0.getChildNodes();
         StringBuffer var2 = new StringBuffer();
         boolean var3 = false;

         for(int var5 = 0; var5 < var1.getLength(); ++var5) {
            Node var4 = var1.item(var5);
            if (var4.getNodeType() == 3) {
               var2.append(var4.getNodeValue());
               var3 = true;
            }
         }

         if (!var3) {
            return null;
         } else {
            return var2.toString();
         }
      }
   }

   private static List O(int var0) {
      ArrayList var1 = new ArrayList();
      boolean var2 = (var0 & 16384) == 0;
      if ((var0 & 4) == 4) {
         var1.add(ex.je);
         var1.add(ex.js);
         var1.add(ex.jv);
         if (var2) {
            var1.add(ex.jw);
         }
      }

      if ((var0 & 64) == 64) {
         var1.add(ex.jq);
         var1.add(ex.js);
         if (var2) {
            var1.add(ex.jr);
         }
      }

      if ((var0 & 256) == 256) {
         var1.add(ex.ju);
         var1.add(ex.js);
         var1.add(ex.jv);
         if (var2) {
            var1.add(ex.jw);
         }
      }

      if ((var0 & 2) == 2) {
         var1.add(ex.jf);
         if (var2) {
            var1.add(ex.jg);
         }
      }

      if ((var0 & 1) == 1) {
         var1.add(ex.jh);
         if (var2) {
            var1.add(ex.ji);
         }
      }

      if ((var0 & 8) == 8) {
         var1.add(ex.jk);
         if (var2) {
            var1.add(ex.jl);
         }
      }

      if ((var0 & 16) == 16) {
         var1.add(ex.jm);
         var1.add(ex.jt);
         if (var2) {
            var1.add(ex.jn);
         }
      }

      if ((var0 & 32) == 32) {
         var1.add(ex.jo);
         var1.add(ex.jt);
         if (var2) {
            var1.add(ex.jp);
         }
      }

      if ((var0 & 128) == 128) {
         var1.add(ex.jx);
         var1.add(ex.jt);
         if (var2) {
            var1.add(ex.jy);
         }
      }

      boolean var3 = (var0 & '耀') != 0;
      if ((var0 & 1024) == 1024) {
         if (var3) {
            var1.add(ex.iL);
            var1.add(ex.iP);
            var1.add(ex.iQ);
            var1.add(ex.iS);
         } else {
            var1.add(ex.iL);
            var1.add(ex.iM);
            var1.add(ex.iN);
            var1.add(ex.iO);
            var1.add(ex.iP);
            var1.add(ex.iQ);
            var1.add(ex.iR);
            var1.add(ex.iS);
         }
      }

      if ((var0 & 512) == 512) {
         if (var3) {
            var1.add(ex.iT);
            var1.add(ex.iU);
         } else {
            var1.add(ex.iT);
            var1.add(ex.iU);
            var1.add(ex.iV);
            var1.add(ex.iW);
            var1.add(ex.iZ);
            var1.add(ex.jb);
            if (var2) {
               var1.add(ex.iY);
            }

            if ((var0 & 8192) == 0) {
               var1.add(ex.jd);
            }
         }
      }

      if ((var0 & 2048) == 2048) {
         var1.add(ex.jc);
      }

      return var1;
   }

   public static List b(int var0, String var1) {
      String var2 = var1.toUpperCase();
      return (List)jJ.stream().filter((var2x) -> {
         return var2x.getName().toUpperCase().indexOf(var2) >= 0 && O(var0).contains(var2x.bc());
      }).collect(Collectors.toList());
   }

   public static List bl() {
      return (List)jJ.stream().filter((var0) -> {
         return var0 instanceof eQ && !var0.bb() && var0.bc() != ex.jz;
      }).collect(Collectors.toList());
   }

   public static List bm() {
      return (List)jJ.stream().filter((var0) -> {
         return var0 instanceof eH && !var0.bb();
      }).collect(Collectors.toList());
   }

   public static ey d(Object var0) {
      String var1 = var0 instanceof fg ? ((fg)var0).bP() : var0.toString();
      return (ey)jJ.stream().filter((var2) -> {
         return !var2.bb() && !(var2 instanceof eR) ? var0.equals(var2.id) : var1.startsWith(var2.id + "#");
      }).findFirst().orElse((Object)null);
   }

   static eA p(String var0) {
      return (eA)jI.stream().filter((var1) -> {
         return var0.equals(var1.id);
      }).findFirst().orElse((Object)null);
   }

   // $FF: synthetic method
   static Pattern bn() {
      return jH;
   }
}
