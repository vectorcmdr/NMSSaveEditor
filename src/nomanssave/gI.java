package nomanssave;

import java.util.function.Function;

class gI extends gt {
   // $FF: synthetic field
   final gH rq;
   // $FF: synthetic field
   private final boolean rr;
   // $FF: synthetic field
   private final int il;

   gI(gH var1, Function var2, eY var3, int var4, int var5, int var6, boolean var7, boolean var8, boolean var9, int var10) {
      super(var2, var3, var4, var5, var6, var7, var8);
      this.rq = var1;
      this.rr = var9;
      this.il = var10;
   }

   public int dj() {
      return this.rr ? 3584 : 3584 | gH.b(this.rq);
   }

   public String toString() {
      return this.rq.dZ() ? "Ship " + this.il + " - Storage Sacs" : super.toString();
   }
}
