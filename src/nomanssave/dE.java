package nomanssave;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class dE extends JPanel {
  private ba hv;
  
  private ba hw;
  
  private JComboBox hx;
  
  private G hy;
  
  private G hz;
  
  private G[] ea;
  
  private JTable hA;
  
  private dt hB;
  
  private gE[] hC;
  
  dE(Application paramApplication) {
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[] { aH.cI };
    gridBagLayout.rowHeights = new int[1];
    gridBagLayout.columnWeights = new double[] { 0.0D, 0.0D, 1.0D };
    gridBagLayout.rowWeights = new double[] { 1.0D };
    setLayout(gridBagLayout);
    this.hv = new ba();
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.insets = new Insets(0, 0, 0, 0);
    gridBagConstraints.fill = 1;
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    add(this.hv, gridBagConstraints);
    this.hx = new JComboBox();
    this.hx.setModel(new dF(this));
    this.hv.a("Settlement", true, this.hx);
    this.hy = new dG(this);
    this.hv.a("Name", this.hy);
    this.hz = new dH(this);
    this.hv.a("Seed", this.hz);
    this.hv.Y();
    this.hv.k("Stats");
    this.ea = new G[(gG.values()).length];
    for (byte b = 0; b < this.ea.length; b++) {
      this.ea[b] = new dM(this, gG.values()[b], null);
      this.hv.a(gG.values()[b].toString(), this.ea[b]);
    } 
    this.hw = new ba();
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.insets = new Insets(0, 0, 0, 0);
    gridBagConstraints.fill = 1;
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    add(this.hw, gridBagConstraints);
    this.hw.k("Perks");
    JScrollPane jScrollPane = new JScrollPane();
    this.hw.a(jScrollPane);
    this.hA = new JTable();
    this.hA.setCellSelectionEnabled(false);
    this.hA.setModel(new dI(this));
    this.hA.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new dJ(this)));
    jScrollPane.setViewportView(this.hA);
    this.hB = new dt(paramApplication);
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.insets = new Insets(0, 0, 0, 0);
    gridBagConstraints.fill = 1;
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    add(this.hB, gridBagConstraints);
  }
  
  gE[] aN() {
    return this.hC;
  }
  
  void a(gE[] paramArrayOfgE) {
    if (paramArrayOfgE.length == 0) {
      this.hC = new gE[0];
      this.hx.setSelectedIndex(-1);
    } else {
      this.hC = paramArrayOfgE;
      this.hx.setSelectedIndex(0);
    } 
    this.hx.updateUI();
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\dE.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */