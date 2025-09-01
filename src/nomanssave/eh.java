package nomanssave;

class eh extends G {
   // $FF: synthetic field
   final ec ik;
   // $FF: synthetic field
   private final int il;

   eh(ec var1, int var2) {
      this.ik = var1;
      this.il = var2;
   }

   protected String g(String var1) {
      try {
         var1 = hg.aB(var1).toString();
         if (!var1.equals(eb.a(ec.h(this.ik))[this.il].eg())) {
            eb.a(ec.h(this.ik))[this.il].ay(var1);
         }

         return var1;
      } catch (RuntimeException var3) {
         return eb.a(ec.h(this.ik))[this.il].eg();
      }
   }
}
