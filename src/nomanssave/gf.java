package nomanssave;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class gf {
   final eY nj;
   // $FF: synthetic field
   final ge nk;

   private gf(ge var1, eY var2) {
      this.nk = var1;
      this.nj = var2;
   }

   public String cF() {
      Object var1 = this.nj.getValue("GalacticAddress");
      if (var1 instanceof String) {
         return (String)var1;
      } else {
         return var1 instanceof Number ? "0x" + Long.toHexString(((Number)var1).longValue()) : null;
      }
   }

   public String getName() {
      return this.nj.getValueAsString("Name");
   }

   public void setName(String var1) {
      this.nj.b("Name", (Object)var1);
   }

   public int cG() {
      return this.nj.d("Objects").size();
   }

   public eY cH() {
      return this.nj;
   }

   public List cI() {
      ArrayList var1 = new ArrayList();
      Iterator var3 = gV.G(this.nj).iterator();

      while(var3.hasNext()) {
         eY var2 = (eY)var3.next();
         var1.add(new gg(this, var2));
      }

      return var1;
   }

   public boolean a(gg var1) {
      return gV.a(this.nj, var1.nl);
   }

   public String toString() {
      return this.nj.getValueAsString("Name");
   }

   // $FF: synthetic method
   gf(ge var1, eY var2, gf var3) {
      this(var1, var2);
   }
}
