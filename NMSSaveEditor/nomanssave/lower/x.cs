using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using System.Globalization;

namespace NMSSaveEditor
{

public class x  {
   public Thread thread;
   // $FF: synthetic field
   public Application aZ;
   // $FF: synthetic field
   public bool ba;

   public x(Application var1, bool var2) {
      this.aZ = var1;
      this.ba = var2;
   }

   public void run() {
      try {
         long var1 = Math.Round((double)Runtime.getRuntime().totalMemory() / 1000000.0D);
         long var3 = Math.Round((double)Runtime.getRuntime().maxMemory() / 1000000.0D);
         hc.debug("Mem Usage: " + var1 + "/" + var3 + " MB");
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

         string var12 = new string(var9, 0, var9.Length);
         if (var12.EndsWith("\r\n")) {
            var12 = var12.Substring(0, var12.Length - 2);
         } else if (var12.EndsWith("\n")) {
            var12 = var12.Substring(0, var12.Length - 1);
         }

         hc.debug("Latest version: \"" + var12 + "\"");
         hc.debug("Current version: \"1.19.14\"");
         if (!"1.19.14".Equals(var12)) {
            JavaCompat.InvokeLater(new y(this, this.ba));
         }
      } catch (IOException var13) {
      }

   }

   // $FF: synthetic method
   public static Application a(x var0) {
      return var0.aZ;
   }
}

}
