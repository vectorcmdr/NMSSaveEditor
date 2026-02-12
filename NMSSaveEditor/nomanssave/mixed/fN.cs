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

public class fN : ft {
   int lT;
   // $FF: synthetic field
   fJ mt;

   fN(fJ var1, int var2) {
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
      long var1 = Long.MIN_VALUE;
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
      var1.append("Slot " + (this.lT + 1) + " - ");
      long var2 = Long.MIN_VALUE;
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
         var1.append(var5.ToString());
         if (var4 != null) {
            var1.append(" - " + var4);
         } else {
            var1.append(" - " + Application.b(var2));
         }
      } else {
         var1.append("[EMPTY]");
      }

      return var1.ToString();
   }

   // $FF: synthetic method
   static fJ a(fN var0) {
      return var0.mt;
   }
}

}
