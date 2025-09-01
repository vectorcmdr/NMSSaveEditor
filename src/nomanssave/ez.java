package nomanssave;

import org.w3c.dom.Element;

public class ez {
   final String id;
   final int jK;
   // $FF: synthetic field
   final ey jL;

   ez(ey var1, Element var2) {
      this.jL = var1;
      this.id = var2.getAttribute("id");
      this.jK = Integer.parseInt(var2.getAttribute("quantity"));
   }

   public String getID() {
      return this.id;
   }

   public int bo() {
      return this.jK;
   }
}
