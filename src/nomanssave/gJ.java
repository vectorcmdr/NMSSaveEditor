package nomanssave;

import java.util.function.Function;

class gJ extends gt {
   // $FF: synthetic field
   final gH rq;
   // $FF: synthetic field
   private final int il;

   gJ(gH var1, Function var2, eY var3, int var4, int var5, int var6, boolean var7, boolean var8, int var9) {
      super(var2, var3, var4, var5, var6, var7, var8);
      this.rq = var1;
      this.il = var9;
   }

   public int dj() {
      return gH.b(this.rq);
   }

   public String toString() {
      return this.rq.dZ() ? "Ship " + this.il + " - Organ Chamber" : super.toString();
   }
}
