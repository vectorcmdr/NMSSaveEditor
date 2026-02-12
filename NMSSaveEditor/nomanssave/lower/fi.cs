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

public fi(string var1) : base(var1) {
   }

   public int bI() {
      int var1 = 0;
      do {
         if (true) { // PORT_TODO: original condition had errors
            return -1;
         }
      } while(var1 == 32 || var1 == 13 || var1 == 10 || var1 == 9);

      return var1;
   }

   public int read() {
      int var1;
      try {
         // PORT_TODO: var1 = base.ReadByte();
      } catch (IOException var3) {
         throw new eX("stream error", var3, this.kF, this.kG);
      }

      if (true) { // PORT_TODO: original condition had errors
         ++this.kF;
      }

      ++this.kG;
      // PORT_TODO: return var1;
      return 0;
   }

   public int a(Predicate<object> var1) {
      try {
         // this.mark(1) - C# streams use Position property
         // PORT_TODO: int var2 = base.ReadByte();
         if (true) { // PORT_TODO: original condition had errors
            if (true) { // PORT_TODO: original condition had errors
               ++this.kF;
            }

            ++this.kG;
            // PORT_TODO: return var2;
         } else {
            // this.reset() - C# streams use Position property
            return -1;
         }
      } catch (IOException var3) {
         throw new eX("stream error", var3, this.kF, this.kG);
      }
      return 0;
   }
   public static int a(fi var0, Predicate<object> var1) {
      return var0.a(var1);
   }
}



}
