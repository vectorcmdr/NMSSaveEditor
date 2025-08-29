package nomanssave;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;

class fO implements FileFilter {
  fO(fN paramfN, ArrayList paramArrayList) {}
  
  public boolean accept(File paramFile) {
    Matcher matcher = fJ.cl().matcher(paramFile.getName());
    if (matcher.matches()) {
      byte b = (matcher.group(1).length() == 0) ? 0 : (Integer.parseInt(matcher.group(1)) - 1);
      if (b / 2 == this.mu.lR)
        try {
          this.me.add(new fL(fN.a(this.mu), paramFile.getName(), b));
        } catch (IOException iOException) {
          hc.a("Cannot load " + paramFile.getName(), iOException);
        }  
    } 
    return false;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\fO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */