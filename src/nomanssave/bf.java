package nomanssave;

class bf extends G {
   // $FF: synthetic field
   final bd dP;

   bf(bd var1) {
      this.dP = var1;
   }

   protected String g(String var1) {
      if (bd.a(this.dP) == null) {
         return "";
      } else {
         try {
            var1 = var1.trim();
            if (!var1.equals(bd.a(this.dP).cU())) {
               bd.a(this.dP).ah(var1);
               bd.c(this.dP).setText(var1);
            }

            return var1;
         } catch (RuntimeException var3) {
            return bd.a(this.dP).cU();
         }
      }
   }
}
