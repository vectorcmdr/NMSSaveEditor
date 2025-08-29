package nomanssave;

import java.io.File;
import javax.swing.Icon;
import javax.swing.filechooser.FileView;

class cU extends FileView {
  cU(cT paramcT) {}
  
  public Icon getIcon(File paramFile) {
    String str = paramFile.getName();
    return str.endsWith(".sh0") ? cT.as() : super.getIcon(paramFile);
  }
  
  public String getName(File paramFile) {
    String str = paramFile.getName();
    return str.endsWith(".sh0") ? str.substring(0, str.length() - 4) : str;
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\cU.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */