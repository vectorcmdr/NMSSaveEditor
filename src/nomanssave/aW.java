package nomanssave;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class aW extends JDialog {
  private JTextField ds;
  
  private JCheckBox dt;
  
  private JCheckBox du;
  
  private JRadioButton dv;
  
  private JRadioButton dw;
  
  private static aW dx;
  
  private aW(cy paramcy) {
    super(paramcy);
    setSize(400, 250);
    setResizable(false);
    setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
    setTitle("Find");
    setModal(true);
    JPanel jPanel1 = new JPanel();
    setContentPane(jPanel1);
    jPanel1.setLayout(new BorderLayout(0, 0));
    JPanel jPanel2 = new JPanel();
    jPanel2.setLayout((LayoutManager)new FormLayout(new ColumnSpec[] { FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("100px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("250px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC }, new RowSpec[] { FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC }));
    JLabel jLabel = new JLabel("Find:");
    jPanel2.add(jLabel, "2, 2, left, center");
    this.ds = new JTextField();
    jPanel2.add(this.ds, "4, 2, fill, default");
    jPanel1.add(jPanel2);
    JPanel jPanel3 = new JPanel();
    jPanel3.setLayout(new GridLayout(1, 2));
    jPanel3.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Direction"), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
    this.dv = new JRadioButton("Forward");
    this.dv.setSelected(true);
    jPanel3.add(this.dv);
    this.dw = new JRadioButton("Backward");
    jPanel3.add(this.dw);
    ButtonGroup buttonGroup = new ButtonGroup();
    buttonGroup.add(this.dv);
    buttonGroup.add(this.dw);
    jPanel2.add(jPanel3, "2, 4, 3, 1");
    JPanel jPanel4 = new JPanel();
    jPanel4.setLayout(new GridLayout(1, 2));
    jPanel4.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Options"), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
    this.dt = new JCheckBox("Case Sensitive");
    this.dt.setSelected(true);
    jPanel4.add(this.dt);
    this.du = new JCheckBox("Wrap Search");
    jPanel4.add(this.du);
    jPanel2.add(jPanel4, "2, 6, 3, 1");
    JPanel jPanel5 = new JPanel();
    jPanel5.setLayout(new FlowLayout(2));
    jPanel1.add(jPanel5, "South");
    JButton jButton1 = new JButton("Find");
    jButton1.setMnemonic(10);
    jButton1.addActionListener(new aX(this, paramcy));
    jPanel5.add(jButton1);
    getRootPane().setDefaultButton(jButton1);
    JButton jButton2 = new JButton("Cancel");
    jButton2.setMnemonic(27);
    jButton2.addActionListener(new aY(this));
    jPanel5.add(jButton2);
    getRootPane().registerKeyboardAction(new aZ(this), KeyStroke.getKeyStroke(27, 0), 2);
    pack();
  }
  
  public static void a(cy paramcy, String paramString) {
    if (dx == null)
      dx = new aW(paramcy); 
    dx.setLocationRelativeTo(paramcy);
    if (paramString != null)
      dx.ds.setText(paramString); 
    dx.ds.setSelectionStart(0);
    dx.ds.setSelectionEnd(dx.ds.getText().length());
    dx.ds.requestFocus();
    dx.setVisible(true);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\aW.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */