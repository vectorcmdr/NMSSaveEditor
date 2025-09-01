package nomanssave;

class bv extends G {
   // $FF: synthetic field
   final bl er;

   bv(bl var1) {
      this.er = var1;
   }

   protected String g(String var1) {
      if (bl.b(this.er) < 0) {
         return "";
      } else {
         var1 = var1.trim();
         if (!var1.equals(bl.c(this.er)[bl.b(this.er)].getName())) {
            bl.c(this.er)[bl.b(this.er)].setName(var1);
            bl.j(this.er).setText(var1);
            bl.e(this.er).updateUI();
         }

         return var1;
      }
   }
}
