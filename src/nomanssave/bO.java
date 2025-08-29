package nomanssave;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.util.Collections;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class bO extends JPanel implements eo {
  private static final Color eE = Color.GRAY;
  
  private static final Color eF = new Color(255, 240, 240);
  
  private static final Color eG = new Color(255, 255, 240);
  
  private static final Color eH = new Color(240, 255, 250);
  
  private static final Color eI = new Color(240, 250, 255);
  
  private static final Color eJ = new Color(240, 255, 255);
  
  public static final Color eK = Color.WHITE;
  
  private static final Color eL = Color.BLACK;
  
  private static final Color eM = Color.YELLOW;
  
  private static final Color eN = Color.RED;
  
  public static final Color eO = Color.BLACK;
  
  public static final Border eP = BorderFactory.createLineBorder(eL, 1);
  
  private static final Border eQ = BorderFactory.createCompoundBorder(eP, BorderFactory.createLineBorder(eM, 2));
  
  private final Application eR;
  
  private final JPanel eS;
  
  private final JComboBox eT;
  
  private final JButton eU;
  
  private List eV;
  
  private gt eW;
  
  bO(Application paramApplication) {
    this.eR = paramApplication;
    setLayout(new BorderLayout());
    JPanel jPanel = new JPanel();
    jPanel.setLayout(new FlowLayout());
    this.eS = new JPanel();
    this.eS.setLayout(new GridBagLayout());
    int i = UIManager.getInt("Inventory.gridSize");
    setPreferredSize(new Dimension(i * 10 + 20, i * 8 + 50));
    add(jPanel, "North");
    JScrollPane jScrollPane = new JScrollPane();
    jScrollPane.setViewportView(this.eS);
    jScrollPane.setBorder(new LineBorder(eL));
    add(jScrollPane, "Center");
    this.eV = Collections.emptyList();
    this.eT = new JComboBox();
    this.eT.setVisible(false);
    this.eT.setModel(new bP(this));
    this.eT.setRenderer(new bQ(this));
    jPanel.add(this.eT);
    this.eU = new JButton("Resize Inventory");
    this.eU.setVisible(false);
    this.eU.addActionListener(new bR(this));
    jPanel.add(this.eU);
    en.a(this);
    UIManager.addPropertyChangeListener(paramPropertyChangeEvent -> {
          if ("lookAndFeel".equals(paramPropertyChangeEvent.getPropertyName()))
            EventQueue.invokeLater(this::af); 
        });
  }
  
  public void a(boolean paramBoolean) {
    this.eU.setVisible((this.eW == null) ? false : (!(!paramBoolean && !this.eW.dk())));
    boolean bool = (this.eW == null) ? false : ((!paramBoolean && !this.eW.dp()) ? false : true);
    synchronized (this.eS.getTreeLock()) {
      for (byte b = 0; b < this.eS.getComponentCount(); b++) {
        Component component = this.eS.getComponent(b);
        if (component instanceof bS) {
          bS bS = (bS)component;
          bS.b(bS).setEnabled(bool);
          bS.g(bS).setEnabled(bool);
        } 
      } 
    } 
  }
  
  void a(gt paramgt) {
    if (this.eW == paramgt)
      af(); 
  }
  
  void w() {
    this.eV.stream().forEach(paramgt -> {
          if (paramgt.dt() && this.eW == paramgt)
            af(); 
        });
  }
  
  void x() {
    this.eV.stream().forEach(paramgt -> {
          if (paramgt.du() && this.eW == paramgt)
            af(); 
        });
  }
  
  void y() {
    this.eV.stream().forEach(paramgt -> {
          if (paramgt.dp() && paramgt.dv() && this.eW == paramgt)
            af(); 
        });
  }
  
  void z() {
    this.eV.stream().forEach(paramgt -> {
          if (paramgt.dq() && paramgt.ds() && this.eW == paramgt)
            af(); 
        });
  }
  
  void A() {
    this.eV.stream().forEach(paramgt -> {
          if (paramgt.dk() && paramgt.dl() && this.eW == paramgt)
            af(); 
        });
  }
  
  void ae() {
    int i = this.eT.getSelectedIndex();
    if (i >= 0) {
      this.eW = this.eV.get(i);
      af();
    } 
  }
  
  private void af() {
    synchronized (this.eS.getTreeLock()) {
      this.eS.removeAll();
      if (this.eW != null) {
        int i = UIManager.getInt("Inventory.gridSize");
        Dimension dimension = new Dimension(i, i);
        for (byte b = 0; b < this.eW.getHeight(); b++) {
          for (byte b1 = 0; b1 < this.eW.getWidth(); b1++) {
            bS bS = new bS(this, b1, b, null);
            bS.setMinimumSize(dimension);
            bS.setMaximumSize(dimension);
            bS.setPreferredSize(dimension);
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.fill = 1;
            gridBagConstraints.insets = new Insets(-1, -1, 0, 0);
            gridBagConstraints.gridx = b1;
            gridBagConstraints.gridy = b;
            this.eS.add(bS, gridBagConstraints);
          } 
        } 
      } 
    } 
    this.eS.revalidate();
    this.eS.updateUI();
  }
  
  void a(List paramList) {
    this.eV = paramList;
    this.eW = null;
    this.eT.updateUI();
    if (this.eV.size() == 0) {
      this.eT.setVisible(false);
      this.eU.setVisible(false);
      af();
    } else {
      this.eT.setVisible((this.eV.size() != 1));
      this.eU.setVisible(false);
      this.eT.setSelectedIndex(0);
    } 
  }
  
  private bS a(int paramInt1, int paramInt2) {
    synchronized (this.eS.getTreeLock()) {
      for (byte b = 0; b < this.eS.getComponentCount(); b++) {
        Component component = this.eS.getComponent(b);
        if (component instanceof bS) {
          bS bS = (bS)component;
          if (bS.h(bS) == paramInt1 && bS.i(bS) == paramInt2)
            return bS; 
        } 
      } 
    } 
    return null;
  }
  
  private void a(bS parambS) {
    ey ey = h.a(this, this.eW.dj());
    if (ey != null) {
      this.eW.a(bS.h(parambS), bS.i(parambS), ey);
      bS.c(parambS);
    } 
  }
  
  private void a(gu paramgu, bS parambS) {
    int i;
    ey ey = ey.d(paramgu.dz());
    if (ey == null) {
      if ("Product".equals(paramgu.getType())) {
        i = 512;
      } else if ("Substance".equals(paramgu.getType())) {
        i = 1024;
      } else {
        this.eR.c("Item details not found!");
        return;
      } 
    } else {
      i = gt.a(ey.bc());
    } 
    List<gt> list = this.eR.g(i);
    int j = list.indexOf(this.eW);
    int k = dd.a(this, list, j);
    if (k != j) {
      gt gt1 = list.get(k);
      if (this.eW.a(bS.h(parambS), bS.i(parambS), gt1)) {
        bS.c(parambS);
        this.eR.a(gt1);
      } 
    } 
  }
  
  private static String a(Object paramObject) {
    return (paramObject instanceof fg) ? "Archived Tech" : paramObject.toString();
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\bO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */