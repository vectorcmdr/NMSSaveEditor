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
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class cY extends JDialog {
  private JComboBox gM;
  
  private List gN = Collections.emptyList();
  
  private int gO = -1;
  
  private static cY gP = null;
  
  private cY(Frame paramFrame) {
    super(paramFrame);
    setResizable(false);
    setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
    setTitle("Move Base Computer");
    setModal(true);
    JPanel jPanel1 = new JPanel();
    setContentPane(jPanel1);
    jPanel1.setLayout(new BorderLayout(0, 0));
    JPanel jPanel2 = new JPanel();
    jPanel2.setLayout(new FlowLayout(0));
    jPanel2.add(new JLabel("Please select a base part to swap your base computer with."));
    jPanel1.add(jPanel2, "North");
    JPanel jPanel3 = new JPanel();
    jPanel3.setLayout((LayoutManager)new FormLayout(new ColumnSpec[] { FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("100px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("250px"), FormFactory.LABEL_COMPONENT_GAP_COLSPEC }, new RowSpec[] { FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC }));
    JLabel jLabel = new JLabel("Base Part:");
    jPanel3.add(jLabel, "2, 2, left, center");
    this.gM = new JComboBox();
    this.gM.setModel(new cZ(this));
    jPanel3.add(this.gM, "4, 2, fill, default");
    jPanel1.add(jPanel3, "Center");
    JPanel jPanel4 = new JPanel();
    jPanel4.setLayout(new FlowLayout(2));
    jPanel1.add(jPanel4, "South");
    JButton jButton1 = new JButton("Save");
    jButton1.addActionListener(new da(this));
    jPanel4.add(jButton1);
    getRootPane().setDefaultButton(jButton1);
    JButton jButton2 = new JButton("Cancel");
    jButton2.addActionListener(new db(this));
    jPanel4.add(jButton2);
    getRootPane().registerKeyboardAction(new dc(this), KeyStroke.getKeyStroke(27, 0), 2);
    pack();
  }
  
  private int b(List paramList) {
    this.gN = paramList;
    setLocationRelativeTo(getParent());
    this.gM.setSelectedIndex(0);
    this.gM.updateUI();
    this.gO = -1;
    setVisible(true);
    return this.gO;
  }
  
  public static int a(Container paramContainer, List paramList) {
    if (gP == null) {
      Frame frame = JOptionPane.getFrameForComponent(paramContainer);
      gP = new cY(frame);
    } 
    return gP.b(paramList);
  }
}


/* Location:              E:\NMSSE_Source\NMSSaveEditor.jar!\nomanssave\cY.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */