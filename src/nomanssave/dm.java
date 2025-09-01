package nomanssave;

class dm extends G {
   // $FF: synthetic field
   final dj hl;

   dm(dj var1) {
      this.hl = var1;
   }

   protected String g(String var1) {
      gv var2 = (gv)dj.j(this.hl).getSelectedItem();
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
