using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace NMSSaveEditor
{

#if PORT_COMPLETE


public class eX : IOException {
   public int kF;
   public int kG;

   public eX(string var1) {
      this(var1, 1, 0);
   }

   public eX(string var1, int var2, int var3) {
      base(var1);
      this.kF = var2;
      this.kG = var3;
   }

   public eX(string var1, IOException var2, int var3, int var4) {
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


#else

public class eX
{
   public eX() { }
   public eX(params object[] args) { }
   public int kF = 0;
   public int kG = 0;
   public int getLineNumber() { return 0; }
   public int bD() { return 0; }
}

#endif

}
