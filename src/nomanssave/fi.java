package nomanssave;

import java.io.IOException;
import java.io.StringReader;
import java.util.function.Predicate;

class fi extends StringReader {
   int kF = 1;
   int kG = 0;

   fi(String var1) {
      super(var1);
   }

   public int bI() {
      int var1;
      do {
         if ((var1 = this.read()) < 0) {
            return -1;
         }
      } while(var1 == 32 || var1 == 13 || var1 == 10 || var1 == 9);

      return var1;
   }

   public int read() {
      int var1;
      try {
         var1 = super.read();
      } catch (IOException var3) {
         throw new eX("stream error", var3, this.kF, this.kG);
      }

      if (var1 == 10) {
         ++this.kF;
      }

      ++this.kG;
      return var1;
   }

   private int a(Predicate var1) {
      try {
         this.mark(1);
         int var2 = super.read();
         if (var2 >= 0 && var1.test(var2)) {
            if (var2 == 10) {
               ++this.kF;
            }

            ++this.kG;
            return var2;
         } else {
            this.reset();
            return -1;
         }
      } catch (IOException var3) {
         throw new eX("stream error", var3, this.kF, this.kG);
      }
   }

   // $FF: synthetic method
   static int a(fi var0, Predicate var1) {
      return var0.a(var1);
   }
}
