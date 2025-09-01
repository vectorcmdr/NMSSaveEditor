package nomanssave;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class gX extends FilterInputStream {
   private ha sa;
   private int sb;

   public gX(InputStream var1, byte[] var2) {
      super(var1);
      int var3 = 255 & var2[4] | (255 & var2[5]) << 8 | (255 & var2[6]) << 16 | (255 & var2[7]) << 24;
      int var4 = 255 & var2[8] | (255 & var2[9]) << 8 | (255 & var2[10]) << 16 | (255 & var2[11]) << 24;
      this.sa = new ha(new gY(this, var3, (gY)null), var4);
      this.sb = 1;
   }

   public int getFrameCount() {
      return this.sb;
   }

   private boolean ej() {
      byte[] var1 = new byte[16];
      int var2 = this.in.read(var1, 0, 16);
      if (var2 < 0) {
         this.sa = null;
         return false;
      } else if (var2 < 16) {
         throw new IOException("Short read " + var2);
      } else if ((255 & var1[0]) == 229 && (255 & var1[1]) == 161 && (255 & var1[2]) == 237 && (255 & var1[3]) == 254) {
         int var3 = 255 & var1[4] | (255 & var1[5]) << 8 | (255 & var1[6]) << 16 | (255 & var1[7]) << 24;
         int var4 = 255 & var1[8] | (255 & var1[9]) << 8 | (255 & var1[10]) << 16 | (255 & var1[11]) << 24;
         this.sa = new ha(new gY(this, var3, (gY)null), var4);
         ++this.sb;
         return true;
      } else {
         throw new IOException("Invalid header");
      }
   }

   public int read() {
      return this.sa != null && (this.sa.available() != 0 || this.ej()) ? this.sa.read() : -1;
   }

   public int read(byte[] var1) {
      return this.read(var1, 0, var1.length);
   }

   public int read(byte[] var1, int var2, int var3) {
      return this.sa != null && (this.sa.available() != 0 || this.ej()) ? this.sa.read(var1, var2, var3) : -1;
   }

   // $FF: synthetic method
   static InputStream a(gX var0) {
      return var0.in;
   }
}
