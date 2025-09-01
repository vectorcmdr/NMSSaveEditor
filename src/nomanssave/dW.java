package nomanssave;

class dW extends G {
   // $FF: synthetic field
   final dN ia;

   dW(dN var1) {
      this.ia = var1;
   }

   protected String g(String var1) {
      if (dN.o(this.ia) == null) {
         return "";
      } else {
         int var2 = dN.o(this.ia).dM();

         try {
            int var3 = hf.b(var1, 1, 500);
            if (var3 != var2) {
               dN.o(this.ia).aB(var3);
            }

            return Integer.toString(var3);
         } catch (RuntimeException var4) {
            return Integer.toString(var2);
         }
      }
   }
}
