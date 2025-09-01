package nomanssave;

class ci extends G {
   // $FF: synthetic field
   final cg fF;

   ci(cg var1) {
      this.fF = var1;
   }

   protected String g(String var1) {
      if (cg.d(this.fF) == null) {
         return "";
      } else {
         try {
            int var2 = hf.b(var1, 1, cg.b(this.fF).dB());
            if (cg.d(this.fF) != var2) {
               cg.b(this.fF).aA(var2);
               cg.b(this.fF, new Integer(var2));
            }
         } catch (RuntimeException var3) {
         }

         return cg.d(this.fF).toString();
      }
   }
}
