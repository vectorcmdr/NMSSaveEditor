package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class r implements ActionListener {
  r(p paramp) {}
  
  public void actionPerformed(ActionEvent paramActionEvent) {
    int[] arrayOfInt = p.b(this.I).getSelectedRows();
    p.a(this.I, new ArrayList());
    for (byte b = 0; b < arrayOfInt.length; b++) {
      int i = p.b(this.I).convertRowIndexToModel(arrayOfInt[b]);
      p.c(this.I).add((String)p.b(this.I).getModel().getValueAt(i, 3));
    } 
    this.I.setVisible(false);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\r.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */