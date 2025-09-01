package nomanssave;

class dX extends G {
   // $FF: synthetic field
   final dN ia;

   dX(dN var1) {
      this.ia = var1;
   }

   protected String g(String var1) {
      if (dN.o(this.ia) == null) {
         return "";
      } else {
         int var2 = dN.o(this.ia).dN();

         try {
            int var3 = hf.b(var1, 1, 200);
            if (var3 != var2) {
               dN.o(this.ia).aC(var3);
            }

            return Integer.toString(var3);
         } catch (RuntimeException var4) {
            return Integer.toString(var2);
         }
      }
   }
}
