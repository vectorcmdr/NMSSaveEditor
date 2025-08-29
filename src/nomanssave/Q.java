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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class Q extends JDialog {
  private W bw;
  
  private int bx;
  
  private int by;
  
  private W bz = null;
  
  private JTextField bA;
  
  private JTextField bB;
  
  private static Q bC;
  
  private Q(Frame paramFrame) {
    super(paramFrame);
    setResizable(false);
    setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
    setTitle("Change Stack Sizes");
    setModal(true);
    JPanel jPanel1 = new JPanel();
    setContentPane(jPanel1);
    jPanel1.setLayout(new BorderLayout(0, 0));
    JPanel jPanel2 = new JPanel();
    jPanel2.setLayout((LayoutManager)new FormLayout(new ColumnSpec[] { FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("100px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("250px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC }, new RowSpec[] { FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("20dlu"), FormFactory.LINE_GAP_ROWSPEC }));
    JLabel jLabel1 = new JLabel("Substances:");
    jPanel2.add(jLabel1, "2, 2, left, center");
    this.bA = new JTextField();
    this.bA.addFocusListener(new R(this));
    jPanel2.add(this.bA, "4, 2, fill, default");
    JLabel jLabel2 = new JLabel("Products:");
    jPanel2.add(jLabel2, "2, 4, left, center");
    this.bB = new JTextField();
    this.bB.addFocusListener(new S(this));
    jPanel2.add(this.bB, "4, 4, fill, default");
    JLabel jLabel3 = new JLabel("<html><font color=\"red\"><b>Please Note: No Man's Sky sometimes reverts these settings back to default.</b></font></html>");
    jPanel2.add(jLabel3, "2, 6, 3, 1, fill, center");
    jPanel1.add(jPanel2);
    JPanel jPanel3 = new JPanel();
    jPanel3.setLayout(new FlowLayout(2));
    jPanel1.add(jPanel3, "South");
    JButton jButton1 = new JButton("Save");
    jButton1.addActionListener(new T(this));
    jPanel3.add(jButton1);
    getRootPane().setDefaultButton(jButton1);
    JButton jButton2 = new JButton("Cancel");
    jButton2.addActionListener(new U(this));
    jPanel3.add(jButton2);
    getRootPane().registerKeyboardAction(new V(this), KeyStroke.getKeyStroke(27, 0), 2);
    pack();
  }
  
  private W a(W paramW, int paramInt1, int paramInt2) {
    this.bw = paramW;
    this.bx = paramInt1;
    this.by = paramInt2;
    this.bA.setText(Integer.toString(paramW.bE));
    this.bB.setText(Integer.toString(paramW.bF));
    this.bz = null;
    setLocationRelativeTo(getParent());
    setVisible(true);
    return this.bz;
  }
  
  public static W a(Container paramContainer, W paramW, int paramInt1, int paramInt2) {
    if (bC == null) {
      Frame frame = JOptionPane.getFrameForComponent(paramContainer);
      bC = new Q(frame);
    } 
    return bC.a(paramW, paramInt1, paramInt2);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\Q.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */