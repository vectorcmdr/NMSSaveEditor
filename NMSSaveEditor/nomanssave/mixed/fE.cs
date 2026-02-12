using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class fE : ft {
   public int lT;
   fA ma;

   public fE(fA var1, int var2) {
      this.ma = var1;
      this.lT = var2;
   }

   public int getIndex() {
      return this.lT;
   }

   public bool isEmpty() {
      return fA.b(this.ma)[this.lT * 2] == null && fA.b(this.ma)[this.lT * 2 + 1] == null;
   }

   public fs[] bX() {
      hc.info("Loading saves for Slot " + (this.lT + 1) + "...");
      List<object> var1 = new List<object>();
      if (fA.b(this.ma)[this.lT * 2] != null) {
         var1.Add(fA.b(this.ma)[this.lT * 2]);
      }

      if (fA.b(this.ma)[this.lT * 2 + 1] != null) {
         var1.Add(fA.b(this.ma)[this.lT * 2 + 1]);
      }

      aH.cG.listFiles(new fF(this, var1));
      var1.sort(new fG(this));
      return (fs[])var1.ToArray(new fs[0]);
   }

   public fn L() {
      long var1 = long.MinValue;
      fn var3 = null;
      if (fA.b(this.ma)[this.lT * 2] != null) {
         var3 = fA.b(this.ma)[this.lT * 2].L();
         var1 = fA.b(this.ma)[this.lT * 2].LastWriteTimeUtc.Ticks;
      }

      if (fA.b(this.ma)[this.lT * 2 + 1] != null) {
         long var4 = fA.b(this.ma)[this.lT * 2 + 1].LastWriteTimeUtc.Ticks;
         if (var4 > var1) {
            var3 = fA.b(this.ma)[this.lT * 2 + 1].L();
         }
      }

      return var3;
   }

   public string toString() {
      StringBuilder var1 = new StringBuilder();
      var1.Append("Slot " + (this.lT + 1) + " - ");
      long var2 = long.MinValue;
      fn var4 = null;
      if (fA.b(this.ma)[this.lT * 2] != null) {
         var4 = fA.b(this.ma)[this.lT * 2].L();
         var2 = fA.b(this.ma)[this.lT * 2].LastWriteTimeUtc.Ticks;
      }

      if (fA.b(this.ma)[this.lT * 2 + 1] != null) {
         long var5 = fA.b(this.ma)[this.lT * 2 + 1].LastWriteTimeUtc.Ticks;
         if (var5 > var2) {
            var4 = fA.b(this.ma)[this.lT * 2 + 1].L();
            var2 = var5;
         }
      }

      if (var4 != null) {
         var1.Append(var4.ToString());
         var1.Append(" - " + Application.b(var2));
      } else {
         var1.Append("[EMPTY]");
      }

      return var1.ToString();
   }
   public static fA a(fE var0) {
      return var0.ma;
   }
}

}
