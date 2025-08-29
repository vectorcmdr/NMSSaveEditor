package nomanssave;

import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class I extends em {
  private JComboBox bh = new JComboBox();
  
  private JTextField bi;
  
  private G bj;
  
  private JComboBox bk;
  
  private G bl;
  
  private JTextField bm;
  
  private JButton bn;
  
  private JButton bo;
  
  private JButton bp;
  
  private bO bq;
  
  private ge br;
  
  I(Application paramApplication) {
    this.bh.setModel(new J(this));
    a("Base NPC", true, this.bh);
    this.bi = new JTextField();
    this.bi.setEnabled(false);
    a("Race", this.bi);
    this.bj = new K(this);
    this.bj.setEnabled(false);
    a("Seed", this.bj);
    Y();
    this.bk = new JComboBox();
    this.bk.setModel(new L(this));
    a("Base Info", true, this.bk);
    this.bl = new M(this);
    a("Name", this.bl);
    this.bm = new JTextField();
    this.bm.setEnabled(false);
    a("Items", this.bm);
    JPanel jPanel = new JPanel();
    this.bn = new JButton("Backup");
    this.bn.addActionListener(new N(this, paramApplication));
    jPanel.add(this.bn);
    this.bo = new JButton("Restore");
    this.bo.addActionListener(new O(this, paramApplication));
    jPanel.add(this.bo);
    this.bp = new JButton("Move Base Computer");
    this.bp.addActionListener(new P(this, paramApplication));
    jPanel.add(this.bp);
    a(jPanel);
    this.bq = new bO(paramApplication);
    b(this.bq);
  }
  
  void w() {
    this.bq.w();
  }
  
  void x() {
    this.bq.x();
  }
  
  void y() {
    this.bq.y();
  }
  
  void A() {
    this.bq.A();
  }
  
  void a(gt paramgt) {
    this.bq.a(paramgt);
  }
  
  ge O() {
    return this.br;
  }
  
  void a(ge paramge) {
    List list;
    this.br = paramge;
    if (paramge == null) {
      list = Collections.emptyList();
      this.bh.setSelectedIndex(-1);
      this.bk.setSelectedIndex(-1);
    } else {
      list = paramge.cC();
      this.bh.setSelectedIndex((paramge.cD().size() == 0) ? -1 : 0);
      this.bk.setSelectedIndex((paramge.cE().size() == 0) ? -1 : 0);
    } 
    this.bh.updateUI();
    this.bk.updateUI();
    this.bq.a(list);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\I.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */