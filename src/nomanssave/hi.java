package nomanssave;

import java.io.EOFException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public class hi {
  private static final String sF = "9710BD8FCF192837DC6DEF6037AB2837";
  
  private static final HashMap sG = new HashMap<>();
  
  public static String h(long paramLong) {
    hj hj;
    synchronized (sG) {
      if (sG.containsKey(Long.valueOf(paramLong))) {
        hj = (hj)sG.get(Long.valueOf(paramLong));
      } else {
        hj = new hj(paramLong);
      } 
    } 
    try {
      hj.join(500L);
    } catch (InterruptedException interruptedException) {}
    return hj.sI;
  }
  
  private static String i(long paramLong) {
    eY eY = aC("http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=9710BD8FCF192837DC6DEF6037AB2837&steamids=" + paramLong);
    eV eV = eY.d("response.players");
    if (eV == null || eV.size() == 0)
      return null; 
    for (byte b = 0; b < eV.size(); b++) {
      eY eY1 = eV.V(b);
      if (Long.toString(paramLong).equals(eY1.getValueAsString("steamid")))
        return eY1.getValueAsString("personaname"); 
    } 
    return null;
  }
  
  private static eY aC(String paramString) {
    URLConnection uRLConnection = (new URL(paramString)).openConnection();
    int i = uRLConnection.getContentLength();
    int j = 0;
    InputStream inputStream = uRLConnection.getInputStream();
    byte[] arrayOfByte = new byte[i];
    int k;
    while ((k = inputStream.read(arrayOfByte, j, i)) >= 0) {
      j += k;
      i -= k;
    } 
    if (i > 0)
      throw new EOFException(); 
    String str1 = uRLConnection.getContentEncoding();
    String str2 = new String(arrayOfByte, (str1 == null) ? "UTF-8" : str1);
    return eY.E(str2);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\hi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */