package nomanssave;

class bq extends G {
   // $FF: synthetic field
   final bl er;

   bq(bl var1) {
      this.er = var1;
   }

   protected String g(String var1) {
      if (bl.b(this.er) < 0) {
         return "";
      } else {
         int var2 = bl.c(this.er)[bl.b(this.er)].df();

         try {
            int var3 = hf.b(var1, 0, Integer.MAX_VALUE);
            if (var3 != var2) {
               bl.c(this.er)[bl.b(this.er)].av(var3);
            }

            return Integer.toString(var3);
         } catch (RuntimeException var4) {
            return Integer.toString(var2);
         }
      }
   }
}
