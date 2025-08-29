package nomanssave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Stream;

class aF implements ActionListener {
  aF(aD paramaD) {}
  
  public void actionPerformed(ActionEvent paramActionEvent) {
    String str = aH.getProperty("LookAndFeel");
    aI aI1 = Stream.<aI>of(aI.values()).filter(paramaI -> paramaI.name().equalsIgnoreCase(paramString)).findFirst().orElse(aI.cN);
    aI aI2 = (aI)aD.a(this.cB).getSelectedItem();
    aD.a(this.cB, false);
    if (aI2 == null) {
      if (aI1 != null) {
        aH.setProperty("LookAndFeel", null);
        aD.a(this.cB, true);
      } 
    } else if (aI1 == null || aI1 != aI2) {
      aH.setProperty("LookAndFeel", aI2.name());
      aD.a(this.cB, true);
    } 
    double d = Double.parseDouble(aD.b(this.cB).getText());
    if (d != aH.a("InventoryScaling", 1.0D)) {
      aH.b("InventoryScaling", d);
      aD.a(this.cB, true);
    } 
    this.cB.setVisible(false);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\aF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */