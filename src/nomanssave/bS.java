package nomanssave;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import javax.swing.border.Border;

class bS extends JPanel {
  private final int x;
  
  private final int y;
  
  private JCheckBoxMenuItem eY;
  
  private JMenuItem eZ;
  
  private JMenuItem fa;
  
  private JMenuItem fb;
  
  private JCheckBoxMenuItem fc;
  
  private JMenuItem fd;
  
  private JMenuItem fe;
  
  private JMenuItem ff;
  
  private JMenuItem fg;
  
  private JMenuItem fh;
  
  private JMenuItem fi;
  
  private JMenuItem fj;
  
  private bS(bO parambO, int paramInt1, int paramInt2) {
    this.x = paramInt1;
    this.y = paramInt2;
    setLayout(new GridBagLayout());
    JPopupMenu jPopupMenu = new JPopupMenu();
    this.eY = new JCheckBoxMenuItem("Enabled");
    this.eY.addActionListener(new bT(this, paramInt1, paramInt2));
    this.eY.setEnabled(!(!bO.a(parambO).dp() && !en.aS()));
    jPopupMenu.add(this.eY);
    this.eZ = new JMenuItem("Enable All Slots");
    this.eZ.addActionListener(new bY(this));
    this.eZ.setEnabled(!(!bO.a(parambO).dp() && !en.aS()));
    jPopupMenu.add(this.eZ);
    this.fa = new JMenuItem("Repair Slot");
    this.fa.addActionListener(new bZ(this, paramInt1, paramInt2));
    this.fa.setVisible(bO.a(parambO).dq());
    jPopupMenu.add(this.fa);
    this.fb = new JMenuItem("Repair All Slots");
    this.fb.addActionListener(new ca(this));
    this.fb.setVisible(bO.a(parambO).dq());
    jPopupMenu.add(this.fb);
    this.fc = new JCheckBoxMenuItem("Supercharged");
    this.fc.addActionListener(new cb(this, paramInt1, paramInt2));
    this.fc.setVisible(bO.a(parambO).do());
    jPopupMenu.add(this.fc);
    this.fd = new JMenuItem("Supercharge All Slots");
    this.fd.addActionListener(new cc(this));
    this.fd.setVisible(bO.a(parambO).do());
    jPopupMenu.add(this.fd);
    jPopupMenu.addSeparator();
    this.fe = new JMenuItem("Item Details");
    this.fe.addActionListener(new cd(this, paramInt1, paramInt2));
    jPopupMenu.add(this.fe);
    this.ff = new JMenuItem("Add Item");
    this.ff.addActionListener(new ce(this, paramInt1, paramInt2));
    jPopupMenu.add(this.ff);
    this.fg = new JMenuItem("Repair Item");
    this.fg.addActionListener(new cf(this, paramInt1, paramInt2));
    jPopupMenu.add(this.fg);
    this.fh = new JMenuItem("Move Item");
    this.fh.addActionListener(new bU(this, paramInt1, paramInt2));
    jPopupMenu.add(this.fh);
    this.fi = new JMenuItem("Fill Stack");
    this.fi.addActionListener(new bV(this, paramInt1, paramInt2));
    jPopupMenu.add(this.fi);
    this.fj = new JMenuItem("Delete Item");
    this.fj.addActionListener(new bW(this, paramInt1, paramInt2));
    jPopupMenu.add(this.fj);
    setComponentPopupMenu(jPopupMenu);
    setBorder(bO.eP);
    addMouseListener(new bX(this, paramInt1, paramInt2));
    aq();
  }
  
  private boolean ao() {
    return bO.a(this.eX).h(this.x, this.y);
  }
  
  private boolean ap() {
    return bO.a(this.eX).l(this.x, this.y);
  }
  
  private void aq() {
    removeAll();
    this.eY.setEnabled(!(!bO.a(this.eX).dp() && !en.aS()));
    this.eZ.setEnabled(!(!bO.a(this.eX).dp() && !en.aS()));
    this.fa.setVisible(bO.a(this.eX).dq());
    this.fb.setVisible(bO.a(this.eX).dq());
    if (!bO.a(this.eX).h(this.x, this.y)) {
      this.eY.setSelected(false);
      this.fa.setEnabled(false);
      this.fe.setVisible(false);
      this.fg.setVisible(false);
      this.ff.setVisible(true);
      this.ff.setEnabled(false);
      this.fh.setVisible(false);
      this.fi.setVisible(false);
      this.fj.setVisible(false);
      this.fc.setVisible(false);
      setBorder(bO.eP);
      setBackground(bO.ag());
      setToolTipText((String)null);
    } else if (bO.a(this.eX).l(this.x, this.y)) {
      this.eY.setSelected(true);
      this.fa.setEnabled(true);
      this.fe.setVisible(false);
      this.fg.setVisible(false);
      this.ff.setVisible(true);
      this.ff.setEnabled(false);
      this.fh.setVisible(false);
      this.fi.setVisible(false);
      this.fj.setVisible(false);
      this.fc.setVisible(bO.a(this.eX).do());
      if (bO.a(this.eX).k(this.x, this.y)) {
        setBorder(bO.ah());
        this.fc.setState(true);
      } else {
        setBorder(bO.eP);
        this.fc.setState(false);
      } 
      setBackground(bO.ai());
      gu gu = bO.a(this.eX).f(this.x, this.y);
      if (gu == null) {
        setToolTipText((String)null);
      } else {
        ey ey = ey.d(gu.dz());
        boolean bool = (ey instanceof eQ && gu.dC() != 0.0D) ? true : false;
        String str = (ey == null) ? bO.b(gu.dz()) : ey.getName();
        int i = UIManager.getInt("Inventory.iconSize");
        ImageIcon imageIcon = (ey == null) ? null : ey.c(i, i);
        byte b = 0;
        if (imageIcon != null)
          a(imageIcon, i, b++); 
        Color color = bool ? bO.aj() : bO.eO;
        a(str, b++, color);
        a((gu.dA() < 0) ? "" : (String.valueOf(gu.dA()) + "/" + gu.dB()), b++, color);
        setToolTipText(str);
      } 
    } else {
      this.eY.setSelected(true);
      this.fa.setEnabled(false);
      this.fc.setVisible(bO.a(this.eX).do());
      if (bO.a(this.eX).k(this.x, this.y)) {
        setBorder(bO.ah());
        this.fc.setState(true);
      } else {
        setBorder(bO.eP);
        this.fc.setState(false);
      } 
      gu gu = bO.a(this.eX).f(this.x, this.y);
      if (gu == null) {
        this.fe.setVisible(false);
        this.fg.setVisible(false);
        this.ff.setVisible(true);
        this.ff.setEnabled(true);
        this.fh.setVisible(false);
        this.fi.setVisible(false);
        this.fj.setVisible(false);
        setBackground(bO.eK);
        setToolTipText((String)null);
      } else {
        ey ey = ey.d(gu.dz());
        boolean bool = (ey instanceof eQ && gu.dC() != 0.0D) ? true : false;
        this.fe.setVisible(true);
        this.fg.setVisible(bool);
        this.ff.setVisible(false);
        this.ff.setEnabled(false);
        this.fh.setVisible(true);
        this.fi.setVisible(false);
        this.fj.setVisible(true);
        String str1 = gu.getType();
        if (str1.equals("Technology")) {
          setBackground(bO.ak());
          if (gu.dA() >= 0 && gu.dA() < gu.dB()) {
            this.fi.setText("Recharge");
            this.fi.setVisible(true);
          } 
        } else if (str1.equals("Product")) {
          setBackground(bO.al());
          if (gu.dB() > 1) {
            this.fi.setText("Fill Stack");
            this.fi.setVisible(true);
          } 
        } else if (str1.equals("Substance")) {
          setBackground(bO.am());
          if (gu.dB() > 1) {
            this.fi.setText("Fill Stack");
            this.fi.setVisible(true);
          } 
        } else {
          setBackground(bO.an());
        } 
        this.fe.setEnabled((ey != null));
        String str2 = (ey == null) ? bO.b(gu.dz()) : ey.getName();
        int i = UIManager.getInt("Inventory.iconSize");
        ImageIcon imageIcon = (ey == null) ? null : ey.c(i, i);
        byte b = 0;
        if (imageIcon != null)
          a(imageIcon, i, b++); 
        Color color = bool ? bO.aj() : bO.eO;
        a(str2, b++, color);
        a((gu.dA() < 0) ? " " : (String.valueOf(gu.dA()) + "/" + gu.dB()), b++, color);
        setToolTipText(str2);
      } 
    } 
    revalidate();
    updateUI();
  }
  
  private void a(ImageIcon paramImageIcon, int paramInt1, int paramInt2) {
    JLabel jLabel = new JLabel(paramImageIcon);
    jLabel.setPreferredSize(new Dimension(paramInt1, paramInt1));
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.anchor = 10;
    gridBagConstraints.fill = 0;
    gridBagConstraints.insets = new Insets(5, 0, 5, 0);
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = paramInt2;
    add(jLabel, gridBagConstraints);
  }
  
  private void a(String paramString, int paramInt, Color paramColor) {
    JLabel jLabel = new JLabel();
    jLabel.setFont(UIManager.getFont("Inventory.font"));
    jLabel.setBackground((Color)null);
    jLabel.setBorder((Border)null);
    jLabel.setText(paramString);
    jLabel.setForeground(paramColor);
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.anchor = 10;
    gridBagConstraints.fill = 0;
    int i = UIManager.getInt("Inventory.iconSize");
    gridBagConstraints.insets = new Insets((paramInt == 0) ? (i + 10) : 0, 0, 0, 0);
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = paramInt;
    add(jLabel, gridBagConstraints);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\bS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */