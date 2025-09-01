package nomanssave;

class aM extends G {
   // $FF: synthetic field
   final aJ dj;

   aM(aJ var1) {
      this.dj = var1;
   }

   protected String g(String var1) {
      if (aJ.a(this.dj) == null) {
         return "";
      } else {
         int var2 = aJ.a(this.dj).dO();

         try {
            int var3 = hf.b(var1, 0, 100);
            if (var3 != var2) {
               aJ.a(this.dj).aD(var3);
            }

            return Integer.toString(var3);
         } catch (RuntimeException var4) {
            return Integer.toString(var2);
         }
      }
   }
}
