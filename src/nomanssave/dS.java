package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class dS implements ActionListener {
  dS(dN paramdN, Application paramApplication) {}
  
  public void actionPerformed(ActionEvent paramActionEvent) {
    gH gH = this.bv.h();
    if (gH != null) {
      gH[] arrayOfGH = new gH[(dN.a(this.ia)).length + 1];
      int i = -1;
      for (byte b = 0; b < (dN.a(this.ia)).length; b++) {
        if (dN.a(this.ia)[b].getIndex() < gH.getIndex()) {
          arrayOfGH[b] = dN.a(this.ia)[b];
        } else {
          arrayOfGH[b + 1] = dN.a(this.ia)[b];
          if (i < 0)
            i = b; 
        } 
      } 
      if (i < 0)
        i = (dN.a(this.ia)).length; 
      arrayOfGH[i] = gH;
      dN.a(this.ia, arrayOfGH);
      hc.info("Imported ship: " + gH.getIndex());
      dN.p(this.ia).setSelectedIndex(i);
      dN.p(this.ia).updateUI();
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\dS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */