package nomanssave;

import javax.swing.JOptionPane;

class A implements Runnable {
  A(Application paramApplication, String paramString) {}
  
  public void run() {
    JOptionPane.showOptionDialog(Application.h(this.aZ), this.bc, "Warning", 0, 2, null, new Object[] { "OK" }, null);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\A.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */