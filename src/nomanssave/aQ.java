package nomanssave;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class aQ extends JDialog {
  private Dimension dk;
  
  private Dimension dl;
  
  private Dimension dm;
  
  private Dimension dn = null;
  
  private JTextField do;
  
  private JTextField dp;
  
  private static aQ dq;
  
  private aQ(Frame paramFrame) {
    super(paramFrame);
    setResizable(false);
    setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
    setTitle("Expand Inventory");
    setModal(true);
    JPanel jPanel1 = new JPanel();
    setContentPane(jPanel1);
    jPanel1.setLayout(new BorderLayout(0, 0));
    JPanel jPanel2 = new JPanel();
    jPanel2.setLayout((LayoutManager)new FormLayout(new ColumnSpec[] { FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("100px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("250px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC }, new RowSpec[] { FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC }));
    JLabel jLabel1 = new JLabel("Width:");
    jPanel2.add(jLabel1, "2, 2, left, center");
    this.do = new JTextField();
    this.do.addFocusListener(new aR(this));
    jPanel2.add(this.do, "4, 2, fill, default");
    JLabel jLabel2 = new JLabel("Height:");
    jPanel2.add(jLabel2, "2, 4, left, center");
    this.dp = new JTextField();
    this.dp.addFocusListener(new aS(this));
    jPanel2.add(this.dp, "4, 4, fill, default");
    jPanel1.add(jPanel2);
    JPanel jPanel3 = new JPanel();
    jPanel3.setLayout(new FlowLayout(2));
    jPanel1.add(jPanel3, "South");
    JButton jButton1 = new JButton("Save");
    jButton1.addActionListener(new aT(this));
    jPanel3.add(jButton1);
    getRootPane().setDefaultButton(jButton1);
    JButton jButton2 = new JButton("Cancel");
    jButton2.addActionListener(new aU(this));
    jPanel3.add(jButton2);
    getRootPane().registerKeyboardAction(new aV(this), KeyStroke.getKeyStroke(27, 0), 2);
    pack();
  }
  
  private Dimension a(Dimension paramDimension1, Dimension paramDimension2, Dimension paramDimension3) {
    this.dk = paramDimension1;
    this.dl = paramDimension2;
    this.dm = paramDimension3;
    this.do.setText(Integer.toString(paramDimension1.width));
    this.dp.setText(Integer.toString(paramDimension1.height));
    this.dn = null;
    setLocationRelativeTo(getParent());
    setVisible(true);
    return this.dn;
  }
  
  public static Dimension a(Container paramContainer, Dimension paramDimension1, Dimension paramDimension2, Dimension paramDimension3) {
    if (dq == null) {
      Frame frame = JOptionPane.getFrameForComponent(paramContainer);
      dq = new aQ(frame);
    } 
    return dq.a(paramDimension1, paramDimension2, paramDimension3);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\aQ.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */