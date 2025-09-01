package nomanssave;

import java.util.ArrayList;

class fy implements ft {
   final int lT;
   // $FF: synthetic field
   final fu lJ;

   fy(fu var1, int var2) {
      this.lJ = var1;
      this.lT = var2;
   }

   public int getIndex() {
      return this.lT;
   }

   public boolean isEmpty() {
      return fu.a(this.lJ)[this.lT * 2] == null && fu.a(this.lJ)[this.lT * 2 + 1] == null;
   }

   public fn L() {
      long var1 = Long.MIN_VALUE;
      fn var3 = null;
      if (fu.a(this.lJ)[this.lT * 2] != null) {
         var3 = fu.a(this.lJ)[this.lT * 2].L();
         var1 = fu.a(this.lJ)[this.lT * 2].lastModified();
      }

      if (fu.a(this.lJ)[this.lT * 2 + 1] != null) {
         long var4 = fu.a(this.lJ)[this.lT * 2 + 1].lastModified();
         if (var4 > var1) {
            var3 = fu.a(this.lJ)[this.lT * 2 + 1].L();
         }
      }

      return var3;
   }

   public fs[] bX() {
      hc.info("Loading saves for Slot " + (this.lT + 1) + "...");
      ArrayList var1 = new ArrayList();
      if (fu.a(this.lJ)[this.lT * 2] != null) {
         var1.add(fu.a(this.lJ)[this.lT * 2]);
      }

      if (fu.a(this.lJ)[this.lT * 2 + 1] != null) {
         var1.add(fu.a(this.lJ)[this.lT * 2 + 1]);
      }

      var1.sort(new fz(this));
      return (fs[])var1.toArray(new fs[0]);
   }

   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("Slot " + (this.lT + 1) + " - ");
      long var2 = Long.MIN_VALUE;
      fn var4 = null;
      if (fu.a(this.lJ)[this.lT * 2] != null) {
         var4 = fu.a(this.lJ)[this.lT * 2].L();
         var2 = fu.a(this.lJ)[this.lT * 2].lastModified();
      }

      if (fu.a(this.lJ)[this.lT * 2 + 1] != null) {
         long var5 = fu.a(this.lJ)[this.lT * 2 + 1].lastModified();
         if (var5 > var2) {
            var4 = fu.a(this.lJ)[this.lT * 2 + 1].L();
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
}
