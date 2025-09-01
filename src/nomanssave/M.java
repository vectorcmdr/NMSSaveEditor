package nomanssave;

class M extends G {
   // $FF: synthetic field
   final I bt;

   M(I var1) {
      this.bt = var1;
   }

   protected String g(String var1) {
      gf var2 = (gf)I.j(this.bt).getSelectedItem();
      if (var2 == null) {
         return "";
      } else {
         var1 = var1.trim();
         if (!var1.equals(var2.getName())) {
            var2.setName(var1);
            I.f(this.bt).setText(var1);
         }

         return var1;
      }
   }
}
