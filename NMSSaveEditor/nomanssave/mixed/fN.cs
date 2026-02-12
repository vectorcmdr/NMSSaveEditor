using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


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
      var1.sort(new fP(this));
      return (fs[])var1.ToArray(new fs[0]);
   }

   public fn L() {
      long var1 = long.MinValue;
      fn var3 = null;
      if (fJ.b(this.mt)[this.lT * 2] != null) {
         var3 = fJ.b(this.mt)[this.lT * 2].L();
         var1 = fJ.b(this.mt)[this.lT * 2].LastWriteTimeUtc.Ticks;
      }

      if (fJ.b(this.mt)[this.lT * 2 + 1] != null) {
         long var4 = fJ.b(this.mt)[this.lT * 2 + 1].LastWriteTimeUtc.Ticks;
         if (var4 > var1) {
            var3 = fJ.b(this.mt)[this.lT * 2 + 1].L();
         }
      }

      return var3;
   }

   public string toString() {
      StringBuilder var1 = new StringBuilder();
      var1.Append("Slot " + (this.lT + 1) + " - ");
      long var2 = long.MinValue;
      string var4 = null;
      fn var5 = null;
      if (fJ.b(this.mt)[this.lT * 2] != null) {
         var5 = fJ.b(this.mt)[this.lT * 2].L();
         var2 = fJ.b(this.mt)[this.lT * 2].LastWriteTimeUtc.Ticks;
         var4 = fJ.b(this.mt)[this.lT * 2].Name;
      }

      if (fJ.b(this.mt)[this.lT * 2 + 1] != null) {
         long var6 = fJ.b(this.mt)[this.lT * 2 + 1].LastWriteTimeUtc.Ticks;
         if (var6 > var2) {
            var5 = fJ.b(this.mt)[this.lT * 2 + 1].L();
            var2 = var6;
            var4 = fJ.b(this.mt)[this.lT * 2 + 1].Name;
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


#else

public class fN
{
   public fN() { }
   public fN(params object[] args) { }
   public int lT = 0;
   public fJ mt = default;
   public int getIndex() { return 0; }
   public bool isEmpty() { return false; }
   public fs[] bX() { return System.Array.Empty<fs>(); }
   public fn L() { return default; }
   public string toString() { return ""; }
   public static fJ a(fN var0) { return default; }
}

#endif

}
