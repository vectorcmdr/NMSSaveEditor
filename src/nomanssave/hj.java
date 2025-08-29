package nomanssave;

import java.io.IOException;
import java.util.Arrays;

class hj extends Thread {
  final long sH;
  
  String sI;
  
  hj(long paramLong) {
    this.sH = paramLong;
    this.sI = aH.getProperty("KnownPlayers." + paramLong);
    hi.ep().put(Long.valueOf(paramLong), this);
    start();
  }
  
  public void run() {
    String str;
    try {
      str = hi.j(this.sH);
    } catch (IOException iOException) {
      hc.a("Steam lookup error", iOException);
      str = null;
    } 
    synchronized (hi.ep()) {
      if (str != null) {
        Long[] arrayOfLong = (Long[])aH.a("SteamIDs", Long.class);
        boolean bool = Arrays.<Long>asList(arrayOfLong).stream().anyMatch(paramLong -> paramLong.equals(Long.valueOf(this.sH)));
        if (!bool) {
          Long[] arrayOfLong1 = new Long[arrayOfLong.length + 1];
          System.arraycopy(arrayOfLong, 0, arrayOfLong1, 0, arrayOfLong.length);
          arrayOfLong1[arrayOfLong.length] = Long.valueOf(this.sH);
          aH.a("SteamIDs", (Object[])arrayOfLong1);
        } 
        if (!str.equals(this.sI)) {
          aH.setProperty("KnownPlayers." + this.sH, str);
          this.sI = str;
        } 
      } 
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\hj.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */