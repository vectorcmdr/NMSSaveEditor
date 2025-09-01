package nomanssave;

import java.io.IOException;

public class eX extends IOException {
   final int kF;
   final int kG;

   eX(String var1) {
      this(var1, 1, 0);
   }

   eX(String var1, int var2, int var3) {
      super(var1);
      this.kF = var2;
      this.kG = var3;
   }

   eX(String var1, IOException var2, int var3, int var4) {
      super(var1, var2);
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
