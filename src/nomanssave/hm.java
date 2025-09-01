package nomanssave;

import java.io.FilterInputStream;
import java.io.InputStream;

public class hm extends FilterInputStream {
   private ha sa;
   private int sb;

   public hm(InputStream var1) {
      super(var1);
      byte[] var2 = new byte[8];
      hk.readFully(var1, var2);
      int var3 = 255 & var2[0] | (255 & var2[1]) << 8 | (255 & var2[2]) << 16 | (255 & var2[3]) << 24;
      int var4 = 255 & var2[4] | (255 & var2[5]) << 8 | (255 & var2[6]) << 16 | (255 & var2[7]) << 24;
      this.sa = new ha(new hn(this, var4, (hn)null), var3);
      this.sb = 1;
   }

   public int getFrameCount() {
      return this.sb;
   }

   private boolean ej() {
      byte[] var1 = new byte[8];
      hk.readFully(this.in, var1);
      int var2 = 255 & var1[0] | (255 & var1[1]) << 8 | (255 & var1[2]) << 16 | (255 & var1[3]) << 24;
      int var3 = 255 & var1[4] | (255 & var1[5]) << 8 | (255 & var1[6]) << 16 | (255 & var1[7]) << 24;
      this.sa = new ha(new hn(this, var3, (hn)null), var2);
      ++this.sb;
      return true;
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
   static InputStream a(hm var0) {
      return var0.in;
   }
}
