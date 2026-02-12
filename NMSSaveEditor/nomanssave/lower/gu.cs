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

public class gu : gQ {
   private eY qD;
   // $FF: synthetic field
   gt qE;

   private gu(gt var1, eY var2) {
      this.qE = var1;
      this.qD = var2;
   }

   public string getType() {
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

   public bool dD() {
      return this.qD.M("FullyInstalled");
   }

   public void e(bool var1) {
      this.qD.b("FullyInstalled", (Object)(new Boolean(var1)));
   }

   // $FF: synthetic method
   gu(gt var1, eY var2, gu var3) {
      // Constructor chain: base(var1, var2)
   }
}

}
