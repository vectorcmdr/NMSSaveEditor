package nomanssave;

class ch extends G {
   // $FF: synthetic field
   final cg fF;

   ch(cg var1) {
      this.fF = var1;
   }

   protected String g(String var1) {
      if (cg.a(this.fF) == null) {
         return "";
      } else {
         try {
            int var2 = hf.b(var1, 0, 99999);
            if (cg.a(this.fF) != var2) {
               cg.b(this.fF).m(cg.c(this.fF).M(var2));
               cg.a(this.fF, new Integer(var2));
            }
         } catch (RuntimeException var3) {
         }

         return cg.a(this.fF).toString();
      }
   }
}
