package nomanssave;

public class gF implements gQ {
   private final eY kM;
   // $FF: synthetic field
   final gE rf;

   private gF(gE var1, eY var2) {
      this.rf = var1;
      this.kM = var2;
   }

   public boolean isValid() {
      String var1 = this.kM.getValueAsString("ElementId");
      return var1 != null && var1.length() > 1;
   }

   public String getType() {
      return "Product";
   }

   public Object dz() {
      return this.kM.getValue("ElementId");
   }

   public void m(Object var1) {
      this.kM.b("ElementId", var1);
      this.kM.b("LastChangeTimestamp", (Object)((int)(System.currentTimeMillis() / 1000L)));
   }

   public int dA() {
      return this.kM.J("Amount");
   }

   public void aA(int var1) {
      this.kM.b("Amount", (Object)var1);
      this.kM.b("LastChangeTimestamp", (Object)((int)(System.currentTimeMillis() / 1000L)));
   }

   public int dB() {
      return 999;
   }

   // $FF: synthetic method
   gF(gE var1, eY var2, gF var3) {
      this(var1, var2);
   }
}
