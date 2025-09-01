package nomanssave;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

class he extends OutputStream {
   final PrintStream ss;
   final String st;
   final ByteArrayOutputStream su;

   he(PrintStream var1, String var2) {
      this.ss = var1;
      this.st = var2;
      this.su = new ByteArrayOutputStream();
   }

   public void write(int var1) {
      this.ss.write(var1);
      this.su.write(var1);
      if (var1 == 10) {
         if (hc.en() != null) {
            synchronized(hc.en()) {
               hc.en().write(this.st.getBytes());
               hc.en().write(this.su.toByteArray());
            }
         }

         this.su.reset();
      }

   }

   public void write(byte[] var1, int var2, int var3) {
      if (this.ss != null) {
         this.ss.write(var1, var2, var3);
      }

      for(int var4 = 0; var4 < var3; ++var4) {
         if (var1[var2 + var4] == 10) {
            this.su.write(var1, var2, var4 + 1);
            if (hc.en() != null) {
               synchronized(hc.en()) {
                  hc.en().write(this.st.getBytes());
                  hc.en().write(this.su.toByteArray());
               }
            }

            this.su.reset();
            var3 -= var4 + 1;
            var2 = var4 + 1;
            var4 = -1;
         }
      }

      this.su.write(var1, var2, var3);
   }

   public void flush() {
      if (this.su.size() > 0) {
         this.su.write(System.lineSeparator().getBytes());
         if (hc.en() != null) {
            synchronized(hc.en()) {
               hc.en().write(this.st.getBytes());
               hc.en().write(this.su.toByteArray());
            }
         }

         this.su.reset();
      }

   }
}
