package nomanssave;

import java.io.File;
import javax.swing.filechooser.FileView;

class cL extends FileView {
  cL(cK paramcK) {}
  
  public String getName(File paramFile) {
    String str = paramFile.getName();
    return str.endsWith(".json") ? str.substring(0, str.length() - 5) : str;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\cL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */