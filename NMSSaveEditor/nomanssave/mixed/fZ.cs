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

public class fZ : ft {
   public int lT;
   // $FF: synthetic field
   public fT mN;

   public fZ(fT var1, int var2) {
      this.mN = var1;
      this.lT = var2;
   }

   public int getIndex() {
      return this.lT;
   }

   public bool isEmpty() {
      return fT.b(this.mN)[this.lT * 2] == null && fT.b(this.mN)[this.lT * 2 + 1] == null;
   }

   public fs[] bX() {
      hc.info("Loading saves for Slot " + (this.lT + 1) + "...");
      List<object> var1 = new List<object>();
      if (fT.b(this.mN)[this.lT * 2] != null) {
         var1.Add(fT.b(this.mN)[this.lT * 2]);
      }
       if (fT.b(this.mN)[this.lT * 2 + 1] != null) {
         var1.Add(fT.b(this.mN)[this.lT * 2 + 1]);
      }
       aH.cG.listFiles(new ga(this, var1));
      var1.sort(new gb(this));
      return (fs[])var1.ToArray(new fs[0]);
   }

   public fn L() {
      long var1 = long.MinValue;
      fn var3 = null;
      if (fT.b(this.mN)[this.lT * 2] != null) {
         var3 = fT.b(this.mN)[this.lT * 2].L();
         var1 = fT.b(this.mN)[this.lT * 2].LastWriteTimeUtc.Ticks;
      }
       if (fT.b(this.mN)[this.lT * 2 + 1] != null) {
         long var4 = fT.b(this.mN)[this.lT * 2 + 1].LastWriteTimeUtc.Ticks;
         if (var4 > var1) {
            var3 = fT.b(this.mN)[this.lT * 2 + 1].L();
         }
      }
       return var3;
   }

   public string toString() {
      StringBuilder var1 = new StringBuilder();
      var1.Append("Slot " + (this.lT + 1) + " - ");
      long var2 = long.MinValue;
      fn var4 = null;
      if (fT.b(this.mN)[this.lT * 2] != null) {
         var4 = fT.b(this.mN)[this.lT * 2].L();
         var2 = fT.b(this.mN)[this.lT * 2].LastWriteTimeUtc.Ticks;
      }
       if (fT.b(this.mN)[this.lT * 2 + 1] != null) {
         long var5 = fT.b(this.mN)[this.lT * 2 + 1].LastWriteTimeUtc.Ticks;
         if (var5 > var2) {
            var4 = fT.b(this.mN)[this.lT * 2 + 1].L();
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

   // $FF: synthetic method
   public static fT a(fZ var0) {
      return var0.mN;
   }

}


}