package nomanssave;

import java.io.ByteArrayOutputStream;
import java.util.List;
import org.w3c.dom.Element;

public class eR extends ey {
   final String kc;
   final eA kn;
   // $FF: synthetic field
   final eQ ko;

   eR(eQ var1, Element var2) {
      super(var2.getAttribute("id"));
      this.ko = var1;
      this.kc = var2.hasAttribute("icon") ? var2.getAttribute("icon") : null;
      this.kn = ey.p(var2.getAttribute("template"));
   }

   public Object aZ() {
      return this.M(this.ko.jY ? (int)Math.floor(Math.random() * 100000.0D) : 0);
   }

   public Object M(int var1) {
      if (this.id.length() == 13 && this.id.charAt(0) == '^') {
         if (var1 >= 0 && var1 < 100000) {
            ByteArrayOutputStream var2 = new ByteArrayOutputStream();
            var2.write(94);

            int var3;
            int var4;
            for(var3 = 0; var3 < 6; ++var3) {
               var4 = "0123456789ABCDEFabcdef".indexOf(this.id.charAt(var3 * 2 + 1));
               int var5 = "0123456789ABCDEFabcdef".indexOf(this.id.charAt(var3 * 2 + 2));
               if (var4 < 0 || var5 < 0) {
                  throw new RuntimeException("Cannot create ID: invalid hex");
               }

               if (var4 >= 16) {
                  var4 -= 6;
               }

               if (var5 >= 16) {
                  var5 -= 6;
               }

               var2.write(var4 << 4 | var5);
            }

            var2.write(35);

            for(var3 = 100000; var3 > 1; var3 /= 10) {
               var4 = var1 * 10 / var3 % 10;
               var2.write("0123456789ABCDEFabcdef".charAt(var4));
            }

            return new fg(var2.toByteArray());
         } else {
            throw new RuntimeException("Cannot create ID: invalid proc");
         }
      } else {
         throw new RuntimeException("Cannot create ID: invalid string");
      }
   }

   public eB ba() {
      return eB.jO;
   }

   public boolean bb() {
      return this.ko.jY;
   }

   private String y(String var1) {
      if ("NAME".equals(var1)) {
         return this.ko.name;
      } else {
         return "TECH_DESC".equals(var1) ? this.ko.description : var1;
      }
   }

   public String getName() {
      return this.kn.a(this::y);
   }

   public ex bc() {
      return ex.jd;
   }

   public boolean bd() {
      return false;
   }

   public boolean be() {
      return false;
   }

   public Integer bf() {
      return null;
   }

   public String bg() {
      return this.kn.b(this::y);
   }

   public boolean bh() {
      return false;
   }

   public String bi() {
      return this.kc;
   }

   public int bj() {
      return 0;
   }

   public String getDescription() {
      return this.kn.c(this::y);
   }

   public List bk() {
      return this.ko.ke;
   }

   public String toString() {
      return this.ko.name.length() == 0 ? this.id : this.ko.name;
   }
}
