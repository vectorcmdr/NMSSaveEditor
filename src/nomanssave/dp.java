package nomanssave;

class dp extends G {
   // $FF: synthetic field
   final dj hl;

   dp(dj var1) {
      this.hl = var1;
   }

   protected String g(String var1) {
      gv var2 = (gv)dj.j(this.hl).getSelectedItem();
      if (var2 == null) {
         return "";
      } else {
         double var3 = var2.dH();

         try {
            double var5 = hf.a(var1, 0.0D, 1000.0D);
            if (var5 != var3) {
               var2.f(var5);
            }

            return Double.toString(var5);
         } catch (RuntimeException var7) {
            return Double.toString(var3);
         }
      }
   }
}
