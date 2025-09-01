package nomanssave;

import org.w3c.dom.Element;

public class ew {
   final String iI;
   final int iJ;
   final int iK;

   ew(Element var1) {
      this.iI = var1.getAttribute("group");
      this.iJ = Integer.parseInt(var1.getAttribute("substance"));
      this.iK = Integer.parseInt(var1.getAttribute("product"));
   }

   public int aX() {
      return this.iJ;
   }

   public int aY() {
      return this.iK;
   }
}
