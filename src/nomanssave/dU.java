package nomanssave;

class dU extends G {
   // $FF: synthetic field
   final dN ia;

   dU(dN var1) {
      this.ia = var1;
   }

   protected String g(String var1) {
      gH var2 = (gH)dN.p(this.ia).getSelectedItem();
      if (var2 == null) {
         return "";
      } else {
         try {
            var1 = hg.aB(var1).toString();
            if (!var1.equals(var2.cK())) {
               var2.aa(var1);
            }

            return var1;
         } catch (RuntimeException var4) {
            return var2.cK();
         }
      }
   }
}
