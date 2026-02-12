using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{
public class gF : gQ {
   public eY kM;
   gE rf;

   public gF(gE var1, eY var2) {
      this.rf = var1;
      this.kM = var2;
   }

   public bool isValid() {
      string var1 = this.kM.getValueAsString("ElementId");
      return var1 != null && var1.length() > 1;
   }

   public string getType() {
      return "Product";
   }

   public object dz() {
      return this.kM.getValue("ElementId");
   }

   public void m(object var1) {
      this.kM.b("ElementId", var1);
      this.kM.b("LastChangeTimestamp", (object)((int)(DateTimeOffset.UtcNow.ToUnixTimeMilliseconds() / 1000L)));
   }

   public int dA() {
      return this.kM.J("Amount");
   }

   public void aA(int var1) {
      this.kM.b("Amount", (object)var1);
      this.kM.b("LastChangeTimestamp", (object)((int)(DateTimeOffset.UtcNow.ToUnixTimeMilliseconds() / 1000L)));
   }

   public int dB() {
      return 999;
   }
   public gF(gE var1, eY var2, gF var3) {
      this(var1, var2);
   }
}

}
