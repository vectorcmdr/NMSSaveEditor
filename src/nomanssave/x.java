package nomanssave;

import java.awt.EventQueue;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

class x extends Thread {
   // $FF: synthetic field
   final Application aZ;
   // $FF: synthetic field
   private final boolean ba;

   x(Application var1, boolean var2) {
      this.aZ = var1;
      this.ba = var2;
   }

   public void run() {
      try {
         long var1 = Math.round((double)Runtime.getRuntime().totalMemory() / 1000000.0D);
         long var3 = Math.round((double)Runtime.getRuntime().maxMemory() / 1000000.0D);
         hc.debug("Mem Usage: " + var1 + "/" + var3 + " MB");
         URL var5 = new URL("https://github.com/goatfungus/NMSSaveEditor/raw/master/VERSION.txt");
         URLConnection var6 = var5.openConnection();
         int var7 = var6.getContentLength();
         InputStream var8 = var6.getInputStream();
         byte[] var9 = new byte[var7];

         int var10;
         for(int var11 = 0; (var10 = var8.read(var9, var11, var7)) > 0; var7 -= var10) {
            var11 += var10;
         }

         if (var7 > 0) {
            throw new IOException("short read");
         }

         String var12 = new String(var9, 0, var9.length);
         if (var12.endsWith("\r\n")) {
            var12 = var12.substring(0, var12.length() - 2);
         } else if (var12.endsWith("\n")) {
            var12 = var12.substring(0, var12.length() - 1);
         }

         hc.debug("Latest version: \"" + var12 + "\"");
         hc.debug("Current version: \"1.19.12\"");
         if (!"1.19.12".equals(var12)) {
            EventQueue.invokeLater(new y(this, this.ba));
         }
      } catch (IOException var13) {
      }

   }

   // $FF: synthetic method
   static Application a(x var0) {
      return var0.aZ;
   }
}
