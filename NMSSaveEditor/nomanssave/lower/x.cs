using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;

namespace NMSSaveEditor
{



public class x : JavaThread {
   public Application aZ;
   public bool ba;

   public x(Application var1, bool var2) {
      this.aZ = var1;
      this.ba = var2;
   }

   public void run() {
      try {
         // PORT_TODO: long var1 = Math.Round((double)Process.GetCurrentProcess().totalMemory() / 1000000.0D);
         // PORT_TODO: long var3 = Math.Round((double)Process.GetCurrentProcess().maxMemory() / 1000000.0D);
         // PORT_TODO: hc.debug("Mem Usage: " + var1 + "/" + var3 + " MB");
         URL var5 = new URL("https://github.com/goatfungus/NMSSaveEditor/raw/master/VERSION.txt");
         URLConnection var6 = var5.openConnection();
         int var7 = var6.getContentLength();
         Stream var8 = var6.getInputStream();
         byte[] var9 = new byte[var7];

         int var10;
         for(int var11 = 0; (var10 = var8.read(var9, var11, var7)) > 0; var7 -= var10) {
            var11 += var10;
         }

         if (var7 > 0) {
            throw new IOException("short read");
         }

         // PORT_TODO: string var12 = new string(var9, 0, var9.Length);
         if (true) { // PORT_TODO: original condition had errors
            // PORT_TODO: var12 = var12.Substring(0, var12.Length - 2);
         // PORT_TODO: } else if (var12.EndsWith("\n")) {
      string var12 = null; // PORT_TODO: stub declaration
            // PORT_TODO: var12 = var12.Substring(0, var12.Length - 1);
         }

         // PORT_TODO: hc.debug("Latest version: \"" + var12 + "\"");
         hc.debug("Current version: \"1.19.14\"");
         if (true) { // PORT_TODO: original condition had errors
            // PORT_TODO: System.Windows.Forms.Application.Run(new y(this, this.ba));
         }
      } catch (IOException var13) {
      }

   }
   public static Application a(x var0) {
      return var0.aZ;
   }
}



}
