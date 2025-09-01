package nomanssave;

class dH extends G {
   // $FF: synthetic field
   final dE hE;

   dH(dE var1) {
      this.hE = var1;
   }

   protected String g(String var1) {
      gE var2 = (gE)dE.a(this.hE).getSelectedItem();
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
