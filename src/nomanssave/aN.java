package nomanssave;

class aN extends G {
   // $FF: synthetic field
   final aJ dj;
   // $FF: synthetic field
   private final Application bv;

   aN(aJ var1, Application var2) {
      this.dj = var1;
      this.bv = var2;
   }

   protected String g(String var1) {
      if (aJ.a(this.dj) == null) {
         return "";
      } else {
         long var2 = aJ.a(this.dj).dJ();

         try {
            long var4 = hf.a(var1, 0L, 4294967295L);
            if (var4 != var2) {
               aJ.a(this.dj).e(var4);
               this.bv.C();
            }

            return Long.toString(var4);
         } catch (RuntimeException var6) {
            return Long.toString(var2);
         }
      }
   }
}
