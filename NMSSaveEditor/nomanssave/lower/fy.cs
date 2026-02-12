using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class fy : ft {
   public int lT;
   public fu lJ;

   public fy(fu var1, int var2) {
      this.lJ = var1;
      this.lT = var2;
   }

   public int getIndex() {
      return this.lT;
   }

   public bool isEmpty() {
      return fu.a(this.lJ)[this.lT * 2] == null && fu.a(this.lJ)[this.lT * 2 + 1] == null;
   }

   public fn L() {
      long var1 = long.MinValue;
      fn var3 = null;
      if (fu.a(this.lJ)[this.lT * 2] != null) {
         var3 = fu.a(this.lJ)[this.lT * 2].L();
         var1 = fu.a(this.lJ)[this.lT * 2].LastWriteTimeUtc.Ticks;
      }

      if (fu.a(this.lJ)[this.lT * 2 + 1] != null) {
         long var4 = fu.a(this.lJ)[this.lT * 2 + 1].LastWriteTimeUtc.Ticks;
         if (var4 > var1) {
            var3 = fu.a(this.lJ)[this.lT * 2 + 1].L();
         }
      }

      return var3;
   }

   public fs[] bX() {
      hc.info("Loading saves for Slot " + (this.lT + 1) + "...");
      List<object> var1 = new List<object>();
      if (fu.a(this.lJ)[this.lT * 2] != null) {
         var1.Add(fu.a(this.lJ)[this.lT * 2]);
      }

      if (fu.a(this.lJ)[this.lT * 2 + 1] != null) {
         var1.Add(fu.a(this.lJ)[this.lT * 2 + 1]);
      }

      var1.sort(new fz(this));
      return (fs[])var1.ToArray(new fs[0]);
   }

   public string toString() {
      StringBuilder var1 = new StringBuilder();
      var1.Append("Slot " + (this.lT + 1) + " - ");
      long var2 = long.MinValue;
      fn var4 = null;
      if (fu.a(this.lJ)[this.lT * 2] != null) {
         var4 = fu.a(this.lJ)[this.lT * 2].L();
         var2 = fu.a(this.lJ)[this.lT * 2].LastWriteTimeUtc.Ticks;
      }

      if (fu.a(this.lJ)[this.lT * 2 + 1] != null) {
         long var5 = fu.a(this.lJ)[this.lT * 2 + 1].LastWriteTimeUtc.Ticks;
         if (var5 > var2) {
            var4 = fu.a(this.lJ)[this.lT * 2 + 1].L();
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
}


#else

public class fy
{
   public fy() { }
   public fy(params object[] args) { }
   public int lT = 0;
   public fu lJ = default;
   public int getIndex() { return 0; }
   public bool isEmpty() { return false; }
   public fn L() { return default; }
   public fs[] bX() { return System.Array.Empty<fs>(); }
   public string toString() { return ""; }
}

#endif

}
