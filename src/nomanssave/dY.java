package nomanssave;

class dY extends G {
   // $FF: synthetic field
   final dN ia;

   dY(dN var1) {
      this.ia = var1;
   }

   protected String g(String var1) {
      gH var2 = (gH)dN.p(this.ia).getSelectedItem();
      if (var2 == null) {
         return "";
      } else {
         double var3 = var2.dF();

         try {
            double var5 = hf.a(var1, 0.0D, 1000.0D);
            if (var5 != var3) {
               var2.d(var5);
            }

            return Double.toString(var5);
         } catch (RuntimeException var7) {
            return Double.toString(var3);
         }
      }
   }
}
