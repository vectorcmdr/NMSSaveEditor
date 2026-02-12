using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class hg {
   private static SecureRandom sv = new SecureRandom();
   private long sw;

   public static hg aB(string var0) {
      var0 = var0.Trim();
      if (!var0.StartsWith("0x")) {
         throw new Exception("Invalid seed: " + var0);
      } else {
         long var1 = Long.parseUnsignedLong(var0.Substring(2), 16);
         return new hg(var1);
      }
   }

   public static hg eo() {
      return new hg(sv.nextLong());
   }

   public hg(long var1) {
      this.sw = var1;
   }

   public string toString() {
      return "0x" + Long.toHexString(this.sw).ToUpper();
   }
}

}
