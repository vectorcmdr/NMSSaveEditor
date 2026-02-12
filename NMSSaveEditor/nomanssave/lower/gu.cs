using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE

public class gu : gQ {
   public eY qD;
   public gt qE;

   public gu(gt var1, eY var2) {
      this.qE = var1;
      this.qD = var2;
   }

   public string getType() {
      return this.qD.getValueAsString("Type.InventoryType");
   }

   public object dz() {
      return this.qD.getValue("Id");
   }

   public void m(object var1) {
      this.qD.b("Id", var1);
   }

   public int dA() {
      return this.qD.J("Amount");
   }

   public void aA(int var1) {
      this.qD.b("Amount", (object)(((int)(var1))));
   }

   public int dB() {
      return this.qD.J("MaxAmount");
   }

   public double dC() {
      return this.qD.L("DamageFactor");
   }

   public void c(double var1) {
      this.qD.b("DamageFactor", (object)(new Double(var1)));
   }

   public bool dD() {
      return this.qD.M("FullyInstalled");
   }

   public void e(bool var1) {
      this.qD.b("FullyInstalled", (object)(new Boolean(var1)));
   }
   public gu(gt var1, eY var2, gu var3) {
      this(var1, var2);
   }
}


#else

public class gu
{
   public gu() { }
   public gu(params object[] args) { }
   public eY qD = default;
   public gt qE = default;
   public string getType() { return ""; }
   public object dz() { return default; }
   public void m(object var1) { }
   public int dA() { return 0; }
   public void aA(int var1) { }
   public int dB() { return 0; }
   public double dC() { return 0; }
   public void c(double var1) { }
   public bool dD() { return false; }
   public void e(bool var1) { }
}

#endif

}
