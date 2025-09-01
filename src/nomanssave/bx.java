package nomanssave;

class bx extends G {
   // $FF: synthetic field
   final bl er;

   bx(bl var1) {
      this.er = var1;
   }

   protected String g(String var1) {
      if (bl.b(this.er) < 0) {
         return "";
      } else {
         try {
            var1 = var1.trim();
            if (!var1.equals(bl.c(this.er)[bl.b(this.er)].cU())) {
               bl.c(this.er)[bl.b(this.er)].ah(var1);
               bl.n(this.er).setText(var1);
            }

            return var1;
         } catch (RuntimeException var3) {
            return bl.c(this.er)[bl.b(this.er)].cU();
         }
      }
   }
}
