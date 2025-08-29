package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class P implements ActionListener {
  P(I paramI, Application paramApplication) {}
  
  public void actionPerformed(ActionEvent paramActionEvent) {
    gf gf = (gf)I.j(this.bt).getSelectedItem();
    if (gf == null)
      return; 
    List<gg> list = gf.cI();
    if (list.size() == 0) {
      this.bv.c("Cannot move base computer.\nPlease ensure that your base has a suitable Signal Booster / Blueprint Analyser / Beacon placed where you want your base computer to be.");
      return;
    } 
    int i;
    if ((i = cY.a(this.bv.g(), list)) < 0)
      return; 
    gg gg = list.get(i);
    hc.info("Attempting to swap base computer with " + gg.toString() + "...");
    if (gf.a(gg)) {
      hc.info("Base computer relocated: " + gf.getName());
    } else {
      hc.info("Base computer not moved.");
      this.bv.c("An error occurred while attempting to move base computer.");
    } 
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\P.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */