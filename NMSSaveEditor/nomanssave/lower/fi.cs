using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class fi : StringReader {
   public int kF = 1;
   public int kG = 0;

   public fi(string var1) {
      base(var1);
   }

   public int bI() {
      int var1;
      do {
         if ((var1 = this.ReadByte()) < 0) {
            return -1;
         }
      } while(var1 == 32 || var1 == 13 || var1 == 10 || var1 == 9);

      return var1;
   }

   public int read() {
      int var1;
      try {
         var1 = base.ReadByte();
      } catch (IOException var3) {
         throw new eX("stream error", var3, this.kF, this.kG);
      }

      if (var1 == 10) {
         ++this.kF;
      }

      ++this.kG;
      return var1;
   }

   public int a(Predicate<object> var1) {
      try {
         this.mark(1);
         int var2 = base.ReadByte();
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
   public static int a(fi var0, Predicate<object> var1) {
      return var0.a(var1);
   }
}

}
