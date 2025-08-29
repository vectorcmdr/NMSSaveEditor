package nomanssave;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class gR {
  private static Map rO = new HashMap<>();
  
  public static eY az(String paramString) {
    eY eY = null;
    if (rO.containsKey(paramString)) {
      eY = (eY)rO.get(paramString);
    } else {
      InputStream inputStream = Application.class.getResourceAsStream("templates/" + paramString + ".json");
      if (inputStream != null)
        try {
          byte[] arrayOfByte = hk.g(inputStream);
          eY = ff.b(arrayOfByte);
        } catch (IOException iOException) {
          hc.a("Cannot load template: " + paramString, iOException);
        }  
      rO.put(paramString, eY);
    } 
    return (eY == null) ? null : eY.bE();
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\gR.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */