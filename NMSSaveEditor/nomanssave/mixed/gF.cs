using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{


public class gF : gQ {
   public eY kM;
   public gE rf;

   public gF(gE var1, eY var2) {
      this.rf = var1;
      this.kM = var2;
   }

   public bool isValid() {
      // PORT_TODO: string var1 = this.kM.getValueAsString("ElementId");
      // PORT_TODO: return var1 != null && var1.Length > 1;
      return false;
   }

   public string getType() {
      return "Product";
   }

   public object dz() {
      // PORT_TODO: return this.kM.getValue("ElementId");
      return default;
   }

   public void m(object var1) {
      // PORT_TODO: this.kM.b("ElementId", var1);
      // PORT_TODO: this.kM.b("LastChangeTimestamp", (object)((int)(DateTimeOffset.UtcNow.ToUnixTimeMilliseconds() / 1000L)));
   }

   public int dA() {
      // PORT_TODO: return this.kM.J("Amount");
      return 0;
   }

   public void aA(int var1) {
      // PORT_TODO: this.kM.b("Amount", (object)var1);
      // PORT_TODO: this.kM.b("LastChangeTimestamp", (object)((int)(DateTimeOffset.UtcNow.ToUnixTimeMilliseconds() / 1000L)));
   }

   public int dB() {
      return 999;
   }
   public gF(gE var1, eY var2, gF var3) {
      // PORT_TODO: // PORT_TODO: this(var1, var2);
   }
}



}
