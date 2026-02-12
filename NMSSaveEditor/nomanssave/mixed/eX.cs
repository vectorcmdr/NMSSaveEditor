using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

public class eX : IOException {
   int kF;
   int kG;

   eX(string var1) {
      this(var1, 1, 0);
   }

   eX(string var1, int var2, int var3) {
      base(var1);
      this.kF = var2;
      this.kG = var3;
   }

   eX(string var1, IOException var2, int var3, int var4) {
      base(var1, var2);
      this.kF = var3;
      this.kG = var4;
   }

   public int getLineNumber() {
      return this.kF;
   }

   public int bD() {
      return this.kG;
   }
}

}
