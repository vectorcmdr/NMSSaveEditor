package nomanssave;

public class gu implements gQ {
   private final eY qD;
   // $FF: synthetic field
   final gt qE;

   private gu(gt var1, eY var2) {
      this.qE = var1;
      this.qD = var2;
   }

   public String getType() {
      return this.qD.getValueAsString("Type.InventoryType");
   }

   public Object dz() {
      return this.qD.getValue("Id");
   }

   public void m(Object var1) {
      this.qD.b("Id", var1);
   }

   public int dA() {
      return this.qD.J("Amount");
   }

   public void aA(int var1) {
      this.qD.b("Amount", (Object)(new Integer(var1)));
   }

   public int dB() {
      return this.qD.J("MaxAmount");
   }

   public double dC() {
      return this.qD.L("DamageFactor");
   }

   public void c(double var1) {
      this.qD.b("DamageFactor", (Object)(new Double(var1)));
   }

   public boolean dD() {
      return this.qD.M("FullyInstalled");
   }

   public void e(boolean var1) {
      this.qD.b("FullyInstalled", (Object)(new Boolean(var1)));
   }

   // $FF: synthetic method
   gu(gt var1, eY var2, gu var3) {
      this(var1, var2);
   }
}
