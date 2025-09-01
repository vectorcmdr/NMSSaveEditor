package nomanssave;

class by extends G {
   // $FF: synthetic field
   final bl er;

   by(bl var1) {
      this.er = var1;
   }

   protected String g(String var1) {
      if (bl.b(this.er) < 0) {
         return "";
      } else {
         try {
            var1 = hg.aB(var1).toString();
            if (!var1.equals(bl.c(this.er)[bl.b(this.er)].cV())) {
               bl.c(this.er)[bl.b(this.er)].ai(var1);
            }

            return var1;
         } catch (RuntimeException var3) {
            return bl.c(this.er)[bl.b(this.er)].cV();
         }
      }
   }
}
