package nomanssave;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class h extends JDialog {
  private ey l = null;
  
  private JTextField m;
  
  private JButton n;
  
  private JComboBox o;
  
  private JComboBox p;
  
  private JComboBox q;
  
  private int r;
  
  private List s = new ArrayList();
  
  private List t = new ArrayList();
  
  private List u = new ArrayList();
  
  private List v = new ArrayList();
  
  private static h w = null;
  
  private h(Frame paramFrame) {
    super(paramFrame);
    setResizable(false);
    setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
    setTitle("Add Item");
    setModal(true);
    JPanel jPanel1 = new JPanel();
    setContentPane(jPanel1);
    jPanel1.setLayout(new BorderLayout(0, 0));
    JPanel jPanel2 = new JPanel();
    jPanel2.setLayout((LayoutManager)new FormLayout(new ColumnSpec[] { FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("100px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("280px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC }, new RowSpec[] { FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC }));
    JLabel jLabel1 = new JLabel("Search:");
    jPanel2.add(jLabel1, "2, 2, left, center");
    JPanel jPanel3 = new JPanel();
    jPanel3.setLayout(new BorderLayout(0, 0));
    this.m = new JTextField();
    this.m.setText("");
    jPanel3.add(this.m, "Center");
    this.n = new JButton("Search");
    this.n.addActionListener(new i(this));
    jPanel3.add(this.n, "East");
    jPanel2.add(jPanel3, "4, 2, fill, default");
    JLabel jLabel2 = new JLabel("Type:");
    jPanel2.add(jLabel2, "2, 4, left, center");
    this.o = new JComboBox();
    this.o.setModel(new j(this));
    jPanel2.add(this.o, "4, 4, fill, default");
    JLabel jLabel3 = new JLabel("Category:");
    jPanel2.add(jLabel3, "2, 6, left, center");
    this.p = new JComboBox();
    this.p.setModel(new k(this));
    jPanel2.add(this.p, "4, 6, fill, default");
    JLabel jLabel4 = new JLabel("Item:");
    jPanel2.add(jLabel4, "2, 8, left, center");
    this.q = new JComboBox();
    this.q.setModel(new l(this));
    jPanel2.add(this.q, "4, 8, fill, default");
    jPanel1.add(jPanel2, "Center");
    JPanel jPanel4 = new JPanel();
    jPanel4.setLayout(new FlowLayout(2));
    jPanel1.add(jPanel4, "South");
    JButton jButton1 = new JButton("Save");
    jButton1.addActionListener(new m(this));
    jPanel4.add(jButton1);
    getRootPane().setDefaultButton(jButton1);
    JButton jButton2 = new JButton("Cancel");
    jButton2.addActionListener(new n(this));
    jPanel4.add(jButton2);
    getRootPane().registerKeyboardAction(new o(this), KeyStroke.getKeyStroke(27, 0), 2);
    pack();
  }
  
  private void a() {
    this.t = (List)this.s.stream().map(ey::ba).distinct().sorted((parameB1, parameB2) -> parameB1.name().compareTo(parameB2.name())).collect(Collectors.toList());
    this.o.setSelectedIndex((this.t.size() == 1) ? 0 : -1);
    this.o.updateUI();
    b();
  }
  
  private void b() {
    eB eB = (eB)this.o.getSelectedItem();
    this.u = (List)this.s.stream().filter(paramey -> (paramey.ba() == parameB)).map(ey::bc).distinct().sorted((paramex1, paramex2) -> paramex1.name().compareTo(paramex2.name())).collect(Collectors.toList());
    this.p.setSelectedIndex((this.u.size() == 1) ? 0 : -1);
    this.p.updateUI();
    c();
  }
  
  private void c() {
    eB eB = (eB)this.o.getSelectedItem();
    ex ex = (ex)this.p.getSelectedItem();
    this.v = (List)this.s.stream().filter(paramey -> (paramey.ba() == parameB && paramey.bc() == paramex && (paramex != ex.iZ || !paramey.be()))).sorted((paramey1, paramey2) -> paramey1.getName().compareTo(paramey2.getName())).collect(Collectors.toList());
    this.q.setSelectedIndex((this.v.size() == 1) ? 0 : -1);
    this.q.updateUI();
  }
  
  private ey a(int paramInt) {
    this.r = paramInt;
    this.s = ey.b(paramInt, this.m.getText());
    a();
    this.l = null;
    setLocationRelativeTo(getParent());
    setVisible(true);
    return this.l;
  }
  
  public static ey a(Container paramContainer, int paramInt) {
    if (w == null) {
      Frame frame = JOptionPane.getFrameForComponent(paramContainer);
      w = new h(frame);
    } 
    return w.a(paramInt);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\h.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */