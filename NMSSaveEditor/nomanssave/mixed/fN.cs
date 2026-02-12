using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{



public class fN : ft {
   public int lT;
   public fJ mt;

   public fN(fJ var1, int var2) {
      this.mt = var1;
      this.lT = var2;
   }

   public int getIndex() {
      return this.lT;
   }

   public bool isEmpty() {
      return fJ.b(this.mt)[this.lT * 2] == null && fJ.b(this.mt)[this.lT * 2 + 1] == null;
   }

   public fs[] bX() {
      hc.info("Loading saves for Slot " + (this.lT + 1) + "...");
      List<object> var1 = new List<object>();
      if (fJ.b(this.mt)[this.lT * 2] != null) {
         var1.Add(fJ.b(this.mt)[this.lT * 2]);
      }

      if (fJ.b(this.mt)[this.lT * 2 + 1] != null) {
         var1.Add(fJ.b(this.mt)[this.lT * 2 + 1]);
      }

      aH.cG.listFiles(new fO(this, var1));
      var1.Sort(new fP(this));
      return var1.Cast<fs>().ToArray();
   }

   public fn L() {
      long var1 = long.MinValue;
      fn var3 = null;
      if (fJ.b(this.mt)[this.lT * 2] != null) {
         var3 = fJ.b(this.mt)[this.lT * 2].L();
         var1 = fJ.b(this.mt)[this.lT * 2].lastModified();
      }

      if (fJ.b(this.mt)[this.lT * 2 + 1] != null) {
         long var4 = fJ.b(this.mt)[this.lT * 2 + 1].lastModified();
         if (var4 > var1) {
            var3 = fJ.b(this.mt)[this.lT * 2 + 1].L();
         }
      }

      return var3;
   }

   public override string ToString() {
      StringBuilder var1 = new StringBuilder();
      var1.Append("Slot " + (this.lT + 1) + " - ");
      long var2 = long.MinValue;
      string var4 = null;
      fn var5 = null;
      if (fJ.b(this.mt)[this.lT * 2] != null) {
         var5 = fJ.b(this.mt)[this.lT * 2].L();
         var2 = fJ.b(this.mt)[this.lT * 2].lastModified();
         var4 = fJ.b(this.mt)[this.lT * 2].getName();
      }

      if (fJ.b(this.mt)[this.lT * 2 + 1] != null) {
         long var6 = fJ.b(this.mt)[this.lT * 2 + 1].lastModified();
         if (var6 > var2) {
            var5 = fJ.b(this.mt)[this.lT * 2 + 1].L();
            var2 = var6;
            var4 = fJ.b(this.mt)[this.lT * 2 + 1].getName();
         }
      }

      if (var5 != null) {
         var1.Append(var5.ToString());
         if (var4 != null) {
            var1.Append(" - " + var4);
         } else {
            var1.Append(" - " + Application.b(var2));
         }
      } else {
         var1.Append("[EMPTY]");
      }

      return var1.ToString();
   }

   public static fJ a(fN var0) {
      return var0.mt;
   }
}



}
