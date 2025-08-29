package nomanssave;

import java.io.File;
import javax.swing.filechooser.FileFilter;

class cV extends FileFilter {
  cV(cT paramcT) {}
  
  public String getDescription() {
    return "Ship Export File";
  }
  
  public boolean accept(File paramFile) {
    return paramFile.isDirectory() ? (!paramFile.isHidden()) : paramFile.getName().endsWith(".sh0");
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\cV.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */