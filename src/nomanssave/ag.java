package nomanssave;

class ag extends G {
   // $FF: synthetic field
   final X bV;

   ag(X var1) {
      this.bV = var1;
   }

   protected String g(String var1) {
      gj var2 = (gj)X.k(this.bV).getSelectedItem();
      if (var2 == null) {
         return "";
      } else {
         var1 = var1.trim();
         if (!var1.equals(var2.cP())) {
            var2.ad(var1);
            X.g(this.bV).setText(var1);
         }

         return var1;
      }
   }
}
