package nomanssave;

class be extends G {
   // $FF: synthetic field
   final bd dP;

   be(bd var1) {
      this.dP = var1;
   }

   protected String g(String var1) {
      if (bd.a(this.dP) == null) {
         return "";
      } else {
         var1 = var1.trim();
         if (!var1.equals(bd.a(this.dP).getName())) {
            bd.a(this.dP).setName(var1);
            bd.b(this.dP).setText(var1);
         }

         return var1;
      }
   }
}
