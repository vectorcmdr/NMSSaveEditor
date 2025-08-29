package nomanssave;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class X extends JPanel {
  private JComboBox bG;
  
  private JComboBox bH;
  
  private G bI;
  
  private G bJ;
  
  private G bK;
  
  private G bL;
  
  private G bM;
  
  private JCheckBox bN;
  
  private cN bO;
  
  private cN bP;
  
  private JButton bQ;
  
  private JButton bR;
  
  private JButton bS;
  
  private gj[] bT;
  
  X(Application paramApplication) {
    GridLayout gridLayout = new GridLayout(1, 3);
    setLayout(gridLayout);
    ba ba = new ba(new int[] { aH.cJ, 0 });
    add(ba);
    add(new JPanel());
    add(new JPanel());
    this.bG = new JComboBox();
    this.bG.setModel(new Y(this));
    ba.a("Companion", true, this.bG);
    this.bH = new JComboBox();
    this.bH.setModel(new ab(this));
    this.bH.setEnabled(false);
    ba.a("Type", this.bH);
    this.bI = new ac(this);
    ba.a("Name", this.bI);
    this.bJ = new ad(this);
    ba.a("Creature Seed", this.bJ);
    this.bK = new ae(this);
    ba.a("Secondary Seed", this.bK);
    this.bL = new af(this);
    ba.a("Species Seed", this.bL);
    this.bM = new ag(this);
    ba.a("Genus Seed", this.bM);
    this.bN = new JCheckBox("Predator");
    this.bN.setEnabled(false);
    this.bN.addActionListener(new ah(this));
    ba.a((String)null, this.bN);
    this.bO = new cN(gi.class);
    this.bO.a(paramString -> {
          gj gj1 = (gj)this.bG.getSelectedItem();
          if (gj1 != null)
            gj1.ae(paramString); 
        });
    ba.a("Biome", this.bO);
    this.bP = new cN(gk.class);
    this.bP.a(paramString -> {
          gj gj1 = (gj)this.bG.getSelectedItem();
          if (gj1 != null)
            gj1.af(paramString); 
        });
    ba.a("Type", this.bP);
    ba.Y();
    JPanel jPanel = new JPanel();
    this.bQ = new JButton("Delete");
    this.bQ.addActionListener(new ai(this, paramApplication));
    jPanel.add(this.bQ);
    this.bR = new JButton("Export");
    this.bR.addActionListener(new Z(this, paramApplication));
    jPanel.add(this.bR);
    this.bS = new JButton("Import");
    this.bS.addActionListener(new aa(this, paramApplication));
    jPanel.add(this.bS);
    ba.a(jPanel);
  }
  
  void a(gj[] paramArrayOfgj) {
    this.bT = paramArrayOfgj;
    if (paramArrayOfgj.length == 0) {
      this.bG.setSelectedIndex(-1);
    } else {
      this.bG.setSelectedIndex(0);
    } 
    this.bG.updateUI();
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\X.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */