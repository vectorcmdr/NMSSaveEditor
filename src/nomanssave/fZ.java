package nomanssave;

import java.util.ArrayList;

class fZ implements ft {
   final int lT;
   // $FF: synthetic field
   final fT mN;

   fZ(fT var1, int var2) {
      this.mN = var1;
      this.lT = var2;
   }

   public int getIndex() {
      return this.lT;
   }

   public boolean isEmpty() {
      return fT.b(this.mN)[this.lT * 2] == null && fT.b(this.mN)[this.lT * 2 + 1] == null;
   }

   public fs[] bX() {
      hc.info("Loading saves for Slot " + (this.lT + 1) + "...");
      ArrayList var1 = new ArrayList();
      if (fT.b(this.mN)[this.lT * 2] != null) {
         var1.add(fT.b(this.mN)[this.lT * 2]);
      }

      if (fT.b(this.mN)[this.lT * 2 + 1] != null) {
         var1.add(fT.b(this.mN)[this.lT * 2 + 1]);
      }

      aH.cG.listFiles(new ga(this, var1));
      var1.sort(new gb(this));
      return (fs[])var1.toArray(new fs[0]);
   }

   public fn L() {
      long var1 = Long.MIN_VALUE;
      fn var3 = null;
      if (fT.b(this.mN)[this.lT * 2] != null) {
         var3 = fT.b(this.mN)[this.lT * 2].L();
         var1 = fT.b(this.mN)[this.lT * 2].lastModified();
      }

      if (fT.b(this.mN)[this.lT * 2 + 1] != null) {
         long var4 = fT.b(this.mN)[this.lT * 2 + 1].lastModified();
         if (var4 > var1) {
            var3 = fT.b(this.mN)[this.lT * 2 + 1].L();
         }
      }

      return var3;
   }

   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("Slot " + (this.lT + 1) + " - ");
      long var2 = Long.MIN_VALUE;
      fn var4 = null;
      if (fT.b(this.mN)[this.lT * 2] != null) {
         var4 = fT.b(this.mN)[this.lT * 2].L();
         var2 = fT.b(this.mN)[this.lT * 2].lastModified();
      }

      if (fT.b(this.mN)[this.lT * 2 + 1] != null) {
         long var5 = fT.b(this.mN)[this.lT * 2 + 1].lastModified();
         if (var5 > var2) {
            var4 = fT.b(this.mN)[this.lT * 2 + 1].L();
            var2 = var5;
         }
      }

      if (var4 != null) {
         var1.append(var4.toString());
         var1.append(" - " + Application.b(var2));
      } else {
         var1.append("[EMPTY]");
      }

      return var1.toString();
   }

   // $FF: synthetic method
   static fT a(fZ var0) {
      return var0.mN;
   }
}
