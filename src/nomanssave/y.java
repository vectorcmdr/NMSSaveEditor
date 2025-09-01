package nomanssave;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.Icon;
import javax.swing.JOptionPane;

class y implements Runnable {
   // $FF: synthetic field
   final x bb;
   // $FF: synthetic field
   private final boolean ba;

   y(x var1, boolean var2) {
      this.bb = var1;
      this.ba = var2;
   }

   public void run() {
      String var1 = "A newer version of the save editor is available.\n";
      if (!this.ba) {
         var1 = var1 + "Please visit https://github.com/goatfungus/NMSSaveEditor to download the latest release.";
         JOptionPane.showOptionDialog(Application.h(x.a(this.bb)), var1, "New Version Available", 0, 1, (Icon)null, new Object[]{"OK"}, (Object)null);
      } else {
         var1 = var1 + "Would you like to download and install? (will require app restart)";
         int var2 = JOptionPane.showConfirmDialog(Application.h(x.a(this.bb)), var1, "New Version Available", 0);
         if (var2 == 0) {
            Application.h(x.a(this.bb)).dispose();
            hc.info("Starting download...");
            File var3 = new File("~NMSSaveEditor.dl");

            try {
               URL var4 = new URL("https://github.com/goatfungus/NMSSaveEditor/raw/master/NMSSaveEditor.jar");
               URLConnection var5 = var4.openConnection();
               int var6 = var5.getContentLength();
               InputStream var7 = var5.getInputStream();
               FileOutputStream var8 = new FileOutputStream(var3);

               try {
                  int var10;
                  for(byte[] var9 = new byte[4096]; (var10 = var7.read(var9)) > 0; var6 -= var10) {
                     var8.write(var9, 0, var10);
                  }

                  if (var6 != 0) {
                     throw new IOException("invalid file size");
                  }
               } finally {
                  var8.close();
               }

               hc.info("Restarting editor...");
               System.exit(2);
            } catch (IOException var15) {
               var15.printStackTrace();
               var3.delete();
               System.exit(1);
            }
         }
      }

   }
}
