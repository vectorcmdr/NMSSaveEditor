package nomanssave;

class K extends G {
   // $FF: synthetic field
   final I bt;

   K(I var1) {
      this.bt = var1;
   }

   protected String g(String var1) {
      gh var2 = (gh)I.d(this.bt).getSelectedItem();
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
