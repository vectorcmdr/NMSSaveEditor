package nomanssave;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class eH extends ey {
   final boolean jY;
   final String name;
   final ex jZ;
   final boolean special;
   final Integer ka;
   final String jM;
   final boolean kb;
   final String kc;
   final int kd;
   final String description;
   final List ke;

   eH(Element var1, boolean var2) {
      super(var1.getAttribute("id"));
      this.jY = var2;
      this.name = var1.getAttribute("name");
      if (var2) {
         this.jZ = ex.iY;
      } else {
         this.jZ = ex.valueOf(var1.getAttribute("category"));
      }

      this.special = var1.hasAttribute("special") ? Boolean.valueOf(var1.getAttribute("special")) : false;
      this.ka = var1.hasAttribute("chargeable") ? new Integer(var1.getAttribute("chargeable")) : null;
      this.jM = var1.getAttribute("subtitle");
      this.kb = var1.hasAttribute("cooking") ? Boolean.valueOf(var1.getAttribute("cooking")) : false;
      this.kc = var1.hasAttribute("icon") ? var1.getAttribute("icon") : null;
      if (var1.hasAttribute("multiplier")) {
         this.kd = Integer.parseInt(var1.getAttribute("multiplier"));
      } else {
         this.kd = 0;
      }

      String var3 = null;
      NodeList var4 = var1.getChildNodes();
      ArrayList var6 = new ArrayList();

      for(int var7 = 0; var7 < var4.getLength(); ++var7) {
         Node var5 = var4.item(var7);
         if (var5 instanceof Element) {
            var1 = (Element)var5;
            if (var1.getNodeName().equals("description")) {
               var3 = a(var1);
            } else if (var1.getNodeName().equals("requirement")) {
               var6.add(new ez(this, var1));
            }
         }
      }

      this.description = var3;
      this.ke = Collections.unmodifiableList(var6);
   }

   public eB ba() {
      return eB.jO;
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
      return this.kb;
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

   public String toString() {
      return this.name.length() == 0 ? this.id : this.name;
   }
}
