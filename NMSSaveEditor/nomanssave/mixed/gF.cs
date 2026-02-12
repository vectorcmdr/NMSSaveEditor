using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using System.Globalization;

namespace NMSSaveEditor
{

public class gF : gQ {
   public eY kM;
   // $FF: synthetic field
   public gE rf;

   public gF(gE var1, eY var2) {
      this.rf = var1;
      this.kM = var2;
   }

   public bool isValid() {
      string var1 = this.kM.getValueAsString("ElementId");
      return var1 != null && var1.Length > 1;
   }

   public string getType() {
      return "Product";
   }

   public Object dz() {
      return this.kM.getValue("ElementId");
   }

   public void m(Object var1) {
      this.kM.b("ElementId", var1);
      this.kM.b("LastChangeTimestamp", (Object)((int)(DateTimeOffset.UtcNow.ToUnixTimeMilliseconds() / 1000L)));
   }

   public int dA() {
      return this.kM.J("Amount");
   }

   public void aA(int var1) {
      this.kM.b("Amount", (Object)var1);
      this.kM.b("LastChangeTimestamp", (Object)((int)(DateTimeOffset.UtcNow.ToUnixTimeMilliseconds() / 1000L)));
   }

   public int dB() {
      return 999;
   }

   // $FF: synthetic method
   public gF(gE var1, eY var2, gF var3) {
      // Constructor chain: base(var1, var2)
   }
}

}
