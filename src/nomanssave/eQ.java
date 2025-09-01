package nomanssave;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class eQ extends ey {
   private static final String gc = "0123456789ABCDEFabcdef";
   final boolean jY;
   final String name;
   final ex jZ;
   final boolean special;
   final Integer ka;
   final String jM;
   final String kc;
   final int kd;
   final String description;
   final List ke;
   final eR km;

   eQ(Element var1, boolean var2) {
      super(var1.getAttribute("id"));
      this.jY = var2;
      this.name = var1.getAttribute("name");

      try {
         if (var2) {
            this.jZ = ex.valueOf("PROC_" + var1.getAttribute("category"));
         } else {
            this.jZ = ex.valueOf(var1.getAttribute("category"));
         }
      } catch (IllegalArgumentException var10) {
         throw new RuntimeException("Error in tech: " + this.id, var10);
      }

      this.special = var1.hasAttribute("special") ? Boolean.valueOf(var1.getAttribute("special")) : false;
      this.ka = var1.hasAttribute("chargeable") ? new Integer(var1.getAttribute("chargeable")) : null;
      this.jM = var1.getAttribute("subtitle");
      this.kc = var1.hasAttribute("icon") ? var1.getAttribute("icon") : null;
      if (var1.hasAttribute("multiplier")) {
         this.kd = Integer.parseInt(var1.getAttribute("multiplier"));
      } else {
         this.kd = 0;
      }

      String var3 = null;
      ArrayList var4 = new ArrayList();
      eR var5 = null;
      NodeList var6 = var1.getChildNodes();

      for(int var9 = 0; var9 < var6.getLength(); ++var9) {
         Node var7 = var6.item(var9);
         if (var7 instanceof Element) {
            Element var8 = (Element)var7;
            if (var8.getNodeName().equals("description")) {
               var3 = a(var8);
            } else if (var8.getNodeName().equals("requirement")) {
               var4.add(new ez(this, var8));
            } else if (var8.getNodeName().equals("techbox")) {
               var5 = new eR(this, var8);
            }
         }
      }

      this.description = var3;
      this.ke = Collections.unmodifiableList(var4);
      this.km = var5;
   }

   public eB ba() {
      return eB.jN;
   }

   public boolean bb() {
      return this.jY;
   }

   public String getName() {
      return this.name;
   }

   public ex bc() {
      return this.jZ;
   }

   public boolean bd() {
      return !this.jY && this.jZ != ex.ja && this.jZ != ex.iZ;
   }

   public boolean be() {
      return !this.jY && this.special;
   }

   public Integer bf() {
      return this.ka;
   }

   public String bg() {
      return this.jM;
   }

   public boolean bh() {
      return false;
   }

   public String bi() {
      return this.kc;
   }

   public int bj() {
      return this.kd;
   }

   public String getDescription() {
      return this.description;
   }

   public List bk() {
      return this.ke;
   }

   public eR bv() {
      return this.km;
   }

   public String toString() {
      return this.name.length() == 0 ? this.id : this.name;
   }
}
