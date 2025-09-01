package nomanssave;

class dG extends G {
   // $FF: synthetic field
   final dE hE;

   dG(dE var1) {
      this.hE = var1;
   }

   protected String g(String var1) {
      gE var2 = (gE)dE.a(this.hE).getSelectedItem();
      if (var2 == null) {
         return "";
      } else {
         var1 = var1.trim();
         if (!var1.equals(var2.getName())) {
            var2.setName(var1);
            dE.c(this.hE).setText(var1);
         }

         return var1;
      }
   }
}
