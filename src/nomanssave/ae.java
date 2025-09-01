package nomanssave;

class ae extends G {
   // $FF: synthetic field
   final X bV;

   ae(X var1) {
      this.bV = var1;
   }

   protected String g(String var1) {
      gj var2 = (gj)X.k(this.bV).getSelectedItem();
      if (var2 == null) {
         return "";
      } else {
         var1 = var1.trim();
         if (!var1.equals(var2.cN())) {
            var2.ab(var1);
            X.e(this.bV).setText(var1);
         }

         return var1;
      }
   }
}
