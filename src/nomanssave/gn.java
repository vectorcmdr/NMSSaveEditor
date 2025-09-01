package nomanssave;

public class gn {
   final eY nj;
   // $FF: synthetic field
   final gm oK;

   private gn(gm var1, eY var2) {
      this.oK = var1;
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

   public String toString() {
      return this.nj.getValueAsString("Name");
   }

   // $FF: synthetic method
   gn(gm var1, eY var2, gn var3) {
      this(var1, var2);
   }
}
