package nomanssave;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

class B extends WindowAdapter {
  B(Application paramApplication) {}
  
  public void windowClosing(WindowEvent paramWindowEvent) {
    if (Application.i(this.aZ) || Application.j(this.aZ)) {
      int i = JOptionPane.showConfirmDialog(Application.h(this.aZ), "Save data before closing?", "Save", 0);
      if (i == 0) {
        if (Application.i(this.aZ))
          Application.k(this.aZ); 
        if (Application.j(this.aZ))
          Application.l(this.aZ); 
      } 
    } 
    if (aH.T())
      aH.U(); 
    Application.h(this.aZ).dispose();
  }
  
  public void windowDeactivated(WindowEvent paramWindowEvent) {
    Application.e(this.aZ, true);
  }
  
  public void windowActivated(WindowEvent paramWindowEvent) {
    Application.e(this.aZ, false);
    Application.m(this.aZ);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\B.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */