package nomanssave;

import java.awt.EventQueue;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

class x extends Thread {
  x(Application paramApplication, boolean paramBoolean) {}
  
  public void run() {
    try {
      long l1 = Math.round(Runtime.getRuntime().totalMemory() / 1000000.0D);
      long l2 = Math.round(Runtime.getRuntime().maxMemory() / 1000000.0D);
      hc.debug("Mem Usage: " + l1 + "/" + l2 + " MB");
      URL uRL = new URL("https://github.com/goatfungus/NMSSaveEditor/raw/master/VERSION.txt");
      URLConnection uRLConnection = uRL.openConnection();
      int i = uRLConnection.getContentLength();
      InputStream inputStream = uRLConnection.getInputStream();
      byte[] arrayOfByte = new byte[i];
      int k = 0;
      int j;
      while ((j = inputStream.read(arrayOfByte, k, i)) > 0) {
        k += j;
        i -= j;
      } 
      if (i > 0)
        throw new IOException("short read"); 
      String str = new String(arrayOfByte, 0, arrayOfByte.length);
      if (str.endsWith("\r\n")) {
        str = str.substring(0, str.length() - 2);
      } else if (str.endsWith("\n")) {
        str = str.substring(0, str.length() - 1);
      } 
      hc.debug("Latest version: \"" + str + "\"");
      hc.debug("Current version: \"1.18.1\"");
      if (!"1.18.1".equals(str))
        EventQueue.invokeLater(new y(this, this.ba)); 
    } catch (IOException iOException) {}
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\x.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */