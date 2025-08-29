package nomanssave;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JOptionPane;

class y implements Runnable {
  y(x paramx, boolean paramBoolean) {}
  
  public void run() {
    String str = "A newer version of the save editor is available.\n";
    if (!this.ba) {
      str = String.valueOf(str) + "Please visit https://github.com/goatfungus/NMSSaveEditor to download the latest release.";
      JOptionPane.showOptionDialog(Application.h(x.a(this.bb)), str, "New Version Available", 0, 1, null, new Object[] { "OK" }, null);
    } else {
      str = String.valueOf(str) + "Would you like to download and install? (will require app restart)";
      int i = JOptionPane.showConfirmDialog(Application.h(x.a(this.bb)), str, "New Version Available", 0);
      if (i == 0) {
        Application.h(x.a(this.bb)).dispose();
        hc.info("Starting download...");
        File file = new File("~NMSSaveEditor.dl");
        try {
          URL uRL = new URL("https://github.com/goatfungus/NMSSaveEditor/raw/master/NMSSaveEditor.jar");
          URLConnection uRLConnection = uRL.openConnection();
          int j = uRLConnection.getContentLength();
          InputStream inputStream = uRLConnection.getInputStream();
          FileOutputStream fileOutputStream = new FileOutputStream(file);
          try {
            byte[] arrayOfByte = new byte[4096];
            int k;
            while ((k = inputStream.read(arrayOfByte)) > 0) {
              fileOutputStream.write(arrayOfByte, 0, k);
              j -= k;
            } 
            if (j != 0)
              throw new IOException("invalid file size"); 
          } finally {
            fileOutputStream.close();
          } 
          hc.info("Restarting editor...");
          System.exit(2);
        } catch (IOException iOException) {
          iOException.printStackTrace();
          file.delete();
          System.exit(1);
        } 
      } 
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\y.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */