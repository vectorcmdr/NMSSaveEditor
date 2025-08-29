package nomanssave;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class dN extends em {
  private static final int cV = 500;
  
  private static final int cW = 200;
  
  private static final double gX = 1000.0D;
  
  private static final double hI = 1000.0D;
  
  private static final double dE = 1000.0D;
  
  private static final double hJ = 1000.0D;
  
  private JComboBox hK = new JComboBox();
  
  private G hL;
  
  private cN hM;
  
  private cN hN;
  
  private G hO;
  
  private JCheckBox hP;
  
  private JButton bQ;
  
  private JButton bR;
  
  private JButton bS;
  
  private G hQ;
  
  private G hR;
  
  private G hS;
  
  private G hT;
  
  private G hU;
  
  private G hV;
  
  private bO hW;
  
  private gH[] hX;
  
  private gC hY;
  
  dN(Application paramApplication) {
    this.hK.setModel(new dO(this, paramApplication));
    a("Ship", true, this.hK);
    this.hL = new dT(this);
    a("Name", this.hL);
    this.hM = new cN(gL.class);
    this.hM.a(paramString -> {
          gH gH1 = (gH)this.hK.getModel().getSelectedItem();
          if (gH1 != null) {
            gL gL = gL.aw(paramString);
            int i = JOptionPane.showConfirmDialog(paramApplication.g(), "You are about to change a ship type to " + ((gL == null) ? "Unknown" : gL.toString()) + ". Are you sure?\nNOTE: Any incompatible technology installed on the ship will be deleted.", "Change Ship Type", 0);
            if (i != 0) {
              this.hM.m(gH1.cT());
              return;
            } 
            gH1.ag(paramString);
            this.hW.a(gH1.cC());
            this.hK.updateUI();
          } 
        });
    a("Type", this.hM);
    this.hN = new cN(gN.class);
    this.hN.a(paramString -> {
          gH gH1 = (gH)this.hK.getModel().getSelectedItem();
          if (gH1 != null)
            gH1.aj(paramString); 
        });
    a("Class", this.hN);
    this.hO = new dU(this);
    a("Seed", this.hO);
    this.hP = new JCheckBox("Use Old Colours");
    this.hP.setEnabled(false);
    this.hP.addActionListener(new dV(this, paramApplication));
    a((String)null, this.hP);
    k("Base Stats");
    this.hQ = new dW(this);
    a("Health", this.hQ);
    this.hR = new dX(this);
    a("Shield", this.hR);
    this.hS = new dY(this);
    a("Damage", this.hS);
    this.hT = new dZ(this);
    a("Shields", this.hT);
    this.hU = new ea(this);
    a("Hyperdrive", this.hU);
    this.hV = new dP(this);
    a("Maneuverability", this.hV);
    Y();
    JPanel jPanel = new JPanel();
    this.bQ = new JButton("Delete Ship");
    this.bQ.addActionListener(new dQ(this, paramApplication));
    jPanel.add(this.bQ);
    this.bR = new JButton("Export");
    this.bR.addActionListener(new dR(this, paramApplication));
    jPanel.add(this.bR);
    this.bS = new JButton("Import");
    this.bS.addActionListener(new dS(this, paramApplication));
    jPanel.add(this.bS);
    a(jPanel);
    this.hW = new bO(paramApplication);
    b(this.hW);
  }
  
  void w() {
    for (byte b = 0; b < this.hX.length; b++) {
      this.hX[b].cC().stream().forEach(paramgt -> {
            if (paramgt.dt())
              hc.info(paramgt + ": technology recharged"); 
            this.hW.a(paramgt);
          });
    } 
  }
  
  void x() {
    for (byte b = 0; b < this.hX.length; b++) {
      this.hX[b].cC().stream().forEach(paramgt -> {
            if (paramgt.du())
              hc.info(paramgt + ": items refilled"); 
            this.hW.a(paramgt);
          });
    } 
  }
  
  void y() {
    for (byte b = 0; b < this.hX.length; b++) {
      this.hX[b].cC().stream().forEach(paramgt -> {
            if (paramgt.dv())
              hc.info(paramgt + ": all slots enabled"); 
            this.hW.a(paramgt);
          });
    } 
  }
  
  void z() {
    for (byte b = 0; b < this.hX.length; b++) {
      this.hX[b].cC().stream().forEach(paramgt -> {
            if (paramgt.ds())
              hc.info(paramgt + ": all slots repaired"); 
            this.hW.a(paramgt);
          });
    } 
  }
  
  void A() {
    for (byte b = 0; b < this.hX.length; b++) {
      this.hX[b].cC().stream().forEach(paramgt -> {
            if (paramgt.dl())
              hc.info(paramgt + ": inventory expanded"); 
            this.hW.a(paramgt);
          });
    } 
  }
  
  void a(gt paramgt) {
    this.hW.a(paramgt);
  }
  
  gH[] aO() {
    return this.hX;
  }
  
  void a(gH[] paramArrayOfgH, gC paramgC) {
    this.hX = paramArrayOfgH;
    this.hY = paramgC;
    if (paramArrayOfgH.length == 0) {
      this.hK.setSelectedIndex(-1);
    } else {
      byte b = (paramgC == null) ? 0 : paramgC.dV();
      if (b >= paramArrayOfgH.length)
        b = 0; 
      this.hK.setSelectedIndex(b);
    } 
    if (paramgC == null) {
      this.hQ.setText("");
      this.hR.setText("");
    } else {
      this.hQ.setText(Long.toString(paramgC.dM()));
      this.hR.setText(Long.toString(paramgC.dN()));
    } 
    this.hK.updateUI();
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\dN.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */