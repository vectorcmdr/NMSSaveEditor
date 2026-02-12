using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Windows.Forms;

namespace NMSSaveEditor
{

public class y : Runnable {
   x bb;
   public bool ba;

   public y(x var1, bool var2) {
      this.bb = var1;
      this.ba = var2;
   }

   public void run() {
      string var1 = "A newer version of the save editor is available.\n";
      if (!this.ba) {
         var1 = var1 + "Please visit https://github.com/goatfungus/NMSSaveEditor to download the latest release.";
         0 /* showOptionDialog(Application.h(x.a(this.bb) */), var1, "New Version Available", 0, 1, (Icon)null, new object[]{"OK"}, (object)null);
      } else {
         var1 = var1 + "Would you like to download and install? (will require app restart)";
         int var2 = MessageBox.Show(Application.h(x.a(this.bb)), var1, "New Version Available", 0);
         if (var2 == 0) {
            Application.h(x.a(this.bb)).Dispose();
            hc.info("Starting download...");
            FileInfo var3 = new FileInfo("~NMSSaveEditor.dl");

            try {
               URL var4 = new URL("https://github.com/goatfungus/NMSSaveEditor/raw/master/NMSSaveEditor.jar");
               URLConnection var5 = var4.openConnection();
               int var6 = var5.getContentLength();
               Stream var7 = var5.getInputStream();
               FileStream var8 = new FileStream(var3);

               try {
                  int var10;
                  for(byte[] var9 = new byte[4096]; (var10 = var7.read(var9)) > 0; var6 -= var10) {
                     var8.Write(var9, 0, var10);
                  }

                  if (var6 != 0) {
                     throw new IOException("invalid file size");
                  }
               } finally {
                  var8.Close();
               }

               hc.info("Restarting editor...");
               Environment.Exit(2);
            } catch (IOException var15) {
               var15.printStackTrace();
               var3.Delete();
               Environment.Exit(1);
            }
         }
      }

   }
}

}
