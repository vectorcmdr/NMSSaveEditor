package nomanssave;

class dM extends G {
   private final gG hH;
   // $FF: synthetic field
   final dE hE;

   private dM(dE var1, gG var2) {
      this.hE = var1;
      this.hH = var2;
   }

   protected String g(String var1) {
      gE var2 = (gE)dE.a(this.hE).getSelectedItem();
      if (var2 == null) {
         return "";
      } else {
         int var3 = var2.aq(this.hH.ordinal());

         try {
            int var4 = hf.b(var1, 0, this.hH.dY());
            if (var4 != var3) {
               var2.e(this.hH.ordinal(), var4);
            }

            return Integer.toString(var4);
         } catch (RuntimeException var5) {
            return Integer.toString(var3);
         }
      }
   }

   // $FF: synthetic method
   dM(dE var1, gG var2, dM var3) {
      this(var1, var2);
   }
}
