package nomanssave;

import java.util.ArrayList;

class fN implements ft {
   final int lT;
   // $FF: synthetic field
   final fJ mt;

   fN(fJ var1, int var2) {
      this.mt = var1;
      this.lT = var2;
   }

   public int getIndex() {
      return this.lT;
   }

   public boolean isEmpty() {
      return fJ.b(this.mt)[this.lT * 2] == null && fJ.b(this.mt)[this.lT * 2 + 1] == null;
   }

   public fs[] bX() {
      hc.info("Loading saves for Slot " + (this.lT + 1) + "...");
      ArrayList var1 = new ArrayList();
      if (fJ.b(this.mt)[this.lT * 2] != null) {
         var1.add(fJ.b(this.mt)[this.lT * 2]);
      }

      if (fJ.b(this.mt)[this.lT * 2 + 1] != null) {
         var1.add(fJ.b(this.mt)[this.lT * 2 + 1]);
      }

      aH.cG.listFiles(new fO(this, var1));
      var1.sort(new fP(this));
      return (fs[])var1.toArray(new fs[0]);
   }

   public fn L() {
      long var1 = Long.MIN_VALUE;
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

   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("Slot " + (this.lT + 1) + " - ");
      long var2 = Long.MIN_VALUE;
      String var4 = null;
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
         var1.append(var5.toString());
         if (var4 != null) {
            var1.append(" - " + var4);
         } else {
            var1.append(" - " + Application.b(var2));
         }
      } else {
         var1.append("[EMPTY]");
      }

      return var1.toString();
   }

   // $FF: synthetic method
   static fJ a(fN var0) {
      return var0.mt;
   }
}
