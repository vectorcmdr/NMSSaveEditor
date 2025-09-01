package nomanssave;

class dT extends G {
   // $FF: synthetic field
   final dN ia;

   dT(dN var1) {
      this.ia = var1;
   }

   protected String g(String var1) {
      gH var2 = (gH)dN.p(this.ia).getSelectedItem();
      if (var2 == null) {
         return "";
      } else {
         var1 = var1.trim();
         if (!var1.equals(var2.getName())) {
            var2.setName(var1);
            dN.b(this.ia).setText(var1);
         }

         return var1;
      }
   }
}
