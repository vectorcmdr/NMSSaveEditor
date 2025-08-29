package nomanssave;

import java.io.File;
import javax.swing.filechooser.FileFilter;

class cM extends FileFilter {
  cM(cK paramcK) {}
  
  public String getDescription() {
    return "JSON File";
  }
  
  public boolean accept(File paramFile) {
    return paramFile.isDirectory() ? (!paramFile.isHidden()) : paramFile.getName().endsWith(".json");
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\cM.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */