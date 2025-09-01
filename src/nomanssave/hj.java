package nomanssave;

import java.io.IOException;
import java.util.Arrays;

class hj extends Thread {
   final long sK;
   String sL;

   hj(long var1) {
      this.sK = var1;
      this.sL = aH.getProperty("KnownPlayers." + var1);
      hi.ep().put(var1, this);
      this.start();
   }

   public void run() {
      String var1;
      try {
         var1 = hi.j(this.sK);
      } catch (IOException var7) {
         hc.a("Steam lookup error", var7);
         var1 = null;
      }

      synchronized(hi.ep()) {
         if (var1 != null) {
            Long[] var3 = (Long[])aH.a("SteamIDs", Long.class);
            boolean var4 = Arrays.asList(var3).stream().anyMatch((var1x) -> {
               return var1x.equals(this.sK);
            });
            if (!var4) {
               Long[] var5 = new Long[var3.length + 1];
               System.arraycopy(var3, 0, var5, 0, var3.length);
               var5[var3.length] = this.sK;
               aH.a("SteamIDs", (Object[])var5);
            }

            if (!var1.equals(this.sL)) {
               aH.setProperty("KnownPlayers." + this.sK, var1);
               this.sL = var1;
            }
         }

      }
   }
}
