package nomanssave;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

class eD extends eE {
  private final String version;
  
  private eD(InputStream paramInputStream, String paramString) {
    super(null);
    this.version = paramString;
    ArrayList<String> arrayList = new ArrayList();
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(paramInputStream));
    try {
      String str;
      while ((str = bufferedReader.readLine()) != null) {
        try {
          if (str.length() == 0)
            continue; 
          int i = str.indexOf("\t");
          if (i < 0) {
            hc.debug("Mapping not available: " + str);
            arrayList.add(str);
            continue;
          } 
          String str1 = str.substring(0, i);
          String str2 = str.substring(i + 1, str.length());
          eF eF;
          if ((eF = t(str1)) != null) {
            if (str2.equals(eF.name)) {
              hc.debug("Mapping duplicated: " + str1);
              continue;
            } 
            throw new IOException("Mapping error: " + str1);
          } 
          if ((eF = u(str2)) != null) {
            if (str1.equals(eF.key)) {
              hc.debug("Reverse duplicated: " + str2);
              continue;
            } 
            throw new IOException("Reverse error: " + str2);
          } 
          add(str1, str2);
        } catch (RuntimeException runtimeException) {
          hc.a("Ignoring: " + str, runtimeException);
        } 
      } 
    } finally {
      bufferedReader.close();
    } 
    for (String str : arrayList) {
      if (t(str) != null)
        throw new IOException("Mapping error: " + str); 
      if (u(str) != null)
        throw new IOException("Reverse error: " + str); 
      add(str, str);
    } 
  }
  
  public String toString() {
    return this.version;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\eD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */