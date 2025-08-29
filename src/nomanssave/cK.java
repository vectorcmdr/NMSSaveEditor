package nomanssave;

import javax.swing.JFileChooser;

public class cK extends JFileChooser {
  private static cK gk = null;
  
  private static final String name = "JSON File";
  
  public static cK aA() {
    if (gk == null)
      gk = new cK(); 
    return gk;
  }
  
  private cK() {
    setFileSelectionMode(0);
    setAcceptAllFileFilterUsed(false);
    setFileView(new cL(this));
    setFileFilter(new cM(this));
    setDialogTitle("Choose JSON File");
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\cK.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */