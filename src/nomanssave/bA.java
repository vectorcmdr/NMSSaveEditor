package nomanssave;

class bA extends G {
   private final int index;
   // $FF: synthetic field
   final bl er;

   private bA(bl var1, int var2) {
      this.er = var1;
      this.index = var2;
   }

   protected String g(String var1) {
      if (bl.b(this.er) < 0) {
         return "";
      } else {
         int var2 = bl.c(this.er)[bl.b(this.er)].aq(this.index);

         try {
            int var3 = hf.b(var1, 0, 50);
            if (var3 != var2) {
               bl.c(this.er)[bl.b(this.er)].e(this.index, var3);
            }

            return Integer.toString(var3);
         } catch (RuntimeException var4) {
            return Integer.toString(var2);
         }
      }
   }

   // $FF: synthetic method
   bA(bl var1, int var2, bA var3) {
      this(var1, var2);
   }
}
